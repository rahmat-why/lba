package id.co.lba.mo.erp.app005.dao;

import LBAJXLibrariesV1.connection.DBConnect;
import LBAJXLibrariesV1.dto.DtoParameter;
import id.co.lba.mo.erp.app005.constant.Erp005Constant;
import id.co.lba.mo.erp.app005.vo.Erp005VoDetailOrder;
import id.co.lba.mo.erp.app005.vo.Erp005VoOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Erp005ObjectDao {
    static DBConnect connection = new DBConnect();

    public static List<Erp005VoOrder> getOrder(DtoParameter dto) throws SQLException {
        List<Erp005VoOrder> orderList = new ArrayList<>();
        String query = Erp005Constant.GET_DATA_ORDER;
        query = query.replace("@VSEARCH", dto.getSearch() != null ? dto.getSearch().get("VSEARCH").toString() : "");
        System.out.println(query);
        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                while (resultSet.next()) {
                    Erp005VoOrder order = new Erp005VoOrder();
                    order.setBjNumber(resultSet.getString("VBJNUMBER"));
                    order.setCustomerId(resultSet.getString("VCSTID"));
                    order.setCustomerName(resultSet.getString("VCSTNAME"));
                    order.setOrderStatus(resultSet.getString("VORDST"));
                    order.setPaymentSystem(resultSet.getString("VPAYSYT"));
                    order.setDeliverySystem(resultSet.getString("VDELSYT"));
                    order.setSubtotal(resultSet.getString("NSUBTOTAL"));
                    order.setOrderDate(resultSet.getString("DORDERDT"));
                    order.setDescription(resultSet.getString("VDESC"));
                    order.setProductId(resultSet.getString("VPRDID"));
                    order.setProductName(resultSet.getString("VPRDNAME"));
                    order.setWarehouseId(resultSet.getString("VWRHSID"));
                    order.setWarehouseName(resultSet.getString("VWRHSNAME"));
                    order.setQuantity(resultSet.getString("NQTY"));
                    order.setUnit(resultSet.getString("VUNIT"));
                    order.setPrice(resultSet.getString("NPRICE"));
                    order.setTotalPayment(String.valueOf(resultSet.getInt("NQTY") * resultSet.getInt("NPRICE")));
                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return orderList;
    }

    public static List<Erp005VoDetailOrder> getDetailOrder(DtoParameter dto) throws SQLException {
        List<Erp005VoDetailOrder> detailOrderList = new ArrayList<>();
        String query = Erp005Constant.GET_DATA_DETAIL_ORDER;
        String bjNumber = dto.getSearch() != null ? dto.getSearch().get("vBjNumber").toString() : "";
        System.out.println(query);
        try (PreparedStatement pstat = connection.conn.prepareStatement(query)) {
            pstat.setString(1, bjNumber);
            try (ResultSet resultSet = pstat.executeQuery()) {
                while (resultSet.next()) {
                    Erp005VoDetailOrder detailOrder = new Erp005VoDetailOrder();
                    detailOrder.setBjNumber(resultSet.getString("VBJNUMBER"));
                    detailOrder.setProductId(resultSet.getString("VPRDID"));
                    detailOrder.setProductName(resultSet.getString("VPRDNAME"));
                    detailOrder.setWarehouseId(resultSet.getString("VWRHSID"));
                    detailOrder.setWarehouseName(resultSet.getString("VWRHSNAME"));
                    detailOrder.setQuantity(resultSet.getString("NQTY"));
                    detailOrder.setPrice(resultSet.getString("NPRICE"));
                    detailOrder.setCost(resultSet.getString("NCOST"));
                    detailOrderList.add(detailOrder);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return detailOrderList;
    }
}
