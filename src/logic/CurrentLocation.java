package logic;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Clase abstracta que se encarga de la gestión de la localización del programa
 * @author Ricardo Soto Estévez, uo265710
 */
public abstract class CurrentLocation {

	// Lista de localizaciones
	public static final Locale SPANISH = new Locale("es","ES");
	public static final Locale GALICIAN = new Locale("gl","ES");
	public static final Locale ENGLISH = new Locale("en","UK");
	
	// Localización actual
	private static Locale location = SPANISH;
	
	/**
	 * @return	la localización actual del usuario
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
	 * Modifica la cadena identificativa almacenada de la localización
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