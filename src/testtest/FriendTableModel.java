package testtest;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class FriendTableModel extends AbstractTableModel {
  private ArrayList<String> data = null;
  private String[] columnNames = { "User" };

  public FriendTableModel(ArrayList<String> data, String columnName) {
    this.data = data;
    this.columnNames[0] = columnName;
  }

  public int getRowCount() {
    return data.size();
  }

  public int getColumnCount() {
    return columnNames.length;
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    return data.get(rowIndex);
  }

  public String getColumnName(int index) {
    return columnNames[index];
  }

}