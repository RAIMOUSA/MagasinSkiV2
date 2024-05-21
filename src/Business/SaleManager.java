package Business;

import InterfaceAccess.SaleDataAccess;
import Model.Sale;
import Model.SaleDetail;

import java.util.ArrayList;

public class SaleManager {
    private SaleDataAccess saleAccess;

    public ArrayList<Sale> readAllSales() {
        return this.saleAccess.readAllSales();
    }

    public Sale getSaleBySaleDetail(SaleDetail saleDetail) {
        return this.saleAccess.getSaleBySaleDetail(saleDetail);
    }
}
