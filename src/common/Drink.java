package common;

/**
 * Clase de los ítems Bebida, hereda de Item e ItemUnitPrice
 * @author Ricardo Soto Estévez, uo265710
 */
public class Drink extends ItemUnitPrice {

	/**
	 * Crea el elemento bebida
	 * @param code				Código de la bebida
	 * @param denomination		Nombre de la bebida
	 * @param description		Descripción de la bebida
	 * @param price				Precio de la bebida
	 */
	public Drink(String code, String denomination, String description, String price) {
		super(code, denomination, description, price);
	}
	
}