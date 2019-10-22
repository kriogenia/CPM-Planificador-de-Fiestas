package file_util;

import java.text.DateFormat;
import java.util.Date;

import logic.CurrentLocation;

/**
 * Procesa cadenas de texto para dar el formato adecuado
 * @author Ricardo Soto Est�vez, uo265710
 */
public abstract class Formatter {

	/**
	 * Lee una l�nea formateada con los datos de un �tem y devuelve una array con los par�metros separados
	 * @param line	L�nea a leer
	 * @return		Array de Strings con los datos de la l�nea le�da
	 */
	public static String[] readLine(String line) {
		return line.split("@");
	}

	/**
	 * Convierte un usuario y una contrase�a al formato del fichero de log-ins
	 * @param username	Nombre de usuario
	 * @param password	Contrase�a
	 * @return			L�nea formateada con ambos datos
	 */
	public static String formatUsername(String username, String password) {
		return (username+"@"+password);
	}
	
	/**
	 * Recibe una cantidad monetaria y la devuelve formateada a la localizaci�n
	 * @param amount	cantidad a formatear
	 * @return			cadena con la cantidad formateada
	 */
	public static String formatCurrency(double amount) {
		return CurrentLocation.getCurrencyFormat().format(amount);
	}

	/**
	 * Formatea la ruta del fichero de texto de salida
	 * @return
	 */
	public static String formatOutput(String nif, String date) {
		return nif + "-" + date; // + ".txt";
	}

	/**
	 * Formatea la fecha recibida y la devuelve formateada
	 * @param date	fecha a formatear	
	 * @return		fecha formateada
	 */
	public static String formatDate(Date date) {
		return DateFormat.getDateInstance(1, CurrentLocation.getLocale()).format(date);
	}
	
}