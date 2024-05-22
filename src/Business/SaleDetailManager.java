package Business;

import DataAccess.SaleDetailDataBaseAccess;
import InterfaceAccess.SaleDetailDataAccess;
import Model.Product;
import Model.SaleDetail;
import Exception.*;
import java.util.ArrayList;

public class SaleDetailManager {
    private SaleDetailDataAccess saleDetailAccess;

    public SaleDetailManager() {
        this.setSaleDetailAccess(new SaleDetailDataBaseAccess());
    }

    public void setSaleDetailAccess(SaleDetailDataAccess saleDetailAccess) {
        this.saleDetailAccess = saleDetailAccess;
    }

    public SaleDetail getSaleDetailByProduct(Product product) throws SaleDetailException {
        return saleDetailAccess.getSaleDetailByProduct(product);
    }

    public ArrayList<SaleDetail> readAllSaleDetails() throws SaleDetailException {
        return saleDetailAccess.readAllSaleDetails();
    }
}
