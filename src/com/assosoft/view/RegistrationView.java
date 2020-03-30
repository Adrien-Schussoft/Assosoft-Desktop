package com.assosoft.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.assosoft.dao.AssoDAO;
import com.assosoft.dao.MainDAO;
import com.assosoft.dao.UserDAO;
import com.assosoft.model.Asso;
import com.assosoft.model.User;

import java.awt.Window.Type;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;

public class RegistrationView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtEmail;
	private JPasswordField txtPass;
	private JPasswordField txtPassConfirm;
	private JTextField txtVille;
	private JTextField txtRue;
	private JTextField txtCodePostal;
	private User user;
	private Asso assoc;
	private Button btnEnvoyer;

	private int idAsso;
	private String assoSelected;

	/*
	 * Create the frame.
	 */
	public RegistrationView() {
		setType(Type.UTILITY);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 764);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 265, 714);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RegistrationView.class.getResource("/img/inscrip.jpg")));
		label.setBounds(10, 105, 245, 218);
		label.setVerticalAlignment(SwingConstants.TOP);
		panel.add(label);

		JLabel lblJee = new JLabel("ASSOSOFT");
		lblJee.setHorizontalAlignment(SwingConstants.CENTER);
		lblJee.setForeground(Color.LIGHT_GRAY);
		lblJee.setFont(new Font("Arial Black", Font.BOLD, 34));
		lblJee.setBounds(25, 668, 215, 35);
		panel.add(lblJee);

		JLabel lblNewLabel_1 = new JLabel("Inscription");
		lblNewLabel_1.setBounds(10, 11, 245, 55);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Nirmala UI", Font.PLAIN, 50));

		ArrayList<Asso> assos = AssoDAO.touteslesassos(true);

		String[] tableau = new String[assos.size()];
		int i = 0;
		for (Asso asso : assos) {
//tableau[i] = asso.getNum_Asso()+","+asso.getNom();
			tableau[i] = asso.getNom();

			i++;
//			System.out.println(asso.getNum_Asso()+","+asso.getNom());

		}

//		JComboBox comboBox = new JComboBox(assos.toArray());
		JComboBox comboBox = new JComboBox(tableau);
		comboBox.setBounds(285, 28, 324, 45);
		contentPane.add(comboBox);
		comboBox.setForeground(new Color(128, 0, 0));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txtNom = new JTextField();
		txtNom.setBounds(305, 116, 285, 31);
		contentPane.add(txtNom);
		txtNom.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.RED);
		separator.setBounds(305, 147, 285, 2);
		contentPane.add(separator);

		JLabel lblLogin = new JLabel("NOM");
		lblLogin.setBounds(295, 100, 48, 14);
		contentPane.add(lblLogin);

		JLabel lblPassword = new JLabel("PRENOM");
		lblPassword.setBounds(295, 163, 101, 14);
		contentPane.add(lblPassword);

		txtPrenom = new JTextField();
		txtPrenom.setBounds(307, 179, 283, 31);
		contentPane.add(txtPrenom);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.RED);
		separator_1.setBounds(305, 210, 285, 5);
		contentPane.add(separator_1);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(305, 247, 285, 31);
		contentPane.add(txtEmail);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.RED);
		separator_2.setBounds(305, 278, 285, 2);
		contentPane.add(separator_2);

		JLabel lblDateDeNaissance = new JLabel("EMAIL");
		lblDateDeNaissance.setBounds(295, 231, 141, 14);
		contentPane.add(lblDateDeNaissance);

		JLabel lblEmail = new JLabel("Ville");
		lblEmail.setBounds(299, 465, 101, 14);
		contentPane.add(lblEmail);

		txtVille = new JTextField();
		txtVille.setBounds(311, 486, 283, 31);
		contentPane.add(txtVille);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.RED);
		separator_3.setBounds(309, 517, 285, 5);
		contentPane.add(separator_3);

		JLabel lblMotDePasse = new JLabel(" MOT DE PASSE (10 caract\u00E8res maximum)");
		lblMotDePasse.setBounds(291, 301, 299, 14);
		contentPane.add(lblMotDePasse);

		txtPass = new JPasswordField();
		txtPass.setBounds(307, 321, 283, 31);
		contentPane.add(txtPass);

		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.RED);
		separator_4.setBounds(309, 580, 285, 5);
		contentPane.add(separator_4);

		txtRue = new JTextField();
		txtRue.setBounds(311, 549, 283, 31);
		contentPane.add(txtRue);

		JLabel lblNRue = new JLabel("Rue");
		lblNRue.setBounds(299, 528, 101, 14);
		contentPane.add(lblNRue);

		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.RED);
		separator_5.setBounds(309, 648, 285, 5);
		contentPane.add(separator_5);

		txtCodePostal = new JTextField();
		txtCodePostal.setBounds(311, 617, 283, 31);
		contentPane.add(txtCodePostal);

		JLabel lblCodePostal = new JLabel("Code Postal");
		lblCodePostal.setBounds(299, 596, 101, 14);
		contentPane.add(lblCodePostal);

		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.RED);
		separator_6.setBounds(305, 352, 285, 2);
		contentPane.add(separator_6);

		JLabel lblConfirmationmotDePasse = new JLabel(" CONFIRMATION MOT DE PASSE");
		lblConfirmationmotDePasse.setBounds(291, 370, 299, 14);
		contentPane.add(lblConfirmationmotDePasse);

		txtPassConfirm = new JPasswordField();
		txtPassConfirm.setBounds(307, 390, 283, 31);
		contentPane.add(txtPassConfirm);

		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(Color.RED);
		separator_7.setBounds(305, 421, 285, 2);
		contentPane.add(separator_7);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 0, 128), null, null, null));
		panel_1.setBounds(283, 84, 326, 356);
		contentPane.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 0, 128), null, null, null));
		panel_2.setBounds(283, 451, 326, 212);
		contentPane.add(panel_2);

		JLabel lblNewLabel = new JLabel("CHOIX DE L'ASSOCIATION");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(275, 3, 207, 25);
		contentPane.add(lblNewLabel);

		// ---------------------- BOUTON ENVOYER
		// ------------------------------------------------------------------------

		// ---------------------- CLICK BOUTON ENVOYER
		// ------------------------------------------------------------------------
		btnEnvoyer = new Button("Envoyer");
		btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean valid = false;
				boolean valid_pass = false;

				// Test si tous les champs sont bien remplis
				valid = MainDAO.validateForm(txtNom, txtPrenom, txtEmail, txtPass, txtPassConfirm, txtVille, txtRue,
						txtCodePostal);
				// Test d'égalité mot de passe et confirmation
				valid_pass = txtPass.getText().equals(txtPassConfirm.getText());

				if (valid) {

					if (valid_pass) {
						txtPassConfirm.setBackground(Color.white);

						// test si email présent en base de donnée

						if (UserDAO.emailExist(txtEmail.getText())) {
							JOptionPane.showMessageDialog(contentPane, "L'email est déjà dans la base.", "Erreur",
									JOptionPane.INFORMATION_MESSAGE);
						} else { // Si l'email n'est pas dans la base, validation inscription
							System.out.println("email pas dans la base");

							// Initialisation du user
							user = new User(txtNom.getText(), txtPrenom.getText(), txtRue.getText(), txtVille.getText(),
									Integer.parseInt(txtCodePostal.getText()), txtEmail.getText(), LocalDate.now(),
									txtPass.getText(), false, false, false);
							UserDAO.addUser(user);
							user.setNum_Personne(UserDAO.readUserMail(user));
							assoSelected = comboBox.getSelectedItem().toString();
							idAsso = AssoDAO.readAssocNom(assoSelected);
							assoc = new Asso(idAsso, assoSelected, "", "", 0, "", false);
							System.out.println(assoc);
							System.out.println(user);

							if (assoc.getNom().equals("")) {
								JOptionPane.showMessageDialog(contentPane, "Association introuvable", "Erreur",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								AssoDAO.addUserAssoc(user, assoc);
//							System.out.println("Ajout user "+user.getNum_Personne()+" avec Asso "+assoc.getNum_Asso());
							}
						}
					} else {
						JOptionPane.showMessageDialog(contentPane, "Les mots de passe ne sont pas identiques. ",
								"Erreur", JOptionPane.INFORMATION_MESSAGE);
						txtPassConfirm.setBackground(Color.red);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Erreur, veuillez renseigner tous les champs.");
				}
			}

		});

		// ---------------------- FIN CLICK BOUTON ENVOYER
		// ------------------------------------------------------------------------

		btnEnvoyer.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnEnvoyer.setForeground(Color.WHITE);
		btnEnvoyer.setBackground(new Color(241, 47, 83));
		btnEnvoyer.setBounds(281, 669, 328, 45);
		contentPane.add(btnEnvoyer);

		// ---------------------- FIN BOUTON ENVOYER
		// ------------------------------------------------------------------------

	}
}
