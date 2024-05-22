package InterfaceAccess;

import Model.Product;
import Model.Sale;
import Model.SaleDetail;
import Exception.*;
import java.util.ArrayList;
import java.util.List;

public interface SaleDetailDataAccess {
    SaleDetail getSaleDetailByProduct(Product product) throws SaleDetailException;

    ArrayList<SaleDetail> readAllSaleDetails() throws SaleDetailException;

    ArrayList<SaleDetail> getSaleDetailsBySale(Sale sale) throws SaleDetailException;
}
