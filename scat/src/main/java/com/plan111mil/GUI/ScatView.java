/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.plan111mil.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.plan111mil.data.ServicesDAO.HousingServices;
import com.plan111mil.data.ServicesDAO.UserServices;
import com.plan111mil.data.entity.HostService;
import com.plan111mil.data.entity.Housing;
import com.plan111mil.data.entity.OfficeAdmin;
import com.plan111mil.data.entity.Room;
import com.plan111mil.data.entity.User;
import com.plan111mil.exception.InvalidCredentialsException;
import com.plan111mil.filter.AvailableFilter;
import com.plan111mil.filter.CapacityFilter;
import com.plan111mil.filter.CommodityFilter;
import com.plan111mil.filter.Filter;
import com.plan111mil.filter.NameFilter;
import com.plan111mil.filter.TypeFilter;
import com.plan111mil.message.Message;

/**
 *
 * @author ET5002
 */
public class ScatView {
	private JFrame scatView;
	private User hUser;
	private JPanel rightPanel;

	// constantes para el archivo de configuración para recordar usuario
	private static final String CONF_FILE_NAME = "conf.properties";
	private static final String CONF_KEY_REMEMBER = "remember";
	private static final String CONF_KEY_USER = "user";
	private static final String CONF_KEY_PASSWORD = "pass";

	public ScatView() {
		this.scatView = new JFrame("Scat");
		scatView.setIconImage(new ImageIcon(getClass().getResource(LookAndFeel.SCAT_ICON)).getImage());
		GraphicsConfiguration config = scatView.getGraphicsConfiguration();

		int left = Toolkit.getDefaultToolkit().getScreenInsets(config).left;
		int right = Toolkit.getDefaultToolkit().getScreenInsets(config).right;
		int top = Toolkit.getDefaultToolkit().getScreenInsets(config).top;
		int bottom = Toolkit.getDefaultToolkit().getScreenInsets(config).bottom;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width - left - right;
		int height = screenSize.height - top - bottom;

		scatView.setResizable(false);
		scatView.setSize(width, height);
		scatView.setBackground(Color.white);

		scatView.getContentPane().add(returnWelcomeView());

		scatView.setVisible(true);
		scatView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// ------------------------------REUSABLE SCAT LOGO
	// PANEL------------------------------//
	private JPanel returnScatLogoPanel() {
		JPanel scatLogoPanelPanel = new JPanel();

		JLabel scatLogoLabel = new JLabel(new ImageIcon(getClass().getResource(LookAndFeel.SCAT_LOGO)));
		scatLogoPanelPanel.add(Box.createRigidArea(new Dimension(200, 200)));
		scatLogoPanelPanel.add(scatLogoLabel);

		scatLogoPanelPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		scatLogoPanelPanel.setLayout(new BoxLayout(scatLogoPanelPanel, BoxLayout.Y_AXIS));
		scatLogoPanelPanel.setVisible(true);

		return scatLogoPanelPanel;
	}

	// ------------------------------WELCOME VIEW------------------------------//
	private JPanel returnWelcomeView() {
		JPanel welcomeMainPanel = new JPanel();

		// ----------RIGHT PANEL----------//
		JPanel welcomeRightPanel = new JPanel();

		JLabel welcomeText = new JLabel("¡Bienvenido a Scat!");
		welcomeText.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		welcomeText.setAlignmentX(welcomeText.CENTER_ALIGNMENT);
		welcomeText.setFont(new Font("Bauhaus 93", Font.PLAIN, 34));

		PlaceHolder userText = new PlaceHolder();
		userText.setPlaceholder("Usuario");
		userText.setAlignmentX(userText.CENTER_ALIGNMENT);
		userText.setMaximumSize(new Dimension(400, 38));
		userText.setForeground(Color.gray);

		PlaceHolderPass passText = new PlaceHolderPass();
		passText.setPlaceholder("Contraseña");
		passText.setMaximumSize(new Dimension(400, 38));
		passText.setForeground(Color.gray);

		JCheckBox recordarmeCheck = new JCheckBox("Recordarme");
		recordarmeCheck.setBackground(LookAndFeel.CUSTOM_GREY);
		recordarmeCheck.setForeground(LookAndFeel.CUSTOM_BLUE);
		recordarmeCheck.setAlignmentX(recordarmeCheck.CENTER_ALIGNMENT);

		JButton loginButton = new JButton("INGRESAR");
		loginButton.setBackground(LookAndFeel.CUSTOM_DARK_BLUE);
		loginButton.setForeground(Color.white);
		loginButton.setAlignmentX(loginButton.CENTER_ALIGNMENT);
		loginButton.setMaximumSize(new Dimension(400, 38));
		JLabel message = new JLabel("");
		message.setForeground(Color.red);
		message.setAlignmentX(message.CENTER_ALIGNMENT);

		welcomeRightPanel.add(Box.createRigidArea(new Dimension(0, 170)));
		welcomeRightPanel.add(welcomeText);
		welcomeRightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		welcomeRightPanel.add(userText);
		welcomeRightPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		welcomeRightPanel.add(passText);
		welcomeRightPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		welcomeRightPanel.add(recordarmeCheck);
		welcomeRightPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		welcomeRightPanel.add(loginButton);
		welcomeRightPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		welcomeRightPanel.add(message);

		welcomeRightPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		welcomeRightPanel.setLayout(new BoxLayout(welcomeRightPanel, BoxLayout.Y_AXIS));
		welcomeRightPanel.setVisible(true);

		welcomeMainPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		BorderLayout mainLayout = new BorderLayout();
		welcomeMainPanel.setLayout(mainLayout);
		welcomeMainPanel.add(returnScatLogoPanel(), BorderLayout.WEST);
		welcomeMainPanel.add(welcomeRightPanel, BorderLayout.CENTER);
		mainLayout.setHgap(200);
		mainLayout.setVgap(70);
		welcomeMainPanel.setVisible(true);

		// cargo las configuraciones de recordar usuario y contraseña desde el archivo
		// de propiedades
		Properties conf = loadConf();
		// si en la configuracion de recordar usuario esta remember en 'true'
		if (Boolean.parseBoolean(conf.getProperty(CONF_KEY_REMEMBER))) {
			recordarmeCheck.setSelected(true); // se mantiene el tilde en recordarme
			userText.setText(conf.getProperty(CONF_KEY_USER));// el texto de user se carga con el guardado
			passText.setText(conf.getProperty(CONF_KEY_PASSWORD));// el texto de pass se carga con el guardado
		} else
			recordarmeCheck.setSelected(false);// sino recordarme se destilda

		// Botón ingresar, validación usuario y contraseña
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					String password = new String(passText.getPassword());
					UserServices uS = new UserServices();
					User user = uS.validateUser(userText.getText(), password);

					// si pasa la validación se salvan los datos de recordarme
					if (recordarmeCheck.isSelected()) { // si recordarme está seleccionado
						conf.setProperty(CONF_KEY_REMEMBER, Boolean.TRUE.toString());// la propiedad con clave
																						// 'remember' se vuelve 'true'
						conf.setProperty(CONF_KEY_USER, userText.getText());// la popiedad con clave 'user' se vuelve
																			// userText
						conf.setProperty(CONF_KEY_PASSWORD, password);// la propiedad con clave 'pass' se vuelve el
																		// texto de passText
					} else {
						conf.setProperty(CONF_KEY_REMEMBER, Boolean.FALSE.toString());// si es falso
						conf.setProperty(CONF_KEY_USER, "");// los valores estan vacios
						conf.setProperty(CONF_KEY_PASSWORD, "");
					}

					saveConf(conf);

					userText.setText("");
					passText.setText("");
					message.setText("");

					// dependiendo del tipo de usuario se redirecciona a la vista correspondiente
					if (user.isOfficeAdmin()) {
						hUser = user;
						scatView.getContentPane().removeAll();
						scatView.getContentPane().add(returnOfficeHomeView());
						scatView.validate();
						scatView.repaint();
					} else {
						hUser = user;
						scatView.getContentPane().removeAll();
						scatView.getContentPane().add(returnHousingHomeView());
						scatView.validate();
						scatView.repaint();
					}
				} catch (InvalidCredentialsException ex) {
					message.setText(ex.getMessage());// si no se puede validar el usuario se muestra msj de error
					userText.setText("");
					passText.setText("");
				}
			}
		});

		return welcomeMainPanel;
	}

	/**
	 * Devuelve un Properties con los datos del archivo de configuración si no
	 * existe el archivo crea el objeto con valores por defecto
	 * 
	 * @return Properties con los datos de recordarme (usuario, contraseña,
	 *         recordarme)
	 */
	private Properties loadConf() {
		// se crea un objeto de propiedades
		Properties conf = new Properties();
		InputStream is = null;
		try {
			// se inicializa desde un archivo
			is = new FileInputStream(CONF_FILE_NAME);
			conf.load(is);
		} catch (IOException e) {
			// valores por defecto en caso que no exista el archivo
			conf.setProperty(CONF_KEY_USER, "");
			conf.setProperty(CONF_KEY_PASSWORD, "**********");
			conf.setProperty(CONF_KEY_REMEMBER, Boolean.FALSE.toString());
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
					Logger.getLogger(ScatView.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return conf;
	}

	/**
	 * Pesiste en un archivo el Properties de configuración
	 * 
	 * @param conf
	 */
	private void saveConf(Properties conf) {
		FileOutputStream os = null;
		try {
			// se abre el archivo para escribir las propiedades
			os = new FileOutputStream(CONF_FILE_NAME);
			conf.store(os, "Archivo de configuracion local de SCAT");
		} catch (IOException ioe) {
			Logger.getLogger(ScatView.class.getName()).log(Level.SEVERE, null, ioe);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException ex) {
					Logger.getLogger(ScatView.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	// **********************************************OFFICE ADMIN
	// VIEW**********************************************//

	// ------------------------------REUSABLE OFFICE MENU
	// PANEL------------------------------//
	private JPanel returnOfficeMenuPanel(int selection) {
		JPanel menuPanel = new JPanel();

		JButton homeButton = new JButton("Home", new ImageIcon(getClass().getResource(LookAndFeel.HOME_BUTTON_ICON)));
		homeButton.setHorizontalTextPosition(SwingConstants.RIGHT);
		homeButton.setBackground(LookAndFeel.CUSTOM_BLUE);
		homeButton.setBorderPainted(false);
		homeButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		homeButton.setFont(new Font("Bauhaus", Font.BOLD, 16));
		homeButton.setForeground(Color.white);

		JButton buscarButton = new JButton("Buscar Hospedaje",
				new ImageIcon(getClass().getResource(LookAndFeel.BUSCAR_BUTTON_ICON)));
		buscarButton.setBackground(LookAndFeel.CUSTOM_BLUE);
		buscarButton.setBorderPainted(false);
		buscarButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		buscarButton.setFont(new Font("Bauhaus", Font.BOLD, 16));
		buscarButton.setForeground(Color.white);

		JButton agregarButton = new JButton("Agregar Hospedaje",
				new ImageIcon(getClass().getResource(LookAndFeel.AGREGAR_BUTTON_ICON)));
		agregarButton.setBackground(LookAndFeel.CUSTOM_BLUE);
		agregarButton.setBorderPainted(false);
		agregarButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		agregarButton.setFont(new Font("Bauhaus", Font.BOLD, 16));
		agregarButton.setForeground(Color.white);

		JButton salirButton = new JButton("Salir",
				new ImageIcon(getClass().getResource(LookAndFeel.SALIR_BUTTON_ICON)));
		salirButton.setBackground(LookAndFeel.CUSTOM_BLUE);
		salirButton.setBorderPainted(false);
		salirButton.setOpaque(false);
		salirButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		salirButton.setFont(new Font("Bauhaus", Font.BOLD, 16));
		salirButton.setForeground(Color.white);

		// muestra como seleccionado el boton de acuerdo a la vista que se este
		// mostrando
		switch (selection) {
		case 1:
			homeButton.setOpaque(true);
			buscarButton.setOpaque(false);
			agregarButton.setOpaque(false);
			break;
		case 2:
			homeButton.setOpaque(false);
			buscarButton.setOpaque(true);
			agregarButton.setOpaque(false);
			break;
		case 3:
			homeButton.setOpaque(false);
			buscarButton.setOpaque(false);
			agregarButton.setOpaque(true);
			break;
		}

		menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		menuPanel.add(homeButton);
		menuPanel.add(buscarButton);
		menuPanel.add(agregarButton);
		menuPanel.add(Box.createRigidArea(new Dimension(0, 500)));
		menuPanel.add(salirButton);

		menuPanel.setBackground(LookAndFeel.CUSTOM_GREEN);
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		menuPanel.setVisible(true);

		// cambia a la vista "Home"
		homeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				scatView.getContentPane().removeAll();
				scatView.getContentPane().add(returnOfficeHomeView());
				scatView.validate();
				scatView.repaint();
			}
		});

		// cambia a la vista "Buscar Hospedaje"
		buscarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {

				scatView.getContentPane().removeAll();
				scatView.getContentPane().add(returnOfficeSearchView(new HousingServices().getHousings()));
				scatView.validate();
				scatView.repaint();
			}
		});

		// cambia a la vista "Agregar Hospedaje"
		agregarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				scatView.getContentPane().removeAll();
				scatView.getContentPane().add(returnOfficeAddUserView());
				scatView.validate();
				scatView.repaint();
			}
		});

		// sale de la sesión y cambia a la pantalla de bienvenida/login
		salirButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				scatView.getContentPane().removeAll();
				scatView.getContentPane().add(returnWelcomeView());
				scatView.validate();
				scatView.repaint();
			}
		});

		return menuPanel;
	}

	// ------------------------------OFFICE HOME
	// VIEW------------------------------//
	private JPanel returnOfficeHomeView() {
		JPanel officeHomePanel = new JPanel();

		// ----------RIGHT PANEL----------//
		JPanel officeHomeRightPanel = new JPanel();
		JLabel welcomeLabel = new JLabel("¡Bienvenido!");
		welcomeLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 35));
		welcomeLabel.setForeground(LookAndFeel.CUSTOM_BLUE);

		JTextArea welcomeText = new JTextArea(Message.WELCOME_MESSAGE_1);
		welcomeText.setLineWrap(true);
		welcomeText.setWrapStyleWord(true);
		welcomeText.setOpaque(false);
		welcomeText.setEditable(false);
		welcomeText.setFont(new Font("Calibri", Font.BOLD, 18));
		welcomeText.setForeground(LookAndFeel.CUSTOM_BLUE);

		officeHomeRightPanel.add(welcomeLabel);
		officeHomeRightPanel.add(welcomeText);

		officeHomeRightPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		officeHomeRightPanel.setLayout(new GridLayout(0, 1, 0, 0));
		officeHomeRightPanel.setPreferredSize(new Dimension(500, 600));
		officeHomeRightPanel.setVisible(true);

		officeHomePanel.setBackground(LookAndFeel.CUSTOM_GREY);
		BorderLayout mainLayout = new BorderLayout();
		officeHomePanel.setLayout(mainLayout);
		officeHomePanel.add(returnOfficeMenuPanel(1), BorderLayout.WEST);
		officeHomePanel.add(returnScatLogoPanel(), BorderLayout.CENTER);
		officeHomePanel.add(officeHomeRightPanel, BorderLayout.EAST);
		officeHomePanel.setVisible(true);

		return officeHomePanel;
	}

	// ------------------------------OFFICE SEARCH
	// VIEW------------------------------//
	private JPanel returnOfficeSearchView(List<Housing> list) {
		JPanel officeSearchPanel = new JPanel();

		// ----------CENTER PANEL----------//
		JPanel officeSearchCenterPanel = new JPanel();

		DefaultListModel<HostService> listModel = new DefaultListModel();

		// Se actualiza los items de la JList
		refreshListPanel(list, listModel);

		JList<HostService> hsList = new JList(listModel);
		hsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		hsList.setCellRenderer(new HousingListRenderer());
		JScrollPane listScroller = new JScrollPane(hsList);
		listScroller.setPreferredSize(new Dimension(500, 650));
		listScroller.setVerticalScrollBarPolicy(listScroller.VERTICAL_SCROLLBAR_AS_NEEDED);

		JButton volverButton = new JButton("Volver");
		volverButton.setMaximumSize(new Dimension(400, 38));
		volverButton.setBackground(LookAndFeel.CUSTOM_DARK_BLUE);
		volverButton.setForeground(Color.white);
		volverButton.setAlignmentX(volverButton.LEFT_ALIGNMENT);
		volverButton.setVisible(false);

		JCheckBox checkDisp = new JCheckBox("Disponibles");
		checkDisp.setFont(new Font("Calibri", Font.BOLD, 16));
		checkDisp.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		checkDisp.setAlignmentX(checkDisp.RIGHT_ALIGNMENT);

		officeSearchCenterPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		officeSearchCenterPanel.add(checkDisp);
		officeSearchCenterPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		officeSearchCenterPanel.add(listScroller);
		officeSearchCenterPanel.add(volverButton);
		officeSearchCenterPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		officeSearchCenterPanel.setLayout(new BoxLayout(officeSearchCenterPanel, BoxLayout.Y_AXIS));
		officeSearchCenterPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		officeSearchCenterPanel.setVisible(true);

		// ----------RIGHT PANEL----------//
		JPanel officeSearchRightPanel = new JPanel();

		// ----------RIGHT top PANEL----------//
		JPanel officeSearchRightTopPanel = new JPanel();

		JLabel filterLabel = new JLabel("Filtros de búsqueda");
		filterLabel.setAlignmentX(filterLabel.LEFT_ALIGNMENT);
		filterLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		filterLabel.setBackground(Color.white);
		filterLabel.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);

		PlaceHolder nombreText = new PlaceHolder();
		nombreText.setPlaceholder("Nombre");
		nombreText.setMaximumSize(new Dimension(400, 38));
		nombreText.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);

		PlaceHolder cantPersonasText = new PlaceHolder();
		cantPersonasText.setPlaceholder("Cantidad de personas");
		cantPersonasText.setMaximumSize(new Dimension(400, 38));
		cantPersonasText.setBackground(Color.white);
		cantPersonasText.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);

		ArrayList<String> opcionesHousing = new ArrayList();
		opcionesHousing.add("Tipo de Hospedaje");
		opcionesHousing.addAll(Arrays.asList(Housing.HOUSING_TYPES));
		JComboBox tipoHousingCombo = new JComboBox(opcionesHousing.toArray());
		tipoHousingCombo.setMaximumSize(new Dimension(400, 38));
		tipoHousingCombo.setBackground(Color.white);
		tipoHousingCombo.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);

		ArrayList<String> opcionesRoom = new ArrayList<>();
		opcionesRoom.add("Tipo de Habitación");
		opcionesRoom.addAll(Arrays.asList(Room.ROOM_TYPES));
		JComboBox tipoRoomCombo = new JComboBox(opcionesRoom.toArray());
		tipoRoomCombo.setMaximumSize(new Dimension(400, 38));
		tipoRoomCombo.setBackground(Color.white);
		tipoRoomCombo.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);

		officeSearchRightTopPanel.add(filterLabel);
		officeSearchRightTopPanel.add(nombreText);
		officeSearchRightTopPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		officeSearchRightTopPanel.add(cantPersonasText);
		officeSearchRightTopPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		officeSearchRightTopPanel.add(tipoHousingCombo);
		officeSearchRightTopPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		officeSearchRightTopPanel.add(tipoRoomCombo);
		officeSearchRightTopPanel.add(Box.createRigidArea(new Dimension(0, 1)));

		officeSearchRightTopPanel.setLayout(new GridLayout(0, 1));
		officeSearchRightTopPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		officeSearchRightTopPanel.setVisible(true);

		// ----------RIGHT bottom PANEL----------//
		JPanel officeSearchRightBottomPanel = new JPanel();

		JLabel housingCommoditiesLabel = new JLabel("Servicios del Hospedaje");
		housingCommoditiesLabel.setAlignmentX(housingCommoditiesLabel.LEFT_ALIGNMENT);
		housingCommoditiesLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		housingCommoditiesLabel.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		JCheckBox housingCommodityCheck1 = new JCheckBox("Estacionamiento");
		housingCommodityCheck1.setFont(new Font("Calibri", Font.BOLD, 14));
		housingCommodityCheck1.setAlignmentX(housingCommodityCheck1.LEFT_ALIGNMENT);
		housingCommodityCheck1.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		housingCommodityCheck1.setBackground(LookAndFeel.CUSTOM_GREY);
		JCheckBox housingCommodityCheck2 = new JCheckBox("Pileta de natacion");
		housingCommodityCheck2.setFont(new Font("Calibri", Font.BOLD, 14));
		housingCommodityCheck2.setAlignmentX(housingCommodityCheck2.LEFT_ALIGNMENT);
		housingCommodityCheck2.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		housingCommodityCheck2.setBackground(LookAndFeel.CUSTOM_GREY);
		JCheckBox housingCommodityCheck3 = new JCheckBox("WIFI");
		housingCommodityCheck3.setFont(new Font("Calibri", Font.BOLD, 14));
		housingCommodityCheck3.setAlignmentX(housingCommodityCheck3.LEFT_ALIGNMENT);
		housingCommodityCheck3.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		housingCommodityCheck3.setBackground(LookAndFeel.CUSTOM_GREY);
		JCheckBox housingCommodityCheck4 = new JCheckBox("Desayuno");
		housingCommodityCheck4.setFont(new Font("Calibri", Font.BOLD, 14));
		housingCommodityCheck4.setAlignmentX(housingCommodityCheck4.LEFT_ALIGNMENT);
		housingCommodityCheck4.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		housingCommodityCheck4.setBackground(LookAndFeel.CUSTOM_GREY);

		JLabel roomCommoditiesLabel = new JLabel("Servicios de la Habitación");
		roomCommoditiesLabel.setAlignmentX(roomCommoditiesLabel.LEFT_ALIGNMENT);
		roomCommoditiesLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		roomCommoditiesLabel.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		JCheckBox roomCommodityCheck1 = new JCheckBox("Jacuzzi");
		roomCommodityCheck1.setFont(new Font("Calibri", Font.BOLD, 14));
		roomCommodityCheck1.setAlignmentX(roomCommodityCheck1.LEFT_ALIGNMENT);
		roomCommodityCheck1.setBackground(LookAndFeel.CUSTOM_GREY);
		roomCommodityCheck1.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		JCheckBox roomCommodityCheck2 = new JCheckBox("TV");
		roomCommodityCheck2.setFont(new Font("Calibri", Font.BOLD, 14));
		roomCommodityCheck2.setAlignmentX(roomCommodityCheck2.LEFT_ALIGNMENT);
		roomCommodityCheck2.setBackground(LookAndFeel.CUSTOM_GREY);
		roomCommodityCheck2.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);

		JButton clearFiltersButton = new JButton("Limpiar filtros");
		clearFiltersButton.setBackground(LookAndFeel.CUSTOM_BLUE);
		clearFiltersButton.setAlignmentX(filterLabel.CENTER_ALIGNMENT);
		clearFiltersButton.setFont(new Font("Bauhaus", Font.BOLD, 14));
		clearFiltersButton.setForeground(Color.white);
		clearFiltersButton.setBorder(BorderFactory.createEmptyBorder());
		clearFiltersButton.setMaximumSize(new Dimension(50, 48));

		JButton buscarButton = new JButton("Buscar");
		buscarButton.setBackground(LookAndFeel.CUSTOM_BLUE);
		buscarButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		buscarButton.setAlignmentX(filterLabel.CENTER_ALIGNMENT);
		buscarButton.setFont(new Font("Bauhaus", Font.BOLD, 14));
		buscarButton.setForeground(Color.white);
		buscarButton.setBorder(BorderFactory.createEmptyBorder());
		buscarButton.setSize(new Dimension(50, 38));

		officeSearchRightBottomPanel.add(housingCommoditiesLabel);
		officeSearchRightBottomPanel.add(housingCommodityCheck1);
		officeSearchRightBottomPanel.add(housingCommodityCheck2);
		officeSearchRightBottomPanel.add(housingCommodityCheck3);
		officeSearchRightBottomPanel.add(housingCommodityCheck4);
		officeSearchRightBottomPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		officeSearchRightBottomPanel.add(roomCommoditiesLabel);
		officeSearchRightBottomPanel.add(roomCommodityCheck1);
		officeSearchRightBottomPanel.add(roomCommodityCheck2);
		// officeSearchRightBottomPanel.add(Box.createRigidArea(new Dimension(0,8)));
		officeSearchRightBottomPanel.add(buscarButton);
		officeSearchRightBottomPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		officeSearchRightBottomPanel.add(clearFiltersButton);

		officeSearchRightBottomPanel.setLayout(new GridLayout(0, 1, 0, 0));
		officeSearchRightBottomPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		officeSearchRightBottomPanel.setVisible(true);

		officeSearchRightPanel.add(officeSearchRightTopPanel);
		officeSearchRightPanel.add(officeSearchRightBottomPanel);

		officeSearchRightPanel.setLayout(new BoxLayout(officeSearchRightPanel, BoxLayout.PAGE_AXIS));
		officeSearchRightPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		officeSearchRightPanel.setPreferredSize(new Dimension(300, 600));

		rightPanel = officeSearchRightPanel;

		officeSearchPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		BorderLayout mainLayout = new BorderLayout();
		officeSearchPanel.setLayout(mainLayout);
		officeSearchPanel.add(returnOfficeMenuPanel(2), BorderLayout.WEST);
		officeSearchPanel.add(officeSearchCenterPanel, BorderLayout.CENTER);
		officeSearchPanel.add(rightPanel, BorderLayout.EAST);
		mainLayout.setHgap(50);
		officeSearchPanel.setVisible(true);

		cantPersonasText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				if (!Character.isDigit(caracter)) {
					e.consume();
				}
			}
		});

		clearFiltersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				nombreText.setText("");
				cantPersonasText.setText("");
				tipoHousingCombo.setSelectedIndex(0);
				tipoRoomCombo.setSelectedIndex(0);
				housingCommodityCheck1.setSelected(false);
				housingCommodityCheck2.setSelected(false);
				housingCommodityCheck3.setSelected(false);
				housingCommodityCheck4.setSelected(false);
				roomCommodityCheck1.setSelected(false);
				roomCommodityCheck2.setSelected(false);
				checkDisp.setSelected(false);
			}
		});

		buscarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				List<Filter> fl = new ArrayList<>();

				// name filter / nombre
				if (!nombreText.getText().equals(""))
					fl.add(new NameFilter((nombreText.getText())));

				// capacity filter/cantidad de huespedes
				if (!cantPersonasText.getText().equals(""))
					fl.add(new CapacityFilter(Integer.parseInt(cantPersonasText.getText())));

				// type filter / tipo de hospedaje
				if (!(String.valueOf(tipoHousingCombo.getSelectedItem()).equals("Tipo de Hospedaje")))
					fl.add(new TypeFilter(String.valueOf(tipoHousingCombo.getSelectedItem())));

				// type filter / tipo de habitacion
				if (!(String.valueOf(tipoRoomCombo.getSelectedItem()).equals("Tipo de Habitación")))
					fl.add(new TypeFilter(String.valueOf(tipoRoomCombo.getSelectedItem())));

				// commodities filter / servicios
				if (housingCommodityCheck1.isSelected())
					fl.add(new CommodityFilter(housingCommodityCheck1.getText()));

				if (housingCommodityCheck2.isSelected())
					fl.add(new CommodityFilter(housingCommodityCheck2.getText()));

				if (housingCommodityCheck3.isSelected())
					fl.add(new CommodityFilter(housingCommodityCheck3.getText()));

				if (housingCommodityCheck4.isSelected())
					fl.add(new CommodityFilter(housingCommodityCheck4.getText()));

				if (roomCommodityCheck1.isSelected())
					fl.add(new CommodityFilter(roomCommodityCheck1.getText()));

				if (roomCommodityCheck2.isSelected())
					fl.add(new CommodityFilter(roomCommodityCheck2.getText()));

				if (checkDisp.isSelected())
					fl.add(new AvailableFilter());

				refreshListPanel(new HousingServices().getFilteredListOfHousings(fl),
						(DefaultListModel<HostService>) hsList.getModel());
				scatView.validate();
				scatView.repaint();

			}
		});

		// recupera las rooms del housing seleccionado y las muestra
		hsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					if (hsList.getSelectedIndex() >= 0) {
						Housing selectedHousing = ((Housing) hsList.getSelectedValue());
						List<Room> rList = selectedHousing.getRooms();

						JList<HostService> roomsList = new JList(rList.toArray());
						roomsList.setCellRenderer(new RoomListRenderer());

						// pongo invisible el panel que muestra los housings
						listScroller.setVisible(false);

						JScrollPane roomsListScroller = new JScrollPane(roomsList);
						roomsListScroller.setPreferredSize(new Dimension(500, 650));
						roomsListScroller.setVerticalScrollBarPolicy(roomsListScroller.VERTICAL_SCROLLBAR_AS_NEEDED);

						// muestro la lista de las habitaciones
						officeSearchCenterPanel.add(roomsListScroller);
						volverButton.setVisible(true);

						// muestro la info del hospedaje seleccionado
						officeSearchPanel.remove(rightPanel);
						rightPanel = returnHousingInfoPanel(selectedHousing);
						officeSearchPanel.add(rightPanel, BorderLayout.EAST);
						scatView.validate();
						scatView.repaint();

						// vuelve a mostrar los hospedajes y el panel de filtros
						volverButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent ae) {
								volverButton.setVisible(false);
								roomsListScroller.setVisible(false);
								listScroller.setVisible(true);
								hsList.clearSelection();
								officeSearchPanel.remove(rightPanel);
								rightPanel = officeSearchRightPanel;
								officeSearchPanel.add(rightPanel, BorderLayout.EAST);
								scatView.validate();
								scatView.repaint();
							}
						});
					}
				}
			}
		});

		return officeSearchPanel;
	}

	/**
	 * Este método actualiza la lista de los hospedajes que debe mostrar de acuerdo
	 * a los filtros que fueron aplicados
	 * 
	 * @param hList
	 * @param hsListModel
	 */
	private void refreshListPanel(List<Housing> hList, DefaultListModel<HostService> hsListModel) {
		hsListModel.clear();
		for (HostService host : hList)
			hsListModel.addElement(host);
	}

	private JPanel returnHousingInfoPanel(Housing selectedHousing) {
		// ----------RIGHT PANEL 2----------//
		JPanel officeSearchRightPanel2 = new JPanel();

		JLabel hName = new JLabel(selectedHousing.getName());
		hName.setFont(new Font("Bauhaus", Font.BOLD, 20));
		JLabel hAddress = new JLabel(selectedHousing.getAddress());
		hAddress.setFont(new Font("Calibri", Font.BOLD, 16));
		JLabel hPhone = new JLabel(selectedHousing.getPhone());
		hPhone.setFont(new Font("Calibri", Font.BOLD, 16));
		JLabel hMail = new JLabel(selectedHousing.getMail());
		hMail.setFont(new Font("Calibri", Font.BOLD, 16));
		JLabel hDescription = new JLabel(selectedHousing.getDescription());
		hDescription.setFont(new Font("Calibri", Font.BOLD, 18));
		JLabel hCommodities = new JLabel("Servicios: " + selectedHousing.getCommodities().toString());
		hDescription.setFont(new Font("Calibri", Font.BOLD, 18));

		JButton volverButton = new JButton("Volver");
		volverButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		volverButton.setBackground(LookAndFeel.CUSTOM_BLUE);
		volverButton.setAlignmentX(volverButton.CENTER_ALIGNMENT);
		volverButton.setVisible(false);

		officeSearchRightPanel2.add(Box.createRigidArea(new Dimension(0, 30)));
		officeSearchRightPanel2.add(hName);
		officeSearchRightPanel2.add(Box.createRigidArea(new Dimension(0, 5)));
		officeSearchRightPanel2.add(hAddress);
		officeSearchRightPanel2.add(Box.createRigidArea(new Dimension(0, 5)));
		officeSearchRightPanel2.add(hPhone);
		officeSearchRightPanel2.add(Box.createRigidArea(new Dimension(0, 5)));
		officeSearchRightPanel2.add(hMail);
		officeSearchRightPanel2.add(Box.createRigidArea(new Dimension(0, 5)));
		officeSearchRightPanel2.add(hDescription);
		officeSearchRightPanel2.add(Box.createRigidArea(new Dimension(0, 5)));
		officeSearchRightPanel2.add(hCommodities);

		officeSearchRightPanel2.setLayout(new BoxLayout(officeSearchRightPanel2, BoxLayout.PAGE_AXIS));
		officeSearchRightPanel2.setBackground(LookAndFeel.CUSTOM_GREY);
		officeSearchRightPanel2.setPreferredSize(new Dimension(400, 600));
		officeSearchRightPanel2.setVisible(true);

		return officeSearchRightPanel2;
	}

	// ------------------------------OFFICE ADD USER
	// VIEW------------------------------//
	private JPanel returnOfficeAddUserView() {
		JPanel officeAddUserPanel = new JPanel();
		JPanel officeAddUserRightPanel = new JPanel();

		JLabel crearUserLabel = new JLabel("Crear Usuario");
		crearUserLabel.setAlignmentX(crearUserLabel.LEFT_ALIGNMENT);
		crearUserLabel.setFont(new Font("Calibri", Font.BOLD, 35));

		PlaceHolder crearUserText = new PlaceHolder();
		crearUserText.setPlaceholder("Usuario");
		crearUserText.setAlignmentX(crearUserLabel.LEFT_ALIGNMENT);
		crearUserText.setMaximumSize(new Dimension(400, 25));
		crearUserText.setForeground(Color.gray);

		PlaceHolder crearPassText = new PlaceHolder();
		crearPassText.setPlaceholder("Contraseña");
		crearPassText.setAlignmentX(crearUserLabel.LEFT_ALIGNMENT);
		crearPassText.setMaximumSize(new Dimension(400, 25));
		crearPassText.setForeground(Color.gray);

		JCheckBox userCheck = new JCheckBox("Usuario Hospedaje");
		userCheck.setBackground(LookAndFeel.CUSTOM_GREY);
		JCheckBox adminCheck = new JCheckBox("Usuario Oficina de Turismo");
		adminCheck.setBackground(LookAndFeel.CUSTOM_GREY);

		ButtonGroup group = new ButtonGroup();
		group.add(userCheck);
		group.add(adminCheck);

		JButton crearButton = new JButton("CREAR");
		crearButton.setMaximumSize(new Dimension(400, 20));

		JLabel selectOne = new JLabel("Seleccione el tipo de usuario por favor");
		selectOne.setFont(new Font("Calibri", Font.BOLD, 16));
		selectOne.setForeground(Color.red);
		selectOne.setVisible(false);

		officeAddUserRightPanel.add(Box.createRigidArea(new Dimension(150, 200)));
		officeAddUserRightPanel.add(crearUserLabel);
		officeAddUserRightPanel.add(Box.createRigidArea(new Dimension(0, 35)));
		officeAddUserRightPanel.add(crearUserText);
		officeAddUserRightPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		officeAddUserRightPanel.add(crearPassText);
		officeAddUserRightPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		officeAddUserRightPanel.add(userCheck);
		officeAddUserRightPanel.add(adminCheck);
		officeAddUserRightPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		officeAddUserRightPanel.add(crearButton);
		officeAddUserRightPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		officeAddUserRightPanel.add(selectOne);

		officeAddUserRightPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		officeAddUserRightPanel.setLayout(new BoxLayout(officeAddUserRightPanel, BoxLayout.Y_AXIS));
		officeAddUserRightPanel.setVisible(true);

		officeAddUserPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		BorderLayout mainLayout = new BorderLayout();
		officeAddUserPanel.setLayout(mainLayout);
		officeAddUserPanel.add(returnOfficeMenuPanel(3), BorderLayout.WEST);
		officeAddUserPanel.add(officeAddUserRightPanel, BorderLayout.CENTER);
		mainLayout.setVgap(100);
		officeAddUserPanel.setVisible(true);

		// chequea el tipo de usuario a crear y cambia a la vista correspondiente
		crearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// chequea que los campos no esten vacios
				if (crearUserText.getText().equals("") || crearPassText.getText().equals("")) {
					JOptionPane.showMessageDialog(scatView, Message.CREARBUTTON_MESSAGE_1);
				} else {
					// si se seleccionó un usuario de hospedaje, crea el objeto User
					// y pasa a la vista de creación de hospedaje
					if (userCheck.isSelected()) {
						if (new UserServices().userExists(crearUserText.getText()) == false) {
							hUser = new User(crearUserText.getText(), crearPassText.getText(),
									User.USER_TYPE_HOUSING_USER);
							scatView.getContentPane().removeAll();
							scatView.getContentPane().add(returnOfficeAddHousingView());
							scatView.validate();
							scatView.repaint();
						} else {// sino se guardó muestra un error de posible usuario duplicado
							JOptionPane.showMessageDialog(scatView, Message.CREARBUTTON_MESSAGE_2);
						}
					} // si se seleccionó un usuario de oficina de turismo, crea el objeto OfficeAdmin
						// y cambia a la vista Home de oficina de turismo
					else {
						if (adminCheck.isSelected()) {
							hUser = new OfficeAdmin(crearUserText.getText(), crearPassText.getText(),
									User.USER_TYPE_OFFICE_ADMIN);
							if (new UserServices().persistUser(hUser)) { // si se pudo guardar el usuario en BD
								JOptionPane.showMessageDialog(scatView, Message.SUCCESS_MESSAGE_1);

								crearUserText.setText("");
								crearPassText.setText("");
								group.clearSelection();
							} else // sino se guardó muestra un error de posible usuario duplicado
							{
								JOptionPane.showMessageDialog(scatView, Message.CREARBUTTON_MESSAGE_2);
							}
						} else {
							selectOne.setVisible(true);
						}
					}

				}

			}
		});

		return officeAddUserPanel;
	}

	// ------------------------------OFFICE ADD HOUSING
	// VIEW------------------------------//
	private JPanel returnOfficeAddHousingView() {

		// ----------LEFT PANEL----------//
		JPanel officeAddHousingLeftPanel = new JPanel();
		JLabel agregarHospedajeText = new JLabel();
		agregarHospedajeText.setAlignmentX(agregarHospedajeText.LEFT_ALIGNMENT);
		agregarHospedajeText.setMaximumSize(new Dimension(400, 20));
		agregarHospedajeText.setFont(new Font("Calibri", Font.BOLD, 18));

		// si lo llamo desde la vista de crear user...
		if (hUser.getHousing() == null)
			agregarHospedajeText.setText("Agregar Hospedaje");
		// si lo llamo desde la vista de modificar hospedaje...
		else
			agregarHospedajeText.setText("Actualizar datos del hospedaje");

		PlaceHolder housingName = new PlaceHolder();
		housingName.setPlaceholder("Nombre del hospedaje");
		housingName.setMaximumSize(new Dimension(400, 25));
		housingName.setBackground(LookAndFeel.CUSTOM_GREY);

		PlaceHolder housingPhone = new PlaceHolder();
		housingPhone.setPlaceholder("Teléfono");
		housingPhone.setMaximumSize(new Dimension(400, 25));
		housingPhone.setBackground(LookAndFeel.CUSTOM_GREY);

		ArrayList<String> opcionesHousing = new ArrayList();
		opcionesHousing.add("Tipo de hospedaje");
		opcionesHousing.addAll(Arrays.asList(Housing.HOUSING_TYPES));

		JComboBox tipoHousingCombo = new JComboBox(opcionesHousing.toArray());
		tipoHousingCombo.setMaximumSize(new Dimension(400, 25));
		tipoHousingCombo.setBackground(LookAndFeel.CUSTOM_GREY);

		PlaceHolderTextArea description = new PlaceHolderTextArea();
		description.setPlaceholder("Descripción");
		description.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		description.setMaximumSize(new Dimension(400, 100));
		description.setBackground(LookAndFeel.CUSTOM_GREY);

		officeAddHousingLeftPanel.add(Box.createRigidArea(new Dimension(0, 70)));
		officeAddHousingLeftPanel.add(agregarHospedajeText);
		officeAddHousingLeftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		officeAddHousingLeftPanel.add(housingName);
		officeAddHousingLeftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		officeAddHousingLeftPanel.add(housingPhone);
		officeAddHousingLeftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		officeAddHousingLeftPanel.add(tipoHousingCombo);
		officeAddHousingLeftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		officeAddHousingLeftPanel.add(description);

		officeAddHousingLeftPanel.setLayout(new BoxLayout(officeAddHousingLeftPanel, BoxLayout.Y_AXIS));
		officeAddHousingLeftPanel.setBackground(LookAndFeel.CUSTOM_GREY);

		// ----------RIGHT top PANEL----------//
		JPanel officeAddHousingRightTopPanel = new JPanel();

		PlaceHolder housingAddress = new PlaceHolder();
		housingAddress.setPlaceholder("Dirección");
		housingAddress.setMaximumSize(new Dimension(400, 25));
		housingAddress.setBackground(LookAndFeel.CUSTOM_GREY);

		PlaceHolder housingEmail = new PlaceHolder();
		housingEmail.setPlaceholder("Dirección de correo electrónico");
		housingEmail.setMaximumSize(new Dimension(400, 25));
		housingEmail.setBackground(LookAndFeel.CUSTOM_GREY);

		JLabel housingCommoditiesLabel = new JLabel("Servicios del Hospedaje");
		JCheckBox housingCommodityCheck1 = new JCheckBox("Estacionamiento");
		housingCommodityCheck1.setBackground(LookAndFeel.CUSTOM_GREY);
		JCheckBox housingCommodityCheck2 = new JCheckBox("Pileta de natacion");
		housingCommodityCheck2.setBackground(LookAndFeel.CUSTOM_GREY);
		JCheckBox housingCommodityCheck3 = new JCheckBox("WIFI");
		housingCommodityCheck3.setBackground(LookAndFeel.CUSTOM_GREY);
		JCheckBox housingCommodityCheck4 = new JCheckBox("Desayuno");
		housingCommodityCheck4.setBackground(LookAndFeel.CUSTOM_GREY);

		officeAddHousingRightTopPanel.add(Box.createRigidArea(new Dimension(0, 110)));
		officeAddHousingRightTopPanel.add(housingAddress);
		officeAddHousingRightTopPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		officeAddHousingRightTopPanel.add(housingEmail);
		officeAddHousingRightTopPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		officeAddHousingRightTopPanel.add(housingCommoditiesLabel);
		officeAddHousingRightTopPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		officeAddHousingRightTopPanel.add(housingCommodityCheck1);
		officeAddHousingRightTopPanel.add(housingCommodityCheck2);
		officeAddHousingRightTopPanel.add(housingCommodityCheck3);
		officeAddHousingRightTopPanel.add(housingCommodityCheck4);
		officeAddHousingRightTopPanel.add(Box.createRigidArea(new Dimension(0, 100)));

		officeAddHousingRightTopPanel.setLayout(new BoxLayout(officeAddHousingRightTopPanel, BoxLayout.Y_AXIS));
		officeAddHousingRightTopPanel.setBackground(LookAndFeel.CUSTOM_GREY);

		// ----------RIGHT bottom PANEL----------//
		JPanel officeAddHousingRightBottomPanel = new JPanel();

		JButton cancelButton = new JButton("CANCELAR");
		cancelButton.setBackground(LookAndFeel.CUSTOM_DARK_BLUE);
		cancelButton.setForeground(Color.white);
		JButton saveButton = new JButton("GUARDAR");
		saveButton.setBackground(LookAndFeel.CUSTOM_DARK_BLUE);
		saveButton.setForeground(Color.white);

		officeAddHousingRightBottomPanel.add(Box.createRigidArea(new Dimension(0, 600)));
		officeAddHousingRightBottomPanel.add(cancelButton);
		officeAddHousingRightBottomPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		officeAddHousingRightBottomPanel.add(saveButton);
		officeAddHousingRightBottomPanel.setLayout(new FlowLayout());
		officeAddHousingRightBottomPanel.setBackground(LookAndFeel.CUSTOM_GREY);

		// ----------RIGHT PANEL----------//
		JPanel officeAddHousingRightPanel = new JPanel();

		officeAddHousingRightPanel.add(officeAddHousingRightTopPanel);
		officeAddHousingRightPanel.add(officeAddHousingRightBottomPanel);

		officeAddHousingRightPanel.setLayout(new GridLayout(0, 1));
		officeAddHousingRightPanel.setPreferredSize(new Dimension(600, 600));
		officeAddHousingRightPanel.setBackground(LookAndFeel.CUSTOM_GREY);

		JPanel officeAddHousingPanel = new JPanel();

		officeAddHousingPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		BorderLayout mainLayout = new BorderLayout();
		officeAddHousingPanel.setLayout(mainLayout);

		housingPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (!Character.isDigit(caracter)) {
					e.consume();
				}
			}
		});

		// si estoy creando el usuario...
		if (hUser.getHousing() == null) {
			officeAddHousingPanel.add(returnOfficeMenuPanel(3), BorderLayout.WEST);
			// Si es usuario de hospedaje, carga la informacion existente
		} else {
			officeAddHousingPanel.add(returnHousingMenuPanel(2), BorderLayout.WEST);

			housingAddress.setText(hUser.getHousing().getAddress());
			housingEmail.setText(hUser.getHousing().getMail());
			housingPhone.setText(hUser.getHousing().getPhone());
			housingName.setText(hUser.getHousing().getName());
			description.setText(hUser.getHousing().getDescription());
			tipoHousingCombo.setSelectedItem(String.valueOf(hUser.getHousing().getHostServiceType()));

			if (hUser.getHousing().getCommodities().contains("Estacionamiento"))
				housingCommodityCheck1.setSelected(true);

			if (hUser.getHousing().getCommodities().contains("Pileta de natacion"))
				housingCommodityCheck2.setSelected(true);

			if (hUser.getHousing().getCommodities().contains("WIFI"))
				housingCommodityCheck3.setSelected(true);

			if (hUser.getHousing().getCommodities().contains("Desayuno"))
				housingCommodityCheck4.setSelected(true);
		}

		officeAddHousingPanel.add(officeAddHousingLeftPanel, BorderLayout.CENTER);
		officeAddHousingPanel.add(officeAddHousingRightPanel, BorderLayout.EAST);
		mainLayout.setVgap(100);
		mainLayout.setHgap(100);
		officeAddHousingPanel.setVisible(true);

		// guarda el hospedaje creado
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// si viene desde crear user/housing...
				if (hUser.getHousing() == null) {
					// comprueba que no haya ningun campo vacio
					if (housingName.getText().equals("") || housingPhone.getText().equals("")
							|| tipoHousingCombo.getSelectedIndex() == 0 || description.getText().equals("")
							|| housingAddress.getText().equals("") || housingEmail.getText().equals("")) {
						JOptionPane.showMessageDialog(scatView, Message.CREARBUTTON_MESSAGE_1);
					} else {
						Housing h = new Housing(housingAddress.getText(), housingEmail.getText(),
								housingPhone.getText(), housingName.getText(), description.getText(),
								String.valueOf(tipoHousingCombo.getSelectedItem()), hUser);

						if (housingCommodityCheck1.isSelected()) {
							h.addCommodity(housingCommodityCheck1.getText());
						}

						if (housingCommodityCheck2.isSelected()) {
							h.addCommodity(housingCommodityCheck2.getText());
						}

						if (housingCommodityCheck3.isSelected()) {
							h.addCommodity(housingCommodityCheck3.getText());
						}

						if (housingCommodityCheck4.isSelected()) {
							h.addCommodity(housingCommodityCheck4.getText());
						}

						if (new UserServices().userExists(h.getName()))
							JOptionPane.showMessageDialog(scatView, Message.FAILED_MESSAGE_1);
						else {
							new UserServices().persistUser(hUser);
							new HousingServices().persistHousing(h);
							JOptionPane.showMessageDialog(scatView, Message.SUCCESS_MESSAGE_2);
							scatView.getContentPane().removeAll();
							scatView.getContentPane().add(returnOfficeAddUserView());
							scatView.validate();
							scatView.repaint();
						}

					}
				} // Si es usuario de hospedaje, guarda los cambios realizados al hospedaje
				else {

					hUser.getHousing().setAddress(housingAddress.getText());
					hUser.getHousing().setMail(housingEmail.getText());
					hUser.getHousing().setPhone(housingPhone.getText());
					hUser.getHousing().setName(housingName.getText());
					hUser.getHousing().setDescription(description.getText());
					hUser.getHousing().setHostServiceType(String.valueOf(tipoHousingCombo.getSelectedItem()));

					hUser.getHousing().getCommodities().clear();

					if (housingCommodityCheck1.isSelected()) {
						hUser.getHousing().addCommodity(housingCommodityCheck1.getText());
					}

					if (housingCommodityCheck2.isSelected()) {
						hUser.getHousing().addCommodity(housingCommodityCheck2.getText());
					}

					if (housingCommodityCheck3.isSelected()) {
						hUser.getHousing().addCommodity(housingCommodityCheck3.getText());
					}

					if (housingCommodityCheck4.isSelected()) {
						hUser.getHousing().addCommodity(housingCommodityCheck4.getText());
					}

					new HousingServices().persistHousing(hUser.getHousing());

					JOptionPane.showMessageDialog(scatView, Message.SUCCESS_MESSAGE_3);

				}

			}
		});

		// Muestra un cartel de advertencia. Si selecciona aceptar, cancela la creacion
		// del hospedaje,
		// borra el usuario y muestra la vista "Home"
		// Si selecciona cancelar, le permite continuar con la creación del hospedaje.
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {

				// si viene desde crear user/housing...
				if (hUser.getHousing() == null) {
					int selection = JOptionPane.showConfirmDialog(scatView, Message.CANCELBUTTON_MESSAGE_3,
							"Advertencia", JOptionPane.WARNING_MESSAGE);
					// si presiona aceptar
					if (selection == 0) {
						scatView.getContentPane().removeAll();
						scatView.getContentPane().add(returnOfficeHomeView());
						scatView.validate();
						scatView.repaint();
					}
					// si esta modificando el housing existente...
				} else {
					int selection = JOptionPane.showConfirmDialog(scatView, Message.CANCELBUTTON_MESSAGE_4,
							"Advertencia", JOptionPane.WARNING_MESSAGE);
					// si presiona aceptar
					if (selection == 0) {
						scatView.getContentPane().removeAll();
						scatView.getContentPane().add(returnHousingHomeView());
						scatView.validate();
						scatView.repaint();
					}
				}
			}
		});

		return officeAddHousingPanel;
	}

	// **********************************************HOUSING
	// VIEW**********************************************//

	// ------------------------------REUSABLE HOUSING MENU
	// PANEL------------------------------//
	private JPanel returnHousingMenuPanel(int selection) {
		JPanel menuPanel = new JPanel();

		JButton homeButton = new JButton("Home", new ImageIcon(getClass().getResource(LookAndFeel.HOME_BUTTON_ICON)));
		homeButton.setHorizontalTextPosition(SwingConstants.RIGHT);
		homeButton.setBackground(LookAndFeel.CUSTOM_BLUE);
		homeButton.setBorderPainted(false);
		homeButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		homeButton.setFont(new Font("Bauhaus", Font.BOLD, 16));
		homeButton.setForeground(Color.white);
		homeButton.setAlignmentX(homeButton.LEFT_ALIGNMENT);

		JButton editarButton = new JButton("Editar Perfil",
				new ImageIcon(getClass().getResource(LookAndFeel.EDITAR_HOUSING_BUTTON_ICON)));
		editarButton.setHorizontalTextPosition(SwingConstants.RIGHT);
		editarButton.setBackground(LookAndFeel.CUSTOM_BLUE);
		editarButton.setBorderPainted(false);
		editarButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		editarButton.setFont(new Font("Bauhaus", Font.BOLD, 16));
		editarButton.setForeground(Color.white);

		JButton habitacionesButton = new JButton("Habitaciones",
				new ImageIcon(getClass().getResource(LookAndFeel.HABITACIONES_BUTTON_ICON)));
		habitacionesButton.setHorizontalTextPosition(SwingConstants.RIGHT);
		habitacionesButton.setBackground(LookAndFeel.CUSTOM_BLUE);
		habitacionesButton.setBorderPainted(false);
		habitacionesButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		habitacionesButton.setFont(new Font("Bauhaus", Font.BOLD, 16));
		habitacionesButton.setForeground(Color.white);

		JButton salirButton = new JButton("Salir",
				new ImageIcon(getClass().getResource(LookAndFeel.SALIR_BUTTON_ICON)));
		salirButton.setHorizontalTextPosition(SwingConstants.RIGHT);
		salirButton.setBackground(LookAndFeel.CUSTOM_BLUE);
		salirButton.setBorderPainted(false);
		salirButton.setOpaque(false);
		salirButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		salirButton.setFont(new Font("Bauhaus", Font.BOLD, 16));
		salirButton.setForeground(Color.white);

		// muestra como seleccionado el boton de acuerdo a la vista que se este
		// mostrando
		switch (selection) {
		case 1:
			homeButton.setOpaque(true);
			editarButton.setOpaque(false);
			habitacionesButton.setOpaque(false);
			break;
		case 2:
			homeButton.setOpaque(false);
			editarButton.setOpaque(true);
			habitacionesButton.setOpaque(false);
			break;
		case 3:
			homeButton.setOpaque(false);
			editarButton.setOpaque(false);
			habitacionesButton.setOpaque(true);
			break;
		}

		menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		menuPanel.add(homeButton);
		menuPanel.add(editarButton);
		menuPanel.add(habitacionesButton);
		menuPanel.add(Box.createRigidArea(new Dimension(0, 500)));
		menuPanel.add(salirButton);

		menuPanel.setBackground(LookAndFeel.CUSTOM_GREEN);
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		menuPanel.setVisible(true);

		homeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				scatView.getContentPane().removeAll();
				scatView.getContentPane().add(returnHousingHomeView());
				scatView.validate();
				scatView.repaint();
			}
		});

		editarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				scatView.getContentPane().removeAll();
				scatView.getContentPane().add(returnOfficeAddHousingView());
				scatView.validate();
				scatView.repaint();
			}
		});

		habitacionesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				scatView.getContentPane().removeAll();
				scatView.getContentPane().add(returnRoomsView(hUser.getHousing().getRooms()));
				scatView.validate();
				scatView.repaint();
			}
		});

		salirButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				scatView.getContentPane().removeAll();
				scatView.getContentPane().add(returnWelcomeView());
				scatView.validate();
				scatView.repaint();
			}
		});

		return menuPanel;
	}

	// ------------------------------HOUSING HOME
	// VIEW------------------------------//
	private JPanel returnHousingHomeView() {
		JPanel housingHomePanel = new JPanel();

		// ----------RIGHT PANEL----------//
		JPanel housingHomeRightPanel = new JPanel();
		JLabel welcomeLabel = new JLabel("¡Bienvenido!");
		welcomeLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 35));
		welcomeLabel.setForeground(LookAndFeel.CUSTOM_BLUE);

		JTextArea welcomeText = new JTextArea(Message.WELCOME_MESSAGE_2);
		welcomeText.setLineWrap(true);
		welcomeText.setWrapStyleWord(true);
		welcomeText.setOpaque(false);
		welcomeText.setEditable(false);
		welcomeText.setFont(new Font("Calibri", Font.BOLD, 18));
		welcomeText.setForeground(LookAndFeel.CUSTOM_BLUE);

		housingHomeRightPanel.add(welcomeLabel);
		housingHomeRightPanel.add(welcomeText);

		housingHomeRightPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		housingHomeRightPanel.setLayout(new GridLayout(0, 1, 0, 0));
		housingHomeRightPanel.setPreferredSize(new Dimension(500, 600));
		housingHomeRightPanel.setVisible(true);

		housingHomePanel.setBackground(LookAndFeel.CUSTOM_GREY);
		BorderLayout mainLayout = new BorderLayout();
		housingHomePanel.setLayout(mainLayout);
		housingHomePanel.add(returnHousingMenuPanel(1), BorderLayout.WEST);
		housingHomePanel.add(returnScatLogoPanel(), BorderLayout.CENTER);
		housingHomePanel.add(housingHomeRightPanel, BorderLayout.EAST);
		housingHomePanel.setVisible(true);

		return housingHomePanel;

	}

	// ------------------------------HOUSING ROOMS
	// VIEW------------------------------//
	private JPanel returnRoomsView(List<Room> list) {
		JPanel housingRoomsPanel = new JPanel();

		// ----------CENTER PANEL----------//
		JPanel housingRoomsCenterPanel = new JPanel();

		JList<HostService> hsList = new JList(list.toArray());
		hsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		hsList.setCellRenderer(new RoomListRenderer());

		JScrollPane listScroller = new JScrollPane(hsList);
		listScroller.setPreferredSize(new Dimension(500, 650));
		listScroller.setVerticalScrollBarPolicy(listScroller.VERTICAL_SCROLLBAR_AS_NEEDED);

		housingRoomsCenterPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		housingRoomsCenterPanel.add(listScroller);
		housingRoomsCenterPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		housingRoomsCenterPanel.setVisible(true);

		// ----------RIGHT top PANEL----------//
		JPanel housingRoomsRightTopPanel = new JPanel();

		JButton agregarHabitacion = new JButton("Agregar Habitacion");
		agregarHabitacion.setBackground(LookAndFeel.CUSTOM_BLUE);
		agregarHabitacion.setForeground(Color.white);
		agregarHabitacion.setMaximumSize(new Dimension(400, 38));
		agregarHabitacion.setFont(new Font("Bauhaus", Font.BOLD, 14));
		agregarHabitacion.setAlignmentX(agregarHabitacion.LEFT_ALIGNMENT);

		housingRoomsRightTopPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		housingRoomsRightTopPanel.add(agregarHabitacion);
		housingRoomsRightTopPanel.setBackground(LookAndFeel.CUSTOM_GREY);

		BorderLayout mainLayout = new BorderLayout();
		housingRoomsPanel.setLayout(mainLayout);
		housingRoomsPanel.add(returnHousingMenuPanel(3), BorderLayout.WEST);
		housingRoomsPanel.add(housingRoomsCenterPanel, BorderLayout.CENTER);
		housingRoomsPanel.add(housingRoomsRightTopPanel, BorderLayout.EAST);
		housingRoomsPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		housingRoomsPanel.setVisible(true);

		hsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				scatView.getContentPane().removeAll();
				scatView.getContentPane().add(returnHousingAddRoomView((Room) hsList.getSelectedValue()));
				scatView.validate();
				scatView.repaint();
			}
		});

		// va a la vista de agregar/modificar habitacion
		agregarHabitacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Room r = null;
				scatView.getContentPane().removeAll();
				scatView.getContentPane().add(returnHousingAddRoomView(r));
				scatView.validate();
				scatView.repaint();
			}
		});

		return housingRoomsPanel;
	}

	// ------------------------------HOUSING ADD ROOM
	// VIEW------------------------------//
	public JPanel returnHousingAddRoomView(Room currentRoom) {

		// ----------LEFT PANEL----------//
		JPanel housingAddRoomLeftPanel = new JPanel();
		JLabel agregarRoom = new JLabel("Agregar Habitación");
		agregarRoom.setAlignmentX(agregarRoom.LEFT_ALIGNMENT);
		agregarRoom.setMaximumSize(new Dimension(400, 25));
		agregarRoom.setFont(new Font("Calibri", Font.BOLD, 18));

		PlaceHolder nameRoom = new PlaceHolder();
		nameRoom.setPlaceholder("Nombre de la habitacion");
		nameRoom.setAlignmentX(nameRoom.LEFT_ALIGNMENT);
		nameRoom.setMaximumSize(new Dimension(400, 25));
		nameRoom.setBackground(Color.white);

		PlaceHolderTextArea descripcion = new PlaceHolderTextArea();
		descripcion.setPlaceholder("Descripcion");
		descripcion.setAlignmentX(descripcion.LEFT_ALIGNMENT);
		descripcion.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		descripcion.setMaximumSize(new Dimension(400, 100));
		descripcion.setBackground(Color.white);

		JComboBox setDisponible = new JComboBox();
		setDisponible.addItem("Disponibilidad");
		setDisponible.addItem("Disponible");
		setDisponible.addItem("No disponible");
		setDisponible.setAlignmentX(setDisponible.LEFT_ALIGNMENT);
		setDisponible.setMaximumSize(new Dimension(400, 25));
		setDisponible.setBackground(Color.white);

		housingAddRoomLeftPanel.add(Box.createRigidArea(new Dimension(0, 70)));
		housingAddRoomLeftPanel.add(agregarRoom);
		housingAddRoomLeftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		housingAddRoomLeftPanel.add(nameRoom);
		housingAddRoomLeftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		housingAddRoomLeftPanel.add(descripcion);
		housingAddRoomLeftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		housingAddRoomLeftPanel.add(setDisponible);

		housingAddRoomLeftPanel.setLayout(new BoxLayout(housingAddRoomLeftPanel, BoxLayout.Y_AXIS));
		housingAddRoomLeftPanel.setBackground(LookAndFeel.CUSTOM_GREY);

		// ----------RIGHT top PANEL----------//
		JPanel housingAddRoomRightTopPanel = new JPanel();

		ArrayList<String> tiposRoom = new ArrayList();
		tiposRoom.add("Tipo de habitación");
		tiposRoom.addAll(Arrays.asList(Room.ROOM_TYPES));

		JComboBox tipoRoom = new JComboBox(tiposRoom.toArray());
		tipoRoom.setMaximumSize(new Dimension(400, 25));
		tipoRoom.setBackground(Color.white);

		PlaceHolder capacidad = new PlaceHolder();
		capacidad.setPlaceholder("Capacidad");
		capacidad.setMaximumSize(new Dimension(400, 25));
		capacidad.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		JLabel servicesRoom = new JLabel("Servicios de la habitacion");
		servicesRoom.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		JCheckBox roomCommodity1 = new JCheckBox("Jacuzzi");
		roomCommodity1.setAlignmentX(JCheckBox.LEFT_ALIGNMENT);
		roomCommodity1.setBackground(LookAndFeel.CUSTOM_GREY);
		JCheckBox roomCommodity2 = new JCheckBox("TV");
		roomCommodity2.setAlignmentX(JCheckBox.LEFT_ALIGNMENT);
		roomCommodity2.setBackground(LookAndFeel.CUSTOM_GREY);

		housingAddRoomRightTopPanel.add(Box.createRigidArea(new Dimension(0, 110)));
		housingAddRoomRightTopPanel.add(tipoRoom);
		housingAddRoomRightTopPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		housingAddRoomRightTopPanel.add(capacidad);
		housingAddRoomRightTopPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		housingAddRoomRightTopPanel.add(servicesRoom);
		housingAddRoomRightTopPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		housingAddRoomRightTopPanel.add(roomCommodity1);
		housingAddRoomRightTopPanel.add(roomCommodity2);
		housingAddRoomRightTopPanel.add(Box.createRigidArea(new Dimension(0, 100)));

		housingAddRoomRightTopPanel.setLayout(new BoxLayout(housingAddRoomRightTopPanel, BoxLayout.Y_AXIS));
		housingAddRoomRightTopPanel.setBackground(LookAndFeel.CUSTOM_GREY);

		// ----------RIGHT bottom PANEL----------//
		JPanel housingAddRoomRightBottomPanel = new JPanel();

		JButton cancelButton = new JButton("CANCELAR");
		cancelButton.setBackground(LookAndFeel.CUSTOM_BLUE);

		JButton saveButton = new JButton("GUARDAR");
		saveButton.setBackground(LookAndFeel.CUSTOM_BLUE);

		JButton deleteRoomButton = new JButton("BORRAR HABITACIÓN");
		deleteRoomButton.setBackground(LookAndFeel.CUSTOM_BLUE);

		housingAddRoomRightBottomPanel.add(Box.createRigidArea(new Dimension(0, 600)));
		housingAddRoomRightBottomPanel.add(deleteRoomButton);
		housingAddRoomRightBottomPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		housingAddRoomRightBottomPanel.add(cancelButton);
		housingAddRoomRightBottomPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		housingAddRoomRightBottomPanel.add(saveButton);
		housingAddRoomRightBottomPanel.setLayout(new FlowLayout());
		housingAddRoomRightBottomPanel.setBackground(LookAndFeel.CUSTOM_GREY);

		// ----------RIGHT PANEL----------//
		JPanel housingAddRoomRightPanel = new JPanel();
		housingAddRoomRightPanel.add(housingAddRoomRightTopPanel);
		housingAddRoomRightPanel.add(housingAddRoomRightBottomPanel);

		housingAddRoomRightPanel.setLayout(new GridLayout(0, 1));
		housingAddRoomRightPanel.setPreferredSize(new Dimension(600, 600));
		housingAddRoomRightPanel.setBackground(LookAndFeel.CUSTOM_GREY);

		JPanel housingAddRoomPanel = new JPanel();

		housingAddRoomPanel.setBackground(LookAndFeel.CUSTOM_GREY);
		BorderLayout mainLayout = new BorderLayout();
		housingAddRoomPanel.setLayout(mainLayout);

		housingAddRoomPanel.add(returnHousingMenuPanel(3), BorderLayout.WEST);
		housingAddRoomPanel.add(housingAddRoomLeftPanel, BorderLayout.CENTER);
		housingAddRoomPanel.add(housingAddRoomRightPanel, BorderLayout.EAST);
		mainLayout.setVgap(100);
		mainLayout.setHgap(100);
		housingAddRoomPanel.setVisible(true);

		capacidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (!Character.isDigit(caracter)) {
					e.consume();
				}
			}
		});

		// si entro a editar una habitacion
		if (currentRoom != null) {
			nameRoom.setText(currentRoom.getName());
			descripcion.setText(currentRoom.getDescription());

			if (currentRoom.isAvailable()) {
				setDisponible.setSelectedIndex(1);
			} else {
				if (!currentRoom.isAvailable()) {
					setDisponible.setSelectedIndex(2);
				}
			}

			if (currentRoom.getHostServiceType().equals("Simple")) {
				tipoRoom.setSelectedIndex(1);
			} else {
				if (currentRoom.getHostServiceType().equals("Doble")) {
					tipoRoom.setSelectedIndex(2);
				} else {
					if (currentRoom.getHostServiceType().equals("Triple")) {
						tipoRoom.setSelectedIndex(3);
					} else {
						if (currentRoom.getHostServiceType().equals("Cuadruple")) {
							tipoRoom.setSelectedIndex(4);
						}
					}
				}

				capacidad.setText(String.valueOf(currentRoom.getCapacity()));

				if (currentRoom.getCommodities().contains("Jacuzzi")) {
					roomCommodity1.setSelected(true);
				}

				if (currentRoom.getCommodities().contains("TV")) {
					roomCommodity2.setSelected(true);
				}

			}
		} else
			deleteRoomButton.setVisible(false);

		deleteRoomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int selection = JOptionPane.showConfirmDialog(scatView, Message.DELETE_ROOM_MESSAGE, "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				// si presiona aceptar
				if (selection == 0) {
					hUser.getHousing().deleteRoom(currentRoom);
					new HousingServices().persistHousing(hUser.getHousing());
					JOptionPane.showMessageDialog(scatView, Message.SUCCESS_MESSAGE_5);
					scatView.getContentPane().removeAll();
					scatView.getContentPane().add(returnRoomsView(hUser.getHousing().getRooms()));
					scatView.validate();
					scatView.repaint();
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int selection = JOptionPane.showConfirmDialog(scatView, Message.CANCELBUTTON_MESSAGE_5, "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				// si presiona aceptar
				if (selection == 0) {
					scatView.getContentPane().removeAll();
					scatView.getContentPane().add(returnRoomsView(hUser.getHousing().getRooms()));
					scatView.validate();
					scatView.repaint();
				}
			}
		});

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// comprueba que no haya ningun campo vacio
				boolean empty = (nameRoom.getText().equals("") || descripcion.getText().equals("")
						|| setDisponible.getSelectedIndex() == 0 || tipoRoom.getSelectedIndex() == 0
						|| capacidad.getText().equals(""));
				if (empty) {
					JOptionPane.showMessageDialog(scatView, Message.CREARBUTTON_MESSAGE_1);
				}

				boolean disp = false;
				if (setDisponible.getSelectedIndex() == 1) {
					disp = true;
				}

				if (currentRoom == null) {
					Room r = new Room(disp, nameRoom.getText(), descripcion.getText(),
							Integer.parseInt(capacidad.getText()), String.valueOf(tipoRoom.getSelectedItem()),
							hUser.getHousing());

					if (roomCommodity1.isSelected()) {
						r.addCommodity(roomCommodity1.getText());
					}

					if (roomCommodity2.isSelected()) {
						r.addCommodity(roomCommodity2.getText());
					}
					hUser.getHousing().addRoom(r);
				} else {
					// si la habitacion se esta modificando...
					currentRoom.setAvailable(disp);
					currentRoom.setName(nameRoom.getText());
					currentRoom.setDescription(descripcion.getText());
					currentRoom.setCapacity(Integer.parseInt(capacidad.getText()));
					currentRoom.setHostServiceType(String.valueOf(tipoRoom.getSelectedItem()));

					currentRoom.getCommodities().clear();

					if (roomCommodity1.isSelected()) {
						currentRoom.addCommodity(roomCommodity1.getText());
					}

					if (roomCommodity2.isSelected()) {
						currentRoom.addCommodity(roomCommodity2.getText());
					}
				}
				if (!empty) {
					new HousingServices().persistHousing(hUser.getHousing());
					JOptionPane.showMessageDialog(scatView, Message.SUCCESS_MESSAGE_4);
				}

				nameRoom.setText("");
				descripcion.setText("");
				setDisponible.setSelectedIndex(0);
				tipoRoom.setSelectedIndex(0);
				capacidad.setText("");
				roomCommodity1.setSelected(false);
				roomCommodity2.setSelected(false);

			}
		});

		return housingAddRoomPanel;

	}

}
