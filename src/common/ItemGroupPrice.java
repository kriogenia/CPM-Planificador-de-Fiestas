package common;

import file_util.Formatter;

/**
 * Superclase de los items con precio por grupo, hereda de la superclase Item
 * @author Ricardo Soto Est�vez, uo265710
 */
public abstract class ItemGroupPrice extends Item {
	
	private double price;	// Precio del item, por n�mero de asistentes

	/**
	 * Constructor de items con precio seg�n el n�mero de asistentes
	 * @param code			Codigo del item
	 * @param denomination	Denominaci�n
	 * @param description	Descripcion
	 * @param price			Precio del �tem, grupal
	 */
	public ItemGroupPrice(String code, String denomination, String description, String price) {
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
	public String toString() {
		return super.toString() + formattedPrice();
	}
	
	/**
	 * @return	el precio en formato String para a�adir al nombre
	 */
	private String formattedPrice() {
		return " (" + Formatter.formatCurrency(price) + ")";
	}

	@Override
	public String totalPrice(int atts) {
		int groups = (atts+9)/10;
		return Formatter.formatCurrency(price*groups);
	}
	
}