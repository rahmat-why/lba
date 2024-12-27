package id.co.lba.mo.erp.app001.dao;

import LBAJXLibrariesV1.connection.DBConnect;
import LBAJXLibrariesV1.dto.DtoCombobox;
import LBAJXLibrariesV1.dto.DtoParameter;
import id.co.lba.mo.erp.app001.constant.Erp001Constant;
import id.co.lba.mo.erp.app001.vo.Erp001VoCustomer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import id.co.lba.mo.erp.app001.constant.Erp001Constant.*;

public class Erp001ObjectDao {
    static DBConnect connection = new DBConnect();

    public static List<Erp001VoCustomer> getCustomer(DtoParameter dto) throws SQLException {
        List<Erp001VoCustomer> customerList = new ArrayList<>();
        String query = Erp001Constant.GET_DATA_CUSTOMER;
        query = query.replace("@VSEARCH", dto.getSearch() != null ? dto.getSearch().get("VSEARCH").toString() : "");

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                while (resultSet.next()) {
                    Erp001VoCustomer customer = new Erp001VoCustomer();
                    customer.setCustomerId(resultSet.getString("VCSTID"));
                    customer.setName(resultSet.getString("VNAME"));
                    customer.setEmail(resultSet.getString("VEMAIL"));
                    customer.setWebsite(resultSet.getString("VWEBSITE"));
                    customer.setSector(resultSet.getString("VSECTOR"));
                    customer.setPhone1(resultSet.getString("VPHONE1"));
                    customer.setPhone2(resultSet.getString("VPHONE2"));
                    customer.setStatus(resultSet.getString("VSTATUS"));
                    customerList.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return customerList;
    }

    public static String getLastCustomerId(DtoParameter dto) throws SQLException {
        String query = Erp001Constant.GET_LAST_CUSTOMER_ID;

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                String lastId = null;
                if (resultSet.next()) {
                    lastId = resultSet.getString("VCSTID");
                }

                if (lastId == null) {
                    return "LBA-000001";
                } else {
                    String numericPart = lastId.substring(lastId.indexOf('-') + 1);
                    int nextNumber = Integer.parseInt(numericPart) + 1;
                    return String.format("LBA-%06d", nextNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<DtoCombobox> getComboboxSector(DtoParameter dto) throws SQLException {
        List<DtoCombobox> comboboxSectorList = new ArrayList<>();
        String query = Erp001Constant.GET_COMBOBOX_SECTOR;

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                while (resultSet.next()) {
                    DtoCombobox sector = new DtoCombobox();
                    sector.setValue(resultSet.getString("VKEY"));
                    sector.setDisplay(resultSet.getString("VVALUE"));
                    sector.setHelper(resultSet.getString("VHELPER"));
                    comboboxSectorList.add(sector);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return comboboxSectorList;
    }
}
