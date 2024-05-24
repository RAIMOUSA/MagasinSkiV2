package InterfaceAccess;

import Model.Product;
import Model.Sale;
import Model.SaleDetail;
import Exception.*;
import java.util.ArrayList;
import java.util.List;

public interface SaleDetailDataAccess {

    ArrayList<SaleDetail> readAllSaleDetails() throws SaleDetailException;

    ArrayList<SaleDetail> getSaleDetailsBySale(Sale sale) throws SaleDetailException;
}
