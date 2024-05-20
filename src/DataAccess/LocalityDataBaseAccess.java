package DataAccess;

import Model.Locality;
import Exception.*;
import java.sql.*;
import java.util.ArrayList;

public class LocalityDataBaseAccess {
    //J'ai pas fait le constructeur, il faut pas locality en argument ? ou juste on appelle createLocality
    public LocalityDataBaseAccess() {

    }

    public ArrayList<Locality> readAllLocality() throws LocalityException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM locality;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Locality> localities = new ArrayList<Locality>();

            while (resultSet.next()) {
                int localityID = resultSet.getInt("localityID");
                String localityName = resultSet.getString("localityName");
                int postalCode = resultSet.getInt("postalCode");
                String street = resultSet.getString("street");
                int houseNumber = resultSet.getInt("houseNumber");
                Locality locality = new Locality(localityID, localityName, postalCode, street, houseNumber);

                String letterBox = resultSet.getString("letterBox");
                if(letterBox != null) {
                    locality.setLetterBox(letterBox);
                }
                localities.add(locality);
            }
            return localities;
        } catch (Exception exception) {
            throw new LocalityException(exception.getMessage(), new AllException(), new ReadException());
        }
    }

    public void updateLocality(Locality locality) throws LocalityException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "UPDATE locality SET localityName = ?, postalCode = ?, street = ?, houseNumber = ?, letterBox = ? WHERE localityID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, locality.getLocalityName());
            statement.setInt(2, locality.getPostalCode());
            statement.setString(3, locality.getStreet());
            statement.setInt(4, locality.getHouseNumber());

            if (locality.getLetterBox() != null) {
                statement.setString(5, locality.getLetterBox());
            } else {
                statement.setNull(5, Types.NULL);
            }

            statement.setInt(6, locality.getLocalityID());
            statement.executeUpdate();
        } catch (Exception exception) {
            throw new LocalityException(exception.getMessage(), new OneException(),new UpdateException());
        }
    }

    public void deleteLocality(int codeLocality) throws LocalityException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "DELETE FROM locality WHERE localityID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, codeLocality);
            statement.executeUpdate();

        } catch (Exception exception) {
            throw new LocalityException(exception.getMessage(), new OneException(), new DeleteException());
        }

    }

    public void createLocality(Locality locality) throws LocalityException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "INSERT INTO locality VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, locality.getLocalityID());
            statement.setString(2, locality.getLocalityName());
            statement.setInt(3, locality.getPostalCode());
            statement.setString(4, locality.getStreet());
            statement.setInt(5, locality.getHouseNumber());

            if (locality.getLetterBox() != null) {
                statement.setString(6, locality.getLetterBox());
            } else {
                statement.setNull(6, Types.NULL);
            }

            statement.executeUpdate();
        } catch (Exception exception) {
            throw new LocalityException(exception.getMessage(), new OneException(), new CreateException());
        }

    }

    public Locality getLocalityByCode(int code) throws LocalityException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM locality WHERE localityID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, code);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int localityID = resultSet.getInt("localityID");
            String localityName = resultSet.getString("localityName");
            int postalCode = resultSet.getInt("postalCode");
            String street = resultSet.getString("street");
            int houseNumber = resultSet.getInt("houseNumber");
            Locality locality = new Locality(localityID, localityName, postalCode, street, houseNumber);

            String letterBox = resultSet.getString("letterBox");
            if(letterBox != null) {
                locality.setLetterBox(letterBox);
            }
            return locality;
        }catch (Exception exception) {
            throw new LocalityException(exception.getMessage(), new OneException(), new ReadException());
        }
    }
}
