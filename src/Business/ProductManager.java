package Business;

import DataAccess.ProductDataBaseAccess;
import InterfaceAccess.ProductDataAccess;
import java.util.ArrayList;
import Model.Product;

public class ProductManager {
    private ProductDataAccess productAccess;

    public ProductManager() {
        setProductAccess(new ProductDataBaseAccess());
    }

    public void setProductAccess(ProductDataAccess productAccess) {
        this.productAccess = productAccess;
    }

    public ArrayList<Product> readAllProducts() throws Exception {
        return productAccess.readAllProducts();
    }
}
