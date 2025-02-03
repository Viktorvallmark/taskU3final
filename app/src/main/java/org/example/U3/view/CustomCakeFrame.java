package org.example.U3.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import org.example.U3.controller.Controller;
import org.example.U3.model.Fillings;

public class CustomCakeFrame extends JFrame implements ActionListener {

    private Controller controller;

    public CustomCakeFrame(Controller controller) {

        setupPanel();
        setTitle("Create your own cake!");
        this.setVisible(true);
        this.controller = controller;
    }

    public void setupPanel() {
        /*
         * TODO: Skapa ny tårta här och spara den.
         */
        Fillings[] jFillings = {Fillings.Banana, Fillings.Chocolate, Fillings.Cream, Fillings.Raspberry,
        Fillings.Strawberry, Fillings.Vanilla};
        ArrayList<Fillings> fillingsArrayList = new ArrayList<>(10);
        JTextField textSlices = new JTextField();

        JLabel labelSlices = new JLabel("Number of slices:");
        JComboBox<Fillings> fillingsJComboBox = new JComboBox<>(jFillings);
        JLabel labelFillings = new JLabel("Fillings:");
        JTextField textName = new JTextField();
        JLabel labelName = new JLabel("Name your cake:");
        JLabel fillingLabel = new JLabel();


        JButton btnMakeCake = new JButton("Make cake");
        JButton btnReset = new JButton("Reset");
        JButton btnAddFillings = new JButton("Add filling");

        btnMakeCake.addActionListener(l -> {
            String name = textName.getText();
            int numSlices = Integer.parseInt(textSlices.getText());
            System.out.println("name = "+name);

            Fillings[] arr = new Fillings[fillingsArrayList.size()];
            arr = fillingsArrayList.toArray(arr);
            controller.getCakes().add(controller.makeCake(name, numSlices, arr));
        });

        btnReset.addActionListener(l -> {
            textSlices.setText("");
            textName.setText("");
            fillingsArrayList.clear();
            JOptionPane.showMessageDialog(null,"You have removed all fillings from the cake");
            fillingLabel.setText("");
        });

        btnAddFillings.addActionListener(l -> {
            int temp = fillingsJComboBox.getSelectedIndex();
            fillingsArrayList.add(fillingsJComboBox.getItemAt(temp));
            fillingLabel.setText("You've added "+fillingsJComboBox.getItemAt(temp)+" filling");
        });


        this.add(labelSlices);
        this.add(textSlices);
        this.add(labelFillings);
        this.add(fillingsJComboBox);
        this.add(labelName);
        this.add(textName);
        this.add(btnMakeCake);
        this.add(btnAddFillings);
        this.add(btnReset);
        this.add(fillingLabel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
         * TODO: Skapa ny tårta här och spara den.
         */
    }
}
