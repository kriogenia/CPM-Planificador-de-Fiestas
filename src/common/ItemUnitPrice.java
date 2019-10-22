package common;

import file_util.Formatter;

/**
 * Superclase de los items con precio por unidad, hereda de la superclase Item
 * @author Ricardo Soto Est�vez, uo265710
 */
public abstract class ItemUnitPrice extends Item {

	private double price;		// Precio del item, por unidad
	
	/**
	 * Constructor de un item con precio por unidad
	 * @param code			C�digo del �tem
	 * @param denomination	Denominaci�n
	 * @param description	Descripci�n
	 * @param price			Precio del �tem, por unidad
	 */
	public ItemUnitPrice(String code, String denomination, String description, String price) {
		super(code, denomination, description);
		this.price = Double.parseDouble(price);
	}

	/**
	 * @return Precio del item
	 */
	public double getPrice() {
		return price;
	}
	
	@Override
	public String totalPrice(int qty) {
		return Formatter.formatCurrency(price*qty);
	}
	
	@Override
	public String toString() {
		return super.toString() + formattedPrice();
	}
	
	/**
	 * @return	el precio en formato String para a�adir al nombre
	 */
	private String formattedPrice() {
		return " (" + Formatter.formatCurrency(price) + ")";
	}

}