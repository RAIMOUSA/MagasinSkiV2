package InterfaceAccess;

import Exception.*;
import Model.Locality;

import java.util.ArrayList;

public interface LocalityDataAccess {
    void createLocality(Locality locality) throws LocalityException;
    Locality getLocalityByCode(int code) throws LocalityException;
    int getLocalityID(Locality locality) throws LocalityException;
    void updateLocality(Locality locality) throws LocalityException;
    void deleteLocality(int codeLocality) throws LocalityException;
    ArrayList<Locality> readAllLocality() throws LocalityException;
}
