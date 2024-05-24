package Controller;

import Business.LocalityManager;
import Model.Locality;
import Exception.*;

import java.util.ArrayList;

public class LocalityController {
    private LocalityManager manager;

    public LocalityController() {
        this.setManager(new LocalityManager());
    }

    public void setManager(LocalityManager manager) {
        this.manager = manager;
    }

    public Locality getLocalityByCode(int code) throws LocalityException {
        return this.manager.getLocalityByCode(code);
    }

    public void updateLocality(Locality locality) throws LocalityException {
        this.manager.updateLocality(locality);
    }

    public void createLocality(Locality locality) throws LocalityException {
        this.manager.createLocality(locality);
    }

    public int getLocalityID(Locality locality) throws LocalityException {
        return this.manager.getLocalityID(locality);
    }
}
