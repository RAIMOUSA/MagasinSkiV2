package Controller;

import Business.SaleDetailManager;
import Model.Product;
import Model.SaleDetail;
import Exception.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDetailController {
    private SaleDetailManager saleDetailManager;

    public SaleDetailController() {
        setSaleDetailManager(new SaleDetailManager());
    }

    public void setSaleDetailManager(SaleDetailManager saleDetailManager) {
        this.saleDetailManager = saleDetailManager;
    }

    public SaleDetail getSaleDetailByProduct(Product product) throws SaleDetailException {
        return saleDetailManager.getSaleDetailByProduct(product);
    }

    public ArrayList<SaleDetail> readAllSaleDetails() throws SaleDetailException{
        return saleDetailManager.readAllSaleDetails();
    }

}
