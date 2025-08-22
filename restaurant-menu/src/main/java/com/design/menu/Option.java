package com.design.menu;

import com.design.menu.enums.MenuItemType;
import java.math.BigDecimal;

public class Option implements MenuItem {
    private final String id; 
    private final String name;
    private final BigDecimal addtionalPrice;

    public Option(String id, String name, BigDecimal addtionalPrice) {
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

    public BigDecimal getPrice() {
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
