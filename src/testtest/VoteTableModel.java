package testtest;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class VoteTableModel extends AbstractTableModel {
    private ArrayList<Vote> data = null;
    private String[] columnNames = { "Movie", "Vote" };

    public VoteTableModel(ArrayList<Vote> data) {
        this.data = data;
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return ((Vote) data.get(rowIndex)).getValue(columnNames[columnIndex]);
    }

    public String getColumnName(int index) {
        return columnNames[index];
    }

}