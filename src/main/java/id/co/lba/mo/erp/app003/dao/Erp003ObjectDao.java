package id.co.lba.mo.erp.app003.dao;

import LBAJXLibrariesV1.connection.DBConnect;
import LBAJXLibrariesV1.dto.DtoParameter;
import id.co.lba.mo.erp.app003.constant.Erp003Constant;
import id.co.lba.mo.erp.app003.vo.Erp003VoWarehouse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Erp003ObjectDao {
    static DBConnect connection = new DBConnect();

    public static List<Erp003VoWarehouse> getWarehouse(DtoParameter dto) throws SQLException {
        List<Erp003VoWarehouse> warehouseList = new ArrayList<>();
        String query = Erp003Constant.GET_DATA_WAREHOUSE;
        query = query.replace("@VSEARCH", dto.getSearch() != null ? dto.getSearch().get("VSEARCH").toString() : "");

        System.out.println("query");
        System.out.println(query);

        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                while (resultSet.next()) {
                    Erp003VoWarehouse warehouse = new Erp003VoWarehouse();
                    warehouse.setWarehouseId(resultSet.getString("VWRHSID"));
                    warehouse.setName(resultSet.getString("VNAME"));
                    warehouse.setAddress(resultSet.getString("VADDRESS"));
                    warehouse.setCapacity(String.valueOf(resultSet.getInt("NCAPACITY")));
                    warehouseList.add(warehouse);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throw the exception for proper error handling
        }
        return warehouseList;
    }
}
