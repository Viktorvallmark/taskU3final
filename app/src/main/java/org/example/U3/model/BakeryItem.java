package org.example.U3.model;

public class BakeryItem implements IBakeryItem{


    private String name;
    private double price;

    public BakeryItem(String name, double price) {
        this.name = name;
        this.price = price;
    }


    /**
     * @return 
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * @param numSlices 
     * @param fillings
     * @return
     */
    @Override
    public double calculatePrice(int numSlices, Fillings[] fillings) {
        return 0;
    }

    @Override
    public String toString() {
        return  name + "\n" +
                ", " + price +
                "\n";
    }

    /**
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
