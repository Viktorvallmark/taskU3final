package org.example.U3.model;

public class Bread extends AbstractBakeryItem{


    private int length;

    public Bread(String name, int length) {
        super(name);
        this.length = length;
    }

    /**
     * @return 
     */
    @Override
    public double calculatePrice() {
        return 10+this.length*2;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return 
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @param name 
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 
     */
    @Override
    public double getPrice() {
        return this.price;
    }
}
