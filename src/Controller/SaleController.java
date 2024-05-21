package Controller;

import Business.SaleManager;
import Model.Sale;
import Model.SaleDetail;

import java.util.ArrayList;

public class SaleController {
    private SaleManager manager;

    public SaleController() {
        setManager(new SaleManager());
    }

    public void setManager(SaleManager manager) {
        this.manager = manager;
    }

    public ArrayList<Sale> readAllSales() {
        return this.manager.readAllSales();
    }

    public Sale getSaleBySaleDetail(SaleDetail saleDetail) {
        return this.manager.getSaleBySaleDetail(saleDetail);
    }
}
