package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import common.Decoration;
import common.Drink;
import common.Food;
import common.Locale;
import common.Other;
import file_util.Formatter;

/**
 * Gestiona la base de datos de items
 * @author Ricardo Soto Estévez, uo265710
 */
public class ItemDatabase {
	
	// Constantes
	public static final String DRINK = "Bebida";
	public static final String FOOD = "Comida";
	public static final String DECORATION = "Decoración";
	public static final String LOCALE = "Local";
	public static final String OTHER = "Otros";
	
	// Listas de items en stock por categorías
	private ArrayList<Drink> drinks;			// Bebidas
	private ArrayList<Food> foods;				// Comidas
	private ArrayList<Decoration> decos;		// Decoraciones
	private ArrayList<Locale> locales;			// Locales
	private ArrayList<Other> others;			// Otros

	/**
	 * Constructor de los listados de ítems, inicia y llena las bases de datos
	 */
	public ItemDatabase() {
		// Inicia los listados
		drinks = new ArrayList<Drink>();
		foods = new ArrayList<Food>();
		decos = new ArrayList<Decoration>();
		locales = new ArrayList<Locale>();
		others = new ArrayList<Other>();
		// Rellena los listados
		try {
			fill();	} 
		catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();	}									
	}
	
	/**
	 * @return el listado de bebidas
	 */
	public ArrayList<Drink> getDrinks() {
		return drinks;
	}

	/**
	 * @return el listado de comidas
	 */
	public ArrayList<Food> getFoods() {
		return foods;
	}

	/**
	 * @return el listado de decoraciones
	 */
	public ArrayList<Decoration> getDecos() {
		return decos;
	}

	/**
	 * @return el listado de locales
	 */
	public ArrayList<Locale> getLocales() {
		return locales;
	}

	/**
	 * @return el listado de otros
	 */
	public ArrayList<Other> getOthers() {
		return others;
	}

	/**
	 * Lee el fichero de entrada de ítems y llena las bases de datos
	 * @param filePath		Dirección del fichero de entrada de ítems
	 * @throws IOException	Error de cierre del BufferedReader o con la ruta del fichero de entrada
	 */
	private void fill() throws IOException {
		String filePath = "./resources/database/fiesta.txt";
		String[] params = new String[5];
		BufferedReader br = new BufferedReader(new FileReader(filePath)); 
		try {
		    String line = br.readLine();
		    while (line != null) {
		        params = Formatter.readLine(line);		// Obtiene los parámetros de la línea del fichero
		    	addItem(params);						// Crea el ítem según los parámetros
		    	line = br.readLine();	}	} 			// Pasa a la siguiente línea
		finally {
		    br.close();	}
	}
	
	/**
	 * Recibe una array de Strings y crea el item adecuado
	 * @param params	Array de Strings con los datos del ítem a crear y añadir
	 */
	private void addItem(String[] params) {
		switch(params[1]) {
			case DRINK:
				drinks.add(new Drink(params[0],params[2],params[3],params[4]));
				break;
			case FOOD:
				foods.add(new Food(params[0],params[2],params[3],params[4]));
				break;
			case DECORATION:
				decos.add(new Decoration(params[0],params[2],params[3],params[5]));
				break;
			case LOCALE:
				locales.add(new Locale(params[0],params[2],params[3],params[5]));
				break;
			case OTHER:
				others.add(new Other(params[0],params[2],params[3],params[5]));
				break;	}
	}

}