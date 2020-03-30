package com.assosoft.model;

import java.time.LocalDate;

public abstract class Personne {
	private int Num_Personne;
	private String Nom;
	private String Prenom;
	private String Rue;
	private String Ville;
	private int Code_Postal;
	private String Mail;
	private LocalDate Date_Inscription;
	private String Password;
	
	public Personne() {
		
	}

	public Personne(int Num_Personne, String Nom, String Prenom, String Rue, String Ville, int Code_Postal, String Mail,
			LocalDate Date_Inscription, String Password) {
		setNum_Personne(Num_Personne);
		setNom(Nom);
		setPrenom(Prenom);
		setRue(Rue);
		setVille(Ville);
		setCode_Postal(Code_Postal);
		setMail(Mail);
		setDate_Inscription(Date_Inscription);
		setPassword(Password);
	}

	public Personne(String Nom, String Prenom, String Rue, String Ville, int Code_Postal, String Mail,
			LocalDate Date_Inscription, String Password) {
		setNom(Nom);
		setPrenom(Prenom);
		setRue(Rue);
		setVille(Ville);
		setCode_Postal(Code_Postal);
		setMail(Mail);
		setDate_Inscription(Date_Inscription);
		setPassword(Password);
	}

	@Override
	public String toString() {
		return "Personne [Num_Personne=" + Num_Personne + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Rue=" + Rue
				+ ", Ville=" + Ville + ", Code_Postal=" + Code_Postal + ", Mail=" + Mail + ", Date_Inscription="
				+ Date_Inscription + ", Password=" + Password + "]";
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public int getNum_Personne() {
		return Num_Personne;
	}

	public void setNum_Personne(int num_Personne) {
		Num_Personne = num_Personne;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getRue() {
		return Rue;
	}

	public void setRue(String rue) {
		Rue = rue;
	}

	public String getVille() {
		return Ville;
	}

	public void setVille(String ville) {
		Ville = ville;
	}

	public int getCode_Postal() {
		return Code_Postal;
	}

	public void setCode_Postal(int code_Postal) {
		Code_Postal = code_Postal;
	}

	public String getMail() {
		return Mail;
	}

	public void setMail(String mail) {
		Mail = mail;
	}

	public LocalDate getDate_Inscription() {
		return Date_Inscription;
	}

	public void setDate_Inscription(LocalDate date_Inscription) {
		Date_Inscription = date_Inscription;
	}

}
