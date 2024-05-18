package UserInterface;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ActivePromotionsModel extends AbstractTableModel {
    private String[] columnNames = {"Produit", "Ancien prix (€)", "Promotion (%)", "Nouveau prix (€)", "Date de fin"};
    private List<Object[]> data = new ArrayList<>();


    public ActivePromotionsModel() {
        Object[] row1 = new Object[]{"WARM300", 10.0, 10, 9.0, "2024-05-31"};
        Object[] row2 = new Object[]{"PROTEC2000", 20.0, 15, 17.0, "2024-05-31"};
        Object[] row3 = new Object[]{"VISIO200", 25.0, 20, 20.0, "2024-05-31"};
        Object[] row4 = new Object[]{"FAST600", 40.0, 25, 30.0, "2024-05-31"};
        Object[] row5 = new Object[]{"PROTECT1000", 50.0, 40, 30.0, "2024-05-31"};
        Object[] row6 = new Object[]{"ULTRA300", 10.0, 50, 5.0, "2024-05-31"};


        data.add(row1);
        data.add(row2);
        data.add(row3);
        data.add(row4);
        data.add(row5);
        data.add(row6);
    }

    public void setData(List<Object[]> newData) {
        data.clear();
        data.addAll(newData);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
