package Business;

import DataAccess.ContactDataBaseAccess;
import InterfaceAccess.ContactDataAccess;
import Model.Contact;
import Exception.*;
import java.util.ArrayList;

public class ContactManager {
    private ContactDataAccess dataAccess;

    public ContactManager() {
        this.setDataAccess(new ContactDataBaseAccess());
    }

    public void setDataAccess(ContactDataBaseAccess dataAccess) {
        this.dataAccess = dataAccess;
    }


    public ArrayList<Contact> readAllContact() throws ContactException {
        return this.dataAccess.readAllContact();
    }

    public void updateContactPhone(String oldMail, Contact contact) throws ContactException {
        this.dataAccess.updateContactPhone(oldMail, contact);
    }
    
    public void updateContactMail(String oldMail, Contact contact) throws ContactException {
        this.dataAccess.updateContactMail(oldMail, contact);
    }

    public void deleteContact(int codeContact) throws ContactException {
        this.dataAccess.deleteContact(codeContact);
    }

    public void createContact(Contact contact) throws ContactException {
        this.dataAccess.createContact(contact);
    }

    public Contact getContactByMail(String mail) throws ContactException {
        return this.dataAccess.getContactByMail(mail);
    }

}
