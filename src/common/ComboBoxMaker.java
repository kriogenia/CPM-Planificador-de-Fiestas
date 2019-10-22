package common;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import logic.Manager;

/**
 * Convierte las listas de la base de datos en comboBoxes
 * @author Ricardo Soto Estévez, uo265710
 */
public abstract class ComboBoxMaker {

	/**
	 * Genera el comboBox de locales
	 * @param manager	Gestor
	 * @return			modelo de la comboBox
	 */
	public static DefaultComboBoxModel<Item> getLocaleModel(Manager manager) {
		ArrayList<Locale> locales = manager.getItemDatabase().getLocales();
		ArrayList<Item> list = new ArrayList<Item>(locales);
		return getItemModel(list);
	}
	
	/**
	 * Genera el comboBox de decoraciones
	 * @param manager	Gestor
	 * @return			modelo de la comboBox
	 */
	public static DefaultComboBoxModel<Item> getDecorationModel(Manager manager) {
		ArrayList<Decoration> decos = manager.getItemDatabase().getDecos();
		ArrayList<Item> list = new ArrayList<Item>(decos);
		return getItemModel(list);
	}
	
	/**
	 * Genera el comboBox de bebidas
	 * @param manager	Gestor
	 * @return			modelo de la comboBox
	 */
	public static DefaultComboBoxModel<Item> getDrinksModel(Manager manager) {
		ArrayList<Drink> drinks = manager.getItemDatabase().getDrinks();
		ArrayList<Item> list = new ArrayList<Item>(drinks);
		return getItemModel(list);
	}
	
	/**
	 * Genera el comboBox de comida
	 * @param manager	Gestor
	 * @return			modelo de la comboBox
	 */
	public static DefaultComboBoxModel<Item> getFoodModel(Manager manager) {
		ArrayList<Food> foods = manager.getItemDatabase().getFoods();
		ArrayList<Item> list = new ArrayList<Item>(foods);
		return getItemModel(list);
	}
	
	/**
	 * Genera el comboBox de otros
	 * @param manager	Gestor
	 * @return			modelo de la comboBox
	 */
	public static DefaultComboBoxModel<Item> getOthersModel(Manager manager) {
		ArrayList<Other> others = manager.getItemDatabase().getOthers();
		ArrayList<Item> list = new ArrayList<Item>(others);
		return getItemModel(list);
	}
	
	/**
	 * Genera un modelo de comboBox de item
	 * @param manager	Gestor
	 * @return			modelo de la comboBox
	 */
	private static DefaultComboBoxModel<Item> getItemModel(ArrayList<Item> list) {
		DefaultComboBoxModel<Item> model = new DefaultComboBoxModel<Item>();
		for (Item item: list)
			model.addElement(item);
		return model;
	}
	
	/**
	 * Genera el comboBox de horas
	 * @return	modelo de la comboBox
	 */
	public static DefaultComboBoxModel<String> getHourModel() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		String hour;
		for (int i=0; i<24; i++) {
			hour = "";
			if (i<10)
				hour += "0";
			hour += i;
			model.addElement(hour);}
		return model;
	}
	
	/**
	 * Genera el comboBox de minutes
	 * @return	modelo de la comboBox
	 */
	public static DefaultComboBoxModel<String> getMinutesModel() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		String[] minutes = {"00","15","30","45"};
		for (int i=0; i<minutes.length; i++) {
			model.addElement(minutes[i]);}
		return model;
	}

}