package logic;

import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import common.Item;
import common.ItemGroupPrice;
import common.ItemUnitPrice;
import common.Registration;
import common.User;
import data.ItemDatabase;
import data.UserDatabase;
import file_util.FileUtil;
import file_util.Formatter;

/**
 * Gestor de la l�gica del programa
 * @author Ricardo Soto Est�vez, uo265710
 *
 */
public class Manager {
	
	// Constantes
	private static final double DISCOUNT = 0.85;
	
	// Datos de la sesi�n
	private int currentTab;
	private boolean logIn;
	private User user;
	private String[] dataInput;		// Almacena nombre, apellidos, dni, telefono, hora, minutos, observaciones, fecha
	
	// Bases de datos del programa
	private UserDatabase ud;
	private ItemDatabase id;
	
	// Carrito
	private Cart cart;
	private int atts;
	
	/**
	 * Constructor de la clase Manager, inicia las bases de datos
	 */
	public Manager() {
		CurrentLocation.setLocation(CurrentLocation.SPANISH);
		this.currentTab = 1;
		this.logIn = false;
		startDataInput();
		this.cart = new Cart();
		atts = 1;
		this.ud = new UserDatabase();
		this.id = new ItemDatabase();		// Se rellena autom�ticamente en la creaci�n
	}
	
// M�todos de sesi�n	
	
	/**
	 * @return	true si se ha hecho logIn y false si es sesi�n de invitado
	 */
	public boolean isLogged() {
		return logIn;
	}
	
	/**
	 * Modifica la localizaci�n del usuario activo
	 * @param newLocation	Nueva localizaci�n
	 */
	public void changeLocation(Locale newLocation) {
		CurrentLocation.setLocation(newLocation);
	}
	
	/**
	 * Modifica el n�mero de invitados a la fiesta
	 * @param attendants	N�mero de invitados introducido
	 */
	public void setAttendants(int attendants) {
		this.atts = attendants;
	}
	
	/**
	 * @return	invitados apuntados a la fiesta
	 */
	public int getAttendants() {
		return atts;
	}
	
	/**
	 * Calcula el n�mero de grupos segun la cantidad de invitados
	 * @return	n�mero de grupos
	 */
	private int getNumberOfGroups() {
		return (atts+9)/10;
	}
	
	/**
	 * @return	el tel�fono de contacto proporcionado
	 */
	public String getContactPhone() {
		return dataInput[3];
	}
	
	/**
	 * Cambia la pesta�a actual en la que est� situado el programa principal
	 * @param currentTab	nueva pesta�a actual
	 */
	public void setCurrentTab(int currentTab) {
		this.currentTab = currentTab;;
	}
	
	/**
	 * @return	pesta�a actual del programa principal
	 */
	public int getCurrentTab() {
		return currentTab;
	}
	
// M�todos de gesti�n de dataInputs
	
	/**
	 * Inicia el data input
	 */
	private void startDataInput() {
		dataInput = new String[8];
		for (int i=0; i<dataInput.length; i++)
			dataInput[0] = "";
		dataInput[4] = "22";
		dataInput[5] = "00";
	}
	
	/**
	 * Carga los datos del usuario �tiles para el dataInput
	 */
	public void loadUser() {
		dataInput[0] = user.getName();
		dataInput[1] = user.getSurname();
		dataInput[2] = user.getNIF();
		dataInput[3] = user.getPhoneNumber();
	}
	
	/**
	 * Guarda los datos de dataInput
	 */
	public void saveDataInput(String[] params) {
		for(int i=0; i<dataInput.length; i++)
			dataInput[i] = params[i];
	}
	
	/**
	 * @return	array de datos introducidos en dataInput
	 */
	public String[] getDataInput() {
		return dataInput;
	}
	
// M�todos de gesti�n de usuario

	/**
	 * @return	Usuario correspondiente al que ha iniciado la sesi�n
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * A�ade un usuario a la base de datos de usuarios
	 * @param user	Usuario a a�adir
	 */
	public void addUser(String[] params) {
		ud.add(params);
	}

	/**
	 * Comprueba si el usuario introduce existe en la base de datos
	 * @param username	Nombre de usuario a comprobar
	 * @return			true si existe, false en caso contrario
	 */
	public boolean existUsername(String username) {
		return ud.checkUsername(username);
	}

	/**
	 * Comprueba la disponibilidad del nombre de usuario introducido
	 * @param username	Nombre de usuario a comprobar
	 * @return			true si est� disponible, falso si ya est� en uso
	 */
	public boolean avaliableUsername(String username) {
		return !ud.checkUsername(username);
	}
	
	/**
	 * Comprueba que la contrase�a corresponde al usuario introducido
	 * @param username	Nombre de usuario
	 * @param password	Contrase�a
	 * @return			true si coinciden, false en caso contrario
	 */
	public boolean passwordMatching(String username, String password) {
		return ud.checkPassword(username, password);
	}
	
	/**
	 * Inicia la sesi�n, carga el usuario y pone el logIn a true
	 * @param username	Nombre de usuario
	 */
	public void logIn(String username) {
		user = ud.getUser(username);
		logIn = true;
		loadUser();
	}
	
	/**
	 * Inicia sesi�n como hu�sped, crea un usuario invitado y pone el logIn a false
	 */
	public void logInAsGuest() {
		user = new User("","","","","","-1","");
		logIn = false;
		loadUser();
	}
	
	/**
	 * Cierra la sesi�n en el gestor
	 */
	public void logOut() {
		logIn = false;
		user = null;
	}
	
	/**
	 * Reinicia el gestor, eliminado todo menos las bases de datos
	 */
	public void reset() {
		CurrentLocation.setLocation(CurrentLocation.SPANISH);
		this.logIn = false;
		startDataInput();
		this.cart = new Cart();
		this.atts = 0;
	}
	
// M�todos de gesti�n de �tems
	
	/**
	 * @return	Base de datos de los items
	 */
	public ItemDatabase getItemDatabase() {
		return id;
	}
	
	/**
	 * A�ade un �tem al carrito
	 * @param item		Item a a�adir
	 * @param quantity	Cantidad a a�adir
	 * @return			 0 si lo a�adi� correctamente
	 * 					-1 si ya existe y solo puede haber uno
	 * 					-2 si ya existe uno del tipo y no puede haber m�s
	 * 					-3 en caso de error
	 */
	public int addItemToCart(Item item, int quantity) {
		return cart.add(item, new Integer(quantity));
	}

	/**
	 * Elimina un item de la cesta
	 * @param item	item a eliminar
	 */
	public void removeItem(Item item) {
		cart.remove(item);
	}
	
	/**
	 * Vac�a la cesta
	 */
	public void removeAll() {
		this.cart = new Cart();
	}

	/**
	 * A�ade un item al carrito a costa de otro
	 * @param item	Item a a�adir
	 */
	public void replace(Item item) {
		cart.replace(item);
	}

	/**
	 * @return	set de entradas del cesta
	 */
	public Set<Entry<Item,Integer>> getCartSet() {
		return cart.getEntrySet();
	}
	
	/**
	 * @return	true si ha sido a�adido alg�n local a la cesta, false en caso contrario
	 */
	public boolean isValidCart() {
		return cart.isValidCart();
	}

	/**
	 * @return	precio del carrito actual
	 */
	public double getPrice() {
		if (logIn)
			return getDiscPrice();
		else
			return getTotalPrice();
	}
	
	/**
	 * Calcula y devuelve el precio descontado de los items a�adidos a la cesta
	 * @return	precio total del carro con el descuento aplicado
	 */
	public double getDiscPrice() {
		return getTotalPrice()*DISCOUNT;
	}
	
	/**
	 * Calcula y devuelve el precio total de los items a�adidos a la cesta
	 * @return	precio total del carro
	 */
	public double getTotalPrice() {
		double price = 0;
		Set<Entry<Item,Integer>> set = getCartSet();
		for (Map.Entry<Item, Integer> entry : set) {
		    Item item = entry.getKey();
		    int qty = entry.getValue();
		    price += calculateCost(item,qty);	}
		return price;
	}
	
	/**
	 * Calcula y devuelve el coste total de un item
	 * @param item	item del que calcular el coste
	 * @param qty	cantidad de unidades del item
	 */
	private double calculateCost(Item item, int qty) {
		if (item instanceof ItemGroupPrice)
			return ((ItemGroupPrice) item).getPrice()*getNumberOfGroups();
		else
			return ((ItemUnitPrice) item).getPrice()*qty;
	}

// M�todos de factuaci�n	
	
	/**
	 * Crea el registro y devuelve la factura
	 */
	public String getBill() {
		String name = dataInput[0];
		String surname = dataInput[1];
		String nif = dataInput[2];
		String hour = dataInput[4];
		String mins = dataInput[5];
		String obs = dataInput[6];
		String date = dataInput[7];
		String atts = String.valueOf(this.atts);
		String username = user.getUsername();
		Set<Entry<Item,Integer>> set = getCartSet();
		double totalPrice = getTotalPrice();
		double discPrice = getDiscPrice();
		Registration r = new Registration(name, surname, username, nif, date, hour, mins, atts, set, obs, totalPrice, discPrice);
		return r.toString();
	}
	
	/**
	 * Genera la ruta recomendada para el archivo de guardado
	 */
	public String getOutputPath() {
		return Formatter.formatOutput(dataInput[2],dataInput[7]);
	}
	
	/**
	 * Genera la factura en el directorio dado
	 */
	public void printBill(String givenPath) {
		FileUtil.addToFile(givenPath, getBill());
	}
	
}