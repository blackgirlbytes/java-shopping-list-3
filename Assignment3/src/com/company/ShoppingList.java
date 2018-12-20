package com.company;
import java.util.Arrays;
import java.util.Scanner;


public class ShoppingList {

    //num of items constant in the array
    final int NUM_ITEMS = 5;
    //the random prices have to be at least $15 for the total to equal 100+
    public double sum = 0;
    public ShoppingListItem[] items;


    private void setItems(ShoppingListItem[] items) {
        this.items = items;

    }

    public void askUserForItems() {
        System.out.println("List name, comma, priority");
        ShoppingListItem[] shoppingList = new ShoppingListItem[NUM_ITEMS];

        Scanner scan = new Scanner(System.in);
        //the user will input an item name and a priority number..that will be added to the shopping list object
        for (int i = 0; i < shoppingList.length; i++) {
            String line = scan.nextLine();
            String input[] = line.split("\\s*,\\s*");
            //if the user's input begins with a space or is only a space, throw an exception
            //accept input if letters, some symbols and spaces in between
            if(!input[0].matches("^[a-zA-Z_'&-]+( [a-zA-Z_'&-]+)*$")){
                throw new IllegalArgumentException("enter only letters and symbols");
            }
            //if the user input's less than 2 items, throw an exception
            if (input.length < 2) {
                throw new IllegalArgumentException("Missing commas or items. Only inserted:" + input.length + " items");

            }
            //if the user inputs more than 2 items, throw an exception
            if (input.length > 2) {
                throw new IllegalArgumentException("Too many items");
            }
            String itemName = input[0];
            for (int j = 0; j < i; j++) {
              //if item name is a duplicate, throw an exception

                if (itemName.equalsIgnoreCase(shoppingList[j].getName())) {
                    throw new IllegalArgumentException(itemName + " item name already exists in the list");
                }

            }

            int priorityInput = Integer.parseInt(input[1]);
            for (int k = 0; k < i; k++) {
                //if priority is a duplicate, throw an exception..I think it would be easier to only have one priority
                if (priorityInput == shoppingList[k].getPriority()) {
                    throw new IllegalArgumentException(priorityInput + " priority num already exists in the list");
                }

            }

            System.out.println("just added to the list: " + itemName + " priority :" + priorityInput);

            double priceInput = Math.random() * 100 ;
            double formattedPrice = Math.floor(priceInput * 100) / 100;
            //if the total is less than 100, find out the difference between the sum and 100
            if (sum < 100) {
                //divide the remainder by the number of items
                double remainder = (100 - sum) / NUM_ITEMS;
                //add it to each item
                formattedPrice += remainder;
            }
            sum += Math.floor(formattedPrice);
            ShoppingListItem listItem = new ShoppingListItem(itemName, priorityInput, formattedPrice, sum);

            shoppingList[i] = listItem;
        }

        setItems(shoppingList);
        for (int f = 0; f < items.length; f++) {
            System.out.println("\nInitial Shopping List");
            System.out.println(items[f].getName() + " $" + Math.floor(items[f].getPrice()) + " priority " + items[f].getPriority());
            System.out.println("the total is " + items[f].getTotal());
        }

    }

    public void sortItems() {
        //user is required to sort list
        System.out.print("Press Y to sort list (Y): ");

        Scanner scan = new Scanner(System.in);
        String userResponse = scan.nextLine();
        //call the comparator sort class and sort by priority
        if (userResponse.equalsIgnoreCase("Y")) {
            Arrays.sort(items, new ShoppingListItem.Sortbypriority());
        }else if(!userResponse.equalsIgnoreCase("Y")){
            throw new IllegalArgumentException("you are required to respond yes (Y) to sort");
        }
        System.out.println("\nSorted by priority");
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i].getName() + " $" + Math.floor(items[i].getPrice()) + " priority " + items[i].getPriority());
            System.out.println("the total is " + items[i].getTotal());

        }
    }

    public void goShopping() {
        double newSum = 0;
        double targetSum = 59;

        System.out.println("\nthe list we are choosing from for prices");
        for (int j = 0; j < items.length; j++) {
            System.out.println(items[j].getName() + " $" + Math.floor(items[j].getPrice()) + " priority " + items[j].getPriority());
        }

        for (int i = 0; i < NUM_ITEMS; i++) {
            //if the item price is less than 59, add them together
            if (items[i].getPrice() < targetSum) {
                newSum += items[i].getPrice();

            } else if (items[i].getPrice() > targetSum) {
                //if the item price is greater than 59, don't add them together. These items are rejected
                System.out.println("Today, you cannot afford " + items[i].getName() + " $" + Math.floor(items[i].getPrice()) + " priority " + items[i].getPriority());
                continue;
            }
            if (newSum <= targetSum) {
                //if the new added items are less than 59, then print the items
                System.out.println("You can afford " + items[i].getName() + " for " + Math.floor(items[i].getPrice()) + " priority " + items[i].getPriority());

            } else if (newSum > targetSum) {
                //if the new added items are greater than 59, then print the items
                newSum -= items[i].getPrice();
                System.out.println("Today, you cannot afford " + items[i].getName() + " $" + Math.floor(items[i].getPrice()) + " priority " + items[i].getPriority());


            }
        }


    }
}
