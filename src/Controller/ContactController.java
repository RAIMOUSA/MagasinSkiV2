package Controller;

import Business.ContactManager;
import Exception.*;
import Model.Contact;

import java.util.ArrayList;

public class ContactController {
    private ContactManager manager;

    public ContactController() {
        this.setManager(new ContactManager());
    }

    public void setManager(ContactManager manager) {
        this.manager = manager;
    }

    public Contact getContactByMail(String mail) throws ContactException {
        return this.manager.getContactByMail(mail);
    }

    public ArrayList<Contact> readAllContact() throws ContactException {
        return this.manager.readAllContact();
    }

    public void updateContactPhone(String oldMail, Contact contact) throws ContactException {
        this.manager.updateContactPhone(oldMail, contact);
    }
    public void updateContactMail(String oldMail, Contact contact) throws ContactException {
        this.manager.updateContactMail(oldMail, contact);
    }

    public void deleteContact(int codeContact) throws ContactException {
        this.manager.deleteContact(codeContact);
    }

    public void createContact(Contact contact) throws ContactException {
        this.manager.createContact(contact);
    }
}
