package xyz.crcismetm.blog.database;

public class EntityValuesEmptyException extends RuntimeException{
    public EntityValuesEmptyException(String message) {
        super("Entity values was empty should be using "+message+" instead");
    }
}
