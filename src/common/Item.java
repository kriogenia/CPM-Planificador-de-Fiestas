package common;

/**
 * Superclase de los items en stock
 * @author Ricardo Soto Estévez, uo265710
 */
public abstract class Item {

	//Datos globales de los item
	private String code;
	private String denomination;
	private String description;
	
	/**
	 * Constructor de la superclase Item
	 * @param code			Codigo del item
	 * @param denomination	Denominación del item
	 * @param description	Descripción del item
	 */
	public Item(String code, String denomination, String description) {
		this.code = code;
		this.denomination = denomination;
		this.description = description;
	}

	/**
	 * @return el codigo del item
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return la denominacion del item
	 */
	public String getDenomination() {
		return denomination;
	}

	/**
	 * @return la descripcion del item
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return ruta formateada de la imagen
	 */
	public String getPath() {
		String path = "/img/";
		path +=  getCode();
		path += ".jpg";
		return path;
	}
	
	/**
	 * @param modifier	modificador del precio del item
	 * @return			precio total del item formateado
	 */
	public String totalPrice(int modifer) {
		return "";
	}

	@Override
	public String toString() {
		return String.valueOf(denomination);
	}
	
}