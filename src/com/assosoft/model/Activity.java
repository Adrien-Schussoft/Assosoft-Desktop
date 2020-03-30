package com.assosoft.model;

import java.time.LocalDate;

public class Activity {
	private int Num_Activite;
	private String Nom;
	private LocalDate Date_Debut;
	private LocalDate Date_Fin;
	private float Budget;
	private float Cout;
	private boolean Convocation;
	private String Compte_Rendu;
	private int Num_Asso;
	
	public Activity() {}
	
	public Activity(String Nom, LocalDate Date_Debut, LocalDate Date_Fin,float Budget,float Cout,boolean Convocation,String Compte_Rendu,int Num_Asso)
	{
		setNom(Nom);
		setDate_Debut(Date_Debut);
		setDate_Fin(Date_Fin);
		setBudget(Budget);
		setCout(Cout);
		setConvocation(Convocation);
		setCompte_Rendu(Compte_Rendu);
		setNum_Asso(Num_Asso);
	}
	
	public Activity(int Num_Activite, String Nom, LocalDate Date_Debut, LocalDate Date_Fin,float Budget,float Cout,boolean Convocation,String Compte_Rendu,int Num_Asso)
	{
		setNum_Activite(Num_Activite);
		setNom(Nom);
		setDate_Debut(Date_Debut);
		setDate_Fin(Date_Fin);
		setBudget(Budget);
		setCout(Cout);
		setConvocation(Convocation);
		setCompte_Rendu(Compte_Rendu);
		setNum_Asso(Num_Asso);
	}

	public int getNum_Activite() {
		return Num_Activite;
	}

	public void setNum_Activite(int num_Activite) {
		Num_Activite = num_Activite;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public LocalDate getDate_Debut() {
		return Date_Debut;
	}

	public void setDate_Debut(LocalDate date_Debut) {
		Date_Debut = date_Debut;
	}

	public LocalDate getDate_Fin() {
		return Date_Fin;
	}

	public void setDate_Fin(LocalDate date_Fin) {
		Date_Fin = date_Fin;
	}

	public float getBudget() {
		return Budget;
	}

	public void setBudget(float budget) {
		Budget = budget;
	}

	public float getCout() {
		return Cout;
	}

	public void setCout(float cout) {
		Cout = cout;
	}

	public boolean isConvocation() {
		return Convocation;
	}

	public void setConvocation(boolean convocation) {
		Convocation = convocation;
	}

	public String getCompte_Rendu() {
		return Compte_Rendu;
	}

	public void setCompte_Rendu(String compte_Rendu) {
		Compte_Rendu = compte_Rendu;
	}

	public int getNum_Asso() {
		return Num_Asso;
	}

	public void setNum_Asso(int num_Asso) {
		Num_Asso = num_Asso;
	}
}

