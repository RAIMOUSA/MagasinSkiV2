package Business;

import DataAccess.SaleDataBaseAccess;
import InterfaceAccess.SaleDataAccess;
import Model.Sale;
import Model.SaleDetail;
import Exception.*;
import java.util.ArrayList;

public class SaleManager {
    private SaleDataAccess saleAccess;

    public SaleManager() {
        this.setSaleAccess(new SaleDataBaseAccess());
    }

    public void setSaleAccess(SaleDataAccess saleAccess) {
        this.saleAccess = saleAccess;
    }

    public ArrayList<Sale> readAllSales() throws SaleException {
        return this.saleAccess.readAllSales();
    }

    public Sale getSaleBySaleDetail(SaleDetail saleDetail) throws SaleException{
        return this.saleAccess.getSaleBySaleDetail(saleDetail);
    }
}
