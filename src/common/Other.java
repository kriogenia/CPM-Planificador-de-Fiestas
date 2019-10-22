package common;

/**
 * Clase de los �tems Otro, hereda de Item e ItemGroupPrice
 * @author Ricardo Soto Est�vez, uo265710
 */

public class Other extends ItemGroupPrice {

	/**
	 * Crea el objeto de tipo otro
	 * @param code				C�digo del objeto
	 * @param denomination		Nombre del objeto
	 * @param description		Descripci�n del objeto
	 * @param price				Precio del objeto
	 */
	public Other(String code, String denomination, String description, String price) {
		super(code, denomination, description, price);
	}

}