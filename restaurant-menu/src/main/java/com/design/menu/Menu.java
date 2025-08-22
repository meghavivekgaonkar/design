package com.design.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Category> categories;
    
    public Menu(){
        this.categories = new ArrayList<>();
    }
    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public List<Category> getCategories() {
        return categories;
    }

}
