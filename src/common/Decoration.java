package common;

/**
 * Clase de los ítems Decoración, hereda de Item e ItemGroupPrice
 * @author Ricardo Soto Estévez, uo265710
 */
public class Decoration extends ItemGroupPrice {

	/**
	 * Crea un elemento Decoración
	 * @param code			código de la decoración
	 * @param denomination	nombre de la decoración
	 * @param description	descripción de la decoración
	 * @param price			precio de la decoración
	 */
	public Decoration(String code, String denomination, String description, String price) {
		super(code, denomination, description, price);
	}

}