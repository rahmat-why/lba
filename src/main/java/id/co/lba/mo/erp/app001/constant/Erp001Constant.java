package id.co.lba.mo.erp.app001.constant;

public class Erp001Constant {
    public final static String GET_DATA_CUSTOMER =
            "SELECT \n" +
            "  VCSTID, \n" +
            "  VNAME, \n" +
            "  VEMAIL, \n" +
            "  VWEBSITE, \n" +
            "  VSECTOR, \n" +
            "  VPHONE1, \n" +
            "  VPHONE2, \n" +
            "  VSTATUS \n" +
            "FROM \n" +
            "  [dbo].[LBAMOERP_MSTCUSTS]\n" +
            "WHERE  \n" +
            "  VSTATUS = 'ACTIVE' AND \n" +
            "  ((VCSTID LIKE '%@VSEARCH%') OR \n" +
            "  (VNAME LIKE '%@VSEARCH%') OR \n" +
            "  (VEMAIL LIKE '%@VSEARCH%') OR \n" +
            "  (VWEBSITE LIKE '%@VSEARCH%') OR \n" +
            "  (VSECTOR LIKE '%@VSEARCH%') OR \n" +
            "  (VPHONE1 LIKE '%@VSEARCH%') OR \n" +
            "  (VPHONE2 LIKE '%@VSEARCH%') OR \n" +
            "  (VSTATUS LIKE '%@VSEARCH%'))";

    public final static String GET_LAST_CUSTOMER_ID =
            "SELECT TOP 1 VCSTID \n" +
            "FROM [dbo].[LBAMOERP_MSTCUSTS] \n" +
            "WHERE VCSTID LIKE 'LBA-%' \n" +
            "ORDER BY VCSTID DESC";

    public final static String GET_COMBOBOX_SECTOR =
            "SELECT VKEY, VVALUE, VHELPER FROM [dbo].[LBAMOERP_DTLSETTINGS] \n" +
            "WHERE VHDRID = 'HDR001' \n" +
            "ORDER BY VDTLID ASC";
}
