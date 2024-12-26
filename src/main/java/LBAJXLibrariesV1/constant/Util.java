package LBAJXLibrariesV1.constant;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Util {
    public final static DateTimeFormatter UTL_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public final static SimpleDateFormat UTL_DATESTRING_FORMATTER = new SimpleDateFormat("dd MMM yyyy");
}
