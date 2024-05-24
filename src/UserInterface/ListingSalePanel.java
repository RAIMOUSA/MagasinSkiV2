package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class ListingSalePanel extends JPanel {
    private JPanel contentPanel;
    private JTable saleTable;
    private ListingSaleModel saleModel;
    private JButton filterButton;
    private JComboBox<String> dateComboBox;

    public ListingSalePanel() {
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("LISTE DES VENTES ");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(label);

        saleModel = new ListingSaleModel();
        Set<LocalDate> dates = saleModel.getDates();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] dateStrings = dates.stream()
                .map(date -> date.format(formatter))
                .toArray(String[]::new);

        dateComboBox = new JComboBox<>(dateStrings);

        filterButton = new JButton("Choisir la date");
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDate = (String) dateComboBox.getSelectedItem();
                saleModel.filterByDate(selectedDate);
            }
        });


        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.add(new JLabel("Date:"));
        filterPanel.add(dateComboBox);
        filterPanel.add(filterButton);

        northPanel.add(filterPanel);

        add(northPanel, BorderLayout.NORTH);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        saleTable = new JTable(saleModel);
        saleTable.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(saleTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
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
