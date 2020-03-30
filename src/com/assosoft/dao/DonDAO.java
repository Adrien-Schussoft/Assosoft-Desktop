package com.assosoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.assosoft.model.Don;
import com.assosoft.view.TabDyn;

public class DonDAO {
	// Ajouter un Don
	public static void addDon(Don d, int idAsso) {
		try {
			new Dbconnexion();
			Connection cn = Dbconnexion.getConnectionDb();

			String query = "insert into don(Type,Etat,Montant,Contenu,Date_Don,Num_Donateur,Num_User) values (?,?,?,?,?,?,?)";
			PreparedStatement pmt = cn.prepareStatement(query);
			pmt.setString(1, d.getType());
			pmt.setBoolean(2, d.isEtat());
			pmt.setFloat(3, d.getMontant());
			pmt.setString(4, d.getContenu());
			pmt.setString(5, d.getDate_Don().toString());
			pmt.setInt(6, d.getNum_Donateur());
			pmt.setInt(7, d.getNum_User());
			pmt.execute();

			int idDon = readLastDon();
//			query = "insert into recoit(Num_Don, Num_Asso) values (?,?)";
//			pmt = cn.prepareStatement(query);
//			pmt.setInt(1, d.getNum_Don());
//			pmt.setInt(2, idAsso);
//			pmt.execute();

			AssoDAO.addDonAssoc(idAsso, idDon);

			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Lecture d'un Don
	public static void readDon(Don d) {
		ResultSet rs = null;
		try {
			new Dbconnexion();
			Connection cn = Dbconnexion.getConnectionDb();

			String query = "select * from don where Num_Don = (?)";
			PreparedStatement pmt = cn.prepareStatement(query);
			pmt.setInt(1, d.getNum_Don());

			rs = pmt.executeQuery();

			if (rs.next()) {
				d.setNum_Don(rs.getInt(1));
				d.setType(rs.getString(2));
				d.setEtat(rs.getBoolean(3));
				d.setMontant(rs.getFloat(4));
				d.setContenu(rs.getString(5));
				d.setDate_Don(MainDAO.convdate(rs.getString(6)));
				d.setNum_Donateur(rs.getInt(7));
				d.setNum_User(rs.getInt(8));
			}
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// Lecture du dernier Don
	public static int readLastDon() {

		int idDon = 0;
		ResultSet rs = null;

		try {
			new Dbconnexion();
			Connection cn = Dbconnexion.getConnectionDb();

			String query = "select * from don order by Num_Don desc limit 1";
			PreparedStatement pmt = cn.prepareStatement(query);

			rs = pmt.executeQuery();

			if (rs.next()) {
				idDon = rs.getInt(1);
			}
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return idDon;
	}

	public static void readDonDonateur(Don d) {
		ResultSet rs = null;
		try {
			new Dbconnexion();
			Connection cn = Dbconnexion.getConnectionDb();

			String query = "select * from don where Num_Donateur = (?)";
			PreparedStatement pmt = cn.prepareStatement(query);
			pmt.setInt(1, d.getNum_Donateur());

			rs = pmt.executeQuery();

			if (rs.next()) {
				d.setNum_Don(rs.getInt(1));
				d.setType(rs.getString(2));
				d.setEtat(rs.getBoolean(3));
				d.setMontant(rs.getFloat(4));
				d.setContenu(rs.getString(5));
				d.setDate_Don(MainDAO.convdate(rs.getString(6)));
				d.setNum_Donateur(rs.getInt(7));
				d.setNum_User(rs.getInt(8));
			} else {
				d.setNum_Don(0);
				d.setType("");
				d.setEtat(false);
				d.setMontant(0);
				d.setContenu("");
				d.setDate_Don(LocalDate.of(1980, 1, 1));
				d.setNum_Donateur(0);
				d.setNum_User(0);
			}
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// Mise à jour d'un Don
	public static void updDon(Don d) {
		try {
			new Dbconnexion();
			Connection cn = Dbconnexion.getConnectionDb();

			String query = "update don set Type = (?), Etat = (?), Montant = (?), Contenu = (?), Date_Don = (?), Num_Donateur = (?), Num_User = (?) = (?) where Num_Don = (?)";
			PreparedStatement pmt = cn.prepareStatement(query);

			pmt.setString(1, d.getType());
			pmt.setBoolean(2, d.isEtat());
			pmt.setFloat(3, d.getMontant());
			pmt.setString(4, d.getContenu());
			pmt.setString(5, d.getDate_Don().toString());
			pmt.setInt(6, d.getNum_Donateur());
			pmt.setInt(7, d.getNum_User());
			pmt.setInt(8, d.getNum_Don());
			pmt.execute();
			pmt.close();
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// Suppression d'un don
	public static void delDon(int idDon) {
		try {
			new Dbconnexion();
			Connection cn = Dbconnexion.getConnectionDb();

			String query = "delete from don where Num_Don = (?)";
			PreparedStatement pmt = cn.prepareStatement(query);
			pmt.setInt(1, idDon);
			pmt.execute();
			pmt.close();
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// une fonction qui retourne un arraylist de tous les dons
	public static ArrayList<Don> readAllDon() {
		Connection cn = null;
		ResultSet rs = null; // objet de type tableau
		Don wdon;
		ArrayList<Don> dons = new ArrayList<Don>(); // tableau des etudiants
		try {
			new Dbconnexion();
			cn = Dbconnexion.getConnectionDb();

			String query = " select * from don";
			PreparedStatement pmt = cn.prepareStatement(query);
			rs = pmt.executeQuery();

			// Parcourir le tableau qui contient les données
			while (rs.next()) {
				wdon = new Don(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getFloat(4), rs.getString(5),
						MainDAO.convdate(rs.getString(6)), rs.getInt(7), rs.getInt(8));
				dons.add(wdon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dons;
	}

	// une fonction qui retourne un arraylist de tous les dons
	public static TabDyn readAllDonTabDyn(TabDyn montab) {
		Connection cn = null;
		ResultSet rs = null; // objet de type tableau
		Don wdon;
		ArrayList<Don> dons = new ArrayList<Don>(); // tableau des etudiants
		try {
			new Dbconnexion();
			cn = Dbconnexion.getConnectionDb();

			String query = " select * from don";
			PreparedStatement pmt = cn.prepareStatement(query);
			rs = pmt.executeQuery();
			montab.InitialiserJTable();
			// Parcourir le tableau qui contient les données
			while (rs.next()) {
				montab.AjouterSousItem(0, "" + rs.getInt(1));
				montab.AjouterSousItem(1, rs.getString(2));
				montab.AjouterSousItem(2, "" + rs.getBoolean(3));
				montab.AjouterSousItem(3, "" + rs.getFloat(4));
				montab.AjouterSousItem(4, rs.getString(5));
				montab.AjouterSousItem(5, "" + rs.getDate(6));
				montab.AjouterSousItem(6, "" + rs.getInt(7));
				montab.AjouterSousItem(7, "" + rs.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return montab;
	}

	// une fonction qui retourne un arraylist de tous les dons d'une association
	public static TabDyn readDonsAsso(TabDyn montab, int assoId) {
		Connection cn = null;
		ResultSet rs = null; // objet de type tableau
		Don wdon;
		ArrayList<Don> dons = new ArrayList<Don>(); // tableau des etudiants
		try {
			new Dbconnexion();
			cn = Dbconnexion.getConnectionDb();

			String query = " select * from don, recoit where don.Num_Don = recoit.Num_Don and recoit.Num_Asso = (?)";
			PreparedStatement pmt = cn.prepareStatement(query);
			pmt.setInt(1, assoId);
			rs = pmt.executeQuery();

			montab.InitialiserJTable();

			// Parcourir le tableau qui contient les données
			while (rs.next()) {
				montab.AjouterSousItem(0, "" + rs.getInt(1));
				montab.AjouterSousItem(1, rs.getString(2));
				montab.AjouterSousItem(2, "" + rs.getBoolean(3));
				montab.AjouterSousItem(3, "" + rs.getInt(4));
				montab.AjouterSousItem(4, rs.getString(5));
				montab.AjouterSousItem(5, rs.getString(6));
				montab.AjouterSousItem(6, "" + rs.getInt(7));
				montab.AjouterSousItem(7, "" + rs.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return montab;
	}

}
