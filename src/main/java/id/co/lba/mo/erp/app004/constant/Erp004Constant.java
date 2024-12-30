package id.co.lba.mo.erp.app004.constant;

public class Erp004Constant {
    public final static String GET_DATA_RESTOCK =
            "SELECT \n" +
            "  tx.VRSTCKID, \n" +
            "  tx.VWRHSID, \n" +
            "  wh.VNAME AS VWRHSNAME, \n" +
            "  pd.VNAME AS VPRDNAME, \n" +
            "  tx.NQTY, \n" +
            "  tx.NTPYMNT, \n" +
            "  CONVERT(VARCHAR, tx.DPYMNTDT, 106) AS DPYMNTDT, \n" +
            "  tx.VPYMNTST, \n" +
            "  tx.NPRICE, \n" +
            "  CONVERT(VARCHAR, tx.DRSTCKDT, 106) AS DRSTCKDT \n" +
            "FROM \n" +
            "  [dbo].[LBAMOERP_TXNRSTCKS] tx \n" +
            "  INNER JOIN [dbo].[LBAMOERP_MSTWRHSES] wh ON tx.VWRHSID = wh.VWRHSID \n" +
            "  INNER JOIN [dbo].[LBAMOERP_MSTPRODUCTS] pd ON tx.VPRDID = pd.VPRDID \n" +
            "WHERE \n" +
            "  (tx.VRSTCKID LIKE '%@VSEARCH%') OR \n" +
            "  (tx.VWRHSID LIKE '%@VSEARCH%') OR \n" +
            "  (wh.VNAME LIKE '%@VSEARCH%') OR \n" +
            "  (pd.VNAME LIKE '%@VSEARCH%') OR \n" +
            "  (CONVERT(VARCHAR, tx.NQTY) LIKE '%@VSEARCH%') OR \n" +
            "  (CONVERT(VARCHAR, tx.NTPYMNT) LIKE '%@VSEARCH%') OR \n" +
            "  (CONVERT(VARCHAR, tx.DPYMNTDT, 106) LIKE '%@VSEARCH%') OR \n" +
            "  (tx.VPYMNTST LIKE '%@VSEARCH%')";

    public final static String GET_COMBOBOX_PRODUCTS =
            "SELECT VPRDID, VNAME, NPRBUY \n" +
            "FROM [dbo].[LBAMOERP_MSTPRODUCTS] \n" +
            "WHERE VSTATUS = 'ACTIVE'";

    public final static String GET_COMBOBOX_WAREHOUSES =
            "SELECT VWRHSID, VNAME \n" +
            "FROM [dbo].[LBAMOERP_MSTWRHSES] \n" +
            "WHERE VSTATUS = 'ACTIVE'";

    public final static String GET_LAST_STOCK_PRODUCT_ID =
            "SELECT TOP 1 VRSTCKID \n" +
            "FROM [dbo].[LBAMOERP_TXNRSTCKS] \n" +
            "WHERE VRSTCKID LIKE 'STC/'+'@WAREHOUSE/'+ CONVERT(VARCHAR, YEAR(GETDATE())) + '/' + FORMAT(GETDATE(), 'MM') + '/%' \n" +
            "ORDER BY VRSTCKID DESC";

    public final static String GET_DATA_TRANSFER_STOCKS = 
            "SELECT \n" +
            "  tx.VTRFSTCID, \n" +
            "  tx.VPRDID, \n" +
            "  pd.VNAME AS VPRDNAME, \n" +
            "  tx.VWRHSFROM, \n" +
            "  whf.VNAME AS VWRHSFROMNAME, \n" +
            "  tx.VWRHSTO, \n" +
            "  wht.VNAME AS VWRHSTONAME, \n" +
            "  tx.NQTY, \n" +
            "  tx.VDESC, \n" +
            "  tx.NTCOST, \n" +
            "  CONVERT(VARCHAR, tx.DTRFDT, 106) AS DTRFDT \n" +
            "FROM \n" +
            "  [dbo].[LBAMOERP_TXNTRFSTCKS] tx\n" +
            "  INNER JOIN [dbo].[LBAMOERP_MSTPRODUCTS] pd ON tx.VPRDID = pd.VPRDID \n" +
            "  INNER JOIN [dbo].[LBAMOERP_MSTWRHSES] whf ON tx.VWRHSFROM = whf.VWRHSID \n" +
            "  INNER JOIN [dbo].[LBAMOERP_MSTWRHSES] wht ON tx.VWRHSTO = wht.VWRHSID \n" +
            "WHERE \n" +
            "  (tx.VTRFSTCID LIKE '%@VSEARCH%') OR \n" +
            "  (tx.VPRDID LIKE '%@VSEARCH%') OR \n" +
            "  (pd.VNAME LIKE '%@VSEARCH%') OR \n" +
            "  (tx.VWRHSFROM LIKE '%@VSEARCH%') OR \n" +
            "  (whf.VNAME LIKE '%@VSEARCH%') OR \n" +
            "  (tx.VWRHSTO LIKE '%@VSEARCH%') OR \n" +
            "  (wht.VNAME LIKE '%@VSEARCH%') OR \n" +
            "  (CONVERT(VARCHAR, tx.NQTY) LIKE '%@VSEARCH%') OR \n" +
            "  (tx.VDESC LIKE '%@VSEARCH%') OR \n" +
            "  (CONVERT(VARCHAR, tx.NTCOST) LIKE '%@VSEARCH%')";

    public final static String GET_LAST_TRANSFER_STOCK_ID =
            "SELECT TOP 1 VTRFSTCID \n" +
            "FROM [dbo].[LBAMOERP_TXNTRFSTCKS] \n" +
            "WHERE VTRFSTCID LIKE 'TSC/'+'@WAREHOUSEFROM/'+ CONVERT(VARCHAR, YEAR(GETDATE())) + '/' + FORMAT(GETDATE(), 'MM') + '/%' \n" +
            "ORDER BY VTRFSTCID DESC";

    public final static String GET_INVENTORY_ID =
            "SELECT VINVTRID, NTSTCK FROM LBAMOERP_MSTINVTRS \n" +
            "WHERE VPRDID = ? AND VWRHID = ?";

    public final static String GET_DATA_INVENTORY =
            "SELECT \n" +
            "  inv.VINVTRID, \n" +
            "  inv.VPRDID, \n" +
            "  pd.VNAME AS VPRDNAME, \n" +
            "  inv.VWRHID, \n" +
            "  wh.VNAME AS VWRHNAME, \n" +
            "  inv.NTSTCK \n" +
            "FROM \n" +
            "  [dbo].[LBAMOERP_MSTINVTRS] inv \n" +
            "  INNER JOIN [dbo].[LBAMOERP_MSTPRODUCTS] pd ON inv.VPRDID = pd.VPRDID \n" +
            "  INNER JOIN [dbo].[LBAMOERP_MSTWRHSES] wh ON inv.VWRHID = wh.VWRHSID \n" +
            "WHERE \n" +
            "  (inv.VINVTRID LIKE '%@VSEARCH%') OR \n" +
            "  (inv.VPRDID LIKE '%@VSEARCH%') OR \n" +
            "  (pd.VNAME LIKE '%@VSEARCH%') OR \n" +
            "  (inv.VWRHID LIKE '%@VSEARCH%') OR \n" +
            "  (wh.VNAME LIKE '%@VSEARCH%') OR \n" +
            "  (CONVERT(VARCHAR, inv.NTSTCK) LIKE '%@VSEARCH%')";

    public final static String GET_LAST_INVENTORY_ID =
            "SELECT TOP 1 VINVTRID \n" +
            "FROM [dbo].[LBAMOERP_MSTINVTRS] \n" +
            "WHERE VINVTRID LIKE 'INV/'+'@WAREHOUSE'+'/%' \n" +
            "ORDER BY VINVTRID DESC";
}