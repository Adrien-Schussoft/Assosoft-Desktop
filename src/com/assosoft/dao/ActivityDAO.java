package com.assosoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.assosoft.dao.Dbconnexion;
import com.assosoft.model.Activity;
import com.assosoft.model.User;
import com.assosoft.view.TabDyn;

public class ActivityDAO {
	
	public ActivityDAO() {}
	
	// Ajouter une activite 
	public static void addActivity(Activity a)
	{
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query="insert into activite(Nom,Date_Debut,Date_Fin,Budget,Cout,Convocation,Compte_Rendu,Num_asso) values (?,?,?,?,?,?,?,?)";
            PreparedStatement pmt = cn.prepareStatement(query);	
            pmt.setString(1, a.getNom());
            pmt.setString(2, a.getDate_Debut().toString()); 
            pmt.setString(3, a.getDate_Fin().toString());
            pmt.setFloat(4, a.getBudget());
            pmt.setFloat(5, a.getCout());
            pmt.setBoolean(6, a.isConvocation());
            pmt.setString(7, a.getCompte_Rendu());
            pmt.setInt(8, a.getNum_Asso());
            pmt.execute();
            cn.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	// Lecture d'une activite
	public static Activity readActivity(int activityId) {
		
		Activity a = new Activity();
		ResultSet rs = null;
		
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			
			
			String query="select * from activite where Num_Activite = (?)";
            PreparedStatement pmt = cn.prepareStatement(query);	
            pmt.setInt(1, activityId);
            
            rs = pmt.executeQuery();

			if (rs.next()) {
				a.setNum_Activite(rs.getInt(1));
				a.setNom(rs.getString(2));
				a.setDate_Debut(MainDAO.convdate(rs.getString(3)));
				a.setDate_Fin(MainDAO.convdate(rs.getString(4)));
				a.setBudget(rs.getFloat(5));
				a.setCout(rs.getFloat(6));
				a.setConvocation(rs.getBoolean(7));
				a.setCompte_Rendu(rs.getString(8));
				a.setNum_Asso(rs.getInt(9));
			}
            cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return a;
		
	}
	
	// Read all Activities of an Association
	public static List<Activity> readAssoActivity(int idAsso) {
		
		Connection cn = null;
		ResultSet rs =null;   //  objet de type tableau
		Activity wactivite;
		List<Activity> activites = new ArrayList<Activity>(); // tableau des etudiants  
		try
		{
			new Dbconnexion();
			cn= Dbconnexion.getConnectionDb();
			
			String query=" select * from activite  where Num_Asso = (?)";
	        PreparedStatement pmt = cn.prepareStatement(query);	
            pmt.setInt(1, idAsso);
	        rs= pmt.executeQuery();
	       
	       while(rs.next())
	       {
	    	   wactivite = new Activity(rs.getString(2),MainDAO.convdate(rs.getString(3)),MainDAO.convdate(rs.getString(4)),rs.getFloat(5),rs.getFloat(6),rs.getBoolean(7),rs.getString(8),rs.getInt(9) );
	    	   activites.add(wactivite);
	       }
		}catch(SQLException e)
		{
			e.printStackTrace();
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return activites;

	}
	
	// Mise � jour d'une activite 
	public static void updActivity(Activity a) {
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query = "update activite set nom = (?), Date_Debut = (?), Date_Fin = (?), Budget = (?), Cout = (?), Convocation = (?), Compte_Rendu = (?), Num_Asso = (?) where Num_Activite = (?)";
			PreparedStatement pmt = cn.prepareStatement(query);

            pmt.setString(1,a.getNom());
            pmt.setString(2, a.getDate_Debut().toString()); 
            pmt.setString(3, a.getDate_Fin().toString());
            pmt.setFloat(4, a.getBudget());
            pmt.setFloat(5, a.getCout());
            pmt.setBoolean(6, a.isConvocation());
            pmt.setString(7, a.getCompte_Rendu());
            pmt.setInt(8, a.getNum_Asso());
            pmt.setInt(9, a.getNum_Activite());
			pmt.execute();
			pmt.close();
            cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	// Suppression d'une activite 
	public static void delActivity(int activityId) {
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query = "delete from activite where Num_Activite = (?)";
			PreparedStatement pmt = cn.prepareStatement(query);
            pmt.setInt(1, activityId);
			pmt.execute();
			pmt.close();
            cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	// une fonction qui return un arraylist de toutes les activites 
	public static ArrayList<Activity> readAllActivity( )
	{
		Connection cn = null;
		ResultSet rs =null;   //  objet de type tableau
		Activity wactivite;
		ArrayList<Activity> activites = new ArrayList<Activity>(); // tableau des etudiants  
		try
		{
			new Dbconnexion();
			cn= Dbconnexion.getConnectionDb();
			
			String query=" select * from activite";
	        PreparedStatement pmt = cn.prepareStatement(query);	
	        rs= pmt.executeQuery();
	       
	       // Parcourir  le tableau qui contient les donn�es
	       while(rs.next())
	       {
//	    	   wactivite = new Activity(rs.getInt(1),rs.getString(2),MainDAO.convdate(rs.getString(3)),MainDAO.convdate(rs.getString(4)),rs.getFloat(5),rs.getFloat(6),rs.getBoolean(7),rs.getString(8),rs.getInt(9) );
	    	   wactivite = new Activity(rs.getString(2),MainDAO.convdate(rs.getString(3)),MainDAO.convdate(rs.getString(4)),rs.getFloat(5),rs.getFloat(6),rs.getBoolean(7),rs.getString(8),rs.getInt(9) );
	    	   activites.add(wactivite);
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
	return 	activites;
	}

	
	// add user activit�
	public static void addUserActivity(User u,Activity a)
	{
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query="insert into appartient_activite(Num_Activite,Num_User) values (?,?)";
            PreparedStatement pmt = cn.prepareStatement(query);	
            pmt.setInt(1, a.getNum_Activite());
            pmt.setInt(2, u.getNum_Personne());
            pmt.execute();
            cn.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	// Del user Activity 
	public static void delUserActivity(User u, Activity a) {
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();
			
			String query = "delete from appartient_activite where Num_Activite = (?) and Num_User = (?)";
			PreparedStatement pmt = cn.prepareStatement(query);
            pmt.setInt(1, a.getNum_Activite());
            pmt.setInt(2, u.getNum_Personne());
			pmt.execute();
			pmt.close();
            cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}


	// une fonction qui return un tabdyn avec les activités d'une association 
	public static TabDyn readActivityAsso(TabDyn montab, int NumAsso) 
	{
		Connection cn = null;
		ResultSet rs =null;   //  objet de type tableau

		try
		{
			new Dbconnexion();
			cn= Dbconnexion.getConnectionDb();
			
			String query=" select * from activite where Num_Asso = (?) ";
	        PreparedStatement pmt = cn.prepareStatement(query);	
            pmt.setInt(1, NumAsso);
	        rs= pmt.executeQuery();
	        montab.InitialiserJTable();
	       // Parcourir  le tableau qui contient les donn�es
			while  ( rs.next() ) 
	       {
	    	   montab.AjouterSousItem(0, "" + rs.getInt(1));
	    	   montab.AjouterSousItem(1, rs.getString(2));
	    	   montab.AjouterSousItem(2, rs.getString(3));
	    	   montab.AjouterSousItem(3, rs.getString(4));
	    	   montab.AjouterSousItem(4, "" + rs.getFloat(5));
	    	   montab.AjouterSousItem(5, "" + rs.getFloat(6));
	    	   montab.AjouterSousItem(6, "" + rs.getBoolean(7));
	    	   montab.AjouterSousItem(7, rs.getString(8));
	    	   montab.AjouterSousItem(8, "" + rs.getInt(9));
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
		return montab;
	}
	
	
	
}
