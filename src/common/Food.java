package common;

/**
 * Clase de los �tems Comida, hereda de Item e ItemUnitPrice
 * @author Ricardo Soto Est�vez, uo265710
 */
public class Food extends ItemUnitPrice {

	/**
	 * Crea un objeto Comida
	 * @param code			c�digo de la comida
	 * @param denomination	nombre de la comida
	 * @param description	descripci�n de la comida
	 * @param price			precio de la comida
	 */
	public Food(String code, String denomination, String description, String price) {
		super(code, denomination, description, price);
	}

}