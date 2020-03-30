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
import java.util.List;

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
import com.assosoft.dao.ServiceDAO;
import com.assosoft.dao.UserDAO;
import com.assosoft.model.Activity;
import com.assosoft.model.Asso;
import com.assosoft.model.Service;
import com.assosoft.model.User;

public class MainUserView extends JFrame {

	private JPanel pane1;
	private JLabel lblAssoc;
	private JComboBox comboAssoc;

	private JPanel pane2;
	private JLabel lblTitleAsso;
	JPanel panebox1;
	JPanel panebox2;

	private JPanel pane3;
	private PaneTemplateView paneActivity;
	private PaneTemplateView paneService;
	private int idAsso;
	private int idUser;

	private JTabbedPane tabbedPane;
	TabActivityView tabActivity;
	TabServicesView tabService;
	TabUsersView tabUser;
	TabDonsView tabDon;

	private String assoSelected;

	private User user;

	public MainUserView(User u) {

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
		
		idAsso = AssoDAO.readAssocNom((String) comboAssoc.getSelectedItem());

		pane1 = new JPanel();
		pane1.setLayout(new FlowLayout(FlowLayout.RIGHT));

		pane1.add(lblAssoc);
		pane1.add(comboAssoc);
		add(pane1, gc);

		/*
		 **********************************************************************
		 * PANE 2 Asso Title
		 **********************************************************************
		 */

		lblTitleAsso = new JLabel("Page Personnel", SwingConstants.CENTER);
		lblTitleAsso.setFont(lblTitleAsso.getFont().deriveFont(40.0f));

		pane2 = new JPanel();
		pane2.setLayout(new BoxLayout(pane2, BoxLayout.Y_AXIS));

		panebox1 = new JPanel();
		panebox1.add(lblTitleAsso);

		pane2.add(panebox1);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;
		add(pane2, gc);

		/*
		 **********************************************************************
		 * PANE 3 panel with panel cards activity and service
		 **********************************************************************
		 */

		pane3 = new JPanel();
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		pane3.setBorder(border);
		pane3.setLayout(new BoxLayout(pane3, BoxLayout.X_AXIS));
		
		List<Activity> activities = ActivityDAO.readAssoActivity(idAsso);
		List<Service> services = ServiceDAO.readAssoService(idAsso);
		paneActivity = new PaneTemplateView("Activités", idUser, idAsso, activities);
		paneService = new PaneTemplateView("Services", idUser, idAsso, services);
		
		pane3.add(paneActivity);
		pane3.add(paneService);


		
		//		paneScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//		JScrollPane paneScroll1 = new JScrollPane(paneActivity);
//		JScrollPane paneScroll2 = new JScrollPane(paneService);
//		pane3.add(paneScroll1);
//		pane3.add(paneScroll2);

		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 6;
		gc.insets = new Insets(20, 20, 20, 20);
		gc.fill = GridBagConstraints.BOTH;
		add(pane3, gc);
		
		System.out.println(tableau.length);
		// select the first element of jcombobox when all panes are added to the parent
		if (tableau.length != 0) {
			comboAssoc.setSelectedIndex(0);
		}

		setLocationRelativeTo(null);
		setSize(1000, 1000);
		centerWindow(this);
		setVisible(true);

	}

	// TODO switch case à compléter
	public void jcheckboxHandler(JTabbedPane tabbedPane) {

//		lblTitleAsso.setText(comboAssoc.getSelectedItem().toString());
////		System.out.println("fillTable()");
//		int index = tabbedPane.getSelectedIndex();
//
//		String tabName = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
//		assoSelected = (String) comboAssoc.getSelectedItem();
////		System.out.println(assoSelected);
//
//		tabActivity.fillTable(assoSelected);
//		tabActivity.setAssoSelected(assoSelected);
//		tabService.fillTable(assoSelected);
//		tabService.setAssoSelected(assoSelected);
//		tabUser.fillTable(assoSelected);
//		tabUser.setAssoSelected(assoSelected);
//		tabDon.fillTable(assoSelected);
//		tabDon.setAssoSelected(assoSelected);
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
		java.net.URL imgURL = MainUserView.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
