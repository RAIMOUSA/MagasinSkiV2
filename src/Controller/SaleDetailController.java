package Controller;

import Business.SaleDetailManager;
import Model.Product;
import Model.SaleDetail;

import java.util.ArrayList;
import java.util.List;

public class SaleDetailController {
    private SaleDetailManager saleDetailManager;
    public SaleDetail getSaleDetailByProduct(Product product) {
        return saleDetailManager.getSaleDetailByProduct(product);
    }

    public ArrayList<SaleDetail> readAllSaleDetails() {
        return saleDetailManager.readAllSaleDetails();
    }

}
