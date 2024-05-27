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
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
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
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM contact WHERE mail = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, mail);
            ResultSet resultSet = statement.executeQuery();

            String phoneNumber = null;
            if (resultSet.next()) {
                phoneNumber = resultSet.getString("phoneNumber");
            }
            return new Contact(mail, phoneNumber);
        } catch (Exception exception) {
            throw new ContactException(exception.getMessage(), new OneException(), new ReadException());
        }
    }

    @Override
    public void updateContactPhone(String oldMail, Contact contact) throws ContactException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "UPDATE contact SET phoneNumber = ? WHERE mail = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            String phoneNumber = contact.getPhoneNumber();
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                statement.setString(1, phoneNumber);
            } else {
                statement.setNull(1, Types.VARCHAR);
            }

            statement.setString(2, oldMail);
            statement.executeUpdate();
        } catch (Exception exception) {
            throw new ContactException(exception.getMessage(), new OneException(), new UpdateException());
        }
    }


    @Override
    public void updateContactMail(String oldMail, Contact contact) throws ContactException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "UPDATE contact SET mail = ? WHERE mail = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, contact.getMail());
            statement.setString(2, oldMail);
            statement.executeUpdate();
        } catch (Exception exception) {
            throw new ContactException(exception.getMessage(), new OneException(), new UpdateException());
        }
    }

    @Override
    public void deleteContact(int contactId) throws ContactException {
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
    public boolean emailExists(String email) {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM contact WHERE mail = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public boolean phoneExists(String phoneNumber) {
       try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM contact WHERE phoneNumber = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, phoneNumber);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (Exception exception) {
            return false;
       }
    }
}
