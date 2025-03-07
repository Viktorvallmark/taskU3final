package org.example.U3.view;

import javax.swing.*;
import org.example.U3.controller.Controller;

public class MainFrame extends JFrame {

  private MainPanel mainPanel;
  private Controller controller;

  public MainFrame(int width, int height, Controller controller) {
    super("Viktor's Bakery");
    this.controller = controller;
    this.setResizable(true);
    this.setSize(width, height);
    this.mainPanel = new MainPanel(width, height, this);
    this.setContentPane(mainPanel);
    this.setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * This method sets the information in the LEFT panel of the main window.
   *
   * @param informationArray An array of String where each element will be shown one line in the
   *     panel.
   */
  public void populateLeftPanel(String[] informationArray) {
    mainPanel.getLeftPanel().populateList(informationArray);
  }

  /**
   * This method sets the information in the RIGHT panel of the main window.
   *
   * @param informationArray An array of String where each element will be shown one line in the
   *     panel.
   */
  public void populateRightPanel(String[] informationArray) {
    mainPanel.getRightPanel().populateList(informationArray);
  }

  /**
   * This method removes information in the RIGHT panel of the main window. There are some problems
   * with this in this code and "ghost items" might appear in the list view at the top of the list
   * after use of this method. This might throw selected indexes of synchronization if this is used.
   */
  public void clearRightPanel() {
    mainPanel.getRightPanel().clearList();
  }
  public void clearLeftPanel() {
    mainPanel.getLeftPanel().clearList();
  }

  /**
   * This method returns the index value as an int of the selected row in the list in the left panel
   * of the main window.
   *
   * @return the index of the selected row as an int. If no selection exists -1 is returned.
   */
  public int getSelectionLeftPanel() {
    return mainPanel.getLeftPanel().getLeftPanelList().getSelectedIndex();
  }

  /**
   * This method sets a text that shows to the farthest right in the window above the right
   * list panel.
   *
   * @param newText the text that is shown in the GUI
   */
  public void setTextCostLabelRightPanel(String newText) {
    mainPanel.getRightPanel().setTextCostLabel(newText);
  }

  /**
   * This method sets a text that shows a bit to the right of the middle of the window above the
   * right list panel.
   *
   * @param newText the text that is shown in the GUI
   */
  public void setTextTitleLabelRightPanel(String newText) {
    mainPanel.getRightPanel().setTextTitleLabel(newText);
  }

  /** This method disables the possibility to press the button labeled "Food". */
  public void disableCakeMenuButton() {
    mainPanel.getLeftPanel().getBtnShowCake().setEnabled(false);
  }

  /** This method disables the possibility to press the button labeled "Drinks". */
  public void disablePerUnitItemMenuButton() {
    mainPanel.getLeftPanel().getBtnShowPerUnitItem().setEnabled(false);
  }

  /** This method disables the possibility to press the button labeled "Add". */
  public void disableAddMenuButton() {
    mainPanel.getLeftPanel().getBtnAddSelectionToOrder().setEnabled(false);
  }

  /** This method disables the possibility to press the button labeled "Order". */
  public void disableOrderButton() {
    mainPanel.getRightPanel().getBtnCreateOrder().setEnabled(false);
  }

  public void disableNewCakeButton() {
    mainPanel.getLeftPanel().getBtnCreateNewCakeType().setEnabled(false);
  }

  /** This method disables the possibility to press the button labeled "View order". */
  public void disableViewSelectedOrderButton() {
    mainPanel.getRightPanel().getBtnViewSelectedOrder().setEnabled(false);
  }

  /** This method enables all buttons in the GUI to be pressed. */
  public void enableAllButtons() {
    mainPanel.getLeftPanel().getBtnShowCake().setEnabled(true);
    mainPanel.getLeftPanel().getBtnShowPerUnitItem().setEnabled(true);
    mainPanel.getLeftPanel().getBtnCreateNewCakeType().setEnabled(true);
    mainPanel.getLeftPanel().getBtnAddSelectionToOrder().setEnabled(true);
    mainPanel.getLeftPanel().getShowOrderHistory().setEnabled(true);
    mainPanel.getRightPanel().getBtnCreateOrder().setEnabled(true);
    mainPanel.getRightPanel().getBtnViewSelectedOrder().setEnabled(true);
  }
  /**
   * This method is called by other parts of the Gui when a button is pressed. The method
   * buttonPressed in class Controller is called and the type of pressed button is sent as an
   * argument.
   *
   * @param pressedButton the type of button
   * @see ButtonType
   */
  public void buttonPressed(ButtonType pressedButton) {
    controller.buttonPressed(pressedButton);
  }
}
