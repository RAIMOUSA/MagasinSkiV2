package Model;

public class SaleDetail {
    private int quantity;
    private int productCode;
    private int saleCode;

    public SaleDetail(int saleCode, int productCode, int quantity ) {
        this.quantity = quantity;
        this.productCode = productCode;
        this.saleCode = saleCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getProductCode() {
        return productCode;
    }

    public int getSaleCode() {
        return saleCode;
    }


}
