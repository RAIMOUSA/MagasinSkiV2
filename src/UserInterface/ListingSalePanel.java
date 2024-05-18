package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ListingSalePanel extends JPanel {
    private JPanel contentPanel;
    private JTable saleTable;
    private ListingSaleModel saleModel;
    private JSpinner dateSpinner;
    private JButton filterButton;

    public ListingSalePanel() {
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("LISTE DES VENTES ");
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
        northPanel.add(label);

        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);

        filterButton = new JButton("Choisir la date");
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterSalesByDate((Date) dateSpinner.getValue());
            }
        });

        // Panel for date filter controls
        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.add(new JLabel("Date:"));
        filterPanel.add(dateSpinner);
        filterPanel.add(filterButton);

        northPanel.add(filterPanel);

        add(northPanel, BorderLayout.NORTH);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        saleModel = new ListingSaleModel();
        saleTable = new JTable(saleModel);
        saleTable.setDefaultEditor(Object.class, null); // Rendre toutes les cellules non éditables
        JScrollPane scrollPane = new JScrollPane(saleTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void filterSalesByDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String selectedDate = sdf.format(date);
        saleModel.filterByDate(selectedDate);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Listing Sale");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new ListingSalePanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
