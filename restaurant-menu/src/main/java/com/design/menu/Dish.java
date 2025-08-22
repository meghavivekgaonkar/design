package com.design.menu;

import com.design.menu.enums.MenuItemType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Dish implements MenuItem {
    private final String id;
    private final String name;
    private final BigDecimal price;
    private List<String> addOns;

    public Dish(String id, String name, BigDecimal price, MenuItemType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        addOns = new ArrayList<>();
    }
    public String getId() {
        return id;
    }
    
    @Override
    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public MenuItemType getType() {
        return MenuItemType.DISH;
    }
    // Add sub-item method
    public void addSubItem(String item) {
        this.addOns.add(item);
    }
    //get addOns
    public List<String> getAddOns() {
        return addOns;  
    }

}
