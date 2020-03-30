package com.assosoft.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.assosoft.dao.MainDAO;
import com.assosoft.dao.UserDAO;
import com.assosoft.model.User;

import java.awt.Window.Type;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ConnectionView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtPass;
	private User user;
	private Button btnSignIn;

	/*
	 * Create the frame.
	 */
	public ConnectionView() {
		setType(Type.UTILITY);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 615, 392);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 265, 353);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ConnectionView.class.getResource("/img/connexion.png")));
		label.setBounds(0, 11, 265, 264);
		label.setVerticalAlignment(SwingConstants.TOP);
		panel.add(label);

		JLabel lblJee = new JLabel("ASSOSOFT");
		lblJee.setBackground(Color.WHITE);
		lblJee.setHorizontalAlignment(SwingConstants.CENTER);
		lblJee.setForeground(new Color(192, 192, 192));
		lblJee.setFont(new Font("Arial Black", Font.BOLD, 34));
		lblJee.setBounds(20, 286, 215, 35);
		panel.add(lblJee);

		btnSignIn = new Button("SignIn");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection();
			}
		});
		btnSignIn.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnSignIn.setForeground(Color.WHITE);
		btnSignIn.setBackground(new Color(241, 47, 83));
		btnSignIn.setBounds(287, 285, 283, 45);
		contentPane.add(btnSignIn);

		txtLogin = new JTextField();
		txtLogin.setBounds(285, 122, 285, 31);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(241, 47, 83));
		separator.setBounds(285, 153, 285, 2);
		contentPane.add(separator);

		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setBounds(275, 101, 48, 14);
		contentPane.add(lblLogin);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(275, 185, 101, 14);
		contentPane.add(lblPassword);

		txtPass = new JPasswordField();
		txtPass.setBounds(287, 206, 283, 31);
		contentPane.add(txtPass);
		// event keypress Enter for connection
		txtPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					connection();
				}
			}
		});

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(241, 47, 83));
		separator_1.setBounds(285, 237, 285, 5);
		contentPane.add(separator_1);

		JLabel lblNewLabel_1 = new JLabel("Connexion");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Nirmala UI", Font.PLAIN, 50));
		lblNewLabel_1.setBounds(289, 0, 270, 55);
		contentPane.add(lblNewLabel_1);
	}

	public void connection() {
		// Initialisation du user avec juste son mail
		user = new User(0, "", "", "", "", 0, txtLogin.getText(), LocalDate.of(1980, 1, 1), "", false, false, false);
		// Recherche du User avec son mail
		UserDAO.readUserMail(user);
		// Vérification du Mail du user
		if (txtLogin.getText().equals(user.getMail()) && user.getPassword().length() > 0) {
			// Vérification du mot de passe
			if (txtPass.getText().equals(user.getPassword())) {
				// Si il est root on affiche la fenêtre root sinon celle pour les users
				if (user.isRoot()) {
					new RootMainView();
				} else {
					if (user.isActif()) {
						if (user.isContact())
							new MainContactView(user);
						else
							new MainUserView(user);
					} else {
						JOptionPane.showMessageDialog(null, "Utilisateur Inactif !");
					}
				}
				// System.exit(0);
			} else {
				// JFrame parent = new JFrame();
				JOptionPane.showMessageDialog(null, "Mot de passe incorrect !");
			}
		} else {
			// JFrame parent = new JFrame();
			JOptionPane.showMessageDialog(null, "Login user ou mot de passe incorrect !");
		}
	}
}
