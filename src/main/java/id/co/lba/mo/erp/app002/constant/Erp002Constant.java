package id.co.lba.mo.erp.app002.constant;

public class Erp002Constant {
    public final static String GET_DATA_PRODUCT
            = "SELECT \n" +
              "  VPRDID, \n" +
              "  VNAME, \n" +
              "  CONVERT(VARCHAR(MAX), XIMG) AS XIMG, \n" +
              "  NWEIGHT, \n" +
              "  VUNIT, \n" +
              "  NPRBUY, \n" +
              "  NPRUNTDEL, \n" +
              "  NPRUNTPIC, \n" +
              "  VSTATUS \n" +
              "FROM \n" +
              "  [dbo].[LBAMOERP_MSTPRODUCTS] \n" +
              "WHERE \n" +
              "  VSTATUS = 'ACTIVE' AND \n" +
              "  ((VPRDID LIKE '%@VSEARCH%') OR \n" +
              "  (VNAME LIKE '%@VSEARCH%') OR \n" +
              "  (CONVERT(VARCHAR(MAX), XIMG) LIKE '%@VSEARCH%') OR \n" +
              "  (CONVERT(VARCHAR, NWEIGHT, 106) LIKE '%@VSEARCH%') OR \n" +
              "  (VUNIT LIKE '%@VSEARCH%') OR \n" +
              "  (CONVERT(VARCHAR, NPRBUY, 106) LIKE '%@VSEARCH%') OR \n" +
              "  (CONVERT(VARCHAR, NPRUNTDEL, 106) LIKE '%@VSEARCH%'))";

    public final static String GET_LAST_PRODUCT_ID =
            "SELECT TOP 1 VPRDID \n" +
            "FROM [dbo].[LBAMOERP_MSTPRODUCTS] \n" +
            "WHERE VPRDID LIKE 'PD-%' \n" +
            "ORDER BY VPRDID DESC";

    public final static String GET_COMBOBOX_UNIT =
            "SELECT VKEY, VVALUE, VHELPER FROM [dbo].[LBAMOERP_DTLSETTINGS] \n" +
            "WHERE VHDRID = 'HDR002' \n" +
            "ORDER BY VDTLID ASC";
}
