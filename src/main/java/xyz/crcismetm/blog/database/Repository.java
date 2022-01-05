package xyz.crcismetm.blog.database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class Repository<T> {
    private final Database database;
    private final String tableName;
    private Map<String, Field> fields;
    private Map<String, Method> methodsOfGet;
    private Map<String, Method> methodsOfSet;
    private final Class<T> model;

    public Repository(String tableName, Class<T> model, Database database) {
        this.tableName = tableName;
        this.model = model;
        this.database = database;
        init();
    }

    private void init() {
        fields = new HashMap<>();
        methodsOfGet = new HashMap<>();
        methodsOfSet = new HashMap<>();
        for (Field field : model.getDeclaredFields()) {
            try {
                String methodName = field.getName().substring(0, 1).toUpperCase(Locale.ROOT) + field.getName().substring(1);
                methodsOfSet.put(field.getName(), model.getMethod("set" + methodName, field.getType()));
                methodsOfGet.put(field.getName(), model.getMethod("get" + methodName));
                fields.put(field.getName(), field);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                System.out.println("initializing failed");
            }
        }
    }

    public int save(T entity) {
        StringJoiner preparedColumnNames = new StringJoiner(",");
        StringJoiner preparedPosition = new StringJoiner(",");
        Map<Integer, Object> values = new HashMap<>();
        int index = 1;
        for (Map.Entry<String, Method> set : methodsOfGet.entrySet()) {
            String fieldName = set.getKey();
            Method methodOfGet = set.getValue();
            try {
                Object value = methodOfGet.invoke(entity);
                if (value != null) {
                    preparedColumnNames.add(fieldName);
                    preparedPosition.add("?");
                    values.put(index++, value);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        String preparedSQL = String.format("INSERT INTO %s (%s) VALUES (%s)",
                tableName,
                preparedColumnNames.toString(),
                preparedPosition.toString());
        return database.executeUpdate(preparedSQL, values);
    }

    public List<T> find(T entity) {
        StringJoiner preparedColumnNames = new StringJoiner(",");
        StringJoiner preparedPosition = new StringJoiner(" AND ");
        Map<Integer, Object> values = new HashMap<>();
        int index = 1;
        for (Map.Entry<String, Method> set : methodsOfGet.entrySet()) {
            String fieldName = set.getKey();
            Method methodOfGet = set.getValue();
            try {
                Object value = methodOfGet.invoke(entity);
                if (value != null) {
                    preparedColumnNames.add(fieldName);
                    preparedPosition.add(fieldName + "=?");
                    values.put(index++, value);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (values.size() == 0) {
            throw new EntityValuesEmptyException("findAll");
        }
        String preparedSQL = String.format("SELECT * FROM %s WHERE %s", tableName, preparedPosition.toString());
        return dealWithResultSet(database.executeQuery(preparedSQL, values));
    }

    public List<T> findAll() {
        String preparedSQL = String.format("SELECT * FROM %s", tableName);
        return dealWithResultSet(database.executeQuery(preparedSQL, new HashMap<>()));
    }

    public int update(T newEntity, T oldEntity) {
        StringJoiner preparedSetPosition = new StringJoiner(",");
        StringJoiner preparedConditionsPosition = new StringJoiner(" AND ");
        Map<Integer, Object> values = new HashMap<>();
        int index = 1;
        for (Map.Entry<String, Method> set : methodsOfGet.entrySet()) {
            String fieldName = set.getKey();
            Method methodOfGet = set.getValue();
            try {
                Object value = methodOfGet.invoke(newEntity);
                if (value != null) {
                    preparedSetPosition.add(fieldName + "=?");
                    values.put(index++, value);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        for (Map.Entry<String, Method> set : methodsOfGet.entrySet()) {
            String fieldName = set.getKey();
            Method methodOfGet = set.getValue();
            try {
                Object value = methodOfGet.invoke(oldEntity);
                if (value != null) {
                    preparedConditionsPosition.add(fieldName + "=?");
                    values.put(index++, value);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        String preparedSQL = String.format("UPDATE %s SET %s  WHERE %s"
                , tableName
                , preparedSetPosition.toString()
                , preparedConditionsPosition.toString());
        return database.executeUpdate(preparedSQL, values);
    }

    public int delete(T entity) {
        StringJoiner preparedColumnNames = new StringJoiner(",");
        StringJoiner preparedPosition = new StringJoiner(" AND ");
        Map<Integer, Object> values = new HashMap<>();
        int index = 1;
        for (Map.Entry<String, Method> set : methodsOfGet.entrySet()) {
            String fieldName = set.getKey();
            Method methodOfGet = set.getValue();
            try {
                Object value = methodOfGet.invoke(entity);
                if (value != null) {
                    preparedColumnNames.add(fieldName);
                    preparedPosition.add(fieldName + "=?");
                    values.put(index++, value);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (values.size() == 0) {
            throw new EntityValuesEmptyException("deleteAll");
        }
        String preparedSQL = String.format("DELETE FROM %s WHERE %s", tableName, preparedPosition.toString());
        return database.executeUpdate(preparedSQL, values);
    }

    public int deleteAll() {
        String preparedSQL = String.format("DELETE FROM %s ", tableName);
        return database.executeUpdate(preparedSQL, new HashMap<>());
    }

    private List<T> dealWithResultSet(ResultSet resultSet) {
        List<T> entities = new ArrayList<>();
        try {
            ResultSetMetaData meta = resultSet.getMetaData();
            int cols = meta.getColumnCount();
            while (resultSet.next()) {
                T entity = model.getConstructor().newInstance();
                for (int n = 1; n <= cols; n++) {
                    String colName = meta.getColumnName(n);
                    if (fields.get(colName) != null) {
                        Class<?> type = fields.get(colName).getType();
                        methodsOfSet.get(colName).invoke(entity, resultSet.getObject(colName, type));
                    }
                }
                entities.add(entity);
            }
        } catch (SQLException nextResultException) {
            System.out.println(nextResultException.getMessage());
            nextResultException.printStackTrace();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException newInstanceException) {
            System.out.println(newInstanceException.getMessage());
        }
        return entities;
    }

    public int create(String... createSQLs) {
        StringJoiner joiner = new StringJoiner(",", "CREATE TABLE IF NOT EXISTS " + tableName + " (", ")");
        for (String sql : createSQLs) {
            joiner.add(sql);
        }
        return database.executeUpdate(joiner.toString(), new HashMap<>());
    }

    public List<T> rawQuery(String preparedSQL, String... args) {
        Map<Integer, Object> values = new HashMap<>();
        int index = 1;
        for (String value : args) {
            values.put(index, value);
        }
        return dealWithResultSet(database.executeQuery(preparedSQL, values));
    }
}
