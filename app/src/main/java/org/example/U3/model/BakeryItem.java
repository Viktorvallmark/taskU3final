package org.example.U3.model;

public class BakeryItem implements IBakeryItem{


    private String name;
    private double price;

    public BakeryItem(String name, double price) {
        this.name = name;
        this.price = price;
    }


    /**
     * @return name of the bakery item
     * @author Viktor Vallmark
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     * @author Viktor Vallmark
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     * @author Viktor Vallmark
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * @param numSlices 
     * @param fillings
     * @return 0
     * Never used in this class, but have to be implemented due to the interface
     * @author Viktor Vallmark
     */
    @Override
    public double calculatePrice(int numSlices, Fillings[] fillings) {
        return 0;
    }

    /**
     * @author Viktor Vallmark
     * @return a string representation of a BakeryItem
     */
    @Override
    public String toString() {
        return  name + "\n" +
                ", " + price +
                "\n";
    }

    /**
     *
     * @param price
     * @author Viktor Vallmark
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
