import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import CustomFiles.CustomListException;
import CustomFiles.Favorite;
import CustomFiles.FavoriteList;

// Name: Zachary Davis
// Helpful Links: 
// https://medium.com/prodsters/how-to-build-a-desktop-application-with-java-a34ee9c18ee3
// https://www.youtube.com/watch?v=JtTy9CnBIyM
// https://www.youtube.com/watch?v=iMBOzY0wuRY
// https://www.youtube.com/watch?v=bn2KdCLqHlg

public class ListGUI {
    public static void main(String[] args){
        // Creating the basic starting frame for the program.
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(new Dimension(800, 650));
        frame.setTitle("Variating List GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        //Radio Button Label
       
        JLabel rdbtnLabel1 = new JLabel("Reference Based or Array Based?");
        rdbtnLabel1.setBounds(100,180, 200,30);
        frame.add(rdbtnLabel1);

        // Radio BUttons
        JRadioButton rButtonArray = new JRadioButton("Array Based");
        JRadioButton rButtonReference = new JRadioButton("Reference Based");
        rButtonArray.setBounds(100, 210, 200, 20);
        rButtonReference.setBounds(100, 230, 200, 20);
        rButtonArray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rButtonArray.isSelected()){
                    rButtonReference.setSelected(false);
                }    
            }
        });
        
        rButtonReference.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rButtonReference.isSelected()){
                    rButtonArray.setSelected(false);
                }    
            }
        });

        frame.add(rButtonArray);
        frame.add(rButtonReference);

        // Combo Box + Label
        String listTypes[] = {"Standard List", "Stack", "Queue", "Custom"};
        JComboBox<String> comboBox = new JComboBox<String>(listTypes);

        comboBox.setBounds(100,150,200, 30);
        comboBox.setSelectedIndex(-1);
        
        frame.add(comboBox);

        JLabel cmboLabel1 = new JLabel("Select List Type:");
        cmboLabel1.setBounds(100,120,200,30);
        frame.add(cmboLabel1);

        // Combo Box for Generic Type
        String genericTypes[] = {"Integer", "String"};
        JComboBox<String> comboBox2 = new JComboBox<String>(genericTypes);
        comboBox2.setBounds(100, 280, 200, 30);
        comboBox2.setSelectedIndex(-1);
        frame.add(comboBox2);

        JLabel cmboLabel2 = new JLabel("Select Generic Type:");
        cmboLabel2.setBounds(100,250,200,30);
        frame.add(cmboLabel2);

        JLabel cmboLabel22 = new JLabel("Custom Generic is decided if Custom List is picked.");
        cmboLabel22.setBounds(325,140,300,30);
        frame.add(cmboLabel22);
        JLabel cmboLabel23 = new JLabel("Array / Reference is decided when making your Custom.");
        cmboLabel23.setBounds(325,155,500,30);
        frame.add(cmboLabel23);

        // Combo Box Action Listeners
         comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                String selectedItem = (String) cb.getSelectedItem();
                rButtonArray.setSelected(false);
                rButtonReference.setSelected(false);
                rButtonArray.setEnabled(!"Custom".equals(selectedItem));
                rButtonReference.setEnabled(!"Custom".equals(selectedItem));
                if("Custom".equals(selectedItem)){
                    comboBox2.setSelectedIndex(-1);
                }
                comboBox2.setEnabled(!"Custom".equals(selectedItem));
            }
        });

        // Confirm Button
        // Holder for Strings to remember: "Standard List", "Stack", "Queue", "Custom"
        //"Integer", "String"

        JButton confirmBtn = new JButton("CONFIRM SELECTION");
        confirmBtn.setBounds(100, 330, 200, 50);
        frame.add(confirmBtn);
        frame.setVisible(true);

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if((rButtonArray.isSelected() || rButtonReference.isSelected()) && comboBox.getSelectedIndex() != -1 && comboBox2.getSelectedIndex() != -1 ){

                    JOptionPane.showMessageDialog(confirmBtn, "Selected Option Successful!", "Confirmation Viewer", JOptionPane.NO_OPTION); // Says successful then closes main frame.
                    frame.setVisible(false);

                    if (comboBox.getSelectedItem().equals("Standard List")){  
                        if(rButtonArray.isSelected()){ // These use: ArrayList.java
                            if(comboBox2.getSelectedItem().equals("Integer")){
                                
                                // List Panel + Split Pane + Frame 
                                // TODO: Code Work Needed Here: Integer - Array List
                                JFrame selectedFrame = new JFrame("LinkedList Array Based | Integers");
                                JList<Integer> toList = new JList();
                                DefaultListModel<Integer> model = new DefaultListModel<>();
                                JScrollPane listScroll = new JScrollPane(toList);
                                
                                toList.setModel(model);
                                listScroll.setBounds(300, 0, 450,550);
               
                                selectedFrame.setLayout(new BorderLayout());
                                selectedFrame.setSize(new Dimension(800, 600));
                                selectedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setLayout(null);
                                selectedFrame.setResizable(false);
                                selectedFrame.add(listScroll);

                                // The beginnings
                                // Array List
                                ArrayList<Integer> arrayList = new ArrayList<>();

                                // Size Labels
                                
                                JLabel sizeLabel = new JLabel("MAX SIZE: 50");
                                sizeLabel.setBounds(50,50, 200, 30);
                                selectedFrame.add(sizeLabel);

                                JLabel sizeLabel2 = new JLabel("Current Size: " + arrayList.size());
                                sizeLabel2.setBounds(200,50, 200, 30);
                                selectedFrame.add(sizeLabel2);

                            

                                // The Textbox for Adding
                                NumberFormat format = NumberFormat.getInstance();
                                format.setGroupingUsed(false);
                                NumberFormatter formatter = new NumberFormatter(format);
                                formatter.setValueClass(Integer.class);
                                formatter.setMinimum(0);
                                formatter.setMaximum(Integer.MAX_VALUE);
                                formatter.setAllowsInvalid(false);

                                // If you want the value to be committed on each keystroke instead of focus lost
                                // formatter.setCommitsOnValidEdit(true);
                                JFormattedTextField addNumberField = new JFormattedTextField(formatter);
                                addNumberField.setBounds(50, 100, 200, 40);
                                selectedFrame.add(addNumberField);

                                // Add Button
                                
                                JButton addButton = new JButton("Add Inputed Value");
                                addButton.setBounds(50, 160, 200, 30);
                                selectedFrame.add(addButton);

                                addButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(addNumberField.getText().equals("")){
                                            JOptionPane.showMessageDialog(addButton, "There is nothing in the textbox, try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{ 
                                            if(arrayList.size() != 50){
                                                int added = Integer.parseInt(addNumberField.getText());
                                                arrayList.add(arrayList.size(), added);
                                                model.add(arrayList.size()-1, added);
                                                sizeLabel2.setText("Current Size: " + arrayList.size());
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(addButton, "Array is full.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                });

                                // Remove Button + Label

                                JButton removeButton = new JButton("Remove Selected");
                                removeButton.setBounds(50, 200, 200, 30);
                                selectedFrame.add(removeButton);

                                removeButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(arrayList.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else if(toList.getSelectedIndex() == -1){
                                            JOptionPane.showMessageDialog(addButton, "You did not select an item. Try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            arrayList.remove(toList.getSelectedIndex());
                                            model.remove(toList.getSelectedIndex());
                                            sizeLabel2.setText("Current Size: " + arrayList.size());
                                        }
                                    }
                                });

                                // Clear All

                                JButton clearButton = new JButton("Clear Button");
                                clearButton.setBounds(50, 240, 200, 30);
                                selectedFrame.add(clearButton);

                                clearButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addNumberField.setText("");
                                        if(arrayList.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            arrayList.removeAll();
                                            model.removeAllElements();
                                            sizeLabel2.setText("Current Size: " + arrayList.size());
                                        }
                                    }
                                });
                                
                                // Return To Base
                                JButton exitButton = new JButton("Exit to Home");
                                exitButton.setBounds(50, 450, 200, 40);
                                selectedFrame.add(exitButton);

                                exitButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addNumberField.setText("");
                                        arrayList.removeAll();
                                        model.removeAllElements();
                                        selectedFrame.setVisible(false);
                                        frame.setVisible(true);
                                    }
                                });

                                // --------------------------------------

                                selectedFrame.setVisible(true);
                            }
                            else if(comboBox2.getSelectedItem().equals("String")){
                                // TODO: Code Work Neeede Here: String - Array List

                                // List Panel + Split Pane + Frame 
                                JFrame selectedFrame = new JFrame("LinkedList Array Based | Strings");
                                JList<String> toList = new JList();
                                DefaultListModel<String> model = new DefaultListModel<>();
                                JScrollPane listScroll = new JScrollPane(toList);
                                
                                toList.setModel(model);
                                listScroll.setBounds(300, 0, 450,550);
               
                                selectedFrame.setLayout(new BorderLayout());
                                selectedFrame.setSize(new Dimension(800, 600));
                                selectedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setLayout(null);
                                selectedFrame.setResizable(false);
                                selectedFrame.add(listScroll);

                                // The beginnings
                                // Array List
                                ArrayList<String> arrayList = new ArrayList<>();

                                // Size Labels
                                
                                JLabel sizeLabel = new JLabel("MAX SIZE: 50");
                                sizeLabel.setBounds(50,50, 200, 30);
                                selectedFrame.add(sizeLabel);

                                JLabel sizeLabel2 = new JLabel("Current Size: " + arrayList.size());
                                sizeLabel2.setBounds(200,50, 200, 30);
                                selectedFrame.add(sizeLabel2);

                            

                                // The Textbox for Adding
                                
                                JTextField addStringField = new JTextField();
                                addStringField.setBounds(50, 100, 200, 40);
                                selectedFrame.add(addStringField);

                                // Add Button
                                
                                JButton addButton = new JButton("Add Inputed Value");
                                addButton.setBounds(50, 160, 200, 30);
                                selectedFrame.add(addButton);

                                addButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(addStringField.getText().equals("")){
                                            JOptionPane.showMessageDialog(addButton, "There is nothing in the textbox, try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            if(arrayList.size() != 50){
                                                arrayList.add(arrayList.size(), addStringField.getText());
                                                model.add(arrayList.size()-1, addStringField.getText());
                                                sizeLabel2.setText("Current Size: " + arrayList.size());
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(addButton, "Array is full.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                });

                                // Remove Button + Label

                                JButton removeButton = new JButton("Remove Selected");
                                removeButton.setBounds(50, 200, 200, 30);
                                selectedFrame.add(removeButton);

                                removeButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(arrayList.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else if(toList.getSelectedIndex() == -1){
                                            JOptionPane.showMessageDialog(addButton, "You did not select an item. Try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            arrayList.remove(toList.getSelectedIndex());
                                            model.remove(toList.getSelectedIndex());
                                            sizeLabel2.setText("Current Size: " + arrayList.size());
                                        }
                                    }
                                });

                                // Clear All

                                JButton clearButton = new JButton("Clear Button");
                                clearButton.setBounds(50, 240, 200, 30);
                                selectedFrame.add(clearButton);

                                clearButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addStringField.setText("");
                                        if(arrayList.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            arrayList.removeAll();
                                            model.removeAllElements();
                                            sizeLabel2.setText("Current Size: " + arrayList.size());
                                        }
                                    }
                                });
                                
                                // Return To Base
                                JButton exitButton = new JButton("Exit to Home");
                                exitButton.setBounds(50, 450, 200, 40);
                                selectedFrame.add(exitButton);

                                exitButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addStringField.setText("");
                                        arrayList.removeAll();
                                        model.removeAllElements();
                                        selectedFrame.setVisible(false);
                                        frame.setVisible(true);
                                    }
                                });

                                // --------------------------------------

                                selectedFrame.setVisible(true);
                            }
                        }
                        else{ // These use: ReferenceBasedList.java
                            if(comboBox2.getSelectedItem().equals("Integer")){

                                // TODO: Code Work Needed Here: Integer - Reference List
                                // List Panel + Split Pane + Frame 
                                JFrame selectedFrame = new JFrame("LinkedList Reference Based | Integers");
                                JList<Integer> toList = new JList();
                                DefaultListModel<Integer> model = new DefaultListModel<>();
                                JScrollPane listScroll = new JScrollPane(toList);
                                
                                toList.setModel(model);
                                listScroll.setBounds(300, 0, 450,550);
               
                                selectedFrame.setLayout(new BorderLayout());
                                selectedFrame.setSize(new Dimension(800, 600));
                                selectedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setLayout(null);
                                selectedFrame.setResizable(false);
                                selectedFrame.add(listScroll);

                                // The beginnings
                                // Array List
                                ReferenceBasedList<Integer> referenceList = new ReferenceBasedList<>();

                                // Size Labels
                                

                                JLabel sizeLabel = new JLabel("Current Size: " + referenceList.size());
                                sizeLabel.setBounds(50,50, 200, 30);
                                selectedFrame.add(sizeLabel);

                            

                                // The Textbox for Adding
                                
                               NumberFormat format = NumberFormat.getInstance();
                                format.setGroupingUsed(false);
                                NumberFormatter formatter = new NumberFormatter(format);
                                formatter.setValueClass(Integer.class);
                                formatter.setMinimum(0);
                                formatter.setMaximum(Integer.MAX_VALUE);
                                formatter.setAllowsInvalid(false);

                                // If you want the value to be committed on each keystroke instead of focus lost
                                // formatter.setCommitsOnValidEdit(true);
                                JFormattedTextField addNumberField = new JFormattedTextField(formatter);
                                addNumberField.setBounds(50, 100, 200, 40);
                                selectedFrame.add(addNumberField);
                                // Add Button
                                
                                JButton addButton = new JButton("Add Inputed Value");
                                addButton.setBounds(50, 160, 200, 30);
                                selectedFrame.add(addButton);

                                addButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(addNumberField.getText().equals("")){
                                            JOptionPane.showMessageDialog(addButton, "There is nothing in the textbox, try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            int added = Integer.parseInt(addNumberField.getText());
                                            referenceList.add(referenceList.size(), added);
                                            model.add(referenceList.size()-1, added);
                                            sizeLabel.setText("Current Size: " + referenceList.size());
                                        }
                                    }
                                });

                                // Remove Button + Label

                                JButton removeButton = new JButton("Remove Selected");
                                removeButton.setBounds(50, 200, 200, 30);
                                selectedFrame.add(removeButton);

                                removeButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(referenceList.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else if(toList.getSelectedIndex() == -1){
                                            JOptionPane.showMessageDialog(addButton, "You did not select an item. Try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            referenceList.remove(toList.getSelectedIndex());
                                            model.remove(toList.getSelectedIndex());
                                            sizeLabel.setText("Current Size: " + referenceList.size());
                                        }
                                    }
                                });

                                // Clear All

                                JButton clearButton = new JButton("Clear Button");
                                clearButton.setBounds(50, 240, 200, 30);
                                selectedFrame.add(clearButton);

                                clearButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addNumberField.setText("");
                                        if(referenceList.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            referenceList.removeAll();
                                            model.removeAllElements();
                                            sizeLabel.setText("Current Size: " + referenceList.size());
                                        }
                                    }
                                });

                                // Return To Base
                                JButton exitButton = new JButton("Exit to Home");
                                exitButton.setBounds(50, 450, 200, 40);
                                selectedFrame.add(exitButton);

                                exitButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addNumberField.setText("");
                                        referenceList.removeAll();
                                        model.removeAllElements();
                                        selectedFrame.setVisible(false);
                                        frame.setVisible(true);
                                    }
                                });

                                // --------------------------------------

                                selectedFrame.setVisible(true);
                            }
                            else if(comboBox2.getSelectedItem().equals("String")){

                                // TODO: Code Work Needed Here: String - Reference List
                                // List Panel + Split Pane + Frame 
                                JFrame selectedFrame = new JFrame("LinkedList Reference Based | Strings");
                                JList<String> toList = new JList();
                                DefaultListModel<String> model = new DefaultListModel<>();
                                JScrollPane listScroll = new JScrollPane(toList);
                                
                                toList.setModel(model);
                                listScroll.setBounds(300, 0, 450,550);
               
                                selectedFrame.setLayout(new BorderLayout());
                                selectedFrame.setSize(new Dimension(800, 600));
                                selectedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setLayout(null);
                                selectedFrame.setResizable(false);
                                selectedFrame.add(listScroll);

                                // The beginnings
                                // Array List
                                ReferenceBasedList<String> referenceList = new ReferenceBasedList<>();

                                // Size Labels
                                

                                JLabel sizeLabel = new JLabel("Current Size: " + referenceList.size());
                                sizeLabel.setBounds(50,50, 200, 30);
                                selectedFrame.add(sizeLabel);

                            

                                // The Textbox for Adding

                                JTextField addStringField = new JTextField();
                                addStringField.setBounds(50, 100, 200, 40);
                                selectedFrame.add(addStringField);
                                // Add Button
                                
                                JButton addButton = new JButton("Add Inputed Value");
                                addButton.setBounds(50, 160, 200, 30);
                                selectedFrame.add(addButton);

                                addButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(addStringField.getText().equals("")){
                                            JOptionPane.showMessageDialog(addButton, "There is nothing in the textbox, try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            referenceList.add(referenceList.size(), addStringField.getText());
                                            model.add(referenceList.size()-1, addStringField.getText());
                                            sizeLabel.setText("Current Size: " + referenceList.size());
                                        }
                                    }
                                });

                                // Remove Button + Label

                                JButton removeButton = new JButton("Remove Selected");
                                removeButton.setBounds(50, 200, 200, 30);
                                selectedFrame.add(removeButton);

                                removeButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(referenceList.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else if(toList.getSelectedIndex() == -1){
                                            JOptionPane.showMessageDialog(addButton, "You did not select an item. Try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            referenceList.remove(toList.getSelectedIndex());
                                            model.remove(toList.getSelectedIndex());
                                            sizeLabel.setText("Current Size: " + referenceList.size());
                                        }
                                    }
                                });

                                // Clear All

                                JButton clearButton = new JButton("Clear Button");
                                clearButton.setBounds(50, 240, 200, 30);
                                selectedFrame.add(clearButton);

                                clearButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addStringField.setText("");
                                        if(referenceList.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            referenceList.removeAll();
                                            model.removeAllElements();
                                            sizeLabel.setText("Current Size: " + referenceList.size());
                                        }
                                    }
                                });

                                // Return To Base
                                JButton exitButton = new JButton("Exit to Home");
                                exitButton.setBounds(50, 450, 200, 40);
                                selectedFrame.add(exitButton);

                                exitButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addStringField.setText("");
                                        referenceList.removeAll();
                                        model.removeAllElements();
                                        selectedFrame.setVisible(false);
                                        frame.setVisible(true);
                                    }
                                });

                                // --------------------------------------

                                selectedFrame.setVisible(true);
                            }
                        }
                    }
                    else if (comboBox.getSelectedItem().equals("Stack")){ // Stack
                        if(rButtonArray.isSelected()){
                            if(comboBox2.getSelectedItem().equals("Integer")){
                                // TODO: Code Work Needed Here: Stack Array Based Integer
                                // List Panel + Split Pane + Frame 
                                JFrame selectedFrame = new JFrame("Stack Array Based | Integers");
                                JList<Integer> toList = new JList();
                                DefaultListModel<Integer> model = new DefaultListModel<>();
                                JScrollPane listScroll = new JScrollPane(toList);
                                
                                toList.setModel(model);
                                listScroll.setBounds(300, 0, 450,550);
               
                                selectedFrame.setLayout(new BorderLayout());
                                selectedFrame.setSize(new Dimension(800, 600));
                                selectedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setLayout(null);
                                selectedFrame.setResizable(false);
                                selectedFrame.add(listScroll);

                                // The beginnings
                                // Array List
                                StackArray<Integer> stackList = new StackArray<>();

                                // Size Labels
                                
                                JLabel sizeLabel = new JLabel("MAX SIZE: 50");
                                sizeLabel.setBounds(50,50, 200, 30);
                                selectedFrame.add(sizeLabel);


                            

                                // The Textbox for Adding
                                NumberFormat format = NumberFormat.getInstance();
                                format.setGroupingUsed(false);
                                NumberFormatter formatter = new NumberFormatter(format);
                                formatter.setValueClass(Integer.class);
                                formatter.setMinimum(0);
                                formatter.setMaximum(Integer.MAX_VALUE);
                                formatter.setAllowsInvalid(false);

                                // If you want the value to be committed on each keystroke instead of focus lost
                                // formatter.setCommitsOnValidEdit(true);
                                JFormattedTextField addNumberField = new JFormattedTextField(formatter);
                                addNumberField.setBounds(50, 100, 200, 40);
                                selectedFrame.add(addNumberField);

                                // Add Button
                                
                                JButton addButton = new JButton("Stack Item");
                                addButton.setBounds(50, 160, 200, 30);
                                selectedFrame.add(addButton);

                                addButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(addNumberField.getText().equals("")){
                                            JOptionPane.showMessageDialog(addButton, "There is nothing in the textbox, try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            if(!stackList.isFull()){
                                                int added = Integer.parseInt(addNumberField.getText());
                                                stackList.push(added);
                                                model.add(model.size(),added);
                                                //sizeLabel2.setText("Current Size: " + stackList.size());
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(addButton, "Array is full.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                });

                                // Remove Button + Label

                                JButton removeButton = new JButton("Remove Stacked Item");
                                removeButton.setBounds(50, 200, 200, 30);
                                selectedFrame.add(removeButton);

                                removeButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                            
                                            System.out.println(stackList.pop());
                                            model.remove(model.size()-1);
                                        
                                    }
                                });

                                // Clear All

                                JButton clearButton = new JButton("Clear Button");
                                clearButton.setBounds(50, 240, 200, 30);
                                selectedFrame.add(clearButton);

                                clearButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addNumberField.setText("");
                                        if(stackList.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            stackList.popAll();
                                            model.removeAllElements();
                                            //stackList.setText("Current Size: " + stackList.size());
                                        }
                                    }
                                });
                                
                                // Return To Base
                                JButton exitButton = new JButton("Exit to Home");
                                exitButton.setBounds(50, 450, 200, 40);
                                selectedFrame.add(exitButton);

                                exitButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addNumberField.setText("");
                                        stackList.popAll();
                                        model.removeAllElements();
                                        selectedFrame.setVisible(false);
                                        frame.setVisible(true);
                                    }
                                });

                                // --------------------------------------

                                selectedFrame.setVisible(true);
                            }
                            else if(comboBox2.getSelectedItem().equals("String")){
                                
                                // TODO: Code Work Needed Here: Stack Array Based String
                                // List Panel + Split Pane + Frame 
                                JFrame selectedFrame = new JFrame("Stack Reference Based | Strings");
                                JList<String> toList = new JList();
                                DefaultListModel<String> model = new DefaultListModel<>();
                                JScrollPane listScroll = new JScrollPane(toList);
                                
                                toList.setModel(model);
                                listScroll.setBounds(300, 0, 450,550);
               
                                selectedFrame.setLayout(new BorderLayout());
                                selectedFrame.setSize(new Dimension(800, 600));
                                selectedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setLayout(null);
                                selectedFrame.setResizable(false);
                                selectedFrame.add(listScroll);

                                // The beginnings
                                // Stack Array List
                                StackArray<String> stackList = new StackArray<>();

                                // Label
                                JLabel sizeLabel = new JLabel("MAX SIZE: 50");
                                sizeLabel.setBounds(50,50, 200, 30);
                                selectedFrame.add(sizeLabel);


                                // The Textbox for Adding
                                JTextField addStringField = new JTextField();
                                addStringField.setBounds(50, 100, 200, 40);
                                selectedFrame.add(addStringField);

                                // Add Button
                                
                                JButton addButton = new JButton("Stack Item");
                                addButton.setBounds(50, 160, 200, 30);
                                selectedFrame.add(addButton);

                                addButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(addStringField.getText().equals("")){
                                            JOptionPane.showMessageDialog(addButton, "There is nothing in the textbox, try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            if(!stackList.isFull()){
                                                stackList.push(addStringField.getText());
                                                model.add(model.size(),addStringField.getText());
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(addButton, "Array is full.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                });

                                // Remove Button + Label

                                JButton removeButton = new JButton("Remove Stacked Item");
                                removeButton.setBounds(50, 200, 200, 30);
                                selectedFrame.add(removeButton);

                                removeButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                            
                                            System.out.println(stackList.pop());
                                            model.remove(model.size()-1);
                                        
                                    }
                                });

                                // Clear All

                                JButton clearButton = new JButton("Clear Button");
                                clearButton.setBounds(50, 240, 200, 30);
                                selectedFrame.add(clearButton);

                                clearButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addStringField.setText("");
                                        if(stackList.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            stackList.popAll();
                                            model.removeAllElements();
                                            //stackList.setText("Current Size: " + stackList.size());
                                        }
                                    }
                                });
                                
                                // Return To Base
                                JButton exitButton = new JButton("Exit to Home");
                                exitButton.setBounds(50, 450, 200, 40);
                                selectedFrame.add(exitButton);

                                exitButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addStringField.setText("");
                                        stackList.popAll();
                                        model.removeAllElements();
                                        selectedFrame.setVisible(false);
                                        frame.setVisible(true);
                                    }
                                });

                                // --------------------------------------

                                selectedFrame.setVisible(true);
                            }
                        }
                        else{
                            if(comboBox2.getSelectedItem().equals("Integer")){
                            
                                // TODO: Code Work Needed Here: Stack Reference Based Integer
                                // List Panel + Split Pane + Frame 
                                JFrame selectedFrame = new JFrame("Stack Array Based | Integers");
                                JList<Integer> toList = new JList();
                                DefaultListModel<Integer> model = new DefaultListModel<>();
                                JScrollPane listScroll = new JScrollPane(toList);
                                
                                toList.setModel(model);
                                listScroll.setBounds(300, 0, 450,550);
               
                                selectedFrame.setLayout(new BorderLayout());
                                selectedFrame.setSize(new Dimension(800, 600));
                                selectedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setLayout(null);
                                selectedFrame.setResizable(false);
                                selectedFrame.add(listScroll);

                                // The beginnings
                                // Stack ReferenceBased List
                                StackReferenceBased<Integer> stackList = new StackReferenceBased<>();


                                // The Textbox for Adding
                                NumberFormat format = NumberFormat.getInstance();
                                format.setGroupingUsed(false);
                                NumberFormatter formatter = new NumberFormatter(format);
                                formatter.setValueClass(Integer.class);
                                formatter.setMinimum(0);
                                formatter.setMaximum(Integer.MAX_VALUE);
                                formatter.setAllowsInvalid(false);

                                JFormattedTextField addNumberField = new JFormattedTextField(formatter);
                                addNumberField.setBounds(50, 100, 200, 40);
                                selectedFrame.add(addNumberField);

                                // Add Button
                                
                                JButton addButton = new JButton("Stack Item");
                                addButton.setBounds(50, 160, 200, 30);
                                selectedFrame.add(addButton);

                                addButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(addNumberField.getText().equals("")){
                                            JOptionPane.showMessageDialog(addButton, "There is nothing in the textbox, try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            int added = Integer.parseInt(addNumberField.getText());
                                            stackList.push(added);
                                            model.add(model.size(),added);
                                            //sizeLabel2.setText("Current Size: " + stackList.size());
                                        }
                                    }
                                });

                                // Remove Button + Label

                                JButton removeButton = new JButton("Remove Stacked Item");
                                removeButton.setBounds(50, 200, 200, 30);
                                selectedFrame.add(removeButton);

                                removeButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                            
                                            System.out.println(stackList.pop());
                                            model.remove(model.size()-1);
                                        
                                    }
                                });

                                // Clear All

                                JButton clearButton = new JButton("Clear Button");
                                clearButton.setBounds(50, 240, 200, 30);
                                selectedFrame.add(clearButton);

                                clearButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addNumberField.setText("");
                                        if(stackList.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            stackList.popAll();
                                            model.removeAllElements();
                                            //stackList.setText("Current Size: " + stackList.size());
                                        }
                                    }
                                });
                                
                                // Return To Base
                                JButton exitButton = new JButton("Exit to Home");
                                exitButton.setBounds(50, 450, 200, 40);
                                selectedFrame.add(exitButton);

                                exitButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addNumberField.setText("");
                                        stackList.popAll();
                                        model.removeAllElements();
                                        selectedFrame.setVisible(false);
                                        frame.setVisible(true);
                                    }
                                });

                                // --------------------------------------

                                selectedFrame.setVisible(true);
                                
                            }
                            else if(comboBox2.getSelectedItem().equals("String")){
                                // TODO: Code Work Needed Here: Stack Reference Based String
                                // List Panel + Split Pane + Frame 
                                JFrame selectedFrame = new JFrame("Stack Array Based | Strings");
                                JList<String> toList = new JList();
                                DefaultListModel<String> model = new DefaultListModel<>();
                                JScrollPane listScroll = new JScrollPane(toList);
                                
                                toList.setModel(model);
                                listScroll.setBounds(300, 0, 450,550);
               
                                selectedFrame.setLayout(new BorderLayout());
                                selectedFrame.setSize(new Dimension(800, 600));
                                selectedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setLayout(null);
                                selectedFrame.setResizable(false);
                                selectedFrame.add(listScroll);

                                // The beginnings
                                // Stack Array List
                                StackReferenceBased<String> stackList = new StackReferenceBased<>();


                                // The Textbox for Adding
                                JTextField addStringField = new JTextField();
                                addStringField.setBounds(50, 100, 200, 40);
                                selectedFrame.add(addStringField);

                                // Add Button
                                
                                JButton addButton = new JButton("Stack Item");
                                addButton.setBounds(50, 160, 200, 30);
                                selectedFrame.add(addButton);

                                addButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(addStringField.getText().equals("")){
                                            JOptionPane.showMessageDialog(addButton, "There is nothing in the textbox, try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            
                                            stackList.push(addStringField.getText());
                                            model.add(model.size(),addStringField.getText());
                                            
                                        }
                                    }
                                });

                                // Remove Button + Label

                                JButton removeButton = new JButton("Remove Stacked Item");
                                removeButton.setBounds(50, 200, 200, 30);
                                selectedFrame.add(removeButton);

                                removeButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                            
                                            System.out.println(stackList.pop());
                                            model.remove(model.size()-1);
                                        
                                    }
                                });

                                // Clear All

                                JButton clearButton = new JButton("Clear Button");
                                clearButton.setBounds(50, 240, 200, 30);
                                selectedFrame.add(clearButton);

                                clearButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addStringField.setText("");
                                        if(stackList.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            stackList.popAll();
                                            model.removeAllElements();
                                            //stackList.setText("Current Size: " + stackList.size());
                                        }
                                    }
                                });
                                
                                // Return To Base
                                JButton exitButton = new JButton("Exit to Home");
                                exitButton.setBounds(50, 450, 200, 40);
                                selectedFrame.add(exitButton);

                                exitButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addStringField.setText("");
                                        stackList.popAll();
                                        model.removeAllElements();
                                        selectedFrame.setVisible(false);
                                        frame.setVisible(true);
                                    }
                                });

                                // --------------------------------------

                                selectedFrame.setVisible(true);
                        
                            }
                        }
                    }
                    else if (comboBox.getSelectedItem().equals("Queue")){ // Queue
                        if(rButtonArray.isSelected()){
                            if(comboBox2.getSelectedItem().equals("Integer")){
                                // TODO: Code Work Needed Here: Queue Array Based Integer
                                JFrame selectedFrame = new JFrame("Queue Array Based | Integers");
                                JList<Integer> toList = new JList();
                                DefaultListModel<Integer> model = new DefaultListModel<>();
                                JScrollPane listScroll = new JScrollPane(toList);
                                
                                toList.setModel(model);
                                listScroll.setBounds(300, 0, 450,550);
               
                                selectedFrame.setLayout(new BorderLayout());
                                selectedFrame.setSize(new Dimension(800, 600));
                                selectedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setLayout(null);
                                selectedFrame.setResizable(false);
                                selectedFrame.add(listScroll);

                                // The beginnings
                                // Queue Array List
                                QueueArray<Integer> queueArray = new QueueArray<>();

                                // Size Labels
                                
                                JLabel sizeLabel = new JLabel("MAX SIZE: 50");
                                sizeLabel.setBounds(50,50, 200, 30);
                                selectedFrame.add(sizeLabel);


                                // The Textbox for Adding
                                NumberFormat format = NumberFormat.getInstance();
                                format.setGroupingUsed(false);
                                NumberFormatter formatter = new NumberFormatter(format);
                                formatter.setValueClass(Integer.class);
                                formatter.setMinimum(0);
                                formatter.setMaximum(Integer.MAX_VALUE);
                                formatter.setAllowsInvalid(false);

                                // If you want the value to be committed on each keystroke instead of focus lost
                                // formatter.setCommitsOnValidEdit(true);
                                JFormattedTextField addNumberField = new JFormattedTextField(formatter);
                                addNumberField.setBounds(50, 100, 200, 40);
                                selectedFrame.add(addNumberField);

                                // Add Button
                                
                                JButton addButton = new JButton("Enqueue");
                                addButton.setBounds(50, 160, 200, 30);
                                selectedFrame.add(addButton);

                                addButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(addNumberField.getText().equals("")){
                                            JOptionPane.showMessageDialog(addButton, "There is nothing in the textbox, try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            if(!queueArray.isFull()){
                                                int added = Integer.parseInt(addNumberField.getText());
                                                queueArray.enqueue(added);
                                                model.add(model.size(),added);
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(addButton, "Array is full.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                });

                                // Remove Button + Label

                                JButton removeButton = new JButton("Dequeue");
                                removeButton.setBounds(50, 200, 200, 30);
                                selectedFrame.add(removeButton);

                                removeButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                            
                                            System.out.println(queueArray.dequeue());
                                            model.remove(0);
                                        
                                    }
                                });

                                // Clear All

                                JButton clearButton = new JButton("Clear Button");
                                clearButton.setBounds(50, 240, 200, 30);
                                selectedFrame.add(clearButton);

                                clearButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addNumberField.setText("");
                                        if(queueArray.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            queueArray.dequeueAll();
                                            model.removeAllElements();
                                            //stackList.setText("Current Size: " + stackList.size());
                                        }
                                    }
                                });
                                
                                // Return To Base
                                JButton exitButton = new JButton("Exit to Home");
                                exitButton.setBounds(50, 450, 200, 40);
                                selectedFrame.add(exitButton);

                                exitButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addNumberField.setText("");
                                        queueArray.dequeueAll();
                                        model.removeAllElements();
                                        selectedFrame.setVisible(false);
                                        frame.setVisible(true);
                                    }
                                });

                                // --------------------------------------

                                selectedFrame.setVisible(true);


                            }
                            else if(comboBox2.getSelectedItem().equals("String")){
                                // TODO: Queue Arrray Based STRING
                                JFrame selectedFrame = new JFrame("Queue Array Based | String");
                                JList<String> toList = new JList();
                                DefaultListModel<String> model = new DefaultListModel<>();
                                JScrollPane listScroll = new JScrollPane(toList);
                                
                                toList.setModel(model);
                                listScroll.setBounds(300, 0, 450,550);
               
                                selectedFrame.setLayout(new BorderLayout());
                                selectedFrame.setSize(new Dimension(800, 600));
                                selectedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setLayout(null);
                                selectedFrame.setResizable(false);
                                selectedFrame.add(listScroll);

                                // The beginnings
                                // Queue Array List
                                QueueArray<String> queueArray = new QueueArray<>();

                                // Size Labels
                                
                                JLabel sizeLabel = new JLabel("MAX SIZE: 50");
                                sizeLabel.setBounds(50,50, 200, 30);
                                selectedFrame.add(sizeLabel);


                                // The Textbox for Adding
                                JTextField addStringField = new JTextField();
                                addStringField.setBounds(50, 100, 200, 40);
                                selectedFrame.add(addStringField);

                                // Add Button
                                
                                JButton addButton = new JButton("Enqueue");
                                addButton.setBounds(50, 160, 200, 30);
                                selectedFrame.add(addButton);

                                addButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(addStringField.getText().equals("")){
                                            JOptionPane.showMessageDialog(addButton, "There is nothing in the textbox, try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            if(!queueArray.isFull()){
                                                queueArray.enqueue(addStringField.getText());
                                                model.add(model.size(),addStringField.getText());
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(addButton, "Array is full.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                });

                                // Remove Button + Label

                                JButton removeButton = new JButton("Dequeue");
                                removeButton.setBounds(50, 200, 200, 30);
                                selectedFrame.add(removeButton);

                                removeButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                            
                                            System.out.println(queueArray.dequeue());
                                            model.remove(0);
                                        
                                    }
                                });

                                // Clear All

                                JButton clearButton = new JButton("Clear Button");
                                clearButton.setBounds(50, 240, 200, 30);
                                selectedFrame.add(clearButton);

                                clearButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addStringField.setText("");
                                        if(queueArray.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            queueArray.dequeueAll();
                                            model.removeAllElements();
                                            //stackList.setText("Current Size: " + stackList.size());
                                        }
                                    }
                                });
                                
                                // Return To Base
                                JButton exitButton = new JButton("Exit to Home");
                                exitButton.setBounds(50, 450, 200, 40);
                                selectedFrame.add(exitButton);

                                exitButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addStringField.setText("");
                                        queueArray.dequeueAll();
                                        model.removeAllElements();
                                        selectedFrame.setVisible(false);
                                        frame.setVisible(true);
                                    }
                                });

                                // --------------------------------------

                                selectedFrame.setVisible(true);
                            }
                        }
                        else{
                            if(comboBox2.getSelectedItem().equals("Integer")){
                                // TODO: Code Work Needed Here: Queue Reference Based (First-Last) Integer
                                JFrame selectedFrame = new JFrame("Queue Reference Based | Integers");
                                JList<Integer> toList = new JList();
                                DefaultListModel<Integer> model = new DefaultListModel<>();
                                JScrollPane listScroll = new JScrollPane(toList);
                                
                                toList.setModel(model);
                                listScroll.setBounds(300, 0, 450,550);
               
                                selectedFrame.setLayout(new BorderLayout());
                                selectedFrame.setSize(new Dimension(800, 600));
                                selectedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setLayout(null);
                                selectedFrame.setResizable(false);
                                selectedFrame.add(listScroll);

                                // The beginnings
                                // Queue Array List
                                QueueReferenceBasedFirstLast<Integer> queueArray = new QueueReferenceBasedFirstLast<>();

                                // The Textbox for Adding
                                NumberFormat format = NumberFormat.getInstance();
                                format.setGroupingUsed(false);
                                NumberFormatter formatter = new NumberFormatter(format);
                                formatter.setValueClass(Integer.class);
                                formatter.setMinimum(0);
                                formatter.setMaximum(Integer.MAX_VALUE);
                                formatter.setAllowsInvalid(false);

                                // If you want the value to be committed on each keystroke instead of focus lost
                                // formatter.setCommitsOnValidEdit(true);
                                JFormattedTextField addNumberField = new JFormattedTextField(formatter);
                                addNumberField.setBounds(50, 100, 200, 40);
                                selectedFrame.add(addNumberField);

                                // Add Button
                                
                                JButton addButton = new JButton("Enqueue");
                                addButton.setBounds(50, 160, 200, 30);
                                selectedFrame.add(addButton);

                                addButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(addNumberField.getText().equals("")){
                                            JOptionPane.showMessageDialog(addButton, "There is nothing in the textbox, try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            int added = Integer.parseInt(addNumberField.getText());
                                            queueArray.enqueue(added);
                                            model.add(model.size(),added);
                                            
                                        }
                                    }
                                });

                                // Remove Button + Label

                                JButton removeButton = new JButton("Dequeue");
                                removeButton.setBounds(50, 200, 200, 30);
                                selectedFrame.add(removeButton);

                                removeButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                            
                                            System.out.println(queueArray.dequeue());
                                            model.remove(0);
                                        
                                    }
                                });

                                // Clear All

                                JButton clearButton = new JButton("Clear Button");
                                clearButton.setBounds(50, 240, 200, 30);
                                selectedFrame.add(clearButton);

                                clearButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addNumberField.setText("");
                                        if(queueArray.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            queueArray.dequeueAll();
                                            model.removeAllElements();
                                            //stackList.setText("Current Size: " + stackList.size());
                                        }
                                    }
                                });
                                
                                // Return To Base
                                JButton exitButton = new JButton("Exit to Home");
                                exitButton.setBounds(50, 450, 200, 40);
                                selectedFrame.add(exitButton);

                                exitButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addNumberField.setText("");
                                        queueArray.dequeueAll();
                                        model.removeAllElements();
                                        selectedFrame.setVisible(false);
                                        frame.setVisible(true);
                                    }
                                });

                                // --------------------------------------

                                selectedFrame.setVisible(true);

                            }
                            else if(comboBox2.getSelectedItem().equals("String")){

                                // TODO: Code Work Needed Here: Queue Reference Based (First-Last) String
                                JFrame selectedFrame = new JFrame("Queue Array Based | String");
                                JList<String> toList = new JList();
                                DefaultListModel<String> model = new DefaultListModel<>();
                                JScrollPane listScroll = new JScrollPane(toList);
                                
                                toList.setModel(model);
                                listScroll.setBounds(300, 0, 450,550);
               
                                selectedFrame.setLayout(new BorderLayout());
                                selectedFrame.setSize(new Dimension(800, 600));
                                selectedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setLayout(null);
                                selectedFrame.setResizable(false);
                                selectedFrame.add(listScroll);

                                // The beginnings
                                // Queue Array List
                                QueueReferenceBasedFirstLast<String> queueArray = new QueueReferenceBasedFirstLast<>();

                                // Size Labels
                                
                                JLabel sizeLabel = new JLabel("MAX SIZE: 50");
                                sizeLabel.setBounds(50,50, 200, 30);
                                selectedFrame.add(sizeLabel);


                                // The Textbox for Adding
                                JTextField addStringField = new JTextField();
                                addStringField.setBounds(50, 100, 200, 40);
                                selectedFrame.add(addStringField);

                                // Add Button
                                
                                JButton addButton = new JButton("Enqueue");
                                addButton.setBounds(50, 160, 200, 30);
                                selectedFrame.add(addButton);

                                addButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(addStringField.getText().equals("")){
                                            JOptionPane.showMessageDialog(addButton, "There is nothing in the textbox, try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            queueArray.enqueue(addStringField.getText());
                                            model.add(model.size(),addStringField.getText());
                                        }
                                    }
                                });

                                // Remove Button + Label

                                JButton removeButton = new JButton("Dequeue");
                                removeButton.setBounds(50, 200, 200, 30);
                                selectedFrame.add(removeButton);

                                removeButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                            
                                            System.out.println(queueArray.dequeue());
                                            model.remove(0);
                                        
                                    }
                                });

                                // Clear All

                                JButton clearButton = new JButton("Clear Button");
                                clearButton.setBounds(50, 240, 200, 30);
                                selectedFrame.add(clearButton);

                                clearButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addStringField.setText("");
                                        if(queueArray.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            queueArray.dequeueAll();
                                            model.removeAllElements();
                                            //stackList.setText("Current Size: " + stackList.size());
                                        }
                                    }
                                });
                                
                                // Return To Base
                                JButton exitButton = new JButton("Exit to Home");
                                exitButton.setBounds(50, 450, 200, 40);
                                selectedFrame.add(exitButton);

                                exitButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        addStringField.setText("");
                                        queueArray.dequeueAll();
                                        model.removeAllElements();
                                        selectedFrame.setVisible(false);
                                        frame.setVisible(true);
                                    }
                                });

                                // --------------------------------------

                                selectedFrame.setVisible(true);

                            }
                        }
                    }
                    
                }
                else if("Custom".equals(comboBox.getSelectedItem())){

                    JOptionPane.showMessageDialog(confirmBtn, "Selected Option Successful!", "Confirmation Viewer", JOptionPane.NO_OPTION); // Says successful then closes main frame.
                    frame.setVisible(false);

                    // Write your code here.  Add Files for Custom in GuiLists Folder called: CustomFiles
                    /*JFrame selectedFrame = new JFrame("YOUR_TITLE");
                    selectedFrame.setLayout(new BorderLayout());
                    selectedFrame.setSize(new Dimension(800, 600));
                    selectedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    selectedFrame.setLocationRelativeTo(null);
                    selectedFrame.setLayout(null);
                    selectedFrame.setResizable(false);
                    selectedFrame.setVisible(true);*/

                    // TODO: Write an example of a Custom List. Specifically a Favorite's List.
                    JFrame selectedFrame = new JFrame("LinkedList Array Based | Integers");
                                JList<Favorite> toList = new JList();
                                DefaultListModel<Favorite> model = new DefaultListModel<>();
                                JScrollPane listScroll = new JScrollPane(toList);
                                
                                toList.setModel(model);
                                listScroll.setBounds(300, 0, 450,550);
               
                                selectedFrame.setLayout(new BorderLayout());
                                selectedFrame.setSize(new Dimension(800, 600));
                                selectedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setLayout(null);
                                selectedFrame.setResizable(false);
                                selectedFrame.add(listScroll);

                                // The beginnings
                                // Array List
                                FavoriteList favoriteList = new FavoriteList();

                                // Size Labels
                                
                                JLabel sizeLabel = new JLabel("MAX SIZE: 50");
                                sizeLabel.setBounds(50,20, 200, 30);
                                selectedFrame.add(sizeLabel);

                                JLabel sizeLabel2 = new JLabel("Current Size: " + favoriteList.size());
                                sizeLabel2.setBounds(200,20, 200, 30);
                                selectedFrame.add(sizeLabel2);

                                JLabel titleLabel = new JLabel("Input Title:");
                                JLabel URLLabel = new JLabel("Input URL:");
                                titleLabel.setBounds(50, 50, 200, 40);
                                URLLabel.setBounds(50, 145, 200, 40);
                                selectedFrame.add(titleLabel);
                                selectedFrame.add(URLLabel);
                                
                                JTextField titleField = new JTextField();
                                JTextField URLField = new JTextField();
                                titleField.setBounds(50, 100, 200, 40);
                                URLField.setBounds(50, 190, 200, 40);
                                selectedFrame.add(titleField);
                                selectedFrame.add(URLField);

                                // Add Button
                                
                                JButton addButton = new JButton("Add Favorite");
                                addButton.setBounds(50, 250, 200, 30);
                                selectedFrame.add(addButton);

                                addButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s) {
                                        if(titleField.getText().equals("") || URLField.getText().equals("")){
                                            JOptionPane.showMessageDialog(addButton, "There is nothing in one of the textboxes, try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{ 
                                            if(favoriteList.size() != 50){
                                                    try {
                                                        favoriteList.add(favoriteList.size(), new Favorite(titleField.getText(), URLField.getText()));
                                                        model.add(favoriteList.size()-1, new Favorite(titleField.getText(), URLField.getText()));
                                                    } catch (CustomListException | MalformedURLException e) {
                                                        JOptionPane.showMessageDialog(addButton, "Failed URL.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                                    }
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(addButton, "Array is full.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                });

                                // Remove Button + Label

                                JButton removeButton = new JButton("Remove Selected");
                                removeButton.setBounds(50, 290, 200, 30);
                                selectedFrame.add(removeButton);

                                removeButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        if(favoriteList.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else if(toList.getSelectedIndex() == -1){
                                            JOptionPane.showMessageDialog(addButton, "You did not select an item. Try again.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            favoriteList.remove(toList.getSelectedIndex());
                                            model.remove(toList.getSelectedIndex());
                                            sizeLabel2.setText("Current Size: " + favoriteList.size());
                                        }
                                    }
                                });

                                // Clear All

                                JButton clearButton = new JButton("Clear Button");
                                clearButton.setBounds(50, 340, 200, 30);
                                selectedFrame.add(clearButton);

                                clearButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        titleField.setText("");
                                        URLField.setText("");
                                        if(favoriteList.isEmpty()){
                                            JOptionPane.showMessageDialog(addButton, "Array List is Empty.", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            favoriteList.removeAll();
                                            model.removeAllElements();
                                            sizeLabel2.setText("Current Size: " + favoriteList.size());
                                        }
                                    }
                                });
                                
                                // Return To Base
                                JButton exitButton = new JButton("Exit to Home");
                                exitButton.setBounds(50, 450, 200, 40);
                                selectedFrame.add(exitButton);

                                exitButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent s){
                                        titleField.setText("");
                                        URLField.setText("");
                                        favoriteList.removeAll();
                                        model.removeAllElements();
                                        selectedFrame.setVisible(false);
                                        frame.setVisible(true);
                                    }
                                });

                                // --------------------------------------

                                selectedFrame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(confirmBtn, "Please ensure all options are selected or at least CUSTOM is selected!", "Confirmation Viewer", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
}