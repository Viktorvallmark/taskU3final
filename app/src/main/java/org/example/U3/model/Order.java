package org.example.U3.model;

import java.util.ArrayList;

public class Order {

   private ArrayList<AbstractBakeryItem> bakeryItems;


    private double cost;


   public Order (ArrayList<AbstractBakeryItem> bakeryItems){
       if (!bakeryItems.isEmpty()){
          this.bakeryItems = bakeryItems;
       }
       double temp = 0;
       for (AbstractBakeryItem bakeryItem : bakeryItems){
          temp += bakeryItem.getPrice();
       }
       this.cost = temp;
   }

    public ArrayList<AbstractBakeryItem> getBakeryItems() {
        return bakeryItems;
    }

    public void setBakeryItems(ArrayList<AbstractBakeryItem> bakeryItems) {
        this.bakeryItems = bakeryItems;
    }


    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


}
