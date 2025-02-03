package org.example.U3.model;

import java.util.Arrays;
import java.util.Optional;

/** Cake */
public class Cake implements IBakeryItem {

  private Fillings[] fillings;
  private int numSlices;
  private double price;
  private String name;

  public Cake(int numSlices, String name, Fillings... fillings) {

    if (numSlices > 0) {
      this.numSlices = numSlices;
    } else {
      this.numSlices = 1;
    }
    Optional<String> optName = Optional.ofNullable(name);
    this.name = optName
        .map(String::trim)
        .filter(n -> !n.isEmpty())
        .filter(n -> !n.isBlank())
        .orElseThrow();

    this.fillings = fillings;
    this.price = calculatePrice(numSlices, fillings);

  }

  public double getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  /**
   * @param name
   */
  @Override
  public void setName(String name) {
    this.name = name;

  }

  public int getNumSlices() {
    return numSlices;
  }

  public Fillings[] getFillings() {
    return fillings;
  }

  @Override
  public String toString(){
    return "Slices: "+this.numSlices+"\n Name: "+this.name+"\n Fillings: "+ Arrays.toString(fillings) +"\n Price: "+price;
  }

  /**
   * @return 
   */
  @Override
  public double calculatePrice(int numSlices, Fillings[] fillings) {

    short BASE_PRICE = 100;
    double sum = 0;
    for (Fillings fill : fillings) {
      sum += fill.getPrice();
    }
    return (numSlices * fillings.length) + BASE_PRICE+ sum;
  }

}
