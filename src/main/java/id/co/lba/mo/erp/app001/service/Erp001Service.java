package id.co.lba.mo.erp.app001.service;

import LBAJXLibrariesV1.dto.DtoCombobox;
import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import id.co.lba.Hibernate;
import id.co.lba.mo.erp.app000.model.LbamoerpMstcusts;
import id.co.lba.mo.erp.app001.dao.Erp001ObjectDao;
import id.co.lba.mo.erp.app001.vo.Erp001VoCustomer;
import id.co.lba.mo.erp.app004.dao.Erp004ObjectDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Erp001Service {
    public static DtoResponse getDataCustomer(DtoParameter param) throws SQLException {
        List<Erp001VoCustomer> dataCustomer = Erp001ObjectDao.getCustomer(param);
        return new DtoResponse("200", dataCustomer, "Data successfully received");
    }

    public static DtoResponse saveDataCustomer(DtoParameter param) {
        Transaction transaction = null;
        try (Session session = Hibernate.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Map<String, Object> search = param.getSearch();

            LbamoerpMstcusts lbamoerpMstcusts = new LbamoerpMstcusts();
            lbamoerpMstcusts.setvCstid((String) search.get("vCstId"));
            lbamoerpMstcusts.setvName((String) search.get("vName"));
            lbamoerpMstcusts.setvEmail((String) search.get("vEmail"));
            lbamoerpMstcusts.setvWebsite((String) search.get("vWebsite"));
            lbamoerpMstcusts.setvSector((String) search.get("vSector"));
            lbamoerpMstcusts.setvPhone1((String) search.get("vPhone1"));
            lbamoerpMstcusts.setvPhone2((String) search.get("vPhone2"));
            lbamoerpMstcusts.setvStatus("ACTIVE");

            lbamoerpMstcusts.setdCrea(LocalDateTime.now());
            lbamoerpMstcusts.setvCrea("NEW");

            session.save(lbamoerpMstcusts);

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

    public static DtoResponse deleteDataCustomer(DtoParameter param) {
        Transaction transaction = null;

        try (Session session = Hibernate.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Map<String, Object> search = param.getSearch();

            LbamoerpMstcusts lbamoerpMstcusts = session.get(LbamoerpMstcusts.class, search.get("vCstId"));

            if (lbamoerpMstcusts != null) {
                lbamoerpMstcusts.setvStatus("INACTIVE");
                session.update(lbamoerpMstcusts);
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

    public static DtoResponse updateDataCustomer(DtoParameter param) {
        Transaction transaction = null;

        try (Session session = Hibernate.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Map<String, Object> search = param.getSearch();

            LbamoerpMstcusts lbamoerpMstcusts = session.get(LbamoerpMstcusts.class, search.get("vCstId"));

            if (lbamoerpMstcusts != null) {
                lbamoerpMstcusts.setvName(search.get("vName").toString());
                lbamoerpMstcusts.setvEmail(search.get("vEmail").toString());
                lbamoerpMstcusts.setvWebsite(search.get("vWebsite").toString());
                lbamoerpMstcusts.setvSector(search.get("vSector").toString());
                lbamoerpMstcusts.setvPhone1((String) search.get("vPhone1"));
                lbamoerpMstcusts.setvPhone2((String) search.get("vPhone2"));

                session.update(lbamoerpMstcusts);

                transaction.commit();
            }

            return new DtoResponse("200", null, "Data successfully updated");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return new DtoResponse("500", null, "Failed to update data: " + e.getMessage());
        }
    }

    public static DtoResponse getLastCustomerId(DtoParameter param) throws SQLException {
        String customerId = Erp001ObjectDao.getLastCustomerId(param);
        return new DtoResponse("200", Collections.singletonList(customerId), "Data successfully received");
    }

    public static DtoResponse getComboboxSector(DtoParameter param) throws SQLException {
        List<DtoCombobox> comboboxSectorList = Erp001ObjectDao.getComboboxSector(param);
        return new DtoResponse("200", comboboxSectorList, "Data successfully received");
    }
}
