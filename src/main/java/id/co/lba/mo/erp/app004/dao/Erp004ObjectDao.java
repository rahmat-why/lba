package id.co.lba.mo.erp.app004.dao;

import LBAJXLibrariesV1.connection.DBConnect;
import LBAJXLibrariesV1.dto.DtoParameter;
import id.co.lba.mo.erp.app004.constant.Erp004Constant;
import id.co.lba.mo.erp.app004.vo.Erp004VoStockProduct;
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

        System.out.println("query");
        System.out.println(query);

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                while (resultSet.next()) {
                    Erp004VoStockProduct stockProduct = new Erp004VoStockProduct();
                    stockProduct.setStockProductId(resultSet.getString("VSTCPRDID"));
                    stockProduct.setWarehouseId(resultSet.getString("VWRHSID"));
                    stockProduct.setWarehouseName(resultSet.getString("VWRHSNAME"));
                    stockProduct.setQuantity(resultSet.getInt("NQTY"));
                    stockProduct.setTotalPayment(resultSet.getInt("NTPYMNTS"));
                    stockProduct.setPaymentDate(resultSet.getString("DPYMNTDT"));
                    stockProduct.setPaymentStatus(resultSet.getString("VPYMNTST"));
                    stockProductList.add(stockProduct);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throw the exception for proper error handling
        }
        return stockProductList;
    }
}