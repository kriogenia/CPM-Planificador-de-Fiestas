package eventHandlers;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import common.Item;
import interfaces.Remover;

/**
 * Gestiona la acción de los botones de eliminación de items del carro
 * @author Ricardo Soto Estévez, uo265710
 */
public class BtnRemoveHandler implements ActionListener {

	private Window frame;			// Ventana del carro para la eliminación de los item
	private Item relatedItem;		// Item a eliminar
	
	/**
	 * Crea el manejador con acceso al carro y al item
	 * @param frame	Diálogo del carro
	 * @param item	Item a eliminar
	 */
	public BtnRemoveHandler(Window frame, Item item) {
		this.frame = frame;
		this.relatedItem = item;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		((Remover) frame).removeItem(relatedItem);
	}

}