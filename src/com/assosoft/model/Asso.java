package com.assosoft.model;

public class Asso {

	private int Num_Asso;
	private String Nom;
	private String Rue;
	private String Ville;
	private int Code_Postal;
	private String Logo;
	private boolean Actif;
	
	public Asso(int Num_Asso, String Nom, String Rue, String Ville,int Code_Postal,String Logo,boolean Actif)
	{
		setNum_Asso(Num_Asso);
		setNom(Nom);
		setRue(Rue);
		setVille(Ville);
		setCode_Postal(Code_Postal);
		setLogo(Logo);
		setActif(Actif);
	}

	public Asso() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Assoc [Num_Asso=" + Num_Asso + ", Nom=" + Nom + ", Rue=" + Rue + ", Ville=" + Ville + ", Code_Postal="
				+ Code_Postal + ", Logo=" + Logo + ", Actif=" + Actif + "]";
	}

	public int getNum_Asso() {
		return Num_Asso;
	}

	public void setNum_Asso(int num_Asso) {
		Num_Asso = num_Asso;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
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

	public String getLogo() {
		return Logo;
	}

	public void setLogo(String logo) {
		Logo = logo;
	}

	public boolean isActif() {
		return Actif;
	}

	public void setActif(boolean actif) {
		Actif = actif;
	}


}
