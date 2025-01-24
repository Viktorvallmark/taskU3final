package org.example.U3.model;

public class Cookie extends AbstractBakeryItem {


  public Cookie(String name) {
    super(name);
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

  /**
   * @return 
   */
  @Override
  public double calculatePrice() {
    return 5.0 + name.length();
  }
}
