package id.co.lba.mo.erp.app001.dao;

import LBAJXLibrariesV1.connection.DBConnect;
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
                    customer.setBeginEffective(resultSet.getString("DBEGINEFF"));
                    customer.setEndEffective(resultSet.getString("DENDEFF"));
                    customerList.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throw the exception for proper error handling
        }

        return customerList;
    }
}
