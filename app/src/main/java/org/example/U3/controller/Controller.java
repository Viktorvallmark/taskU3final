package org.example.U3.controller;

// only imports what is strictly necessary from view-package
import org.example.U3.model.*;
import org.example.U3.view.ButtonType;
import org.example.U3.view.MainFrame;

import javax.swing.*;
import java.util.*;

/**
 * @author Viktor Vallmark
 * Brain of the program. Handles the connection between the GUI and the entity classes.
 * Updates the GUI when buttons are pressed.
 */
public class Controller {

  private MainFrame view;
  private ButtonType currentLeftMenu = ButtonType.NoChoice;


  private ArrayList<Cake> cakes;
  private ArrayList<Order> orders;
  private ArrayList<IBakeryItem> currentOrder;
  private String[] cakeMenuString;
  private String[] perUnitItemMenuString;
  private BakeryItem[] perItemArray;
  private String[] orderHistoryMenuString;
  private String[] currentOrderArray;
  private double costCurrentOrder = 0;

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

  /**
   * @author Viktor Vallmark
   * Adds some starting values when the program is started. Creates both Cakes and BakeryItems.
   * Populates the String[] that is used as a parameter for the different JPanels.
   */
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
      perUnitItemMenuString[i] = perItemArray[i].getName()+", "+perItemArray[i].getPrice()+"\n";
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
  public Order makeOrder(ArrayList<IBakeryItem> list){
    return new Order(list);
  }

  public ArrayList<Cake> getCakes() {
    return cakes;
  }

  public void setCakes(ArrayList<Cake> cakes) {
    this.cakes = cakes;
  }
  public void setOrders(ArrayList<Order> orders) {
    this.orders = orders;
  }

  /**
   * Adds the selected item to both currentOrder and the string representation of currentOrder.
   * Updates the GUI.
   * @author Viktor Vallmark
   * @param selectionIndex - integer that represents where the user has clicked in the GUI.
   */
  public void addItemToOrder(int selectionIndex) {
    if (selectionIndex != -1) {
      switch (currentLeftMenu) {
        case Cake:
          currentOrder.add(cakes.get(selectionIndex));
          costCurrentOrder += cakes.get(selectionIndex).getPrice();
          break;
        case PerUnitItem:
          currentOrder.add(perItemArray[selectionIndex]);
          // for case above
          costCurrentOrder += perItemArray[selectionIndex].getPrice();
          System.out.println(currentOrder.toString());
          break;
      }

      for (int i = 0; i < currentOrder.size(); i++) {
        currentOrderArray[i] = currentOrder.get(i).toString();
      }
      view.populateRightPanel(currentOrderArray);
      view.setTextCostLabelRightPanel(
          "Total cost of order: "
              + costCurrentOrder);
    }
  }

  /**
   * Populates the left and right panels with information from orders.
   * @author Viktor Vallmark
   * @param selectionIndex - integer that represents where the user has clicked in the GUI.
   */
  public void viewSelectedOrder(int selectionIndex) {
    if ((selectionIndex != -1) && currentLeftMenu == ButtonType.OrderHistory) {
      view.populateRightPanel(
              new String[]{String.valueOf(orders.get(selectionIndex))});
      view.setTextCostLabelRightPanel(
          "Total cost of order: "
              + String.valueOf(orders.get(selectionIndex).getCost()));

    }
  }

  /**
   * @author Viktor Vallmark
   * This is called when user presses the cake button in the gui.
   * Populates the GUi with information.
   */
  public void setToCakeMenu() {
    currentLeftMenu = ButtonType.Cake;
    for (int i = 0; i < cakes.size(); i++){
      cakeMenuString[i] = cakes.get(i).toString();
    }
    view.clearLeftPanel();
    view.clearRightPanel();
    view.populateLeftPanel(cakeMenuString);
    view.populateRightPanel(
        currentOrderArray);
    view.setTextCostLabelRightPanel(
        "Total cost of order: "
            + String.valueOf(costCurrentOrder));
    view.enableAllButtons();
    view.disableCakeMenuButton();
    view.disableViewSelectedOrderButton();
  }

  /**
   * @author Viktor Vallmark
   * Is called when PerUnitItem button is pressed by user.
   * Populates the GUI with relevant information
   */
  public void setToPerUnitItemMenu() {
    currentLeftMenu = ButtonType.PerUnitItem;
    Arrays.fill(currentOrderArray, "");
    view.clearLeftPanel();
    view.clearRightPanel();
    view.populateLeftPanel(perUnitItemMenuString);
    view.populateRightPanel(
        currentOrderArray);
    view.setTextCostLabelRightPanel(
        "Total cost of order: "
            + String.valueOf(costCurrentOrder));
    view.enableAllButtons();
    view.disablePerUnitItemMenuButton();
    view.disableViewSelectedOrderButton();
  }

  /**
   * @author Viktor Vallmark
   * Is called when OrderHistory button is pressed by user.
   * Populates the GUI with relevant information
   */
  public void setToOrderHistoryMenu() {
    currentLeftMenu = ButtonType.OrderHistory;
    if(orders.isEmpty()){
      JOptionPane.showMessageDialog(null, "No order history!");
    }else {

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

  }

  /**
   * @author Viktor Vallmark
   * Not implemented due to time constraints
   */
  public void setToAddNewCakeMenu() {
    // TODO: For grade VG: Add more code to save the new cake type and update menu,

    view.clearRightPanel();
    view.clearLeftPanel();

    view.enableAllButtons();
    view.disableNewCakeButton();
  }

  /**
   * @author Viktor Vallmark
   * Is called when Order button is pressed by user.
   * Populates the GUI with relevant information.
   * If currentOrder contains IBakeryItems
   * Saves currentOrder in the orders ArrayList and then removes items from currentOrder if they are non-null.
   * Checks if currentOrder is empty, if it is then a JOptionPane is shown with a warning to the user.
   */
  public void placeOrder() {
    Order order;
    if(currentOrder.isEmpty()){
      JOptionPane.showMessageDialog(null,"Nothing to order!");
    }else {

      JOptionPane.showMessageDialog(null, "Order placed!");
      order = makeOrder(currentOrder);
      orders.add(order);
      Arrays.fill(currentOrderArray, "");
      view.clearRightPanel();
      view.clearLeftPanel();
      costCurrentOrder = 0;
      view.setTextCostLabelRightPanel("TOTAL COST: "+costCurrentOrder);
      view.enableAllButtons();
      view.disableAddMenuButton();
      view.disableViewSelectedOrderButton();

      currentOrder.removeIf(Objects::nonNull);
    }
  }
}
