package org.example.U3.controller;

// only imports what is strictly necessary from view-package
import org.example.U3.model.*;
import org.example.U3.view.ButtonType;
import org.example.U3.view.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

  private MainFrame view;
  private ButtonType currentLeftMenu = ButtonType.NoChoice;


  private ArrayList<Cake> cakes;
  private ArrayList<Order> orders;
  private ArrayList<IBakeryItem> currentOrder;
  private String[] cakeMenuString; // for test purposes only
  private String[] perUnitItemMenuString; // for test purposes only
  private BakeryItem[] perItemArray;
  private String[] orderHistoryMenuString; // for test purposes only
  private String[] currentOrderArray; // for test purposes only
  private double costCurrentOrder = 0; // for test purposes only

  public Controller() {
    view = new MainFrame(1000, 500, this);
    cakes = new ArrayList<>(5);
    orders = new ArrayList<>(5);
    currentOrder = new ArrayList<>(5);
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
    currentOrderArray = new String[100];


    perItemArray = new BakeryItem[3];
    BakeryItem bakeryItem = new BakeryItem("Rye", 10);
    BakeryItem bakeryItem1 = new BakeryItem("Danish", 5);
    BakeryItem bakeryItem2 = new BakeryItem("Cookie", 3);
    perItemArray[0] = bakeryItem;
    perItemArray[1] = bakeryItem1;
    perItemArray[2] = bakeryItem2;

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
    if (selectionIndex != -1) { // if something is selected in the left menu list
      switch (currentLeftMenu) { // This might need to change depending on architecture
        case Cake:
          currentOrder.add(cakes.get(selectionIndex)); // for test purposes - needs to be replaced with solution of finding
          // chosen menu item matching architecture for model
          costCurrentOrder += cakes.get(selectionIndex).getPrice();
          break;
        case PerUnitItem:
          currentOrder.add(perItemArray[selectionIndex]);
          // for case above
          costCurrentOrder += perItemArray[selectionIndex].getPrice();
          System.out.println(currentOrder.toString()+"\n");
          break;
      }

      for (int i = 0; i < currentOrder.size(); i++) {
        currentOrderArray[i] = currentOrder.get(i).toString();
      }

      view.populateRightPanel(currentOrderArray); // update left panel with new item - this takes a shortcut in updating
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
              new String[]{String.valueOf(orders.get(selectionIndex))}); // update left panel with order details - this takes a shortcut in
      // updating the entire information in the panel not just adds to the
      // end
      view.setTextCostLabelRightPanel(
          "Total cost of order: "
              + String.valueOf(orders.get(selectionIndex).getCost())); // set the text to show cost of current order

      currentOrder.clear();
    }

  }

  public void setToCakeMenu() {
    currentLeftMenu = ButtonType.Cake;
    for (int i = 0; i < cakes.size(); i++){
      cakeMenuString[i] = cakes.get(i).toString()+"\n";
    }
    view.clearLeftPanel();
    view.clearRightPanel();
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
    view.clearLeftPanel();
    view.clearRightPanel();
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

    for (int i = 0; i < orders.size(); i++) {
      orderHistoryMenuString[i] = orders.get(i).toString();
    }

    view.clearRightPanel();
    view.clearLeftPanel();
    view.populateLeftPanel(orderHistoryMenuString);
    view.enableAllButtons();
    view.disableAddMenuButton();
    view.disableOrderButton();
  }

  public void setToAddNewCakeMenu() {
    // TODO: For grade VG: Add more code to save the new cake type and update menu,

    view.clearRightPanel();
    view.clearLeftPanel();

    view.enableAllButtons();
    view.disableNewCakeButton();
  }

  public void placeOrder() {
    if(currentOrder.isEmpty()){
      JOptionPane.showMessageDialog(null,"Nothing to order!");
    }else {

      JOptionPane.showMessageDialog(null, "Order placed!");
      Order order = new Order(currentOrder);
      orders.add(order);
      view.clearRightPanel(); // Removes information from right panel in GUI
      view.clearLeftPanel();
      Arrays.fill(currentOrderArray, "");
      costCurrentOrder = 0;
      view.setTextCostLabelRightPanel("TOTAL COST: "+costCurrentOrder);
      view.enableAllButtons();
      view.disableAddMenuButton();
      view.disableViewSelectedOrderButton();
    }
  }
}
