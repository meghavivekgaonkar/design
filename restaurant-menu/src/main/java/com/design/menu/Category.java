package com.design.menu;

import com.design.menu.enums.MenuItemType;

public class Category implements MenuItem {
    private final String id;
    private String name;

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public MenuItemType getType() {
        return MenuItemType.CATEGORY;
    }

}
