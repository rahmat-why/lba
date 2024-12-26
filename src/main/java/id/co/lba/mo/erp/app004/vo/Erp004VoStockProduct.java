package id.co.lba.mo.erp.app004.vo;

public class Erp004VoStockProduct {
    private String stockProductId;
    private String warehouseId;
    private String warehouseName;
    private int quantity;
    private int totalPayment;
    private String paymentDate;
    private String paymentStatus;

    // Getter dan Setter
    public String getStockProductId() {
        return stockProductId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
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
}