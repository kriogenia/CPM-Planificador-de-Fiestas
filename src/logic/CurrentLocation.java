package logic;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Clase abstracta que se encarga de la gesti�n de la localizaci�n del programa
 * @author Ricardo Soto Est�vez, uo265710
 */
public abstract class CurrentLocation {

	// Lista de localizaciones
	public static final Locale SPANISH = new Locale("es","ES");
	public static final Locale GALICIAN = new Locale("gl","ES");
	public static final Locale ENGLISH = new Locale("en","UK");
	
	// Localizaci�n actual
	private static Locale location = SPANISH;
	
	/**
	 * @return	la localizaci�n actual del usuario
	 */
	public static Locale getLocale() {
		return location;
	}
	
	/**
	 * @return	devuelve el formato de la moneda local
	 */
	public static NumberFormat getCurrencyFormat() {
		if (location.equals(ENGLISH))
			return NumberFormat.getCurrencyInstance(Locale.UK);
		else
			return NumberFormat.getCurrencyInstance(Locale.GERMANY);
	}
	
	/**
	 * Modifica la cadena identificativa almacenada de la localizaci�n
	 * @param newLocation	nueva cadena identificativa
	 */
	public static void setLocation(Locale newLocation) {
		if 		(newLocation.toString().contains("gl"))
			location = GALICIAN;
		else if (newLocation.toString().contains("en"))
			location = ENGLISH;
		else
			location = SPANISH;
	}
	
}