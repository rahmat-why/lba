package id.co.lba.mo.erp.app004.view;

import LBAJXLibrariesV1.dto.DtoCombobox;
import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import com.toedter.calendar.JDateChooser;
import id.co.lba.mo.erp.app004.service.Erp004Service;
import id.co.lba.mo.erp.app004.vo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Erp004View extends JFrame{
    private JFrame frame;
    private JButton erp004p01BtnFilter;
    private JTextField erp004p01Search;
    private JTextField erp004p01TxtRestockId;
    private JTextField erp004p01TxtQuantity;
    private JTextField erp004p01TxtTotalPayment;
    private JTable erp004p01TblRestock;
    private JButton erp004p01BtnRestock;
    public JPanel erp004p01Panel;
    private JPanel erp004p01PanelRestock;
    private DefaultTableModel erp004p01TableModel;
    private DefaultTableModel erp004p02TableModel;
    private DefaultTableModel erp004p03TableModel;
    private JTabbedPane erp004p01Tab;
    private JPanel erp004p01PanelTransferStock;
    private JComboBox erp004p01CmbWarehouse;
    private JComboBox erp004p01CmbProduct;
    private JTextField erp004p01TxtPrice;
    private JTextField erp004p02TxtTransferStockId;
    private JTextField erp004p02QuantityFrom;
    private JTextField erp004p02QuantityTo;
    private JTextField erp004p02QuantityTransfer;
    private JTextField erp004p02TotalCost;
    private JButton erp004p02BtnFilter;
    private JTextField erp004p02Search;
    private JButton erp004p02BtnSaveTransferStock;
    private JTable erp004p02TblTransferStock;
    private JComboBox erp004p02CmbProduct;
    private JComboBox erp004p02CmbWarehouseFrom;
    private JComboBox erp004p02CmbWarehouseTo;
    private JTextField erp004p02Description;
    private JPanel erp004p01PanelInventory;
    private JComboBox erp004p03CmbWarehouse;
    private JComboBox erp004p03CmbProduct;
    private JTextField erp004p03TxtTotalStock;
    private JTextField erp004p03Search;
    private JButton erp004p03BtnFilter;
    private JTable erp004p03TblInventory;
    private JButton erp004p03BtnSaveInventory;
    private JTextField erp004p03TxtInventoryId;
    private JPanel erp004p02JdtTransferDate;
    private JPanel erp004p01JdtRestockDate;
    JDateChooser erp004p02TransferDate = new JDateChooser();
    JDateChooser erp004p01RestockDate = new JDateChooser();

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
        erp004p01AddColumn();
        erp004p01GetStock();
        erp004p01GetProduct();
        erp004p01GetWarehouse();
        erp004p01GetRestockId();
        erp004p01GetProductPrice();
        erp004p01BtnRestock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp004p01SaveRestock();
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
                    erp004p01GetRestockId();
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

        erp004p02AddColumn();
        erp004p02GetTransferStock();
        erp004p02GetProduct();
        erp004p02GetWarehouse();
        erp004p02GetTransferStockId();
        erp004p02GetTotalStockInventoryFrom();
        erp004p02GetTotalStockInventoryTo();
        erp004p02BtnSaveTransferStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp004p02SaveTransferStock();
            }
        });
        erp004p02CmbWarehouseFrom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp004p02GetTotalStockInventoryFrom();
            }
        });
        erp004p02CmbWarehouseTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp004p02GetTotalStockInventoryTo();
            }
        });
        erp004p02BtnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    erp004p02Search();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        erp004p02CmbProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp004p02GetTotalStockInventoryFrom();
                erp004p02GetTotalStockInventoryTo();
            }
        });

        erp004p03AddColumn();
        erp004p03GetInventory();
        erp004p03GetWarehouse();
        erp004p03GetProduct();
        erp004p03GetInventoryId();
        erp004p03BtnSaveInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    erp004p03SaveInventory();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        erp004p03BtnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    erp004p03Search();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void erp004Initialize() {
        erp004p01JdtRestockDate.add(erp004p01RestockDate);
        erp004p02JdtTransferDate.add(erp004p02TransferDate);
    }

    private void erp004p01AddColumn() {
        erp004p01TableModel = new DefaultTableModel();
        erp004p01TableModel.addColumn("Stock ID");
        erp004p01TableModel.addColumn("Product");
        erp004p01TableModel.addColumn("Warehouse");
        erp004p01TableModel.addColumn("Price");
        erp004p01TableModel.addColumn("Quantity");
        erp004p01TableModel.addColumn("Total Payment");
        erp004p01TableModel.addColumn("Restock Date");
        erp004p01TblRestock.setModel(erp004p01TableModel);
    }

    private void erp004p01GetStock() throws SQLException {
        DtoResponse response = Erp004Service.getDataRestock(new DtoParameter());
        List<Erp004VoRestock> restockList = response.getData();
        for (int i = 0; i < restockList.size(); i++) {
            Erp004VoRestock restock = restockList.get(i);
            Object[] obj = new Object[7];

            obj[0] = restock.getRestockId();
            obj[1] = restock.getProductName();
            obj[2] = restock.getWarehouseName();
            obj[3] = restock.getPrice();
            obj[4] = restock.getQuantity();
            obj[5] = restock.getTotalPayment();
            obj[6] = restock.getRestockDate();

            erp004p01TableModel.addRow(obj);
        }
        erp004p01TblRestock.setModel(erp004p01TableModel);
    }

    private void erp004p01GetProduct() throws SQLException {
        DtoResponse response = Erp004Service.getProduct(new DtoParameter());
        List<Erp004VoProduct> productList = response.getData();
        for (int i = 0; i < productList.size(); i++) {
            Erp004VoProduct product = productList.get(i);
            erp004p01CmbProduct.addItem(new DtoCombobox(product.getProductId(), product.getName(), product.getPriceBuy()));
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

    private void erp004p01SaveRestock() {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("vRstId", erp004p01TxtRestockId.getText());

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

        Date restockDate = erp004p01RestockDate.getDate();
        LocalDate localRestockDate = restockDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        searchMap.put("dRestockDate", localRestockDate);

        searchMap.put("vPaymentStatus", "PAID");
        param.setSearch(searchMap);
        Erp004Service.saveDataRestock(param);
        erp004p01Clear();
        try {
            erp004p01Search();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void erp004p01Clear() {
        erp004p01TxtRestockId.setText("");
        erp004p01CmbProduct.setSelectedIndex(0);
        erp004p01CmbWarehouse.setSelectedIndex(0);
        erp004p01TxtPrice.setText("");
        erp004p01TxtQuantity.setText("");
        erp004p01TxtTotalPayment.setText("");
        erp004p01RestockDate.setDate(null);
    }

    private void erp004p01Search() throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("VSEARCH", erp004p01Search.getText());
        param.setSearch(searchMap);
        erp004p01TableModel.setRowCount(0);
        DtoResponse response = Erp004Service.getDataRestock(param);
        List<Erp004VoRestock> restockList = response.getData();
        for (int i = 0; i < restockList.size(); i++) {
            Erp004VoRestock restock = restockList.get(i);
            Object[] obj = new Object[7];

            obj[0] = restock.getRestockId();
            obj[1] = restock.getProductName();
            obj[2] = restock.getWarehouseName();
            obj[3] = restock.getPrice();
            obj[4] = restock.getQuantity();
            obj[5] = restock.getTotalPayment();
            obj[6] = restock.getRestockDate();
            erp004p01TableModel.addRow(obj);
        }
        erp004p01TblRestock.setModel(erp004p01TableModel);
    }

    private void erp004p01GetRestockId() throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();

        DtoCombobox selectedWarehouse = (DtoCombobox) erp004p01CmbWarehouse.getSelectedItem();
        String selectedWarehouseId = selectedWarehouse.getValue();

        searchMap.put("WAREHOUSE", selectedWarehouseId);
        param.setSearch(searchMap);

        DtoResponse response = Erp004Service.getLastRestockId(param);
        String lastId = (String) response.getData().get(0);
        erp004p01TxtRestockId.setText(lastId);
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

    private void erp004p02AddColumn() {
        erp004p02TableModel = new DefaultTableModel();
        erp004p02TableModel.addColumn("ID Transfer");
        erp004p02TableModel.addColumn("Product");
        erp004p02TableModel.addColumn("Warehouse From");
        erp004p02TableModel.addColumn("Warehouse To");
        erp004p02TableModel.addColumn("Quantity");
        erp004p02TableModel.addColumn("Description");
        erp004p02TableModel.addColumn("Total Cost");
        erp004p02TableModel.addColumn("Transfer Date");
        erp004p02TblTransferStock.setModel(erp004p02TableModel);
    }

    private void erp004p02GetTransferStock() throws SQLException {
        DtoResponse response = Erp004Service.getDataTransferStock(new DtoParameter());
        List<Erp004VoTransferStock> transferStockList = response.getData();
        for (int i = 0; i < transferStockList.size(); i++) {
            Erp004VoTransferStock transferStock = transferStockList.get(i);
            Object[] obj = new Object[8];

            obj[0] = transferStock.getTransferStockId();
            obj[1] = transferStock.getProductName();
            obj[2] = transferStock.getWarehouseFromName();
            obj[3] = transferStock.getWarehouseToName();
            obj[4] = transferStock.getQuantity();
            obj[5] = transferStock.getDescription();
            obj[6] = transferStock.getTotalCost();
            obj[7] = transferStock.getTransferDate();
            erp004p02TableModel.addRow(obj);
        }
        erp004p02TblTransferStock.setModel(erp004p02TableModel);
    }

    private void erp004p02GetProduct() throws SQLException {
        DtoResponse response = Erp004Service.getProduct(new DtoParameter());
        List<Erp004VoProduct> productList = response.getData();
        for (int i = 0; i < productList.size(); i++) {
            Erp004VoProduct product = productList.get(i);
            erp004p02CmbProduct.addItem(new DtoCombobox(product.getProductId(), product.getName(), product.getPriceBuy()));
        }
    }

    private void erp004p02GetWarehouse() throws SQLException {
        DtoResponse response = Erp004Service.getWarehouse(new DtoParameter());
        List<Erp004VoWarehouse> warehouseList = response.getData();
        for (int i = 0; i < warehouseList.size(); i++) {
            Erp004VoWarehouse warehouse = warehouseList.get(i);
            erp004p02CmbWarehouseFrom.addItem(new DtoCombobox(warehouse.getWarehouseId(), warehouse.getName(), null));
            erp004p02CmbWarehouseTo.addItem(new DtoCombobox(warehouse.getWarehouseId(), warehouse.getName(), null));
        }
    }

    private void erp004p02GetTransferStockId() throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();

        DtoCombobox selectedWarehouse = (DtoCombobox) erp004p02CmbWarehouseFrom.getSelectedItem();
        String selectedWarehouseId = selectedWarehouse.getValue();

        searchMap.put("WAREHOUSEFROM", selectedWarehouseId);
        param.setSearch(searchMap);

        DtoResponse response = Erp004Service.getLastTransferStockId(param);
        String lastId = (String) response.getData().get(0);
        erp004p02TxtTransferStockId.setText(lastId);
    }

    private void erp004p02SaveTransferStock() {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("vTransferStockId", erp004p02TxtTransferStockId.getText());

        DtoCombobox selectedProduct = (DtoCombobox) erp004p02CmbProduct.getSelectedItem();
        String selectedProductId = selectedProduct.getValue();
        searchMap.put("vProductId", selectedProductId);

        DtoCombobox selectedWarehouseFrom = (DtoCombobox) erp004p02CmbWarehouseFrom.getSelectedItem();
        String selectedWarehouseFromId = selectedWarehouseFrom.getValue();
        searchMap.put("vWarehouseFrom", selectedWarehouseFromId);

        DtoCombobox selectedWarehouseTo = (DtoCombobox) erp004p02CmbWarehouseTo.getSelectedItem();
        String selectedWarehouseToId = selectedWarehouseTo.getValue();
        searchMap.put("vWarehouseTo", selectedWarehouseToId);

        int quantity = Integer.parseInt(erp004p02QuantityTransfer.getText());
        searchMap.put("nQuantity", quantity);

        int totalCost = Integer.parseInt(erp004p02TotalCost.getText());
        searchMap.put("nTotalCost", totalCost);

        searchMap.put("vDescription", erp004p02Description.getText());

        Date transferDate = erp004p02TransferDate.getDate();
        LocalDate localTransferDate = transferDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        searchMap.put("dTransferDate", localTransferDate);

        param.setSearch(searchMap);
        Erp004Service.saveDataTransferStock(param);

        erp004p02Clear();

        try {
            erp004p02Search();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void erp004p02Clear() {
        erp004p02TxtTransferStockId.setText("");
        erp004p02CmbProduct.setSelectedIndex(0);
        erp004p02CmbWarehouseFrom.setSelectedIndex(0);
        erp004p02CmbWarehouseTo.setSelectedIndex(0);

        try {
            erp004p02GetTransferStockId();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        erp004p02QuantityTransfer.setText("");
        erp004p02TotalCost.setText("");
        erp004p02Description.setText("");
        erp004p02TransferDate.setDate(null);

        erp004p02GetTotalStockInventoryFrom();
        erp004p02GetTotalStockInventoryTo();
    }

    private int erp004p02GetTotalStockInventory(String productId, String warehouseId) throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("PRODUCTID", productId);
        searchMap.put("WAREHOUSEID", warehouseId);
        param.setSearch(searchMap);

        DtoResponse response = Erp004Service.getTotalStockInventory(param);
        int totalStockInventory = (Integer) response.getData().get(0);
        return totalStockInventory;
    }

    private void erp004p02GetTotalStockInventoryFrom() {
        DtoCombobox selectedProduct = (DtoCombobox) erp004p02CmbProduct.getSelectedItem();
        String selectedProductId = selectedProduct.getValue();

        DtoCombobox selectedWarehouse = (DtoCombobox) erp004p02CmbWarehouseFrom.getSelectedItem();
        String selectedWarehouseId = selectedWarehouse.getValue();

        try {
            int totalStockInventory = erp004p02GetTotalStockInventory(selectedProductId, selectedWarehouseId);
            erp004p02QuantityFrom.setText(String.valueOf(totalStockInventory));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void erp004p02GetTotalStockInventoryTo() {
        DtoCombobox selectedProduct = (DtoCombobox) erp004p02CmbProduct.getSelectedItem();
        String selectedProductId = selectedProduct.getValue();

        DtoCombobox selectedWarehouse = (DtoCombobox) erp004p02CmbWarehouseTo.getSelectedItem();
        String selectedWarehouseId = selectedWarehouse.getValue();

        try {
            int totalStockInventory = erp004p02GetTotalStockInventory(selectedProductId, selectedWarehouseId);
            erp004p02QuantityTo.setText(String.valueOf(totalStockInventory));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void erp004p02Search() throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("VSEARCH", erp004p02Search.getText());
        param.setSearch(searchMap);
        erp004p02TableModel.setRowCount(0);
        DtoResponse response = Erp004Service.getDataTransferStock(param);
        List<Erp004VoTransferStock> transferStockList = response.getData();
        for (int i = 0; i < transferStockList.size(); i++) {
            Erp004VoTransferStock transferStock = transferStockList.get(i);
            Object[] obj = new Object[8];

            obj[0] = transferStock.getTransferStockId();
            obj[1] = transferStock.getProductName();
            obj[2] = transferStock.getWarehouseFromName();
            obj[3] = transferStock.getWarehouseToName();
            obj[4] = transferStock.getQuantity();
            obj[5] = transferStock.getDescription();
            obj[6] = transferStock.getTotalCost();
            obj[7] = transferStock.getTransferDate();

            erp004p02TableModel.addRow(obj);
        }
        erp004p02TblTransferStock.setModel(erp004p02TableModel);
    }

    private void erp004p03AddColumn() {
        erp004p03TableModel = new DefaultTableModel();
        erp004p03TableModel.addColumn("ID Inventory");
        erp004p03TableModel.addColumn("Warehouse");
        erp004p03TableModel.addColumn("Product");
        erp004p03TableModel.addColumn("Total Stock");
        erp004p03TblInventory.setModel(erp004p03TableModel);
    }

    private void erp004p03GetInventory() throws SQLException {
        DtoResponse response = Erp004Service.getDataInventory(new DtoParameter());
        List<Erp004VoInventory> inventoryList = response.getData();
        for (int i = 0; i < inventoryList.size(); i++) {
            Erp004VoInventory inventory = inventoryList.get(i);
            Object[] obj = new Object[4];

            obj[0] = inventory.getInventoryId();
            obj[1] = inventory.getWarehouseName();
            obj[2] = inventory.getProductName();
            obj[3] = inventory.getTotalStock();
            erp004p03TableModel.addRow(obj);
        }
        erp004p03TblInventory.setModel(erp004p03TableModel);
    }

    private void erp004p03SaveInventory() throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("vIvtId", erp004p03TxtInventoryId.getText());

        DtoCombobox selectedWarehouse = (DtoCombobox) erp004p03CmbWarehouse.getSelectedItem();
        String selectedWarehouseId = selectedWarehouse.getValue();
        searchMap.put("vWrhsId", selectedWarehouseId);

        DtoCombobox selectedProduct = (DtoCombobox) erp004p03CmbProduct.getSelectedItem();
        String selectedProductId = selectedProduct.getValue();
        searchMap.put("vPrdId", selectedProductId);

        searchMap.put("nTotalStock", 0);

        param.setSearch(searchMap);
        Erp004Service.saveDataInventory(param);

        erp004p03Clear();

        erp004p03GetInventoryId();

        try {
            erp004p03Search();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void erp004p03Clear() {
        erp004p03TxtInventoryId.setText("");
        erp004p03CmbWarehouse.setSelectedIndex(0);
        erp004p03CmbProduct.setSelectedIndex(0);
    }

    private void erp004p03Search() throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("VSEARCH", erp004p03Search.getText());
        param.setSearch(searchMap);
        erp004p03TableModel.setRowCount(0);
        DtoResponse response = Erp004Service.getDataInventory(param);
        List<Erp004VoInventory> inventoryList = response.getData();
        for (int i = 0; i < inventoryList.size(); i++) {
            Erp004VoInventory inventory = inventoryList.get(i);
            Object[] obj = new Object[4];

            obj[0] = inventory.getInventoryId();
            obj[1] = inventory.getWarehouseName();
            obj[2] = inventory.getProductName();
            obj[3] = inventory.getTotalStock();
            erp004p03TableModel.addRow(obj);
        }
        erp004p03TblInventory.setModel(erp004p03TableModel);
    }

    private void erp004p03GetProduct() throws SQLException {
        DtoResponse response = Erp004Service.getProduct(new DtoParameter());
        List<Erp004VoProduct> productList = response.getData();
        for (int i = 0; i < productList.size(); i++) {
            Erp004VoProduct product = productList.get(i);
            erp004p03CmbProduct.addItem(new DtoCombobox(product.getProductId(), product.getName(), product.getPriceBuy()));
        }
    }

    private void erp004p03GetWarehouse() throws SQLException {
        DtoResponse response = Erp004Service.getWarehouse(new DtoParameter());
        List<Erp004VoWarehouse> warehouseList = response.getData();
        for (int i = 0; i < warehouseList.size(); i++) {
            Erp004VoWarehouse warehouse = warehouseList.get(i);
            erp004p03CmbWarehouse.addItem(new DtoCombobox(warehouse.getWarehouseId(), warehouse.getName(), null));
        }
    }

    private void erp004p03GetInventoryId() throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();

        DtoCombobox selectedWarehouse = (DtoCombobox) erp004p03CmbWarehouse.getSelectedItem();
        String selectedWarehouseId = selectedWarehouse.getValue();

        searchMap.put("WAREHOUSE", selectedWarehouseId);
        param.setSearch(searchMap);

        DtoResponse response = Erp004Service.getLastInventoryId(param);
        String lastId = (String) response.getData().get(0);
        erp004p03TxtInventoryId.setText(lastId);
    }
}
