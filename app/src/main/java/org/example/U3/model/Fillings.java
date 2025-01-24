package org.example.U3.model;

public enum Fillings {
  Strawberry(20),
  Raspberry(15),
  Chocolate(30),
  Vanilla(25),
  Cream(10),
  Banana(5);

  private final int price;

  Fillings(int price) {
    this.price = price;
  }

  int getPrice() {
    return this.price;
  }
}
