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
 * @param quantity	Cantidad de cada ítem
 */
public class Cart {

	private HashMap<Item,Integer> cart;		// Cesta
	private Item localeSelected;			// Local único
	
	/**
	 * Crea un carro vacío
	 */
	public Cart() {
		cart = new HashMap<Item,Integer>();
		localeSelected = null;
	}
	
	public boolean isValidCart() {
		return !cart.isEmpty();
	}
	
// Métodos de adición de ítems
	
	/**
	 * @return	el local seleccionado en el carrito
	 */
	public Item getLocaleSelected() {
		return localeSelected;
	}
	
	/**
	 * Añade un elemento al carrito
	 * @param item			Elemento a añadir
	 * @param quantity		Cantidad del elemento
	 * @return				 0 si añadió el ítem con éxito
	 * 						-1 si no pudo añadir el IGP porque ya existe
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
	 * Añade un IGP al carrito
	 * @param item			Elemento a añadir
	 * @param quantity		Cantidad del elemento
	 * @return				 0 si añadió el ítem con éxito
	 * 						-1 si no pudo añadir el IGP porque ya existe
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
	 * Añade un local al carrito
	 * @param item	Local a añadir
	 * @return		 0 si lo añadió con éxito
	 * 				-1 si ya estaba añadido
	 * 				-2 si ya había otro local añadido
	 */
	private int addLocale(Item item) {
		if (cart.containsKey(item))		// si contiene el IGP no lo reañade
			return -1;
		if (localeSelected != null)		// si ya hay un local distinto, no lo añade
			return -2;
		cart.put(item, 1);
		localeSelected = item;
		return 0;
	}
	
	/**
	 * Añade una decoración al carrito
	 * @param item	Decoración a añadir
	 * @return		 0 si lo añadió con éxito
	 * 				-1 si ya estaba añadido
	 * 				-2 si ya había otra decoración añadida
	 */
	private int addDecoration(Item item) {
		if (cart.containsKey(item))		// si contiene el IGP no lo reañade
			return -1;
		cart.put(item, 1);
		return 0;
	}
	
	/**
	 * Añade un Otro al carrito
	 * @param item	Otro a añadir
	 * @return		 0 si lo añadió con éxito
	 * 				-1 si ya estaba añadido
	 */
	private int addOther(Item item) {
		if (cart.containsKey(item))		// si contiene el IGP no lo reañade
			return -1;
		cart.put(item, 1);
		return 0;
	}

	/**
	 * Elimina del carro el ítem introducido
	 * @param item	item a eliminar
	 */
	public void remove(Item item) {
		if (item instanceof Locale)
			localeSelected = null;
		cart.remove(item);
	}
	
	/**
	 * Reemplaza un item de un tipo por el ya añadido del mismo tipo
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