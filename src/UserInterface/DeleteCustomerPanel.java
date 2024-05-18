package src.UserInterface;

import Controller.CustomerController;
import Exception.CustomerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteCustomerPanel extends JPanel {
    private JButton deleteButton;
    private JTable clientTable;
    private CustomerController customerController;

    public DeleteCustomerPanel() {
        this.customerController = new CustomerController();

        setLayout(new BorderLayout());

        JLabel label = new JLabel("CHOISIR LE CLIENT À SUPPRIMER");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        clientTable = new JTable(new ListingCustomerModel());
        JScrollPane scrollPane = new JScrollPane(clientTable);
        add(scrollPane, BorderLayout.CENTER);

        deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = clientTable.getSelectedRow();
                if (selectedRow != -1) {
                    int customerId = (Integer) clientTable.getValueAt(selectedRow, 0); // Conversion correcte
                    int confirmation = JOptionPane.showConfirmDialog(
                            null,
                            "Voulez-vous vraiment supprimer ce client ?",
                            "Confirmation de suppression",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirmation == JOptionPane.YES_OPTION) {
                        try {
                            customerController.deleteCustomer(customerId);
                            ((ListingCustomerModel) clientTable.getModel()).removeCustomerAt(selectedRow);
                            JOptionPane.showMessageDialog(DeleteCustomerPanel.this, "Le client a été supprimé avec succès.");
                        } catch (CustomerException ex) {
                            JOptionPane.showMessageDialog(DeleteCustomerPanel.this, "Erreur lors de la suppression du client: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(DeleteCustomerPanel.this, "Veuillez sélectionner un client à supprimer.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add(deleteButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
