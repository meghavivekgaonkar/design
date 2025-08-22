package com.design.menu;

import com.design.menu.enums.MenuItemType;

public class Option implements MenuItem {
    private final String id; 
    private final String name;
    private final float addtionalPrice;

    public Option(String id, String name, float addtionalPrice) {
        this.id = id;
        this.name = name;
        this.addtionalPrice = addtionalPrice;
    }
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public float getPrice() {
        return addtionalPrice;
    }

    @Override
    public String toString() {
        return "Option{" +
                "name='" + name + '\'' +
                ", addtionalPrice=" + addtionalPrice +
                '}';
    }


    @Override
    public MenuItemType getType() {
       return MenuItemType.OPTION;
    }
    
}
