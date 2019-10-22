package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import common.User;
import file_util.FileUtil;
import file_util.Formatter;

/**
 * Clase que gestiona la base de datos de usuarios
 * @author Ricardo Soto Est�vez,uo265710
 */
public class UserDatabase {

	public final static String LOGINFILE = "./resources/database/clientes.dat";		// Ruta del archivo de log in
	
	// Bases de datos
	private ArrayList<User> usersData;			// Lista de usuarios con sus datos
	private HashMap<String,String> logInTable;	// Tabla con los usuarios y sus contrase�as
	
	/**
	 * Constructor de la base de datos
	 */
	public UserDatabase() {
		logInTable = new HashMap<String,String>();
		usersData = new ArrayList<User>();
		try {
			fill();	} 
		catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();	}	
	}
	
	/**
	 * Lee el archivo de clientes y vuelve a introducirlos en la base de datos
	 * @throws IOException 
	 */
	public void fill() throws IOException {
		String filePath = "./resources/database/clientes.dat";
		String[] params = new String[2];
		BufferedReader br = new BufferedReader(new FileReader(filePath)); 
		try {
		    String line = br.readLine();
		    while (line != null) {
		        params = Formatter.readLine(line);		// Obtiene los par�metros de la l�nea del fichero
		    	reload(params);							// Crea el usuario seg�n los par�metros
		    	line = br.readLine();	}	} 			// Pasa a la siguiente l�nea
		finally {
		    br.close();	}
	}
	
	public boolean reload(String[] params) {
		if (params == null || checkUsername(params[0]))									// Si el usuario ya existe o es nulo, no se permite a�adirlo
			return false;
		User newUser = new User(params[0],params[1],"","","","-1","");
		logInTable.put(newUser.getUsername(), newUser.getPassword());					// A�ade el usuario a la tabla de log-ins
		usersData.add(newUser);															// A�ade el usuario a la lista
		return true;
	}
	
	/**
	 * A�ade un usuario a la base de datos, tanto a la de logeo como a la de datos
	 * @param user	Usuario a a�adir
	 * @return		true si a�adi� al usuario, false en caso contrario
	 */
	public boolean add(String[] params) {
		if (params == null || checkUsername(params[0]))									// Si el usuario ya existe o es nulo, no se permite a�adirlo
			return false;
		User newUser = new User(params[0],params[1],params[2],params[3],params[4],params[5],params[6]);
		logInTable.put(newUser.getUsername(), newUser.getPassword());					// A�ade el usuario a la tabla de log-ins
		usersData.add(newUser);															// A�ade el usuario a la lista
		String toFile = Formatter.formatUsername(newUser.getUsername(), newUser.getPassword());
		FileUtil.addToFile(LOGINFILE, toFile);											// A�ade el usuario al archivo de log-ins
		return true;
	}
	
	/**
	 * Comprueba si existe el usuario pedido
	 * @param user	Nombre de usuario a buscar
	 * @return		true si existe, false en caso contrario
	 */
	public boolean checkUsername(String user) {
		return logInTable.containsKey(user);
	}
	
	/**
	 * Comprueba la dupla de usuario y contrase�a pasados y mira si es correcta
	 * @param user	Nombre de usuario
	 * @param pass	Contrase�a
	 * @return		true si es correcto, false si no lo es
	 */
	public boolean checkPassword(String user, String pass) {
		if (checkUsername(user))									// Si existe el usuario
			return logInTable.get(user).equals(pass);				// Comprueba la contrase�a
		return false;
	}
	
	/**
	 * Busca y devuelve el usuario correspondiente al nombre introducido
	 * @param username	Nombre de usuario
	 * @return			Usuario completo
	 */
	public User getUser(String username) {
		int i = 0;
		while(!usersData.get(i).getUsername().equals(username))
			i++;
		return usersData.get(i);
	}
	
}