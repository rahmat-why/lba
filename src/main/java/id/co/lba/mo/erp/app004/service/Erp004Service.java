package id.co.lba.mo.erp.app004.service;

import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import id.co.lba.Hibernate;
import id.co.lba.mo.erp.app000.model.LbamoerpMstinvtrs;
import id.co.lba.mo.erp.app000.model.LbamoerpTxnrstcks;
import id.co.lba.mo.erp.app000.model.LbamoerpTxntrfstcks;
import id.co.lba.mo.erp.app004.dao.Erp004ObjectDao;
import id.co.lba.mo.erp.app004.vo.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Erp004Service {

    public static DtoResponse getDataRestock(DtoParameter param) throws SQLException {
        List<Erp004VoRestock> dataRestock = Erp004ObjectDao.getRestock(param);
        return new DtoResponse("200", dataRestock, "Data successfully received");
    }

    public static DtoResponse saveDataRestock(DtoParameter param) {
        Transaction transaction = null;
        try (Session session = Hibernate.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Map<String, Object> search = param.getSearch();
            LbamoerpTxnrstcks restock = new LbamoerpTxnrstcks();
            restock.setvRstckId((String) search.get("vRstId"));
            restock.setvPrdId((String) search.get("vPrdId"));
            restock.setvWrhsId((String) search.get("vWrhsId"));
            restock.setnPrice((Integer) search.get("vPrice"));
            restock.setnQty((Integer) search.get("vQuantity"));
            restock.setnTpyment((Integer) search.get("vTotalPayment"));
            restock.setdPaymentDate((LocalDate) search.get("dPaymentDate"));
            restock.setvPaymentStatus((String) search.get("vPaymentStatus"));
            restock.setdRestockDate((LocalDate) search.get("dRestockDate"));
            restock.setvCrea("NEW");
            restock.setdCrea(LocalDateTime.now());
            session.save(restock);
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

    public static DtoResponse getProduct(DtoParameter param) throws SQLException {
        List<Erp004VoProduct> productList = Erp004ObjectDao.getProduct(new DtoParameter());
        return new DtoResponse("200", productList, "Data successfully received");
    }

    public static DtoResponse getWarehouse(DtoParameter param) throws SQLException {
        List<Erp004VoWarehouse> warehouseList = Erp004ObjectDao.getWarehouse(new DtoParameter());
        return new DtoResponse("200", warehouseList, "Data successfully received");
    }

    public static DtoResponse getLastRestockId(DtoParameter param) throws SQLException {
        String restockId = Erp004ObjectDao.getLastRestockId(param);
        return new DtoResponse("200", Collections.singletonList(restockId), "Data successfully received");
    }

    public static DtoResponse getDataTransferStock(DtoParameter param) throws SQLException {
        List<Erp004VoTransferStock> dataTransferStock = Erp004ObjectDao.getTransferStock(param);
        return new DtoResponse("200", dataTransferStock, "Data successfully received");
    }

    public static DtoResponse getLastTransferStockId(DtoParameter param) throws SQLException {
        String transferStockId = Erp004ObjectDao.getLastTransferStockId(param);
        return new DtoResponse("200", Collections.singletonList(transferStockId), "Data successfully received");
    }

    public static DtoResponse saveDataTransferStock(DtoParameter param) {
        Transaction transaction = null;
        try (Session session = Hibernate.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Map<String, Object> search = param.getSearch();
            LbamoerpTxntrfstcks transferStock = new LbamoerpTxntrfstcks();
            transferStock.setvTrfstcId((String) search.get("vTransferStockId"));
            transferStock.setvPrdId((String) search.get("vProductId"));
            transferStock.setvWrhsFrom((String) search.get("vWarehouseFrom"));
            transferStock.setvWrhsTo((String) search.get("vWarehouseTo"));
            transferStock.setnQty((Integer) search.get("nQuantity"));
            transferStock.setvDesc((String) search.get("vDescription"));
            transferStock.setnTcost((Integer) search.get("nTotalCost"));
            transferStock.setdTrfdt((LocalDate) search.get("dTransferDate"));
            transferStock.setvCrea("NEW");
            transferStock.setdCrea(LocalDateTime.now());
            session.save(transferStock);
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

    public static DtoResponse getTotalStockInventory(DtoParameter param) throws SQLException {
        int totalStockInventory = Erp004ObjectDao.getTotalStockInventory(param);
        return new DtoResponse("200", Collections.singletonList(totalStockInventory), "Data successfully received");
    }

    public static DtoResponse getDataInventory(DtoParameter param) throws SQLException {
        List<Erp004VoInventory> dataInventory = Erp004ObjectDao.getInventory(param);
        return new DtoResponse("200", dataInventory, "Data successfully received");
    }

    public static DtoResponse saveDataInventory(DtoParameter param) {
        Transaction transaction = null;
        try (Session session = Hibernate.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Map<String, Object> search = param.getSearch();
            LbamoerpMstinvtrs inventory = new LbamoerpMstinvtrs();
            inventory.setvInvtrId((String) search.get("vIvtId"));
            inventory.setvWrhsId((String) search.get("vWrhsId"));
            inventory.setvPrdId((String) search.get("vPrdId"));
            inventory.setnStck((Integer) search.get("nTotalStock"));
            inventory.setvCrea("NEW");
            inventory.setdCrea(LocalDateTime.now());
            session.save(inventory);
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

    public static DtoResponse getLastInventoryId(DtoParameter param) throws SQLException {
        String inventoryId = Erp004ObjectDao.getLastInventoryId(param);
        return new DtoResponse("200", Collections.singletonList(inventoryId), "Data successfully received");
    }
}