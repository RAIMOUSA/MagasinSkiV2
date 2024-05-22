package UserInterface;

import Controller.ContactController;
import Controller.CustomerController;
import Controller.LocalityController;
import Model.Customer;
import Model.Contact;
import Model.Locality;
import Exception.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ModifyCustomerFormPanel extends JPanel {

    private JButton saveButton;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField dobField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField postalCodeField;
    private JTextField streetField;
    private JTextField houseNumberField;
    private JTextField letterBoxField;
    private JTextField localityIDField;
    private JTextField localityNameField;
    private JCheckBox isProfessionalCheckBox;

    private CustomerController customerController;
    private ContactController contactController;
    private LocalityController localityController;
    private Customer customer;
    private Locality locality;
    private Contact contact;
    private Locality localityBeforeChange;
    private String oldMail;

    public ModifyCustomerFormPanel(Customer customer, CustomerController customerController,
                                   ContactController contactController, LocalityController localityController) throws ContactException, LocalityException {
        this.customer = customer;
        this.customerController = customerController;
        this.contactController = contactController;
        this.localityController = localityController;

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
        formPanel.add(new JLabel("Date de naissance: "), gbc);
        gbc.gridx++;
        dobField = new JTextField(15);
        if (customer.getDateOfBirth() == null) {
            dobField.setText("");
        } else {
            dobField.setText(customer.getDateOfBirth().toString());
        }
        formPanel.add(dobField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Professionnel: "), gbc);
        gbc.gridx++;
        isProfessionalCheckBox = new JCheckBox();
        isProfessionalCheckBox.setSelected(customer.isProfessional());
        formPanel.add(isProfessionalCheckBox, gbc);

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
        phoneField = new JTextField(15);
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
        localityNameField.setText(locality.getLocalityName());  // Assuming getName() method exists in Locality class
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
            // Mettre à jour le modèle avec les nouvelles données
            Contact contact = new Contact(emailField.getText(), phoneField.getText());
            Locality locality = new Locality(
                    localityNameField.getText(),
                    Integer.parseInt(postalCodeField.getText()),
                    streetField.getText(),
                    Integer.parseInt(houseNumberField.getText()),
                    letterBoxField.getText()
            );
            customer.setFirstName(firstNameField.getText());
            customer.setLastName(lastNameField.getText());
            customer.setDateOfBirth(LocalDate.parse(dobField.getText()));
            customer.setMail(emailField.getText());
            customer.setProfessional(isProfessionalCheckBox.isSelected());

            // Enregistrer les mises à jour dans la base de données
            contactController.updateContactPhone(contact);
            contactController.updateContactMail(oldMail, contact);
            locality.setLocalityID(localityController.getLocalityID(localityBeforeChange));
            localityController.updateLocality(locality);
            customerController.updateCustomer(customer);

            JOptionPane.showMessageDialog(this, "Le client a été mis à jour avec succès.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour du client: " + ex.getMessage());
        }
    }
}
