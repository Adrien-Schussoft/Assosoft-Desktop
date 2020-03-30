package com.assosoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.assosoft.model.Asso;
import com.assosoft.model.Don;
import com.assosoft.model.User;
import com.assosoft.view.TabDyn;

public class AssoDAO {
	
		public AssoDAO()
		{
		}
		
		// Ajouter une association 
		public static void addAssoc(Asso a)
		{
			try
			{
				new Dbconnexion();
				Connection cn= Dbconnexion.getConnectionDb();
				
				String query="insert into association(Num_Asso,Nom,Rue,Ville,Code_Postal,Logo,Actif) values (?,?,?,?,?,?,?)";
	            PreparedStatement pmt = cn.prepareStatement(query);	
	            pmt.setInt(1, a.getNum_Asso());
	            pmt.setString(2, a.getNom());
	            pmt.setString(3, a.getRue());
	            pmt.setString(4, a.getVille());
	            pmt.setInt(5, a.getCode_Postal());
	            pmt.setString(6, a.getLogo());
	            pmt.setBoolean(7, a.isActif());
	            pmt.execute();
	            cn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}

		/*
		 * function : readAssocNom
		 * description : Lecture d'une association
		 * param : nom de l'association
		 * return : id de l'association
		 */
		public static int readAssocNom(String assoName) {
			ResultSet rs = null;
			int assoId = -1;
			try
			{
				new Dbconnexion();
				Connection cn= Dbconnexion.getConnectionDb();
				
				String query="select * from association where Nom = (?)";
	            PreparedStatement pmt = cn.prepareStatement(query);	
//	            pmt.setString(1, a.getNom());
	            pmt.setString(1, assoName);
	            
	            rs = pmt.executeQuery();

				if (rs.next()) {
//					a.setNum_Asso(rs.getInt(1));
//					a.setNom(rs.getString(2));
//					a.setRue(rs.getString(3));
//					a.setVille(rs.getString(4));
//					a.setCode_Postal(rs.getInt(5));
//					a.setLogo(rs.getString(6));
//					a.setActif(rs.getBoolean(7));
					assoId = rs.getInt(1);
				}
				else
				{
//					a.setNum_Asso(0);
//					a.setNom("");
//					a.setRue("");
//					a.setVille("");
//					a.setCode_Postal(0);
//					a.setLogo("");
//					a.setActif(false);
				}
				
	            cn.close();
			}catch(SQLException ex)
			{
				ex.printStackTrace();
			}
			
			return assoId;
		}		

		
		// Lecture d'une association by id
		public static Asso readAsso(int idAsso) {
			ResultSet rs = null;
			Asso a = new Asso();
			try
			{
				new Dbconnexion();
				Connection cn= Dbconnexion.getConnectionDb();
				
				String query="select * from association where Num_Asso = (?)";
	            PreparedStatement pmt = cn.prepareStatement(query);	
	            pmt.setInt(1, a.getNum_Asso());
	            
	            rs = pmt.executeQuery();

				if (rs.next()) {
					a.setNum_Asso(rs.getInt(1));
					a.setNom(rs.getString(2));
					a.setRue(rs.getString(3));
					a.setVille(rs.getString(4));
					a.setCode_Postal(rs.getInt(5));
					a.setLogo(rs.getString(6));
					a.setActif(rs.getBoolean(7));
				}
				else
				{
					a.setNum_Asso(0);
					a.setNom("");
					a.setRue("");
					a.setVille("");
					a.setCode_Postal(0);
					a.setLogo("");
					a.setActif(false);
				}

	            cn.close();
			}catch(SQLException ex)
			{
				ex.printStackTrace();
			}
			
			return a;
		}		
		
		// Mise � jour d'une association 
		public static void updAssoc(Asso a) {
			try
			{
				new Dbconnexion();
				Connection cn= Dbconnexion.getConnectionDb();
				
				String query = "update association set nom = (?), rue = (?), ville = (?), code_postal = (?), logo = (?), actif = (?) where Num_Asso = (?)";
				PreparedStatement pmt = cn.prepareStatement(query);

	            pmt.setString(1,a.getNom());
	            pmt.setString(2, a.getRue());
	            pmt.setString(3, a.getVille());
	            pmt.setInt(4, a.getCode_Postal());
	            pmt.setString(5, a.getLogo());
	            pmt.setBoolean(6, a.isActif());
	            pmt.setInt(7, a.getNum_Asso());
				pmt.execute();
				pmt.close();
	            cn.close();
			}catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		
		// Suppression d'une association 
		public static void delAssoc(Asso a) {
			try
			{
				new Dbconnexion();
				Connection cn= Dbconnexion.getConnectionDb();
				
				String query = "delete from association where Num_Asso = (?)";
				PreparedStatement pmt = cn.prepareStatement(query);
	            pmt.setInt(1, a.getNum_Asso());
				pmt.execute();
				pmt.close();
	            cn.close();
			}catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		
		
	// une fonction qui return un arraylist de toutes les associations 
		public static ArrayList<Asso> touteslesassos(boolean actif )
		{
			Connection cn = null;
			ResultSet rs =null;   //  objet de type tableau
			Asso wasso;
			ArrayList<Asso> assocs = new ArrayList<Asso>(); // tableau des etudiants  
			try
			{
				new Dbconnexion();
				cn= Dbconnexion.getConnectionDb();
				
				String query=" select * from association where Actif = (?)";
		        PreparedStatement pmt = cn.prepareStatement(query);	
		        pmt.setBoolean(1, actif);
		        rs= pmt.executeQuery();
		       
		       // Parcourir  le tableau qui contient les donn�es
		       while(rs.next())
		       {
		    	   wasso = new Asso(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getBoolean(7));
		    	   assocs.add(wasso);
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
		return 	assocs;
		}
		
		
		// une fonction qui return un arraylist de toutes les associations users
		public static ArrayList<Asso> touteslesassosUsers(int user)
		{
			Connection cn = null;
			ResultSet rs =null;   //  objet de type tableau
			Asso wasso;
			ArrayList<Asso> assocs = new ArrayList<Asso>(); // tableau des users  
			try
			{
				new Dbconnexion();
				cn= Dbconnexion.getConnectionDb();
				
				//String query=" select * from Association where Actif = (?)";
				String query="SELECT * FROM association, appartient_asso  WHERE association.num_asso = appartient_asso.num_asso and appartient_asso.num_user = (?)"; 
		        PreparedStatement pmt = cn.prepareStatement(query);	
		        pmt.setInt(1, user);
		        rs= pmt.executeQuery();
		       
		       // Parcourir  le tableau qui contient les donn�es
		       while(rs.next())
		       {
		    	   wasso = new Asso(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getBoolean(7));
		    	   assocs.add(wasso);
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
		return 	assocs;
		}
		
		// une fonction qui return un arraylist de toutes les associations 
		public static TabDyn afftousAssoc(TabDyn montab) //- ArrayList<Assoc> afftousAssoc()
		{
			Connection cn = null;
			ResultSet rs =null;   //  objet de type tableau
			try
			{
				new Dbconnexion();
				cn= Dbconnexion.getConnectionDb();
				
				String query=" select * from association ";
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
		    	   montab.AjouterSousItem(4,""+rs.getInt(5));
		    	   montab.AjouterSousItem(5,rs.getString(6));
		    	   montab.AjouterSousItem(6,""+rs.getBoolean(7));
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
		
		// add user assoc
		public static void addUserAssoc(User u,Asso a)
		{
			try
			{
				new Dbconnexion();
				Connection cn= Dbconnexion.getConnectionDb();
				
				String query="insert into appartient_asso(Num_Asso,Num_User) values (?,?)";
	            PreparedStatement pmt = cn.prepareStatement(query);	
	            pmt.setInt(1, a.getNum_Asso());
	            pmt.setInt(2, u.getNum_Personne());
	            pmt.execute();
	            cn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}

		// Del user assoc 
		public static void delUserAssoc(User u,Asso a) {
			try
			{
				new Dbconnexion();
				Connection cn= Dbconnexion.getConnectionDb();
				
				String query = "delete from appartient_asso where Num_Asso = (?) and Num_User = (?)";
				PreparedStatement pmt = cn.prepareStatement(query);
	            pmt.setInt(1, a.getNum_Asso());
	            pmt.setInt(2, u.getNum_Personne());
				pmt.execute();
				pmt.close();
	            cn.close();
			}catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		
		// add don assoc
		public static void addDonAssoc(int idAsso, int idDon)
		{
			try
			{
				new Dbconnexion();
				Connection cn= Dbconnexion.getConnectionDb();
				
				String query="insert into recoit(Num_Asso,Num_Don) values (?,?)";
	            PreparedStatement pmt = cn.prepareStatement(query);	
	            pmt.setInt(1, idAsso);
	            pmt.setInt(2, idDon);
	            pmt.execute();
	            cn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}

		// del don assoc
		public static void delDonAssoc(Don d,Asso a)
		{
			try
			{
				new Dbconnexion();
				Connection cn= Dbconnexion.getConnectionDb();
				
				String query="delete from recoit where  Num_Asso = (?) and Num_Don = (?)";
	            PreparedStatement pmt = cn.prepareStatement(query);	
	            pmt.setInt(1, a.getNum_Asso());
	            pmt.setInt(2, d.getNum_Don());
	            pmt.execute();
	            cn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	
	}
