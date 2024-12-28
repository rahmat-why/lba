package id.co.lba.mo.erp.app004.vo;

public class Erp004VoTransferStock {
    private String transferStockId;
    private String productId;
    private String productName;
    private String warehouseFromId;
    private String warehouseFromName;
    private String warehouseToId;
    private String warehouseToName;
    private String quantity;
    private String description;
    private String totalCost;
    private String transferDate;

    public String getTransferStockId() {
        return transferStockId;
    }

    public void setTransferStockId(String transferStockId) {
        this.transferStockId = transferStockId;
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

    public String getWarehouseFromId() {
        return warehouseFromId;
    }

    public void setWarehouseFromId(String warehouseFromId) {
        this.warehouseFromId = warehouseFromId;
    }

    public String getWarehouseFromName() {
        return warehouseFromName;
    }

    public void setWarehouseFromName(String warehouseFromName) {
        this.warehouseFromName = warehouseFromName;
    }

    public String getWarehouseToId() {
        return warehouseToId;
    }

    public void setWarehouseToId(String warehouseToId) {
        this.warehouseToId = warehouseToId;
    }

    public String getWarehouseToName() {
        return warehouseToName;
    }

    public void setWarehouseToName(String warehouseToName) {
        this.warehouseToName = warehouseToName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }
}
