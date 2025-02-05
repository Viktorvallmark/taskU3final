package org.example.U3.model;

/**
 * @author Viktor Vallmark
 * This handles first and foremost how to calculate the price of a Cake object.
 * This is used to add polymorphism.
 */
public interface IBakeryItem {

  String getName();
  void setName(String name);
  double getPrice();
  double calculatePrice(int numSlices, Fillings[] fillings);
  String toString();

}
