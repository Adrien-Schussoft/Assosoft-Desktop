package com.assosoft.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class TabDyn extends JTable {
	
	private static final long serialVersionUID = 1L;
	int NvlleTaille;
	int LongueurColonne = 0;
	JPanel ConteneurJTable;
	Object[][] Donnees;
	String[] NomColonne = new String[0];
	DefaultTableModel Model;
	JScrollPane jScroll1 = new JScrollPane();

	public TabDyn(JPanel p) {
		ConteneurJTable = p;
		ConteneurJTable.setLayout(new BorderLayout());
		this.InitialiserJTable();
	}

	public void AjouterColonne(String Colonne) {
		LongueurColonne++;
		int r = 0;
		String[] ColonneGarde;
		if (LongueurColonne >= 2) {
			r = LongueurColonne - 1;
			ColonneGarde = new String[r];
			ColonneGarde = NomColonne;
			NomColonne = new String[LongueurColonne];
			for (int i = 0; i < r; i++) {
				NomColonne[i] = ColonneGarde[i];
			}
			NomColonne[r] = Colonne;
		} else {
			r = LongueurColonne - 1;
			NomColonne = new String[LongueurColonne];
			NomColonne[r] = Colonne;
		}
	}

	public void AjouterLigne() {
		NvlleTaille = Donnees.length + 1;
		Object[][] DonneesGarde = new Object[Donnees.length][3];
		DonneesGarde = Donnees;
		Donnees = new Object[NvlleTaille][LongueurColonne];
		for (int i = 0; i < NvlleTaille - 1; i++) {
			for (int j = 0; j < LongueurColonne; j++) {
				Donnees[i][j] = DonneesGarde[i][j];
			}
		}
	}

	public void SupprimerLigneSelect() {
		int Position = getSelectedRow();
		if (Donnees.length == 0) {
			return;
		}
		NvlleTaille = Donnees.length - 1;
		Object[][] DonneesGarde = new Object[Donnees.length][3];
		DonneesGarde = Donnees;
		Donnees = new Object[NvlleTaille][LongueurColonne];
		for (int i = 0; i < Position; i++) {
			for (int j = 0; j < LongueurColonne; j++) {
				Donnees[i][j] = DonneesGarde[i][j];
			}
		}
		for (int i = Position + 1; i < NvlleTaille + 1; i++) {
			for (int j = 0; j < LongueurColonne; j++) {
				Donnees[i-1][j] = DonneesGarde[i][j];
			}
		}
		ActualiserTable();
	}

	public void SupprimerLigne() {
		if (Donnees.length == 0) {
			return;
		}
		NvlleTaille = Donnees.length - 1;
		Object[][] DonneesGarde = new Object[Donnees.length][3];
		DonneesGarde = Donnees;
		Donnees = new Object[NvlleTaille][LongueurColonne];
		for (int i = 0; i < NvlleTaille; i++) {
			for (int j = 0; j < LongueurColonne; j++) {
				Donnees[i][j] = DonneesGarde[i][j];
			}
		}
		ActualiserTable();
	}

	public void AjouterSousItem(int y, String a) {
		if (y == 0) {
			AjouterLigne();
		}
		Donnees[NvlleTaille - 1][y] = a;
		ActualiserTable();
	}

	public void EffacerJTable() {
		int i = getRowCount() - 1;
		while (i >= 0) {  
			for (int j = 0; j < LongueurColonne; j++) {
				setValueAt("", i, j);
			}
			i--;
		}
		Donnees = new Object[getRowCount()][LongueurColonne];
	}

	public void ActualiserTable() {
		ConteneurJTable.remove(jScroll1);
		ConteneurJTable.remove(getTableHeader());
		ConteneurJTable.revalidate();
		Model.fireTableDataChanged();
		Model = new DefaultTableModel(Donnees, NomColonne);
		setModel(Model);
		jScroll1.setViewportView(this);
		ConteneurJTable.add(BorderLayout.CENTER, jScroll1);
		ConteneurJTable.repaint();
	}


	public void InitialiserJTable() {
		Donnees = new Object[0][0];
		Model = new DefaultTableModel(Donnees, NomColonne);
		setModel(Model);
		jScroll1.setViewportView(this);
		ConteneurJTable.add(BorderLayout.CENTER, jScroll1);
		JTableHeader jTableHeader = this.getTableHeader();
		jTableHeader.setForeground(new Color(255, 255, 204));
		jTableHeader.setBackground(new Color(255, 153, 0));
		jTableHeader.setFont(jTableHeader.getFont().deriveFont(20.0f));
	}
}
