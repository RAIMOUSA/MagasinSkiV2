package Model;

public class Locality {
    private int localityID;
    private String localityName;
    private int postalCode;
    private String street;
    private int houseNumber;
    private String letterBox;

    public Locality(int localityID, String localityName, int postalCode, String street, int houseNumber, String letterBox) {
        this.localityID = localityID;
        this.localityName = localityName;
        this.postalCode = postalCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.letterBox = letterBox;
    }

    public Locality(int localityID, String localityName, int postalCode, String street, int houseNumber) {
        this.localityID = localityID;
        this.localityName = localityName;
        this.postalCode = postalCode;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public Locality(String localityName, int postalCode, String street, int houseNumber, String letterBox) {
        this.localityName = localityName;
        this.postalCode = postalCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.letterBox = letterBox;
    }

    public int getLocalityID() {
        return localityID;
    }

    public void setLocalityID(int localityID) {
        this.localityID = localityID;
    }

    public String getLocalityName() {
        return localityName;
    }


    public int getPostalCode() {
        return postalCode;
    }


    public String getStreet() {
        return street;
    }


    public int getHouseNumber() {
        return houseNumber;
    }

    public String getLetterBox() {
        return letterBox;
    }

    public void setLetterBox(String letterBox) {
        this.letterBox = letterBox;
    }
}
