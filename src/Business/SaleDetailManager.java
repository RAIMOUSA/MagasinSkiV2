package Business;

import DataAccess.SaleDetailDataBaseAccess;
import InterfaceAccess.SaleDetailDataAccess;
import Model.Product;
import Model.Sale;
import Model.SaleDetail;
import Exception.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDetailManager {
    private SaleDetailDataAccess saleDetailAccess;

    public SaleDetailManager() {
        this.setSaleDetailAccess(new SaleDetailDataBaseAccess());
    }

    public void setSaleDetailAccess(SaleDetailDataAccess saleDetailAccess) {
        this.saleDetailAccess = saleDetailAccess;
    }


    public ArrayList<SaleDetail> readAllSaleDetails() throws SaleDetailException {
        return saleDetailAccess.readAllSaleDetails();
    }

    public ArrayList<SaleDetail> getSaleDetailsBySale(Sale sale) throws SaleDetailException {
        return saleDetailAccess.getSaleDetailsBySale(sale);
    }
}
