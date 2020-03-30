package com.assosoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.assosoft.dao.ActivityDAO;
import com.assosoft.dao.AssoDAO;
import com.assosoft.dao.UserDAO;
import com.assosoft.model.Asso;
import com.assosoft.model.User;

public class MainContactView extends JFrame {

	private JPanel pane1;
	private JLabel lblAssoc;
	private JComboBox comboAssoc;

	private JPanel pane2;
	private JLabel lblTitleAsso;
	JPanel panebox1;
	JPanel panebox2;

	private JTabbedPane tabbedPane;
	TabActivityView tabActivity;
	TabServicesView tabService;
	TabUsersView tabUser;
	TabDonsView tabDon;

	private String assoSelected;

	private User user;

	public MainContactView(User u) {

		Border border = BorderFactory.createLineBorder(Color.RED);

		Container pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;

		/*
		 **********************************************************************
		 * PANE 1 combobox association
		 **********************************************************************
		 */

		lblAssoc = new JLabel("Sélectionnez une association : ");
		lblAssoc.setFont(new Font("Tahoma", Font.PLAIN, 14));

		ArrayList<Asso> assos = AssoDAO.touteslesassosUsers(u.getNum_Personne());
		String[] tableau = new String[assos.size()];
		int i = 0;
		for (Asso asso : assos) {
			tableau[i] = asso.getNom();

			i++;
		}

		comboAssoc = new JComboBox(tableau);
		comboAssoc.setForeground(new Color(128, 0, 0));
		comboAssoc.setFont(new Font("Tahoma", Font.PLAIN, 14));

		comboAssoc.setAlignmentX(Component.RIGHT_ALIGNMENT);
		comboAssoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcheckboxHandler(tabbedPane);
			}
		});

		pane1 = new JPanel();
		pane1.setLayout(new FlowLayout(FlowLayout.RIGHT));

		pane1.add(lblAssoc);
		pane1.add(comboAssoc);
		add(pane1, gc);

		/*
		 **********************************************************************
		 * PANE 2 Asso Title & buttons association/utilisateur
		 **********************************************************************
		 */
		
		lblTitleAsso = new JLabel("test", SwingConstants.CENTER);
		lblTitleAsso.setFont(lblTitleAsso.getFont().deriveFont(40.0f));

		JButton btnAssoc = new JButton("Page Association");
		JButton btnPerso = new JButton("Page Personnel");
		btnAssoc.setPreferredSize(new Dimension(200, 50));
		btnPerso.setPreferredSize(new Dimension(200, 50));

		pane2 = new JPanel();
		pane2.setLayout(new BoxLayout(pane2, BoxLayout.Y_AXIS));
		
		panebox1 = new JPanel();
//		panebox2 = new JPanel();

		panebox1.add(lblTitleAsso);
//		panebox2.add(btnAssoc);
//		panebox2.add(btnPerso);
		
		pane2.add(panebox1);
//		pane2.add(panebox2);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;
		add(pane2, gc);

		/*
		 **********************************************************************
		 * PANE 3 Tab Panel with JTable
		 **********************************************************************
		 */

		tabbedPane = new JTabbedPane();
//		tabbedPane.setBorder(border);

		ImageIcon icon1 = createImageIcon("/img/tab_activites.png");
		ImageIcon icon2 = createImageIcon("/img/tab_services.png");
		ImageIcon icon3 = createImageIcon("/img/tab_users.png");
		ImageIcon icon4 = createImageIcon("/img/tab_dons.png");

		tabActivity = new TabActivityView((String) comboAssoc.getSelectedItem());
		tabbedPane.addTab("Activités", icon1, tabActivity, "Afficher la liste des activités");

		tabService = new TabServicesView((String) comboAssoc.getSelectedItem());
		tabbedPane.addTab("Services", icon2, tabService, "Afficher la liste des services");

		tabUser = new TabUsersView((String) comboAssoc.getSelectedItem());
		tabbedPane.addTab("Adhérents", icon3, tabUser, "Afficher la liste des Adhérents");

		tabDon = new TabDonsView((String) comboAssoc.getSelectedItem());
		tabbedPane.addTab("Dons", icon4, tabDon, "Afficher la liste des dons");

		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 4;
		gc.insets = new Insets(20, 20, 20, 20);
		gc.fill = GridBagConstraints.BOTH;
		add(tabbedPane, gc);

		setLocationRelativeTo(null);
		setSize(1000, 1000);
		centerWindow(this);

		// the tabbedPane must be resize after the setSize() of the parent
//		Dimension d;
//		d = new Dimension(getWidth() * 4 / 5, getHeight() / 2);
//		tabbedPane.setPreferredSize(d);
//
//		d = new Dimension(getWidth(), getHeight() / 10);
//		pane1.setPreferredSize(d);
//		d = new Dimension(getWidth(), getHeight() / 10);
//		pane2.setPreferredSize(d);
//		d = new Dimension(getWidth(), getHeight() * 8 / 10);
//		pane3.setPreferredSize(d);

		// set the pane2 with association label title with the first element of combobox
		// asso

		if (tableau.length != 0) {
			comboAssoc.setSelectedIndex(0);
		}

		setVisible(true);

	}

	// TODO switch case à compléter
	public void jcheckboxHandler(JTabbedPane tabbedPane) {

		lblTitleAsso.setText(comboAssoc.getSelectedItem().toString());
		int index = tabbedPane.getSelectedIndex();

		String tabName = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
		assoSelected = (String) comboAssoc.getSelectedItem();

//		switch (tabName) {
//		case "Activités":
//			System.out.println(tabName);
//			System.out.println(comboAssoc.getSelectedItem());
//			tabActivity.fillTable(assoSelected);
//			tabActivity.setAssoSelected(assoSelected);
//			break;
//		case "Services":
//			System.out.println(tabName);
//			tabService.fillTable(assoSelected);
//			tabService.setAssoSelected(assoSelected);
//			break;
//		case "Adhérents":
//			System.out.println(tabName);
//			tabUser.fillTable(assoSelected);
//			tabUser.setAssoSelected(assoSelected);
//			break;
//		case "Dons":
//			System.out.println(tabName);
//			tabDon.fillTable(assoSelected);
//			tabDon.setAssoSelected(assoSelected);
//			break;
//		default:
//			break;
//		}

		tabActivity.fillTable(assoSelected);
		tabActivity.setAssoSelected(assoSelected);
		tabService.fillTable(assoSelected);
		tabService.setAssoSelected(assoSelected);
		tabUser.fillTable(assoSelected);
		tabUser.setAssoSelected(assoSelected);
		tabDon.fillTable(assoSelected);
		tabDon.setAssoSelected(assoSelected);
	}

	public static void centerWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	public void addToPanel(JPanel p, Object... childs) {
		for (Object object : childs) {
			p.add((Component) object);
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
}
