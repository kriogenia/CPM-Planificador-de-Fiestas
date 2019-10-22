package common;

/**
 * Clase de los �tems Bebida, hereda de Item e ItemUnitPrice
 * @author Ricardo Soto Est�vez, uo265710
 */
public class Drink extends ItemUnitPrice {

	/**
	 * Crea el elemento bebida
	 * @param code				C�digo de la bebida
	 * @param denomination		Nombre de la bebida
	 * @param description		Descripci�n de la bebida
	 * @param price				Precio de la bebida
	 */
	public Drink(String code, String denomination, String description, String price) {
		super(code, denomination, description, price);
	}
	
}