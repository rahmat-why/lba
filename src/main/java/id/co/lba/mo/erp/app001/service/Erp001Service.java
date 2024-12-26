package id.co.lba.mo.erp.app001.service;

import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import id.co.lba.Hibernate;
import id.co.lba.mo.erp.app000.model.LbamoerpMstcusts;
import id.co.lba.mo.erp.app001.dao.Erp001ObjectDao;
import id.co.lba.mo.erp.app001.vo.Erp001VoCustomer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            System.out.println(search.get("vCstId"));
            lbamoerpMstcusts.setvCstid((String) search.get("vCstId"));
            lbamoerpMstcusts.setvName((String) search.get("vName"));
            lbamoerpMstcusts.setvEmail((String) search.get("vEmail"));
            lbamoerpMstcusts.setvWebsite((String) search.get("vWebsite"));
            lbamoerpMstcusts.setvSector((String) search.get("vSector"));
            lbamoerpMstcusts.setdBegineff((LocalDate) search.get("dBeginEff"));
            lbamoerpMstcusts.setdEndeff((LocalDate) search.get("dEndEff"));

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
                session.delete(lbamoerpMstcusts);
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
                // Update fields
                lbamoerpMstcusts.setvName(search.get("vName").toString());
                lbamoerpMstcusts.setvEmail(search.get("vEmail").toString());
                lbamoerpMstcusts.setvWebsite(search.get("vWebsite").toString());
                lbamoerpMstcusts.setvSector(search.get("vSector").toString());
                lbamoerpMstcusts.setdBegineff(LocalDate.parse(search.get("dBeginEff").toString()));
                lbamoerpMstcusts.setdEndeff(LocalDate.parse(search.get("dEndEff").toString()));

                session.update(lbamoerpMstcusts);

                transaction.commit();
            }

            return new DtoResponse("200", null, "Data successfully updated");
        } catch (Exception e) {
            System.out.println("error");
            System.out.println(e.getMessage());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return new DtoResponse("500", null, "Failed to update data: " + e.getMessage());
        }
    }
}
