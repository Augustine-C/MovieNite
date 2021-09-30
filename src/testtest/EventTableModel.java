package testtest;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class EventTableModel extends AbstractTableModel {
  private ArrayList<Event> data = null;
  private String[] columnNames = { "EventID", "Location", "TimeMovieEvent", "Host" };

  public EventTableModel(ArrayList<Event> data) {
    this.data = data;
  }

  public int getRowCount() {
    return data.size();
  }

  public int getColumnCount() {
    return columnNames.length;
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    return ((Event) data.get(rowIndex)).getValue(columnNames[columnIndex]);
  }

  public String getColumnName(int index) {
    return columnNames[index];
  }
}