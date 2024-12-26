package id.co.lba.mo.erp.app004.view;

import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import id.co.lba.mo.erp.app003.service.Erp003Service;
import id.co.lba.mo.erp.app003.view.Erp003View;
import id.co.lba.mo.erp.app003.vo.Erp003VoWarehouse;
import id.co.lba.mo.erp.app004.service.Erp004Service;
import id.co.lba.mo.erp.app004.vo.Erp004VoStockProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Erp004View extends JFrame{
    private JFrame frame;
    private JButton erp004p01BtnFilter;
    private JTextField erp004p01Search;
    private JTextField erp004p01TxtIdStock;
    private JTextField erp004p01TxtQuantity;
    private JTextField erp004p01TxtCmbWarehouse;
    private JTextField erp004p01TxtTotalPayment;
    private JTable erp004p01TblStock;
    private JButton erp004p01BtnSaveStock;
    private JPanel erp004p01Panel;
    private JPanel erp004p01PanelStoctProduct;
    private DefaultTableModel erp004p01TableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Erp004View window = new Erp004View();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Erp004View() throws SQLException {
        erp004Initialize();
        erp004AddColumn();
        erp004p01GetStock();
        erp004p01BtnSaveStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp003p01SaveStock();
            }
        });
        erp004p01BtnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    erp004p01Search();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void erp004Initialize() {
        add(this.erp004p01Panel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void erp004AddColumn() {
        erp004p01TableModel = new DefaultTableModel();
        erp004p01TableModel.addColumn("Stock ID");
        erp004p01TableModel.addColumn("Warehouse");
        erp004p01TableModel.addColumn("Quantity");
        erp004p01TableModel.addColumn("Total Payment");
        erp004p01TableModel.addColumn("Payment Date");
        erp004p01TableModel.addColumn("Payment Status");
        erp004p01TblStock.setModel(erp004p01TableModel);
    }

    private void erp004p01GetStock() throws SQLException {
        DtoResponse response = Erp004Service.getDataStockProduct(new DtoParameter());
        List<Erp004VoStockProduct> stockProductList = response.getData();
        for (int i = 0; i < stockProductList.size(); i++) {
            Erp004VoStockProduct stockProduct = stockProductList.get(i);
            Object[] obj = new Object[6];

            obj[0] = stockProduct.getStockProductId();
            obj[1] = stockProduct.getWarehouseName();
            obj[2] = stockProduct.getQuantity();
            obj[3] = stockProduct.getTotalPayment();
            obj[4] = stockProduct.getPaymentDate();
            obj[5] = stockProduct.getPaymentStatus();
            erp004p01TableModel.addRow(obj);
        }
        erp004p01TblStock.setModel(erp004p01TableModel);
    }

    private void erp003p01SaveStock() {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("vStcId", "STC7");
        searchMap.put("vWrhsId", "WHS004");
        searchMap.put("vQuantity", 100);
        searchMap.put("vTotalPayment", 200);

        LocalDate dPaymentDate = LocalDate.parse("2024-01-01");
        searchMap.put("dPaymentDate", dPaymentDate);

        searchMap.put("vPaymentStatus", "PAID");
        param.setSearch(searchMap);
        Erp004Service.saveDataStockProduct(param);
        erp004p01Clear();
        try {
            erp004p01GetStock();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void erp004p01Clear() {
        erp004p01TxtIdStock.setText("");
        erp004p01TxtQuantity.setText("");
        erp004p01TxtTotalPayment.setText("");
    }

    private void erp004p01Search() throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("VSEARCH", erp004p01Search.getText());
        param.setSearch(searchMap);
        erp004p01TableModel.setRowCount(0);
        DtoResponse response = Erp004Service.getDataStockProduct(param);
        List<Erp004VoStockProduct> stockProductList = response.getData();
        for (int i = 0; i < stockProductList.size(); i++) {
            Erp004VoStockProduct stockProduct = stockProductList.get(i);
            Object[] obj = new Object[6];

            obj[0] = stockProduct.getStockProductId();
            obj[1] = stockProduct.getWarehouseName();
            obj[2] = stockProduct.getQuantity();
            obj[3] = stockProduct.getTotalPayment();
            obj[4] = stockProduct.getPaymentDate();
            obj[5] = stockProduct.getPaymentStatus();
            erp004p01TableModel.addRow(obj);
        }
        erp004p01TblStock.setModel(erp004p01TableModel);
    }
}
