package id.co.lba.mo.erp.app004.dao;

import LBAJXLibrariesV1.connection.DBConnect;
import LBAJXLibrariesV1.dto.DtoParameter;
import id.co.lba.mo.erp.app004.constant.Erp004Constant;
import id.co.lba.mo.erp.app004.vo.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Erp004ObjectDao {
    static DBConnect connection = new DBConnect();

    public static List<Erp004VoRestock> getRestock(DtoParameter dto) throws SQLException {
        List<Erp004VoRestock> restockList = new ArrayList<>();
        String query = Erp004Constant.GET_DATA_RESTOCK;
        query = query.replace("@VSEARCH", dto.getSearch() != null ? dto.getSearch().get("VSEARCH").toString() : "");
        System.out.println(query);
        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                while (resultSet.next()) {
                    Erp004VoRestock restock = new Erp004VoRestock();
                    restock.setRestockId(resultSet.getString("VRSTCKID"));
                    restock.setWarehouseId(resultSet.getString("VWRHSID"));
                    restock.setWarehouseName(resultSet.getString("VWRHSNAME"));
                    restock.setProductName(resultSet.getString("VPRDNAME"));
                    restock.setQuantity(resultSet.getString("NQTY"));
                    restock.setTotalPayment(resultSet.getString("NTPYMNT"));
                    restock.setPaymentDate(resultSet.getString("DPYMNTDT"));
                    restock.setPaymentStatus(resultSet.getString("VPYMNTST"));
                    restock.setPrice(resultSet.getString("NPRICE"));
                    restock.setRestockDate(resultSet.getString("DRSTCKDT"));
                    restockList.add(restock);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return restockList;
    }

    public static List<Erp004VoProduct> getProduct(DtoParameter dto) throws SQLException {
        List<Erp004VoProduct> productList = new ArrayList<>();
        String query = Erp004Constant.GET_COMBOBOX_PRODUCTS;

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                while (resultSet.next()) {
                    Erp004VoProduct product = new Erp004VoProduct();
                    product.setProductId(resultSet.getString("VPRDID"));
                    product.setName(resultSet.getString("VNAME"));
                    product.setPriceBuy(resultSet.getString("NPRBUY"));
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return productList;
    }

    public static List<Erp004VoWarehouse> getWarehouse(DtoParameter dto) throws SQLException {
        List<Erp004VoWarehouse> warehouseList = new ArrayList<>();
        String query = Erp004Constant.GET_COMBOBOX_WAREHOUSES;

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                while (resultSet.next()) {
                    Erp004VoWarehouse warehouse = new Erp004VoWarehouse();
                    warehouse.setWarehouseId(resultSet.getString("VWRHSID"));
                    warehouse.setName(resultSet.getString("VNAME"));
                    warehouseList.add(warehouse);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return warehouseList;
    }

    public static String getLastRestockId(DtoParameter dto) throws SQLException {
        String query = Erp004Constant.GET_LAST_STOCK_PRODUCT_ID;
        String warehouseId = dto.getSearch() != null ? dto.getSearch().get("WAREHOUSE").toString() : "";
        query = query.replace("@WAREHOUSE", warehouseId);

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                String lastId = null;
                while (resultSet.next()) {
                    lastId = resultSet.getString("VRSTCKID");
                }

                if (lastId == null) {
                    String year = String.valueOf(java.time.LocalDate.now().getYear());
                    String month = String.format("%02d", java.time.LocalDate.now().getMonthValue());
                    return "STC/" + warehouseId + "/" + year + "/" + month + "/000001";
                } else {
                    String lastOrder = lastId.substring(lastId.length() - 6);
                    int nextNumberingOrder = Integer.parseInt(lastOrder) + 1;
                    String numberingOrder = String.format("%06d", nextNumberingOrder);

                    String year = String.valueOf(java.time.LocalDate.now().getYear());
                    String month = String.format("%02d", java.time.LocalDate.now().getMonthValue());
                    return "STC/" + warehouseId + "/" + year + "/" + month + "/" + numberingOrder;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<Erp004VoTransferStock> getTransferStock(DtoParameter dto) throws SQLException {
        List<Erp004VoTransferStock> transferStockList = new ArrayList<>();
        String query = Erp004Constant.GET_DATA_TRANSFER_STOCKS;
        query = query.replace("@VSEARCH", dto.getSearch() != null ? dto.getSearch().get("VSEARCH").toString() : "");
        System.out.println(query);
        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                while (resultSet.next()) {
                    Erp004VoTransferStock transferStock = new Erp004VoTransferStock();
                    transferStock.setTransferStockId(resultSet.getString("VTRFSTCID"));
                    transferStock.setProductId(resultSet.getString("VPRDID"));
                    transferStock.setProductName(resultSet.getString("VPRDNAME"));
                    transferStock.setWarehouseFromId(resultSet.getString("VWRHSFROM"));
                    transferStock.setWarehouseFromName(resultSet.getString("VWRHSFROMNAME"));
                    transferStock.setWarehouseToId(resultSet.getString("VWRHSTO"));
                    transferStock.setWarehouseToName(resultSet.getString("VWRHSTONAME"));
                    transferStock.setQuantity(resultSet.getString("NQTY"));
                    transferStock.setDescription(resultSet.getString("VDESC"));
                    transferStock.setTotalCost(resultSet.getString("NTCOST"));

                    transferStock.setTransferDate(resultSet.getString("DTRFDT"));
                    transferStockList.add(transferStock);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return transferStockList;
    }

    public static String getLastTransferStockId(DtoParameter dto) throws SQLException {
        String query = Erp004Constant.GET_LAST_TRANSFER_STOCK_ID;
        String warehouseFromId = dto.getSearch() != null ? dto.getSearch().get("WAREHOUSEFROM").toString() : "";
        query = query.replace("@WAREHOUSEFROM", warehouseFromId);

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                String lastId = null;
                while (resultSet.next()) {
                    lastId = resultSet.getString("VTRFSTCID");
                }

                if (lastId == null) {
                    String year = String.valueOf(java.time.LocalDate.now().getYear());
                    String month = String.format("%02d", java.time.LocalDate.now().getMonthValue());
                    return "TSC/" + warehouseFromId + "/" + year + "/" + month + "/000001";
                } else {
                    String nomorUrut = lastId.substring(lastId.length() - 6);
                    int nextNumberingOrder = Integer.parseInt(nomorUrut) + 1;
                    String numberingOrder = String.format("%06d", nextNumberingOrder);

                    String year = String.valueOf(java.time.LocalDate.now().getYear());
                    String month = String.format("%02d", java.time.LocalDate.now().getMonthValue());
                    return "TSC/" + warehouseFromId + "/" + year + "/" + month + "/" + numberingOrder;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static int getTotalStockInventory(DtoParameter dto) throws SQLException {
        String query = Erp004Constant.GET_INVENTORY_ID;
        String productId = dto.getSearch() != null ? dto.getSearch().get("PRODUCTID").toString() : "";
        String warehouseId = dto.getSearch() != null ? dto.getSearch().get("WAREHOUSEID").toString() : "";
        int totalStock = 0;

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            pstat.setString(1, productId);
            pstat.setString(2, warehouseId);
            try (ResultSet resultSet = pstat.executeQuery()) {
                if (resultSet.next()) {
                    totalStock = resultSet.getInt("NTSTCK");
                }
            }
        }
        return totalStock;
    }

    public static List<Erp004VoInventory> getInventory(DtoParameter dto) throws SQLException {
        List<Erp004VoInventory> inventoryList = new ArrayList<>();
        String query = Erp004Constant.GET_DATA_INVENTORY;
        query = query.replace("@VSEARCH", dto.getSearch() != null ? dto.getSearch().get("VSEARCH").toString() : "");
        System.out.println(query);
        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                while (resultSet.next()) {
                    Erp004VoInventory inventory = new Erp004VoInventory();
                    inventory.setInventoryId(resultSet.getString("VINVTRID"));
                    inventory.setWarehouseId(resultSet.getString("VWRHID"));
                    inventory.setWarehouseName(resultSet.getString("VWRHNAME"));
                    inventory.setProductId(resultSet.getString("VPRDID"));
                    inventory.setProductName(resultSet.getString("VPRDNAME"));
                    inventory.setTotalStock(resultSet.getString("NTSTCK"));
                    inventoryList.add(inventory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return inventoryList;
    }

    public static String getLastInventoryId(DtoParameter dto) throws SQLException {
        String query = Erp004Constant.GET_LAST_INVENTORY_ID;
        String warehouse = dto.getSearch() != null ? dto.getSearch().get("WAREHOUSE").toString() : "";
        query = query.replace("@WAREHOUSE", warehouse);

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                String lastId = null;
                while (resultSet.next()) {
                    lastId = resultSet.getString("VINVTRID");
                }

                if (lastId == null) {
                    return "INV/" + warehouse + "/000001";
                } else {
                    String lastOrder = lastId.substring(lastId.length() - 6);
                    int nextNumberingOrder = Integer.parseInt(lastOrder) + 1;
                    String numberingOrder = String.format("%06d", nextNumberingOrder);

                    return "INV/" + warehouse + "/" + numberingOrder;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static String getInventoryId(DtoParameter dto) throws SQLException {
        String query = Erp004Constant.GET_INVENTORY_ID;
        String productId = dto.getSearch() != null ? dto.getSearch().get("vPrdId").toString() : "";
        String warehouseId = dto.getSearch() != null ? dto.getSearch().get("vWrhsId").toString() : "";
        String inventoryId = "";

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            pstat.setString(1, productId);
            pstat.setString(2, warehouseId);
            try (ResultSet resultSet = pstat.executeQuery()) {
                if (resultSet.next()) {
                    inventoryId = resultSet.getString("VINVTRID");
                }
            }
        }
        return inventoryId;
    }
}