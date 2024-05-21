package Business;

import InterfaceAccess.SaleDetailDataAccess;
import Model.Product;
import Model.SaleDetail;
import Exception.*;
import java.util.ArrayList;

public class SaleDetailManager {
    private SaleDetailDataAccess saleDetailAccess;
    public SaleDetail getSaleDetailByProduct(Product product) throws SaleDetailException {
        return saleDetailAccess.getSaleDetailByProduct(product);
    }

    public ArrayList<SaleDetail> readAllSaleDetails() throws SaleDetailException {
        return saleDetailAccess.readAllSaleDetails();
    }
}
