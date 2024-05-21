package Business;

import InterfaceAccess.SaleDataAccess;
import Model.Sale;
import Model.SaleDetail;
import Exception.*;
import java.util.ArrayList;

public class SaleManager {
    private SaleDataAccess saleAccess;

    public ArrayList<Sale> readAllSales() throws SaleException {
        return this.saleAccess.readAllSales();
    }

    public Sale getSaleBySaleDetail(SaleDetail saleDetail) throws SaleException{
        return this.saleAccess.getSaleBySaleDetail(saleDetail);
    }
}
