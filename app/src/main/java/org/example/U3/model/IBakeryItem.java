package org.example.U3.model;

public interface IBakeryItem {

  String getName();
  void setName(String name);
  double getPrice();
  double calculatePrice(int numSlices, Fillings[] fillings);
  String toString();

}
