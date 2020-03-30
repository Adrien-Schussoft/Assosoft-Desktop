	package com.assosoft.view;

import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.assosoft.dao.ActivityDAO;
import com.assosoft.dao.AssoDAO;
import com.assosoft.dao.DonDAO;
import com.assosoft.dao.MainDAO;
import com.assosoft.model.Activity;
import com.assosoft.model.Asso;
import com.assosoft.utils.CustomSearchField;
import com.assosoft.utils.RowFilterUtil;
import com.assosoft.view.RootUserView.addForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class RootDonView extends JPanel {

	private JPanel formPanel = new JPanel();

	private static final long serialVersionUID = 1L;
	private JLabel lblNom = new JLabel("Nom");
	private JTextField txtNom = new JTextField(15);
	private JLabel lblRue = new JLabel("Rue");
	private JTextField txtRue = new JTextField(15);
	private JLabel lblVille = new JLabel("Ville");
	private JTextField txtVille = new JTextField(15);
	private JLabel lblCodePostal = new JLabel("Code Postal");
	private JTextField txtCodePostal = new JTextField(15);
	private JLabel lblLogo = new JLabel("Logo");
	private JTextField txtLogo = new JTextField(15);
	private JLabel lblActif = new JLabel("Actif");
	private JTextField txtActif = new JTextField(15);
	private JLabel lblNumAsso = new JLabel("NumAsso");
	private JTextField txtNumAsso = new JTextField(5);

	private JPanel crudPanel = new JPanel();
	private JButton btnAdd = new JButton("Ajouter");
	private JButton btnUpdate = new JButton("Modifier");
	private JButton btnDelete = new JButton("Supprimer");
	private JButton cmdAffTout = new JButton("Afficher Tout");
	private JButton cmdAfficher = new JButton("Afficher");
	private CustomSearchField filterField;

	private Asso association;

	TabDyn nouvtab;

	public RootDonView() {

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		JPanel nouvpanel = new JPanel();
		nouvtab = new TabDyn(nouvpanel);
		nouvtab.AjouterColonne("Num Don");
		nouvtab.AjouterColonne("Type");
		nouvtab.AjouterColonne("Etat");
		nouvtab.AjouterColonne("Montant");
		nouvtab.AjouterColonne("Contenu");
		nouvtab.AjouterColonne("Date Don");
		nouvtab.AjouterColonne("Num Donateur");
		nouvtab.AjouterColonne("Num User");
		nouvtab = DonDAO.readAllDonTabDyn(nouvtab);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 10;
		gc.weighty = 10;
		gc.fill = GridBagConstraints.BOTH;
		add(nouvpanel, gc);

		/*
		 * CRUD Buttons
		 */
		ImageIcon iconAdd = createImageIcon("/img/btnAdd.png");
		ImageIcon iconUpdate = createImageIcon("/img/btnUpdate.png");
		ImageIcon iconDelete = createImageIcon("/img/btnDelete.png");
		ImageIcon iconSearch = createImageIcon("/img/search.png");

		btnAdd.setIcon(iconAdd);
		btnUpdate.setIcon(iconUpdate);
		btnDelete.setIcon(iconDelete);

		btnAdd.setBackground(new Color(51, 153, 255));
		btnAdd.setForeground(Color.WHITE);
		btnUpdate.setBackground(new Color(0, 153, 0));
		btnUpdate.setForeground(Color.WHITE);
		btnDelete.setBackground(new Color(255, 51, 51));
		btnDelete.setForeground(Color.WHITE);

		crudPanel.add(btnAdd);
		crudPanel.add(btnUpdate);
		crudPanel.add(btnDelete);

		filterField = RowFilterUtil.createRowFilter(nouvtab);
		filterField.setIcon(iconSearch);
		filterField.setFont(filterField.getFont().deriveFont(25.0f));
		crudPanel.add(filterField);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(crudPanel, gc);

		// Listener + Action
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clearFormPanel(txtNom, txtRue, txtVille, txtCodePostal, txtLogo, txtActif);

				JDialog dialog = new JDialog();

				addForm tp = new addForm();
				dialog.add(tp.createPanel());

				dialog.setTitle("Ajouter");
				dialog.setModal(true);
				dialog.pack();
				centerWindow(dialog);
				dialog.setVisible(true);

			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (nouvtab.getSelectedRow() == -1) {

					JOptionPane.showMessageDialog(null, "Veuillez sélectionnez une ligne pour supprimer.",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				} else {
					int n = JOptionPane.showConfirmDialog(null, "Confirmer la suppression ?", "Validation",
							JOptionPane.YES_NO_OPTION);
					if (n == 0) {
						association = new Asso(Integer.parseInt(txtNumAsso.getText()), "", "", "", 0, "", false);
						AssoDAO.delAssoc(association);
					}
					nouvtab = AssoDAO.afftousAssoc(nouvtab);
					crudPanel.remove(filterField);
					createSearchField();
				}
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (nouvtab.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionnez une ligne pour modifier.", "Information",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					JDialog dialog = new JDialog();

					updateForm tp = new updateForm();
					dialog.add(tp.createPanel());

					dialog.setTitle("Ajouter");
					dialog.setModal(true);
					dialog.pack();
					centerWindow(dialog);
					dialog.setVisible(true);
				}

			}
		});

		nouvtab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				if (e.getClickCount() == 1) {
					final JTable jTable = (JTable) e.getSource();
					final int row = jTable.getSelectedRow();
					final String valueInCell = "" + jTable.getValueAt(row, 0);
					final String valueInCell1 = (String) jTable.getValueAt(row, 1);
					final String valueInCell2 = (String) jTable.getValueAt(row, 2);
					final String valueInCell3 = (String) jTable.getValueAt(row, 3);
					final String valueInCell4 = "" + jTable.getValueAt(row, 4);
					final String valueInCell5 = (String) jTable.getValueAt(row, 5);
					final String valueInCell6 = "" + jTable.getValueAt(row, 6);
					txtNom.setText("" + valueInCell1);
					txtRue.setText(valueInCell2);
					txtVille.setText(valueInCell3);
					txtCodePostal.setText(valueInCell4);
					txtLogo.setText(valueInCell5);
					txtActif.setText(valueInCell6);
					txtNumAsso.setText("" + valueInCell);
				}
			};
		});

		this.setVisible(true);

	}

	private void createSearchField() {
		ImageIcon iconSearch = createImageIcon("/img/search.png");
		filterField = RowFilterUtil.createRowFilter(nouvtab);
		filterField.setIcon(iconSearch);
		filterField.setFont(filterField.getFont().deriveFont(25.0f));
		crudPanel.add(filterField);
	}

	public void clearFormPanel(JTextField... textFields) {

		for (JTextField textField : textFields) {
			textField.setText("");
		}
	}

	public void addChildPanel(JPanel p, GridBagConstraints gbc, Object... childs) {
		for (Object object : childs) {
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 0;
			gbc.gridy++;
			p.add((Component) object, gbc);
		}
	}

	public void alignFields(Object... jLabels) {
		for (Object object : jLabels) {
			if (object instanceof JLabel)
				((JLabel) object).setHorizontalAlignment(SwingConstants.LEFT);
			if (object instanceof JTextField)
				((JTextField) object).setHorizontalAlignment(SwingConstants.LEFT);
		}
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MainContactView.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public static void centerWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	/*
	 * add form
	 */
	public class addForm {

		private JButton btAccept;
		private JButton btCancel;
		private JLabel lbInput;
		private JTextField tfInput;
		private JPanel pnDialog;

		private JPanel createPanel() {

			pnDialog = new JPanel();

			pnDialog.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridy = -1;
			addChildPanel(pnDialog, gbc, lblNom, txtNom, lblRue, txtRue, lblVille, txtVille, lblCodePostal,
					txtCodePostal, lblLogo, txtLogo, lblActif, txtActif);

			btAccept = new JButton();
			btCancel = new JButton();

			btAccept.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					btAcceptAction(evt);
				}
			});

			btCancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					btCancelAction(evt);
				}
			});

			JPanel pnButtons = new JPanel();

			btAccept.setText("Accept");
			btCancel.setText("Cancel");
			pnButtons.add(btAccept);
			pnButtons.add(btCancel);

			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 0;
			gbc.gridy = 16;
			gbc.weighty = 1;
			gbc.anchor = GridBagConstraints.SOUTH;
			pnDialog.add(pnButtons, gbc);

			pnDialog.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

			return pnDialog;
		}

		private void btAcceptAction(java.awt.event.ActionEvent evt) {

			boolean valid = MainDAO.validateForm(txtNom, txtRue, txtVille, txtCodePostal, txtLogo, txtActif);

			if (valid) {

				if (txtActif.getText().equals("true")) {
					association = new Asso(0, txtNom.getText(), txtRue.getText(), txtVille.getText(),
							Integer.parseInt(txtCodePostal.getText()), txtLogo.getText(), true);
				} else {
					association = new Asso(0, txtNom.getText(), txtRue.getText(), txtVille.getText(),
							Integer.parseInt(txtCodePostal.getText()), txtLogo.getText(), false);
				}

				AssoDAO.addAssoc(association);

				nouvtab = AssoDAO.afftousAssoc(nouvtab);
				crudPanel.remove(filterField);
				createSearchField();

				JDialog topFrame = (JDialog) SwingUtilities.getWindowAncestor(pnDialog);
				topFrame.dispose();

			} else {
				JOptionPane.showMessageDialog(null, "Erreur, veuillez renseigner tous les champs.");
			}

		}

		private void btCancelAction(java.awt.event.ActionEvent evt) {
			JDialog topFrame = (JDialog) SwingUtilities.getWindowAncestor(pnDialog);
			topFrame.dispose();
		}

	}

	/*
	 * update form
	 */
	public class updateForm {

		private JButton btAccept;
		private JButton btCancel;
		private JLabel lbInput;
		private JTextField tfInput;
		private JPanel pnDialog;

		private JPanel createPanel() {

			pnDialog = new JPanel();

			pnDialog.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridy = -1;
			addChildPanel(pnDialog, gbc, lblNom, txtNom, lblRue, txtRue, lblVille, txtVille, lblCodePostal,
					txtCodePostal, lblLogo, txtLogo, lblActif, txtActif);

			int row = nouvtab.getSelectedRow();
			final String valueInCell = "" + nouvtab.getValueAt(row, 0);
			final String valueInCell1 = (String) nouvtab.getValueAt(row, 1);
			final String valueInCell2 = (String) nouvtab.getValueAt(row, 2);
			final String valueInCell3 = (String) nouvtab.getValueAt(row, 3);
			final String valueInCell4 = "" + nouvtab.getValueAt(row, 4);
			final String valueInCell5 = (String) nouvtab.getValueAt(row, 5);
			final String valueInCell6 = "" + nouvtab.getValueAt(row, 6);
			txtNom.setText("" + valueInCell1);
			txtRue.setText(valueInCell2);
			txtVille.setText(valueInCell3);
			txtCodePostal.setText(valueInCell4);
			txtLogo.setText(valueInCell5);
			txtActif.setText(valueInCell6);
//			txtNumAsso.setText("" + valueInCell);

			btAccept = new JButton();
			btCancel = new JButton();

			btAccept.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					btAcceptAction(evt);
				}
			});

			btCancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					btCancelAction(evt);
				}
			});

			JPanel pnButtons = new JPanel();

			btAccept.setText("Accept");
			btCancel.setText("Cancel");
			pnButtons.add(btAccept);
			pnButtons.add(btCancel);

			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 0;
			gbc.gridy = 16;
			gbc.weighty = 1;
			gbc.anchor = GridBagConstraints.SOUTH;
			pnDialog.add(pnButtons, gbc);

			pnDialog.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

			return pnDialog;
		}

		private void btAcceptAction(java.awt.event.ActionEvent evt) {

			boolean valid = MainDAO.validateForm(txtNom, txtRue, txtVille, txtCodePostal, txtLogo, txtActif);

			if (valid) {
				if (txtActif.getText().equals("true")) {
					association = new Asso(Integer.parseInt(txtNumAsso.getText()), txtNom.getText(), txtRue.getText(),
							txtVille.getText(), Integer.parseInt(txtCodePostal.getText()), txtLogo.getText(), true);
				} else {
					association = new Asso(Integer.parseInt(txtNumAsso.getText()), txtNom.getText(), txtRue.getText(),
							txtVille.getText(), Integer.parseInt(txtCodePostal.getText()), txtLogo.getText(), false);
				}
				AssoDAO.updAssoc(association);
				nouvtab = AssoDAO.afftousAssoc(nouvtab);
				crudPanel.remove(filterField);
				createSearchField();

				JDialog topFrame = (JDialog) SwingUtilities.getWindowAncestor(pnDialog);
				topFrame.dispose();

			} else {
				JOptionPane.showMessageDialog(null, "Erreur, veuillez renseigner tous les champs.");
			}

		}

		private void btCancelAction(java.awt.event.ActionEvent evt) {
			JDialog topFrame = (JDialog) SwingUtilities.getWindowAncestor(pnDialog);
			topFrame.dispose();
		}

	}
}
