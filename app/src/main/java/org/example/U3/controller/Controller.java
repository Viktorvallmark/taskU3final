package org.example.U3.controller;

// only imports what is strictly necessary from view-package
import org.example.U3.model.*;
import org.example.U3.view.ButtonType;
import org.example.U3.view.CustomCakeFrame;
import org.example.U3.view.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
  private MainFrame view;
  private CustomCakeFrame newCakeType;
  private ButtonType currentLeftMenu = ButtonType.NoChoice;


  private ArrayList<Cake> cakes;
  private ArrayList<Order> orders;
  private String[] cakeMenuString; // for test purposes only
  private String[] perUnitItemMenuString; // for test purposes only
  private AbstractBakeryItem[] perItemArray;
  private String[] orderHistoryMenuString; // for test purposes only
  private String[] orderArray; // for test purposes only
  private String[] currentOrderArray; // for test purposes only
  private double costCurrentOrder = 0; // for test purposes only
  private int nbrOfOrders = 0; // for test purposes only
  private int orderCounter = 0;

  public Controller() {
    view = new MainFrame(1000, 500, this);
    cakes = new ArrayList<>(5);
    loadStringCakeValues();
    view.enableAllButtons();
    view.disableAddMenuButton();
    view.disableViewSelectedOrderButton();
  }

  private void loadStringCakeValues() {
    Fillings[] fillingsBanana = new Fillings[3];
    Fillings[] fillingsChocolate = new Fillings[3];
    Fillings[] fillingsBerryFantasy = new Fillings[3];
    fillingsBanana[0] = Fillings.Banana;
    fillingsBanana[1] = Fillings.Chocolate;
    fillingsBanana[2] = Fillings.Cream;
    cakes.add(new Cake(5, "Banana Split", fillingsBanana));

    fillingsChocolate[0] = Fillings.Cream;
    fillingsChocolate[1] = Fillings.Chocolate;
    fillingsChocolate[2] = Fillings.Vanilla;
    cakes.add(new Cake(5, "Chocolate", fillingsChocolate));

    fillingsBerryFantasy[0] = Fillings.Cream;
    fillingsBerryFantasy[1] = Fillings.Strawberry;
    fillingsBerryFantasy[2] = Fillings.Raspberry;
    cakes.add(new Cake(5, "Berry Fantasy", fillingsBerryFantasy));



    cakeMenuString = new String[100];
    perUnitItemMenuString = new String[3];
    orderHistoryMenuString = new String[100];
    orderArray = new String[100];
    currentOrderArray = new String[100];
/*
    cakeMenuString[0] = "tårta0, storlek: 4 bitar, topping1, topping2, Pris0";
    cakeMenuString[1] = "tårta1, storlek: 6 bitar, topping1, topping3, Pris1";
    cakeMenuString[2] = "tårta2, storlek: 4 bitar, topping1, topping2, Pris2";
    cakeMenuString[3] = "tårta3, storlek: 12 bitar,topping1, topping3, Pris3";
*/
    perItemArray = new AbstractBakeryItem[3];
    Bread bread = new Bread("Rye", 10);
    Cookie cookie = new Cookie("Chocolate cookie");
    Danish danish = new Danish("Wiener bread", true);
    perItemArray[0] = bread;
    perItemArray[1] = cookie;
    perItemArray[2] = danish;

    for (int i = 0; i < perItemArray.length; i++) {
      perUnitItemMenuString[i] = perItemArray[i].getName()+", "+perItemArray[i].getPrice();
    }
  }

  // This method is called by class MainFrame when a button in the GUI is pressed
  public void buttonPressed(ButtonType button) {

    switch (button) {
      case Add:
        addItemToOrder(view.getSelectionLeftPanel());
        break;

      case Cake:
        setToCakeMenu();
        break;

      case PerUnitItem:
        setToPerUnitItemMenu();
        break;

      case MakeCake:
        setToAddNewCakeMenu();
        break;

      case OrderHistory:
        setToOrderHistoryMenu();
        break;

      case Order:
        placeOrder();
        break;

      case ViewOrder:
        viewSelectedOrder(view.getSelectionLeftPanel());
        break;

      case NoChoice:
        break;
    }
  }
  public Cake makeCake(String name, int numSlices, Fillings... fillings){
   return new Cake(numSlices,name,fillings);
  }

  public ArrayList<Cake> getCakes() {
    return cakes;
  }

  public void setCakes(ArrayList<Cake> cakes) {
    this.cakes = cakes;
  }

  public void addItemToOrder(int selectionIndex) {
    System.out.println(
        "Index selection left panel: "
            + selectionIndex); // for test purposes - remove when not needed
    double order = 0;
    if (selectionIndex != -1) { // if something is selected in the left menu list
      switch (currentLeftMenu) { // This might need to change depending on architecture
        case Cake:
          currentOrderArray[nbrOfOrders] = cakeMenuString[selectionIndex]; // for test purposes - needs to be replaced with solution of finding
          // chosen menu item matching architecture for model
          order += cakes.get(selectionIndex).getPrice();
          break;
        case PerUnitItem:
          currentOrderArray[nbrOfOrders] = perUnitItemMenuString[selectionIndex]; // see comment
          // for case above
          order += perItemArray[selectionIndex].getPrice();
          break;
      }
      costCurrentOrder += order;
      nbrOfOrders++; // for test purposes - need to be removed or changed when model for handling
      // orders is implemented
      view.populateRightPanel(
          currentOrderArray); // update left panel with new item - this takes a shortcut in updating
      // the entire information in the panel not just adds to the end
      view.setTextCostLabelRightPanel(
          "Total cost of order: "
              + costCurrentOrder); // set the text to show cost of current order
    }
  }

  public void viewSelectedOrder(int selectionIndex) {
    System.out.println(
        "Index selection left panel: "
            + selectionIndex); // for test purposes - remove when not needed

    if ((selectionIndex != -1) && currentLeftMenu == ButtonType.OrderHistory) {

      view.populateRightPanel(
              new String[]{orderArray[selectionIndex]}); // update left panel with order details - this takes a shortcut in
      // updating the entire information in the panel not just adds to the
      // end
      view.setTextCostLabelRightPanel(
          "Total cost of order: "
              + String.valueOf(orderArray[selectionIndex])); // set the text to show cost of current order
    }
  }

  public void setToCakeMenu() {
    currentLeftMenu = ButtonType.Cake;
    for (int i = 0; i < cakes.size(); i++){
      cakeMenuString[i] = cakes.get(i).toString();
    }
    view.populateLeftPanel(cakeMenuString);
    view.populateRightPanel(
        currentOrderArray); // update left panel with new item - this takes a shortcut in updating
    // the entire information in the panel not just adds to the end
    view.setTextCostLabelRightPanel(
        "Total cost of order: "
            + String.valueOf(costCurrentOrder)); // set the text to show cost of current order
    view.enableAllButtons();
    view.disableCakeMenuButton();
    view.disableViewSelectedOrderButton();
  }

  public void setToPerUnitItemMenu() {
    currentLeftMenu = ButtonType.PerUnitItem;
    view.populateLeftPanel(perUnitItemMenuString);
    view.populateRightPanel(
        currentOrderArray); // update left panel with new item - this takes a shortcut in updating
    // the entire information in the panel not just adds to the end
    view.setTextCostLabelRightPanel(
        "Total cost of order: "
            + String.valueOf(costCurrentOrder)); // set the text to show cost of current order
    view.enableAllButtons();
    view.disablePerUnitItemMenuButton();
    view.disableViewSelectedOrderButton();
  }

  public void setToOrderHistoryMenu() {
    currentLeftMenu = ButtonType.OrderHistory;
    view.clearRightPanel();
    view.populateLeftPanel(orderHistoryMenuString);
    view.enableAllButtons();
    view.disableAddMenuButton();
    view.disableOrderButton();
  }

  public void setToAddNewCakeMenu() {
    if (newCakeType == null){

      newCakeType = new CustomCakeFrame(this);
    }
    // TODO: For grade VG: Add more code to save the new cake type and update menu,
    view.enableAllButtons();
    view.disableNewCakeButton();
  }

  public void placeOrder() {
    JOptionPane.showMessageDialog(null, "Order placed!");
    orderHistoryMenuString[orderCounter] = Arrays.toString(currentOrderArray).replace("null","");
    orderArray[orderCounter] = orderHistoryMenuString[orderCounter];
    orderCounter++;
    view.clearRightPanel(); // Removes information from right panel in GUI
    view.clearLeftPanel();
    view.setTextCostLabelRightPanel("TOTAL COST: 0");
    Arrays.fill(currentOrderArray, null);
    costCurrentOrder = 0;
    view.enableAllButtons();
    view.disableAddMenuButton();
    view.disableViewSelectedOrderButton();
  }
}
