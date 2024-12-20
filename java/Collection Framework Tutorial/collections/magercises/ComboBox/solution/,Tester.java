// Copyright 1999 MageLang Institute
// <version>$Id: //depot/main/src/edu/modules/Collections/magercises/ComboBox/Solution/Tester.java#1 $</version>
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.*;

public class Tester {

  // Test program
  public static void main(String args[]) {
    JFrame frame = new JFrame("ArrayListComboBoxModel");
    frame.setDefaultCloseOperation(3);

    // Convert command line argument array into List
    List list = Arrays.asList(args);

    // Use List for ComboBoxModel
    ArrayListComboBoxModel model = new ArrayListComboBoxModel(list);

    // Update model
    // Add a few names
    model.insertElementAt("Tom", 0);
    model.addElement("Dick");
    model.addElement("Harry");
    model.addElement("Bill");
    // Remove first entry
    model.removeElementAt(0);
    // Remove "Bill"
    model.removeElement("Bill");

    // Use model in JComboBox
    JComboBox comboBox = new JComboBox (model);
    comboBox.setSelectedIndex(0);

    Container contentPane = frame.getContentPane();
    contentPane.add(comboBox, BorderLayout.NORTH);
    frame.setSize(200, 150);
    frame.setVisible(true);
  }
}
