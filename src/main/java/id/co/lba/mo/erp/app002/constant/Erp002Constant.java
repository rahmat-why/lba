package id.co.lba.mo.erp.app002.constant;

public class Erp002Constant {
    public final static String GET_DATA_PRODUCT
            = "SELECT \n" +
              "  VPRDID,\n" +
              "  VNAME,\n" +
              "  CONVERT(VARCHAR(MAX), XIMG) AS XIMG,\n" +
              "  NWEIGHT,\n" +
              "  VUNIT,\n" +
              "  NPRBUY,\n" +
              "  NPRUNTDEL,\n" +
              "  VCREA,\n" +
              "  CONVERT(VARCHAR, DCREA, 106) AS DCREA,\n" +
              "  VMODI,\n" +
              "  CONVERT(VARCHAR, DMODI, 106) AS DMODI\n" +
              "FROM \n" +
              "  [dbo].[LBAMOERP_MSTPRODUCTS]\n" +
              "WHERE \n" +
              "  (VPRDID LIKE '%@VSEARCH%') OR\n" +
              "  (VNAME LIKE '%@VSEARCH%') OR\n" +
              "  (CONVERT(VARCHAR(MAX), XIMG) LIKE '%@VSEARCH%') OR\n" +
              "  (CONVERT(VARCHAR, NWEIGHT, 106) LIKE '%@VSEARCH%') OR\n" +
              "  (VUNIT LIKE '%@VSEARCH%') OR\n" +
              "  (CONVERT(VARCHAR, NPRBUY, 106) LIKE '%@VSEARCH%') OR\n" +
              "  (CONVERT(VARCHAR, NPRUNTDEL, 106) LIKE '%@VSEARCH%') OR\n" +
              "  (VCREA LIKE '%@VSEARCH%') OR\n" +
              "  (CONVERT(VARCHAR, DCREA, 106) LIKE '%@VSEARCH%') OR\n" +
              "  (VMODI LIKE '%@VSEARCH%') OR\n" +
              "  (CONVERT(VARCHAR, DMODI, 106) LIKE '%@VSEARCH%')";
}
