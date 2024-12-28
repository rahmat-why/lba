package id.co.lba.mo.erp.app000.dao;

import LBAJXLibrariesV1.connection.DBConnect;
import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoUser;

import javax.swing.*;
import java.sql.SQLException;

public class Erp000ObjectDao {
    static DBConnect connection = new DBConnect();

    public static DtoUser verifyUser(DtoParameter dto) throws SQLException {
        String username = dto.getSearch() != null ? dto.getSearch().get("vUsername").toString() : "";
        String password = dto.getSearch() != null ? dto.getSearch().get("vPassword").toString() : "";

        try {
            String functionCall = "SELECT * FROM VerifyUser(?, ?)";
            connection.pstat = connection.conn.prepareStatement(functionCall);
            connection.pstat.setString(1, username);
            connection.pstat.setString(2, password);

            connection.result = connection.pstat.executeQuery();

            if (!connection.result.next()) {
                return null;
            }

            String resultUserId = connection.result.getString("VUSERID");
            String resultFullname = connection.result.getString("VFULLNAME");
            String resultUsername = connection.result.getString("VUSERNAME");

            DtoUser user = new DtoUser();
            user.setUserId(resultUserId);
            user.setFullname(resultFullname);
            user.setUsername(resultUsername);

            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
