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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int getSaleCode() {
        return saleCode;
    }

    public void setSaleCode(int saleCode) {
        this.saleCode = saleCode;
    }
}
