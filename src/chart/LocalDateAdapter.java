package util;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter (for JAXB) to convert between the LocalDate and the ISO 8601 String
 * representation of the date such as '2012-12-03'.
 *
 * @author Marco Jakob
 *
 * DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MMM-dd");
 * DateTime dateTime = FORMATTER.parseDateTime("2005-nov-12"); LocalDate
 * localDate = dateTime.toLocalDate();
 *
 *
 * The Java 8 version (and later) of java.sql.Date has built in support for
 * LocalDate, including toLocalDate and valueOf(LocalDate). To convert from
 * LocalDate to java.sql.Date you can use
 *
 * java.sql.Date.valueOf( localDate );
 *
 * And to convert from java.sql.Date to LocalDate:
 *
 * sqlDate.toLocalDate();
 *
 *
 * Date now = new Date(); LocalDate current = now.toInstant()
 * .atZone(ZoneId.systemDefault()) // Specify the correct timezone
 * .toLocalDate();
 *
 *
 *
 * //Get SQL date instance 
 * java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
 *
 * //Get LocalDate from SQL date 
 * LocalDate localDate = sqlDate.toLocalDate();
 *
 * System.out.println( localDate ); //2018-07-15
 *
 * LocalDate localDate = LocalDate.now();
 * 
 * java.sql.Date sqlDate = java.sql.Date.valueOf( localDate );
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
