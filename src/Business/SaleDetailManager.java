package Business;

import InterfaceAccess.SaleDetailDataAccess;
import Model.Product;
import Model.SaleDetail;

import java.util.ArrayList;

public class SaleDetailManager {
    private SaleDetailDataAccess saleDetailAccess;
    public SaleDetail getSaleDetailByProduct(Product product) {
        return saleDetailAccess.getSaleDetailByProduct(product);
    }

    public ArrayList<SaleDetail> readAllSaleDetails() {
        return saleDetailAccess.readAllSaleDetails();
    }
}
