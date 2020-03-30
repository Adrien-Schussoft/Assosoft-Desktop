package com.assosoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.assosoft.model.Donateur;

public class DonateurDAO {

	public DonateurDAO()
	{
	}
	
	// Ajouter un Donateur 
	public static void addDonateur(Donateur d)
	{
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query="insert into donateur(Num_Donateur,Nom,Prenom,Rue,Ville,Code_Postal,Mail,Date_Inscription,Password) values (?,?,?,?,?,?,?,?,?)";
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
            pmt.execute();
            cn.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	// Lecture d'un Donateur
	public static void readDonateurMail(Donateur d) {
		ResultSet rs = null;
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query="select * from donateur where Mail = (?)";
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
				d.setDate_Inscription(MainDAO.convdate(rs.getString(8)) );
				d.setPassword(rs.getString(9));
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
			}
            cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}		

	// Lecture d'un Donateur
	public static void readDonateur(Donateur d) {
		ResultSet rs = null;
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query="select * from donateur where Num_Donateur = (?)";
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
				d.setDate_Inscription(MainDAO.convdate(rs.getString(8)) );
				d.setPassword(rs.getString(9));
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
			}
            cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}		

	
	// Test si l'adresse mail est déjà en base
	public static boolean emailExist(String email) {
		
		boolean userExist = false;
		ResultSet rs = null;
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query="select * from donateur where Mail = (?)";
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

	
	// Mise � jour d'un Donateur 
	public static void updDonateur(Donateur d) {
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query = "update donateur set nom = (?), prenom = (?), rue = (?), ville = (?), code_postal = (?), Mail = (?), Date_Inscription = (?), Password = (?) where Num_Donateur = (?)";
			PreparedStatement pmt = cn.prepareStatement(query);

            pmt.setString(1, d.getNom());
            pmt.setString(2, d.getPrenom());
            pmt.setString(3, d.getRue());
            pmt.setString(4, d.getVille());
            pmt.setInt(5, d.getCode_Postal());
            pmt.setString(6, d.getMail());
            pmt.setString(7, d.getDate_Inscription().toString());
            pmt.setString(8, d.getPassword());
            pmt.setInt(9, d.getNum_Personne());
			pmt.execute();
			pmt.close();
            cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	// Suppression d'un donateur 
	public static void delDonateur(Donateur d) {
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query = "delete from donateur where Num_Donateur = (?)";
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
	
	
	// une fonction qui return un arraylist de tous les donateurs 
	public static ArrayList<Donateur> touslesdonateurs( )
	{
		Connection cn = null;
		ResultSet rs =null;   //  objet de type tableau
		Donateur wdonateur;
		ArrayList<Donateur> donateurs = new ArrayList<Donateur>(); // tableau des etudiants  
		try
		{
			new Dbconnexion();
			cn= Dbconnexion.getConnectionDb();
			
			String query=" select * from donateur";
	        PreparedStatement pmt = cn.prepareStatement(query);	
	        rs= pmt.executeQuery();
	       
	       // Parcourir  le tableau qui contient les donn�es
	       while(rs.next())
	       {
	    	   wdonateur = new Donateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7), MainDAO.convdate(rs.getString(8)) ,rs.getString(9));
	    	   donateurs.add(wdonateur);
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
	return 	donateurs;
	}

}
