package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import Exception.*;

public class ClientPurchasePanel extends JPanel {
    private JPanel contentPanel;
    private JTable purchaseTable;
    private ListingClientPurchaseModel purchaseModel;
    private JButton filterButton;
    private JComboBox<Integer> userIDComboBox;

    public ClientPurchasePanel() {
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("ACHATS DU CLIENT");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(label);

        purchaseModel = new ListingClientPurchaseModel();
        Set<Integer> userIDs = purchaseModel.getUserIDs();
        userIDComboBox = new JComboBox<>(userIDs.toArray(new Integer[0]));


        filterButton = new JButton("Filtrer par userID");
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    filterPurchasesByUserID((Integer) userIDComboBox.getSelectedItem());
                } catch (SaleException | SaleDetailException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.add(new JLabel("UserID:"));
        filterPanel.add(userIDComboBox);
        filterPanel.add(filterButton);

        northPanel.add(filterPanel);

        add(northPanel, BorderLayout.NORTH);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        purchaseTable = new JTable(purchaseModel);
        purchaseTable.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(purchaseTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void filterPurchasesByUserID(Integer userID) throws SaleException, SaleDetailException {
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
