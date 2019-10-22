package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * Subclase JPanel que sobreescribe el paintComponent para permitir poner una imagen de fondo
 * @author Ricardo Soto Estévez, uo265710
 */
public class BackgroundPanel extends JPanel {

	private static final long serialVersionUID = 6389466815890264749L;
	private Image img;
 
	/**
	 * Crea el panel con la imagen pedida
	 * @param img	imagen para emplear de fondo
	 */
	public BackgroundPanel(Image img) {
		this.img = img;
	}
 
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_FAST), 0, 0, null); 
	}

}