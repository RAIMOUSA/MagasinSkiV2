package src.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class MonthlySaleStatsPanel extends JPanel {
    private JPanel contentPanel;
    private JTable statsTable;
    private MonthlySaleStatsModel statsModel;
    private JSpinner monthSpinner;
    private JComboBox<String> typeComboBox;
    private JButton filterButton;

    public MonthlySaleStatsPanel() {
        setLayout(new BorderLayout());

        // Create the panel that will hold both the label and the filter controls
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("STATISTIQUES DES VENTES");
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
        northPanel.add(label);

        // Create the month spinner
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.MONTH);
        monthSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(monthSpinner, "yyyy-MM");
        monthSpinner.setEditor(dateEditor);

        // Create the type combo box
        statsModel = new MonthlySaleStatsModel();
        Set<String> productTypes = statsModel.getProductTypes();
        typeComboBox = new JComboBox<>(productTypes.toArray(new String[0]));

        // Create the filter button
        filterButton = new JButton("Filtrer par type et mois");
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                String selectedMonthYear = sdf.format((Date) monthSpinner.getValue());
                String type = (String) typeComboBox.getSelectedItem();
                statsModel.filterByTypeAndMonth(type, selectedMonthYear);
            }
        });

        // Panel for filter controls
        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.add(new JLabel("Mois:"));
        filterPanel.add(monthSpinner);
        filterPanel.add(new JLabel("Type de produit:"));
        filterPanel.add(typeComboBox);
        filterPanel.add(filterButton);

        northPanel.add(filterPanel);

        add(northPanel, BorderLayout.NORTH);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        statsTable = new JTable(statsModel);
        statsTable.setDefaultEditor(Object.class, null); // Rendre toutes les cellules non éditables
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
