package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Window;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import common.Item;
import common.ItemGroupPrice;
import common.ItemUnitPrice;
import eventHandlers.BtnRemoveHandler;
import file_util.Formatter;
import interfaces.Localizable;
import logic.CurrentLocation;

import javax.swing.Box;
import javax.swing.BoxLayout;

/**
 * Panel de cada item del carro
 * @author Ricardo Soto Estévez, uo265710
 */
public class CartItemPanel extends JPanel implements Localizable {

	// Serial
	private static final long serialVersionUID = -5546025899416305815L;
	
	// Elementos lógicos
	private Item item;
	private Integer units;
	private CartDialog ancestor;
	private ResourceBundle bundle;
	
	// Gestores de eventos
	private BtnRemoveHandler rh;
	
	// Elementos localizables
	private String priceTxt;
	private String totalTxt;
	private String group;
	private String usTxt;
	private String remove;
	private String btnTooltip;
	
	// Elementos visuales
	private JLabel lblImage;
	private JPanel pnlMid;
		private JLabel lblName;
		private JLabel lblPrice;
	private JPanel pnlRight;
		private JPanel pnlUnits;
			private JLabel lblUnits;
			private JTextField txtUnits;
		private JButton btnRemove;
		private JLabel lblTotalPrice;

	/**
	 * Crea el panel con los datos que debe contener
	 * @param item		item a mostrar
	 * @param units		número de unidades del item
	 * @param ancestor	carro para la gestión del botón de eliminación del item
	 */
	public CartItemPanel(Item item, Integer units, Window ancestor) {
		super();
		this.item = item;
		this.units = units;
		this.ancestor = (CartDialog) ancestor;
		// Creación de manejador y localización
		this.rh = new BtnRemoveHandler(ancestor,item);
		locate();
		// Creación del panel
		setMaximumSize(new Dimension(32767, 100));
		setBackground(Color.WHITE);
		GridBagLayout gbl_pnlItem = new GridBagLayout();
		gbl_pnlItem.columnWidths = new int[]{100,100,100};
		gbl_pnlItem.rowHeights = new int[]{100};
		gbl_pnlItem.columnWeights = new double[]{0,1.0,1.0};
		gbl_pnlItem.rowWeights = new double[]{1.0};
		setLayout(gbl_pnlItem);
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.anchor = GridBagConstraints.NORTH;
		gbc_lblImage.insets = new Insets(5, 5, 5, 5);
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 0;
		add(getLblImage(), gbc_lblImage);
		GridBagConstraints gbc_pnlMid = new GridBagConstraints();
		gbc_pnlMid.insets = new Insets(0, 0, 0, 0);
		gbc_pnlMid.fill = GridBagConstraints.BOTH;
		gbc_pnlMid.gridx = 1;
		gbc_pnlMid.gridy = 0;
		add(getPnlMid(), gbc_pnlMid);
		GridBagConstraints gbc_pnlRight = new GridBagConstraints();
		gbc_pnlRight.insets = new Insets(0, 0, 0, 10);
		gbc_pnlRight.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnlRight.gridx = 2;
		gbc_pnlRight.gridy = 0;
		add(getPnlRight(), gbc_pnlRight);
		if (item instanceof ItemGroupPrice)
			pnlUnits.setVisible(false);
	}

// Elementos visuales	
	
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			Image imgOriginal = new ImageIcon(getClass().getResource(getImagePath())).getImage();
			Image imgScaled = imgOriginal.getScaledInstance(90,90, Image.SCALE_FAST);
			lblImage.setIcon(new ImageIcon(imgScaled));
			lblImage.setToolTipText(getDescription());
		}
		return lblImage;
	}
	private JPanel getPnlMid() {
		if (pnlMid == null) {
			pnlMid = new JPanel();
			pnlMid.setBackground(Color.WHITE);
			pnlMid.setMaximumSize(new Dimension(32767, 100));
			pnlMid.setLayout(new BoxLayout(pnlMid, BoxLayout.Y_AXIS));
			pnlMid.add(Box.createRigidArea(new Dimension(0,5)));
			pnlMid.add(getLblName());
			pnlMid.add(Box.createRigidArea(new Dimension(0,20)));
			pnlMid.add(getLblPrice());
			pnlMid.add(getLblTotalPrice());
		}
		return pnlMid;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel(getItemName());
			lblName.setPreferredSize(new Dimension(100, 14));
			lblName.setFont(new Font("Dialog", Font.PLAIN, 13));
		}
		return lblName;
	}
	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel(getItemPrice());
			lblPrice.setFont(new Font("Dialog", Font.PLAIN, 11));
		}
		return lblPrice;
	}
	private JLabel getLblTotalPrice() {
		if (lblTotalPrice == null) {
			lblTotalPrice = new JLabel(getTotalPrice());
			lblTotalPrice.setFont(new Font("Dialog", Font.BOLD, 11));
		}
		return lblTotalPrice;
	}
	private JPanel getPnlRight() {
		if (pnlRight == null) {
			pnlRight = new JPanel();
			pnlRight.setBackground(Color.WHITE);
			pnlRight.setLayout(new GridLayout(2, 1, 0, 0));
			pnlRight.add(getPnlUnits());
			pnlRight.add(getBtnRemove());
		}
		return pnlRight;
	}
	private JPanel getPnlUnits() {
		if (pnlUnits == null) {
			pnlUnits = new JPanel();
			pnlUnits.setBackground(Color.WHITE);
			pnlUnits.add(getLblUnits());
			pnlUnits.add(getTxtUnids());
		}
		return pnlUnits;
	}
	private JLabel getLblUnits() {
		if (lblUnits == null) {
			lblUnits = new JLabel(getUnitsText());
		}
		return lblUnits;
	}
	private JTextField getTxtUnids() {
		if (txtUnits == null) {
			txtUnits = new JTextField(units);
			txtUnits.setColumns(3);
			txtUnits.setText(String.valueOf(units));
			txtUnits.setEditable(false);
		}
		return txtUnits;
	}
	private JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton(remove);
			btnRemove.setToolTipText(btnTooltip);
			btnRemove.setActionCommand(item.getCode());
			btnRemove.addActionListener(rh);
			btnRemove.setEnabled(ancestor.isRemoveEnabled());
		}
		return btnRemove;
	}
	
// Métodos de generación de elementos por cada ítem
	
	/**
	 * @return	la ruta formateada de la imagen
	 */
	private String getImagePath() {
		return item.getPath();
	}
	
	/**
	 * @return	descripción del item
	 */
	private String getDescription() {
		return item.getDescription();
	}
	
	/**
	 * @return	el nombre formateado del item
	 */
	private String getItemName() {
		String name;
		name = "<html>";
		name += item.getDenomination();
		name+= "</html>";
		return name;
	}
	
	/**
	 * @return	el precio formateado del item
	 */
	private String getItemPrice() {
		if (item instanceof ItemGroupPrice)
			return getItemGroupPrice();
		else
			return getItemUnitPrice();
	}
	
	/**
	 * @return	el precio grupal formateado
	 */
	private String getItemGroupPrice() {
		String price = priceTxt;
		price += Formatter.formatCurrency(((ItemGroupPrice) item).getPrice());
		price += group;
		return price;
	}
	
	/**
	 * @return	el precio unitario formateado
	 */
	private String getItemUnitPrice() {
		String price = priceTxt;
		price += Formatter.formatCurrency(((ItemUnitPrice) item).getPrice());
		price += "/u";
		return price;
	}
	
	/**
	 * @return	el precio total formateado del item
	 */
	private String getTotalPrice() {
		if (item instanceof ItemGroupPrice)
			return totalTxt + item.totalPrice(ancestor.getAttendants());
		else
			return totalTxt + item.totalPrice(units);
	}
	
	/**
	 * @return	valor de la label Units
	 */
	private String getUnitsText() {
		return usTxt;
	}

// Método de localización	
	
	@Override
	public void locate() {
		Locale location = CurrentLocation.getLocale();
		bundle = ResourceBundle.getBundle("rcs/CartItem",location);
		priceTxt = bundle.getString("price");
		totalTxt = bundle.getString("total");
		group = bundle.getString("group");
		usTxt = bundle.getString("us");
		remove = bundle.getString("remove");
		btnTooltip = bundle.getString("btnTooltip");
	}
}