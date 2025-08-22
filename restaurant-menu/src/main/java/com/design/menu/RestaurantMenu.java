
package com.design.menu;

import com.design.menu.enums.MenuItemType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author meghagaonkar
 */
public class RestaurantMenu {

    class  Input{
         List<String> rawMenuData = new ArrayList<>(List.of(
                "1", "DISH", "Spring Rolls", "5.99", "",
                "2", "DISH", "Garlic Bread", "4.50", "",
                "3", "CATEGORY", "Main Courses", "4", "6", "",
                "4", "DISH", "Chicken Alfredo", "14.99" , "5", "",
                "5", "OPTION", "Extra Chicken", "3.00", "",
                "6", "DISH", "Steak with mashed potatoes", "21.50", "",
                "7", "CATEGORY", "Appetizer", "1", "2", ""
        ));

        Iterator<String> iterator = rawMenuData.iterator();
        public String getString(){
            if (iterator.hasNext()) {
                return iterator.next();
            } else {
                return null; // or throw an exception if preferred  
            }
        }

    }
    /**
     * Parses the input and displays the menu.
     */
    public void parseAndDisplayMenu() {
        Map<String,MenuItem> menu = new HashMap<>();// id - MenuItem mapping
        Map<String, List<String>> categoryMap = new HashMap<>(); // Category to Dish mapping
        Input input = new Input();

        while(true) {
            String next = input.getString();
            if(next == null) {
                break; // End of input
            }
            if(next.isEmpty()) {
                continue; // Skip empty lines
            }
            String id = next;
            MenuItemType type = MenuItemType.valueOf(input.getString());
            String name = input.getString();
            switch (type) {
                case DISH:
                    float price = Float.parseFloat(input.getString());
                    Dish dish = new Dish(id, name, price, MenuItemType.DISH);
                    while(!next.isEmpty()){
                        //add on 
                        next = input.getString();
                        if(!next.isEmpty()){
                            dish.addSubItem(next);
                        }

                    }
                    menu.put(id,dish);
                    break;
                case CATEGORY:
                    Category category= new Category(id, name);
                    while(!next.isEmpty()){
                        next = input.getString();
                        if(!next.isEmpty()){
                            categoryMap.computeIfAbsent(name, k -> new ArrayList<>()).add(next);
                        }
                    }
                    menu.put(id, category);
                    break;
                case OPTION:
                        float optionPrice = Float.parseFloat(input.getString());
                        MenuItem option = new Option(id, name, optionPrice);
                        menu.put(id, option);
                    break;
                default:
                    System.out.println("Unknown type: " + type);
            }
        }
        // Display the menu by Category
        System.out.println("Restaurant Menu:");
        System.out.println("-----------------");
        // Iterate through categories and display dishes
        if (categoryMap.isEmpty()) {
            System.out.println("No categories found.");
        } else {
        // Iterate through the category map to display dishes under each category
            for (Map.Entry<String, List<String>> entry : categoryMap.entrySet()) {
                String categoryName = entry.getKey();
                List<String> dishIds = entry.getValue();
                System.out.println("Category: " + categoryName);
                for (String dishId : dishIds) {
                    Dish dishItem = (Dish)menu.get(dishId);
                    if (dishItem != null) {
                        System.out.println("  - " + dishItem.getName() + " (" + ((Dish) dishItem).getPrice() + ")");
                        List<String> addOns = dishItem.getAddOns();
                        if (!addOns.isEmpty()) {
                            for(String id : addOns) {
                                Option option = (Option) menu.get(id);
                                if (option != null) {
                                    System.out.println("    Add-ons: " + option.getName() + " (+ " + option.getPrice() + ")");
                                }
                            }
                        }
                    }
                }
            }
        
        }
    }

    public static void main(String[] args) {
        RestaurantMenu parser = new RestaurantMenu();
        parser.parseAndDisplayMenu();
       
    }
}
