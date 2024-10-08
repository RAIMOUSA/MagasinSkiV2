package UserInterface;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import Controller.ContactController;
import Controller.CustomerController;
import Controller.LocalityController;
import Model.Contact;
import Model.Customer;
import Model.Locality;

public class AddCustomerPanel extends JPanel {

    private JButton addButton;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JFormattedTextField phoneField;
    private JCheckBox professionalCheckBox;
    private JTextField emailField;
    private JTextField descriptionField;
    private JTextField postalCodeField;
    private JTextField streetField;
    private JTextField houseNumberField;
    private JTextField letterBoxField;
    private JSpinner dobSpinner;
    private JComboBox<String> genderComboBox;
    private JCheckBox dobCheckBox;
    private JCheckBox genderCheckBox;

    private CustomerController customerController = new CustomerController();
    private ContactController contactController = new ContactController();
    private LocalityController localityController = new LocalityController();

    public AddCustomerPanel() {
        setLayout(new BorderLayout());

        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.CENTER);

        JLabel label = new JLabel("AJOUTER UN CLIENT ");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        addButton = new JButton("Ajouter le client");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });
        addButton.setEnabled(false);
        add(addButton, BorderLayout.SOUTH);

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateForm();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateForm();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateForm();
            }
        };

        firstNameField.getDocument().addDocumentListener(documentListener);
        lastNameField.getDocument().addDocumentListener(documentListener);
        emailField.getDocument().addDocumentListener(documentListener);
        streetField.getDocument().addDocumentListener(documentListener);
        houseNumberField.getDocument().addDocumentListener(documentListener);
        descriptionField.getDocument().addDocumentListener(documentListener);
        postalCodeField.getDocument().addDocumentListener(documentListener);
        professionalCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateForm();
            }
        });

        addNumberValidationListener(postalCodeField);
        addNumberValidationListener(houseNumberField);

        addStringValidationListener(firstNameField);
        addStringValidationListener(lastNameField);
        addStringValidationListener(descriptionField);
        addStringValidationListener(streetField);

    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        panel.add(new JLabel("Prénom: *"), gbc);
        gbc.gridx++;
        firstNameField = new JTextField(15);
        firstNameField.setBorder(BorderFactory.createEmptyBorder());
        panel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Nom: *"), gbc);
        gbc.gridx++;
        lastNameField = new JTextField(15);
        lastNameField.setBorder(BorderFactory.createEmptyBorder());
        panel.add(lastNameField, gbc);

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
        panel.add(dobCheckBox, gbc);
        gbc.gridx++;
        dobSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dobSpinner, "yyyy-MM-dd");
        dobSpinner.setEditor(dateEditor);
        dobSpinner.setEnabled(false);
        panel.add(dobSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Professionnel: *"), gbc);
        gbc.gridx++;
        professionalCheckBox = new JCheckBox();
        panel.add(professionalCheckBox, gbc);

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
        panel.add(genderCheckBox, gbc);
        gbc.gridx++;
        genderComboBox = new JComboBox<>(new String[]{"Masculin", "Féminin", "Autre"});
        genderComboBox.setEnabled(false);
        panel.add(genderComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Email: *"), gbc);
        gbc.gridx++;
        emailField = new JTextField(20);
        emailField.setBorder(BorderFactory.createEmptyBorder());
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Numéro de téléphone: "), gbc);
        gbc.gridx++;
        phoneField = new JFormattedTextField(createFormatter("+## ### ######"));
        phoneField.setColumns(15);
        phoneField.setBorder(BorderFactory.createEmptyBorder());
        panel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Localité: *"), gbc);
        gbc.gridx++;
        descriptionField = new JTextField(20);
        descriptionField.setBorder(BorderFactory.createEmptyBorder());
        panel.add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Code postal: *"), gbc);
        gbc.gridx++;
        postalCodeField = new JTextField(10);
        postalCodeField.setBorder(BorderFactory.createEmptyBorder());
        panel.add(postalCodeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Rue: *"), gbc);
        gbc.gridx++;
        streetField = new JTextField(20);
        streetField.setBorder(BorderFactory.createEmptyBorder());
        panel.add(streetField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Numéro de maison: *"), gbc);
        gbc.gridx++;
        houseNumberField = new JTextField(5);
        houseNumberField.setBorder(BorderFactory.createEmptyBorder());
        panel.add(houseNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Boîte aux lettres: "), gbc);
        gbc.gridx++;
        letterBoxField = new JTextField(5);
        letterBoxField.setBorder(BorderFactory.createEmptyBorder());
        panel.add(letterBoxField, gbc);

        return panel;
    }

    private void validateForm() {
        boolean firstNameValid = !firstNameField.getText().trim().isEmpty();
        boolean lastNameValid = !lastNameField.getText().trim().isEmpty();
        boolean emailValid = !emailField.getText().trim().isEmpty();
        boolean postalCodeValid = !postalCodeField.getText().trim().isEmpty();
        boolean streetValid = !streetField.getText().trim().isEmpty();
        boolean houseNumberValid = !houseNumberField.getText().trim().isEmpty();
        boolean descriptionValid = !descriptionField.getText().trim().isEmpty();

        addButton.setEnabled(firstNameValid && lastNameValid && emailValid && postalCodeValid && streetValid && houseNumberValid && descriptionValid);
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

    private void addCustomer() {

        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        LocalDate dob = dobCheckBox.isSelected() ? getSelectedDate() : null;
        boolean isProfessional = professionalCheckBox.isSelected();
        String gender = genderCheckBox.isSelected() ? (String) genderComboBox.getSelectedItem() : null;
        String email = emailField.getText().trim();
        String phoneNumber = phoneField.getText().trim();
        String locality = descriptionField.getText().trim();
        String postalCode = postalCodeField.getText().trim();
        String street = streetField.getText().trim();
        String houseNumber = houseNumberField.getText().trim();
        String letterBox = letterBoxField.getText().trim();

        boolean emailExists = contactController.emailExists(email);
        boolean phoneExists = contactController.phoneExists(phoneNumber);

        if (emailExists || phoneExists) {

            if (emailExists && phoneExists) {
                JOptionPane.showMessageDialog(this, "L'email et le numéro de téléphone sont déjà enregistrés.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else if (emailExists) {
                JOptionPane.showMessageDialog(this, "L'email est déjà enregistré.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Le numéro de téléphone est déjà enregistré.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }

        Customer customer = new Customer(0, firstName, lastName, dob, isProfessional, gender, 0, email);
        Contact contact = new Contact(email, phoneNumber);
        Locality locality1 = new Locality(0, locality, Integer.parseInt(postalCode), street, Integer.parseInt(houseNumber), letterBox);

        try {
            contactController.createContact(contact);
            localityController.createLocality(locality1);
            customer.setLocalityID(localityController.getLocalityID(locality1));
            customerController.createCustomer(customer);

            JOptionPane.showMessageDialog(this, "Le client a été ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            resetFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du client: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        dobSpinner.setValue(new Date());
        professionalCheckBox.setSelected(false);
        genderComboBox.setSelectedIndex(0);
        emailField.setText("");
        phoneField.setText("");
        descriptionField.setText("");
        postalCodeField.setText("");
        streetField.setText("");
        houseNumberField.setText("");
        letterBoxField.setText("");
        dobCheckBox.setSelected(false);
        dobSpinner.setEnabled(false);
        genderCheckBox.setSelected(false);
        genderComboBox.setEnabled(false);
    }

    private LocalDate getSelectedDate() {
        Date selectedDate = (Date) dobSpinner.getValue();
        return selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private void addNumberValidationListener(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                    JOptionPane.showMessageDialog(AddCustomerPanel.this, "Ce champ n'accepte que des chiffres.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void addStringValidationListener(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume();
                    JOptionPane.showMessageDialog(AddCustomerPanel.this, "Ce champ n'accepte que des lettres.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
