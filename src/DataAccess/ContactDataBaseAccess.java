package DataAccess;

import InterfaceAccess.ContactDataAccess;
import Model.Contact;
import Exception.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ContactDataBaseAccess implements ContactDataAccess {
    public void createContact(Contact contact) throws ContactException {
        // code to create a contact
    }

    @Override
    public Contact getContactByMail(String mail) throws ContactException {
        return null;
    }

    @Override
    public void updateContact(Contact contact) throws ContactException {

    }

    @Override
    public void deleteContact(int contactId) throws ContactException {

    }

    @Override
    public ArrayList<Contact> readAllContact() throws ContactException {
        return null;
    }

    @Override
    public ArrayList<Contact> searchContacts(String keyword) throws ContactException {
        return null;
    }
}
