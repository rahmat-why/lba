package id.co.lba.mo.erp.app005.view;

import LBAJXLibrariesV1.common.Email;
import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import com.toedter.calendar.JDateChooser;
import id.co.lba.mo.erp.app005.service.Erp005Service;
import id.co.lba.mo.erp.app005.vo.Erp005VoDashboardOrder;
import id.co.lba.mo.erp.app005.vo.Erp005VoOrder;
import net.sf.jasperreports.engine.JRException;

import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;

import static LBAJXLibrariesV1.common.Util.UTL_DATESTRING_FORMATTER;
import static LBAJXLibrariesV1.common.Util.UTL_Rupiah;

public class Erp005View extends JFrame{
    private JFrame frame;
    private JButton erp005p01BtnFilter;
    private JTextField erp005p01Search;
    private JTable erp005p01TblOrder;
    public JPanel erp005p01Panel;
    private JLabel erp005p01LblTotalOrder;
    private JLabel erp005p01LblTotalCost;
    private JTabbedPane erp004p01Tab;
    private JButton erp005p01BtnsendReportEmail;
    private DefaultTableModel erp005p01TableModel;
    private JPanel erp005p01JdtDateStart;
    private JPanel erp005p01JdtDateEnd;
    JDateChooser erp005p01StartDate = new JDateChooser();
    JDateChooser erp005p01EndDate = new JDateChooser();

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
        erp005p01Search();
        erp005p01GetDashboardOrder();
        erp005p01BtnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    erp005p01Search();
                    erp005p01GetDashboardOrder();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        erp005p01BtnsendReportEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date startDate = erp005p01StartDate.getDate();
                String startDateFormatted = UTL_DATESTRING_FORMATTER.format(startDate);

                Date endDate = erp005p01EndDate.getDate();
                String endDateFormatted = UTL_DATESTRING_FORMATTER.format(endDate);

                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to send report order ("+startDateFormatted+" - "+endDateFormatted+")?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        erp005p01SendEmailReportOrder();
                    } catch (MessagingException | SQLException | JRException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    private void erp005Initialize() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = calendar.getTime();

        erp005p01StartDate.setDate(startDate);
        erp005p01EndDate.setDate(endDate);

        erp005p01JdtDateStart.add(erp005p01StartDate);
        erp005p01JdtDateEnd.add(erp005p01EndDate);
    }

    private void erp005AddColumn() {
        erp005p01TableModel = new DefaultTableModel();
        erp005p01TableModel.addColumn("ID Order");
        erp005p01TableModel.addColumn("Date");
        erp005p01TableModel.addColumn("Delivery System");
        erp005p01TableModel.addColumn("Customer Name");
        erp005p01TableModel.addColumn("Product Name");
        erp005p01TableModel.addColumn("Quantity");
        erp005p01TableModel.addColumn("Unit");
        erp005p01TableModel.addColumn("Price");
        erp005p01TableModel.addColumn("Total Transaction");
        erp005p01TableModel.addColumn("Total Cost");
        erp005p01TableModel.addColumn("Payment System");
        erp005p01TableModel.addColumn("Notes");
        erp005p01TableModel.addColumn("Warehouse");
        erp005p01TblOrder.setModel(erp005p01TableModel);
    }

    private void erp005p01GetDashboardOrder() throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();

        Date startDate = erp005p01StartDate.getDate();
        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        searchMap.put("DSTARTDATE", localStartDate);

        Date endDate = erp005p01EndDate.getDate();
        LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        searchMap.put("DENDDATE", localEndDate);

        searchMap.put("VSEARCH", erp005p01Search.getText());
        param.setSearch(searchMap);
        DtoResponse response = Erp005Service.getDashboardOrder(param);
        Erp005VoDashboardOrder dashboardOrder = (Erp005VoDashboardOrder) response.getData().get(0);
        erp005p01LblTotalOrder.setText(UTL_Rupiah(Double.parseDouble(dashboardOrder.getTotalOrder())));
        erp005p01LblTotalCost.setText(UTL_Rupiah(Double.parseDouble(dashboardOrder.getTotalCost())));
    }

    private void erp005p01Search() throws SQLException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();

        Date startDate = erp005p01StartDate.getDate();
        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        searchMap.put("DSTARTDATE", localStartDate);

        Date endDate = erp005p01EndDate.getDate();
        LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        searchMap.put("DENDDATE", localEndDate);

        searchMap.put("VSEARCH", erp005p01Search.getText());
        param.setSearch(searchMap);
        erp005p01TableModel.setRowCount(0);
        DtoResponse response = Erp005Service.getDataOrder(param);
        List<Erp005VoOrder> orderList = response.getData();
        for (int i = 0; i < orderList.size(); i++) {
            Erp005VoOrder order = orderList.get(i);
            Object[] obj = new Object[13];

            obj[0] = order.getBjNumber();
            obj[1] = order.getOrderDate();
            obj[2] = order.getDeliverySystem();
            obj[3] = order.getCustomerName();
            obj[4] = order.getProductName();
            obj[5] = order.getQuantity();
            obj[6] = order.getUnit();
            obj[7] = UTL_Rupiah(Double.parseDouble(order.getPrice()));
            obj[8] = UTL_Rupiah(Double.parseDouble(order.getTotalPayment()));
            obj[9] = UTL_Rupiah(Double.parseDouble(order.getTotalCost()));
            obj[10] = order.getPaymentSystem();
            obj[11] = order.getDescription();
            obj[12] = order.getWarehouseName();
            erp005p01TableModel.addRow(obj);
        }
        erp005p01TblOrder.setModel(erp005p01TableModel);
    }

    private void erp005p01SendEmailReportOrder() throws MessagingException, SQLException, JRException, IOException {
        DtoParameter param = new DtoParameter();
        Map<String, Object> searchMap = new HashMap<>();

        Date startDate = erp005p01StartDate.getDate();
        String startDateFormatted = UTL_DATESTRING_FORMATTER.format(startDate);

        Date endDate = erp005p01EndDate.getDate();
        String endDateFormatted = UTL_DATESTRING_FORMATTER.format(endDate);

        searchMap.put("DSTARTDATEFORMAT", startDateFormatted);
        searchMap.put("DENDDATEFORMAT", endDateFormatted);
        searchMap.put("VSENDERNAME", "Sender Name");

        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        searchMap.put("DSTARTDATE", localStartDate);

        LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        searchMap.put("DENDDATE", localEndDate);

        searchMap.put("VSEARCH", erp005p01Search.getText());

        param.setSearch(searchMap);

        DtoResponse response = Erp005Service.sendEmailReportOrder(param);
        JOptionPane.showMessageDialog(null, response.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
