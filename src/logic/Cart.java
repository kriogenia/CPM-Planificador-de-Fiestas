package logic;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import common.Locale;
import common.Other;
import common.Decoration;
import common.Item;
import common.ItemGroupPrice;
import common.ItemUnitPrice;

/***
 * Clase que gestiona el carrito de la compra
 * @param item		Items del carrito
 * @param quantity	Cantidad de cada �tem
 */
public class Cart {

	private HashMap<Item,Integer> cart;		// Cesta
	private Item localeSelected;			// Local �nico
	
	/**
	 * Crea un carro vac�o
	 */
	public Cart() {
		cart = new HashMap<Item,Integer>();
		localeSelected = null;
	}
	
	public boolean isValidCart() {
		return !cart.isEmpty();
	}
	
// M�todos de adici�n de �tems
	
	/**
	 * @return	el local seleccionado en el carrito
	 */
	public Item getLocaleSelected() {
		return localeSelected;
	}
	
	/**
	 * A�ade un elemento al carrito
	 * @param item			Elemento a a�adir
	 * @param quantity		Cantidad del elemento
	 * @return				 0 si a�adi� el �tem con �xito
	 * 						-1 si no pudo a�adir el IGP porque ya existe
	 * 						-2 si ya existe un local del tipo pero distinto
	 * 						-3 en caso de error
	 */
	public int add(Item item, Integer quantity) {
		if (item instanceof ItemGroupPrice)				
			return addIGP(item);
		if (item instanceof ItemUnitPrice)
			return addIUP(item,quantity);
		return -3;
	}
	
	/**
	 * A�ade un IGP al carrito
	 * @param item			Elemento a a�adir
	 * @param quantity		Cantidad del elemento
	 * @return				 0 si a�adi� el �tem con �xito
	 * 						-1 si no pudo a�adir el IGP porque ya existe
	 * 						-2 si ya existe un IGP del tipo pero distinto
	 * 						-3 en caso de error
	 */
	private int addIGP(Item item) {
		if (item instanceof Locale)				
			return addLocale(item);
		if (item instanceof Decoration)
			return addDecoration(item);
		if (item instanceof Other)
			return addOther(item);
		return -3;
	}
	
	private int addIUP(Item item, Integer quantity) {
		if (cart.containsKey(item))
			cart.replace(item, cart.get(item)+quantity);
		else
			cart.put(item, quantity);
		return 0;
	}
	
	/**
	 * A�ade un local al carrito
	 * @param item	Local a a�adir
	 * @return		 0 si lo a�adi� con �xito
	 * 				-1 si ya estaba a�adido
	 * 				-2 si ya hab�a otro local a�adido
	 */
	private int addLocale(Item item) {
		if (cart.containsKey(item))		// si contiene el IGP no lo rea�ade
			return -1;
		if (localeSelected != null)		// si ya hay un local distinto, no lo a�ade
			return -2;
		cart.put(item, 1);
		localeSelected = item;
		return 0;
	}
	
	/**
	 * A�ade una decoraci�n al carrito
	 * @param item	Decoraci�n a a�adir
	 * @return		 0 si lo a�adi� con �xito
	 * 				-1 si ya estaba a�adido
	 * 				-2 si ya hab�a otra decoraci�n a�adida
	 */
	private int addDecoration(Item item) {
		if (cart.containsKey(item))		// si contiene el IGP no lo rea�ade
			return -1;
		cart.put(item, 1);
		return 0;
	}
	
	/**
	 * A�ade un Otro al carrito
	 * @param item	Otro a a�adir
	 * @return		 0 si lo a�adi� con �xito
	 * 				-1 si ya estaba a�adido
	 */
	private int addOther(Item item) {
		if (cart.containsKey(item))		// si contiene el IGP no lo rea�ade
			return -1;
		cart.put(item, 1);
		return 0;
	}

	/**
	 * Elimina del carro el �tem introducido
	 * @param item	item a eliminar
	 */
	public void remove(Item item) {
		if (item instanceof Locale)
			localeSelected = null;
		cart.remove(item);
	}
	
	/**
	 * Reemplaza un item de un tipo por el ya a�adido del mismo tipo
	 * @param item	item a introducir
	 */
	public void replace(Item item) {
		if (item instanceof Locale) {
			cart.remove(localeSelected);
			cart.put(item,1);	}
	}

	/**
	 * Devuelve el set de items almacenados en la cesta
	 * @return	set de items y cantidades
	 */
	public Set<Entry<Item,Integer>> getEntrySet() {
		return cart.entrySet();
	}

}