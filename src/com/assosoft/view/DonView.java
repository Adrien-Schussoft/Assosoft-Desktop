package com.assosoft.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.assosoft.dao.AssoDAO;
import com.assosoft.dao.DonDAO;
import com.assosoft.dao.DonateurDAO;
import com.assosoft.dao.UserDAO;
import com.assosoft.model.Asso;
import com.assosoft.model.Don;
import com.assosoft.model.Donateur;
import com.assosoft.model.User;

public class DonView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtMail;
	private JTextField txtVille;
	private JTextField txtRue;
	private JTextField txtCodePostal;

	private Button btnDon;
	private JTextField txtDonsEuro;
	private JTextField txtDonsMateriel;

	private Donateur donateur;
	private Don don;

	private Asso assoc;
	
	private String assoSelected;

	String[] tableau;

	/*
	 * Create the frame.
	 */
	public DonView() {
		setType(Type.UTILITY);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 878, 695);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 482, 642);
		contentPane.add(panel);
		panel.setLayout(null);

		ArrayList<Asso> assos = AssoDAO.touteslesassos(true);

		tableau = new String[assos.size()];
		int i = 0;
		for (Asso asso : assos) {
			tableau[i] = asso.getNom();
			i++;

//			System.out.println(asso.getNum_Asso()+","+asso.getNom());
		}

		JComboBox<String> comboBox = new JComboBox<String>(tableau);
		comboBox.setBackground(new Color(220, 20, 60));
		comboBox.setBounds(22, 11, 423, 55);
		panel.add(comboBox);
		comboBox.setForeground(Color.WHITE);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 24));

		txtDonsEuro = new JTextField();
		txtDonsEuro.setBackground(Color.WHITE);

//***********************************************************************************		
		txtDonsEuro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char caracterOk[] = { '1', '2', '3', '4', '5', '6', '7', '8', '9', ',', '.' };

				for (int i = 0; i < caracterOk.length; i++) {
					if (e.getKeyChar() == '1') {
						System.out.println("ooo");
					}

				}
			}
		});

		JLabel lblDonsEuro = new JLabel("Faire un dons de");
		lblDonsEuro.setForeground(new Color(0, 139, 139));
		lblDonsEuro.setBounds(22, 65, 423, 55);
		panel.add(lblDonsEuro);
		lblDonsEuro.setHorizontalAlignment(SwingConstants.LEFT);
		lblDonsEuro.setFont(new Font("Nirmala UI", Font.BOLD, 50));
		// ***********************************************************************
		txtDonsEuro.setFont(new Font("Tahoma", Font.BOLD, 50));
		txtDonsEuro.setBounds(22, 131, 150, 59);
		panel.add(txtDonsEuro);

		JSeparator separator_DonsEuro = new JSeparator();
		separator_DonsEuro.setBounds(22, 190, 151, 2);
		panel.add(separator_DonsEuro);
		separator_DonsEuro.setForeground(Color.RED);

		JLabel lblEuro = new JLabel("\u20AC");
		lblEuro.setForeground(new Color(0, 128, 128));
		lblEuro.setBounds(182, 131, 50, 59);
		panel.add(lblEuro);
		lblEuro.setFont(new Font("Tahoma", Font.BOLD, 61));

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(DonView.class.getResource("/img/donation.jpg")));
		label.setBounds(22, 134, 435, 370);
		label.setVerticalAlignment(SwingConstants.TOP);
		panel.add(label);

		JLabel lblDonsMateriel = new JLabel("Faire un dons Mat\u00E9riel");
		lblDonsMateriel.setHorizontalAlignment(SwingConstants.CENTER);
		lblDonsMateriel.setForeground(new Color(0, 139, 139));
		lblDonsMateriel.setFont(new Font("Nirmala UI", Font.BOLD, 40));
		lblDonsMateriel.setBounds(20, 499, 446, 55);
		panel.add(lblDonsMateriel);

		txtDonsMateriel = new JTextField();
		txtDonsMateriel.setFont(new Font("Tahoma", Font.BOLD, 30));
		txtDonsMateriel.setBounds(29, 565, 416, 59);
		panel.add(txtDonsMateriel);

		JSeparator separator__DonsMatriel = new JSeparator();
		separator__DonsMatriel.setBounds(25, 624, 420, 2);
		panel.add(separator__DonsMatriel);
		separator__DonsMatriel.setForeground(Color.RED);

		// ---------------------- BOUTON DON
		// ------------------------------------------------------------------------

//---------------------- CLICK BOUTON CONNEXION ------------------------------------------------------------------------

		btnDon = new Button("Donner");

		btnDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean valid = false;
				if (txtNom.getText().equals("") || txtPrenom.getText().equals("") || txtMail.getText().equals("")
						|| txtVille.getText().equals("") || txtRue.getText().equals("")
						|| txtCodePostal.getText().equals(""))

				{
					System.out.println("Champ vide");
					valid = false;
					JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs.");
				} else {
					valid = true;
				}
				if (valid == true) {
					if (UserDAO.emailExist(txtMail.getText())) {
						JOptionPane.showMessageDialog(contentPane, "L'email est déjà dans la base user.", "Erreur",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						if (DonateurDAO.emailExist(txtMail.getText())) {
							JOptionPane.showMessageDialog(contentPane, "L'email est déjà dans la base donateur.",
									"Erreur", JOptionPane.INFORMATION_MESSAGE);
						} else {
//							assoc = new Assoc(0, comboBox.getSelectedItem().toString(), "", "", 0, "", false);
							assoSelected = comboBox.getSelectedItem().toString();

							if (assoc.getNom().equals("")) {
								JOptionPane.showMessageDialog(contentPane, "Association introuvable", "Erreur",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								// Ajout du donateur
								donateur = new Donateur(0, txtNom.getText(), txtPrenom.getText(), txtRue.getText(),
										txtVille.getText(), Integer.parseInt(txtCodePostal.getText()),
										txtMail.getText(), LocalDate.now(), "");
								DonateurDAO.addDonateur(donateur);
								DonateurDAO.readDonateurMail(donateur); // Pour récupérer l'ID du nouveau donateur

								// Ajout du don
								don = new Don(0, "", false, Float.parseFloat(txtDonsEuro.getText()),
										txtDonsMateriel.getText(), LocalDate.now(), donateur.getNum_Personne(), 0);
								int idAsso = AssoDAO.readAssocNom(assoSelected);
								DonDAO.addDon(don, idAsso);

//									DonDAO.readDonDonateur(don); // Pour récupérer l'ID du nouveau don

//									// Ajout du don a l'association
//									assoc = new Assoc(0,comboBox.getSelectedItem().toString(),"","",0,"",false);
//									AssocDAO.readAssocNom(assoc.getNom());
//									AssocDAO.addDonAssoc(don, assoc);
//									System.out.println("Ajout don "+don.getNum_Don()+" avec Asso "+assoc.getNum_Asso());
							}
						}
					}
				}
			}
		});
		// ---------------------- FIN CLICK BOUTON DON
		// ------------------------------------------------------------------------

		btnDon.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnDon.setForeground(Color.WHITE);
		btnDon.setBackground(new Color(241, 47, 83));
		btnDon.setBounds(509, 513, 328, 44);
		contentPane.add(btnDon);

		// ---------------------- FIN BOUTON DON
		// ------------------------------------------------------------------------

		txtNom = new JTextField();
		txtNom.setBounds(531, 43, 285, 31);
		contentPane.add(txtNom);
		txtNom.setColumns(10);

		JSeparator separatorNom = new JSeparator();
		separatorNom.setForeground(Color.RED);
		separatorNom.setBounds(531, 74, 285, 2);
		contentPane.add(separatorNom);

		JLabel lblNom = new JLabel("NOM");
		lblNom.setBounds(521, 27, 48, 14);
		contentPane.add(lblNom);

		JLabel lblPrenom = new JLabel("PRENOM");
		lblPrenom.setBounds(521, 90, 101, 14);
		contentPane.add(lblPrenom);

		txtPrenom = new JTextField();
		txtPrenom.setBounds(533, 106, 283, 31);
		contentPane.add(txtPrenom);

		JSeparator separatorPrenom = new JSeparator();
		separatorPrenom.setForeground(Color.RED);
		separatorPrenom.setBounds(531, 137, 285, 5);
		contentPane.add(separatorPrenom);

		txtMail = new JTextField();
		txtMail.setColumns(10);
		txtMail.setBounds(531, 174, 285, 31);
		contentPane.add(txtMail);

		JSeparator separatorMail = new JSeparator();
		separatorMail.setForeground(Color.RED);
		separatorMail.setBounds(531, 205, 285, 2);
		contentPane.add(separatorMail);

		JLabel lblMail = new JLabel("EMAIL");
		lblMail.setBounds(521, 158, 141, 14);
		contentPane.add(lblMail);

		JLabel lblVille = new JLabel("Ville");
		lblVille.setBounds(525, 276, 101, 14);
		contentPane.add(lblVille);

		txtVille = new JTextField();
		txtVille.setBounds(537, 297, 283, 31);
		contentPane.add(txtVille);

		JSeparator separatorVille = new JSeparator();
		separatorVille.setForeground(Color.RED);
		separatorVille.setBounds(535, 328, 285, 5);
		contentPane.add(separatorVille);

		JSeparator separatorRue = new JSeparator();
		separatorRue.setForeground(Color.RED);
		separatorRue.setBounds(535, 391, 285, 5);
		contentPane.add(separatorRue);

		txtRue = new JTextField();
		txtRue.setBounds(537, 360, 283, 31);
		contentPane.add(txtRue);

		JLabel lblRue = new JLabel("Rue");
		lblRue.setBounds(525, 339, 101, 14);
		contentPane.add(lblRue);

		JSeparator separatorCodePostal = new JSeparator();
		separatorCodePostal.setForeground(Color.RED);
		separatorCodePostal.setBounds(535, 459, 285, 5);
		contentPane.add(separatorCodePostal);

		txtCodePostal = new JTextField();
		txtCodePostal.setBounds(537, 428, 283, 31);
		contentPane.add(txtCodePostal);

		JLabel lblCodePostal = new JLabel("Code Postal");
		lblCodePostal.setBounds(525, 407, 101, 14);
		contentPane.add(lblCodePostal);

		JPanel panel1 = new JPanel();
		panel1.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 0, 128), null, null, null));
		panel1.setBounds(509, 11, 326, 229);
		contentPane.add(panel1);

		JPanel panel2 = new JPanel();
		panel2.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 0, 128), null, null, null));
		panel2.setBounds(509, 262, 326, 212);
		contentPane.add(panel2);

		JLabel lblJee = new JLabel("ASSOSOFT");
		lblJee.setBounds(554, 584, 215, 35);
		contentPane.add(lblJee);
		lblJee.setHorizontalAlignment(SwingConstants.CENTER);
		lblJee.setForeground(Color.LIGHT_GRAY);
		lblJee.setFont(new Font("Arial Black", Font.BOLD, 34));
	}
}
