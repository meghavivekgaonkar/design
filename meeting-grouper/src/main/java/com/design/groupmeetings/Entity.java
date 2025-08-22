package com.design.groupmeetings;

public class  Entity {
    public enum Type { PERSON, ROOM }
    private Type type;
    private String name;

    public Entity(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    public Type getType() { return type; }
    public String getName() { return name; }
}
