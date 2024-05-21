package InterfaceAccess;

import Model.Product;
import Model.SaleDetail;
import Exception.*;
import java.util.ArrayList;

public interface SaleDetailDataAccess {
    SaleDetail getSaleDetailByProduct(Product product) throws SaleDetailException;

    ArrayList<SaleDetail> readAllSaleDetails() throws SaleDetailException;
}
