package id.co.lba.mo.erp.app004.constant;

public class Erp004Constant {
    public final static String GET_DATA_STOCK_PRODUCTS =
            "SELECT \n" +
            " tx.VSTCPRDID, \n" +
            " tx.VWRHSID, \n" +
            " wh.VNAME AS VWRHSNAME, \n" +
            " pd.VNAME AS VPRDNAME, \n" +
            " tx.NQTY, \n" +
            " tx.NTPYMNTS, \n" +
            " CONVERT(VARCHAR, tx.DPYMNTDT, 106) AS DPYMNTDT, \n" +
            " tx.VPYMNTST, \n" +
            " tx.NPRICE \n" +
            "FROM \n" +
            " [dbo].[LBAMOERP_TXNSTCKPRDS] tx \n" +
            " INNER JOIN [dbo].[LBAMOERP_MSTWRHSES] wh ON tx.VWRHSID = wh.VWRHSID \n" +
            " INNER JOIN [dbo].[LBAMOERP_MSTPRODUCTS] pd ON tx.VPRDID = pd.VPRDID \n" +
            "WHERE \n" +
            " (tx.VSTCPRDID LIKE '%@VSEARCH%') OR \n" +
            " (tx.VWRHSID LIKE '%@VSEARCH%') OR \n" +
            " (wh.VNAME LIKE '%@VSEARCH%') OR \n" +
            " (pd.VNAME LIKE '%@VSEARCH%') OR \n" +
            " (CONVERT(VARCHAR, tx.NQTY) LIKE '%@VSEARCH%') OR \n" +
            " (CONVERT(VARCHAR, tx.NTPYMNTS) LIKE '%@VSEARCH%') OR \n" +
            " (CONVERT(VARCHAR, tx.DPYMNTDT, 106) LIKE '%@VSEARCH%') OR \n" +
            " (tx.VPYMNTST LIKE '%@VSEARCH%')";

    public final static String GET_DATA_PRODUCTS =
            "SELECT VPRDID, VNAME, NPRBUY FROM [dbo].[LBAMOERP_MSTPRODUCTS]";

    public final static String GET_DATA_WAREHOUSES =
            "SELECT VWRHSID, VNAME FROM [dbo].[LBAMOERP_MSTWRHSES]";

    public final static String GET_LAST_STOCK_PRODUCT_ID =
            "SELECT TOP 1 VSTCPRDID \n" +
            "FROM [dbo].[LBAMOERP_TXNSTCKPRDS] \n" +
            "WHERE VSTCPRDID LIKE 'STC/'+'@WAREHOUSE/'+ CONVERT(VARCHAR, YEAR(GETDATE())) + '/' + FORMAT(GETDATE(), 'MM') + '/%' \n" +
            "ORDER BY VSTCPRDID DESC";
}