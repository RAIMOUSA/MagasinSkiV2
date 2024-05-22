package UserInterface;

import Controller.ContactController;
import Controller.CustomerController;
import Controller.LocalityController;
import Model.Contact;
import Model.Customer;
import Model.Locality;
import Exception.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ListingCustomerModel extends AbstractTableModel {
    private final String[] columnNames;
    private ArrayList<Customer> customers;
    private final CustomerController customerController;
    private final ContactController contactController;
    private final LocalityController localityController;

    public ListingCustomerModel() {
        this.columnNames = new String[] {
                "ID",
                "Prénom",
                "Nom",
                "Genre",
                "Date de naissance",
                "Professionnel",
                "Email",
                "Téléphone",
                "Localité",
                "Code postal",
                "Rue",
                "Numéro de maison",
                "Boîte aux lettres"
        };
        this.customerController = new CustomerController();
        this.contactController = new ContactController();
        this.localityController = new LocalityController();

        // Charger les données des clients depuis le contrôleur des clients
        try {
            this.customers = customerController.readAllCustomers();
        } catch (CustomerException exception) {
            System.err.println("Erreur lors de la lecture des clients: " + exception.getMessage());
            this.customers = new ArrayList<>(); // Assurez-vous que la liste est initialisée
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return (customers == null) ? 0 : customers.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return switch (column) {
            case 0 -> Integer.class;
            case 1, 2, 3, 4, 6, 7, 8, 10, 11, 12 -> String.class;
            case 5 -> Boolean.class;
            case 9 -> Integer.class;
            default -> Object.class;
        };
    }

    @Override
    public Object getValueAt(int row, int col) {
        try {
            Customer customer = customers.get(row);
            Contact contact = contactController.getContactByMail(customer.getMail());
            Locality locality = localityController.getLocalityByCode(customer.getLocalityID());

            return switch (col) {
                case 0 -> customer.getUserID();
                case 1 -> customer.getFirstName();
                case 2 -> customer.getLastName();
                case 3 -> customer.getGender();
                case 4 -> customer.getDateOfBirth() == null ? "" : customer.getDateOfBirth().toString();
                case 5 -> customer.isProfessional();
                case 6 -> contact.getMail();
                case 7 -> contact.getPhoneNumber() == null ? "" : contact.getPhoneNumber();
                case 8 -> locality.getLocalityName();
                case 9 -> locality.getPostalCode();
                case 10 -> locality.getStreet();
                case 11 -> locality.getHouseNumber();
                case 12 -> locality.getLetterBox() == null ? "" : locality.getLetterBox();
                default -> null;
            };
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void removeCustomerAt(int rowIndex) {
        customers.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}