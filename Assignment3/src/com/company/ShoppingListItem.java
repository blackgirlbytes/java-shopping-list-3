package com.company;

import java.util.Comparator;

public class ShoppingListItem {

    private String name;
    private double price;
    private int priority;
    private double total;

    public ShoppingListItem(String name, int priority, double price, double total) {
        //constructor class
        this.name = name;
        this.priority = priority;
        this.price = price;
        this.total = total;

    }

    public String getName() {

        return name;
    }

    public double getPrice() {

        return price;
    }

    public int getPriority() {

        return priority;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ShoppingListItem) {
            ShoppingListItem listItem = (ShoppingListItem) obj;
            return name.equals(listItem.name) && price == listItem.price && priority == listItem.priority;
        }
        return false;
    }

    public static class Sortbypriority implements Comparator<ShoppingListItem> {
        // Used for sorting in ascending order of priority
        public int compare(ShoppingListItem a, ShoppingListItem b) {
            return a.priority - b.priority;
        }
    }


}

