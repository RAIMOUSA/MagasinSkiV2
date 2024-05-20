package Model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Customer {
    private int userID;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private boolean isProfessional;
    private String gender;
    private int localityID;
    private String mail;

    public Customer(int userID, String firstName, String lastName, LocalDate dateOfBirth, boolean isProfessional, String gender, int localityID, String mail) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.isProfessional = isProfessional;
        this.gender = gender;
        this.localityID = localityID;
        this.mail = mail;
    }

    public Customer(int userID, String firstName, String lastName, boolean isProfessional, int localityID, String mail) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isProfessional = isProfessional;
        this.localityID = localityID;
        this.mail = mail;
    }

    public Customer(int userID, String firstName, String lastName, LocalDate dateOfBirth, boolean isProfessional, int localityID, String mail) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.isProfessional = isProfessional;
        this.localityID = localityID;
        this.mail = mail;
    }

    public Customer(int userID, String firstName, String lastName, boolean isProfessional, String gender, int localityID, String mail) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isProfessional = isProfessional;
        this.gender = gender;
        this.localityID = localityID;
        this.mail = mail;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isProfessional() {
        return isProfessional;
    }

    public void setProfessional(boolean professional) {
        isProfessional = professional;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getLocalityID() {
        return localityID;
    }

    public void setLocalityID(int localityID) {
        this.localityID = localityID;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}