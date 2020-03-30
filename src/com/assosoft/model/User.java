package com.assosoft.model;

import java.time.LocalDate;

public class User extends Personne {
	private boolean Actif;
	private boolean Root;
	private boolean Contact;
	
	public User() {
		super();
	}

	public User(int Num_Personne, String Nom, String Prenom, String Rue, String Ville,int Code_Postal,String Mail, LocalDate Date_Inscription, String Password, boolean Actif, boolean Root, boolean Contact)
	{
		super(Num_Personne, Nom, Prenom, Rue, Ville, Code_Postal, Mail, Date_Inscription, Password);
		setActif(Actif);
		setContact(Contact);
		setRoot(Root);
		
	}
	
	public User(String Nom, String Prenom, String Rue, String Ville,int Code_Postal,String Mail, LocalDate Date_Inscription, String Password, boolean Actif, boolean Root, boolean Contact)
	{
		super(Nom, Prenom, Rue, Ville, Code_Postal, Mail, Date_Inscription, Password);
		setActif(Actif);
		setContact(Contact);
		setRoot(Root);
		
	}

	@Override
	public String toString() {
		return "User [" + super.toString() + " Actif=" + Actif + ", Root=" + Root + ", Contact=" + Contact + "]";
	}

	public boolean isActif() {
		return Actif;
	}

	public void setActif(boolean actif) {
		Actif = actif;
	}

	public boolean isRoot() {
		return Root;
	}

	public void setRoot(boolean root) {
		Root = root;
	}

	public boolean isContact() {
		return Contact;
	}

	public void setContact(boolean contact) {
		Contact = contact;
	}

	
}
