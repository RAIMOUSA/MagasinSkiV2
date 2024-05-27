package Test;

import Controller.ProductController;
import Model.Product;
import UserInterface.ListingProductsModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestListingProductsModel {
    private ProductController productController;
    private ListingProductsModel listingProductsModel;
    private ArrayList<Product> testProducts;

    @BeforeEach
    public void setUp() throws Exception {
        productController = new ProductController();
        testProducts = new ArrayList<>();
        testProducts.add(new Product(1234,"Type1", "Test Product 1", 100, 8, true));
        testProducts.add(new Product(1235,"Type2", "Test Product 2", 200, 5, true));

        listingProductsModel = new ListingProductsModel();
        listingProductsModel.getProducts().addAll(testProducts);
        listingProductsModel.getProducts().addAll(testProducts);
    }

    @Test
    public void testGetRowCount() {
        assertEquals(testProducts.size() * 2, listingProductsModel.getRowCount());
    }

    @Test
    public void testGetValueAt() {
        assertEquals("Ski alpin", listingProductsModel.getValueAt(0, 0));
        assertEquals("XTREM 500", listingProductsModel.getValueAt(0, 1));
        assertEquals(499.99, listingProductsModel.getValueAt(0, 2));
        assertEquals(3, listingProductsModel.getValueAt(0, 3));
        assertEquals(15, listingProductsModel.getValueAt(0, 4));
    }

    @Test
    public void testFilterProductsByName() {
        listingProductsModel.filterProductsByName("Test Product 1");
        assertEquals(2, listingProductsModel.getRowCount());
    }

    @Test
    public void testRefreshData() throws Exception {
        listingProductsModel.refreshData();
        assertEquals(4, listingProductsModel.getRowCount());
    }
}
