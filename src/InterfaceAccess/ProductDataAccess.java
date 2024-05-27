package InterfaceAccess;

import Exception.ProductException;
import Model.Product;
import Model.SaleDetail;

import java.util.ArrayList;

public interface ProductDataAccess {


    void applyDiscount(Product product, int discount) throws ProductException;

    ArrayList<Product> getProductInPromotion() throws ProductException;

    ArrayList<Product> getProductsWhithoutPromotion() throws ProductException;

    Product getProductByCode(int productCode) throws ProductException;


    void removePromotion(int productId) throws ProductException;
}
