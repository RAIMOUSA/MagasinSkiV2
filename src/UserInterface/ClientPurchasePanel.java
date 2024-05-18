package src.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientPurchasePanel extends JPanel {
    private JPanel contentPanel;
    private JTable purchaseTable;
    private ListingClientPurchaseModel purchaseModel;
    private JTextField userIDField;
    private JButton filterButton;

    public ClientPurchasePanel() {
        setLayout(new BorderLayout());

        // Create the panel that will hold both the label and the filter controls
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("ACHATS DU CLIENT");
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
        northPanel.add(label);

        // Create the user ID field
        userIDField = new JTextField(10);

        // Create the filter button
        filterButton = new JButton("Filtrer par userID");
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterPurchasesByUserID(userIDField.getText().trim());
            }
        });

        // Panel for userID filter controls
        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.add(new JLabel("UserID:"));
        filterPanel.add(userIDField);
        filterPanel.add(filterButton);

        northPanel.add(filterPanel);

        add(northPanel, BorderLayout.NORTH);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        purchaseModel = new ListingClientPurchaseModel();
        purchaseTable = new JTable(purchaseModel);
        purchaseTable.setDefaultEditor(Object.class, null); // Rendre toutes les cellules non éditables
        JScrollPane scrollPane = new JScrollPane(purchaseTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void filterPurchasesByUserID(String userID) {
        purchaseModel.filterByUserID(userID);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Client Purchase");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new ClientPurchasePanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
