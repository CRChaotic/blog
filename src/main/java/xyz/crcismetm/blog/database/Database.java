package xyz.crcismetm.blog.database;

import java.sql.*;
import java.util.Map;

public class Database {
    private Connection database;
    public Database(String url,String username, String password){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            database = DriverManager.getConnection(url,username,password);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            System.out.println("connecting failed");
        }
    }

    public int executeUpdate(String preparedSQL, Map<Integer,Object> values){
        System.out.println(preparedSQL);
        values.forEach((integer, o) -> {
            System.out.println(integer+"="+o);
        });
        try {
            PreparedStatement statement = database.prepareStatement(preparedSQL);
            for (Map.Entry<Integer,Object> entry: values.entrySet()) {
                statement.setObject(entry.getKey(),entry.getValue());
            }
            return statement.executeUpdate();
        } catch (SQLException exception) {
            try {
                if(!database.getAutoCommit()){
                    database.rollback();
                    System.out.println("database: rollback");
                    return -1;
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            exception.printStackTrace();
            return -2;
        }
    }

    public ResultSet executeQuery(String preparedSQL, Map<Integer, Object> values){
        System.out.println(preparedSQL);
        values.forEach((integer, o) -> {
            System.out.println(integer+"="+o);
        });
        try {
            PreparedStatement statement = database.prepareStatement(preparedSQL);
            for (Map.Entry<Integer,Object> entry: values.entrySet()) {
                statement.setObject(entry.getKey(),entry.getValue());
            }
            return statement.executeQuery();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public boolean startAtomicAction(){
        try {
            database.setAutoCommit(false);
            return true;
        } catch (SQLException exception) {
            return false;
        }
    }

    public boolean endAtomicAction(){
        try {
            if(!database.getAutoCommit()){
                database.commit();
                database.setAutoCommit(true);
                return true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
