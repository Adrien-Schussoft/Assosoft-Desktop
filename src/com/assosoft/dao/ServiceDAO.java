package com.assosoft.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.assosoft.model.Activity;
import com.assosoft.model.Service;
import com.assosoft.view.TabDyn;

public class ServiceDAO {
	public ServiceDAO()
	{
	}

	// Ajouter un service
	public static void addService(Service a)
	{
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();

			String query="insert into service(Num_Service,Nom,Num_asso) values (?,?,?)";
			PreparedStatement pmt = cn.prepareStatement(query);
			pmt.setInt(1, a.getNum_Service());
			pmt.setString(2, a.getNom());
			pmt.setInt(3, a.getNum_Asso());
			pmt.execute();
			cn.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	// Lecture d'un service
	public static void readService(Service a) {
		ResultSet rs = null;
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();

			String query="select * from service where Num_Service = (?)";
			PreparedStatement pmt = cn.prepareStatement(query);
			pmt.setInt(1, a.getNum_Service());

			rs = pmt.executeQuery();

			if (rs.next()) {
				a.setNum_Service(rs.getInt(1));
				a.setNom(rs.getString(2));
				a.setNum_Asso(rs.getInt(3));
			}
			cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	// Read all Services of an Association
	public static List<Service> readAssoService(int idAsso) {
		
		Connection cn = null;
		ResultSet rs =null;   //  objet de type tableau
		Service wservice;
		List<Service> services = new ArrayList<Service>(); // tableau des etudiants  
		try
		{
			new Dbconnexion();
			cn= Dbconnexion.getConnectionDb();
			
			String query=" select * from service where Num_Asso = (?)";
	        PreparedStatement pmt = cn.prepareStatement(query);	
            pmt.setInt(1, idAsso);
	        rs= pmt.executeQuery();
	       
	       while(rs.next())
	       {
	    	   wservice = new Service(rs.getInt(1),rs.getString(2),rs.getInt(3));
	    	   services.add(wservice);
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
		return services;

	}

	// Mise � jour d'un service
	public static void updService(Service a) {
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();

			String query = "update service set nom = (?), Num_Asso = (?) where Num_Service = (?)";
			PreparedStatement pmt = cn.prepareStatement(query);

			pmt.setString(1,a.getNom());
			pmt.setInt(2, a.getNum_Asso());
			pmt.setInt(3, a.getNum_Service());
			pmt.execute();
			pmt.close();
			cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}

	// Suppression d'un service
	public static void delService(int idService) {
		try
		{
			new Dbconnexion();
			Connection cn= Dbconnexion.getConnectionDb();

			String query = "delete from service where Num_Service = (?)";
			PreparedStatement pmt = cn.prepareStatement(query);
			pmt.setInt(1, idService);
			pmt.execute();
			pmt.close();
			cn.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}


	// une fonction qui retourne un arraylist de tous les services
	public static ArrayList<Service> readAllService( )
	{
		Connection cn = null;
		ResultSet rs =null;   //  objet de type tableau
		Service wservice;
		ArrayList<Service> services = new ArrayList<Service>();
		try
		{
			new Dbconnexion();
			cn= Dbconnexion.getConnectionDb();

			String query=" select * from service";
			PreparedStatement pmt = cn.prepareStatement(query);
			rs= pmt.executeQuery();

			// Parcourir  le tableau qui contient les donn�es
			while(rs.next())
			{
				wservice = new Service(rs.getInt(1),rs.getString(2),rs.getInt(3));
				services.add(wservice);
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
		return 	services;
	}

	 /*
	  * readServicesByAssoId
	  * read all the services that belong to an association
	  */
//		public static ArrayList<Service> readServicesByAssoId(int assoId)
//		{
//			Connection cn = null;
//			ResultSet rs =null;   //  objet de type tableau
//			Service wservice;
//			ArrayList<Service> services = new ArrayList<Service>();
//
//			try
//			{
//				new Dbconnexion();
//				cn= Dbconnexion.getConnectionDb();
//
//				String query=" select * from Service where Num_Asso = (?)";
//				PreparedStatement pmt = cn.prepareStatement(query);
//				pmt.setInt(1, assoId);
//				rs= pmt.executeQuery();
//
//				// Parcourir  le tableau qui contient les données
//				while(rs.next())
//				{
//					wservice = new Service(rs.getInt(1),rs.getString(2),rs.getInt(3));
//					services.add(wservice);
//				}
//			}catch(SQLException e)
//			{
//				e.printStackTrace();
//			}finally
//			{
//				try {
//					cn.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			return 	services;
//		}
	
	public static TabDyn readServicesByAssoId(TabDyn montab, int assoId)
	{
		Connection cn = null;
		ResultSet rs =null;   //  objet de type tableau
		Service wservice;
		ArrayList<Service> services = new ArrayList<Service>();

		try
		{
			new Dbconnexion();
			cn= Dbconnexion.getConnectionDb();

			String query=" select * from service where Num_Asso = (?)";
			PreparedStatement pmt = cn.prepareStatement(query);
			pmt.setInt(1, assoId);
			rs= pmt.executeQuery();
			montab.InitialiserJTable();

			// Parcourir  le tableau qui contient les données
			while(rs.next())
			{
//				wservice = new Service(rs.getInt(1),rs.getString(2),rs.getInt(3));
//				services.add(wservice);
				montab.AjouterSousItem(0, "" + rs.getInt(1));
				montab.AjouterSousItem(1, rs.getString(2));
				montab.AjouterSousItem(2, "" + rs.getInt(3));
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
		return 	montab;
	}


//	// une fonction qui retourne un arraylist de tous les services pour les asso
//	public static ArrayList<Service> readServicesAsso( int NumAsso )
//	{
//		Connection cn = null;
//		ResultSet rs =null;   //  objet de type tableau
//		Service wservice;
//		ArrayList<Service> services = new ArrayList<Service>();
//		try
//		{
//			new Dbconnexion();
//			cn= Dbconnexion.getConnectionDb();
//
//			String query=" select * from service where Num_Asso = (?) ";
//			PreparedStatement pmt = cn.prepareStatement(query);
//			pmt.setInt(1, NumAsso);
//			rs= pmt.executeQuery();
//
//			// Parcourir  le tableau qui contient les donn�es
//			while(rs.next())
//			{
//				wservice = new Service(rs.getInt(1),rs.getString(2),rs.getInt(3));
//				services.add(wservice);
//			}
//		}catch(SQLException e)
//		{
//			e.printStackTrace();
//		}finally
//		{
//			try {
//				cn.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return 	services;
//	}


}


