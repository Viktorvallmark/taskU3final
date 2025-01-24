package org.example.U3.model;

public class Danish extends AbstractBakeryItem{


    private final boolean isGlazed;


    public Danish(String name, boolean isGlazed) {
        super(name);
        this.isGlazed = isGlazed;
        this.price = calculatePrice();
    }


    public boolean isGlazed() {
        return isGlazed;
    }

    /**
     * @return 
     */
    @Override
    public double calculatePrice() {
        if(isGlazed){
            return 25;
        }else {
            return 20;
        }
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
