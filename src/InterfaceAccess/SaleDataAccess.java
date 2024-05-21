package InterfaceAccess;

import Model.Sale;
import Model.SaleDetail;

import java.util.ArrayList;

public interface SaleDataAccess {

    ArrayList<Sale> readAllSales();

    Sale getSaleBySaleDetail(SaleDetail saleDetail);
}
