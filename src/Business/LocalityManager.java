package Business;

import DataAccess.LocalityDataBaseAccess;
import InterfaceAccess.LocalityDataAccess;
import Model.Locality;
import Exception.*;

import java.util.ArrayList;

public class LocalityManager {
    private LocalityDataAccess dataAccess;

    public LocalityManager() {
        this.setDataAccess(new LocalityDataBaseAccess());
    }

    public void setDataAccess(LocalityDataBaseAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public void updateLocality(Locality locality) throws LocalityException {
        this.dataAccess.updateLocality(locality);
    }

    public void createLocality(Locality locality) throws LocalityException {
        this.dataAccess.createLocality(locality);
    }

    public int getLocalityID(Locality locality) throws LocalityException {
        return this.dataAccess.getLocalityID(locality);
    }

    public Locality getLocalityByCode(int code) throws LocalityException {
        return this.dataAccess.getLocalityByCode(code);
    }
}
