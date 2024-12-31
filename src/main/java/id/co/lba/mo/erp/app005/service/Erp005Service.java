package id.co.lba.mo.erp.app005.service;

import LBAJXLibrariesV1.common.Email;
import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import id.co.lba.mo.erp.app005.constant.Erp005Constant;
import id.co.lba.mo.erp.app005.dao.Erp005ObjectDao;
import id.co.lba.mo.erp.app005.vo.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static LBAJXLibrariesV1.common.Util.UTL_DATESTRING_FORMATTER;

public class Erp005Service {
    public static DtoResponse getDataOrder(DtoParameter param) throws SQLException {
        List<Erp005VoOrder> dataOrder = Erp005ObjectDao.getOrder(param);
        return new DtoResponse("200", dataOrder, "Data successfully received");
    }

    public static DtoResponse getDataDetailOrder(DtoParameter param) throws SQLException {
        List<Erp005VoDetailOrder> dataDetailOrder = Erp005ObjectDao.getDetailOrder(param);
        return new DtoResponse("200", dataDetailOrder, "Data successfully received");
    }

    public static DtoResponse getDashboardOrder(DtoParameter param) throws SQLException {
        Erp005VoDashboardOrder dashboardOrder = Erp005ObjectDao.getDashboardOrder(param);
        return new DtoResponse("200", Collections.singletonList(dashboardOrder), "Data successfully received");
    }

    public static DtoResponse sendEmailReportOrder(DtoParameter param) throws MessagingException, SQLException, JRException, IOException {
        List<Erp005VoReceiverReportOrder> dataReceiver = Erp005ObjectDao.getReceiverReportOrder(param);
        String startDate = param.getSearch() != null ? param.getSearch().get("DSTARTDATEFORMAT").toString() : "";
        String endDate = param.getSearch() != null ? param.getSearch().get("DENDDATEFORMAT").toString() : "";
        String senderName = param.getSearch() != null ? param.getSearch().get("VSENDERNAME").toString() : "";

        List<Erp005VoReportOrder> reportData = new ArrayList<>();
        List<Erp005VoOrder> dataOrder = Erp005ObjectDao.getOrder(param);
        for (Erp005VoOrder order : dataOrder) {
            Erp005VoReportOrder reportOrder = new Erp005VoReportOrder();

            reportOrder.setVIDORD(order.getBjNumber());
            reportOrder.setVDATE(order.getOrderDate());
            reportOrder.setVDELSY(order.getDeliverySystem());
            reportOrder.setVCSTNM(order.getCustomerName());
            reportOrder.setVPRDNM(order.getProductName());
            reportOrder.setVQTY(order.getQuantity());
            reportOrder.setVUNIT(order.getUnit());
            reportOrder.setVPRICE(order.getPrice());
            reportOrder.setVTPYMN(order.getTotalPayment());
            reportOrder.setVTCOST(order.getTotalCost());
            reportOrder.setVPYMSY(order.getPaymentSystem());
            reportOrder.setVNOTES(order.getDescription());
            reportOrder.setVWRHNM(order.getWarehouseName());

            reportData.add(reportOrder);
        }

        String subject = String.format(Erp005Constant.SUBJECT_REPORT_ORDER, startDate, endDate);

        String filePath = generateReportOrder(reportData, startDate, endDate);
        System.out.println("filePath");
        System.out.println(filePath);

        for (int i = 0; i < dataReceiver.size(); i++) {
            Erp005VoReceiverReportOrder receiver = dataReceiver.get(i);
            String body = String.format(Erp005Constant.BODY_REPORT_ORDER, receiver.getName(), startDate, endDate, "Nama File.pdf", senderName);;

            Email email = new Email();
            email.sendEmailWithAttachment(receiver.getEmail(), subject, body, filePath);
        }

        return new DtoResponse("200", null, "Email send successfully");
    }

    private static String generateReportOrder(List<Erp005VoReportOrder> reportData,
                                              String startDate, String endDate) throws JRException {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("src/main/java/id/co/lba/mo/erp/app005/jasper/erp005JspReportOrder.jrxml");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("DSTART", startDate);
            parameters.put("DEND", endDate);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);
            System.out.println("Jumlah data: " + reportData.size());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            String filePath = "REPORT_ORDER_" + startDate + "_" + endDate + ".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);

            return filePath;
        } catch (JRException e) {
            System.out.println("Error generating report: " + e.getMessage());
            throw e;
        }
    }
}
