package id.co.lba.mo.erp.app001.constant;

public class Erp001Constant {
    public final static String GET_DATA_CUSTOMER
            = "SELECT \n"
              + " VCSTID, \n"
              + " VNAME, \n"
              + " VEMAIL, \n"
              + " VWEBSITE, \n"
              + " VSECTOR, \n"
              + " CONVERT(VARCHAR, DBEGINEFF, 106) AS DBEGINEFF, \n"
              + " CONVERT(VARCHAR, DENDEFF, 106) AS DENDEFF \n"
              + "FROM [dbo].[LBAMOERP_MSTCUSTS]" +
              "WHERE \n" +
              "    (VCSTID LIKE '%@VSEARCH%') OR\n" +
              "    (VNAME LIKE '%@VSEARCH%') OR\n" +
              "    (VEMAIL LIKE '%@VSEARCH%') OR\n" +
              "    (VWEBSITE LIKE '%@VSEARCH%') OR\n" +
              "    (VSECTOR LIKE '%@VSEARCH%') OR\n" +
              "    (CONVERT(VARCHAR, DBEGINEFF, 106) LIKE '%@VSEARCH%') OR\n" +
              "    (CONVERT(VARCHAR, DENDEFF, 106) LIKE '%@VSEARCH%')";
}
