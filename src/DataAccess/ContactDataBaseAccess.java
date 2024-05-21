package DataAccess;

import InterfaceAccess.ContactDataAccess;
import Model.Contact;
import Exception.*;

import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDataBaseAccess implements ContactDataAccess {
    @Override
    public void createContact(Contact contact) throws ContactException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "INSERT INTO contact VALUES (?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, contact.getMail());

            String phoneNumber = contact.getPhoneNumber();
            if (phoneNumber != null) {
                statement.setString(2, phoneNumber);
            }else {
                statement.setNull(2, Types.VARCHAR);
            }

            statement.executeUpdate();
        } catch (Exception exception) {
            throw new ContactException(exception.getMessage(), new OneException(), new CreateException());
        }
    }

    @Override
    public Contact getContactByMail(String mail) throws ContactException {
        // code to get a contact by mail
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM contact WHERE mail = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, mail);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String phoneNumber = resultSet.getString("phoneNumber");
                return new Contact(mail, phoneNumber);
            }
        } catch (Exception exception) {
            throw new ContactException(exception.getMessage(), new OneException(), new ReadException());
        }
        return null;
    }

    @Override
    public void updateContact(Contact contact) throws ContactException {
        // code to update a contact
        try {
            if (getContactByMail(contact.getMail()) == null) {
                throw new ContactException("Contact not found", new OneException(), new UpdateException());
            }else {
                //Pas sur ici, enfin de mon string query quoi
                Connection connection = SingletonConnexion.getInstance();
                String query = "UPDATE contact SET phoneNumber = ? WHERE mail = ?;";
                PreparedStatement statement = connection.prepareStatement(query);

                String phoneNumber = contact.getPhoneNumber();
                statement.setString(1, contact.getMail());
                if (phoneNumber != null) {
                    statement.setString(2, phoneNumber);
                } else {
                    statement.setNull(2, Types.NULL);
                }
                statement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new ContactException(exception.getMessage(), new OneException(), new UpdateException());
        }
    }

    @Override
    public void deleteContact(int contactId) throws ContactException {
        // code to delete a contact
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "DELETE FROM contact WHERE mail = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, contactId);
            statement.executeUpdate();
        } catch (Exception exception) {
            throw new ContactException(exception.getMessage(), new OneException(), new DeleteException());
        }
    }

    @Override
    public ArrayList<Contact> readAllContact() throws ContactException {
        // code to read all contacts
        try {

            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM contact;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Contact> contacts = new ArrayList<Contact>();
            while (resultSet.next()) {
                String mail = resultSet.getString("mail");
                Contact contact = new Contact(mail);
                String phoneNumber = resultSet.getString("phoneNumber");
                if (phoneNumber != null) {
                    contact.setPhoneNumber(phoneNumber);
                }
                contacts.add(contact);
            }
            return contacts;
        } catch (Exception exception) {
            throw new ContactException(exception.getMessage(), new AllException(), new ReadException());
        }
    }

    @Override
    public ArrayList<Contact> searchContacts(String keyword) throws ContactException {
        // code to search contacts
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM contact WHERE mail LIKE ? OR phoneNumber LIKE ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Contact> contacts = new ArrayList<Contact>();
            while (resultSet.next()) {
                String mail = resultSet.getString("mail");
                Contact contact = new Contact(mail);
                String phoneNumber = resultSet.getString("phoneNumber");
                if (phoneNumber != null) {
                    contact.setPhoneNumber(phoneNumber);
                }
                contacts.add(contact);
            }
            return contacts;
        } catch (Exception exception) {
            throw new ContactException(exception.getMessage(), new AllException(), new ReadException());
        }
    }
}
