package LBAJXLibrariesV1.common;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Util {
    public final static DateTimeFormatter UTL_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public final static SimpleDateFormat UTL_DATESTRING_FORMATTER = new SimpleDateFormat("dd MMM yyyy");

    public static String UTL_Rupiah(double value) {
        Locale locale = new Locale("id", "ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(value).replace("Rp", "Rp. ");
    }

}
