package Controller;

import Business.SaleManager;
import Model.Sale;
import Model.SaleDetail;
import Exception.*;
import java.util.ArrayList;

public class SaleController {
    private SaleManager manager;

    public SaleController() {
        setManager(new SaleManager());
    }

    public void setManager(SaleManager manager) {
        this.manager = manager;
    }

    public ArrayList<Sale> readAllSales() throws SaleException{
        return this.manager.readAllSales();
    }

    public Sale getSaleBySaleDetail(SaleDetail saleDetail) throws SaleException {
        return this.manager.getSaleBySaleDetail(saleDetail);
    }
}
