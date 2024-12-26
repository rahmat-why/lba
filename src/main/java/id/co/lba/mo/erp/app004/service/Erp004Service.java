package id.co.lba.mo.erp.app004.service;

import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import id.co.lba.Hibernate;
import id.co.lba.mo.erp.app000.model.LbamoerpTxnstckprds;
import id.co.lba.mo.erp.app004.dao.Erp004ObjectDao;
import id.co.lba.mo.erp.app004.vo.Erp004VoStockProduct;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Erp004Service {

    public static DtoResponse getDataStockProduct(DtoParameter param) throws SQLException {
        List<Erp004VoStockProduct> dataStockProduct = Erp004ObjectDao.getStockProduct(param);
        return new DtoResponse("200", dataStockProduct, "Data successfully received");
    }

    public static DtoResponse saveDataStockProduct(DtoParameter param) {
        Transaction transaction = null;
        try (Session session = Hibernate.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Map<String, Object> search = param.getSearch();
            LbamoerpTxnstckprds stockProduct = new LbamoerpTxnstckprds();
            stockProduct.setvStcprdid((String) search.get("vStcId"));
            stockProduct.setvWrhsid((String) search.get("vWrhsId"));
            stockProduct.setnQty((Integer) search.get("vQuantity"));
            stockProduct.setnTpyments((Integer) search.get("vTotalPayment"));
            stockProduct.setdPaymentDate((LocalDate) search.get("dPaymentDate"));
            stockProduct.setvPaymentStatus((String) search.get("vPaymentStatus"));
            stockProduct.setvCrea("NEW");
            stockProduct.setdCrea(LocalDateTime.now());
            session.save(stockProduct);
            transaction.commit();
            return new DtoResponse("200", null, "Data successfully saved");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return new DtoResponse("500", null, "Failed to save data: " + e.getMessage());
        }
    }
}