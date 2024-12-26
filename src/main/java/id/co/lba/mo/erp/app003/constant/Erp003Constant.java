package id.co.lba.mo.erp.app003.constant;

public class Erp003Constant {
    public final static String GET_DATA_WAREHOUSE =
            "SELECT \n" +
            " VWRHSID,\n" +
            " VNAME,\n" +
            " VADDRESS,\n" +
            " CONVERT(VARCHAR, NCAPACITY) AS NCAPACITY,\n" +
            " VCREA,\n" +
            " CONVERT(VARCHAR, DCREA, 106) AS DCREA,\n" +
            " VMODI,\n" +
            " CONVERT(VARCHAR, DMODI, 106) AS DMODI\n" +
            "FROM \n" +
            " [dbo].[LBAMOERP_MSTWRHSES]\n" +
            "WHERE \n" +
            " (VWRHSID LIKE '%@VSEARCH%') OR\n" +
            " (VNAME LIKE '%@VSEARCH%') OR\n" +
            " (VADDRESS LIKE '%@VSEARCH%') OR\n" +
            " (CONVERT(VARCHAR, NCAPACITY) LIKE '%@VSEARCH%') OR\n" +
            " (VCREA LIKE '%@VSEARCH%') OR\n" +
            " (CONVERT(VARCHAR, DCREA, 106) LIKE '%@VSEARCH%') OR\n" +
            " (VMODI LIKE '%@VSEARCH%') OR\n" +
            " (CONVERT(VARCHAR, DMODI, 106) LIKE '%@VSEARCH%')";
}