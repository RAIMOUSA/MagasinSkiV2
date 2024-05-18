package InterfaceAccess;

import Model.Contact;
import Exception.*;

import java.util.ArrayList;
import java.util.List;

public interface ContactDataAccess {
    void createContact(Contact contact) throws ContactException;

    Contact getContactByMail(String mail) throws ContactException;

    void updateContact(Contact contact) throws ContactException;
    void deleteContact(int contactId) throws ContactException;
    ArrayList<Contact> readAllContact() throws ContactException;
    ArrayList<Contact> searchContacts(String keyword) throws ContactException;
}
