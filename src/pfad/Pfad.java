package pfad;

/**
 *
 * Mit dieser Klasse legen wir den Dateipfad zur Datenbank fest.
 * In unseren DAO-Klassen brauchen wir dann den Pfad nicht jedesmal
 * anzugeben. Wir greifen dann auf diese Klasse zu.
 *
 */
public class Pfad {

	// fuer Derby
	// private final static String USER = "admin";
	// private final static String PASSWORD = "admin";
	// private static final String DBURL =
	// "jdbc:derby:C:\\database;create=true;user=admin;password=admin";
	//
	// fuer Access mdb

	private final static String USER = "";
	private final static String PASSWORD = "geheimnis";
	private final static String DATEI = "";
	private final static String DATEIREST = "C:\\Datenbanken\\" + "Betreuer.mdb";
	private final static String DBURL = "jdbc:ucanaccess://" + DATEI + DATEIREST;

	public static String getUrl() {

		return DBURL;

	}

	public static String getUser() {
		return USER;
	}

	public static String getPassword() {
		return PASSWORD;
	}
}
