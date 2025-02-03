package org.example.U3.model;

import java.util.ArrayList;

public class Order {

   private ArrayList<IBakeryItem> bakeryItems;
   private double cost;


   public Order (ArrayList<IBakeryItem> bakeryItems){
       if (!bakeryItems.isEmpty()){
          this.bakeryItems = bakeryItems;
       }
       double temp = 0;
       for (IBakeryItem bakeryItem : bakeryItems){
          temp += bakeryItem.getPrice();
       }
       this.cost = temp;
   }

    public ArrayList<IBakeryItem> getBakeryItems() {
        return bakeryItems;
    }

    public void setBakeryItems(ArrayList<IBakeryItem> bakeryItems) {
        this.bakeryItems = bakeryItems;
    }


    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString(){

       StringBuilder temp = new StringBuilder();
       for(IBakeryItem bakeryItem : bakeryItems){
           temp.append(bakeryItem.toString()).append("\n");
       }

       return temp.toString();
    }

}
