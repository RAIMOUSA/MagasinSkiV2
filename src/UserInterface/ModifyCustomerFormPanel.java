package UserInterface;

import Controller.ContactController;
import Controller.CustomerController;
import Controller.LocalityController;
import Model.Customer;
import Model.Contact;
import Model.Locality;
import Exception.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ModifyCustomerFormPanel extends JPanel {

    private JButton saveButton;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JSpinner dobSpinner;
    private JCheckBox dobCheckBox;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField postalCodeField;
    private JTextField streetField;
    private JTextField houseNumberField;
    private JTextField letterBoxField;
    private JTextField localityNameField;
    private JCheckBox isProfessionalCheckBox;
    private JComboBox genderComboBox;
    private JCheckBox genderCheckBox;

    private CustomerController customerController;
    private ContactController contactController;
    private LocalityController localityController;
    private Customer customer;
    private Locality localityBeforeChange;
    private String oldMail;
    private JTable clientTable;

    public ModifyCustomerFormPanel(Customer customer, JTable clientTable) throws ContactException, LocalityException {
        this.customer = customer;
        this.customerController = new CustomerController();
        this.contactController = new ContactController();
        this.localityController = new LocalityController();
        this.clientTable = clientTable;
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        formPanel.add(new JLabel("Prénom: *"), gbc);
        gbc.gridx++;
        firstNameField = new JTextField(15);
        firstNameField.setText(customer.getFirstName());
        formPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Nom: *"), gbc);
        gbc.gridx++;
        lastNameField = new JTextField(15);
        lastNameField.setText(customer.getLastName());
        formPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        dobCheckBox = new JCheckBox("Voulez-vous remplir la date de naissance?");
        dobCheckBox.setSelected(false);
        dobCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dobSpinner.setEnabled(dobCheckBox.isSelected());
            }
        });
        formPanel.add(dobCheckBox, gbc);
        gbc.gridy++;
        formPanel.add(new JLabel("Date de naissance: "), gbc);
        gbc.gridx++;
        dobSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dobSpinner, "yyyy-MM-dd");
        dobSpinner.setEditor(dateEditor);
        formPanel.add(dobSpinner, gbc);
        dobSpinner.setEnabled(false);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Professionnel: "), gbc);
        gbc.gridx++;
        isProfessionalCheckBox = new JCheckBox();
        isProfessionalCheckBox.setSelected(customer.isProfessional());
        formPanel.add(isProfessionalCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        genderCheckBox = new JCheckBox("Voulez-vous remplir le genre?");
        genderCheckBox.setSelected(false);
        genderCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genderComboBox.setEnabled(genderCheckBox.isSelected());
            }
        });
        formPanel.add(genderCheckBox, gbc);
        gbc.gridx++;
        genderComboBox = new JComboBox<>(new String[]{"Masculin", "Féminin", "X"});
        genderComboBox.setEnabled(false);
        formPanel.add(genderComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Email: *"), gbc);
        gbc.gridx++;
        emailField = new JTextField(20);
        oldMail = customer.getMail();
        emailField.setText(contactController.getContactByMail(customer.getMail()).getMail());
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Numéro de téléphone: "), gbc);
        gbc.gridx++;
        phoneField = new JFormattedTextField(createFormatter("+## ### ######"));
        phoneField.setColumns(15);
        phoneField.setText(contactController.getContactByMail(customer.getMail()).getPhoneNumber());
        formPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Code postal: *"), gbc);
        gbc.gridx++;
        postalCodeField = new JTextField(10);
        Locality locality = localityController.getLocalityByCode(customer.getLocalityID());
        localityBeforeChange =  locality;
        postalCodeField.setText(String.valueOf(locality.getPostalCode()));
        formPanel.add(postalCodeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Rue: *"), gbc);
        gbc.gridx++;
        streetField = new JTextField(20);
        streetField.setText(locality.getStreet());
        formPanel.add(streetField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Numéro de maison: *"), gbc);
        gbc.gridx++;
        houseNumberField = new JTextField(5);
        houseNumberField.setText(String.valueOf(locality.getHouseNumber()));
        formPanel.add(houseNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Boîte aux lettres: "), gbc);
        gbc.gridx++;
        letterBoxField = new JTextField(5);
        letterBoxField.setText(locality.getLetterBox());
        formPanel.add(letterBoxField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Nom Localité: *"), gbc);
        gbc.gridx++;
        localityNameField = new JTextField(20);
        localityNameField.setText(locality.getLocalityName());
        formPanel.add(localityNameField, gbc);


        add(formPanel, BorderLayout.CENTER);

        saveButton = new JButton("Enregistrer");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCustomer();
            }
        });
        add(saveButton, BorderLayout.SOUTH);
    }

    private void saveCustomer() {
        try {
            String newEmail = emailField.getText();
            if (!newEmail.equals(oldMail) && contactController.emailExists(newEmail)) {
                JOptionPane.showMessageDialog(this, "Cet email est déjà utilisé par un autre client.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String newPhoneNumber = phoneField.getText();
            if (!newPhoneNumber.equals(contactController.getContactByMail(customer.getMail()).getPhoneNumber()) && contactController.phoneExists(newPhoneNumber)) {
                JOptionPane.showMessageDialog(this, "Ce numéro de téléphone est déjà utilisé par un autre client.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Contact contact = new Contact(newEmail, newPhoneNumber);
            Locality locality = new Locality(
                    localityNameField.getText(),
                    Integer.parseInt(postalCodeField.getText()),
                    streetField.getText(),
                    Integer.parseInt(houseNumberField.getText()),
                    letterBoxField.getText()
            );
            customer.setFirstName(firstNameField.getText());
            customer.setLastName(lastNameField.getText());
            if (genderCheckBox.isSelected()){
                customer.setGender(genderComboBox.getSelectedItem().toString());
            }  else{
                customer.setGender(null);
            }
            if(dobCheckBox.isSelected())
                customer.setDateOfBirth(getSelectedDate());
            else
                customer.setDateOfBirth(null);
            customer.setMail(emailField.getText());
            customer.setProfessional(isProfessionalCheckBox.isSelected());


            contactController.updateContactPhone(oldMail, contact);
            contactController.updateContactMail(oldMail, contact);
            locality.setLocalityID(localityController.getLocalityID(localityBeforeChange));
            localityController.updateLocality(locality);
            customerController.updateCustomer(customer);
            JOptionPane.showMessageDialog(this, "Le client a été mis à jour avec succès.");
           refresh();

            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour du client: " + ex.getMessage());
        }

    }

    private LocalDate getSelectedDate() {
        Date selectedDate = (Date) dobSpinner.getValue();
        return selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void refresh() {
        clientTable.setModel(new ListingCustomerModel());
    }

    private MaskFormatter createFormatter(String format) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(format);
        } catch (java.text.ParseException e) {
            System.err.println("Format non valide");
            System.exit(-1);
        }
        return formatter;
    }
}
