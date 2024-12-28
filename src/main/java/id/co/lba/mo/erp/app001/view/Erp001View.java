package id.co.lba.mo.erp.app001.view;

import LBAJXLibrariesV1.dto.DtoCombobox;
import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import com.toedter.calendar.JDateChooser;
import id.co.lba.mo.erp.app001.service.Erp001Service;
import id.co.lba.mo.erp.app001.vo.Erp001VoCustomer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import LBAJXLibrariesV1.constant.Util;

public class Erp001View extends JFrame{
    private JFrame frame;
    private JTable erp001p01TblCustomer;
    private DefaultTableModel erp001p01TableModel;
    private JButton erp001p01BtnFilter;
    private JTextField erp001p01TxtIdCustomer;
    private JTextField erp001p01Search;
    private JComboBox erp001p01CmbSector;
    private JTextField erp001p01TxtEmail;
    private JTextField erp001p01TxtWebsite;
    private JTextField erp001p01TxtName;
    public JPanel erp001p01Panel;
    private JButton erp001p01BtnDelete;
    private JButton erp001p01BtnSave;
    private JButton erp001p01BtnClear;
    private JButton erp001p01BtnUpdate;
    private JTextField erp001p01TxtPhone1;
    private JTextField erp001p01TxtPhone2;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Erp001View window = new Erp001View();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Erp001View() throws SQLException {
        erp001Initialize();
        erp001Default();
        erp001AddColumn();
        erp001p01GetCustomer();
        erp001p01GetCustomerId();
        erp001p01GetComboboxSector();

        erp001p01BtnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to save the customer?",
                "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        erp001p01SaveCustomer();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        erp001p01TblCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = erp001p01TblCustomer.getSelectedRow();
                if (selectedRow == -1) {
                    return;
                }

                erp001p01TxtIdCustomer.setText((String) erp001p01TableModel.getValueAt(selectedRow, 0));
                erp001p01TxtName.setText((String) erp001p01TableModel.getValueAt(selectedRow, 1));
                erp001p01TxtPhone1.setText((String) erp001p01TableModel.getValueAt(selectedRow, 2));
                erp001p01TxtPhone2.setText((String) erp001p01TableModel.getValueAt(selectedRow, 3));
                erp001p01TxtEmail.setText((String) erp001p01TableModel.getValueAt(selectedRow, 4));
                erp001p01TxtWebsite.setText((String) erp001p01TableModel.getValueAt(selectedRow, 5));

                for (int x = 0; x < erp001p01CmbSector.getItemCount(); x++) {
                    Object item = erp001p01CmbSector.getItemAt(x);
                    if (((DtoCombobox) item).getDisplay().equals((String) erp001p01TableModel.getValueAt(selectedRow, 6))) {
                        erp001p01CmbSector.setSelectedItem(item);
                        break;
                    }
                }

                erp001p01BtnSave.setEnabled(false);
                erp001p01BtnUpdate.setEnabled(true);
                erp001p01BtnDelete.setEnabled(true);
            }
        });

        erp001p01BtnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp001p01Clear();
            }
        });
        erp001p01BtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    erp001p01DeleteCustomer();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        erp001p01BtnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    erp001p01UpdateCustomer();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        erp001p01BtnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    erp001p01Search();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void erp001Initialize() {

    }

    private void erp001p01GetCustomer() throws SQLException {
        DtoResponse response = Erp001Service.getDataCustomer(new DtoParameter());

        List<Erp001VoCustomer> customerList = response.getData();
        for (int i = 0; i < customerList.size(); i++) {
            Erp001VoCustomer customer = customerList.get(i);
            Object[] obj = new Object[7];
            obj[0] = customer.getCustomerId();
            obj[1] = customer.getName();
            obj[2] = customer.getPhone1();
            obj[3] = customer.getPhone2();
            obj[4] = customer.getEmail();
            obj[5] = customer.getWebsite();
            obj[6] = customer.getSector();
            erp001p01TableModel.addRow(obj);
        }
        erp001p01TblCustomer.setModel(erp001p01TableModel);
    }

    private void erp001p01SaveCustomer() throws SQLException {
        DtoParameter param = new DtoParameter();

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("vCstId", erp001p01TxtIdCustomer.getText());
        searchMap.put("vName", erp001p01TxtName.getText());
        searchMap.put("vEmail", erp001p01TxtEmail.getText());
        searchMap.put("vWebsite", erp001p01TxtWebsite.getText());

        DtoCombobox selectedSector = (DtoCombobox) erp001p01CmbSector.getSelectedItem();
        searchMap.put("vSector", selectedSector.getDisplay());

        searchMap.put("vPhone1", erp001p01TxtPhone1.getText());
        searchMap.put("vPhone2", erp001p01TxtPhone2.getText());

        param.setSearch(searchMap);

        Erp001Service.saveDataCustomer(param);

        erp001p01Search();

        erp001p01Clear();
    }

    private void erp001p01UpdateCustomer() throws SQLException {
        DtoParameter param = new DtoParameter();

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("vCstId", erp001p01TxtIdCustomer.getText());
        searchMap.put("vName", erp001p01TxtName.getText());
        searchMap.put("vEmail", erp001p01TxtEmail.getText());
        searchMap.put("vWebsite", erp001p01TxtWebsite.getText());

        DtoCombobox selectedSector = (DtoCombobox) erp001p01CmbSector.getSelectedItem();
        searchMap.put("vSector", selectedSector.getDisplay());

        searchMap.put("vPhone1", erp001p01TxtPhone1.getText());
        searchMap.put("vPhone2", erp001p01TxtPhone2.getText());

        param.setSearch(searchMap);

        Erp001Service.updateDataCustomer(param);

        erp001p01Search();

        erp001p01Clear();
    }

    private void erp001p01DeleteCustomer() throws SQLException {
        DtoParameter param = new DtoParameter();

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("vCstId", erp001p01TxtIdCustomer.getText());

        param.setSearch(searchMap);

        Erp001Service.deleteDataCustomer(param);

        erp001p01Search();

        erp001p01Clear();
    }

    private void erp001p01Search() throws SQLException {
        DtoParameter param = new DtoParameter();

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("VSEARCH", erp001p01Search.getText());

        param.setSearch(searchMap);

        erp001p01TableModel.setRowCount(0);

        DtoResponse response = Erp001Service.getDataCustomer(param);

        List<Erp001VoCustomer> customerList = response.getData();
        for (int i = 0; i < customerList.size(); i++) {
            Erp001VoCustomer customer = customerList.get(i);
            Object[] obj = new Object[7];
            obj[0] = customer.getCustomerId();
            obj[1] = customer.getName();
            obj[2] = customer.getPhone1();
            obj[3] = customer.getPhone2();
            obj[4] = customer.getEmail();
            obj[5] = customer.getWebsite();
            obj[6] = customer.getSector();
            erp001p01TableModel.addRow(obj);
        }
        erp001p01TblCustomer.setModel(erp001p01TableModel);
    }

    private void erp001AddColumn() {
        erp001p01TableModel = new DefaultTableModel();

        erp001p01TableModel.addColumn("Customer ID");
        erp001p01TableModel.addColumn("Name");
        erp001p01TableModel.addColumn("Phone 1");
        erp001p01TableModel.addColumn("Phone 2");
        erp001p01TableModel.addColumn("Email");
        erp001p01TableModel.addColumn("Website");
        erp001p01TableModel.addColumn("Sector");
    }

    private void erp001p01GetCustomerId() throws SQLException {
        DtoResponse response = Erp001Service.getLastCustomerId(new DtoParameter());
        String lastId = (String) response.getData().get(0);
        erp001p01TxtIdCustomer.setText(lastId);
    }

    private void erp001p01GetComboboxSector() throws SQLException {
        DtoResponse response = Erp001Service.getComboboxSector(new DtoParameter());
        List<DtoCombobox> comboboxSectorList = response.getData();
        for (int i = 0; i < comboboxSectorList.size(); i++) {
            DtoCombobox comboboxSector = comboboxSectorList.get(i);
            erp001p01CmbSector.addItem(new DtoCombobox(comboboxSector.getValue(), comboboxSector.getDisplay(), comboboxSector.getHelper()));
        }
    }

    private void erp001p01Clear() {
        erp001p01TxtName.setText("");
        erp001p01TxtPhone1.setText("");
        erp001p01TxtPhone2.setText("");
        erp001p01TxtEmail.setText("");
        erp001p01TxtWebsite.setText("");
        erp001p01CmbSector.setSelectedIndex(0);

        try {
            erp001p01GetCustomerId();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        erp001p01BtnSave.setEnabled(true);
        erp001p01BtnUpdate.setEnabled(false);
        erp001p01BtnDelete.setEnabled(false);
    }

    private void erp001Default() {
        erp001p01BtnUpdate.setEnabled(false);
        erp001p01BtnDelete.setEnabled(false);
    }
}
