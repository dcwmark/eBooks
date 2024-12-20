// Copyright 1999 MageLang Institute
// $Id: //depot/main/src/edu/modules/Collections/magercises/JList/Solution/Tester.java#2 $
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Tester {

  // Test program
  public static void main(String args[]) {
    JFrame frame = new JFrame("Sorted JList");
    frame.setDefaultCloseOperation(3);

    final SortedListModel model = new SortedListModel();
    model.addAll(args);

    // Use model with JList
    JList list = new JList(model);
    JScrollPane scrollPane = new JScrollPane(list);

    Container contentPane = frame.getContentPane();
    contentPane.add(scrollPane, BorderLayout.CENTER);

    final JTextField textField = new JTextField();
    contentPane.add(textField, BorderLayout.NORTH);

    JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
    JButton clearButton = new JButton("Clear");
    buttonPanel.add(clearButton);
    JButton addOneButton = new JButton("Add 1");
    buttonPanel.add(addOneButton);
    JButton addManyButton = new JButton("Add Many");
    buttonPanel.add(addManyButton);
    JButton removeFirstButton = new JButton("Remove First");
    buttonPanel.add(removeFirstButton);
    JButton removeLastButton = new JButton("Remove Last");
    buttonPanel.add(removeLastButton);
    JButton printButton = new JButton("Print");
    buttonPanel.add(printButton);

    ActionListener clearAction = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        model.clear();
      };
    };
    clearButton.addActionListener(clearAction);

    ActionListener addOneAction = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        String entry = textField.getText().trim();
        if (entry.length() != 0) {
          model.add(entry);
        }
      };
    };
    addOneButton.addActionListener(addOneAction);

    ActionListener addManyAction = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        String entry = textField.getText().trim();
        if (entry.length() != 0) {
          StringTokenizer tokenizer = new StringTokenizer(entry);
          while (tokenizer.hasMoreTokens()) {
            model.add(tokenizer.nextToken());
          }
        }
      };
    };
    addManyButton.addActionListener(addManyAction);

    ActionListener removeFirstAction = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        Object first = model.firstElement();
        if (first != null) {
          model.removeElement(first);
        }
      };
    };
    removeFirstButton.addActionListener(removeFirstAction);

    ActionListener removeLastAction = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        Object last = model.lastElement();
        if (last != null) {
          model.removeElement(last);
        }
      };
    };
    removeLastButton.addActionListener(removeLastAction);

    ActionListener printAction = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        // Print out the elements in 'model'
      };
    };
    printButton.addActionListener(printAction);

    contentPane.add(buttonPanel, BorderLayout.SOUTH);
    frame.setSize(400, 250);
    frame.setVisible(true);
  }
}
