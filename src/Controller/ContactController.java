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

    public void updateContactPhone(String oldMail, Contact contact) throws ContactException {
        this.manager.updateContactPhone(oldMail, contact);
    }
    public void updateContactMail(String oldMail, Contact contact) throws ContactException {
        this.manager.updateContactMail(oldMail, contact);
    }


    public void createContact(Contact contact) throws ContactException {
        this.manager.createContact(contact);
    }

    public boolean emailExists(String email) {
        return this.manager.emailExists(email);
    }

    public boolean phoneExists(String phoneNumber) {
        return this.manager.phoneExists(phoneNumber);
    }
}
