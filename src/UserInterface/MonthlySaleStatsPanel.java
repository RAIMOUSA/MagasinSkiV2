package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Month;
import java.util.Set;
import Exception.*;

public class MonthlySaleStatsPanel extends JPanel {
    private JPanel contentPanel;
    private JTable statsTable;
    private MonthlySaleStatsModel statsModel;
    private JComboBox<String> yearMonthComboBox;
    private JComboBox<String> typeComboBox;
    private JButton filterButton;

    public MonthlySaleStatsPanel() {
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("STATISTIQUES DES VENTES");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(label);

        statsModel = new MonthlySaleStatsModel();
        Set<String> dates = statsModel.getDates();
        yearMonthComboBox = new JComboBox<>(dates.toArray(new String[0]));

        Set<String> productTypes = statsModel.getProductTypes();
        typeComboBox = new JComboBox<>(productTypes.toArray(new String[0]));

        filterButton = new JButton("Filtrer par type et mois");
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDate = (String) yearMonthComboBox.getSelectedItem();
                int selectYear = Integer.parseInt(selectedDate.split("-")[0]);
                int selectMonthValue = Integer.parseInt(selectedDate.split("-")[1]);
                Month selectMonth = Month.of(selectMonthValue);

                String type = (String) typeComboBox.getSelectedItem();

                try {
                    statsModel.filterByTypeAndMonth(type, selectMonth, selectYear);
                } catch (ProductException | SaleException ex) {
                    ex.printStackTrace();
                }
            }
        });


        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.add(new JLabel("Mois et Année:"));
        filterPanel.add(yearMonthComboBox);
        filterPanel.add(new JLabel("Type de produit:"));
        filterPanel.add(typeComboBox);
        filterPanel.add(filterButton);

        northPanel.add(filterPanel);

        add(northPanel, BorderLayout.NORTH);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        statsTable = new JTable(statsModel);
        statsTable.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(statsTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Sales Stats");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new MonthlySaleStatsPanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
