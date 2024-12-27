package id.co.lba.mo.erp.app002.dao;

import LBAJXLibrariesV1.connection.DBConnect;
import LBAJXLibrariesV1.dto.DtoCombobox;
import LBAJXLibrariesV1.dto.DtoParameter;
import id.co.lba.mo.erp.app001.constant.Erp001Constant;
import id.co.lba.mo.erp.app002.constant.Erp002Constant;
import id.co.lba.mo.erp.app002.vo.Erp002VoProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Erp002ObjectDao {
    static DBConnect connection = new DBConnect();

    public static List<Erp002VoProduct> getProduct(DtoParameter dto) throws SQLException {
        List<Erp002VoProduct> productList = new ArrayList<>();
        String query = Erp002Constant.GET_DATA_PRODUCT;
        query = query.replace("@VSEARCH", dto.getSearch() != null ? dto.getSearch().get("VSEARCH").toString() : "");

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                while (resultSet.next()) {
                    Erp002VoProduct product = new Erp002VoProduct();
                    product.setProductId(resultSet.getString("VPRDID"));
                    product.setName(resultSet.getString("VNAME"));
                    product.setImage(Arrays.toString(resultSet.getBytes("XIMG")));
                    product.setWeight(String.valueOf(resultSet.getInt("NWEIGHT")));
                    product.setUnit(resultSet.getString("VUNIT"));
                    product.setPriceBuy(String.valueOf(resultSet.getInt("NPRBUY")));
                    product.setPriceUnitDelivery(String.valueOf(resultSet.getInt("NPRUNTDEL")));
                    product.setPriceUnitPrice(String.valueOf(resultSet.getInt("NPRUNTPIC")));
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throw the exception for proper error handling
        }
        return productList;
    }

    public static String getLastProductId(DtoParameter dto) throws SQLException {
        String query = Erp002Constant.GET_LAST_PRODUCT_ID;

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                String lastId = null;
                if (resultSet.next()) {
                    lastId = resultSet.getString("VPRDID");
                }

                if (lastId == null) {
                    return "PD-000001";
                } else {
                    String numericPart = lastId.substring(lastId.indexOf('-') + 1);
                    int nextNumber = Integer.parseInt(numericPart) + 1;
                    return String.format("PD-%06d", nextNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<DtoCombobox> getComboboxUnit(DtoParameter dto) throws SQLException {
        List<DtoCombobox> comboboxUnitList = new ArrayList<>();
        String query = Erp002Constant.GET_COMBOBOX_UNIT;

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                while (resultSet.next()) {
                    DtoCombobox unit = new DtoCombobox();
                    unit.setValue(resultSet.getString("VKEY"));
                    unit.setDisplay(resultSet.getString("VVALUE"));
                    unit.setHelper(resultSet.getString("VHELPER"));
                    comboboxUnitList.add(unit);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return comboboxUnitList;
    }
}
