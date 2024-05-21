package DataAccess;

import Exception.ProductException;
import InterfaceAccess.ProductDataAccess;
import Model.Product;
import Model.SaleDetail;

import java.util.ArrayList;

public class ProductDataBaseAccess implements ProductDataAccess {

    @Override
    public ArrayList<Product> readAllProducts() throws Exception {
        return null;
    }

    @Override
    public Product getProductInPromotion() {
        return null;
    }

    @Override
    public void applyDiscount(Product product, double discount) throws ProductException {
        // fait esclave Félix
    }

    @Override
    public Product getProductByCode(int productCode) {
        return null;
        // fait esclave Félix
    }

    @Override
    public Product getProductBySaleDetail(SaleDetail saleDetail) {
        return null;
        // fait esclave Félix
    }

}
