package eventHandlers;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * Manejador que cambia el icono de una imagen cuando esta es seleccionada
 * @author Ricardo Soto Estévez,uo265710
 */
public class ImageButtonChanger implements FocusListener {
	
	private Icon defaultIcon;						// Icono por defecto del botón
	private Icon selectedIcon;						// Icono para remarcar el boton
	
	/**
	 * Crea el manejador
	 * @param selectedIcon	icono que usará al recibir el foco
	 */
	public ImageButtonChanger(Icon selectedIcon) {
		this.selectedIcon = selectedIcon;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		defaultIcon = ((JButton) e.getComponent()).getIcon();
		((JButton) e.getComponent()).setIcon(selectedIcon);			
	}
	@Override
	public void focusLost(FocusEvent e) {
		((JButton) e.getComponent()).setIcon(defaultIcon);	
	}	
	
}