package common;

/**
 * Clase de los ítems Otro, hereda de Item e ItemGroupPrice
 * @author Ricardo Soto Estévez, uo265710
 */

public class Other extends ItemGroupPrice {

	/**
	 * Crea el objeto de tipo otro
	 * @param code				Código del objeto
	 * @param denomination		Nombre del objeto
	 * @param description		Descripción del objeto
	 * @param price				Precio del objeto
	 */
	public Other(String code, String denomination, String description, String price) {
		super(code, denomination, description, price);
	}

}