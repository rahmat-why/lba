package id.co.lba.mo.erp.app005.service;

import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import id.co.lba.mo.erp.app005.dao.Erp005ObjectDao;
import id.co.lba.mo.erp.app005.vo.Erp005VoDetailOrder;
import id.co.lba.mo.erp.app005.vo.Erp005VoOrder;

import java.sql.SQLException;
import java.util.List;

public class Erp005Service {
    public static DtoResponse getDataOrder(DtoParameter param) throws SQLException {
        List<Erp005VoOrder> dataOrder = Erp005ObjectDao.getOrder(param);
        return new DtoResponse("200", dataOrder, "Data successfully received");
    }

    public static DtoResponse getDataDetailOrder(DtoParameter param) throws SQLException {
        List<Erp005VoDetailOrder> dataDetailOrder = Erp005ObjectDao.getDetailOrder(param);
        return new DtoResponse("200", dataDetailOrder, "Data successfully received");
    }
}
