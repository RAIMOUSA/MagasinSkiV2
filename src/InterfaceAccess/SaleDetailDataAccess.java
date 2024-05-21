package InterfaceAccess;

import Model.Product;
import Model.SaleDetail;

import java.util.ArrayList;

public interface SaleDetailDataAccess {
    SaleDetail getSaleDetailByProduct(Product product);

    ArrayList<SaleDetail> readAllSaleDetails();
}
