package id.co.lba.mo.erp.app003.service;

import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import id.co.lba.Hibernate;
import id.co.lba.mo.erp.app000.model.LbamoerpMstwrhses;
import id.co.lba.mo.erp.app002.dao.Erp002ObjectDao;
import id.co.lba.mo.erp.app003.dao.Erp003ObjectDao;
import id.co.lba.mo.erp.app003.vo.Erp003VoWarehouse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Erp003Service {

    public static DtoResponse getDataWarehouse(DtoParameter param) throws SQLException {
        List<Erp003VoWarehouse> dataWarehouse = Erp003ObjectDao.getWarehouse(param);
        return new DtoResponse("200", dataWarehouse, "Data successfully received");
    }

    public static DtoResponse saveDataWarehouse(DtoParameter param) {
        Transaction transaction = null;
        try (Session session = Hibernate.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Map<String, Object> search = param.getSearch();
            LbamoerpMstwrhses warehouse = new LbamoerpMstwrhses();
            warehouse.setvWrhsid((String) search.get("vWrhsId"));
            warehouse.setvName((String) search.get("vName"));
            warehouse.setvAddress((String) search.get("vAddress"));
            warehouse.setnCapacity((Integer) search.get("nCapacity"));
            warehouse.setvStatus("ACTIVE");
            warehouse.setvCrea("NEW");
            warehouse.setdCrea(LocalDateTime.now());
            session.save(warehouse);
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

    public static DtoResponse deleteDataWarehouse(DtoParameter param) {
        Transaction transaction = null;
        try (Session session = Hibernate.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Map<String, Object> search = param.getSearch();
            LbamoerpMstwrhses warehouse = session.get(LbamoerpMstwrhses.class, search.get("vWrhsId"));
            if (warehouse != null) {
                warehouse.setvStatus("INACTIVE");
                session.update(warehouse);
            }
            transaction.commit();
            return new DtoResponse("200", null, "Data successfully deleted");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return new DtoResponse("500", null, "Failed to delete data: " + e.getMessage());
        }
    }

    public static DtoResponse updateDataWarehouse(DtoParameter param) {
        Transaction transaction = null;
        try (Session session = Hibernate.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Map<String, Object> search = param.getSearch();
            LbamoerpMstwrhses warehouse = session.get(LbamoerpMstwrhses.class, search.get("vWrhsId"));
            if (warehouse != null) {
                warehouse.setvName((String) search.get("vName"));
                warehouse.setvAddress((String) search.get("vAddress"));
                warehouse.setnCapacity((Integer) search.get("nCapacity"));
                session.update(warehouse);
            }
            transaction.commit();
            return new DtoResponse("200", null, "Data successfully updated");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return new DtoResponse("500", null, "Failed to update data: " + e.getMessage());
        }
    }

    public static DtoResponse getLastWarehouseId(DtoParameter param) throws SQLException {
        String warehouseId = Erp003ObjectDao.getLastWarehouseId(param);
        return new DtoResponse("200", Collections.singletonList(warehouseId), "Data successfully received");
    }
}