package gui;

import java.awt.Color;
import java.util.Date;

import javax.swing.JPopupMenu;

import com.toedter.calendar.JDateChooser;

/**
 * Clase que extiene el JDateChooser de toedter
 * @author esekr_000
 *
 */
public class MyDateChooser extends JDateChooser {

	// Serial
	private static final long serialVersionUID = -1984787623138569090L;

	/**
	 * Crea el DateChooser modificando fecha mínima, color y comportamiento
	 */
	public MyDateChooser() {
		this.jcalendar.setBackground(Color.WHITE);
		this.popup = new JPopupMenu();
		this.popup.setLightWeightPopupEnabled(true);
		this.popup.add(this.jcalendar);
		setMinSelectableDate(new Date());
	}
	
}