package chart;

import java.math.BigDecimal;

/**
 *
 * Mit dieser Klasse verabreiten wir Double nach BigDezimal
 * um zum Beispiel auf 2 Nachkommastellen zu Runden
 */
public class BigDezimal {

    public static void main(String[] args) {
 //       Double zahl = 1145.2735698;
/*
 BigDecimal petrol = new BigDecimal(zahl).multiply( new BigDecimal(45.36589) );
 System.out.println( petrol.setScale( 3, BigDecimal.ROUND_HALF_UP ) );
 System.out.println( petrol.setScale( 2, BigDecimal.ROUND_HALF_UP ) );
 System.out.println( petrol.setScale( 1, BigDecimal.ROUND_HALF_UP ) );
 */
        System.out.println(doubleToBigDecimalOneDigit(145.3657896));
    }

    public static Double doubleToBigDecimalOneDigit(Double wert){
    	BigDecimal petrol = new BigDecimal(wert);
    //	petrol.setScale( 1, BigDecimal.ROUND_HALF_UP );

    	Double ergebnis = petrol.setScale( 1, BigDecimal.ROUND_HALF_UP ).doubleValue();
    	return ergebnis;
    }

    public static Double doubleToBigDecimalTwoDigit(Double wert){
    	BigDecimal petrol = new BigDecimal(wert);
    //	petrol.setScale( 2, BigDecimal.ROUND_HALF_UP );

    	Double ergebnis = petrol.setScale( 2, BigDecimal.ROUND_HALF_UP ).doubleValue();
    	return ergebnis;
    }

}
