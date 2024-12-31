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
                "  ISNULL(tx.NSUBTOTAL, 0) AS NSUBTOTAL,\n" +
            "  ISNULL(tx.NTPAYMENT, 0) AS NTPAYMENT,\n" +
            "  ISNULL(tx.NTCOST, 0) AS NTCOST,\n" +
            "  ISNULL(tx.NFEE, 0) AS NFEE,\n" +
            "  CONVERT(VARCHAR, tx.DORDERDT, 106) AS DORDERDT,\n" +
            "  tx.VDESC,\n" +
            "  do.VPRDID,\n" +
            "  p.VNAME AS VPRDNAME,\n" +
            "  do.VWRHSID,\n" +
            "  w.VNAME AS VWRHSNAME,\n" +
            "  ISNULL(do.NQTY, 0) AS NQTY,\n" +
            "  ISNULL(do.NPRICE, 0) AS NPRICE,\n" +
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
            "  (tx.DORDERDT BETWEEN '@DSTARTDATE' AND '@DENDDATE') AND (\n" +
            "  tx.VBJNUMBER LIKE '%@VSEARCH%' OR\n" +
            "  cs.VCSTID LIKE '%@VSEARCH%' OR\n" +
            "  cs.VNAME LIKE '%@VSEARCH%' OR\n" +
            "  tx.VORDST LIKE '%@VSEARCH%' OR\n" +
            "  tx.VPAYSYT LIKE '%@VSEARCH%' OR\n" +
            "  tx.VDELSYT LIKE '%@VSEARCH%' OR\n" +
            "  CONVERT(VARCHAR, tx.NSUBTOTAL) LIKE '%@VSEARCH%' OR\n" +
            "  CONVERT(VARCHAR, tx.NTPAYMENT) LIKE '%@VSEARCH%' OR\n" +
            "  CONVERT(VARCHAR, tx.NTCOST) LIKE '%@VSEARCH%' OR\n" +
            "  CONVERT(VARCHAR, tx.NFEE) LIKE '%@VSEARCH%' OR\n" +
            "  CONVERT(VARCHAR, tx.DORDERDT, 106) LIKE '%@VSEARCH%' OR\n" +
            "  tx.VDESC LIKE '%@VSEARCH%' OR\n" +
            "  do.VPRDID LIKE '%@VSEARCH%' OR\n" +
            "  p.VNAME LIKE '%@VSEARCH%' OR\n" +
            "  do.VWRHSID LIKE '%@VSEARCH%' OR\n" +
            "  w.VNAME LIKE '%@VSEARCH%' OR\n" +
            "  CONVERT(VARCHAR, do.NQTY) LIKE '%@VSEARCH%' OR\n" +
            "  CONVERT(VARCHAR, do.NPRICE) LIKE '%@VSEARCH%' OR\n" +
            "  p.VUNIT LIKE '%@VSEARCH%')";

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

    public static final String GET_DATA_DASHBOARD_ORDER =
            "SELECT \n" +
            "  ISNULL(SUM(do.NQTY*do.NPRICE), 0) AS NPRICE,\n" +
            "  ISNULL(SUM(do.NQTY*do.NCOST), 0) AS NCOST\n" +
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
            "  (tx.DORDERDT BETWEEN '@DSTARTDATE' AND '@DENDDATE') AND (\n" +
            "  tx.VBJNUMBER LIKE '%@VSEARCH%' OR\n" +
            "  cs.VCSTID LIKE '%@VSEARCH%' OR\n" +
            "  cs.VNAME LIKE '%@VSEARCH%' OR\n" +
            "  tx.VORDST LIKE '%@VSEARCH%' OR\n" +
            "  tx.VPAYSYT LIKE '%@VSEARCH%' OR\n" +
            "  tx.VDELSYT LIKE '%@VSEARCH%' OR\n" +
            "  CONVERT(VARCHAR, tx.NSUBTOTAL) LIKE '%@VSEARCH%' OR\n" +
            "  CONVERT(VARCHAR, tx.NTPAYMENT) LIKE '%@VSEARCH%' OR\n" +
            "  CONVERT(VARCHAR, tx.NTCOST) LIKE '%@VSEARCH%' OR\n" +
            "  CONVERT(VARCHAR, tx.NFEE) LIKE '%@VSEARCH%' OR\n" +
            "  CONVERT(VARCHAR, tx.DORDERDT, 106) LIKE '%@VSEARCH%' OR\n" +
            "  tx.VDESC LIKE '%@VSEARCH%' OR\n" +
            "  do.VPRDID LIKE '%@VSEARCH%' OR\n" +
            "  p.VNAME LIKE '%@VSEARCH%' OR\n" +
            "  do.VWRHSID LIKE '%@VSEARCH%' OR\n" +
            "  w.VNAME LIKE '%@VSEARCH%' OR\n" +
            "  CONVERT(VARCHAR, do.NQTY) LIKE '%@VSEARCH%' OR\n" +
            "  CONVERT(VARCHAR, do.NPRICE) LIKE '%@VSEARCH%' OR\n" +
            "  p.VUNIT LIKE '%@VSEARCH%')";

    public static final String SUBJECT_REPORT_ORDER = "REPORT ORDER %s - %s LIJORA BERKAT ABADI";

    public static final String BODY_REPORT_ORDER =
            "Dear. %s,\n" +
            "\n" +
            "Berikut adalah laporan pesanan untuk periode %s hingga %s.\n" +
            "\n" +
            "Detail laporan terlampir dalam file %s.\n" +
            "\n" +
            "Terima kasih atas perhatian Anda.\n" +
            "\n" +
            "Hormat Saya,\n" +
            "%s\n" +
            "[This email is generated by system]\n" +
            "[PT. LIJORA BERKAT ABADI]";

    public static final String GET_DATA_RECEIVER_REPORT_ORDER =
            "SELECT VKEY AS VEMAIL, VVALUE AS VNAME FROM LBAMOERP_DTLSETTINGS\n" +
            "WHERE VHDRID = 'HDR003'";
}
