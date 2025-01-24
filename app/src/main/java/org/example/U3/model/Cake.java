package org.example.U3.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/** Cake */
public class Cake implements IBakeryItem {

  private Fillings[] fillings;
  private int numSlices;
  private double price;
  private String name;
  private final short BASE_PRICE = 100;

  public Cake(int numSlices, String name, Fillings... fillings) {

    if (numSlices > 0) {
      this.numSlices = numSlices;
    }
    Optional<String> optName = Optional.ofNullable(name);
    this.name = optName
        .map(String::trim)
        .filter(n -> !n.isEmpty())
        .filter(n -> !n.isBlank())
        .orElseThrow();
    //
    double sum = 0;
    for (Fillings fill : fillings) {
      sum += fill.getPrice();
    }
    this.fillings = fillings;
    this.price = (numSlices * fillings.length) + BASE_PRICE + sum;

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
    return "Slices: "+this.numSlices+" Name: "+this.name+" Fillings: "+ Arrays.toString(fillings) +" Price: "+price;
  }

}
