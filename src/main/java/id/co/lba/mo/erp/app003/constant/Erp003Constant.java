package id.co.lba.mo.erp.app003.constant;

public class Erp003Constant {
    public final static String GET_DATA_WAREHOUSE =
            "SELECT \n" +
            "  VWRHSID, \n" +
            "  VNAME, \n" +
            "  VADDRESS, \n" +
            "  NCAPACITY \n" +
            "FROM \n" +
            "  [dbo].[LBAMOERP_MSTWRHSES] \n" +
            "WHERE \n" +
            "  VSTATUS = 'ACTIVE' AND \n" +
            "  ((VWRHSID LIKE '%@VSEARCH%') OR \n" +
            "  (VNAME LIKE '%@VSEARCH%') OR \n" +
            "  (VADDRESS LIKE '%@VSEARCH%') OR \n" +
            "  (CONVERT(VARCHAR, NCAPACITY) LIKE '%@VSEARCH%')) \n";

    public final static String GET_LAST_WAREHOUSE_ID =
            "SELECT TOP 1 VWRHSID \n" +
            "FROM [dbo].[LBAMOERP_MSTWRHSES] \n" +
            "WHERE VWRHSID LIKE 'WH-%' \n" +
            "ORDER BY VWRHSID DESC";
}