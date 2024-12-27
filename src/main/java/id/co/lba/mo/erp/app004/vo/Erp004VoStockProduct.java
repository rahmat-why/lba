package id.co.lba.mo.erp.app004.vo;

public class Erp004VoStockProduct {
    private String stockProductId;
    private String productId;
    private String productName;
    private String warehouseId;
    private String warehouseName;
    private String quantity;
    private String totalPayment;
    private String paymentDate;
    private String paymentStatus;
    private String price;

    public String getStockProductId() {
        return stockProductId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setStockProductId(String stockProductId) {
        this.stockProductId = stockProductId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}