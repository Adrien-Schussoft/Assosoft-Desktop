package com.assosoft.model;

import java.time.LocalDate;

public class Donateur extends Personne {

	
	public Donateur(int Num_Personne, String Nom, String Prenom, String Rue, String Ville,int Code_Postal,String Mail, LocalDate Date_Inscription,String Password)
	{
		super(Num_Personne, Nom, Prenom, Rue, Ville, Code_Postal, Mail, Date_Inscription, Password);
	}

}
