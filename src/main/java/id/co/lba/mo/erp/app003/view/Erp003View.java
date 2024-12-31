package id.co.lba.mo.erp.app003.view;

import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import id.co.lba.mo.erp.app003.service.Erp003Service;
import id.co.lba.mo.erp.app003.vo.Erp003VoWarehouse;
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

public class Erp003View extends JFrame {

    private JFrame frame;
    private JButton erp003p01BtnFilter;
    private JTextField erp003p01Search;
    private JTextField erp003p01TxtIdWarehouse;
    private JTextField erp003p01TxtAddress;
    private JTextField erp003p01TxtName;
    private JTextField erp003p01TxtCapacity;
    private JTable erp003p01TblWarehouse;
    private JButton erp003p01BtnDelete;
    private JButton erp003p01BtnUpdate;
    private JButton erp003p01BtnSave;
    private JButton erp003p01BtnClear;
    public JPanel erp003p01Panel;
    private DefaultTableModel erp003p01TableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Erp003View window = new Erp003View();
                window.frame.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                throw new RuntimeException(ex);
            }
        });
    }

    public Erp003View() throws SQLException {
        erp003Initialize();
        erp003AddColumn();
        erp003p01GetWarehouse();
        erp003p01GetWarehouseId();
        erp003Default();
        erp003p01BtnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp003p01Clear();
            }
        });
        erp003p01BtnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int validate = erp003Validate();
                if(validate == 0) {
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to save this data?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        erp003p01SaveWarehouse();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        erp003p01BtnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int validate = erp003Validate();
                if(validate == 0) {
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to update this data?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        erp003p01UpdateWarehouse();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        erp003p01BtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int validate = erp003Validate();
                if(validate == 0) {
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete this data?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        erp003p01DeleteWarehouse();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        erp003p01TblWarehouse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = erp003p01TblWarehouse.getSelectedRow();
                if (selectedRow == -1) {
                    return;
                }
                erp003p01TxtIdWarehouse.setText((String) erp003p01TableModel.getValueAt(selectedRow, 0));
                erp003p01TxtName.setText((String) erp003p01TableModel.getValueAt(selectedRow, 1));
                erp003p01TxtAddress.setText((String) erp003p01TableModel.getValueAt(selectedRow, 2));
                erp003p01TxtCapacity.setText((String) erp003p01TableModel.getValueAt(selectedRow, 3));

                erp003p01BtnSave.setEnabled(false);
                erp003p01BtnUpdate.setEnabled(true);
                erp003p01BtnDelete.setEnabled(true);
            }
        });
        erp003p01BtnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    erp003p01Search();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void erp003Initialize() {

    }

    private void erp003AddColumn() {
        erp003p01TableModel = new DefaultTableModel();
        erp003p01TableModel.addColumn("Warehouse ID");
        erp003p01TableModel.addColumn("Name");
        erp003p01TableModel.addColumn("Address");
        erp003p01TableModel.addColumn("Capacity");
        erp003p01TblWarehouse.setModel(erp003p01TableModel);
    }

    private void erp003p01GetWarehouse() throws SQLException {
        DtoResponse response = Erp003Service.getDataWarehouse(new DtoParameter());
        List<Erp003VoWarehouse> warehouseList = response.getData();
        for (int i = 0; i < warehouseList.size(); i++) {
            Erp003VoWarehouse warehouse = warehouseList.get(i);
            Object[] obj = new Object[4];

            obj[0] = warehouse.getWarehouseId();
            obj[1] = warehouse.getName();
            obj[2] = warehouse.getAddress();
            obj[3] = warehouse.getCapacity();
            erp003p01TableModel.addRow(obj);
        }
        erp003p01TblWarehouse.setModel(erp003p01TableModel);
    }

    private void erp003p01SaveWarehouse() throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("vWrhsId", erp003p01TxtIdWarehouse.getText());
        searchMap.put("vName", erp003p01TxtName.getText());
        searchMap.put("vAddress", erp003p01TxtAddress.getText());

        int capacity = Integer.parseInt(erp003p01TxtCapacity.getText());
        searchMap.put("nCapacity", capacity);

        param.setSearch(searchMap);

        DtoResponse response = Erp003Service.saveDataWarehouse(param);
        JOptionPane.showMessageDialog(null, response.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);

        if(!response.getStatus().equals("200")) {
            return;
        }

        erp003p01Search();

        erp003p01Clear();
    }

    private void erp003p01UpdateWarehouse() throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("vWrhsId", erp003p01TxtIdWarehouse.getText());
        searchMap.put("vName", erp003p01TxtName.getText());
        searchMap.put("vAddress", erp003p01TxtAddress.getText());

        int capacity = Integer.parseInt(erp003p01TxtCapacity.getText());
        searchMap.put("nCapacity", capacity);

        param.setSearch(searchMap);

        DtoResponse response = Erp003Service.updateDataWarehouse(param);
        JOptionPane.showMessageDialog(null, response.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);

        if(!response.getStatus().equals("200")) {
            return;
        }

        erp003p01Search();

        erp003p01Clear();
    }

    private void erp003p01DeleteWarehouse() throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("vWrhsId", erp003p01TxtIdWarehouse.getText());
        param.setSearch(searchMap);

        DtoResponse response = Erp003Service.deleteDataWarehouse(param);
        JOptionPane.showMessageDialog(null, response.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);

        if(!response.getStatus().equals("200")) {
            return;
        }

        erp003p01Search();

        erp003p01Clear();
    }

    private void erp003p01Clear() {
        erp003p01TxtIdWarehouse.setText("");
        erp003p01TxtName.setText("");
        erp003p01TxtAddress.setText("");
        erp003p01TxtCapacity.setText("");

        erp003p01TxtIdWarehouse.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        erp003p01TxtName.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        erp003p01TxtAddress.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        erp003p01TxtCapacity.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));

        try {
            erp003p01GetWarehouseId();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            throw new RuntimeException(ex);
        }

        erp003p01BtnSave.setEnabled(true);
        erp003p01BtnUpdate.setEnabled(false);
        erp003p01BtnDelete.setEnabled(false);
    }

    private void erp003p01Search() throws SQLException {
        DtoParameter param = new DtoParameter();

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("VSEARCH", erp003p01Search.getText());

        param.setSearch(searchMap);

        erp003p01TableModel.setRowCount(0);

        DtoResponse response = Erp003Service.getDataWarehouse(param);

        List<Erp003VoWarehouse> warehouseList = response.getData();
        for (int i = 0; i < warehouseList.size(); i++) {
            Erp003VoWarehouse warehouse = warehouseList.get(i);
            Object[] obj = new Object[4];
            obj[0] = warehouse.getWarehouseId();
            obj[1] = warehouse.getName();
            obj[2] = warehouse.getAddress();
            obj[3] = warehouse.getCapacity();
            erp003p01TableModel.addRow(obj);
        }
        erp003p01TblWarehouse.setModel(erp003p01TableModel);
    }

    private void erp003Default() {
        erp003p01BtnUpdate.setEnabled(false);
        erp003p01BtnDelete.setEnabled(false);
    }

    private void erp003p01GetWarehouseId() throws SQLException {
        DtoResponse response = Erp003Service.getLastWarehouseId(new DtoParameter());
        String lastId = (String) response.getData().get(0);
        erp003p01TxtIdWarehouse.setText(lastId);
    }

    private int erp003Validate() {
        int valid = 1;

        if (erp003p01TxtIdWarehouse.getText().isEmpty()) {
            erp003p01TxtIdWarehouse.setBorder(BorderFactory.createLineBorder(Color.RED));
            valid = 0;
        } else {
            erp003p01TxtIdWarehouse.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }

        if (erp003p01TxtName.getText().isEmpty()) {
            erp003p01TxtName.setBorder(BorderFactory.createLineBorder(Color.RED));
            valid = 0;
        } else {
            erp003p01TxtName.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }

        if (erp003p01TxtAddress.getText().isEmpty()) {
            erp003p01TxtAddress.setBorder(BorderFactory.createLineBorder(Color.RED));
            valid = 0;
        } else {
            erp003p01TxtAddress.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }

        if (erp003p01TxtCapacity.getText().isEmpty()) {
            erp003p01TxtCapacity.setBorder(BorderFactory.createLineBorder(Color.RED));
            valid = 0;
        } else {
            erp003p01TxtCapacity.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }

        return valid;
    }
}