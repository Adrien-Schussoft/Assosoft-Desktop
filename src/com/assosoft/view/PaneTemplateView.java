package com.assosoft.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.assosoft.dao.ActivityDAO;
import com.assosoft.dao.AssoDAO;
import com.assosoft.dao.ServiceDAO;
import com.assosoft.dao.UserDAO;
import com.assosoft.model.Activity;
import com.assosoft.model.Asso;
import com.assosoft.model.Service;
import com.assosoft.model.User;
import com.assosoft.utils.Tools;

public class PaneTemplateView extends JPanel {

	private String title;
	private JLabel lblTitle;
	private JPanel paneHeader;
	private JPanel paneContent;
	private JScrollPane paneScroll;
//	private JPanel paneFooter;
	private User u;
	private Asso a;
	private List listContent;
	private String listType;

	// boolean for label button OK/Cancel/Plus
	private boolean lblActionState;

//		Border border = BorderFactory.createLineBorder(Color.RED);
	Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

	public PaneTemplateView(String title, int idUser, int idAsso, List listContent) {
		super();
		this.title = title;

		u = UserDAO.readUser(idUser);
		a = AssoDAO.readAsso(idAsso);

		this.listContent = listContent;
		if (this.listContent.size() != 0) {
			if (listContent.get(0) instanceof Activity) {
				listType = "Activity";
			} else {
				listType = "Service";
			}
		}

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		Border bord = getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		setBorder(new CompoundBorder(bord, margin));

		/**
		 * Header Pane
		 */
		paneHeader = new JPanel();
		paneHeader.setBorder(border);
		lblTitle = new JLabel(title);
		lblTitle.setFont(lblTitle.getFont().deriveFont(25.0f));
		paneHeader.add(lblTitle);

		/**
		 * Content Pane
		 */

		paneContent = new JPanel();
		paneContent.setLayout(new BoxLayout(paneContent, BoxLayout.Y_AXIS));
		paneScroll = new JScrollPane(paneContent);
		paneScroll.getVerticalScrollBar().setUnitIncrement(20);
		paneScroll.setBorder(border);

		for (int i = 0; i < listContent.size(); i++) {
			paneContent.add(createCardPanel(i));
		}

		add(paneHeader);
//		add(paneContent);
		add(paneScroll);

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public JPanel createCardPanel(int i) {

		JPanel cardPanel = new JPanel();
		cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
		cardPanel.setBorder(border);

		JPanel lblPanel = new JPanel();
		lblPanel.setLayout(new GridLayout(5, 1));

		// JPanel for the button label
		// the user can toggle participation to an activity
		JPanel actionPanel = new JPanel();

		JLabel actionPlus = new JLabel();
		lblActionState = false;

		ImageIcon imgPlus = Tools.createImageIcon("/img/task-plus.png");
		ImageIcon imgOk = Tools.createImageIcon("/img/task-ok.png");
		ImageIcon imgCancel = Tools.createImageIcon("/img/task-cancel.png");

		actionPlus.setIcon(imgPlus);

		actionPlus.addMouseListener(new MouseListener() {

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (lblActionState)
					actionPlus.setIcon(imgOk);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (lblActionState)
					actionPlus.setIcon(imgCancel);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (lblActionState) {
					actionPlus.setIcon(imgPlus);
					lblActionState = false;
				} else {
					actionPlus.setIcon(imgOk);
					lblActionState = true;
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		// Adding the JButton "b1"
		actionPanel.add(actionPlus);

		if (listType.equals("Activity")) {

			Activity activity = (Activity) listContent.get(i);
			JLabel lblTitle = new JLabel(activity.getNom());
			lblTitle.setFont(lblTitle.getFont().deriveFont(20.0f));

			JLabel lblDescriptionTitle = new JLabel("Description", SwingConstants.CENTER);
			lblDescriptionTitle.setFont(lblDescriptionTitle.getFont().deriveFont(15.0f));
			JTextArea textArea = new JTextArea();
			textArea.setText(
					"Swine kevin doner buffalo. Venison pork belly sirloin, prosciutto capicola ham shoulder chicken. Jowl sirloin chicken, ball tip t-bone filet mignon salami andouille frankfurter kielbasa. Tail kielbasa chicken tri-tip doner jowl burgdoggen biltong fatback sirloin alcatra short ribs. ");
			textArea.setWrapStyleWord(true);
			textArea.setLineWrap(true);
			textArea.setOpaque(false);
			textArea.setEditable(false);
			textArea.setFocusable(false);
			textArea.setBackground(UIManager.getColor("Label.background"));
			textArea.setFont(UIManager.getFont("Label.font"));
			textArea.setBorder(UIManager.getBorder("Label.border"));

			JLabel lblDescription = new JLabel("", SwingConstants.CENTER);
			lblDescription.setFont(lblDescription.getFont().deriveFont(15.0f));

			JLabel lblDateDebut = new JLabel("Date de Début : " + activity.getDate_Debut().toString(),
					SwingConstants.CENTER);
			lblDateDebut.setFont(lblDateDebut.getFont().deriveFont(15.0f));

			JLabel lblDateFin = new JLabel("Date de Fin : " + activity.getDate_Fin().toString(), SwingConstants.CENTER);
			lblDateFin.setFont(lblDateFin.getFont().deriveFont(15.0f));

			JLabel lblPrix = new JLabel("Prix : " + String.valueOf(activity.getCout()), SwingConstants.CENTER);
			lblPrix.setFont(lblPrix.getFont().deriveFont(15.0f));

			Image image = null;
			try {
//				URL url = new URL("https://placeimg.com/340/260/any");
//				URL url = new URL("https://via.placeholder.com/360x240/0000FF/808080?Text=Digital.com");
				URL url = new URL("https://picsum.photos/340/260");
				image = ImageIO.read(url);
			} catch (IOException e) {
				e.printStackTrace();
			}

			JLabel lblImage = new JLabel(new ImageIcon(image));

			// center alignement in BoxLayout
			lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);

			cardPanel.add(Box.createRigidArea(new Dimension(0, 30)));
			cardPanel.add(lblTitle);
			cardPanel.add(Box.createRigidArea(new Dimension(0, 10)));
			cardPanel.add(lblImage);
			cardPanel.add(Box.createRigidArea(new Dimension(0, 10)));

			lblPanel.add(lblDescriptionTitle);
			lblPanel.add(textArea);

			lblPanel.add(lblDateDebut);
			lblPanel.add(lblDateFin);
			lblPanel.add(lblPrix);

			cardPanel.add(lblPanel);
			cardPanel.add(Box.createRigidArea(new Dimension(0, 10)));

			cardPanel.add(actionPanel);
			cardPanel.add(Box.createRigidArea(new Dimension(0, 30)));

			return cardPanel;
		} else {

			Service service = (Service) listContent.get(i);
			JLabel lblTitle = new JLabel(service.getNom());
			lblTitle.setFont(lblTitle.getFont().deriveFont(20.0f));

			Image image = null;
			try {
//				URL url = new URL("https://placeimg.com/340/260/any");
//				URL url = new URL("https://via.placeholder.com/360x240/0000FF/808080?Text=Digital.com");
				URL url = new URL("https://picsum.photos/340/260");
				image = ImageIO.read(url);
			} catch (IOException e) {
				e.printStackTrace();
			}

			JLabel lblImage = new JLabel(new ImageIcon(image));

			lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);

			cardPanel.add(Box.createRigidArea(new Dimension(0, 30)));
			cardPanel.add(lblTitle);
			cardPanel.add(Box.createRigidArea(new Dimension(0, 10)));
			cardPanel.add(lblImage);
			cardPanel.add(Box.createRigidArea(new Dimension(0, 10)));
			cardPanel.add(actionPanel);
			cardPanel.add(Box.createRigidArea(new Dimension(0, 30)));

			return cardPanel;
		}
	}

}
