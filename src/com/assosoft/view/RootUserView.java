package com.assosoft.view;

import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.time.LocalDate;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.assosoft.dao.ActivityDAO;
import com.assosoft.dao.AssoDAO;
import com.assosoft.dao.MainDAO;
import com.assosoft.dao.UserDAO;
import com.assosoft.model.Activity;
import com.assosoft.model.User;
import com.assosoft.utils.CustomSearchField;
import com.assosoft.utils.RowFilterUtil;
import com.assosoft.view.TabActivityView.addForm;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class RootUserView extends JPanel {

	private JPanel formPanel = new JPanel();

	private static final long serialVersionUID = 1L;
	private JLabel lblNom = new JLabel("Nom");
	private JTextField txtNom = new JTextField(15);
	private JLabel lblPrenom = new JLabel("Prenom");
	private JTextField txtPrenom = new JTextField(15);
	private JLabel lblRue = new JLabel("Rue");
	private JTextField txtRue = new JTextField(15);
	private JLabel lblVille = new JLabel("Ville");
	private JTextField txtVille = new JTextField(15);
	private JLabel lblCodePostal = new JLabel("CodePostal");
	private JTextField txtCodePostal = new JTextField(15);
	private JLabel lblMail = new JLabel("Mail");
	private JTextField txtMail = new JTextField(15);
	private JLabel lblDateInscription = new JLabel("Date Inscription");
	private JTextField txtDateInscription = new JTextField(15);
	private JLabel lblPassword = new JLabel("Password");
	private JTextField txtPassword = new JTextField(15);
	private JLabel lblActif = new JLabel("Actif");
	private JTextField txtActif = new JTextField(15);
	private JLabel lblRoot = new JLabel("Root");
	private JTextField txtRoot = new JTextField(15);
	private JLabel lblContact = new JLabel("Contact");
	private JTextField txtContact = new JTextField(15);
	private JLabel lblNumUser = new JLabel("NumUser");
	private JTextField txtNumUser = new JTextField(5);

	private JButton btnAdd = new JButton("Ajouter");
	private JButton btnUpdate = new JButton("Modifier");
	private JButton btnDelete = new JButton("Supprimer");
	private CustomSearchField filterField;

	private User user;

	TabDyn nouvtab;

	// JPanel that contains the JButton for crud operations
	private final JPanel crudPanel = new JPanel();

	// Constructeur
	public RootUserView() {

		Border border = BorderFactory.createLineBorder(Color.BLACK);

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		JPanel nouvpanel = new JPanel();
		nouvtab = new TabDyn(nouvpanel);
		nouvtab.AjouterColonne("Num_User");
		nouvtab.AjouterColonne("Nom");
		nouvtab.AjouterColonne("Prenom");
		nouvtab.AjouterColonne("Rue");
		nouvtab.AjouterColonne("Ville");
		nouvtab.AjouterColonne("Code_Postal");
		nouvtab.AjouterColonne("Mail");
		nouvtab.AjouterColonne("Date");
		nouvtab.AjouterColonne("Password");
		nouvtab.AjouterColonne("Actif");
		nouvtab.AjouterColonne("Root");
		nouvtab.AjouterColonne("Contact");
		nouvtab = UserDAO.afftousUser(nouvtab);

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

		createSearchField();

		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(crudPanel, gc);

		// Listener + Action
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clearFormPanel(txtNom, txtPrenom, txtRue, txtVille, txtCodePostal, txtMail, txtDateInscription,
						txtPassword, txtActif, txtRoot, txtContact, txtNumUser);

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
					user = new User(Integer.parseInt(txtNumUser.getText()), "", "", "", "", 0, "",
							LocalDate.of(1980, 1, 1), "", false, false, false);

					int n = JOptionPane.showConfirmDialog(null, "Confirmer la suppression ?", "Validation",
							JOptionPane.YES_NO_OPTION);
					if (n == 0) {

						UserDAO.delUser(user);
					}
					nouvtab = UserDAO.afftousUser(nouvtab);
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

					dialog.setTitle("Modifier");
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
					final String valueInCell4 = (String) jTable.getValueAt(row, 4);
					final String valueInCell5 = "" + jTable.getValueAt(row, 5);

					final String valueInCell6 = (String) jTable.getValueAt(row, 6);
					final String valueInCell7 = (String) jTable.getValueAt(row, 7);
					final String valueInCell8 = (String) jTable.getValueAt(row, 8);
					final String valueInCell9 = "" + jTable.getValueAt(row, 9);
					final String valueInCell10 = "" + jTable.getValueAt(row, 10);
					final String valueInCell11 = (String) jTable.getValueAt(row, 11);

					txtNom.setText("" + valueInCell1);
					txtPrenom.setText("" + valueInCell2);

					txtRue.setText(valueInCell3);
					txtVille.setText(valueInCell4);
					txtCodePostal.setText(valueInCell5);
					txtMail.setText(valueInCell6);
					txtDateInscription.setText(valueInCell7);
					txtPassword.setText(valueInCell8);
					txtActif.setText(valueInCell9);
					txtRoot.setText(valueInCell10);
					txtContact.setText(valueInCell11);
					txtNumUser.setText(valueInCell);
				}
			};
		});

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
			addChildPanel(pnDialog, gbc, lblNom, txtNom, lblPrenom, txtPrenom, lblRue, txtRue, lblVille, txtVille,
					lblCodePostal, txtCodePostal, lblMail, txtMail, lblDateInscription, txtDateInscription, lblPassword,
					txtPassword, lblActif, txtActif, lblRoot, txtRoot, lblContact, txtContact);

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
			gbc.gridy = 22;
			gbc.weighty = 1;
			gbc.anchor = GridBagConstraints.SOUTH;
			pnDialog.add(pnButtons, gbc);

			pnDialog.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

			return pnDialog;
		}

		private void btAcceptAction(java.awt.event.ActionEvent evt) {

			boolean valid = false;

			valid = MainDAO.validateForm(txtNom, txtPrenom, txtRue, txtVille, txtCodePostal, txtMail,
					txtDateInscription, txtPassword, txtActif, txtRoot, txtContact);

			if (valid) {

				boolean bActif;
				boolean bRoot;
				boolean bContact;

				if (txtActif.getText().equals("true")) {
					bActif = true;
				} else {
					bActif = false;
				}

				if (txtRoot.getText().equals("true")) {
					bRoot = true;
				} else {
					bRoot = false;
				}
				
				if (txtContact.getText().equals("true")) {
					bContact = true;
				} else {
					bContact = false;
				}

				user = new User(0, txtNom.getText(), txtPrenom.getText(), txtRue.getText(), txtVille.getText(),
						Integer.parseInt(txtCodePostal.getText()), txtMail.getText(),
						MainDAO.convdate(txtDateInscription.getText()), txtPassword.getText(), bActif, bRoot, bContact);

				UserDAO.addUser(user);

				nouvtab = UserDAO.afftousUser(nouvtab);
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
			addChildPanel(pnDialog, gbc, lblNom, txtNom, lblPrenom, txtPrenom, lblRue, txtRue, lblVille, txtVille,
					lblCodePostal, txtCodePostal, lblMail, txtMail, lblDateInscription, txtDateInscription, lblPassword,
					txtPassword, lblActif, txtActif, lblRoot, txtRoot, lblContact, txtContact);

			int row = nouvtab.getSelectedRow();
			final String valueInCell = "" + nouvtab.getValueAt(row, 0);
			final String valueInCell1 = (String) nouvtab.getValueAt(row, 1);
			final String valueInCell2 = (String) nouvtab.getValueAt(row, 2);
			final String valueInCell3 = (String) nouvtab.getValueAt(row, 3);
			final String valueInCell4 = (String) nouvtab.getValueAt(row, 4);
			final String valueInCell5 = "" + nouvtab.getValueAt(row, 5);

			final String valueInCell6 = (String) nouvtab.getValueAt(row, 6);
			final String valueInCell7 = (String) nouvtab.getValueAt(row, 7);
			final String valueInCell8 = (String) nouvtab.getValueAt(row, 8);
			final String valueInCell9 = "" + nouvtab.getValueAt(row, 9);
			final String valueInCell10 = "" + nouvtab.getValueAt(row, 10);
			final String valueInCell11 = (String) nouvtab.getValueAt(row, 11);

			txtNom.setText("" + valueInCell1);
			txtPrenom.setText("" + valueInCell2);
			txtRue.setText(valueInCell3);
			txtVille.setText(valueInCell4);
			txtCodePostal.setText(valueInCell5);
			txtMail.setText(valueInCell6);
			txtDateInscription.setText(valueInCell7);
			txtPassword.setText(valueInCell8);
			txtActif.setText(valueInCell9);
			txtRoot.setText(valueInCell10);
			txtContact.setText(valueInCell11);
			txtNumUser.setText(valueInCell);

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
			gbc.gridy = 22;
			gbc.weighty = 1;
			gbc.anchor = GridBagConstraints.SOUTH;
			pnDialog.add(pnButtons, gbc);

			pnDialog.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

			return pnDialog;
		}

		private void btAcceptAction(java.awt.event.ActionEvent evt) {

			boolean valid = MainDAO.validateForm(txtNom, txtPrenom, txtRue, txtVille, txtCodePostal, txtMail,
					txtDateInscription, txtPassword, txtActif, txtRoot, txtContact, txtNumUser);

			if (valid) {

				//TODO replace code below with combobox true or false
				boolean bActif;
				boolean bRoot;
				boolean bContact;

				if (txtActif.getText().equals("true")) {
					bActif = true;
				} else {
					bActif = false;
				}

				if (txtRoot.getText().equals("true")) {
					bRoot = true;
				} else {
					bRoot = false;
				}
				
				if (txtContact.getText().equals("true")) {
					bContact = true;
				} else {
					bContact = false;
				}

				user = new User(Integer.parseInt(txtNumUser.getText()), txtNom.getText(), txtPrenom.getText(),
						txtRue.getText(), txtVille.getText(), Integer.parseInt(txtCodePostal.getText()),
						txtMail.getText(), MainDAO.convdate(txtDateInscription.getText()), txtPassword.getText(),
						bActif, bRoot, bContact);
				UserDAO.updUser(user);
				nouvtab = UserDAO.afftousUser(nouvtab);
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
