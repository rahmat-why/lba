package id.co.lba.mo.erp.app005.view;

import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import id.co.lba.mo.erp.app003.service.Erp003Service;
import id.co.lba.mo.erp.app003.view.Erp003View;
import id.co.lba.mo.erp.app003.vo.Erp003VoWarehouse;
import id.co.lba.mo.erp.app004.service.Erp004Service;
import id.co.lba.mo.erp.app004.vo.Erp004VoInventory;
import id.co.lba.mo.erp.app005.service.Erp005Service;
import id.co.lba.mo.erp.app005.vo.Erp005VoOrder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Erp005View extends JFrame{
    private JFrame frame;
    private JButton erp005p01BtnFilter;
    private JTextField erp005p01Search;
    private JTable erp005p01TblOrder;
    private JButton erp005p01BtnCreateOrder;
    public JPanel erp005p01Panel;
    private DefaultTableModel erp005p01TableModel;


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Erp005View window = new Erp005View();
                window.frame.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                throw new RuntimeException(ex);
            }
        });
    }

    public Erp005View() throws SQLException {
        erp005Initialize();
        erp005AddColumn();
        erp005p01GetOrder();
        erp005p01BtnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    erp005p01Search();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void erp005Initialize() {

    }

    private void erp005AddColumn() {
        erp005p01TableModel = new DefaultTableModel();
        erp005p01TableModel.addColumn("Order ID");
        erp005p01TableModel.addColumn("Date");
        erp005p01TableModel.addColumn("Delivery System");
        erp005p01TableModel.addColumn("Customer Name");
        erp005p01TableModel.addColumn("Product Name");
        erp005p01TableModel.addColumn("Quantity");
        erp005p01TableModel.addColumn("Unit");
        erp005p01TableModel.addColumn("Price");
        erp005p01TableModel.addColumn("Total Price");
        erp005p01TableModel.addColumn("Payment System");
        erp005p01TableModel.addColumn("Notes");
        erp005p01TableModel.addColumn("Warehouse");
        erp005p01TblOrder.setModel(erp005p01TableModel);
    }

    private void erp005p01GetOrder() throws SQLException {
        DtoResponse response = Erp005Service.getDataOrder(new DtoParameter());
        List<Erp005VoOrder> orderList = response.getData();
        for (int i = 0; i < orderList.size(); i++) {
            Erp005VoOrder order = orderList.get(i);
            Object[] obj = new Object[12];

            obj[0] = order.getBjNumber();
            obj[1] = order.getOrderDate();
            obj[2] = order.getDeliverySystem();
            obj[3] = order.getCustomerName();
            obj[4] = order.getProductName();
            obj[5] = order.getQuantity();
            obj[6] = order.getUnit();
            obj[7] = order.getPrice();
            obj[8] = order.getTotalPayment();
            obj[9] = order.getPaymentSystem();
            obj[10] = order.getDescription();
            obj[11] = order.getWarehouseName();
            erp005p01TableModel.addRow(obj);
        }
        erp005p01TblOrder.setModel(erp005p01TableModel);
    }

    private void erp005p01Search() throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("VSEARCH", erp005p01Search.getText());
        param.setSearch(searchMap);
        erp005p01TableModel.setRowCount(0);
        DtoResponse response = Erp005Service.getDataOrder(param);
        List<Erp005VoOrder> orderList = response.getData();
        for (int i = 0; i < orderList.size(); i++) {
            Erp005VoOrder order = orderList.get(i);
            Object[] obj = new Object[4];

            obj[0] = order.getBjNumber();
            obj[1] = order.getOrderDate();
            obj[2] = order.getDeliverySystem();
            obj[3] = order.getCustomerName();
            obj[4] = order.getProductName();
            obj[5] = order.getQuantity();
            obj[6] = order.getUnit();
            obj[7] = order.getPrice();
            obj[8] = order.getTotalPayment();
            obj[9] = order.getPaymentSystem();
            obj[10] = order.getDescription();
            obj[11] = order.getWarehouseName();
            erp005p01TableModel.addRow(obj);
        }
        erp005p01TblOrder.setModel(erp005p01TableModel);
    }
}
