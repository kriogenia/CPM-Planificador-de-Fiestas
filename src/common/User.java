package common;

/**
 * Clase que almacena los datos de un usuario
 * @author Ricardo Soto Estévez, uo265710
 */
public class User {
	
	// Constantes
	public static final int MALE = 0;
	public static final int FEMALE = 1;
	public static final int OTHER = 2;
	public static final int EMPTY = -1;
	
	// Datos almacenados del usuario
	private String username;		// Nombre del usuario, clave única del mismo
	private String password;		// Contraseña del usuario
	private String nif;				// DNI del usuario
	private String name;			// Nombre de pila del usuario
	private String surname;			// Apellido/s del usuario
	private int gender;				// Género del usuario (0: Masculino, 1: Femenino, 2: Otro) (-1: Vacío)
	private String phoneNumber;		// Número de teléfono del usuario
		
	/**
	 * Constructor del usuario, rellena todos los campos
	 * Todos los parámetros serán válidos, comprobados en la entrada de datos
	 * @param username		Nombre de usuario
	 * @param password		Contraseña
	 * @param nif			DNI
	 * @param name			Nombre
	 * @param surname		Apellido/s
	 * @param gender		Género
	 * @param phoneNumber	Número de teléfono
	 */
	public User (String username, String password, String nif, String name,
			String surname, String gender, String phoneNumber) {
		this.username = username;
		this.password = password;
		this.nif= nif;
		this.name = name;
		this.surname = surname;
		this.gender = Integer.parseInt(gender);
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @param username Nuevo nombre de usuario
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password Nueva contraseña del usuario
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param nif Nuevo DNI del usuario
	 */
	public void setNIF(String nif) {
		this.nif = nif;
	}

	/**
	 * @param name Nuevo nombre del usuario
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param surname Nuevo apellido del usuario
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @param gender Nuevo género del usuario
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @param phoneNumber Nuevo número de teléfono del usuario
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return	Nombre de usuario
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return	Contraseña del usuario
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return	DNI del usuario
	 */
	public String getNIF() {
		return nif;
	}

	/**
	 * @return	Nombre del usuario
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return	Apellido del usuario
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @return	Género del usuario
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @return	Número de teléfono del usuario
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	@Override
	public int hashCode() {
		return username.hashCode();
	}
	
}