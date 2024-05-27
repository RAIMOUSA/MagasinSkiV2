package UserInterface;

import Controller.ContactController;
import Controller.CustomerController;
import Controller.LocalityController;
import Model.Customer;
import Exception.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModifyCustomerListPanel extends JPanel implements ActionListener {

    private JTable clientTable;
    private CustomerController customerController;

    private JButton selectButton;

    public ModifyCustomerListPanel() {
        setLayout(new BorderLayout());

        customerController = new CustomerController();

        JLabel label = new JLabel("CHOISIR LE CLIENT A MODIFIER ");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        clientTable = new JTable(new ListingCustomerModel());
        JScrollPane scrollPane = new JScrollPane(clientTable);
        add(scrollPane, BorderLayout.CENTER);

        selectButton = new JButton("Sélectionner");
        selectButton.addActionListener(this);
        add(selectButton, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectButton) {
            int selectedRow = clientTable.getSelectedRow();
            if (selectedRow != -1) {
                Customer selectedCustomer = getSelectedCustomer(selectedRow);
                if (selectedCustomer != null) {
                    JFrame modifyClientFrame = new JFrame("Modifier Client");
                    modifyClientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    try {
                        modifyClientFrame.getContentPane().add(new ModifyCustomerFormPanel(selectedCustomer, clientTable));
                    } catch (ContactException ex) {
                        throw new RuntimeException(ex);
                    } catch (LocalityException ex) {
                        throw new RuntimeException(ex);
                    }
                    modifyClientFrame.pack();
                    modifyClientFrame.setLocationRelativeTo(null);
                    modifyClientFrame.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un client à modifier.");
            }
        }
    }

    private Customer getSelectedCustomer(int rowIndex) {
        try {
            ArrayList<Customer> customers = customerController.readAllCustomers();
            return customers.get(rowIndex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération du client : " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Modify Client List");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new ModifyCustomerListPanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
