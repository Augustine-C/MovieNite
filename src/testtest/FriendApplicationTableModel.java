package testtest;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class FriendApplicationTableModel extends AbstractTableModel {
  private ArrayList<String> data = null;
  private String[] columnNames = { "User1" };

  public FriendApplicationTableModel(ArrayList<String> data) {
    this.data = data;
  }

  public int getRowCount() {
    return data.size();
  }

  public int getColumnCount() {
    return columnNames.length;
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    return ((String) data.get(rowIndex));
  }

  public String getColumnName(int index) {
    return columnNames[index];
  }

}