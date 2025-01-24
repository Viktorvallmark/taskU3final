package org.example.U3.model;

public abstract class AbstractBakeryItem implements IBakeryItem{


    String name;
    double price;


    public AbstractBakeryItem (String name) {
       this.name = name;
       this.price = calculatePrice();
    }

    public abstract double calculatePrice();



    @Override
    public String toString(){
        return name + " "+price;
    }


}
