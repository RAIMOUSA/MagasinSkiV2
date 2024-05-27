package Controller;

import Business.SaleDetailManager;
import Model.Product;
import Model.Sale;
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

    public ArrayList<SaleDetail> readAllSaleDetails() throws SaleDetailException{
        return saleDetailManager.readAllSaleDetails();
    }

    public ArrayList<SaleDetail> getSaleDetailsBySale(Sale sale) throws SaleDetailException {
        return saleDetailManager.getSaleDetailsBySale(sale);
    }
}
