package id.co.lba.mo.erp.app002.view;

import LBAJXLibrariesV1.dto.DtoCombobox;
import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
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
    public JPanel erp002p01Panel;
    private JComboBox erp002p01CmbUnit;
    private JTextField erp002p01TxtPriceBuy;
    private JTextField erp002p01TxtPriceUnitDelivery;
    private JTextField erp002p01TxtPriceUnitPickup;
    private DefaultTableModel erp002p01TableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Erp002View window = new Erp002View();
                window.frame.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                throw new RuntimeException(ex);
            }
        });
    }

    public Erp002View() throws SQLException {
        erp002Initialize();
        erp002Default();
        erp002AddColumn();
        erp002p01GetProduct();
        erp002p01GetProductId();
        erp002p01GetComboboxUnit();

        erp002p01BtnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp002p01Clear();
            }
        });
        erp002p01BtnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int validate = erp002Validate();
                if(validate == 0) {
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to save this data?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        erp002p01SaveProduct();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        erp002p01BtnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int validate = erp002Validate();
                if(validate == 0) {
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to update this data?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        erp002p01UpdateProduct();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        erp002p01BtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete this data?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        erp002p01DeleteProduct();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                        throw new RuntimeException(ex);
                    }
                }
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
                erp002p01TxtPriceUnitPickup.setText((String) erp002p01TableModel.getValueAt(selectedRow, 6));

                erp002p01BtnSave.setEnabled(false);
                erp002p01BtnUpdate.setEnabled(true);
                erp002p01BtnDelete.setEnabled(true);
            }
        });
        erp002p01BtnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    erp002p01Search();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void erp002Initialize() {

    }

    private void erp002p01GetProduct() throws SQLException {
        DtoResponse response = Erp002Service.getDataProduct(new DtoParameter());

        List<Erp002VoProduct> productList = response.getData();
        for (int i = 0; i < productList.size(); i++) {
            Erp002VoProduct product = productList.get(i);
            Object[] obj = new Object[7];
            obj[0] = product.getProductId();
            obj[1] = product.getName();
            obj[2] = product.getWeight();
            obj[3] = product.getUnit();
            obj[4] = product.getPriceBuy();
            obj[5] = product.getPriceUnitDelivery();
            obj[6] = product.getPriceUnitPrice();
            erp002p01TableModel.addRow(obj);
        }
        erp002p01TblProduct.setModel(erp002p01TableModel);
    }

    private void erp002p01SaveProduct() throws SQLException {
        DtoParameter param = new DtoParameter();

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("vPrdId", erp002p01TxtIdProduct.getText());
        searchMap.put("vName", erp002p01TxtName.getText());
        searchMap.put("xImg", "-");

        int weight = Integer.parseInt(erp002p01TxtWeight.getText());
        searchMap.put("nWeight", weight);

        DtoCombobox selectedSector = (DtoCombobox) erp002p01CmbUnit.getSelectedItem();
        searchMap.put("vUnit", selectedSector.getDisplay());

        int priceBuy = Integer.parseInt(erp002p01TxtPriceBuy.getText());
        searchMap.put("nprBuy", priceBuy);

        int priceUnitDelivery = Integer.parseInt(erp002p01TxtPriceUnitDelivery.getText());
        searchMap.put("nprUntDel", priceUnitDelivery);

        int priceUnitPickup = Integer.parseInt(erp002p01TxtPriceUnitPickup.getText());
        searchMap.put("nprUntPic", priceUnitPickup);

        param.setSearch(searchMap);

        DtoResponse response = Erp002Service.saveDataProduct(param);
        JOptionPane.showMessageDialog(null, response.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);

        if(!response.getStatus().equals("200")) {
            return;
        }

        erp002p01Search();

        erp002p01Clear();
    }

    private void erp002p01UpdateProduct() throws SQLException {
        DtoParameter param = new DtoParameter();

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("vPrdId", erp002p01TxtIdProduct.getText());
        searchMap.put("vName", erp002p01TxtName.getText());
        searchMap.put("xImg", "-");

        int weight = Integer.parseInt(erp002p01TxtWeight.getText());
        searchMap.put("nWeight", weight);

        DtoCombobox selectedSector = (DtoCombobox) erp002p01CmbUnit.getSelectedItem();
        searchMap.put("vUnit", selectedSector.getDisplay());

        int priceBuy = Integer.parseInt(erp002p01TxtPriceBuy.getText());
        searchMap.put("nprBuy", priceBuy);

        int priceUnitDelivery = Integer.parseInt(erp002p01TxtPriceUnitDelivery.getText());
        searchMap.put("nprUntDel", priceUnitDelivery);

        int priceUnitPickup = Integer.parseInt(erp002p01TxtPriceUnitPickup.getText());
        searchMap.put("nprUntPic", priceUnitPickup);

        param.setSearch(searchMap);

        DtoResponse response = Erp002Service.updateDataProduct(param);
        JOptionPane.showMessageDialog(null, response.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);

        if(!response.getStatus().equals("200")) {
            return;
        }

        erp002p01Search();

        erp002p01Clear();
    }

    private void erp002p01DeleteProduct() throws SQLException {
        DtoParameter param = new DtoParameter();

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("vPrdId", erp002p01TxtIdProduct.getText());

        param.setSearch(searchMap);

        DtoResponse response = Erp002Service.deleteDataProduct(param);
        JOptionPane.showMessageDialog(null, response.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);

        if(!response.getStatus().equals("200")) {
            return;
        }

        erp002p01Search();

        erp002p01Clear();
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
            obj[2] = product.getWeight();
            obj[3] = product.getUnit();
            obj[4] = product.getPriceBuy();
            obj[5] = product.getPriceUnitDelivery();
            obj[6] = product.getPriceUnitPrice();
            erp002p01TableModel.addRow(obj);
        }
        erp002p01TblProduct.setModel(erp002p01TableModel);
    }

    private void erp002AddColumn() {
        erp002p01TableModel = new DefaultTableModel();
        erp002p01TableModel.addColumn("ID Product");
        erp002p01TableModel.addColumn("Nama");
        erp002p01TableModel.addColumn("Berat Bersih");
        erp002p01TableModel.addColumn("Satuan");
        erp002p01TableModel.addColumn("Harga Beli");
        erp002p01TableModel.addColumn("Harga Satuan Antar");
        erp002p01TableModel.addColumn("Harga Satuan Jemput");
    }

    private void erp002p01Clear() {
        erp002p01TxtIdProduct.setText("");
        erp002p01TxtName.setText("");
        erp002p01TxtWeight.setText("");
        erp002p01CmbUnit.setSelectedIndex(0);
        erp002p01TxtPriceBuy.setText("");
        erp002p01TxtPriceUnitDelivery.setText("");
        erp002p01TxtPriceUnitPickup.setText("");

        erp002p01TxtIdProduct.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        erp002p01TxtName.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        erp002p01TxtWeight.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        erp002p01CmbUnit.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        erp002p01TxtPriceBuy.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        erp002p01TxtPriceUnitDelivery.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        erp002p01TxtPriceUnitPickup.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));

        try {
            erp002p01GetProductId();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            throw new RuntimeException(ex);
        }

        erp002p01BtnSave.setEnabled(true);
        erp002p01BtnUpdate.setEnabled(false);
        erp002p01BtnDelete.setEnabled(false);
    }

    private void erp002Default() {
        erp002p01BtnUpdate.setEnabled(false);
        erp002p01BtnDelete.setEnabled(false);
    }

    private void erp002p01GetProductId() throws SQLException {
        DtoResponse response = Erp002Service.getLastProductId(new DtoParameter());
        String lastId = (String) response.getData().get(0);
        erp002p01TxtIdProduct.setText(lastId);
    }

    private void erp002p01GetComboboxUnit() throws SQLException {
        DtoResponse response = Erp002Service.getComboboxUnit(new DtoParameter());
        List<DtoCombobox> comboboxUnitList = response.getData();
        for (int i = 0; i < comboboxUnitList.size(); i++) {
            DtoCombobox comboboxSector = comboboxUnitList.get(i);
            erp002p01CmbUnit.addItem(new DtoCombobox(comboboxSector.getValue(), comboboxSector.getDisplay(), comboboxSector.getHelper()));
        }
    }

    private int erp002Validate() {
        int valid = 1;

        if (erp002p01TxtIdProduct.getText().isEmpty()) {
            erp002p01TxtIdProduct.setBorder(BorderFactory.createLineBorder(Color.RED));
            valid = 0;
        } else {
            erp002p01TxtIdProduct.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }

        if (erp002p01TxtName.getText().isEmpty()) {
            erp002p01TxtName.setBorder(BorderFactory.createLineBorder(Color.RED));
            valid = 0;
        } else {
            erp002p01TxtName.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }

        if (erp002p01TxtWeight.getText().isEmpty()) {
            erp002p01TxtWeight.setBorder(BorderFactory.createLineBorder(Color.RED));
            valid = 0;
        } else {
            erp002p01TxtWeight.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }

        if (erp002p01CmbUnit.getSelectedIndex() < 0) {
            erp002p01CmbUnit.setBorder(BorderFactory.createLineBorder(Color.RED));
            valid = 0;
        } else {
            erp002p01CmbUnit.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }

        if (erp002p01TxtPriceBuy.getText().isEmpty()) {
            erp002p01TxtPriceBuy.setBorder(BorderFactory.createLineBorder(Color.RED));
            valid = 0;
        } else {
            erp002p01TxtPriceBuy.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }

        if (erp002p01TxtPriceUnitDelivery.getText().isEmpty()) {
            erp002p01TxtPriceUnitDelivery.setBorder(BorderFactory.createLineBorder(Color.RED));
            valid = 0;
        } else {
            erp002p01TxtPriceUnitDelivery.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }

        if (erp002p01TxtPriceUnitPickup.getText().isEmpty()) {
            erp002p01TxtPriceUnitPickup.setBorder(BorderFactory.createLineBorder(Color.RED));
            valid = 0;
        } else {
            erp002p01TxtPriceUnitPickup.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }

        return valid;
    }
}
