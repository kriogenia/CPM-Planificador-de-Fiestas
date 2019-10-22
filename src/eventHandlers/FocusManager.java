package eventHandlers;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * Proporciona el foco al elemento pertinente cuando la ventana lo obtiene
 * @author Ricardo Soto Estévez,uo265710
 */
public class FocusManager implements WindowFocusListener {

	private Window frame;			// Ventana a gesstionar
	private Component lastFocus;	// Último elemento enfocado
	
	/**
	 * Crea el manejador con referencias a la ventana
	 * @param frame			Ventana de la que gestionar el foco
	 * @param firstFocus	Primer elemento a enfocar
	 */
	public FocusManager(Window frame, Component firstFocus) {
		this.frame = frame;
		this.lastFocus = firstFocus;
	}
	
	@Override
	public void windowGainedFocus(WindowEvent e) {
		lastFocus.requestFocus();
	}
	
	@Override
	public void windowLostFocus(WindowEvent e) {
		lastFocus = frame.getMostRecentFocusOwner();
	}

}