package chart;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import util.LocalDateAdapter;


@XmlRootElement
/*
Reihenfolge xml-Elemente festlegen
@XmlType(propOrder={"id", "persnr", "vorname", "nachname",
    "geschlecht", "strasse", "wohnort", "funktion",
    "rd", "rdname"})
*/
public class Betreuer {

private IntegerProperty id;
private IntegerProperty persnr;
    private StringProperty vorname;
    private StringProperty nachname;
    private StringProperty geschlecht;
    private StringProperty strasse;
    private StringProperty wohnort;
    private StringProperty funktion;
    private IntegerProperty rd;
    private StringProperty rdname;
    private final ObjectProperty<LocalDate> eintrittsdatum;
    private DoubleProperty gehalt;

	public Betreuer(Integer id, Integer persnr, String vorname,
			           String nachname, String geschlecht, String strasse,
			           String wohnort, String funktion, Integer rd, String rdname,
			           LocalDate eintrittsdatum, Double gehalt){
		super();
		   this.id = new SimpleIntegerProperty(id);
		   this.persnr = new SimpleIntegerProperty(persnr);
		   this.vorname = new SimpleStringProperty(vorname);
		   this.nachname = new SimpleStringProperty(nachname);
		   this.geschlecht = new SimpleStringProperty(geschlecht);
		   this.strasse = new SimpleStringProperty(strasse);
		   this.wohnort = new SimpleStringProperty(wohnort);
           this.funktion = new SimpleStringProperty(funktion);
           this.rd = new SimpleIntegerProperty(rd);
           this.rdname = new SimpleStringProperty(rdname);
           this.eintrittsdatum = new SimpleObjectProperty<LocalDate>(eintrittsdatum);
           this.gehalt = new SimpleDoubleProperty(gehalt);

	}

	public Betreuer(){
            this(0,0,null, null,null, null,null, null, 0, null, LocalDate.of(1900, 1, 1), 0.00);
	}


	//id festlegen
    public Integer getId(){
        return id.get();
    }
    public void setId(Integer id){
        this.id.set(id);
    }
    public IntegerProperty idProperty(){
        return id;
    }

    //Personalnummer festlegen
    public Integer getPersnr(){
    	return persnr.get();
    }
    public void setPersnr(Integer persnr){
    	this.persnr.set(persnr);
    }
    public IntegerProperty persnrProperty(){
    	return persnr;
    }

    //Vorname festlegen
    public String getVorname(){
    	return vorname.get();
    }
    public void setVorname(String vorname){
    	this.vorname.set(vorname);
    }
    public StringProperty vornameProperty(){
    	return vorname;
    }

    //Nachname festlegen
    public String getNachname(){
    	return nachname.get();
    }

    public void setNachname(String nachname){
    	this.nachname.set(nachname);
    }
    public StringProperty nachnameProperty(){
    	return nachname;
    }

    //Geschlecht festlegen w = weiblich m = maennlich
    public String getGeschlecht(){
    	return geschlecht.get();
    }

    public void setGeschlecht(String geschlecht){
    	this.geschlecht.set(geschlecht);
    }
    public StringProperty geschlechtProperty(){
    	return geschlecht;
    }

    //Strasse definieren Strassennamen und Hausnummer

    public String getStrasse(){
    	return strasse.get();
    }
    public void setStrasse(String strasse){
    	this.strasse.set(strasse);

    }
    public StringProperty strasseProperty(){
    	return strasse;
    }

    //Wohnort definieren Postleitzahl und Wohnortname

    public String getWohnort(){
    	return wohnort.get();
    }

    public void setWohnort(String wohnort){
    	this.wohnort.set(wohnort);
    }
    public StringProperty wohnortProperty(){
    	return wohnort;
    }

    //Funktion definieren

      public String getFunktion(){
    	return funktion.get();
    }

    public void setFunktion(String funktion){
    	this.funktion.set(funktion);
    }
    public StringProperty funktionProperty(){
    	return funktion;
    }

    //RD festlegen
      public Integer getRd(){
        return rd.get();
    }
    public void setRd(Integer rd){
        this.rd.set(rd);
    }
    public IntegerProperty rdProperty(){
        return rd;
    }

    // RDNAme
     public String getRdname(){
    	return rdname.get();
    }
    public void setRdname(String rdname){
    	this.rdname.set(rdname);
    }
    public StringProperty rdnameProperty(){
    	return rdname;
    }

    //Eintrittsdatum
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getEintrittsdatum() {
        return eintrittsdatum.get();
    }
    public void setEintrittsdatum(LocalDate eintrittsdatum) {
        this.eintrittsdatum.set(eintrittsdatum);
    }
    public ObjectProperty<LocalDate> eintrittsdatumProperty() {
        return eintrittsdatum;
    }

    //Gehalt
    public Double getGehalt(){
        return gehalt.get();
    }
    public void setGehalt(Double gehalt){
        this.gehalt.set(gehalt);
    }
    public DoubleProperty gehaltProperty(){
        return gehalt;
    }

	@Override
	public String toString() {
		return "Mitarbeiter [persnr=" + persnr.get() +
				", vorname=" + vorname.get() +
				", nachname=" + nachname.get() +
				", geschlecht=" + geschlecht.get()+
                ", eintritt=" + eintrittsdatum.get()+
                ", gehalt=" + gehalt.get()+
				"]";
	}



}
