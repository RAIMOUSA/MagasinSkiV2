package InterfaceAccess;

import Exception.ProductException;
import Model.Product;
import Model.SaleDetail;

import java.util.ArrayList;

public interface ProductDataAccess {

    ArrayList<Product> readAllProducts() throws Exception;

    void applyDiscount(Product product, double discount) throws ProductException;

    Product getProductInPromotion();

    Product getProductByCode(int productCode);

    Product getProductBySaleDetail(SaleDetail saleDetail);
}
