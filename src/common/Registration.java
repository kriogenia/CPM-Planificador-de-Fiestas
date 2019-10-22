package common;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Map.Entry;

import file_util.Formatter;
import logic.CurrentLocation;

/**
 * Clase que almacena el registro de la fiesta y genera la factura
 * @author Ricardo Soto Estévez, uo265710
 */
public class Registration {

	// Información del registro
	private String fullName;
	private String username;
	private String nif;
	private String date;
	private String time;
	private String attendants;
	private Set<Entry<Item,Integer>> cart;
	private String observations;
	private double totalPrice;
	private double discPrice;
	private int groups;
	
	// Elementos del toString
	private StringBuilder sb;
	private String openL;
	private String clientL;
	private String rClientL;
	private String nifL;
	private String dateL;
	private String timeL;
	private String attL;
	private String tableL;
	private String drinkL;
	private String foodL;
	private String decoL;
	private String localeL;
	private String otherL;
	private String obserL;
	private String billL;
	private String rBillL;
	private String discL;
	
	
	/**
	 * Crea el objeto Registro
	 * @param name			nombre del cliente
	 * @param surname		apellidos del cliente
	 * @param username		nombre de usuario
	 * @param nif			dni del cliente
	 * @param date			fecha de la fiesta
	 * @param hour			hora de la fiesta
	 * @param minutes		minutos de la fiesta
	 * @param attendants	invitados de la fiesta
	 * @param cart			cesta de la compra
	 * @param observations	observaciones del cliente
	 * @param totalPrice	precio total del pedido
	 * @param discPrice		precio descontado del pedido
	 */
	public Registration(String name, String surname, String username, 
						String nif, String date, String hour, String minutes,
						String attendants, Set<Entry<Item,Integer>> cart,
						String observations, double totalPrice, double discPrice) {
		this.fullName = name + " " + surname;
		this.username = username;
		this.nif = nif;
		this.date = date;
		this.time = hour + ":" + minutes;
		this.attendants = attendants;
		this.cart = cart;
		this.observations = observations;
		this.totalPrice = totalPrice;
		this.discPrice = discPrice;
		this.groups = (Integer.parseInt(attendants)+9)/10;
	}
	
	@Override
	public String toString() {
		locate();
		sb = new StringBuilder();
		appendData();
		appendDrinks();
		appendFoods();
		appendDecorations();
		appendLocale();
		appendOthers();
		appendObservations();
		appendPrice();
		return sb.toString();
	}
	
	/**
	 * Escribe el encabezado de la factura
	 */
	private void appendData() {
		sb.append(openL);
		sb.append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));
		sb.append("--------------");
		sb.append(System.getProperty("line.separator"));
		sb.append(clientL);
		sb.append(fullName);
		sb.append(" ");
		if (username.length() != 0) {
			sb.append(rClientL);
			sb.append(username);
			sb.append(")");	}
		sb.append(System.getProperty("line.separator"));
		sb.append(nifL);
		sb.append(nif);
		sb.append(System.getProperty("line.separator"));
		sb.append(dateL);
		sb.append(date);
		sb.append(" ");
		sb.append(timeL);
		sb.append(time);
		sb.append(System.getProperty("line.separator"));
		sb.append(attL);
		sb.append(attendants);
		sb.append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));
		sb.append(tableL);
		sb.append(System.getProperty("line.separator"));
		sb.append("----------------------------------------------------------------------");
		sb.append(System.getProperty("line.separator"));
	}
	
	/**
	 * Añade las bebidas
	 */
	private void appendDrinks() {
		sb.append(drinkL);
		sb.append(System.getProperty("line.separator"));
		for (Map.Entry<Item, Integer> item: cart) {
			if (item.getKey() instanceof Drink)
				appendIUP(item.getKey(),item.getValue());
		}
	}
	
	/**
	 * Añade las comidas
	 */
	private void appendFoods() {
		sb.append(foodL);
		sb.append(System.getProperty("line.separator"));
		for (Map.Entry<Item, Integer> item: cart) {
			if (item.getKey() instanceof Food)
				appendIUP(item.getKey(),item.getValue());
		}
	}
	
	/**
	 * Añade la decoración
	 */
	private void appendDecorations() {
		sb.append(decoL);
		sb.append(System.getProperty("line.separator"));
		for (Map.Entry<Item, Integer> item: cart) {
			if (item.getKey() instanceof Decoration) {
				appendIGP(item.getKey(),groups);
				break;	}
		}
	}
	
	/**
	 * Añade el local
	 */
	private void appendLocale() {
		sb.append(localeL);
		sb.append(System.getProperty("line.separator"));
		for (Map.Entry<Item, Integer> item: cart) {
			if (item.getKey() instanceof Locale) {
				appendIGP(item.getKey(),groups);
				break;	}
		}
	}
	
	/**
	 * Añade los otros artículos
	 */
	private void appendOthers() {
		sb.append(otherL);
		sb.append(System.getProperty("line.separator"));
		for (Map.Entry<Item, Integer> item: cart) {
			if (item.getKey() instanceof Other) {
				appendIGP(item.getKey(),groups);
				break;	}
		}
	}
	
	/**
	 * Añade las observaciones del cliente
	 */
	private void appendObservations() {
		sb.append(System.getProperty("line.separator"));
		sb.append(obserL);
		sb.append(System.getProperty("line.separator"));
		sb.append("-------------");
		sb.append(System.getProperty("line.separator"));
		sb.append(observations);
		sb.append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));
	}
	
	/**
	 * Añade el precio de la factura
	 */
	private void appendPrice() {
		if (username.length() == 0) {
			sb.append(billL);
			sb.append(Formatter.formatCurrency(totalPrice));	}
		else {
			sb.append(rBillL);
			sb.append(totalPrice);
			sb.append(" ");
			sb.append(discL);
			sb.append(totalPrice);
			sb.append(") = ");
			sb.append(Formatter.formatCurrency(discPrice));
		}
	}
	
	/**
	 * Añade un item IUP
	 */
	private void appendIUP(Item item, Integer qty) {
		sb.append("* ");
		sb.append(item.getDenomination());
		sb.append(" / ");
		sb.append(item.getCode());
		sb.append(" / ");
		sb.append(qty);
		sb.append(" / ");
		sb.append(((ItemUnitPrice) item).totalPrice(qty));
		sb.append(System.getProperty("line.separator"));
	}
	
	/**
	 * Añade un item IGP
	 */
	private void appendIGP(Item item, Integer groups) {
		sb.append("* ");
		sb.append(item.getDenomination());
		sb.append(" / ");
		sb.append(item.getCode());
		sb.append(" / ");
		sb.append(groups);
		sb.append(" / ");
		sb.append(((ItemGroupPrice) item).totalPrice(Integer.parseInt(attendants)));
		sb.append(System.getProperty("line.separator"));
	}

	/**
	 * Localiza la salida de la factura
	 */
	public void locate() {
		ResourceBundle bundle = ResourceBundle.getBundle("rcs/Registration",CurrentLocation.getLocale());
		openL = bundle.getString("openL");
		clientL = bundle.getString("clientL");
		rClientL = bundle.getString("rClientL");
		nifL = bundle.getString("nifL");
		dateL = bundle.getString("dateL");
		timeL = bundle.getString("timeL");
		attL = bundle.getString("attL");
		tableL = bundle.getString("tableL");
		drinkL = bundle.getString("drinkL");
		foodL = bundle.getString("foodL");
		decoL = bundle.getString("decoL");
		localeL = bundle.getString("localeL");
		otherL = bundle.getString("otherL");
		obserL = bundle.getString("obserL");
		billL = bundle.getString("billL");
		rBillL = bundle.getString("rBillL");
		discL = bundle.getString("discL");
	}
	
}