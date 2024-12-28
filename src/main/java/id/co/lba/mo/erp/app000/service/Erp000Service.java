package id.co.lba.mo.erp.app000.service;

import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import LBAJXLibrariesV1.dto.DtoUser;
import id.co.lba.mo.erp.app000.dao.Erp000ObjectDao;

import java.sql.SQLException;
import java.util.Collections;

public class Erp000Service {
    public static DtoResponse verifyUser(DtoParameter param) throws SQLException {
        DtoUser user = Erp000ObjectDao.verifyUser(param);
        String message = "Login berhasil!";
        if(user == null) {
            message = "Username atau password salah!";
        }
        return new DtoResponse("200", Collections.singletonList(user), message);
    }
}
