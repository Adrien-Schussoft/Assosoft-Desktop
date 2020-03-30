package com.assosoft.model;

public class Service {

	private int Num_Service;
	private String Nom;
	private int Num_Asso;
	
	public Service(int Num_Service, String Nom, int Num_Asso)
	{
		setNum_Service(Num_Service);
		setNom(Nom);
		setNum_Asso(Num_Asso);
	}
	
	public Service(String Nom, int Num_Asso)
	{
		setNom(Nom);
		setNum_Asso(Num_Asso);
	}

	
	public int getNum_Service() {
		return Num_Service;
	}

	public void setNum_Service(int num_Service) {
		Num_Service = num_Service;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public int getNum_Asso() {
		return Num_Asso;
	}

	public void setNum_Asso(int num_Asso) {
		Num_Asso = num_Asso;
	}

}
