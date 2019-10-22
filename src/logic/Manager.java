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
 * Gestor de la lógica del programa
 * @author Ricardo Soto Estévez, uo265710
 *
 */
public class Manager {
	
	// Constantes
	private static final double DISCOUNT = 0.85;
	
	// Datos de la sesión
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
		this.id = new ItemDatabase();		// Se rellena automáticamente en la creación
	}
	
// Métodos de sesión	
	
	/**
	 * @return	true si se ha hecho logIn y false si es sesión de invitado
	 */
	public boolean isLogged() {
		return logIn;
	}
	
	/**
	 * Modifica la localización del usuario activo
	 * @param newLocation	Nueva localización
	 */
	public void changeLocation(Locale newLocation) {
		CurrentLocation.setLocation(newLocation);
	}
	
	/**
	 * Modifica el número de invitados a la fiesta
	 * @param attendants	Número de invitados introducido
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
	 * Calcula el número de grupos segun la cantidad de invitados
	 * @return	número de grupos
	 */
	private int getNumberOfGroups() {
		return (atts+9)/10;
	}
	
	/**
	 * @return	el teléfono de contacto proporcionado
	 */
	public String getContactPhone() {
		return dataInput[3];
	}
	
	/**
	 * Cambia la pestaña actual en la que está situado el programa principal
	 * @param currentTab	nueva pestaña actual
	 */
	public void setCurrentTab(int currentTab) {
		this.currentTab = currentTab;;
	}
	
	/**
	 * @return	pestaña actual del programa principal
	 */
	public int getCurrentTab() {
		return currentTab;
	}
	
// Métodos de gestión de dataInputs
	
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
	 * Carga los datos del usuario útiles para el dataInput
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
	
// Métodos de gestión de usuario

	/**
	 * @return	Usuario correspondiente al que ha iniciado la sesión
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Añade un usuario a la base de datos de usuarios
	 * @param user	Usuario a añadir
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
	 * @return			true si está disponible, falso si ya está en uso
	 */
	public boolean avaliableUsername(String username) {
		return !ud.checkUsername(username);
	}
	
	/**
	 * Comprueba que la contraseña corresponde al usuario introducido
	 * @param username	Nombre de usuario
	 * @param password	Contraseña
	 * @return			true si coinciden, false en caso contrario
	 */
	public boolean passwordMatching(String username, String password) {
		return ud.checkPassword(username, password);
	}
	
	/**
	 * Inicia la sesión, carga el usuario y pone el logIn a true
	 * @param username	Nombre de usuario
	 */
	public void logIn(String username) {
		user = ud.getUser(username);
		logIn = true;
		loadUser();
	}
	
	/**
	 * Inicia sesión como huésped, crea un usuario invitado y pone el logIn a false
	 */
	public void logInAsGuest() {
		user = new User("","","","","","-1","");
		logIn = false;
		loadUser();
	}
	
	/**
	 * Cierra la sesión en el gestor
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
	
// Métodos de gestión de ítems
	
	/**
	 * @return	Base de datos de los items
	 */
	public ItemDatabase getItemDatabase() {
		return id;
	}
	
	/**
	 * Añade un ítem al carrito
	 * @param item		Item a añadir
	 * @param quantity	Cantidad a añadir
	 * @return			 0 si lo añadió correctamente
	 * 					-1 si ya existe y solo puede haber uno
	 * 					-2 si ya existe uno del tipo y no puede haber más
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
	 * Vacía la cesta
	 */
	public void removeAll() {
		this.cart = new Cart();
	}

	/**
	 * Añade un item al carrito a costa de otro
	 * @param item	Item a añadir
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
	 * @return	true si ha sido añadido algún local a la cesta, false en caso contrario
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
	 * Calcula y devuelve el precio descontado de los items añadidos a la cesta
	 * @return	precio total del carro con el descuento aplicado
	 */
	public double getDiscPrice() {
		return getTotalPrice()*DISCOUNT;
	}
	
	/**
	 * Calcula y devuelve el precio total de los items añadidos a la cesta
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

// Métodos de factuación	
	
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