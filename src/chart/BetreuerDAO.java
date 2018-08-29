package chart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JOptionPane;
import pfad.Pfad;
import chart.BigDezimal;

/**
 * Mit dieser Klasse stellen wir die Verbindung zur
 * Datenbank her
 */
public class BetreuerDAO {

    private Connection myConn;
    private static BetreuerDAO handler = null;
    private String dburl = Pfad.getUrl();
    private String user = Pfad.getUser();
    private String password = Pfad.getPassword();

    public static BetreuerDAO getInstance() throws Exception {
        if (handler == null) {
            handler = new BetreuerDAO();
        }
        return handler;
    }

    public BetreuerDAO() throws Exception {
        myConn = DriverManager.getConnection(dburl, user, password);
        //nur beim ersten Mal bei einrichten nutzen createConnection()
        //    createConnection();
    }


    private static void close(Connection myConn, Statement myStmt,
            ResultSet myRs) throws SQLException {

        if (myRs != null) {
            myRs.close();
        }
        if (myStmt != null) {
        }
        if (myConn != null) {
            myConn.close();
        }
    }


    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null, myStmt, myRs);
    }


    @SuppressWarnings("unused")
	private void close(Statement myStmt) throws SQLException {
        close(null, myStmt, null);
    }


    private Betreuer convertRowToDatabase(ResultSet myRs) throws SQLException {

        int id = myRs.getInt("ID");
        int persnr = myRs.getInt("PERSNR");
        String vorname = myRs.getString("VORNAME");
        String nachname = myRs.getString("NACHNAME");
        String strasse = myRs.getString("STRASSE");
        String wohnort = myRs.getString("WOHNORT");
        String funktion = myRs.getString("FUNKTION");
        int rd = myRs.getInt("RD");
        String rdname = myRs.getString("RDNAME");
        String geschlecht = myRs.getString("GESCHLECHT");
        String eintritt = myRs.getString("Eintritt");
        Double gehalt = myRs.getDouble("GEHALT");


        //In ACCESS MDB
        //Kommt aus slq so an = 1999-03-01 00:00:00.000000
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00.000000");

        //DERBY
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //Unterschiede je Datenbank oracle, mysql, sqLite, derby
        formatter = formatter.withLocale(Locale.GERMAN);
        LocalDate eintrittsdatum = LocalDate.parse(eintritt, formatter);


        Betreuer tempDaten = new Betreuer(id, persnr, vorname, nachname,
                geschlecht, strasse, wohnort, funktion, rd, rdname, eintrittsdatum, gehalt);

        /*
        System.out.println(id + ", " + persnr + ", " + vorname
                + ", " + nachname + ", " + strasse + ", " + wohnort
                + ", " + funktion + ", " + rd + ", " + rdname
                + ", " + geschlecht + ", " + eintritt+ ", " + gehalt);
		*/
        return tempDaten;

    }


    public List<Betreuer> getAllDaten() throws Exception {
        List<Betreuer> list = new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            //partner += "%";

            String sql = "SELECT * FROM BETREUER";

            myStmt = myConn.prepareStatement(sql);

            //myStmt.setInt(1, persNr);
            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                Betreuer tempDaten = convertRowToDatabase(myRs);
                list.add(tempDaten);
            }
            Thread.sleep(2);
            return list;
        } finally {
            close(myStmt, myRs);
        }

    }

    public int getAnzahlDatensaetze()throws Exception{
        int anzahl = 0;

          PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            //partner += "%";

            String sql = "SELECT Count(Betreuer.PERSNR) AS Anzahl FROM Betreuer";

            myStmt = myConn.prepareStatement(sql);

            //myStmt.setInt(1, persNr);
            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                int Stueck = myRs.getInt("Anzahl");
                anzahl += Stueck;

            }
            Thread.sleep(2);
            return anzahl;
        } finally {
            close(myStmt, myRs);
        }

    }

    public List<Betreuer> getPersNr(int persNr) throws Exception {
        List<Betreuer> list = new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            //partner += "%";

            String sql = "SELECT * FROM BETREUER WHERE PERSNR = ?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, persNr);
            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                Betreuer tempDaten = convertRowToDatabase(myRs);
                list.add(tempDaten);
            }
            Thread.sleep(2);
            return list;
        } finally {
            close(myStmt, myRs);
        }
    }


    public List<Betreuer> getPersonalName(String personalName) throws Exception {
        List<Betreuer> list = new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {

            String sql = "SELECT * from Betreuer where NACHNAME like ?";

            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, "%" + personalName + "%");
            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                Betreuer tempDaten = convertRowToDatabase(myRs);
                list.add(tempDaten);
            }
            Thread.sleep(1);
            return list;
        } finally {
            close(myStmt, myRs);
        }

    }

    /**
     * Mit dieser Methode berechnen wir das Durchschnittgehalt
     * jeder Abteilung. Summe Gehalt je Abteilung durch Anzahl der Mitarbeiter
     * dieser Abteilung
     *
     * @return  PieChart.Data
     * @throws SQLException by Fehler in der Datenbankanwendung
     */
    public ObservableList<PieChart.Data> getGehaltGraphStatistics() throws SQLException {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {

            String sql = "SELECT Betreuer.RDNAME, Sum(Betreuer.GEHALT) AS GEHALT, "+
            "Count(Betreuer.RDNAME) AS Anzahl, "+
            "Sum([Betreuer].[Gehalt])/Count([Betreuer].[RDNAME]) AS Schnitt "+
            "FROM Betreuer "+
            "GROUP BY Betreuer.RDNAME "+
            "ORDER BY Sum(Betreuer.GEHALT) DESC";

            myStmt = myConn.prepareStatement(sql);
            myRs = myStmt.executeQuery();

            while (myRs.next()) {
              String text = myRs.getString("RDNAME");
              Double gehalt = myRs.getDouble("Schnitt");
              	//	System.out.println(text + " " + BigDezimal.doubleToBigDecimalTwoDigit(gehalt));
              		//Nutzen unseres Util.BigDezimal wir runden auf 2 Nachkommastellen
                data.add(new PieChart.Data(text, BigDezimal.doubleToBigDecimalTwoDigit(gehalt)));
            }

            return data;
        } finally {
            close(myStmt, myRs);
        }


    }

    /**
     * Wir erzeugen eine BarChart.
     * Serie1 = Maennlich Serie2 = Weiblich
     * Wir bilden die Anzahl der Personen je Abteilung und je Geschlecht
     * @return
     * @throws SQLException
     */
    public ObservableList<XYChart.Series<String, Number>> getCountrySeries() throws SQLException {
    	XYChart.Series<String, Number> seriesA = new XYChart.Series<>();
    	  seriesA.setName("Maennlich");

    		XYChart.Series<String, Number> seriesB = new XYChart.Series<>();
      	  seriesB.setName("Weiblich");

          PreparedStatement myStmt = null;
          ResultSet myRs = null;

          try {
        	  //Maennlich Abfragen
              String sqlSerie = "SELECT Betreuer.GESCHLECHT, Betreuer.RDNAME, " +
        	  "Count(Betreuer.GESCHLECHT) AS Anzahl "+
              "FROM Betreuer " +
              "GROUP BY Betreuer.GESCHLECHT, Betreuer.RDNAME";

              myStmt = myConn.prepareStatement(sqlSerie);
              myRs = myStmt.executeQuery();


              while (myRs.next()) {
            	String geschlecht = myRs.getString("Geschlecht");
                String text = myRs.getString("RDNAME");
                int gehalt = myRs.getInt("Anzahl");
                	//	System.out.println(text + " " + BigDezimal.doubleToBigDecimalTwoDigit(gehalt));

                if(geschlecht.equals("M")){
                	seriesA.getData().add(new XYChart.Data<>(text, gehalt));
                }
                if(geschlecht.equals("W")){
                	seriesB.getData().add(new XYChart.Data<>(text, gehalt));
                }

              }

              ObservableList<XYChart.Series<String, Number>> data =
            		  FXCollections.<XYChart.Series<String, Number>>observableArrayList();
            		  data.addAll(seriesA, seriesB);
            		  return data;


          } finally {
              close(myStmt, myRs);
          }
    }
    void createConnection() {
        try {
            myConn = DriverManager.getConnection(dburl, user, password);
            JOptionPane.showMessageDialog(null, "Datenbankverbindung ok",
                    "Datenbank verbunden", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cant load database",
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public static void main(String[] args) throws Exception {
        BetreuerDAO dao = new BetreuerDAO();


    }
}
