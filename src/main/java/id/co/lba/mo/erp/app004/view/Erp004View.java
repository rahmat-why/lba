package id.co.lba.mo.erp.app004.view;

import LBAJXLibrariesV1.dto.DtoCombobox;
import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import id.co.lba.mo.erp.app004.service.Erp004Service;
import id.co.lba.mo.erp.app004.vo.Erp004VoProduct;
import id.co.lba.mo.erp.app004.vo.Erp004VoStockProduct;
import id.co.lba.mo.erp.app004.vo.Erp004VoWarehouse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Erp004View extends JFrame{
    private JFrame frame;
    private JButton erp004p01BtnFilter;
    private JTextField erp004p01Search;
    private JTextField erp004p01TxtStockProductId;
    private JTextField erp004p01TxtQuantity;
    private JTextField erp004p01TxtTotalPayment;
    private JTable erp004p01TblStock;
    private JButton erp004p01BtnSaveStock;
    private JPanel erp004p01Panel;
    private JPanel erp004p01PanelStockProduct;
    private DefaultTableModel erp004p01TableModel;
    private JTabbedPane erp004p01Tab;
    private JPanel erp004p01PanelTransferStock;
    private JComboBox erp004p01CmbWarehouse;
    private JComboBox erp004p01CmbProduct;
    private JTextField erp004p01TxtPrice;

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
        erp004p01GetProduct();
        erp004p01GetWarehouse();
        erp004p01GetStockProductId();
        erp004p01GetProductPrice();
        erp004p01BtnSaveStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp004p01SaveStock();
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
        erp004p01CmbWarehouse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    erp004p01GetStockProductId();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        erp004p01CmbProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp004p01GetProductPrice();
            }
        });
        erp004p01TxtPrice.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                erp004p01CalculatePayment();
            }
        });
        erp004p01TxtQuantity.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                erp004p01CalculatePayment();
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
        erp004p01TableModel.addColumn("Product");
        erp004p01TableModel.addColumn("Warehouse");
        erp004p01TableModel.addColumn("Price");
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
            Object[] obj = new Object[8];

            obj[0] = stockProduct.getStockProductId();
            obj[1] = stockProduct.getProductName();
            obj[2] = stockProduct.getWarehouseName();
            obj[3] = stockProduct.getPrice();
            obj[4] = stockProduct.getQuantity();
            obj[5] = stockProduct.getTotalPayment();
            obj[6] = stockProduct.getPaymentDate();
            obj[7] = stockProduct.getPaymentStatus();
            erp004p01TableModel.addRow(obj);
        }
        erp004p01TblStock.setModel(erp004p01TableModel);
    }

    private void erp004p01GetProduct() throws SQLException {
        DtoResponse response = Erp004Service.getProduct(new DtoParameter());
        List<Erp004VoProduct> productList = response.getData();
        for (int i = 0; i < productList.size(); i++) {
            Erp004VoProduct stockProduct = productList.get(i);
            erp004p01CmbProduct.addItem(new DtoCombobox(stockProduct.getProductId(), stockProduct.getName(), stockProduct.getPriceBuy()));
        }
    }

    private void erp004p01GetWarehouse() throws SQLException {
        DtoResponse response = Erp004Service.getWarehouse(new DtoParameter());
        List<Erp004VoWarehouse> warehouseList = response.getData();
        for (int i = 0; i < warehouseList.size(); i++) {
            Erp004VoWarehouse warehouse = warehouseList.get(i);
            erp004p01CmbWarehouse.addItem(new DtoCombobox(warehouse.getWarehouseId(), warehouse.getName(), null));
        }
    }

    private void erp004p01SaveStock() {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("vStcId", erp004p01TxtStockProductId.getText());

        DtoCombobox selectedProduct = (DtoCombobox) erp004p01CmbProduct.getSelectedItem();
        String selectedProductId = selectedProduct.getValue();
        searchMap.put("vPrdId", selectedProductId);

        DtoCombobox selectedWarehouse = (DtoCombobox) erp004p01CmbWarehouse.getSelectedItem();
        String selectedWarehouseId = selectedWarehouse.getValue();
        searchMap.put("vWrhsId", selectedWarehouseId);

        int price = Integer.parseInt(erp004p01TxtPrice.getText());
        int quantity = Integer.parseInt(erp004p01TxtQuantity.getText());
        int totalPayment = Integer.parseInt(erp004p01TxtTotalPayment.getText());

        searchMap.put("vPrice", price);
        searchMap.put("vQuantity", quantity);
        searchMap.put("vTotalPayment", totalPayment);

        LocalDate dPaymentDate = LocalDate.now();
        searchMap.put("dPaymentDate", dPaymentDate);

        searchMap.put("vPaymentStatus", "PAID");
        param.setSearch(searchMap);
        Erp004Service.saveDataStockProduct(param);
        erp004p01Clear();
        try {
            erp004p01Search();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void erp004p01Clear() {
        erp004p01TxtStockProductId.setText("");
        erp004p01CmbProduct.setSelectedIndex(0);
        erp004p01CmbWarehouse.setSelectedIndex(0);
        erp004p01TxtPrice.setText("");
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
            Object[] obj = new Object[8];

            obj[0] = stockProduct.getStockProductId();
            obj[1] = stockProduct.getProductName();
            obj[2] = stockProduct.getWarehouseName();
            obj[3] = stockProduct.getPrice();
            obj[4] = stockProduct.getQuantity();
            obj[5] = stockProduct.getTotalPayment();
            obj[6] = stockProduct.getPaymentDate();
            obj[7] = stockProduct.getPaymentStatus();
            erp004p01TableModel.addRow(obj);
        }
        erp004p01TblStock.setModel(erp004p01TableModel);
    }

    private void erp004p01GetStockProductId() throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();

        DtoCombobox selectedWarehouse = (DtoCombobox) erp004p01CmbWarehouse.getSelectedItem();
        String selectedWarehouseId = selectedWarehouse.getValue();

        searchMap.put("WAREHOUSE", selectedWarehouseId);
        param.setSearch(searchMap);

        DtoResponse response = Erp004Service.getLastStockProductId(param);
        String lastId = (String) response.getData().get(0);
        erp004p01TxtStockProductId.setText(lastId);
    }

    private void erp004p01GetProductPrice() {
        DtoCombobox selectedProduct = (DtoCombobox) erp004p01CmbProduct.getSelectedItem();
        String selectedProductPrice = selectedProduct.getHelper();
        erp004p01TxtPrice.setText(selectedProductPrice);
    }

    private void erp004p01CalculatePayment() {
        int price = Integer.parseInt(erp004p01TxtPrice.getText());
        int quantity = Integer.parseInt(erp004p01TxtQuantity.getText());
        int totalPayment = price*quantity;

        erp004p01TxtTotalPayment.setText(String.valueOf(totalPayment));
    }
}
