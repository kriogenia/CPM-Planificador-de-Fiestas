package eventHandlers;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import interfaces.Localizable;
import logic.CurrentLocation;

/**
 * Manejador de eventos que ejecuta el método de localización con una acción
 * @author Ricardo Soto Estévez, uo265710
 *
 */
public class LocationChanger implements ActionListener {
	
	private Window frame;					// Ventana donde se produjo la acción
	
	/**
	 * Crea el manejador
	 * @param frame	Ventana a localizar
	 */
	public LocationChanger(Window frame) {
		super();
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CurrentLocation.setLocation(new Locale(e.getActionCommand()));
		((Localizable) frame).locate();
	}
	
}