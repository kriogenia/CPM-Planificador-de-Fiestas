package gui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;

import common.User;
import eventHandlers.LocationChanger;
import interfaces.Localizable;
import logic.CurrentLocation;
import logic.Manager;

import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import org.eclipse.wb.swing.FocusTraversalOnArray;

/**
 * Ventana que gestiona el proceso de registro
 * @author Ricardo Soto Estévez, uo265710
 */
public class SignUpDialog extends JDialog implements Localizable {
	
	// Serial
	private static final long serialVersionUID = -4951091973620823900L;
	
	// Elementos lógicos
	Manager manager;
	User user;
	ResourceBundle bundle;
	
	// Mensajes de sistema
	private String sucessfulRegistration = "Te has registrado correctamente, ya puedes iniciar sesión.";
	private String fillEverythingWarning = "Registro inválido\nRellene todos los campos obligatorios";
	private String invalidUsernameWarning = "Registro inválido\nEse nombre de usuario no está disponible, elija otro";
	private String invalidPasswordWarning = "Registro inválido\nLas contraseñas no coinciden";
	private String invalidPhoneWarning = "Registro inválido\nEl número de teléfono solo puede contener dígitos";
	
	// Elementos visuales
	private JPanel pnlIntro;							// Introducción
		private JLabel lblIntro;
		private JLabel lblAsteriskWarning;
	private JScrollPane scrollPane;
	private JPanel pnlForm;								// Formulario
	private JPanel pnlUsername;
		private JLabel lblUsername;
		private JTextField txtUsername;
		private JLabel lblInvalidUsername;
	private JPanel pnlPass;
		private JLabel lblPass;
		private JPasswordField pFPass;
	private JPanel pnlRePass;
		private JLabel lblRePass;
		private JPasswordField pFRePass;
		private JLabel lblNoMatchingPass;
	private JPanel pnlPersonalData;
	private JPanel pnlName;
		private JLabel lblName;
		private JTextField txtName;
	private JPanel pnlSurname;
		private JLabel lblSurname;
		private JTextField txtSurname;
	private JPanel pnlNIF;
		private JLabel lblNIF;
		private JTextField txtNIF;
	private JPanel pnlGender;
		private JLabel lblGender;
		private final ButtonGroup genderButtonGroup = new ButtonGroup();
		private JRadioButton rdbtnMale;
		private JRadioButton rdbtnFemale;
		private JRadioButton rdbtnOther;
	private JPanel pnlPhoneNumber;
		private JLabel lblPhoneNumber;
	private JTextField txtPhoneNumber;
	private JPanel pnlSouth;								// Panel inferior
	private JPanel pnlButtons;
		private JButton btnOk;
		private JButton btnCancel;
	private JPanel pnlFlags;
		private JButton btnEs;
		private JButton btnGl;
		private JButton btnEn;

//	// Pruebas
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SignUpWindow frame = new SignUpWindow(new Manager());
//					frame.setLocationRelativeTo(null);
//					frame.setVisible(true);	} 
//				catch (Exception e) {
//					System.out.print(e.getMessage()+"\n");
//					e.printStackTrace();	}
//			}
//		});
//	}
		
	/**
	 * Crea el diálogo de registro
	 */
	public SignUpDialog(Manager manager) {
		this.manager = manager;
		setMinimumSize(new Dimension(380, 220));
		setMaximumSize(new Dimension(450, 1000));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SignUpDialog.class.getResource(Main.ICON)));
		setTitle("titleRegister");
		setBounds(100, 100, 450, 420);
		getContentPane().add(getPnlIntro(), BorderLayout.NORTH);
		getContentPane().add(getPnlSouth(), BorderLayout.SOUTH);
		getContentPane().add(getScrollPane(), BorderLayout.CENTER);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getTxtUsername(), getPFpass(), getPfRePass(), getTxtName(), getTxtSurname(), getTxtNIF(), getRdbtnMale(), getRdbtnFemale(), getRdbtnOther(), getTxtPhoneNumber(), getBtnOk(), getBtnCancel(), getBtnEs(), getBtnGl(), getBtnEn()}));
		locate();
		this.addWindowFocusListener(new FocusManager());
		// Botones por defecto
		getRootPane().setDefaultButton(btnOk);
		getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "CANCEL");
		getRootPane().getActionMap().put("CANCEL", btnCancel.getAction());
		createHelp();
	}

// Métodos de los elementos visuales
	
	private JPanel getPnlIntro() {
		if (pnlIntro == null) {
			pnlIntro = new JPanel();
			pnlIntro.setBorder(new EmptyBorder(5,5,5,5));
			pnlIntro.setLayout(new BoxLayout(pnlIntro, BoxLayout.Y_AXIS));
			pnlIntro.add(getLblIntro());
			pnlIntro.add(getLblAsteriskWarning());
		}
		return pnlIntro;
	}
	private JLabel getLblIntro() {
		if (lblIntro == null) {
			lblIntro = new JLabel("lblIntro");
			lblIntro.setHorizontalAlignment(SwingConstants.LEFT);
			lblIntro.setFont(new Font("Dialog", Font.BOLD, 16));
		}
		return lblIntro;
	}
	private JLabel getLblAsteriskWarning() {
		if (lblAsteriskWarning == null) {
			lblAsteriskWarning = new JLabel("lblAsteriskWarning");
			lblAsteriskWarning.setHorizontalAlignment(SwingConstants.LEFT);
			lblAsteriskWarning.setFont(new Font("Dialog", Font.ITALIC, 10));
		}
		return lblAsteriskWarning;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setViewportView(getPnlForm());
		}
		return scrollPane;
	}
	private JPanel getPnlForm() {
		if (pnlForm == null) {
			pnlForm = new JPanel();
			pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));
			pnlForm.add(getPnlUsername());
			pnlForm.add(getPnlPass());
			pnlForm.add(getPnlRePass());
			pnlForm.add(getPnlPersonalData());
		}
		return pnlForm;
	}
	private JPanel getPnlUsername() {
		if (pnlUsername == null) {
			pnlUsername = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlUsername.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlUsername.add(getLblUsername());
			pnlUsername.add(getTxtUsername());
			pnlUsername.add(getLblInvalidUsername());
		}
		return pnlUsername;
	}
	private JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel("lblUsername");
			lblUsername.setLabelFor(getTxtUsername());
		}
		return lblUsername;
	}
	private JTextField getTxtUsername() {
		if (txtUsername == null) {
			txtUsername = new JTextField();
			txtUsername.setColumns(12);
			txtUsername.addFocusListener(new UserChecker());
		}
		return txtUsername;
	}
	private JLabel getLblInvalidUsername() {
		if (lblInvalidUsername == null) {
			lblInvalidUsername = new JLabel("lblInvalidUsername");
			lblInvalidUsername.setVisible(false);
			lblInvalidUsername.setFont(new Font("Tahoma", Font.ITALIC, 10));
		}
		return lblInvalidUsername;
	}
	private JPanel getPnlPass() {
		if (pnlPass == null) {
			pnlPass = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlPass.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlPass.add(getLblPass());
			pnlPass.add(getPFpass());
		}
		return pnlPass;
	}
	private JLabel getLblPass() {
		if (lblPass == null) {
			lblPass = new JLabel("lblPass");
			lblPass.setLabelFor(getPFpass());
		}
		return lblPass;
	}
	private JPasswordField getPFpass() {
		if (pFPass == null) {
			pFPass = new JPasswordField();
			pFPass.setColumns(12);
			pFPass.addFocusListener(new Selector());
		}
		return pFPass;
	}
	private JPanel getPnlRePass() {
		if (pnlRePass == null) {
			pnlRePass = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlRePass.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlRePass.add(getLblRePass());
			pnlRePass.add(getPfRePass());
			pnlRePass.add(getLblNoMatchingPass());
		}
		return pnlRePass;
	}
	private JLabel getLblRePass() {
		if (lblRePass == null) {
			lblRePass = new JLabel("lblRePass");
			lblRePass.setLabelFor(getPfRePass());
		}
		return lblRePass;
	}
	private JPasswordField getPfRePass() {
		if (pFRePass == null) {
			pFRePass = new JPasswordField();
			pFRePass.setColumns(12);
			pFRePass.addFocusListener(new Selector());
			pFRePass.addFocusListener(new PasswordChecker());
		}
		return pFRePass;
	}
	private JLabel getLblNoMatchingPass() {
		if (lblNoMatchingPass == null) {
			lblNoMatchingPass = new JLabel("lblNoMatchingPass");
			lblNoMatchingPass.setVisible(false);
			lblNoMatchingPass.setFont(new Font("Tahoma", Font.ITALIC, 10));
		}
		return lblNoMatchingPass;
	}
	private JPanel getPnlPersonalData() {
		if (pnlPersonalData == null) {
			pnlPersonalData = new JPanel();
			pnlPersonalData.setBorder(new TitledBorder(new LineBorder(new Color(100, 149, 237), 1, true), "pnlPersonalData", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlPersonalData.setLayout(new BoxLayout(pnlPersonalData, BoxLayout.Y_AXIS));
			pnlPersonalData.add(getPnlName());
			pnlPersonalData.add(getPnlSurname());
			pnlPersonalData.add(getPnlNIF());
			pnlPersonalData.add(getPnlGender());
			pnlPersonalData.add(getPnlPhoneNumber());
		}
		return pnlPersonalData;
	}
	private JPanel getPnlName() {
		if (pnlName == null) {
			pnlName = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlName.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlName.add(getLblName());
			pnlName.add(getTxtName());
		}
		return pnlName;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("lblName");
			lblName.setLabelFor(getTxtName());
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
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlSurname.add(getLblSurname());
			pnlSurname.add(getTxtSurname());
		}
		return pnlSurname;
	}
	private JLabel getLblSurname() {
		if (lblSurname == null) {
			lblSurname = new JLabel("lblSurname");
			lblSurname.setLabelFor(getTxtSurname());
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
	private JPanel getPnlNIF() {
		if (pnlNIF == null) {
			pnlNIF = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlNIF.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlNIF.add(getLblNIF());
			pnlNIF.add(getTxtNIF());
		}
		return pnlNIF;
	}
	private JLabel getLblNIF() {
		if (lblNIF == null) {
			lblNIF = new JLabel("lblNIF");
			lblNIF.setLabelFor(getTxtNIF());
		}
		return lblNIF;
	}
	private JTextField getTxtNIF() {
		if (txtNIF == null) {
			txtNIF = new JTextField();
			txtNIF.setColumns(10);
		}
		return txtNIF;
	}
	private JPanel getPnlGender() {
		if (pnlGender == null) {
			pnlGender = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlGender.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlGender.add(getLblGender());
			pnlGender.add(getRdbtnMale());
			pnlGender.add(getRdbtnFemale());
			pnlGender.add(getRdbtnOther());
		}
		return pnlGender;
	}private JLabel getLblGender() {
		if (lblGender == null) {
			lblGender = new JLabel("lblGender");
		}
		return lblGender;
	}
	private JPanel getPnlPhoneNumber() {
		if (pnlPhoneNumber == null) {
			pnlPhoneNumber = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlPhoneNumber.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlPhoneNumber.add(getLblPhoneNumber());
			pnlPhoneNumber.add(getTxtPhoneNumber());
		}
		return pnlPhoneNumber;
	}
	private JLabel getLblPhoneNumber() {
		if (lblPhoneNumber == null) {
			lblPhoneNumber = new JLabel("lblPhoneNumber");
			lblPhoneNumber.setLabelFor(getTxtPhoneNumber());
		}
		return lblPhoneNumber;
	}
	private JTextField getTxtPhoneNumber() {
		if (txtPhoneNumber == null) {
			txtPhoneNumber = new JTextField();
			txtPhoneNumber.setColumns(10);
		}
		return txtPhoneNumber;
	}
	private JRadioButton getRdbtnMale() {
		if (rdbtnMale == null) {
			rdbtnMale = new JRadioButton("rdbtnMAle");
			rdbtnMale.setSelected(true);
			rdbtnMale.setActionCommand("0");
			genderButtonGroup.add(rdbtnMale);
		}
		return rdbtnMale;
	}
	private JRadioButton getRdbtnFemale() {
		if (rdbtnFemale == null) {
			rdbtnFemale = new JRadioButton("rdbtnFemale");
			rdbtnFemale.setActionCommand("1");
			genderButtonGroup.add(rdbtnFemale);
		}
		return rdbtnFemale;
	}
	private JRadioButton getRdbtnOther() {
		if (rdbtnOther == null) {
			rdbtnOther = new JRadioButton("rdBtnOther");
			rdbtnOther.setActionCommand("2");
			genderButtonGroup.add(rdbtnOther);
		}
		return rdbtnOther;
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
			pnlButtons.add(getBtnOk());
			pnlButtons.add(getBtnCancel());
		}
		return pnlButtons;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("OK");
			btnOk.addActionListener(new OkChecker());
		}
		return btnOk;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setAction(new Disposer());
		}
		return btnCancel;
	}	
	private JPanel getPnlFlags() {
		if (pnlFlags == null) {
			pnlFlags = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlFlags.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlFlags.add(getBtnEs());
			pnlFlags.add(getBtnGl());
			pnlFlags.add(getBtnEn());
		}
		return pnlFlags;
	}
	private JButton getBtnEs() {
		if (btnEs == null) {
			btnEs = new JButton("ES");
			btnEs.setMnemonic('e');
			btnEs.setActionCommand("es");
			btnEs.addActionListener(new LocationChanger(this));
		}
		return btnEs;
	}
	private JButton getBtnGl() {
		if (btnGl == null) {
			btnGl = new JButton("GL");
			btnGl.setMnemonic('g');
			btnGl.setActionCommand("gl");
			btnGl.addActionListener(new LocationChanger(this));
		}
		return btnGl;
	}
	private JButton getBtnEn() {
		if (btnEn == null) {
			btnEn = new JButton("EN");
			btnEn.setMnemonic('n');
			btnEn.setActionCommand("en");
			btnEn.addActionListener(new LocationChanger(this));
		}
		return btnEn;
	}
	
// Métodos auxiliares
	
	/**
	 * @return	El componente que tenía el foco por última vez
	 */
	private Component getLastFocusedComponent() {
		return this.getMostRecentFocusOwner();
	}
	
	/**
	 * Comprueba si el nombre de usuario introducido es válido
	 * @return	true si el usuario es válido, falso en caso contrario
	 */
	private boolean checkUsername() {
		return manager.avaliableUsername(txtUsername.getText());
	}
	
	/**
	 * Compara la primera contraseña con la segunda para ver si coinciden
	 * @return	true si las contraseñas, false en caso contrario
	 */
	private boolean checkPasswords() {
		char[] pass = pFPass.getPassword();
		char[] rePass = pFRePass.getPassword();
		if (pass.length != rePass.length)		// Si las longitudes son distintas, no coinciden
			return false;
		for (int i=0; i<pass.length; i++)
			if (pass[i] != rePass[i])			// Si algun caracter no coinciden, las contraseñas tampoco
				return false;
		return true;
	}
	
	/**
	 * Comprueba que todos los campos obligatorios han sido cubiertos
	 * @return	true si han sido cubiertos, false si alguno está vacío
	 */
	private boolean checkObligatoryFields() {
		if (txtUsername.getText().length() == 0) {
			txtUsername.requestFocus();
			return false;	}
		if (pFPass.getPassword().length == 0) {
			pFPass.requestFocus();
			return false;	}
		if (pFRePass.getPassword().length == 0) {
			pFRePass.requestFocus();
			return false;	}
		if (txtName.getText().length() == 0) {
			txtName.requestFocus();
			return false;	}
		if (txtSurname.getText().length() == 0) {
			txtSurname.requestFocus();
			return false;	}
		if (txtNIF.getText().length() == 0) {
			txtNIF.requestFocus();
			return false;	}
		return true;
	}
	
	/**
	 * Comprueba que el número de teléfono introducido solo contiene dígitos
	 * @return	true si es válido, false si no lo es
	 */
	private boolean checkPhoneNumber() {
		return txtPhoneNumber.getText().matches("[0-9]*");
	}
	
	/**
	 * Comprueba que el registro sea válido y manda mensajes de advertencia en caso contrario
	 */
	private boolean validSignUpCheck() {
		if (!checkObligatoryFields()) {			// Si no están todos los campos llenos
			JOptionPane.showMessageDialog(this, fillEverythingWarning );
			return false;	}
		if (!checkUsername()) {					// Si el usuario no está disponible
			txtUsername.requestFocus();
			JOptionPane.showMessageDialog(this, invalidUsernameWarning );
			return false;	}
		if (!checkPasswords()) {					// Si el usuario no está disponible
			pFPass.selectAll();
			JOptionPane.showMessageDialog(this, invalidPasswordWarning );
			return false;	}
		if (!checkPhoneNumber()) {
			txtPhoneNumber.requestFocus();
			JOptionPane.showMessageDialog(this, invalidPhoneWarning );
			return false;	}
		return true;
	}
	
	/**
	 * Recoge los datos introducidos y los usa para crear un nuevo usuario
	 */
	private void createUser() {
		String[] params = new String[7];
		params[0] = txtUsername.getText();
		params[1] = String.valueOf(pFPass.getPassword());
		params[2] = txtNIF.getText();
		params[3] = txtName.getText();
		params[4] = txtSurname.getText();
		params[5] = genderButtonGroup.getSelection().getActionCommand();
		params[6] = txtPhoneNumber.getText();
		manager.addUser(params);
	}
	
	/**
	 * Envía una mensaje de confirmación del registro exitoso
	 */
	private void completeSignUp() {
		JOptionPane.showMessageDialog(this, sucessfulRegistration);
	}
	
	/**
	 * Cierra el diálogo
	 */
	private void close() {
		dispose();
	}
	
// Clases gestoras de eventos
	
	/**
	 * Da el foco al elemento pertinente cuando esta gana foco
	 */
	class FocusManager implements WindowFocusListener {
		private Component lastFocus = txtUsername;
		@Override
		public void windowGainedFocus(WindowEvent e) {
			lastFocus.requestFocus();
		}
		@Override
		public void windowLostFocus(WindowEvent e) {
			lastFocus = getLastFocusedComponent();
		}
	}
	
	/**
	 * Comprueba si el nombre de usuario está disponible
	 */
	class UserChecker extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent e) {
			if (checkUsername())
				lblInvalidUsername.setVisible(false);
			else
				lblInvalidUsername.setVisible(true);
		}
	} 
	
	/**
	 * Comprueba las contraseñas cuando deja de escribir en el campo de rePass
	 */
	class PasswordChecker extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent e) {
			if (checkPasswords())
				lblNoMatchingPass.setVisible(false);
			else
				lblNoMatchingPass.setVisible(true);
		}
	}
	
	/**
	 * Selecciona el contenido completo de los campos de contraseñas cuando ganan el foco
	 */
	class Selector extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			((JPasswordField) e.getComponent()).selectAll();
		}
	}
	
	/**
	 * Comprueba que el registro es válido
	 */
	class OkChecker implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (validSignUpCheck()) {
				createUser();
				completeSignUp();
				close();	}
		}	
	}
	
	/**
	 * Cierra el diálogo al pulsar el botón Cancel
	 */
	class Disposer extends AbstractAction {
		private static final long serialVersionUID = 8320872204796807179L;
		@Override
		public void actionPerformed(ActionEvent e) {
			close();
		}
	}
	
// Localización
	
	@Override
	public void locate() {
		Locale location = CurrentLocation.getLocale();
		JOptionPane.setDefaultLocale(location);
		// Carga del bundle
		bundle = ResourceBundle.getBundle("rcs/SignUp",location);		
		// Sustitución de textos
		this.setTitle(bundle.getString("title"));
		lblIntro.setText(bundle.getString("lblIntro"));				
		lblAsteriskWarning.setText(bundle.getString("lblAsteriskWarning"));	
		lblInvalidUsername.setText(bundle.getString("lblInvalidUsername"));
		lblUsername.setText(bundle.getString("lblUsername"));				
		lblPass.setText(bundle.getString("lblPass"));				
		lblRePass.setText(bundle.getString("lblRePass"));				
		lblNoMatchingPass.setText(bundle.getString("lblNoMatchingPass"));
		pnlPersonalData.setBorder(new TitledBorder(new LineBorder(new Color(100, 149, 237), 1, true), bundle.getString("pnlPersonalData"), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lblName.setText(bundle.getString("lblName"));
		lblSurname.setText(bundle.getString("lblSurname"));
		lblNIF.setText(bundle.getString("lblNIF"));
		lblGender.setText(bundle.getString("lblGender"));
		rdbtnMale.setText(bundle.getString("rdbtnMale"));
		rdbtnFemale.setText(bundle.getString("rdbtnFemale"));
		rdbtnOther.setText(bundle.getString("rdbtnOther"));
		lblPhoneNumber.setText(bundle.getString("lblPhoneNumber"));
		btnOk.setText(bundle.getString("btnOk"));
		btnCancel.setText(bundle.getString("btnCancel"));
		// Sustitución de mnemónicos de textos
		lblUsername.setDisplayedMnemonic(bundle.getString("mneUsername").charAt(0));
		lblPass.setDisplayedMnemonic(bundle.getString("mnePass").charAt(0));				
		lblRePass.setDisplayedMnemonic(bundle.getString("mneRePass").charAt(0));		
		lblName.setDisplayedMnemonic(bundle.getString("mneName").charAt(0));
		lblSurname.setDisplayedMnemonic(bundle.getString("mneSurname").charAt(0));
		lblNIF.setDisplayedMnemonic(bundle.getString("mneNIF").charAt(0));
		lblPhoneNumber.setDisplayedMnemonic(bundle.getString("mnePhoneNumber").charAt(0));
		// Sustitución de mnemómicos de botones
		rdbtnMale.setMnemonic(bundle.getString("mneMale").charAt(0));
		rdbtnFemale.setMnemonic(bundle.getString("mneFemale").charAt(0));
		rdbtnOther.setMnemonic(bundle.getString("mneOther").charAt(0));
		// Sustitución de tooltips de botones
		btnEs.setToolTipText(bundle.getString("toolBtnEs"));
		btnGl.setToolTipText(bundle.getString("toolBtnGl"));
		btnEn.setToolTipText(bundle.getString("toolBtnEn"));
		// Sustitución de mensajes
		sucessfulRegistration = bundle.getString("sucessfulRegistration");
		fillEverythingWarning = bundle.getString("fillEverythingWarning");
		invalidUsernameWarning = bundle.getString("invalidUsernameWarning");
		invalidPasswordWarning = bundle.getString("invalidPasswordWarning");
		invalidPhoneWarning = bundle.getString("invalidPhoneWarning");
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
		hb.enableHelpKey(getRootPane(),"registro", hs);
	}
	
}