package com.assosoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.assosoft.model.User;
import com.assosoft.view.TabDyn;

public class UserDAO {
	public UserDAO()
	{
	}
	
	// Ajouter un User 
	public static void addUser(User d)
	{
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query="insert into users (Num_User, Nom,Prenom,Rue,Ville,Code_Postal,Mail,Date_Inscription,Password,Actif,Root,Contact) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pmt = cn.prepareStatement(query);	
            pmt.setInt(1, d.getNum_Personne());
            pmt.setString(2, d.getNom());
            pmt.setString(3, d.getPrenom());
            pmt.setString(4, d.getRue());
            pmt.setString(5, d.getVille());
            pmt.setInt(6, d.getCode_Postal());
            pmt.setString(7, d.getMail());
            pmt.setString(8, d.getDate_Inscription().toString());
            pmt.setString(9, d.getPassword());
            pmt.setBoolean(10, d.isActif());
            pmt.setBoolean(11, d.isRoot());
            pmt.setBoolean(12, d.isContact());
            pmt.execute();
            cn.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	// Lecture d'un User via adresse mail
	public static int readUserMail(User d) {
		
			ResultSet rs = null;
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query="select * from users where Mail = (?)";
            PreparedStatement pmt = cn.prepareStatement(query);	
            pmt.setString(1, d.getMail());
            
            rs = pmt.executeQuery();

			if (rs.next()) {
				d.setNum_Personne(rs.getInt(1));
				d.setNom(rs.getString(2));
				d.setPrenom(rs.getString(3));
				d.setRue(rs.getString(4));
				d.setVille(rs.getString(5));
				d.setCode_Postal(rs.getInt(6));
				d.setMail(rs.getString(7));
				d.setDate_Inscription( MainDAO.convdate(rs.getString(8)) );
				d.setPassword(rs.getString(9));
				d.setActif(rs.getBoolean(10));
				d.setRoot(rs.getBoolean(11));
				d.setContact(rs.getBoolean(12));
			}
			else
			{
				d.setNum_Personne(0);
				d.setNom("");
				d.setPrenom("");
				d.setRue("");
				d.setVille("");
				d.setCode_Postal(0);
				d.setMail("");
				d.setDate_Inscription( LocalDate.of(1980,1,1) );
				d.setPassword("");
				d.setActif(false);
				d.setRoot(false);
				d.setContact(false);
			}
            cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return d.getNum_Personne();
		
	}
	
	// Test si l'adresse mail est déjà en base
	public static boolean emailExist(String email) {
		
		boolean userExist = false;
		ResultSet rs = null;
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query="select * from users where Mail = (?)";
            PreparedStatement pmt = cn.prepareStatement(query);	
            pmt.setString(1, email);
            
            rs = pmt.executeQuery();

			if (rs.next()) {
				userExist = true;
			}
            cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		if (userExist) {
			return true;
		} else {
			return false;
		}
		
	}
	
	// Test si le mail et le mot de passe sont valides
	public static boolean validLogin(String email, String pass) {
		
		System.out.println(email);
		System.out.println(pass);
		
		boolean userExist = false;
		ResultSet rs = null;
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query="select * from users where Mail = (?) and Password = (?)";
            PreparedStatement pmt = cn.prepareStatement(query);	
            pmt.setString(1, email);
            pmt.setString(2, pass);
            
            rs = pmt.executeQuery();

			if (rs.next()) {
				userExist = true;
			}
            cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		if (userExist) {
			return true;
		} else {
			return false;
		}
		
	}	

	
	
	// Lecture d'un User by id
	public static User readUser(int idUser) {
		ResultSet rs = null;
		User d = new User();
		
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query="select * from users where Num_User = (?)";
            PreparedStatement pmt = cn.prepareStatement(query);	
            pmt.setInt(1, d.getNum_Personne());
            
            rs = pmt.executeQuery();

			if (rs.next()) {
				d.setNum_Personne(rs.getInt(1));
				d.setNom(rs.getString(2));
				d.setPrenom(rs.getString(3));
				d.setRue(rs.getString(4));
				d.setVille(rs.getString(5));
				d.setCode_Postal(rs.getInt(6));
				d.setMail(rs.getString(7));
				d.setDate_Inscription( MainDAO.convdate(rs.getString(8)) );
				d.setPassword(rs.getString(9));
				d.setActif(rs.getBoolean(10));
				d.setRoot(rs.getBoolean(11));
				d.setContact(rs.getBoolean(12));
			}
			else
			{
				d.setNum_Personne(0);
				d.setNom("");
				d.setPrenom("");
				d.setRue("");
				d.setVille("");
				d.setCode_Postal(0);
				d.setMail("");
				d.setDate_Inscription( LocalDate.of(1980,1,1) );
				d.setPassword("");
				d.setActif(false);
				d.setRoot(false);
				d.setContact(false);
			}
 
            cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return d;
	}		
	
	// Mise � jour d'un user 
	public static void updUser(User d) {
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query = "update users set nom = (?), prenom = (?), rue = (?), ville = (?), code_postal = (?), Mail = (?), Date_Inscription = (?), Password = (?), Actif = (?), Root = (?), Contact = (?)  where Num_User = (?)";
			PreparedStatement pmt = cn.prepareStatement(query);

            pmt.setString(1, d.getNom());
            pmt.setString(2, d.getPrenom());
            pmt.setString(3, d.getRue());
            pmt.setString(4, d.getVille());
            pmt.setInt(5, d.getCode_Postal());
            pmt.setString(6, d.getMail());
            pmt.setString(7, d.getDate_Inscription().toString());
            pmt.setString(8, d.getPassword());
            pmt.setBoolean(9, d.isActif());
            pmt.setBoolean(10, d.isRoot());
            pmt.setBoolean(11, d.isContact());
            pmt.setInt(12, d.getNum_Personne());
			pmt.execute();
			pmt.close();
            cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	// Suppression d'un user 
	public static void delUser(User d) {
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query = "delete from users where Num_User = (?)";
			PreparedStatement pmt = cn.prepareStatement(query);
            pmt.setInt(1, d.getNum_Personne());
			pmt.execute();
			pmt.close();
            cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	// une fonction qui return un arraylist de tous les users 
	public static ArrayList<User> touslesusers( )
	{
		Connection cn = null;
		ResultSet rs =null;   //  objet de type tableau
		User wuser;
		ArrayList<User> users = new ArrayList<User>(); // tableau des users 
		try
		{
			new Dbconnexion();
			cn= Dbconnexion.getConnectionDb();
			
			String query=" select * from users";
	        PreparedStatement pmt = cn.prepareStatement(query);	
	        rs= pmt.executeQuery();
	       
	       // Parcourir  le tableau qui contient les donn�es
	       while(rs.next())
	       {
	    	   wuser = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),MainDAO.convdate(rs.getString(8)),rs.getString(9),rs.getBoolean(10),rs.getBoolean(11),rs.getBoolean(12));
	    	   users.add(wuser);
	    	   //System.out.println(wuser.getNom());
	       }
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally 
		{
			 try {
				cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	return 	users;
	}
	

	
	
	// une fonction qui return un tabdyn avec les users 
	public static TabDyn afftousUser(TabDyn montab) //- ArrayList<Assoc> afftousAssoc()
	{
		Connection cn = null;
		ResultSet rs =null;   //  objet de type tableau

		try
		{
			new Dbconnexion();
			cn= Dbconnexion.getConnectionDb();
			
			String query=" select * from users ";
	        PreparedStatement pmt = cn.prepareStatement(query);	
	        rs= pmt.executeQuery();
	        montab.InitialiserJTable();
	       // Parcourir  le tableau qui contient les donn�es
			while  ( rs.next() ) 
	       {
	    	   montab.AjouterSousItem(0,""+rs.getInt(1));
	    	   montab.AjouterSousItem(1,rs.getString(2));
	    	   montab.AjouterSousItem(2,rs.getString(3));
	    	   montab.AjouterSousItem(3,rs.getString(4));
	    	   montab.AjouterSousItem(4,rs.getString(5));
	    	   montab.AjouterSousItem(5,""+rs.getInt(6));
	    	   montab.AjouterSousItem(6,rs.getString(7));
	    	   montab.AjouterSousItem(7,rs.getString(8));
	    	   montab.AjouterSousItem(8,rs.getString(9));
	    	   montab.AjouterSousItem(9,""+rs.getBoolean(10));
	    	   montab.AjouterSousItem(10,""+rs.getBoolean(11));
	    	   montab.AjouterSousItem(11,rs.getString(12));

	    	   //- assocs.add(wasso);
	       }
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally 
		{
			 try {
				cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	//-return 	assocs;
		return montab;
	}

	// une fonction qui return un tabdyn avec les users 
	public static TabDyn readUsersAsso(TabDyn montab, int NumAsso) //- ArrayList<Assoc> afftousAssoc()
	{
		Connection cn = null;
		ResultSet rs =null;   //  objet de type tableau

		try
		{
			new Dbconnexion();
			cn= Dbconnexion.getConnectionDb();
			
			String query=" select * from users, appartient_asso where users.Num_User = appartient_asso.Num_User and appartient_asso.Num_Asso = (?) ";
	        PreparedStatement pmt = cn.prepareStatement(query);	
            pmt.setInt(1, NumAsso);
	        rs= pmt.executeQuery();
	        montab.InitialiserJTable();
	       // Parcourir  le tableau qui contient les donn�es
			while  ( rs.next() ) 
	       {
	    	   montab.AjouterSousItem(0,"" + rs.getInt(1));
	    	   montab.AjouterSousItem(1,rs.getString(2));
	    	   montab.AjouterSousItem(2,rs.getString(3));
	    	   montab.AjouterSousItem(3,rs.getString(4));
	    	   montab.AjouterSousItem(4,rs.getString(5));
	    	   montab.AjouterSousItem(5,""+rs.getInt(6));
	    	   montab.AjouterSousItem(6,rs.getString(7));
	    	   montab.AjouterSousItem(7,rs.getString(8));
	    	   montab.AjouterSousItem(8,rs.getString(9));
	    	   montab.AjouterSousItem(9,""+rs.getBoolean(10));
	    	   montab.AjouterSousItem(10,""+rs.getBoolean(11));
	    	   montab.AjouterSousItem(11, "" + rs.getBoolean(12));

	    	   //- assocs.add(wasso);
	       }
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally 
		{
			 try {
				cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	//-return 	assocs;
		return montab;
	}

}
