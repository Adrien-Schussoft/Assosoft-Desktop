package com.assosoft.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.assosoft.dao.UserDAO;
import com.assosoft.model.User;



public class MainView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainView() {
		setType(Type.UTILITY);
		//JPanel contentPane = null;
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);		
		setBounds(0, 0, 451, 281);

		
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 434, 47);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(""); 
		lblNewLabel.setBounds(3, 1, 104, 32);
		lblNewLabel.setIcon(new ImageIcon(MainView.class.getResource("/img/logo-Assosoft.png")));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_5 = new JLabel("Gestion des associations");
		lblNewLabel_5.setForeground(Color.GREEN);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_5.setBounds(124, 8, 310, 25);
		panel.add(lblNewLabel_5);

		
//-----------------------------------------------------------------------------------------------------------------		
//---------------------- CLICK BOUTON INSCRIPTION ------------------------------------------------------------------------	
//-----------------------------------------------------------------------------------------------------------------		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrationView fregistr  = new RegistrationView();
				
				fregistr.setLocationRelativeTo(null);
				fregistr.setVisible(true);
				
				
			}
		});
//---------------------- FIN CLICK BOUTON INSCRIPTION ------------------------------------------------------------------------	
		
		
		
		lblNewLabel_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setIcon(new ImageIcon(MainView.class.getResource("/img/inscrip.png")));
		lblNewLabel_1.setBounds(330, 87, 64, 108);
		getContentPane().add(lblNewLabel_1);
		

//-----------------------------------------------------------------------------------------------------------------
//---------------------- CLICK BOUTON CONNEXION ------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
// Fred test fonction
// test vue assoc				
//				 AssocViewRoot fconn = new AssocViewRoot();
// test vue user				
//				 UserViewRoot fconn = new UserViewRoot();

				
				ConnectionView fconn  = new ConnectionView();
				fconn.setLocationRelativeTo(null);
				fconn.setVisible(true);								
		
				 
				// Fred test fonction
				LocalDate madate = LocalDate.of(2006,7,22);
				
				// Test ajout d'un user
				// User utilisateur = new User(0,"-Nom","-Pren","Rue","Ville",76,"@",madate,"pwd",true,false,"Contact");
				// UserDAO.addUser(utilisateur);
				
				// Test lecture du numero 13
				// User utilisateur = new User(13,"","","","",0,"",null,"",false,false,"");
				// UserDAO.readUser(utilisateur);
				// System.out.println(utilisateur.getDate_Inscription());
				
				// Test ajout d'un donateur
				//Donateur donateur = new Donateur(0,"Nom","Pren","Rue","Ville",76,"@",madate,"pwd");
				//DonateurDAO.addDonateur(donateur);
				
				//ArrayList<User> users = UserDAO.touslesusers();
				
				//int tailleArrayList=  UserDAO.touslesusers().size();
				//System.out.println("Nb enreg dans tab "+tailleArrayList);
				
				
				
			}
		});
//---------------------- FIN CLICK BOUTON CONNEXION ------------------------------------------------------------------------		

		lblNewLabel_2.setIcon(new ImageIcon(MainView.class.getResource("/img/log.png")));
		lblNewLabel_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblNewLabel_2.setBounds(37, 87, 106, 108);
		getContentPane().add(lblNewLabel_2);
		
		
//-----------------------------------------------------------------------------------------------------------------	
//---------------------- CLICK BOUTON DONS ------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------	
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DonView fregistr  = new DonView();				
				fregistr.setLocationRelativeTo(null);
				fregistr.setVisible(true);
				
			}
		});
		
//-----------------------------------------------------------------------------------------------------------------
//---------------------- FIN CLICK BOUTON DONS ------------------------------------------------------------------------	
//-----------------------------------------------------------------------------------------------------------------
		
		lblNewLabel_3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblNewLabel_3.setIcon(new ImageIcon(MainView.class.getResource("/img/dons.png")));
		lblNewLabel_3.setBounds(180, 87, 113, 108);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Connexion");
		lblNewLabel_4.setForeground(new Color(128, 0, 128));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(23, 206, 113, 20);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblDons = new JLabel("Dons");
		lblDons.setHorizontalAlignment(SwingConstants.CENTER);
		lblDons.setForeground(new Color(128, 0, 128));
		lblDons.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDons.setBounds(165, 206, 113, 20);
		getContentPane().add(lblDons);
		
		JLabel lblInscription = new JLabel("Inscription");
		lblInscription.setHorizontalAlignment(SwingConstants.CENTER);
		lblInscription.setForeground(new Color(128, 0, 128));
		lblInscription.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInscription.setBounds(294, 206, 113, 20);
		getContentPane().add(lblInscription);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(128, 0, 128));
		separator.setBounds(23, 58, 384, 2);
		getContentPane().add(separator);
				
		setVisible(true);
		//pack();
		setLocationRelativeTo(null);
	}
}
