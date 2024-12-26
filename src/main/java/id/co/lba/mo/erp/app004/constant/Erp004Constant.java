package id.co.lba.mo.erp.app004.constant;

public class Erp004Constant {
    public final static String GET_DATA_STOCK_PRODUCTS =
            "SELECT \n" +
            " tx.VSTCPRDID, \n" +
            " tx.VWRHSID, \n" +
            " wh.VNAME AS VWRHSNAME, \n" +
            " tx.NQTY, \n" +
            " tx.NTPYMNTS, \n" +
            " CONVERT(VARCHAR, tx.DPYMNTDT, 106) AS DPYMNTDT, \n" +
            " tx.VPYMNTST \n" +
            "FROM \n" +
            " [dbo].[LBAMOERP_TXNSTCKPRDS] tx \n" +
            " INNER JOIN [dbo].[LBAMOERP_MSTWRHSES] wh ON tx.VWRHSID = wh.VWRHSID \n" +
            "WHERE \n" +
            " (tx.VSTCPRDID LIKE '%@VSEARCH%') OR \n" +
            " (tx.VWRHSID LIKE '%@VSEARCH%') OR \n" +
            " (wh.VNAME LIKE '%@VSEARCH%') OR \n" +
            " (CONVERT(VARCHAR, tx.NQTY) LIKE '%@VSEARCH%') OR \n" +
            " (CONVERT(VARCHAR, tx.NTPYMNTS) LIKE '%@VSEARCH%') OR \n" +
            " (CONVERT(VARCHAR, tx.DPYMNTDT, 106) LIKE '%@VSEARCH%') OR \n" +
            " (tx.VPYMNTST LIKE '%@VSEARCH%')";
}