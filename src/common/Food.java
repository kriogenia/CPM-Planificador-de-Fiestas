package common;

/**
 * Clase de los ítems Comida, hereda de Item e ItemUnitPrice
 * @author Ricardo Soto Estévez, uo265710
 */
public class Food extends ItemUnitPrice {

	/**
	 * Crea un objeto Comida
	 * @param code			código de la comida
	 * @param denomination	nombre de la comida
	 * @param description	descripción de la comida
	 * @param price			precio de la comida
	 */
	public Food(String code, String denomination, String description, String price) {
		super(code, denomination, description, price);
	}

}