package InterfaceAccess;

import Model.Contact;
import Exception.*;

import java.util.ArrayList;
import java.util.List;

public interface ContactDataAccess {
    void createContact(Contact contact) throws ContactException;

    Contact getContactByMail(String mail) throws ContactException;

    void updateContactPhone(String oldMail, Contact contact) throws ContactException;
    void updateContactMail(String oldMail, Contact contact) throws ContactException;
    void deleteContact(int contactId) throws ContactException;
    boolean emailExists(String email);

    boolean phoneExists(String phoneNumber);
}
