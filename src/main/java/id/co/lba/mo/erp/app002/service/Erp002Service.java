package id.co.lba.mo.erp.app002.service;

import LBAJXLibrariesV1.dto.DtoCombobox;
import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import id.co.lba.mo.erp.app000.model.LbamoerpMstproducts;
import id.co.lba.Hibernate;
import id.co.lba.mo.erp.app001.dao.Erp001ObjectDao;
import id.co.lba.mo.erp.app002.dao.Erp002ObjectDao;
import id.co.lba.mo.erp.app002.vo.Erp002VoProduct;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Erp002Service {
    public static DtoResponse getDataProduct(DtoParameter param) throws SQLException {
        List<Erp002VoProduct> dataProduct = Erp002ObjectDao.getProduct(param);
        return new DtoResponse("200", dataProduct, "Data successfully received");
    }

    public static DtoResponse saveDataProduct(DtoParameter param) {
        Transaction transaction = null;
        try (Session session = Hibernate.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Map<String, Object> search = param.getSearch();
            LbamoerpMstproducts product = new LbamoerpMstproducts();
            product.setvPrdId((String) search.get("vPrdId"));
            product.setvName((String) search.get("vName"));
            product.setxImg((String) search.get("xImg"));
            product.setnWeight((Integer) search.get("nWeight"));
            product.setvUnit((String) search.get("vUnit"));
            product.setnPrbuy((Integer) search.get("nprBuy"));
            product.setnPruntdel((Integer) search.get("nprUntDel"));
            product.setnPruntpic((Integer) search.get("nprUntPic"));
            product.setvStatus("ACTIVE");
            product.setvCrea("NEW");
            product.setdCrea(LocalDateTime.now());
            session.save(product);
            transaction.commit();
            return new DtoResponse("200", null, "Data successfully saved");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return new DtoResponse("500", null, "Failed to save data: " + e.getMessage());
        }
    }

    public static DtoResponse deleteDataProduct(DtoParameter param) {
        Transaction transaction = null;
        try (Session session = Hibernate.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Map<String, Object> search = param.getSearch();

            LbamoerpMstproducts product = session.get(LbamoerpMstproducts.class, search.get("vPrdId"));

            if (product != null) {
                product.setvStatus("INACTIVE");
                session.update(product);
            }

            transaction.commit();

            return new DtoResponse("200", null, "Data successfully deleted");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return new DtoResponse("500", null, "Failed to update data: " + e.getMessage());
        }
    }

    public static DtoResponse updateDataProduct(DtoParameter param) {
        Transaction transaction = null;
        try (Session session = Hibernate.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Map<String, Object> search = param.getSearch();

            LbamoerpMstproducts product = session.get(LbamoerpMstproducts.class, search.get("vPrdId"));

            if (product != null) {
                product.setvName((String) search.get("vName"));
                product.setxImg((String) search.get("xImg"));
                product.setnWeight((Integer) search.get("nWeight"));
                product.setvUnit((String) search.get("vUnit"));
                product.setnPrbuy((Integer) search.get("nprBuy"));
                product.setnPruntdel((Integer) search.get("nprUntDel"));
                product.setnPruntpic((Integer) search.get("nprUntPic"));
                session.update(product);
            }
            transaction.commit();
            return new DtoResponse("200", null, "Data successfully updated");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return new DtoResponse("500", null, "Failed to update data: " + e.getMessage());
        }
    }

    public static DtoResponse getLastProductId(DtoParameter param) throws SQLException {
        String productId = Erp002ObjectDao.getLastProductId(param);
        return new DtoResponse("200", Collections.singletonList(productId), "Data successfully received");
    }

    public static DtoResponse getComboboxUnit(DtoParameter param) throws SQLException {
        List<DtoCombobox> comboboxUnitList = Erp002ObjectDao.getComboboxUnit(param);
        return new DtoResponse("200", comboboxUnitList, "Data successfully received");
    }
}
