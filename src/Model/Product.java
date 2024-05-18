package Model;

import java.util.List;

public class Product {
    private int code;
    private String type;
    private String name;
    private double price;
    private int stockQuantity;
    private Boolean promoIsEnable;
    private int percentPromo;

    public Product(int code, String type, String name, double price, int stockQuantity, Boolean promoIsEnable, int percentPromo) {
        this.code = code;
        this.type = type;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.promoIsEnable = promoIsEnable;
        this.percentPromo = percentPromo;
    }

    public Product(int code, String type, String name, double price, int stockQuantity, Boolean promoIsEnable) {
        this.code = code;
        this.type = type;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.promoIsEnable = promoIsEnable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Boolean getPromoIsEnable() {
        return promoIsEnable;
    }

    public void setPromoIsEnable(Boolean promoIsEnable) {
        this.promoIsEnable = promoIsEnable;
    }

    public int getPercentPromo() {
        return percentPromo;
    }

    public void setPercentPromo(int percentPromo) {
        this.percentPromo = percentPromo;
    }
}
