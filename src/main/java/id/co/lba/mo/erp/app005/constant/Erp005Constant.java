package id.co.lba.mo.erp.app005.constant;

public class Erp005Constant {
    public final static String GET_DATA_ORDER =
            "SELECT \n" +
            "  tx.VBJNUMBER,\n" +
            "  cs.VCSTID,\n" +
            "  cs.VNAME AS VCSTNAME,\n" +
            "  tx.VORDST,\n" +
            "  tx.VPAYSYT,\n" +
            "  tx.VDELSYT,\n" +
            "  tx.NSUBTOTAL,\n" +
            "  tx.NTPAYMENT,\n" +
            "  tx.NTCOST,\n" +
            "  tx.NFEE,\n" +
            "  CONVERT(VARCHAR, tx.DORDERDT, 106) AS DORDERDT,\n" +
            "  tx.VDESC,\n" +
            "  do.VPRDID,\n" +
            "  p.VNAME AS VPRDNAME,\n" +
            "  do.VWRHSID,\n" +
            "  w.VNAME AS VWRHSNAME,\n" +
            "  do.NQTY,\n" +
            "  do.NPRICE,\n" +
            "  p.VUNIT\n" +
            "FROM \n" +
            "  LBAMOERP_TXNORDERS tx\n" +
            "INNER JOIN \n" +
            "  LBAMOERP_MSTCUSTS cs ON tx.VCSTID = cs.VCSTID\n" +
            "INNER JOIN \n" +
            "  LBAMOERP_DTLORDERS do ON tx.VBJNUMBER = do.VBJNUMBER\n" +
            "INNER JOIN \n" +
            "  LBAMOERP_MSTPRODUCTS p ON do.VPRDID = p.VPRDID\n" +
            "INNER JOIN \n" +
            "  LBAMOERP_MSTWRHSES w ON do.VWRHSID = w.VWRHSID\n" +
            "WHERE \n" +
            "  tx.VBJNUMBER LIKE '%@SEARCH%' OR\n" +
            "  cs.VCSTID LIKE '%@SEARCH%' OR\n" +
            "  cs.VNAME LIKE '%@SEARCH%' OR\n" +
            "  tx.VORDST LIKE '%@SEARCH%' OR\n" +
            "  tx.VPAYSYT LIKE '%@SEARCH%' OR\n" +
            "  tx.VDELSYT LIKE '%@SEARCH%' OR\n" +
            "  CONVERT(VARCHAR, tx.NSUBTOTAL) LIKE '%@SEARCH%' OR\n" +
            "  CONVERT(VARCHAR, tx.NTPAYMENT) LIKE '%@SEARCH%' OR\n" +
            "  CONVERT(VARCHAR, tx.NTCOST) LIKE '%@SEARCH%' OR\n" +
            "  CONVERT(VARCHAR, tx.NFEE) LIKE '%@SEARCH%' OR\n" +
            "  CONVERT(VARCHAR, tx.DORDERDT, 106) LIKE '%@SEARCH%' OR\n" +
            "  tx.VDESC LIKE '%@SEARCH%' OR\n" +
            "  do.VPRDID LIKE '%@SEARCH%' OR\n" +
            "  p.VNAME LIKE '%@SEARCH%' OR\n" +
            "  do.VWRHSID LIKE '%@SEARCH%' OR\n" +
            "  w.VNAME LIKE '%@SEARCH%' OR\n" +
            "  CONVERT(VARCHAR, do.NQTY) LIKE '%@SEARCH%' OR\n" +
            "  CONVERT(VARCHAR, do.NPRICE) LIKE '%@SEARCH%' OR\n" +
            "  p.VUNIT LIKE '%@SEARCH%'";

    public static final String GET_DATA_DETAIL_ORDER =
            "SELECT \n" +
            "  ddo.VBJNUMBER,\n" +
            "  ddo.VPRDID,\n" +
            "  mp.VNAME AS VPRDNAME,\n" +
            "  ddo.VWRHSID,\n" +
            "  mw.VNAME AS VWRHSNAME,\n" +
            "  ddo.NQTY,\n" +
            "  ddo.NPRICE,\n" +
            "  ddo.NCOST\n" +
            "FROM \n" +
            "    LBAMOERP_DTLORDERS ddo\n" +
            "INNER JOIN \n" +
            "    LBAMOERP_MSTPRODUCTS mp ON ddo.VPRDID = mp.VPRDID\n" +
            "INNER JOIN \n" +
            "    LBAMOERP_MSTWRHSES mw ON ddo.VWRHSID = mw.VWRHSID\n" +
            "WHERE \n" +
            "    ddo.VBJNUMBER = ?";
}
