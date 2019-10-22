package common;

import file_util.Formatter;

/**
 * Superclase de los items con precio por unidad, hereda de la superclase Item
 * @author Ricardo Soto Estévez, uo265710
 */
public abstract class ItemUnitPrice extends Item {

	private double price;		// Precio del item, por unidad
	
	/**
	 * Constructor de un item con precio por unidad
	 * @param code			Código del ítem
	 * @param denomination	Denominación
	 * @param description	Descripción
	 * @param price			Precio del ítem, por unidad
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
	 * @return	el precio en formato String para añadir al nombre
	 */
	private String formattedPrice() {
		return " (" + Formatter.formatCurrency(price) + ")";
	}

}