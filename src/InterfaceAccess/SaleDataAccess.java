package InterfaceAccess;

import Model.Sale;
import Model.SaleDetail;
import Exception.*;
import java.util.ArrayList;

public interface SaleDataAccess {

    ArrayList<Sale> readAllSales() throws SaleException;

    Sale getSaleBySaleDetail(SaleDetail saleDetail) throws SaleException;
}
