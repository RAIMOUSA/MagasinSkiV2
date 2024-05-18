package src.UserInterface;
import javax.swing.*;
import java.awt.*;

public class ListingCustomerPanel extends JPanel {
    private JPanel contentPanel;
    private JTable clientTable;

    public ListingCustomerPanel() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("LISTE DES CLIENTS ");
        label.setHorizontalAlignment(SwingConstants.CENTER); // Centrer le texte horizontalement
        add(label, BorderLayout.NORTH);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        clientTable = new JTable(new ListingCustomerModel());
        JScrollPane scrollPane = new JScrollPane(clientTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }
}

