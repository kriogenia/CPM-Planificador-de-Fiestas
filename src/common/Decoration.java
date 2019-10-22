package common;

/**
 * Clase de los �tems Decoraci�n, hereda de Item e ItemGroupPrice
 * @author Ricardo Soto Est�vez, uo265710
 */
public class Decoration extends ItemGroupPrice {

	/**
	 * Crea un elemento Decoraci�n
	 * @param code			c�digo de la decoraci�n
	 * @param denomination	nombre de la decoraci�n
	 * @param description	descripci�n de la decoraci�n
	 * @param price			precio de la decoraci�n
	 */
	public Decoration(String code, String denomination, String description, String price) {
		super(code, denomination, description, price);
	}

}