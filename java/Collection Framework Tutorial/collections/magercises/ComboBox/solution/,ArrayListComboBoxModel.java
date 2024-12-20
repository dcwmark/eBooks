// Copyright 1999 MageLang Institute
// <version>$Id: //depot/main/src/edu/modules/Collections/magercises/ComboBox/Solution/ArrayListComboBoxModel.java#1 $</version>

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class ArrayListComboBoxModel 
    extends AbstractListModel 
    implements MutableComboBoxModel {

  private Object selectedItem;

  // Define List variable
  private List list;

  public ArrayListComboBoxModel(List list) {
    // Save list in List variable
    this.list = new ArrayList(list);
  }

  // ListModel
  public int getSize() { 
    // Return the size of the ArrayList
    return list.size();
  }

  public Object getElementAt(int i) {
    // Return the element at the specified position
    return list.get(i);
  }

  // ComboBoxModel

  public Object getSelectedItem() {
    return selectedItem;
  }

  public void setSelectedItem(Object newValue) {
    selectedItem = newValue;
  }

  // MutableComboBoxModel
  public void addElement(Object element) {
    // Insert the element
    list.add(element);

    // Added at end, notify ListDataListener objects
    int length = getSize();
    fireIntervalAdded(this, length-1, length-1);
  }

  public void insertElementAt(Object element, int index) {
    // Insert the element at the specified position
    list.add(index, element);

    // Added in middle, notify ListDataListener objects
    fireIntervalAdded(this, index, index);
  }

  public void removeElement(Object element) {
    // Find out position
    int index = list.indexOf(element);

    if (index != -1) {

      // Remove an element
      list.remove(element);

      // Removed from middle, notify ListDataListener objects
      fireIntervalRemoved(this, index, index);
    }
  }

  public void removeElementAt(int index) {
    if (getSize() >= index) {

      // Remove an element at the specified position
      list.remove(index);

      // Removed from index, notify ListDataListener objects
      fireIntervalRemoved(this, index, index);
    }
  }
}
