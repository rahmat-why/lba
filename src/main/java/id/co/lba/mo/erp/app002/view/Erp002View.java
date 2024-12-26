package id.co.lba.mo.erp.app002.view;

import LBAJXLibrariesV1.constant.Util;
import LBAJXLibrariesV1.dto.DtoCombobox;
import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import id.co.lba.mo.erp.app001.service.Erp001Service;
import id.co.lba.mo.erp.app001.view.Erp001View;
import id.co.lba.mo.erp.app001.vo.Erp001VoCustomer;
import id.co.lba.mo.erp.app002.service.Erp002Service;
import id.co.lba.mo.erp.app002.vo.Erp002VoProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Erp002View extends JFrame{
    private JFrame frame;
    private JButton erp002p01BtnFilter;
    private JTextField erp002p01Search;
    private JTextField erp002p01TxtIdProduct;
    private JTextField erp002p01TxtName;
    private JTextField erp002p01TxtWeight;
    private JTable erp002p01TblProduct;
    private JButton erp002p01BtnDelete;
    private JButton erp002p01BtnUpdate;
    private JButton erp002p01BtnSave;
    private JButton erp002p01BtnClear;
    private JPanel erp002p01Panel;
    private JComboBox erp002p01CmbUnit;
    private JTextField erp002p01TxtPriceBuy;
    private JTextField erp002p01TxtPriceUnitDelivery;
    private DefaultTableModel erp002p01TableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Erp002View window = new Erp002View();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Erp002View() throws SQLException {
        erp002Initialize();
        erp002AddColumn();
        erp002p01GetProduct();

        erp002p01CmbUnit.addItem(new DtoCombobox("MOERP001", "KG", "1"));
        erp002p01CmbUnit.addItem(new DtoCombobox("MOERP002", "GR", "2"));

        erp002p01BtnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp002p01TxtIdProduct.setText("");
                erp002p01TxtName.setText("");
                erp002p01TxtWeight.setText("");

                if (erp002p01CmbUnit.getItemCount() > 0) {
                    erp002p01CmbUnit.setSelectedIndex(0);
                }

                erp002p01TxtPriceBuy.setText("");
                erp002p01TxtPriceUnitDelivery.setText("");
            }
        });
        erp002p01BtnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to save the customer?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    erp002p01SaveProduct();
                }
            }
        });
        erp002p01BtnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp002p01UpdateProduct();
            }
        });
        erp002p01BtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp002p01DeleteProduct();
            }
        });
        erp002p01TblProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = erp002p01TblProduct.getSelectedRow();
                if (selectedRow == -1) {
                    return;
                }

                erp002p01TxtIdProduct.setText((String) erp002p01TableModel.getValueAt(selectedRow, 0));
                erp002p01TxtName.setText((String) erp002p01TableModel.getValueAt(selectedRow, 1));
                erp002p01TxtWeight.setText((String) erp002p01TableModel.getValueAt(selectedRow, 2));

                for (int x = 0; x < erp002p01CmbUnit.getItemCount(); x++) {
                    Object item = erp002p01CmbUnit.getItemAt(x);
                    if (((DtoCombobox) item).getDisplay().equals((String) erp002p01TableModel.getValueAt(selectedRow, 3))) {
                        erp002p01CmbUnit.setSelectedItem(item);
                        break;
                    }
                }

                erp002p01TxtPriceBuy.setText((String) erp002p01TableModel.getValueAt(selectedRow, 4));
                erp002p01TxtPriceUnitDelivery.setText((String) erp002p01TableModel.getValueAt(selectedRow, 5));
            }
        });
        erp002p01BtnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    erp002p01Search();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void erp002Initialize() {
        add(this.erp002p01Panel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void erp002p01GetProduct() throws SQLException {
        DtoResponse response = Erp002Service.getDataProduct(new DtoParameter());

        List<Erp002VoProduct> productList = response.getData();
        for (int i = 0; i < productList.size(); i++) {
            Erp002VoProduct product = productList.get(i);
            Object[] obj = new Object[7];
            obj[0] = product.getProductId();
            obj[1] = product.getName();
            obj[2] = "-";
            obj[3] = product.getWeight();
            obj[4] = product.getUnit();
            obj[5] = product.getPriceBuy();
            obj[6] = product.getPriceUnitDelivery();
            erp002p01TableModel.addRow(obj);
        }
        erp002p01TblProduct.setModel(erp002p01TableModel);
    }

    private void erp002p01SaveProduct() {
        DtoParameter param = new DtoParameter();

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("vPrdId", "RHMT3");
        searchMap.put("vName", erp002p01TxtName.getText());
        searchMap.put("xImg", "-");
        searchMap.put("nWeight", erp002p01TxtWeight.getText());

        DtoCombobox selectedSector = (DtoCombobox) erp002p01CmbUnit.getSelectedItem();
        searchMap.put("vUnit", selectedSector.getDisplay());

        searchMap.put("nprBuy", erp002p01TxtPriceBuy.getText());
        searchMap.put("nprUntDel", erp002p01TxtPriceUnitDelivery.getText());

        param.setSearch(searchMap);

        Erp002Service.saveDataProduct(param);
    }

    private void erp002p01UpdateProduct() {
        DtoParameter param = new DtoParameter();

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("vPrdId", erp002p01TxtIdProduct.getText());
        searchMap.put("vName", erp002p01TxtName.getText());
        searchMap.put("xImg", "-");
        searchMap.put("nWeight", erp002p01TxtWeight.getText());

        DtoCombobox selectedSector = (DtoCombobox) erp002p01CmbUnit.getSelectedItem();
        searchMap.put("vUnit", selectedSector.getDisplay());

        searchMap.put("nprBuy", erp002p01TxtPriceBuy.getText());
        searchMap.put("nprUntDel", erp002p01TxtPriceUnitDelivery.getText());

        param.setSearch(searchMap);

        Erp002Service.updateDataProduct(param);
    }

    private void erp002p01DeleteProduct() {
        DtoParameter param = new DtoParameter();

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("vPrdId", erp002p01TxtIdProduct.getText());

        param.setSearch(searchMap);

        Erp002Service.deleteDataProduct(param);
    }

    private void erp002p01Search() throws SQLException {
        DtoParameter param = new DtoParameter();

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("VSEARCH", erp002p01Search.getText());

        param.setSearch(searchMap);

        erp002p01TableModel.setRowCount(0);

        DtoResponse response = Erp002Service.getDataProduct(param);

        List<Erp002VoProduct> productList = response.getData();
        for (int i = 0; i < productList.size(); i++) {
            Erp002VoProduct product = productList.get(i);
            Object[] obj = new Object[7];
            obj[0] = product.getProductId();
            obj[1] = product.getName();
            obj[2] = "-";
            obj[3] = product.getWeight();
            obj[4] = product.getUnit();
            obj[5] = product.getPriceBuy();
            obj[6] = product.getPriceUnitDelivery();
            erp002p01TableModel.addRow(obj);
        }
        erp002p01TblProduct.setModel(erp002p01TableModel);
    }

    private void erp002AddColumn() {
        erp002p01TableModel = new DefaultTableModel();
        erp002p01TableModel.addColumn("Product ID");
        erp002p01TableModel.addColumn("Name");
        erp002p01TableModel.addColumn("Berat Bersih");
        erp002p01TableModel.addColumn("Satuan");
        erp002p01TableModel.addColumn("Harga Beli");
        erp002p01TableModel.addColumn("Harga Satuan Antar");
    }
}
