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
    private JPanel erp001p01Panel;
    private JButton erp001p01BtnDelete;
    private JButton erp001p01BtnSave;
    private JButton erp001p01BtnClear;
    private JPanel erp001p01JpnBeginEff;
    private JPanel erp001p01JpnEndEff;
    private JButton erp001p01BtnUpdate;

    JDateChooser erp001JdcBeginEff = new JDateChooser();
    JDateChooser erp001JdcEndEff = new JDateChooser();

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

        erp001AddColumn();
        erp001p01GetCustomer();

        erp001p01JpnBeginEff.add(erp001JdcBeginEff);
        erp001p01JpnEndEff.add(erp001JdcEndEff);

        erp001p01CmbSector.addItem(new DtoCombobox("MOERP001", "MANUFACTURE", "1"));
        erp001p01CmbSector.addItem(new DtoCombobox("MOERP002", "RETAIL", "2"));

        erp001p01BtnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to save the customer?",
                "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    erp001p01SaveCustomer();
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
                erp001p01TxtEmail.setText((String) erp001p01TableModel.getValueAt(selectedRow, 2));
                erp001p01TxtWebsite.setText((String) erp001p01TableModel.getValueAt(selectedRow, 3));

                for (int x = 0; x < erp001p01CmbSector.getItemCount(); x++) {
                    Object item = erp001p01CmbSector.getItemAt(x);
                    if (((DtoCombobox) item).getDisplay().equals((String) erp001p01TableModel.getValueAt(selectedRow, 4))) {
                        erp001p01CmbSector.setSelectedItem(item);
                        break;
                    }
                }

                try {
                    Date beginEff = Util.UTL_DATESTRING_FORMATTER.parse(erp001p01TableModel.getValueAt(selectedRow, 5).toString());
                    erp001JdcBeginEff.setDate(beginEff);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    Date endEff = Util.UTL_DATESTRING_FORMATTER.parse(erp001p01TableModel.getValueAt(selectedRow, 6).toString());
                    erp001JdcEndEff.setDate(endEff);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        erp001p01BtnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp001p01TxtName.setText("");
                erp001p01TxtEmail.setText("");
                erp001p01TxtWebsite.setText("");

                if (erp001p01CmbSector.getItemCount() > 0) {
                    erp001p01CmbSector.setSelectedIndex(0);
                }

                erp001JdcBeginEff.setDate(null);
                erp001JdcEndEff.setDate(null);
            }
        });
        erp001p01BtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp001p01DeleteCustomer();
            }
        });
        erp001p01BtnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp001p01UpdateCustomer();
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
        add(this.erp001p01Panel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void erp001p01GetCustomer() throws SQLException {
        DtoResponse response = Erp001Service.getDataCustomer(new DtoParameter());

        List<Erp001VoCustomer> customerList = response.getData();
        for (int i = 0; i < customerList.size(); i++) {
            Erp001VoCustomer customer = customerList.get(i);
            Object[] obj = new Object[7];
            obj[0] = customer.getCustomerId();
            obj[1] = customer.getName();
            obj[2] = customer.getEmail();
            obj[3] = customer.getWebsite();
            obj[4] = customer.getSector();
            obj[5] = customer.getBeginEffective();
            obj[6] = customer.getEndEffective();
            erp001p01TableModel.addRow(obj);
        }
        erp001p01TblCustomer.setModel(erp001p01TableModel);
    }

    private void erp001p01SaveCustomer() {
        DtoParameter param = new DtoParameter();

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("vCstId", "RHMT3");
        searchMap.put("vName", erp001p01TxtName.getText());
        searchMap.put("vEmail", erp001p01TxtEmail.getText());
        searchMap.put("vWebsite", erp001p01TxtWebsite.getText());

        DtoCombobox selectedSector = (DtoCombobox) erp001p01CmbSector.getSelectedItem();
        searchMap.put("vSector", selectedSector.getDisplay());

        Date erp001p01DateBeginEff = erp001JdcBeginEff.getDate();
        LocalDate beginDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(erp001p01DateBeginEff), Util.UTL_DATETIME_FORMATTER);
        searchMap.put("dBeginEff", beginDate);

        Date erp001p01DateEndEff = erp001JdcEndEff.getDate();
        LocalDate endDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(erp001p01DateEndEff), Util.UTL_DATETIME_FORMATTER);
        searchMap.put("dEndEff", endDate);

        param.setSearch(searchMap);

        Erp001Service.saveDataCustomer(param);
    }

    private void erp001p01UpdateCustomer() {
        DtoParameter param = new DtoParameter();

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("vCstId", erp001p01TxtIdCustomer.getText());
        searchMap.put("vName", erp001p01TxtName.getText());
        searchMap.put("vEmail", erp001p01TxtEmail.getText());
        searchMap.put("vWebsite", erp001p01TxtWebsite.getText());

        DtoCombobox selectedSector = (DtoCombobox) erp001p01CmbSector.getSelectedItem();
        searchMap.put("vSector", selectedSector.getDisplay());

        Date erp001p01DateBeginEff = erp001JdcBeginEff.getDate();
        LocalDate beginDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(erp001p01DateBeginEff), Util.UTL_DATETIME_FORMATTER);
        searchMap.put("dBeginEff", beginDate);

        Date erp001p01DateEndEff = erp001JdcEndEff.getDate();
        LocalDate endDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(erp001p01DateEndEff), Util.UTL_DATETIME_FORMATTER);
        searchMap.put("dEndEff", endDate);

        param.setSearch(searchMap);

        Erp001Service.updateDataCustomer(param);
    }

    private void erp001p01DeleteCustomer() {
        DtoParameter param = new DtoParameter();

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("vCstId", erp001p01TxtIdCustomer.getText());

        param.setSearch(searchMap);

        Erp001Service.deleteDataCustomer(param);
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
            obj[2] = customer.getEmail();
            obj[3] = customer.getWebsite();
            obj[4] = customer.getSector();
            obj[5] = customer.getBeginEffective();
            obj[6] = customer.getEndEffective();
            erp001p01TableModel.addRow(obj);
        }
        erp001p01TblCustomer.setModel(erp001p01TableModel);
    }

    private void erp001AddColumn() {
        erp001p01TableModel = new DefaultTableModel();

        erp001p01TableModel.addColumn("Customer ID");
        erp001p01TableModel.addColumn("Name");
        erp001p01TableModel.addColumn("Email");
        erp001p01TableModel.addColumn("Website");
        erp001p01TableModel.addColumn("Sector");
        erp001p01TableModel.addColumn("Begin Effective");
        erp001p01TableModel.addColumn("End Effective");
    }
}
