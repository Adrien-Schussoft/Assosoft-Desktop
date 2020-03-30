package com.assosoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import com.assosoft.dao.ActivityDAO;
import com.assosoft.dao.AssoDAO;
import com.assosoft.dao.MainDAO;
import com.assosoft.dao.ServiceDAO;
import com.assosoft.model.Activity;
import com.assosoft.model.Service;
import com.assosoft.view.TabActivityView.addForm;
import com.assosoft.view.TabActivityView.updateForm;

public class TabServicesView extends JPanel {

	private String assoSelected;

	public String getAssoSelected() {
		return assoSelected;
	}

	public void setAssoSelected(String assoName) {
		this.assoSelected = assoName;
	}

	private JLabel lblNumService = new JLabel("Nom");
	private JTextField txtNumService = new JTextField(15);
	private JLabel lblNom = new JLabel("Nom");
	private JTextField txtNom = new JTextField(15);
	private JLabel lblNumAsso = new JLabel("Num Asso");
	private JTextField txtNumAsso = new JTextField(15);

	TabDyn nouvtab;

	private JPanel formPanel = new JPanel();

	// JPanel that contains the JButton for crud operations
	private final JPanel crudPanel = new JPanel();

	private JButton btnAdd = new JButton("Ajouter");
	private JButton btnUpdate = new JButton("Modifier");
	private JButton btnDelete = new JButton("Supprimer");
	private JButton cmdAfficher = new JButton("Afficher");

	private Service service;

	public TabServicesView(String pAssoSelected) {

		assoSelected = pAssoSelected;

		/*
		 **********************************************************************
		 * TAB 2 SERVICES
		 **********************************************************************
		 */

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		JPanel nouvpanel = new JPanel();
		nouvtab = new TabDyn(nouvpanel);
		nouvtab.AjouterColonne("Num_Service");
		nouvtab.AjouterColonne("Nom");
		nouvtab.AjouterColonne("Num_Asso");
		fillTable(this.assoSelected);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 10;
		gc.weighty = 10;
		gc.fill = GridBagConstraints.BOTH;
		add(nouvpanel, gc);

		Border border = BorderFactory.createLineBorder(Color.RED);

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

		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(crudPanel, gc);

		// Listener + Action
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clearFormPanel(txtNom);

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
					JOptionPane.showMessageDialog(null, "Veuillez sélectionnez une ligne pour modifier.", "Information",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					int n = JOptionPane.showConfirmDialog(null, "Suppression ?", "Validation",
							JOptionPane.YES_NO_OPTION);
					System.out.println(n);
					if (n == 0) {
						ServiceDAO.delService(Integer.parseInt(txtNumService.getText()));
					}

					clearFormPanel(txtNom);

					fillTable(assoSelected);

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

		/*
		 * Events Listener
		 */
		nouvtab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				if (e.getClickCount() == 1) {
					final JTable jTable = (JTable) e.getSource();
					final int row = jTable.getSelectedRow();

					final String valueInCell = (String) jTable.getValueAt(row, 0);
					final String valueInCell1 = (String) jTable.getValueAt(row, 1);
					final String valueInCell2 = (String) jTable.getValueAt(row, 2);

					txtNumService.setText("" + valueInCell);
					txtNom.setText("" + valueInCell1);
					txtNumAsso.setText(valueInCell2);
				}
			};
		});

	}

	public void fillTable(String assoName) {
		int assoId = AssoDAO.readAssocNom(assoName);
		nouvtab = ServiceDAO.readServicesByAssoId(nouvtab, assoId);
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
			addChildPanel(formPanel, gbc, lblNom, txtNom/* , lblNumAsso, txtNumAsso */);

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

			pnDialog.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			return pnDialog;
		}

		private void btAcceptAction(java.awt.event.ActionEvent evt) {

			boolean valid = MainDAO.validateForm(txtNom);

			if (valid) {
				service = new Service(txtNom.getText(), AssoDAO.readAssocNom(assoSelected));

				ServiceDAO.addService(service);

				clearFormPanel(txtNom);

				fillTable(assoSelected);

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
			addChildPanel(formPanel, gbc, lblNom, txtNom/* , lblNumAsso, txtNumAsso */);

			int row = nouvtab.getSelectedRow();
			String valueInCell = "" + nouvtab.getValueAt(row, 0);
			String valueInCell1 = (String) nouvtab.getValueAt(row, 1);
			String valueInCell2 = (String) nouvtab.getValueAt(row, 2);
//			String valueInCell3 = (String) nouvtab.getValueAt(row, 3);
//			String valueInCell4 = (String) nouvtab.getValueAt(row, 4);
//			String valueInCell5 = "" + nouvtab.getValueAt(row, 5);
//
//			String valueInCell6 = (String) nouvtab.getValueAt(row, 6);
//			String valueInCell7 = (String) nouvtab.getValueAt(row, 7);
//			String valueInCell8 = (String) nouvtab.getValueAt(row, 8);

			txtNom.setText("" + valueInCell1);

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

			pnDialog.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			return pnDialog;
		}

		private void btAcceptAction(java.awt.event.ActionEvent evt) {

			boolean valid = MainDAO.validateForm(txtNom);

			if (valid) {
				service = new Service(Integer.parseInt(txtNumService.getText()), txtNom.getText(),
						AssoDAO.readAssocNom(assoSelected));

				ServiceDAO.updService(service);

				fillTable(assoSelected);

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
