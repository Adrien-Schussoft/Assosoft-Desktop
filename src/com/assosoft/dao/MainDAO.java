package com.assosoft.dao;

import java.time.LocalDate;
import javax.swing.JTextField;


import com.assosoft.view.MainView;

public class MainDAO {

	public static void main(String[] args) {
		//MainView = new MainView();
		new MainView();
		

	}
	
	public static LocalDate convdate(String sdate) {
		String annee;
		String mois;
		String jour;
		
		annee = sdate.substring(0,4);
		mois = sdate.substring(5,7);
		jour = sdate.substring(8,10);
		return( LocalDate.of(Integer.parseInt(annee),Integer.parseInt(mois),Integer.parseInt(jour)) );
	}
	
//	public static boolean validateInscription(String nom, String prenom, String email, char[] pass, char[] passConfirm, String ville, String rue, String codePostal) {
		public static boolean validateForm(Object... arguments) {
			
		for(Object item : arguments) {
//			if (checkString(item.toString())) {
			if (checkString(((JTextField) item).getText())) {
				return false;
			}
		}
		
		// Si la boucle for s'est terminée normalement, tous les champs sont bien remplis
		return true;
	};
	
	private static boolean checkString(String str) {
		return str.isBlank();
	}
}
