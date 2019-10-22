package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.help.*;
import java.net.*;
import java.io.*;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;

import interfaces.Localizable;
import logic.CurrentLocation;
import logic.Manager;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import common.ComboBoxMaker;
import common.Item;
import eventHandlers.FocusManager;
import eventHandlers.ImageButtonChanger;
import eventHandlers.LocationChanger;
import file_util.Formatter;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase principal que inicia la ventna principal y el programa
 * @author Ricardo Soto Estévez, uo265710
 */
public class Main extends JFrame implements Localizable {

	// Serial
	private static final long serialVersionUID = 4550584654507000657L;
	
	// Pestañas
	public static final int LOGIN = 0;
	public static final int SELECTION = 1;
	public static final int DATA_INPUT = 2;
	public static final int CONFIRMATION = 3;
	
	// Rutas de imágenes
	public static final String ICON = "/img/icon.png";
	public static final String IMG_ICON_MICRO = "/img/icon_micro.png";
	public static final String IMG_EMPTY = "/img/empty.png";
	public static final String IMG_GL_SE = "/img/gl_se.png";					// Banderas
	public static final String IMG_SP_SE = "/img/sp_se.png";
	public static final String IMG_UK_SE = "/img/uk_se.png";
	public static final String IMG_HELP = "/img/help.png";						// Botones superiores
	public static final String IMG_HELP_IN = "/img/help_in.png";
	public static final String IMG_HELP_SE = "/img/help_se.png";
	public static final String IMG_CART = "/img/cart.png";
	public static final String IMG_CART_IN = "/img/cart_in.png";
	public static final String IMG_CART_SE = "/img/cart_se.png";
	public static final String IMG_LOGIN = "/img/login.png";
	public static final String IMG_LOGIN_IN = "/img/login_in.png";
	public static final String IMG_LOGIN_SE = "/img/login_se.png";
	public static final String IMG_LOGOUT = "/img/logout.png";
	public static final String IMG_LOGOUT_IN = "/img/logout_in.png";
	public static final String IMG_LOGOUT_SE = "/img/logout_se.png";
	
	// Elementos lógicos
	private Manager manager;
	private ResourceBundle bundle;
	private Item selectedItem;
	
	// Elementos de navegación
	private int currentTab;
	private boolean visitedDataInput;
	
	// Mensajes de sistema
	private int wrongAddition = 0;
	private String cancelDialog;
	private String closeWarning;
	private String wrongUsernameWarning;
	private String wrongPasswordWarning;
	private String alreadyAddedWarning;
	private String invalidCartWarning;
	private String dataEmptyFieldsWarning;
	private String replaceDialog;
	private String confirmDialog;
	private String guest;
	
	// Event handlers
	private ComboBoxHandler cbh;
	private ImageButtonChanger ibcLogIn;
	private ImageButtonChanger ibcLogOut;
	private LogOutAsker loa;
	private LogInAsker lia;
	
	// Elementos visuales
		// Vista general
	private JPanel pnlNorth;						// Panel norte
		private JPanel pnlNorthWest;
		private JPanel pnlNorthEast;
	private JPanel pnlSouth;						// Panel sur
		private JPanel pnlButtons;
		private JPanel pnlFlags;
	private JTabbedPane tabbedPane;					//Panel central
		private JPanel pnlTabLogIn;						//Subpaneles
		private JPanel pnlTabSelection;
		private JPanel pnlTabDataInput;
		private JPanel pnlTabConfirmation;
	private JLabel imgLogo;
	private JLabel lblLoggedAs;
	private JButton btnBack;						// Botones de control
	private JButton btnOkNext;
	private JButton btnCancel;
	private JButton imgEs;							// Banderas
	private JButton imgGl;
	private JButton imgEn;
	private JButton imgLogInOut;					// Iconos superiores
	private JButton imgCart;
	private JButton imgHelp;
		// Pestaña log-in
	private JPanel pnlLogInEast;
	private JPanel pnlLogInMid;
	private JPanel pnlLogInWest;
	private JLabel lblSignUp;						// Lado registro
	private JLabel lblSignUpIntro;
	private JButton btnSignUp;
	private JLabel imgMidLogo;						// Imagen central
	private JLabel lblLogIn;						// Lado log-in
	private JLabel lblUsername;
	private JTextField txtUsername;
	private JLabel lblPassword;
	private JPasswordField pFPass;
	private JButton btnLogIn;
	private JLabel imgSeparator;
	private JLabel lblGuest;
	private JButton btnGuest;
		// Pestaña selección
	private JPanel pnlSelectionEast;
	private JPanel pnlSelectionWest;
		private JLabel imgSelection;				// Imagen del artículo
		private JTextArea txtADescription;			// Descripción del artículo
		private JPanel pnlSelectionNorthEast;		// Selectores
			private JPanel pnlSelectionPrice;
			private JPanel pnlAttendants;
			private JPanel pnlLocale;
			private JPanel pnlDecoration;
			private JPanel pnlDrinks;
			private JPanel pnlFood;
			private JPanel pnlOthers;
			private JLabel lblAttendants;
			private JLabel lblLocale;
			private JLabel lblDecoration;
			private JLabel lblDrinks;
			private JLabel lblFood;
			private JLabel lblOthers;
			private JTextField txtPrice;
			private JLabel lblPrice;
			private JSpinner spAttendants;
			private JSpinner spDrinks;
			private JSpinner spFood;
			private JButton btnLocale;
			private JButton btnDecoration;
			private JButton btnDrinks;
			private JButton btnFood;
			private JButton btnOthers;
			private JComboBox<Item> cbLocale;
			private JComboBox<Item> cbDecoration;
			private JComboBox<Item> cbDrinks;
			private JComboBox<Item> cbFood;
			private JComboBox<Item> cbOthers;
			private JScrollPane spDescription;
		// Pestaña datos
	private JPanel pnlDataWest;
		private JPanel pnlUserData;
			private JPanel pnlName;
				private JLabel lblName;
				private JTextField txtName;
			private JPanel pnlSurname;
				private JLabel lblSurname;
				private JTextField txtSurname;
			private JPanel pnlNif;
				private JLabel lblNif;
				private JTextField txtNif;
			private JPanel pnlPhoneNumber;
				private JLabel lblPhone;
				private JTextField txtPhone;
		private JPanel pnlPartyData;
			private JPanel pnlDate;
				private JLabel lblDate;
				private MyDateChooser dateChooser;
			private JPanel pnlHour;
				private JLabel lblTime;
				private JComboBox<String> cbHour;
				private JLabel lblHourSep;
				private JComboBox<String> cbMinutes;
	private JPanel pnlDataEast;
		private JPanel pnlObservations;
			private JLabel lblObservations;
				private JScrollPane sPnObservations;
					private JTextArea txtAObservations;
		// Pestaña confirmación
	private JLabel lblBill;
	private JScrollPane sPnBill;
	private JTextArea txtABill;
	private JLabel lblContactPhone;
	private JPanel pnlContactPhone;
	private JTextField txtContactPhone;

			
	/**
	 * Inicia la aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main main = new Main();
					main.setLocationRelativeTo(null);
					main.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea el diálogo de la aplicacion
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public Main() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource(ICON)));
		UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		// Creación de elementos
		this.manager = new Manager();
		cbh = new ComboBoxHandler();
		this.visitedDataInput = false;
		// Diseño de la ventana
		setTitle("setTitlePerLabel");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setMinimumSize(new Dimension(700, 500));
		getContentPane().add(getPnlNorth(), BorderLayout.NORTH);
		getContentPane().add(getPnlSouth(), BorderLayout.SOUTH);
		getContentPane().add(getTabbedPane(), BorderLayout.CENTER);
		// Gestores de focos
		this.addWindowFocusListener(new FocusManager(this, txtUsername));
		this.addWindowFocusListener(new PriceUpdater());
		this.addWindowFocusListener(new LocationUpdater());
		this.addWindowListener(new ClosingListener());
		// Localización al idioma local del ordenador
		manager.changeLocation(Locale.getDefault(Locale.Category.FORMAT));
		// Botón ESC por defecto
		getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "CANCEL");
		getRootPane().getActionMap().put("CANCEL", btnCancel.getAction());
		// Carga de la ayuda
		createHelp();
		// Inicia en la primera pestaña
		goToLogInTab();
	}

// Elementos visuales
	
	// Paneles generales
	
	private JPanel getPnlNorth() {
		if (pnlNorth == null) {
			pnlNorth = new JPanel();
			pnlNorth.setLayout(new GridLayout(0, 2, 0, 0));
			pnlNorth.add(getPnlNorthWest());
			pnlNorth.add(getPnlNorthEast());
		}
		return pnlNorth;
	}
	private JPanel getPnlNorthWest() {
		if (pnlNorthWest == null) {
			pnlNorthWest = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlNorthWest.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlNorthWest.add(getImgLogo());
		}
		return pnlNorthWest;
	}
	private JPanel getPnlNorthEast() {
		if (pnlNorthEast == null) {
			pnlNorthEast = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlNorthEast.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnlNorthEast.add(getLblLoggedAs());
			pnlNorthEast.add(getImgLogInOut());
			pnlNorthEast.add(getImgCart());
			pnlNorthEast.add(getImgHelp());
		}
		return pnlNorthEast;
	}
	private JPanel getPnlSouth() {
		if (pnlSouth == null) {
			pnlSouth = new JPanel();
			pnlSouth.setLayout(new GridLayout(0, 2, 0, 0));
			pnlSouth.add(getPnlFlags());
			pnlSouth.add(getPnlButtons());
		}
		return pnlSouth;
	}
	private JPanel getPnlButtons() {
		if (pnlButtons == null) {
			pnlButtons = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlButtons.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnlButtons.add(getBtnBack());
			pnlButtons.add(getBtnOkNext());
			pnlButtons.add(getBtnCancel());
		}
		return pnlButtons;
	}
	private JPanel getPnlFlags() {
		if (pnlFlags == null) {
			pnlFlags = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlFlags.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlFlags.add(getImgEs());
			pnlFlags.add(getImgGl());
			pnlFlags.add(getImgEn());
		}
		return pnlFlags;
	}
	private JTabbedPane getTabbedPane() throws IOException {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setFocusable(false);
			tabbedPane.addTab("tabLogIn", null, getPnlTabLogIn(), null);
			tabbedPane.addTab("tabSelection", null, getPnlTabSelection(), null);
			tabbedPane.addTab("tabDataInput", null, getPnlTabDataInput(), null);
			tabbedPane.addTab("tabConfirmation", null, getPnlTabConfirmation(), null);
		}
		return tabbedPane;
	}
	private JPanel getPnlTabLogIn() throws IOException {
		if (pnlTabLogIn == null) {
//			pnlTabLogIn = new JPanel();
			// Comentar esta línea y descomentar la anterior para rehabilitar WindowBuilder
			pnlTabLogIn = new BackgroundPanel(ImageIO.read(new File("./resources/img/mainbackground.png")));
			// Define las características del GridBag
			GridBagLayout gbl_pnlTabLogIn = new GridBagLayout();
			gbl_pnlTabLogIn.columnWidths = new int[]{100,250,100,250,100};
			gbl_pnlTabLogIn.rowHeights = new int[]{0};
			gbl_pnlTabLogIn.columnWeights = new double[]{0.2,0.3,0.0,0.3,0.2};
			gbl_pnlTabLogIn.rowWeights = new double[]{Double.MIN_VALUE};
			pnlTabLogIn.setLayout(gbl_pnlTabLogIn);
			// Introduce el panel izquierdo
			GridBagConstraints gbc_pnlEast = new GridBagConstraints();
			gbc_pnlEast.fill = GridBagConstraints.HORIZONTAL;
			gbc_pnlEast.insets = new Insets(0, 0, 0, 0);
			gbc_pnlEast.gridx = 1;
			gbc_pnlEast.gridy = 0;
			pnlTabLogIn.add(getPnlLogInEast(), gbc_pnlEast);
			// Introduce la imagen central
			GridBagConstraints gbc_pnlMiddle = new GridBagConstraints();
			gbc_pnlMiddle.fill = GridBagConstraints.HORIZONTAL;
			gbc_pnlMiddle.insets = new Insets(0, 0, 0, 0);
			gbc_pnlMiddle.gridx = 2;
			gbc_pnlMiddle.gridy = 0;
			pnlTabLogIn.add(getPnlLogInMid(), gbc_pnlMiddle);
			// Introduce el panel derecho
			GridBagConstraints gbc_pnlWest = new GridBagConstraints();
			gbc_pnlWest.fill = GridBagConstraints.HORIZONTAL;
			gbc_pnlWest.insets = new Insets(0, 0, 0, 0);
			gbc_pnlWest.gridx = 3;
			gbc_pnlWest.gridy = 0;
			pnlTabLogIn.add(getPnlLogInWest(), gbc_pnlWest);
		}
		return pnlTabLogIn;
	}
	private JPanel getPnlTabSelection() {
		if (pnlTabSelection == null) {
			pnlTabSelection = new JPanel();
			// Define las características del GridBag
			GridBagLayout gbl_pnlTabSelection = new GridBagLayout();
			gbl_pnlTabSelection.columnWidths = new int[]{200,50,400};
			gbl_pnlTabSelection.rowHeights = new int[]{0};
			gbl_pnlTabSelection.columnWeights = new double[]{0.0,1.5,2.0};
			gbl_pnlTabSelection.rowWeights = new double[]{Double.MIN_VALUE};
			pnlTabSelection.setLayout(gbl_pnlTabSelection);
			// Introduce el panel izquierdo
			GridBagConstraints gbc_pnlSelectionWest = new GridBagConstraints();
			gbc_pnlSelectionWest.fill = GridBagConstraints.BOTH;
			gbc_pnlSelectionWest.insets = new Insets(0, 0, 0, 0);
			gbc_pnlSelectionWest.gridx = 0;
			gbc_pnlSelectionWest.gridy = 0;
			pnlTabSelection.add(getPnlSelectionWest(), gbc_pnlSelectionWest);
			// Introduce el panel derecho
			GridBagConstraints gbc_pnlEast = new GridBagConstraints();
			gbc_pnlEast.fill = GridBagConstraints.HORIZONTAL;
			gbc_pnlEast.insets = new Insets(0, 0, 0, 0);
			gbc_pnlEast.gridx = 2;
			gbc_pnlEast.gridy = 0;
			pnlTabSelection.add(getPnlSelectionEast(), gbc_pnlEast);
		}
		return pnlTabSelection;
	}
	private JPanel getPnlTabDataInput() {
		if (pnlTabDataInput == null) {
			pnlTabDataInput = new JPanel();
			pnlTabDataInput.setLayout(new GridLayout(0, 2, 0, 0));
			pnlTabDataInput.add(getPnlDataWest());
			pnlTabDataInput.add(getPnlDataEast());
		}
		return pnlTabDataInput; 
	}
	private JPanel getPnlTabConfirmation() {
		if (pnlTabConfirmation == null) {
			pnlTabConfirmation = new JPanel();
			pnlTabConfirmation.setLayout(new BoxLayout(pnlTabConfirmation, BoxLayout.Y_AXIS));
			pnlTabConfirmation.add(getLblBill());
			pnlTabConfirmation.add(Box.createRigidArea(new Dimension(0,10)));
			pnlTabConfirmation.add(getSPnBill());
			pnlTabConfirmation.add(Box.createRigidArea(new Dimension(0,10)));
			pnlTabConfirmation.add(getPnlContactPhone());
			pnlTabConfirmation.add(Box.createRigidArea(new Dimension(0,20)));
		}
		return pnlTabConfirmation;
	}
	
	// Elementos generales
	
	private JLabel getImgLogo() {
		if (imgLogo == null) {
			imgLogo = new JLabel("");
			imgLogo.setHorizontalAlignment(SwingConstants.LEFT);
			imgLogo.setIcon(new ImageIcon(Main.class.getResource("/img/logoLittle.png")));
		}
		return imgLogo;
	}
	private JLabel getLblLoggedAs() {
		if (lblLoggedAs == null) {
			lblLoggedAs = new JLabel("lblLoggedAs");
			lblLoggedAs.setFont(new Font("Dialog", Font.BOLD, 12));
		}
		return lblLoggedAs;
	}
			// Botones inferiores
	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("btnBack");
			btnBack.addActionListener(new TriggerPreviousPage());
		}
		return btnBack;
	}
	private JButton getBtnOkNext() {
		if (btnOkNext == null) {
			btnOkNext = new JButton("btnOkNext");
			btnOkNext.addActionListener(new ContinueTrigger());
		}
		return btnOkNext;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("btnCancel");
			btnCancel.setAction(new AbstractAction() {
				private static final long serialVersionUID = -3958897981605815084L;
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cancelAction();
				}
			});
		}
		return btnCancel;
	}
			// Banderas
	private JButton getImgEs() {
		if (imgEs == null) {
			imgEs = new JButton("ES");
			imgEs.setRolloverIcon(new ImageIcon(Main.class.getResource(IMG_SP_SE)));
			imgEs.setSelectedIcon(new ImageIcon(Main.class.getResource(IMG_SP_SE)));
			imgEs.setBorderPainted(false);
			imgEs.setContentAreaFilled(false);
			imgEs.setMnemonic('e');
			imgEs.addActionListener(new LocationChanger(this));
			imgEs.addFocusListener(new ImageButtonChanger(imgEs.getRolloverIcon()));
		}
		return imgEs;
	}
	private JButton getImgGl() {
		if (imgGl == null) {
			imgGl = new JButton("GL");
			imgGl.setRolloverIcon(new ImageIcon(Main.class.getResource(IMG_GL_SE)));
			imgGl.setSelectedIcon(new ImageIcon(Main.class.getResource(IMG_GL_SE)));
			imgGl.setBorderPainted(false);
			imgGl.setContentAreaFilled(false);
			imgGl.setMnemonic('g');
			imgGl.addActionListener(new LocationChanger(this));
			imgGl.addFocusListener(new ImageButtonChanger(imgGl.getRolloverIcon()));
		}
		return imgGl;
	}
	private JButton getImgEn() {
		if (imgEn == null) {
			imgEn = new JButton("EN");
			imgEn.setRolloverIcon(new ImageIcon(Main.class.getResource(IMG_UK_SE)));
			imgEn.setSelectedIcon(new ImageIcon(Main.class.getResource(IMG_UK_SE)));
			imgEn.setBorderPainted(false);
			imgEn.setContentAreaFilled(false);
			imgEn.setMnemonic('n');
			imgEn.addActionListener(new LocationChanger(this));
			imgEn.addFocusListener(new ImageButtonChanger(imgEn.getRolloverIcon()));
		}
		return imgEn;
	}
			// Botones superiores
	private JButton getImgLogInOut() {
		if (imgLogInOut == null) {
			imgLogInOut = new JButton("");
			imgLogInOut.setIcon(new ImageIcon(Main.class.getResource(IMG_LOGIN_IN)));
			imgLogInOut.setRolloverIcon(new ImageIcon(Main.class.getResource(IMG_LOGIN)));
			imgLogInOut.setBorderPainted(false);
			imgLogInOut.setContentAreaFilled(false);
			imgLogInOut.setMnemonic('l');
			ibcLogIn = new ImageButtonChanger(new ImageIcon(Main.class.getResource(IMG_LOGIN_SE)));
			ibcLogOut = new ImageButtonChanger(new ImageIcon(Main.class.getResource(IMG_LOGOUT_SE)));
			loa = new LogOutAsker();
			lia = new LogInAsker();
			imgLogInOut.addFocusListener(ibcLogIn);
			imgLogInOut.addActionListener(lia);
		}
		return imgLogInOut;
	}
	private JButton getImgCart() {
		if (imgCart == null) {
			imgCart = new JButton("");
			imgCart.setIcon(new ImageIcon(Main.class.getResource(IMG_CART_IN)));
			imgCart.setRolloverIcon(new ImageIcon(Main.class.getResource(IMG_CART)));
			imgCart.setBorderPainted(false);
			imgCart.setContentAreaFilled(false);
			imgCart.setMnemonic('c');
			imgCart.addFocusListener(new ImageButtonChanger(new ImageIcon(Main.class.getResource(IMG_CART_SE))));
			imgCart.addActionListener(new TriggerCart());
		}
		return imgCart;
	}
	private JButton getImgHelp() {
		if (imgHelp == null) {
			imgHelp = new JButton("");
			imgHelp.setIcon(new ImageIcon(Main.class.getResource(IMG_HELP_IN)));
			imgHelp.setRolloverIcon(new ImageIcon(Main.class.getResource(IMG_HELP)));
			imgHelp.setBorderPainted(false);
			imgHelp.setContentAreaFilled(false);
			imgHelp.addFocusListener(new ImageButtonChanger(new ImageIcon(Main.class.getResource(IMG_HELP_SE))));
		}
		return imgHelp;
	}
	
	// Panel logIn
		// Botón de registro
	private JPanel getPnlLogInEast() {
		if (pnlLogInEast == null) {
			pnlLogInEast = new JPanel();
			pnlLogInEast.setBackground(new Color(235,255,255));
			pnlLogInEast.setBorder(new LineBorder(Color.blue,1,true));
			pnlLogInEast.setLayout(new BoxLayout(pnlLogInEast, BoxLayout.Y_AXIS));
			pnlLogInEast.add(Box.createRigidArea(new Dimension(0,10)));
			pnlLogInEast.add(getLblSignUp());
			pnlLogInEast.add(Box.createRigidArea(new Dimension(0,5)));
			pnlLogInEast.add(getLblSignUpIntro());
			pnlLogInEast.add(Box.createRigidArea(new Dimension(0,5)));
			pnlLogInEast.add(getBtnSignUp());
			pnlLogInEast.add(Box.createRigidArea(new Dimension(0,10)));
		}
		return pnlLogInEast;
	}
		// Imagen central
	private JPanel getPnlLogInMid() {
		if (pnlLogInMid == null) {
			pnlLogInMid = new JPanel();
			pnlLogInMid.add(getImgMidLogo());
		}
		return pnlLogInMid;
	}
		// Formulario de inicio de sesión
	private JPanel getPnlLogInWest() {
		if (pnlLogInWest == null) {
			pnlLogInWest = new JPanel();
			pnlLogInWest.setBackground(new Color(235,255,255));
			pnlLogInWest.setBorder(new LineBorder(Color.blue,1,true));
			pnlLogInWest.setLayout(new BoxLayout(pnlLogInWest, BoxLayout.Y_AXIS));
			pnlLogInWest.add(Box.createRigidArea(new Dimension(0,10)));
			pnlLogInWest.add(getLblLogIn());
			pnlLogInWest.add(Box.createRigidArea(new Dimension(0,5)));
			pnlLogInWest.add(getLblUsername());
			pnlLogInWest.add(getTxtUsername());
			pnlLogInWest.add(Box.createRigidArea(new Dimension(0,5)));
			pnlLogInWest.add(getLblPassword());
			pnlLogInWest.add(getPFPass());
			pnlLogInWest.add(Box.createRigidArea(new Dimension(0,5)));
			pnlLogInWest.add(getBtnLogIn());
			pnlLogInWest.add(Box.createRigidArea(new Dimension(0,10)));
			pnlLogInWest.add(getImgSeparator());
			pnlLogInWest.add(Box.createRigidArea(new Dimension(0,10)));
			pnlLogInWest.add(getLblGuest());
			pnlLogInWest.add(getBtnGuest());
			pnlLogInWest.add(Box.createRigidArea(new Dimension(0,10)));
		}
		return pnlLogInWest;
	}
		// Elementos del lado de registro
	private JLabel getLblSignUp() {
		if (lblSignUp == null) {
			lblSignUp = new JLabel("lblSignUp");
			lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
			lblSignUp.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblSignUp.setFont(new Font("Dialog", Font.BOLD, 16));
		}
		return lblSignUp;
	}
	private JLabel getLblSignUpIntro() {
		if (lblSignUpIntro == null) {
			lblSignUpIntro = new JLabel("<html><div align=center>Nuestros usuarios registrados cuentan con ventajas como una l\\u00EDnea de contacto directo con los gestores de tu fiesta y un suculento descuento del 15%. Si no est\\u00E1s todav\\u00EDa registrado, \\u00BFa qu\\u00E9 esperas?</div></html>\r\n");
			lblSignUpIntro.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblSignUpIntro.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblSignUpIntro;
	}
	private JButton getBtnSignUp() {
		if (btnSignUp == null) {
			btnSignUp = new JButton("btnSignUp");
			btnSignUp.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnSignUp.addActionListener(new TriggerSignUpWindow());
		}
		return btnSignUp;
	}
		// Logo central
	private JLabel getImgMidLogo() {
		if (imgMidLogo == null) {
			imgMidLogo = new JLabel("");
			imgMidLogo.setIcon(new ImageIcon(Main.class.getResource("/img/middleStart.png")));
		}
		return imgMidLogo;
	}
		// Elementos del formulario
	private JLabel getLblLogIn() {
		if (lblLogIn == null) {
			lblLogIn = new JLabel("lblLogIn");
			lblLogIn.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
			lblLogIn.setFont(new Font("Dialog", Font.BOLD, 16));
		}
		return lblLogIn;
	}
	private JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel("lblUsername");
			lblUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblUsername;
	}
	private JTextField getTxtUsername() {
		if (txtUsername == null) {
			txtUsername = new JTextField();
			txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
			txtUsername.setColumns(15);
		}
		return txtUsername;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("lblPass");
			lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPassword;
	}
	private JPasswordField getPFPass() {
		if (pFPass == null) {
			pFPass = new JPasswordField();
			pFPass.setHorizontalAlignment(SwingConstants.CENTER);
			pFPass.setColumns(15);
		}
		return pFPass;
	}
	private JButton getBtnLogIn() {
		if (btnLogIn == null) {
			btnLogIn = new JButton("btnLogIn");
			btnLogIn.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnLogIn.addActionListener(new LogInChecker());
		}
		return btnLogIn;
	}
	private JLabel getImgSeparator() {
		if (imgSeparator == null) {
			imgSeparator = new JLabel("");
			imgSeparator.setIcon(new ImageIcon(Main.class.getResource("/img/separator.png")));
			imgSeparator.setHorizontalAlignment(SwingConstants.CENTER);
			imgSeparator.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return imgSeparator;
	}
	private JLabel getLblGuest() {
		if (lblGuest == null) {
			lblGuest = new JLabel("lblGuest");
			lblGuest.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblGuest.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblGuest;
	}
	private JButton getBtnGuest() {
		if (btnGuest == null) {
			btnGuest = new JButton("btnGuest");
			btnGuest.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnGuest.addActionListener(new GuestLogger());
		}
		return btnGuest;
	}
	
	// Panel de selección de artículos
	private JPanel getPnlSelectionWest() {
		if (pnlSelectionWest == null) {
			pnlSelectionWest = new JPanel();
			pnlSelectionWest.setMinimumSize(new Dimension(200, 10));
			pnlSelectionWest.setPreferredSize(new Dimension(600, 600));
			pnlSelectionWest.setLayout(new GridLayout(2, 1, 5, 5));
			pnlSelectionWest.add(getImgSelection());
			pnlSelectionWest.add(getSpDescription());
		}
		return pnlSelectionWest;
	}
	private JPanel getPnlSelectionEast() {
		if (pnlSelectionEast == null) {
			pnlSelectionEast = new JPanel();
			GridBagLayout gbl_pnlSelectionEast = new GridBagLayout();
			gbl_pnlSelectionEast.columnWidths = new int[]{0};
			gbl_pnlSelectionEast.rowHeights = new int[]{400,100};
			gbl_pnlSelectionEast.columnWeights = new double[]{Double.MIN_VALUE};
			gbl_pnlSelectionEast.rowWeights = new double[]{0.8,0.2};
			pnlSelectionEast.setLayout(gbl_pnlSelectionEast);
			// Introduce el panel superior
			GridBagConstraints gbc_pnlNorthEast = new GridBagConstraints();
			gbc_pnlNorthEast.fill = GridBagConstraints.BOTH;
			gbc_pnlNorthEast.insets = new Insets(0, 20, 0, 10);
			gbc_pnlNorthEast.gridx = 0;
			gbc_pnlNorthEast.gridy = 0;
			pnlSelectionEast.add(getPnlSelectionNorthEast(), gbc_pnlNorthEast);
			// Introduce el panel derecho
			GridBagConstraints gbc_pnlPrice = new GridBagConstraints();
			gbc_pnlPrice.fill = GridBagConstraints.HORIZONTAL;
			gbc_pnlPrice.insets = new Insets(0, 20, 0, 10);
			gbc_pnlPrice.gridx = 0;
			gbc_pnlPrice.gridy = 1;
			pnlSelectionEast.add(getPnlSelectionPrice(), gbc_pnlPrice);
		}
		return pnlSelectionEast;
	}
	private JLabel getImgSelection() {
		if (imgSelection == null) {
			imgSelection = new JLabel("");
			imgSelection.addComponentListener(new ImageResizer());
		}
		return imgSelection;
	}
	private JScrollPane getSpDescription() {
		if (spDescription == null) {
			spDescription = new JScrollPane();
			spDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			spDescription.setViewportView(getTxtADescription());
		}
		return spDescription;
	}
	private JTextArea getTxtADescription() {
		if (txtADescription == null) {
			txtADescription = new JTextArea();
			txtADescription.setFocusable(false);
			txtADescription.setLineWrap(true);
			txtADescription.setWrapStyleWord(true);
			txtADescription.setEditable(false);
		}
		return txtADescription;
	}
	private JPanel getPnlSelectionNorthEast() {
		if (pnlSelectionNorthEast == null) {
			pnlSelectionNorthEast = new JPanel();
			pnlSelectionNorthEast.setLayout(new GridLayout(6, 0, 5, 5));
			pnlSelectionNorthEast.add(getPnlAttendants());
			pnlSelectionNorthEast.add(getPnlLocale());
			pnlSelectionNorthEast.add(getPnlDecoration());
			pnlSelectionNorthEast.add(getPnlDrinks());
			pnlSelectionNorthEast.add(getPnlFood());
			pnlSelectionNorthEast.add(getPnlOthers());
		}
		return pnlSelectionNorthEast;
	}
	private JPanel getPnlSelectionPrice() {
		if (pnlSelectionPrice == null) {
			pnlSelectionPrice = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlSelectionPrice.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnlSelectionPrice.add(getLblPrice());
			pnlSelectionPrice.add(getTxtPrice());
		}
		return pnlSelectionPrice;
	}
	private JPanel getPnlAttendants() {
		if (pnlAttendants == null) {
			pnlAttendants = new JPanel();
			GridBagLayout gbl_pnl = new GridBagLayout();
			gbl_pnl.columnWidths = new int[]{175,200,75,15};
			gbl_pnl.rowHeights = new int[]{0};
			gbl_pnl.columnWeights = new double[]{0.6, 0.4, 0, 0};
			gbl_pnl.rowWeights = new double[]{Double.MIN_VALUE};
			pnlAttendants.setLayout(gbl_pnl);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.fill = GridBagConstraints.BOTH;
			gbc_label.insets = new Insets(0, 0, 0, 0);
			gbc_label.gridx = 0;
			gbc_label.gridy = 0;
			pnlAttendants.add(getLblAttendants(), gbc_label);
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.BOTH;
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 0;
			pnlAttendants.add(getSpAttendants(), gbc_spinner);
		}
		return pnlAttendants;
	}
	private JPanel getPnlLocale() {
		if (pnlLocale == null) {
			pnlLocale = new JPanel();
			GridBagLayout gbl_pnl = new GridBagLayout();
			gbl_pnl.columnWidths = new int[]{125,250,75,15};
			gbl_pnl.rowHeights = new int[]{0};
			gbl_pnl.columnWeights = new double[]{0.6, 0.4, 0, 0};
			gbl_pnl.rowWeights = new double[]{Double.MIN_VALUE};
			pnlLocale.setLayout(gbl_pnl);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.fill = GridBagConstraints.BOTH;
			gbc_label.insets = new Insets(0, 0, 0, 0);
			gbc_label.gridx = 0;
			gbc_label.gridy = 0;
			pnlLocale.add(getLblLocale(), gbc_label);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.BOTH;
			gbc_comboBox.insets = new Insets(3, 0, 3, 4);
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 0;
			pnlLocale.add(getCbLocale(), gbc_comboBox);
			GridBagConstraints gbc_add = new GridBagConstraints();
			gbc_add.fill = GridBagConstraints.BOTH;
			gbc_add.gridx = 3;
			gbc_add.gridy = 0;
			pnlLocale.add(getBtnLocale(), gbc_add);
		}
		return pnlLocale;
	}
	private JPanel getPnlDecoration() {
		if (pnlDecoration == null) {
			pnlDecoration = new JPanel();
			GridBagLayout gbl_pnl = new GridBagLayout();
			gbl_pnl.columnWidths = new int[]{125,250,75,15};
			gbl_pnl.rowHeights = new int[]{0};
			gbl_pnl.columnWeights = new double[]{0.6, 0.4, 0, 0};
			gbl_pnl.rowWeights = new double[]{Double.MIN_VALUE};
			pnlDecoration.setLayout(gbl_pnl);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.fill = GridBagConstraints.BOTH;
			gbc_label.insets = new Insets(0, 0, 0, 0);
			gbc_label.gridx = 0;
			gbc_label.gridy = 0;
			pnlDecoration.add(getLblDecoration(), gbc_label);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.BOTH;
			gbc_comboBox.insets = new Insets(3, 0, 3, 4);
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 0;
			pnlDecoration.add(getCbDecoration(), gbc_comboBox);
			GridBagConstraints gbc_add = new GridBagConstraints();
			gbc_add.fill = GridBagConstraints.BOTH;
			gbc_add.gridx = 3;
			gbc_add.gridy = 0;
			pnlDecoration.add(getBtnDecoration(), gbc_add);
		}
		return pnlDecoration;
	}
	private JPanel getPnlDrinks() {
		if (pnlDrinks == null) {
			pnlDrinks = new JPanel();
			GridBagLayout gbl_pnl = new GridBagLayout();
			gbl_pnl.columnWidths = new int[]{125,250,75,15};
			gbl_pnl.rowHeights = new int[]{0};
			gbl_pnl.columnWeights = new double[]{0.6, 0.4, 0, 0};
			gbl_pnl.rowWeights = new double[]{Double.MIN_VALUE};
			pnlDrinks.setLayout(gbl_pnl);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.fill = GridBagConstraints.BOTH;
			gbc_label.insets = new Insets(0, 0, 0, 0);
			gbc_label.gridx = 0;
			gbc_label.gridy = 0;
			pnlDrinks.add(getLblDrinks(), gbc_label);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.BOTH;
			gbc_comboBox.insets = new Insets(3, 0, 3, 0);
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 0;
			pnlDrinks.add(getCbDrinks(), gbc_comboBox);
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.BOTH;
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 0;
			pnlDrinks.add(getSpDrinks(), gbc_spinner);
			GridBagConstraints gbc_add = new GridBagConstraints();
			gbc_add.fill = GridBagConstraints.BOTH;
			gbc_add.gridx = 3;
			gbc_add.gridy = 0;
			pnlDrinks.add(getBtnDrinks(), gbc_add);
		}
		return pnlDrinks;
	}
	private JPanel getPnlFood() {
		if (pnlFood == null) {
			pnlFood = new JPanel();
			GridBagLayout gbl_pnl = new GridBagLayout();
			gbl_pnl.columnWidths = new int[]{125,250,75,15};
			gbl_pnl.rowHeights = new int[]{0};
			gbl_pnl.columnWeights = new double[]{0.6, 0.4, 0, 0};
			gbl_pnl.rowWeights = new double[]{Double.MIN_VALUE};
			pnlFood.setLayout(gbl_pnl);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.fill = GridBagConstraints.BOTH;
			gbc_label.insets = new Insets(0, 0, 0, 0);
			gbc_label.gridx = 0;
			gbc_label.gridy = 0;
			pnlFood.add(getLblFood(), gbc_label);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.BOTH;
			gbc_comboBox.insets = new Insets(3, 0, 3, 0);
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 0;
			pnlFood.add(getCbFood(), gbc_comboBox);
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.BOTH;
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 0;
			pnlFood.add(getSpFood(), gbc_spinner);
			GridBagConstraints gbc_add = new GridBagConstraints();
			gbc_add.fill = GridBagConstraints.BOTH;
			gbc_add.gridx = 3;
			gbc_add.gridy = 0;
			pnlFood.add(getBtnFood(), gbc_add);
		}
		return pnlFood;
	}
	private JPanel getPnlOthers() {
		if (pnlOthers == null) {
			pnlOthers = new JPanel();
			GridBagLayout gbl_pnl = new GridBagLayout();
			gbl_pnl.columnWidths = new int[]{125,250,75,15};
			gbl_pnl.rowHeights = new int[]{0};
			gbl_pnl.columnWeights = new double[]{0.6, 0.4, 0, 0};
			gbl_pnl.rowWeights = new double[]{Double.MIN_VALUE};
			pnlOthers.setLayout(gbl_pnl);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.fill = GridBagConstraints.BOTH;
			gbc_label.insets = new Insets(0, 0, 0, 0);
			gbc_label.gridx = 0;
			gbc_label.gridy = 0;
			pnlOthers.add(getLblOthers(), gbc_label);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.BOTH;
			gbc_comboBox.insets = new Insets(3, 0, 3, 4);
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 0;
			pnlOthers.add(getCbOthers(), gbc_comboBox);
			GridBagConstraints gbc_add = new GridBagConstraints();
			gbc_add.fill = GridBagConstraints.BOTH;
			gbc_add.gridx = 3;
			gbc_add.gridy = 0;
			pnlOthers.add(getBtnOthers(), gbc_add);
		}
		return pnlOthers;
	}
	private JLabel getLblAttendants() {
		if (lblAttendants == null) {
			lblAttendants = new JLabel("lblAttendants");
			lblAttendants.setLabelFor(getSpAttendants());
			lblAttendants.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return lblAttendants;
	}
	private JLabel getLblLocale() {
		if (lblLocale == null) {
			lblLocale = new JLabel("lblLocale");
			lblLocale.setLabelFor(getCbLocale());
			lblLocale.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return lblLocale;
	}
	private JLabel getLblDecoration() {
		if (lblDecoration == null) {
			lblDecoration = new JLabel("lblDecoration");
			lblDecoration.setLabelFor(getCbDecoration());
			lblDecoration.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return lblDecoration;
	}
	private JLabel getLblDrinks() {
		if (lblDrinks == null) {
			lblDrinks = new JLabel("lblDrinks");
			lblDrinks.setLabelFor(getCbDrinks());
			lblDrinks.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return lblDrinks;
	}
	private JLabel getLblFood() {
		if (lblFood == null) {
			lblFood = new JLabel("lblFood");
			lblFood.setLabelFor(getCbFood());
			lblFood.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return lblFood;
	}
	private JLabel getLblOthers() {
		if (lblOthers == null) {
			lblOthers = new JLabel("lblOthers");
			lblOthers.setLabelFor(getCbOthers());
			lblOthers.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return lblOthers;
	}
	private JSpinner getSpAttendants() {
		if (spAttendants == null) {
			SpinnerModel model = new SpinnerNumberModel(1, 1, 150, 1);
			spAttendants = new JSpinner(model);
			spAttendants.addChangeListener(new SpinnerHandler());
		}
		return spAttendants;
	}
	private JSpinner getSpDrinks() {
		if (spDrinks == null) {
			SpinnerModel model = new SpinnerNumberModel(1, 1, 150, 1);
			spDrinks = new JSpinner(model);
		}
		return spDrinks;
	}
	private JSpinner getSpFood() {
		if (spFood == null) {
			SpinnerModel model = new SpinnerNumberModel(1, 1, 150, 1);
			spFood = new JSpinner(model);
		}
		return spFood;
	}
	private JButton getBtnLocale() {
		if (btnLocale == null) {
			btnLocale = new JButton("+");
			btnLocale.addActionListener(new BtnAddHandler(cbLocale));
		}
		return btnLocale;
	}
	private JButton getBtnDecoration() {
		if (btnDecoration == null) {
			btnDecoration = new JButton("+");
			btnDecoration.addActionListener(new BtnAddHandler(cbDecoration));
		}
		return btnDecoration;
	}
	private JButton getBtnDrinks() {
		if (btnDrinks == null) {
			btnDrinks = new JButton("+");
			btnDrinks.addActionListener(new BtnAddHandler(cbDrinks, spDrinks));
		}
		return btnDrinks;
	}
	private JButton getBtnFood() {
		if (btnFood == null) {
			btnFood = new JButton("+");
			btnFood.addActionListener(new BtnAddHandler(cbFood,spFood));
		}
		return btnFood;
	}
	private JButton getBtnOthers() {
		if (btnOthers == null) {
			btnOthers = new JButton("+");
			btnOthers.addActionListener(new BtnAddHandler(cbOthers));
		}
		return btnOthers;
	}
	private JComboBox<Item> getCbLocale() {
		if (cbLocale == null) {
			cbLocale = new JComboBox<Item>(ComboBoxMaker.getLocaleModel(manager));
			cbLocale.addActionListener(cbh);
		}
		return cbLocale;
	}
	private JComboBox<Item> getCbDecoration() {
		if (cbDecoration == null) {
			cbDecoration = new JComboBox<Item>(ComboBoxMaker.getDecorationModel(manager));
			cbDecoration.addActionListener(cbh);
		}
		return cbDecoration;
	}
	private JComboBox<Item> getCbDrinks() {
		if (cbDrinks == null) {
			cbDrinks = new JComboBox<Item>(ComboBoxMaker.getDrinksModel(manager));
			cbDrinks.addActionListener(cbh);
		}
		return cbDrinks;
	}
	private JComboBox<Item> getCbFood() {
		if (cbFood == null) {
			cbFood = new JComboBox<Item>(ComboBoxMaker.getFoodModel(manager));
			cbFood.addActionListener(cbh);
		}
		return cbFood;
	}
	private JComboBox<Item> getCbOthers() {
		if (cbOthers == null) {
			cbOthers = new JComboBox<Item>(ComboBoxMaker.getOthersModel(manager));
			cbOthers.setFont(new Font("Dialog", Font.PLAIN, 12));
			cbOthers.addActionListener(cbh);
		}
		return cbOthers;
	}
	private JTextField getTxtPrice() {
		if (txtPrice == null) {
			txtPrice = new JTextField();
			txtPrice.setFocusable(false);
			txtPrice.setFont(new Font("Dialog", Font.PLAIN, 13));
			txtPrice.setEditable(false);
			txtPrice.setColumns(10);
		}
		return txtPrice;
	}
	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel("lblPrice:");
			lblPrice.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return lblPrice;
	}
	
	// Panel de introducción de datos
	
	private JPanel getPnlDataWest() {
		if (pnlDataWest == null) {
			pnlDataWest = new JPanel();
			GridBagLayout gbl_pnlDataWest = new GridBagLayout();
			gbl_pnlDataWest.columnWidths = new int[]{0};
			gbl_pnlDataWest.rowHeights = new int[]{150,150};
			gbl_pnlDataWest.columnWeights = new double[]{Double.MIN_VALUE};
			gbl_pnlDataWest.rowWeights = new double[]{0.0,0.0};
			pnlDataWest.setLayout(gbl_pnlDataWest);
			GridBagConstraints gbc_pnlUserData = new GridBagConstraints();
			gbc_pnlUserData.insets = new Insets(0, 10, 0, 0);
			gbc_pnlUserData.fill = GridBagConstraints.NONE;
			gbc_pnlUserData.anchor = GridBagConstraints.SOUTH;
			gbc_pnlUserData.gridx = 0;
			gbc_pnlUserData.gridy = 0;
			pnlDataWest.add(getPnlUserData(), gbc_pnlUserData);
			GridBagConstraints gbc_pnlPartyData = new GridBagConstraints();
			gbc_pnlPartyData.insets = new Insets(0, 10, 0, 0);
			gbc_pnlPartyData.fill = GridBagConstraints.NONE;
			gbc_pnlPartyData.anchor = GridBagConstraints.CENTER;
			gbc_pnlPartyData.gridx = 0;
			gbc_pnlPartyData.gridy = 1;
			pnlDataWest.add(getPnlPartyData(), gbc_pnlPartyData);
		}
		return pnlDataWest;
	}
	private JPanel getPnlDataEast() {
		if (pnlDataEast == null) {
			pnlDataEast = new JPanel();
			GridBagLayout gbl_pnlDataEast = new GridBagLayout();
			gbl_pnlDataEast.columnWidths = new int[]{0, 0};
			gbl_pnlDataEast.rowHeights = new int[]{200,100};
			gbl_pnlDataEast.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_pnlDataEast.rowWeights = new double[]{1.0,0.5};
			pnlDataEast.setLayout(gbl_pnlDataEast);
			GridBagConstraints gbc_pnlObservations = new GridBagConstraints();
			gbc_pnlObservations.insets = new Insets(20, 0, 5, 10);
			gbc_pnlObservations.fill = GridBagConstraints.BOTH;
			gbc_pnlObservations.anchor = GridBagConstraints.CENTER;
			gbc_pnlObservations.gridx = 0;
			gbc_pnlObservations.gridy = 0;
			pnlDataEast.add(getPnlObservations(), gbc_pnlObservations);
		}
		return pnlDataEast;
	}
	private JPanel getPnlUserData() {
		if (pnlUserData == null) {
			pnlUserData = new JPanel();
			pnlUserData.setBorder(new TitledBorder(new LineBorder(new Color(100, 149, 237), 1, true), "pnlUserData", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlUserData.setLayout(new BoxLayout(pnlUserData, BoxLayout.Y_AXIS));
			pnlUserData.add(getPnlName());
			pnlUserData.add(getPnlSurname());
			pnlUserData.add(getPnlNif());
			pnlUserData.add(getPnlPhoneNumber());
		}
		return pnlUserData;
	}
	private JPanel getPnlPartyData() {
		if (pnlPartyData == null) {
			pnlPartyData = new JPanel();
			pnlPartyData.setBorder(new TitledBorder(new LineBorder(new Color(100, 149, 237), 1, true), "pnlPartyData", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlPartyData.setLayout(new BoxLayout(pnlPartyData, BoxLayout.Y_AXIS));
			pnlPartyData.add(getPnlDate());
			pnlPartyData.add(getPnlHour());
		}
		return pnlPartyData;
	}
	private JPanel getPnlName() {
		if (pnlName == null) {
			pnlName = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlName.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnlName.add(getLblName());
			pnlName.add(getTxtName());
		}
		return pnlName;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("name");
			lblName.setLabelFor(getTxtName());
			lblName.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return lblName;
	}
	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setColumns(15);
		}
		return txtName;
	}
	private JPanel getPnlSurname() {
		if (pnlSurname == null) {
			pnlSurname = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlSurname.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnlSurname.add(getLblSurname());
			pnlSurname.add(getTxtSurname());
		}
		return pnlSurname;
	}
	private JLabel getLblSurname() {
		if (lblSurname == null) {
			lblSurname = new JLabel("surname");
			lblSurname.setLabelFor(getTxtSurname());
			lblSurname.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return lblSurname;
	}
	private JTextField getTxtSurname() {
		if (txtSurname == null) {
			txtSurname = new JTextField();
			txtSurname.setColumns(15);
		}
		return txtSurname;
	}
	private JPanel getPnlNif() {
		if (pnlNif == null) {
			pnlNif = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlNif.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnlNif.add(getLblNif());
			pnlNif.add(getTxtNif());
		}
		return pnlNif;
	}
	private JLabel getLblNif() {
		if (lblNif == null) {
			lblNif = new JLabel("nif");
			lblNif.setLabelFor(getTxtNif());
			lblNif.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return lblNif;
	}
	private JTextField getTxtNif() {
		if (txtNif == null) {
			txtNif = new JTextField();
			txtNif.setColumns(15);
		}
		return txtNif;
	}
	private JPanel getPnlPhoneNumber() {
		if (pnlPhoneNumber == null) {
			pnlPhoneNumber = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlPhoneNumber.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnlPhoneNumber.add(getLblPhone());
			pnlPhoneNumber.add(getTxtPhone());
		}
		return pnlPhoneNumber;
	}
	private JLabel getLblPhone() {
		if (lblPhone == null) {
			lblPhone = new JLabel("phone");
			lblPhone.setLabelFor(getTxtPhone());
			lblPhone.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return lblPhone;
	}
	private JTextField getTxtPhone() {
		if (txtPhone == null) {
			txtPhone = new JTextField();
			txtPhone.setColumns(15);
		}
		return txtPhone;
	}
	private JPanel getPnlDate() {
		if (pnlDate == null) {
			pnlDate = new JPanel();
			pnlDate.add(getLblDate());
			pnlDate.add(getDateChooser());
		}
		return pnlDate;
	}
	private JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel("date");
			lblDate.setLabelFor(getDateChooser());
			lblDate.setFont(new Font("Dialog", Font.BOLD, 13));
			lblDate.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblDate;
	}
	private MyDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new MyDateChooser();
		}
		return dateChooser;
	}
	private JLabel getLblTime() {
		if (lblTime == null) {
			lblTime = new JLabel("hour");
			lblTime.setLabelFor(getCbHour());
			lblTime.setFont(new Font("Dialog", Font.BOLD, 13));
			lblTime.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return lblTime;
	}
	private JPanel getPnlHour() {
		if (pnlHour == null) {
			pnlHour = new JPanel();
			pnlHour.add(getLblTime());
			pnlHour.add(getCbHour());
			pnlHour.add(getLblHourSep());
			pnlHour.add(getCbMinutes());
		}
		return pnlHour;
	}
	private JComboBox<String> getCbHour() {
		if (cbHour == null) {
			cbHour = new JComboBox<String>(ComboBoxMaker.getHourModel());
		}
		return cbHour;
	}
	private JLabel getLblHourSep() {
		if (lblHourSep == null) {
			lblHourSep = new JLabel(":");
		}
		return lblHourSep;
	}
	private JComboBox<String> getCbMinutes() {
		if (cbMinutes == null) {
			cbMinutes = new JComboBox<String>(ComboBoxMaker.getMinutesModel());
		}
		return cbMinutes;
	}
	private JPanel getPnlObservations() {
		if (pnlObservations == null) {
			pnlObservations = new JPanel();
			pnlObservations.setLayout(new BoxLayout(pnlObservations, BoxLayout.Y_AXIS));
			pnlObservations.add(getLblObservations());
			pnlObservations.add(getSPnObservations());
		}
		return pnlObservations;
	}
	private JLabel getLblObservations() {
		if (lblObservations == null) {
			lblObservations = new JLabel("observations");
			lblObservations.setHorizontalAlignment(SwingConstants.CENTER);
			lblObservations.setLabelFor(getTxtAObservations());
			lblObservations.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return lblObservations;
	}
	private JScrollPane getSPnObservations() {
		if (sPnObservations == null) {
			sPnObservations = new JScrollPane();
			sPnObservations.setViewportView(getTxtAObservations());
		}
		return sPnObservations;
	}
	private JTextArea getTxtAObservations() {
		if (txtAObservations == null) {
			txtAObservations = new JTextArea();
			txtAObservations.setMaximumSize(new Dimension(2147483647, 300));
			txtAObservations.setLineWrap(true);
			txtAObservations.setWrapStyleWord(true);
		}
		return txtAObservations;
	}
	
	// Panel de confirmación

	private JLabel getLblBill() {
		if (lblBill == null) {
			lblBill = new JLabel("bill");
			lblBill.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblBill.setHorizontalAlignment(SwingConstants.CENTER);
			lblBill.setFont(new Font("Dialog", Font.BOLD, 24));
		}
		return lblBill;
	}
	private JScrollPane getSPnBill() {
		if (sPnBill == null) {
			sPnBill = new JScrollPane();
			sPnBill.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			sPnBill.setViewportView(getTxtABill());
		}
		return sPnBill;
	}
	private JTextArea getTxtABill() {
		if (txtABill == null) {
			txtABill = new JTextArea();
			txtABill.setFocusable(false);
			txtABill.setWrapStyleWord(true);
			txtABill.setLineWrap(true);
			txtABill.setEditable(false);
		}
		return txtABill;
	}
	private JLabel getLblContactPhone() {
		if (lblContactPhone == null) {
			lblContactPhone = new JLabel("Contact Phone");
		}
		return lblContactPhone;
	}
	private JPanel getPnlContactPhone() {
		if (pnlContactPhone == null) {
			pnlContactPhone = new JPanel();
			pnlContactPhone.add(getLblContactPhone());
			pnlContactPhone.add(getTxtContactPhone());
		}
		return pnlContactPhone;
	}
	private JTextField getTxtContactPhone() {
		if (txtContactPhone == null) {
			txtContactPhone = new JTextField();
			txtContactPhone.setFocusable(false);
			txtContactPhone.setEditable(false);
			txtContactPhone.setColumns(10);
		}
		return txtContactPhone;
	}
	
// Clases gestoras de eventos
	
	/**
	 * Abre la ventana de registro al usar el botón Registrarse
	 */
	class TriggerSignUpWindow implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			SignUpDialog signUp = new SignUpDialog(manager);
			signUp.setLocationRelativeTo(null);
			signUp.setVisible(true);	 
			signUp.requestFocus();
		}
	}
	
	/**
	 * Abre la ventana de la cesta al emplear el botón Cesta
	 */
	class TriggerCart implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			CartDialog cart = new CartDialog(manager);
			cart.setLocationRelativeTo(null);
			cart.setVisible(true);	 
			cart.requestFocus();
		}
	}
	
	/**
	 * Cambia a la siguiente pestaña o confirma el proceso al usar el botón OkNext
	 */
	class ContinueTrigger implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentTab != CONFIRMATION)
				checkAdvance();
			else
				confirm();
		}
	}
	
	/**
	 * Cambia a la pestaña anterior al usar el botón Next
	 */
	class TriggerPreviousPage implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			goToPreviousTab();
		}
	}
	
	/**
	 * Comprueba y realiza el log-in en la aplicación al usar el botón Iniciar sesión
	 */
	class LogInChecker implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			checkLogin();
		}
	}
	
	/**
	 * Inicia sesión como invitado al usar el botón Entrar como Invitado
	 */
	class GuestLogger implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			manager.logInAsGuest();
			goToNextTab();
		}
	}
	
	/**
	 * Devuelve a la ventana de inicio de sesión al usar el botón de Log-in
	 */
	class LogInAsker implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			goToLogInTab();
		}
	}
	
	/**
	 * Cierra la sesión al usar el botón de Log-out
	 */
	class LogOutAsker implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			logOut();
		}
	}
	
	/**
	 * Relocaliza el programa cuando este recupera el foco
	 */
	class LocationUpdater extends WindowAdapter {
		@Override
		public void windowGainedFocus(WindowEvent e) {
			locate();
		}
	}
	
	class ClosingListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) { 
			close();
		}		
	}
	
	/**
	 * Redimensiona la imagen de la selección cuando detecta un cambio en el tamaño
	 */
	class ImageResizer extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent e) {
			adaptImage(); 
		}
	}
	
	/**
	 * Recarga el precio de la seslcción cuando la ventana recupera el foco
	 */
	class PriceUpdater extends WindowAdapter {
		@Override
		public void windowGainedFocus(WindowEvent e) {
			reloadPrice();
		}
	}
	
	/**
	 * Actualiza los precios al detectar cambio en el spinner de invitados
	 */
	class SpinnerHandler implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent arg0) {
			reloadPrice();			
		}
	}
	
	/**
	 * Gestiona las comboBoxes mostrando el item seleccionado
	 */
	class ComboBoxHandler implements ActionListener {
		@SuppressWarnings("rawtypes")
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedItem = (Item) ((JComboBox)e.getSource()).getSelectedItem();
			reloadSelection();
		}
	}
	
	/**
	 * Gestiona los botones de añadir al carro
	 */
	class BtnAddHandler implements ActionListener {
		// Variables
		private JComboBox<Item> cb;
		private JSpinner sp;
		private Item item;
		private int quantity;
		// Constructor de items IGP
		public BtnAddHandler(JComboBox<Item> cb) {
			this.cb = cb;		
		}
		// Constructor de items IUP
		public BtnAddHandler(JComboBox<Item> cb, JSpinner sp) {
			this.cb = cb;
			this.sp = sp;
		}
		// Añade el item al carro
		@Override
		public void actionPerformed(ActionEvent e) {
			item = (Item) cb.getSelectedItem();
			quantity = getQty();
			wrongAddition = manager.addItemToCart(item, quantity);
			if (wrongAddition < 0)
				manageWrongAddition(item);
			reloadPrice();
		}
		// Obtiene la cantidad del spinner relacionado
		private int getQty() {
			if (sp != null)
				return (int) sp.getValue();
			return 1;					// Si no tiene Spinner relacionado, se añadirá una unidad
		}
	}
	
	/**
	 * Acción del botón cancelar, ciera en la primera pestaña y cancela el pedido en las demás
	 */
	public void cancelAction() {
		if (currentTab == 0)
			close();
		else {
			if (JOptionPane.showConfirmDialog(this, cancelDialog) == JOptionPane.YES_OPTION)
				reset();
		}
	}
	
// Métodos de navegación
	
	/**
	 * Comprueba si puede continuar, envía un mensaje en caso contrario
	 */
	private void checkAdvance() {
		if (currentTab == DATA_INPUT)
			if (checkDataFields())
				goToNextTab();
			else
				JOptionPane.showMessageDialog(this,dataEmptyFieldsWarning);
		if (currentTab == SELECTION)
			if (manager.isValidCart())
				goToNextTab();
			else {
				cbLocale.requestFocus();
				JOptionPane.showMessageDialog(this,invalidCartWarning);	}
	}
	
	/**
	 * Desactiva las pestañas no activas
	 * @param destiny	Pestaña destino
	 */
	private void enableTab(int destiny) {
		int count = tabbedPane.getTabCount();
		for (int i=0; i<count; i++)
			tabbedPane.setEnabledAt(i,false);
		tabbedPane.setEnabledAt(destiny,true);
	}
	
	/**
	 * Cambia la flecha a la pestaña activa
	 * @param destiny	Pestaña activa
	 */
	private void moveArrow(int destiny) {
		int count = tabbedPane.getTabCount();
		for (int i=0; i<count; i++)
			tabbedPane.setIconAt(i,null);
		tabbedPane.setIconAt(destiny,new ImageIcon(Main.class.getResource(IMG_ICON_MICRO)));
	}
	
	/**
	 * Ejecuta el método que dirige a la siguiente pestaña
	 */
	private void goToNextTab() {
		switch (currentTab) {
			case LOGIN:			goToSelectionTab();	
								break;
			case SELECTION: 	goToDataInputTab();
								break;
			case DATA_INPUT:	goToConfirmationTab();
		}
	}
	
	/**
	 * Ejecuta el método que dirige a la anterior pestaña
	 */
	private void goToPreviousTab() {
		switch (currentTab) {
			case DATA_INPUT:	goToSelectionTab();
								break;
			case CONFIRMATION:	goToDataInputTab();
		}
	}
	
	/*
	 * Sitúa el programa en la pestaña de Log-In
	 */
	private void goToLogInTab() {
		currentTab = LOGIN;										// Cambia la pestaña
		manager.setCurrentTab(currentTab);
		enableTab(currentTab);
		tabbedPane.setSelectedIndex(currentTab);
		moveArrow(currentTab);
		locate();					// Relocaliza
		imgLogInOut.setVisible(false);							// Oculta los botones no necesarios
		lblLoggedAs.setVisible(false);
		btnBack.setEnabled(false);
		btnOkNext.setEnabled(false);		
		getRootPane().setDefaultButton(btnLogIn);				// Cambia el botón por defecto
		txtUsername.grabFocus();								// Sitúa el foco 
	}
	
	/**
	 * Sitúa el programa en la pestaña de Selection
	 */
	private void goToSelectionTab() {
		currentTab = SELECTION;									// Cambia la pestaña
		manager.setCurrentTab(currentTab);
		enableTab(currentTab);
		tabbedPane.setSelectedIndex(currentTab);
		moveArrow(currentTab);
		locate();												// Relocaliza
		saveDataInput();										// Guarda los datos de dataInput
		imgLogInOut.setVisible(true);							// Oculta/muestra los botones pertinentes
		lblLoggedAs.setVisible(true);
		btnBack.setEnabled(false);
		btnOkNext.setEnabled(true);	
		txtADescription.setText("");							// Resetea los elementos necesarios
		adaptImage();
		reloadPrice();
		getRootPane().setDefaultButton(btnOkNext);				// Cambia el botón por defecto
		spAttendants.grabFocus();								// Sitúa el foco 
	}
	
	/**
	 * Sitúa el programa en la pestaña de DataIput
	 */
	private void goToDataInputTab() {
		visitedDataInput = true;
		currentTab = DATA_INPUT;								// Cambia la pestaña
		manager.setCurrentTab(currentTab);
		enableTab(currentTab);
		tabbedPane.setSelectedIndex(currentTab);
		moveArrow(currentTab);
		locate();												// Relocaliza
		imgLogInOut.setVisible(true);							// Muestra/oculta lo pertinente
		lblLoggedAs.setVisible(true);
		btnBack.setEnabled(true);
		btnOkNext.setEnabled(true);	
		loadDataInput();										// Resetea los elementos necesarios
		getRootPane().setDefaultButton(btnOkNext);				// Cambia el botón por defecto
		txtName.grabFocus();									// Sitúa el foco en el campo de nombre de usuario
	}
	
	/**
	 * Sitúa el programa en la pestaña de Confirmation
	 */
	private void goToConfirmationTab() {
		currentTab = CONFIRMATION;								// Cambia la pestaña
		manager.setCurrentTab(currentTab);
		enableTab(currentTab);
		tabbedPane.setSelectedIndex(currentTab);
		moveArrow(currentTab);
		locate();												// Relocaliza
		imgLogInOut.setVisible(true);							// Muestra/oculta lo pertinente
		lblLoggedAs.setVisible(true);
		btnBack.setEnabled(true);
		btnOkNext.setEnabled(true);
		saveDataInput();
		txtABill.setText(manager.getBill());					// Rellena los campos
		txtContactPhone.setText(manager.getContactPhone());
		getRootPane().setDefaultButton(btnOkNext);				// Cambia el botón por defecto
		btnOkNext.grabFocus();
		txtABill.setCaretPosition(0);
	}
	
// Métodos lógicos

	/**
	 * Realiza el proceso de finalización
	 */
	private void confirm() {
		int bill = JOptionPane.showConfirmDialog(this, confirmDialog);
		if (bill == JOptionPane.YES_OPTION)
			bill = saveFile();
		if (bill != JOptionPane.CANCEL_OPTION)
			reset();
	}
	
	/**
	 * Proporciona un fileChooser para elegir donde guardar el archivo y lo guarda
	 * @return	0 si ha elegido donde guardarlo, 2 si ha cancelado (esto cancela el reseteo de la finalización del pedido y el reseteo)
	 */
	private int saveFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(new File(manager.getOutputPath()));
		int confirmation = chooser.showSaveDialog(null);
		if (confirmation == JFileChooser.APPROVE_OPTION)
			manager.printBill(chooser.getSelectedFile()+".txt");
		if (confirmation == JFileChooser.CANCEL_OPTION)
			return JOptionPane.CANCEL_OPTION;
		return JOptionPane.YES_OPTION;
	}
	
	/**
	 * Cierra la aplicación
	 */
	private void close() {
		if (JOptionPane.showConfirmDialog(this, closeWarning) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	/**
	 * Resetea la aplicación
	 */
	private void reset() {
		visitedDataInput = false;
		dateChooser.setDate(null);
		manager.reset();
		logOut();
		selectedItem = null;
		goToLogInTab();
	}
	
	/**
	 * Comprueba que el inicio de sesión sea correcto
	 */
	private void checkLogin() {
		if (checkUsername()) 
			if (checkPassword())
				logIn();	
			else
				wrongPassword();
		else
			wrongUsername();
	}
	
	/**
	 * Comprueba si el usuario introducido existe
	 * @return	true si existe, false en caso contrario
	 */
	private boolean checkUsername() {
		return manager.existUsername(txtUsername.getText());
	}
	
	/**
	 * Comprueba si usuario y contraseña introducida coinciden
	 * @return	true si coinciden, false en caso contrario
	 */
	private boolean checkPassword() {
		return manager.passwordMatching(txtUsername.getText(),String.valueOf(pFPass.getPassword()));
	}
	
	/**
	 * Avisa al usuario que el nombre de usuario es inválido y lo redirije a su edición
	 */
	private void wrongUsername() {
		txtUsername.grabFocus();
		JOptionPane.showMessageDialog(this, wrongUsernameWarning);
	}
	
	/**
	 * Avisa al usuario que la contraseña no es correcta y lo redirije a su edición
	 */
	private void wrongPassword() {
		pFPass.selectAll();
		JOptionPane.showMessageDialog(this, wrongPasswordWarning);
	}
	
	/**
	 * Comprueba que todos los campos obligatorios de Data Input han sido cubiertos
	 */
	private boolean checkDataFields() {
		if (txtName.getText().length() == 0) {
			txtName.requestFocus();
			return false;	}
		if (txtSurname.getText().length() == 0) {
			txtSurname.requestFocus();
			return false;	}
		if (txtNif.getText().length() == 0) {
			txtNif.requestFocus();
			return false;	}
		if (dateChooser.getDate() == null) {
			dateChooser.requestFocusInWindow();
			return false;	}
		return true;
			
	}
	
	/**
	 * Loguea al usuario en la aplicación
	 */
	private void logIn() {
		manager.logIn(txtUsername.getText());
		changeLogInOutButton();
		goToNextTab();
	}
	
	/**
	 * Desloquea al usuario de la aplicación
	 */
	private void logOut() {
		manager.logOut();
		changeLogInOutButton();
		locate();
	}
	
	/**
	 * Devuelve el estado de logIn el usuario
	 * @return	true si está logeado, false si está como invitado
	 */
	private boolean isLogged() {
		return manager.isLogged();
	}
	
	/**
	 * Devuelve la ruta de la imagen del item seleccionado actual
	 * @return	ruta de la imagen
	 */
	private String getPath() {
		if (selectedItem != null)
			return selectedItem.getPath();
		return IMG_EMPTY;
	}
	
	/**
	 * Envía un mensaje de error según el problema producido al añadir el item
	 */
	private void manageWrongAddition(Item item) {
		if (wrongAddition == -1)
			JOptionPane.showMessageDialog(this, alreadyAddedWarning);
		int option = -1;
		if (wrongAddition == -2)
			option = JOptionPane.showConfirmDialog(this, replaceDialog);
		if (option == JOptionPane.YES_OPTION)
			manager.replace(item);		
	}
	
	/**
	 * Actualiza el número de invitados a la fiesta
	 */
	private void updateAttendants() {
		manager.setAttendants((int) spAttendants.getValue());
	}
	
	/**
	 * Recarga el precio del pedido
	 */
	private void reloadPrice() {
		updateAttendants();
		double price = manager.getPrice();
		txtPrice.setText(Formatter.formatCurrency(price));
	}
	
	/**
	 * Guarda los datos almacenados en dataInput
	 */
	public void saveDataInput() {	
		String[] dataInput = new String[10];
		if (visitedDataInput) {
			dataInput[0] = txtName.getText();
			dataInput[1] = txtSurname.getText();
			dataInput[2] = txtNif.getText();
			dataInput[3] = txtPhone.getText();
			dataInput[4] = (String) cbHour.getSelectedItem();
			dataInput[5] = (String) cbMinutes.getSelectedItem();
			dataInput[6] = txtAObservations.getText();
			if (dateChooser.getDate() != null)
				dataInput[7] = Formatter.formatDate(dateChooser.getDate());
			manager.saveDataInput(dataInput);	}
	}
	
	/**
	 * Recupera los datos almacenados en dataInput
	 */
	public void loadDataInput() {
		String[] dataInput = manager.getDataInput();
		txtName.setText(dataInput[0]);
		txtSurname.setText(dataInput[1]);
		txtNif.setText(dataInput[2]);
		txtPhone.setText(dataInput[3]);
		cbHour.setSelectedItem(dataInput[4]);
		cbMinutes.setSelectedItem(dataInput[5]);
		txtAObservations.setText(dataInput[6]);
	}
	
// Métodos visuales
	
	/**
	 * Cambia el icono del botón de LogInOut
	 */
	private void changeLogInOutButton() {
		if (isLogged()) {
			imgLogInOut.setIcon(new ImageIcon(Main.class.getResource(IMG_LOGOUT_IN)));
			imgLogInOut.setRolloverIcon(new ImageIcon(Main.class.getResource(IMG_LOGOUT)));
			imgLogInOut.removeFocusListener(ibcLogIn);
			imgLogInOut.addFocusListener(ibcLogOut);	
			imgLogInOut.removeActionListener(lia);
			imgLogInOut.addActionListener(loa);	}
		else {
			imgLogInOut.setIcon(new ImageIcon(Main.class.getResource(IMG_LOGIN_IN)));
			imgLogInOut.setRolloverIcon(new ImageIcon(Main.class.getResource(IMG_LOGIN)));
			imgLogInOut.removeFocusListener(ibcLogOut);
			imgLogInOut.addFocusListener(ibcLogIn);	
			imgLogInOut.removeActionListener(loa);
			imgLogInOut.addActionListener(lia);	}
	}
	
	/**
	 * Modifica el texto de la label LoggedAs
	 * @param string
	 */
	private void setLblLoggedAsText(String string) {
		if (isLogged())
			lblLoggedAs.setText("<html><div align=center width:'150px'>" + string + manager.getUser().getUsername() + "</div></html>");
		else
			lblLoggedAs.setText("<html><div align=center width:'150px'>" + string + guest + "</div></html>");
	}
	
	/**
	 * Redimensiona la imagen
	 * @param imgSelection	imagen a redimensionar
	 */
	private void adaptImage() {
		Image imgOriginal = new ImageIcon(getClass().getResource(getPath())).getImage();
		Image imgScaled = imgOriginal.getScaledInstance((int)(imgSelection.getWidth()),(int)(imgSelection.getHeight()), Image.SCALE_FAST);
		imgSelection.setIcon(new ImageIcon(imgScaled));
	}
	
	/**
	 * Modifica los elementos necesarios para mostrar el seleccionado
	 * @param newSelectedItem nuevo item seleccionado
	 */
	private void reloadSelection() {
		adaptImage();
		txtADescription.setText(selectedItem.getDescription());
	}
	
// Localización

	@Override
	public void locate() {
		// Localización
		Locale location = CurrentLocation.getLocale();
		bundle = ResourceBundle.getBundle("rcs/Main",location);	
		locateGeneral(bundle);											// Localiza los elementos comunes a todas las pestañas
		locateDialogs(bundle);
		switch (currentTab) {											// Localiza la pestaña actual
			case LOGIN: 		locateLogIn(bundle);
								break;
			case SELECTION: 	locateSelection(bundle);
								break;
			case DATA_INPUT:	locateDataInput(bundle);
								break;
			case CONFIRMATION:	locateConfirmationInput(bundle);
		};
	}
	
	/**
	 * Localiza los elementos comunes a todas las pestañas
	 * @param bundle	Bundle con los elementos de localización
	 */
	private void locateGeneral(ResourceBundle bundle) {
		// Sustitución de textos
		guest = bundle.getString("guest");
		setLblLoggedAsText(bundle.getString("lblLoggedAs"));
		tabbedPane.setTitleAt(0, bundle.getString("tabLogIn"));
		tabbedPane.setTitleAt(1, bundle.getString("tabSelection"));
		tabbedPane.setTitleAt(2, bundle.getString("tabDataInput"));
		tabbedPane.setTitleAt(3, bundle.getString("tabConfirmation"));
		btnBack.setText(bundle.getString("btnBack"));
		btnOkNext.setText(bundle.getString("btnOkNext"));
		btnCancel.setText(bundle.getString("btnCancel"));
		// Sustitución de tooltip
		imgCart.setToolTipText(bundle.getString("toolImgCart"));
		imgHelp.setToolTipText(bundle.getString("toolImgHelp"));
		setLogButtonTooltipText(bundle);
		imgEs.setToolTipText(bundle.getString("toolSpain"));
		imgGl.setToolTipText(bundle.getString("toolGalicia"));
		imgEn.setToolTipText(bundle.getString("toolUK"));
	}

	/**
	 * Localiza los mensajes de sistema
	 */
	private void locateDialogs(ResourceBundle bundle) {
		cancelDialog = bundle.getString("cancelDialog");
		closeWarning = bundle.getString("closeWarning");
		wrongUsernameWarning = bundle.getString("wrongUsernameWarning");
		wrongPasswordWarning = bundle.getString("wrongPasswordWarning");
		alreadyAddedWarning = bundle.getString("alreadyAddedWarning");
		invalidCartWarning = bundle.getString("invalidCartWarning");
		dataEmptyFieldsWarning = bundle.getString("dataEmptyFieldsWarning");
		replaceDialog = bundle.getString("replaceDialog");
		confirmDialog = bundle.getString("confirmDialog");
		guest = bundle.getString("guest");
	}
	
	/**
	 * Localiza la pestaña de LogIn
	 * @param bundle	Bundle con los elementos de localización
	 */
	private void locateLogIn(ResourceBundle bundle) {
		// Sustitución de textos
		setTitle(bundle.getString("titleLogIn"));
		lblSignUp.setText(bundle.getString("lblSignUp"));	
		lblSignUpIntro.setText(bundle.getString("lblSignUpIntro"));	
		btnSignUp.setText(bundle.getString("btnSignUp"));				
		lblLogIn.setText(bundle.getString("lblLogIn"));				
		lblUsername.setText(bundle.getString("lblUsername"));				
		lblPassword.setText(bundle.getString("lblPass"));
		btnLogIn.setText(bundle.getString("btnLogIn"));				
		lblGuest.setText(bundle.getString("lblGuest"));				
		btnGuest.setText(bundle.getString("btnGuest"));		
		btnCancel.setText(bundle.getString("btnCancelLogIn"));
		// Sustitución de mnemónicos de textos
		btnSignUp.setMnemonic(bundle.getString("mneSignUp").charAt(0));
		btnLogIn.setMnemonic(bundle.getString("mneLogIn").charAt(0));
		btnGuest.setMnemonic(bundle.getString("mneGuest").charAt(0));
		// Sustitución de tooltips de botones
		btnSignUp.setToolTipText(bundle.getString("toolSignUp"));
		btnLogIn.setToolTipText(bundle.getString("toolLogIn"));
		btnGuest.setToolTipText(bundle.getString("toolGuest"));
		// Sustitución de banderas
		imgEs.setIcon(new ImageIcon(Main.class.getResource(bundle.getString("spFlag"))));
		imgGl.setIcon(new ImageIcon(Main.class.getResource(bundle.getString("glFlag"))));
		imgEn.setIcon(new ImageIcon(Main.class.getResource(bundle.getString("ukFlag"))));
	}
	
	/**
	 * Localiza la pestaña Selection
	 * @param bundle	Bundle con los elementos de localización
	 */
	private void locateSelection(ResourceBundle bundle) {
		// Sustitución de textos
		setTitle(bundle.getString("titleSelection"));
		lblAttendants.setText(bundle.getString("lblAttendants"));
		lblLocale.setText(bundle.getString("lblLocale"));
		lblDecoration.setText(bundle.getString("lblDecoration"));
		lblDrinks.setText(bundle.getString("lblDrinks"));
		lblFood.setText(bundle.getString("lblFood"));
		lblOthers.setText(bundle.getString("lblOthers"));
		lblPrice.setText(bundle.getString("lblPrice"));
		// Sustitución de mnemómicos
		lblAttendants.setDisplayedMnemonic(bundle.getString("mneAttendants").charAt(0));
		lblLocale.setDisplayedMnemonic(bundle.getString("mneLocale").charAt(0));
		lblDecoration.setDisplayedMnemonic(bundle.getString("mneDecoration").charAt(0));
		lblDrinks.setDisplayedMnemonic(bundle.getString("mneDrinks").charAt(0));
		lblFood.setDisplayedMnemonic(bundle.getString("mneFood").charAt(0));
		lblOthers.setDisplayedMnemonic(bundle.getString("mneOthers").charAt(0));
		// Sustitución de tooltips
		btnLocale.setToolTipText(bundle.getString("btnAdd"));
		btnDecoration.setToolTipText(bundle.getString("btnAdd"));
		btnDrinks.setToolTipText(bundle.getString("btnAdd"));
		btnFood.setToolTipText(bundle.getString("btnAdd"));
		btnOthers.setToolTipText(bundle.getString("btnAdd"));
	}
	
	/**
	 * Localiza la pestaña DataInput
	 * @param bundle	Bundle con los elementos de localización
	 */
	private void locateDataInput(ResourceBundle bundle) {
		// Sustitución de textos
		setTitle(bundle.getString("titleDataInput"));
		pnlUserData.setBorder(new TitledBorder(new LineBorder(new Color(100, 149, 237), 1, true), bundle.getString("pnlUserData"), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lblName.setText(bundle.getString("name"));
		lblSurname.setText(bundle.getString("surname"));
		lblNif.setText(bundle.getString("nif"));
		lblPhone.setText(bundle.getString("phone"));
		pnlPartyData.setBorder(new TitledBorder(new LineBorder(new Color(100, 149, 237), 1, true), bundle.getString("pnlPartyData"), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lblDate.setText(bundle.getString("date"));
		lblTime.setText(bundle.getString("time"));
		lblObservations.setText(bundle.getString("observations"));
		// Sustitución de mnemónicos
		lblName.setDisplayedMnemonic(bundle.getString("mneName").charAt(0));
		lblSurname.setDisplayedMnemonic(bundle.getString("mneSurname").charAt(0));
		lblNif.setDisplayedMnemonic(bundle.getString("mneNif").charAt(0));
		lblPhone.setDisplayedMnemonic(bundle.getString("mnePhone").charAt(0));
		lblDate.setDisplayedMnemonic(bundle.getString("mneDate").charAt(0));
		lblTime.setDisplayedMnemonic(bundle.getString("mneTime").charAt(0));
		lblObservations.setDisplayedMnemonic(bundle.getString("mneObservations").charAt(0));
		// Sustitución de tooltips
		dateChooser.setToolTipText(bundle.getString("toolDate"));
		// Sustitución de la localización del dateChooser
		dateChooser.setLocale(CurrentLocation.getLocale());
	}
	
	/**
	 * Localiza la pestaña Confirmation
	 * @param bundle	Bundle con los elementos de localización
	 */
	private void locateConfirmationInput(ResourceBundle bundle) {
		setTitle(bundle.getString("titleConfirmation"));
		lblBill.setText(bundle.getString("lblBill"));
		lblContactPhone.setText(bundle.getString("lblContactPhone"));
		btnOkNext.setText(bundle.getString("btnConfirm"));
	}
	
	/**
	 * Modifica el tooltipText del botón de LogInOut
	 * @param bundle	Bundle con los elemento de localización
	 */
	private void setLogButtonTooltipText(ResourceBundle bundle) {
		if (isLogged())
			imgLogInOut.setToolTipText(bundle.getString("toolImgLogIn"));
		else
			imgLogInOut.setToolTipText(bundle.getString("toolImgLogOut"));
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
		hb.enableHelpKey(getRootPane(),"introduccion", hs);
		hb.enableHelpOnButton(imgHelp, "introduccion", hs);
		hb.enableHelp(btnSignUp, "registro", hs);
		hb.enableHelp(btnLogIn, "login", hs);
		hb.enableHelp(btnGuest, "login", hs);
		hb.enableHelp(spAttendants, "seleccion", hs);
		hb.enableHelp(spDrinks, "seleccion", hs);
		hb.enableHelp(spFood, "seleccion", hs);
		hb.enableHelp(cbDecoration, "seleccion", hs);
		hb.enableHelp(cbDrinks, "seleccion", hs);
		hb.enableHelp(cbFood, "seleccion", hs);
		hb.enableHelp(cbLocale, "seleccion", hs);
		hb.enableHelp(cbOthers, "seleccion", hs);
		hb.enableHelp(btnDecoration, "seleccion", hs);
		hb.enableHelp(btnDrinks, "seleccion", hs);
		hb.enableHelp(btnFood, "seleccion", hs);
		hb.enableHelp(btnLocale, "seleccion", hs);
		hb.enableHelp(btnOthers, "seleccion", hs);
		hb.enableHelp(txtName, "datos", hs);
		hb.enableHelp(txtSurname, "datos", hs);
		hb.enableHelp(txtNif, "datos", hs);
		hb.enableHelp(txtPhone, "datos", hs);
		hb.enableHelp(dateChooser, "datos", hs);
		hb.enableHelp(cbHour, "datos", hs);
		hb.enableHelp(cbMinutes, "datos", hs);
		hb.enableHelp(txtAObservations, "datos", hs);
		hb.enableHelp(imgCart, "cesta", hs);
		hb.enableHelp(imgEs, "introduccion", hs);
		hb.enableHelp(imgGl, "introduccion", hs);
		hb.enableHelp(imgEn, "introduccion", hs);
	}
	
}