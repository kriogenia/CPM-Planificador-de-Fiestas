package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JPanel;

import eventHandlers.FocusManager;
import eventHandlers.ImageButtonChanger;
import file_util.Formatter;
import interfaces.Localizable;
import interfaces.Remover;
import logic.CurrentLocation;
import logic.Manager;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import common.Item;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class CartDialog extends JDialog implements Localizable, Remover {

	// Serial
	private static final long serialVersionUID = -1368393089476571755L;
	
	// Elementos lógicos
	private Manager manager;
	private ResourceBundle bundle;
	private boolean removeEnabled;
	
	// Elementos visuales
	private JPanel pnlNorth;
		private JLabel lblCart;
		private JPanel pnlHelp;
			private JButton imgHelp;
	private JPanel pnlSouth;
		private JButton btnBack;
	private JPanel pnlCenter;
		private JPanel pnlWest;
			private JScrollPane sPnList;
				private JPanel pnlList;
		private JPanel pnlEast;
			private JPanel pnlTotalPrice;
				private JLabel lblTotalPrice;
				private JTextField txtTotalPrice;
			private JPanel pnlDiscPrice;
				private JLabel lblDiscPrice;
				private JTextField txtDiscPrice;
			private JButton btnRemoveAll;

	// Prueba
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CartDialog dialog = new CartDialog(new Manager());
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					dialog.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the dialog.
	 */
	public CartDialog(Manager manager) {
		this.manager = manager;
		if (manager.getCurrentTab() > 1)
			removeEnabled = false;
		else
			removeEnabled = true;
		setMinimumSize(new Dimension(500, 300));
		setTitle("titleCart");
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CartDialog.class.getResource(Main.ICON)));
		setBounds(100, 100, 500, 500);
		getContentPane().add(getPnlNorth(), BorderLayout.NORTH);
		getContentPane().add(getPanelCenter(), BorderLayout.CENTER);
		getContentPane().add(getPnlSouth(), BorderLayout.SOUTH);
		this.addWindowFocusListener(new FocusManager(this,btnBack));
		// Botón ESC por defecto
		getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "CANCEL");
		getRootPane().getActionMap().put("CANCEL", btnBack.getAction());
		// Localización y actualización de datos
		locate();
		updatePrices();
		manageDiscount();
		createHelp();
	}

// Elementos visuales
	
	private JPanel getPnlNorth() {
		if (pnlNorth == null) {
			pnlNorth = new JPanel();
			pnlNorth.setLayout(new GridLayout(0, 2, 0, 0));
			pnlNorth.add(getLblCart());
			pnlNorth.add(getPnlHelp());
		}
		return pnlNorth;
	}
	private JLabel getLblCart() {
		if (lblCart == null) {
			lblCart = new JLabel("lblCart");
			lblCart.setBorder(new EmptyBorder(0, 10, 0, 0));
			lblCart.setHorizontalAlignment(SwingConstants.LEFT);
			lblCart.setFont(new Font("Dialog", Font.BOLD, 18));
		}
		return lblCart;
	}
	private JPanel getPnlHelp() {
		if (pnlHelp == null) {
			pnlHelp = new JPanel();
			FlowLayout fl_pnlHelp = (FlowLayout) pnlHelp.getLayout();
			fl_pnlHelp.setAlignment(FlowLayout.RIGHT);
			pnlHelp.add(getImgHelp());
		}
		return pnlHelp;
	}
	private JButton getImgHelp() {
		if (imgHelp == null) {
			imgHelp = new JButton("");
			imgHelp.setHorizontalAlignment(SwingConstants.RIGHT);
			imgHelp.setIcon(new ImageIcon(Main.class.getResource(Main.IMG_HELP_IN)));
			imgHelp.setRolloverIcon(new ImageIcon(Main.class.getResource(Main.IMG_HELP)));
			imgHelp.setBorderPainted(false);
			imgHelp.setContentAreaFilled(false);
			imgHelp.addFocusListener(new ImageButtonChanger(new ImageIcon(Main.class.getResource(Main.IMG_HELP_SE))));
		}
		return imgHelp;
	}
	private JPanel getPnlSouth() {
		if (pnlSouth == null) {
			pnlSouth = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlSouth.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnlSouth.add(getBtnBack());
		}
		return pnlSouth;
	}
	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("Back");
			btnBack.setAction(new AbstractAction() {
				private static final long serialVersionUID = -3958897981605815084L;
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		}
		return btnBack;
	}
	private JPanel getPanelCenter() {
		if (pnlCenter == null) {
			pnlCenter = new JPanel();
			GridBagLayout gbl_pnlCenter = new GridBagLayout();
			gbl_pnlCenter.columnWidths = new int[]{400,200};
			gbl_pnlCenter.rowHeights = new int[]{0, 0};
			gbl_pnlCenter.columnWeights = new double[]{1.0,1.0};
			gbl_pnlCenter.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			pnlCenter.setLayout(gbl_pnlCenter);
			GridBagConstraints gbc_pnlWest = new GridBagConstraints();
			gbc_pnlWest.insets = new Insets(5, 5, 5, 5);
			gbc_pnlWest.fill = GridBagConstraints.BOTH;
			gbc_pnlWest.gridx = 0;
			gbc_pnlWest.gridy = 0;
			pnlCenter.add(getPnlWest(), gbc_pnlWest);
			GridBagConstraints gbc_pnlEast = new GridBagConstraints();
			gbc_pnlEast.insets = new Insets(5, 5, 5, 5);
			gbc_pnlEast.fill = GridBagConstraints.BOTH;
			gbc_pnlEast.anchor= GridBagConstraints.NORTH;
			gbc_pnlEast.gridx = 1;
			gbc_pnlEast.gridy = 0;
			pnlCenter.add(getPnlEast(), gbc_pnlEast);
		}
		return pnlCenter;
	}
	private JPanel getPnlEast() {
		if (pnlEast == null) {
			pnlEast = new JPanel();
			pnlEast.setLayout(new GridLayout(3, 0, 0, 0));
			pnlEast.add(getPnlTotalPrice());
			pnlEast.add(getPnlDiscPrice());
			pnlEast.add(getBtnRemoveAll());
		}
		return pnlEast;
	}
	private JPanel getPnlTotalPrice() {
		if (pnlTotalPrice == null) {
			pnlTotalPrice = new JPanel();
			pnlTotalPrice.add(getLblTotalPrice());
			pnlTotalPrice.add(getTxtTotalPrice());
		}
		return pnlTotalPrice;
	}
	private JPanel getPnlDiscPrice() {
		if (pnlDiscPrice == null) {
			pnlDiscPrice = new JPanel();
			pnlDiscPrice.add(getLblDiscPrice());
			pnlDiscPrice.add(getTxtDiscPrice());
		}
		return pnlDiscPrice;
	}
	private JButton getBtnRemoveAll() {
		if (btnRemoveAll == null) {
			btnRemoveAll = new JButton("RemoveAll");
			btnRemoveAll.addActionListener(new RemoveAllHandler());
			btnRemoveAll.setEnabled(isRemoveEnabled());
		}
		return btnRemoveAll;
	}
	private JLabel getLblTotalPrice() {
		if (lblTotalPrice == null) {
			lblTotalPrice = new JLabel("TotalPrice");
		}
		return lblTotalPrice;
	}
	private JTextField getTxtTotalPrice() {
		if (txtTotalPrice == null) {
			txtTotalPrice = new JTextField();
			txtTotalPrice.setEditable(false);
			txtTotalPrice.setColumns(10);
		}
		return txtTotalPrice;
	}
	private JLabel getLblDiscPrice() {
		if (lblDiscPrice == null) {
			lblDiscPrice = new JLabel("Disc Price");
		}
		return lblDiscPrice;
	}
	private JTextField getTxtDiscPrice() {
		if (txtDiscPrice == null) {
			txtDiscPrice = new JTextField();
			txtDiscPrice.setEditable(false);
			txtDiscPrice.setColumns(10);
		}
		return txtDiscPrice;
	}
	private JPanel getPnlWest() {
		if (pnlWest == null) {
			pnlWest = new JPanel();
			pnlWest.setPreferredSize(new Dimension(300, 300));
			pnlWest.setMinimumSize(new Dimension(300, 10));
			pnlWest.setLayout(new BorderLayout(0, 0));
			pnlWest.add(getSPnList());
		}
		return pnlWest;
	}
	private JScrollPane getSPnList() {
		if (sPnList == null) {
			sPnList = new JScrollPane();
			sPnList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			sPnList.setViewportView(getPnlList());
		}
		return sPnList;
	}
	private JPanel getPnlList() {
		if (pnlList == null) {
			pnlList = new JPanel();
			pnlList.setBackground(Color.LIGHT_GRAY);
			pnlList.setLayout(new BoxLayout(pnlList, BoxLayout.Y_AXIS));
			fillCartList();
		}
		return pnlList;
	}

// Métodos de gestión
	
	/**
	 * Rellena la lista de la cesta con los items de esta
	 */
	private void fillCartList() {
		Set<Entry<Item,Integer>> set = manager.getCartSet();
		for (Map.Entry<Item, Integer> entry : set) {
		    CartItemPanel cip = new CartItemPanel(entry.getKey(), entry.getValue(), this);
		    pnlList.add(cip);	
		    pnlList.add(Box.createRigidArea(new Dimension(0,2)));	}
	}
	
	/**
	 * Actualiza el valor de los campos de precio
	 */
	private void updatePrices() {
		txtTotalPrice.setText(Formatter.formatCurrency(manager.getTotalPrice()));
		txtDiscPrice.setText(Formatter.formatCurrency(manager.getDiscPrice()));
	}
	
	/**
	 * Oculta y muestra elementos según se esté loggeado o no
	 */
	private void manageDiscount() {
		if (!manager.isLogged()) {
			lblTotalPrice.setFont(new Font("Dialog", Font.BOLD, 13));
			lblDiscPrice.setFont(new Font("Dialog", Font.PLAIN, 13));
			lblDiscPrice.setEnabled(false);
			txtDiscPrice.setEnabled(false);	}
		else {
			lblTotalPrice.setFont(new Font("Dialog", Font.PLAIN, 13));
			lblDiscPrice.setFont(new Font("Dialog", Font.BOLD, 13));
			lblDiscPrice.setEnabled(true);
			txtDiscPrice.setEnabled(true);		}
	}
	
	/**
	 * Actualiza el panel del carro
	 */
	private void repaintList() {
		pnlList.removeAll();
		fillCartList();
		pnlList.revalidate();
		pnlList.repaint();
	}
	
	@Override
	public void removeItem(Item item) {
		manager.removeItem(item);
		repaintList();
		updatePrices();
	}
	
	/**
	 * @return	número de invitados a la fiesta
	 */
	public int getAttendants() {
		return manager.getAttendants();
	}
	
	/**
	 * @return true si el botón de remove debe estar activado, false en caso contrario
	 */
	public boolean isRemoveEnabled() {
		return removeEnabled;
	}

// Localización	
	
	@Override
	public void locate() {
		Locale location = CurrentLocation.getLocale();
		bundle = ResourceBundle.getBundle("rcs/Cart",location);
		// Sustitución de textos
		setTitle(bundle.getString("titleCart"));
		lblCart.setText(bundle.getString("lblCart"));
		lblTotalPrice.setText(bundle.getString("totalPrice"));
		lblDiscPrice.setText(bundle.getString("discPrice"));
		btnRemoveAll.setText(bundle.getString("removeAll"));
		btnBack.setText(bundle.getString("btnBack"));
		// Sustitución de mnemónicos	
		btnRemoveAll.setMnemonic(bundle.getString("mneRemoveAll").charAt(0));
		btnBack.setMnemonic(bundle.getString("mneBack").charAt(0));
		// Sustitución de tooltips	
		imgHelp.setToolTipText(bundle.getString("toolHelp"));
		btnRemoveAll.setToolTipText(bundle.getString("toolRemoveAll"));
		btnBack.setToolTipText(bundle.getString("toolBack"));
	}
	
// Gestores de eventos
	
	/**
	 * Manejador del botón para vaciar el carro
	 */
	class RemoveAllHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			manager.removeAll();
			repaintList();
			updatePrices();
		}
	}
	
// Ayuda

	private void createHelp(){
		URL hsURL;
		HelpSet hs;
		try {
			File fichero = new File("help/Ayuda.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);	}
		catch (Exception e){
			System.out.println("Ayuda no encontrada");
			return;	}	
		HelpBroker hb = hs.createHelpBroker();
		hb.initPresentation();
		hb.enableHelpKey(getRootPane(),"cesta", hs);
		hb.enableHelpOnButton(imgHelp, "cesta", hs);
	}
	
}