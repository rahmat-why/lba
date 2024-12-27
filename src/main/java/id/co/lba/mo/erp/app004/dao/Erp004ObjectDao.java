package id.co.lba.mo.erp.app004.dao;

import LBAJXLibrariesV1.connection.DBConnect;
import LBAJXLibrariesV1.dto.DtoParameter;
import id.co.lba.mo.erp.app004.constant.Erp004Constant;
import id.co.lba.mo.erp.app004.vo.Erp004VoProduct;
import id.co.lba.mo.erp.app004.vo.Erp004VoStockProduct;
import id.co.lba.mo.erp.app004.vo.Erp004VoWarehouse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Erp004ObjectDao {
    static DBConnect connection = new DBConnect();

    public static List<Erp004VoStockProduct> getStockProduct(DtoParameter dto) throws SQLException {
        List<Erp004VoStockProduct> stockProductList = new ArrayList<>();
        String query = Erp004Constant.GET_DATA_STOCK_PRODUCTS;
        query = query.replace("@VSEARCH", dto.getSearch() != null ? dto.getSearch().get("VSEARCH").toString() : "");
        System.out.println(query);
        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                while (resultSet.next()) {
                    Erp004VoStockProduct stockProduct = new Erp004VoStockProduct();
                    stockProduct.setStockProductId(resultSet.getString("VSTCPRDID"));
                    stockProduct.setWarehouseId(resultSet.getString("VWRHSID"));
                    stockProduct.setWarehouseName(resultSet.getString("VWRHSNAME"));
                    stockProduct.setProductName(resultSet.getString("VPRDNAME"));
                    stockProduct.setQuantity(resultSet.getString("NQTY"));
                    stockProduct.setTotalPayment(resultSet.getString("NTPYMNTS"));
                    stockProduct.setPaymentDate(resultSet.getString("DPYMNTDT"));
                    stockProduct.setPaymentStatus(resultSet.getString("VPYMNTST"));
                    stockProduct.setPrice(resultSet.getString("NPRICE"));
                    stockProductList.add(stockProduct);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throw the exception for proper error handling
        }
        return stockProductList;
    }

    public static List<Erp004VoProduct> getProduct(DtoParameter dto) throws SQLException {
        List<Erp004VoProduct> productList = new ArrayList<>();
        String query = Erp004Constant.GET_DATA_PRODUCTS;

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
        String query = Erp004Constant.GET_DATA_WAREHOUSES;

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

    public static String getLastStockProductId(DtoParameter dto) throws SQLException {
        String query = Erp004Constant.GET_LAST_STOCK_PRODUCT_ID;
        String warehouseId = dto.getSearch() != null ? dto.getSearch().get("WAREHOUSE").toString() : "";
        query = query.replace("@WAREHOUSE", warehouseId);

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                String lastId = null;
                while (resultSet.next()) {
                    lastId = resultSet.getString("VSTCPRDID");
                }

                if (lastId == null) {
                    String tahun = String.valueOf(java.time.LocalDate.now().getYear());
                    String bulan = String.format("%02d", java.time.LocalDate.now().getMonthValue());
                    return "STC/" + warehouseId + "/" + tahun + "/" + bulan + "/000001";
                } else {
                    String nomorUrut = lastId.substring(lastId.length() - 6);
                    int nextNomorUrut = Integer.parseInt(nomorUrut) + 1;
                    String nextNomorUrutStr = String.format("%06d", nextNomorUrut);

                    String tahun = String.valueOf(java.time.LocalDate.now().getYear());
                    String bulan = String.format("%02d", java.time.LocalDate.now().getMonthValue());
                    return "STC/" + warehouseId + "/" + tahun + "/" + bulan + "/" + nextNomorUrutStr;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}