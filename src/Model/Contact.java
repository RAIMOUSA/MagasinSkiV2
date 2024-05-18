package src.Model;

public class Contact {
    private String mail;
    private String phoneNumber;


    public Contact(String mail, String phoneNumber) {
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }
    public Contact(String mail) {
        this.mail = mail;

    }
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
