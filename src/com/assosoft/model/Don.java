package com.assosoft.model;

import java.time.LocalDate;

public class Don {


	private int Num_Don;
	private String Type;
	private boolean Etat;
	private float Montant;
	private String Contenu;
	private LocalDate Date_Don;
	private int Num_Donateur;
	private int Num_User;
		
	public Don(int Num_Don, String Type, boolean Etat, float Montant, String Contenu, LocalDate Date_Don, int Num_Donateur, int Num_User)
	{
		setNum_Don(Num_Don);
		setType(Type);
		setEtat(Etat);
		setMontant(Montant);
		setContenu(Contenu);
		setDate_Don(Date_Don);
		setNum_Donateur(Num_Donateur);
		setNum_User(Num_User);
	}
	
	public Don(String Type, boolean Etat, float Montant, String Contenu, LocalDate Date_Don, int Num_Donateur, int Num_User)
	{
		setType(Type);
		setEtat(Etat);
		setMontant(Montant);
		setContenu(Contenu);
		setDate_Don(Date_Don);
		setNum_Donateur(Num_Donateur);
		setNum_User(Num_User);
	}

	public LocalDate getDate_Don() {
		return Date_Don;
	}

	public void setDate_Don(LocalDate date_Don) {
		Date_Don = date_Don;
	}

	public int getNum_Don() {
		return Num_Don;
	}

	public void setNum_Don(int num_Don) {
		Num_Don = num_Don;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public boolean isEtat() {
		return Etat;
	}

	public void setEtat(boolean etat) {
		Etat = etat;
	}

	public float getMontant() {
		return Montant;
	}

	public void setMontant(float montant) {
		Montant = montant;
	}

	public String getContenu() {
		return Contenu;
	}

	public void setContenu(String contenu) {
		Contenu = contenu;
	}

	public int getNum_Donateur() {
		return Num_Donateur;
	}

	public void setNum_Donateur(int num_Donateur) {
		Num_Donateur = num_Donateur;
	}

	public int getNum_User() {
		return Num_User;
	}

	public void setNum_User(int num_User) {
		Num_User = num_User;
	}

}
