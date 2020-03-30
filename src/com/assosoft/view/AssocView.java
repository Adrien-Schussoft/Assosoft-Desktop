package com.assosoft.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AssocView extends JFrame{
	public AssocView() {
		getContentPane().setBackground(Color.WHITE);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(0, 0, 954, 109);
		getContentPane().add(panel);
				panel.setLayout(new BorderLayout(0, 0));
		//----------------------------------CREATION BAR DE MENU ---------------------------------------------------------
				
				JToolBar toolBar = new JToolBar();
				toolBar.setBackground(Color.RED);
				panel.add(toolBar, BorderLayout.NORTH);
				
				//getContentPane().setLayout(null);
				
				JLabel lbl_logo = new JLabel("");
				toolBar.add(lbl_logo);
				lbl_logo.setHorizontalAlignment(SwingConstants.RIGHT);
				lbl_logo.setBounds(147, 308, 97, 38);
				lbl_logo.setIcon(new ImageIcon(AssocView.class.getResource("/img/logo-Assosoft.png")));
				
				JButton btnNewButton = new JButton("");
				btnNewButton.setBackground(Color.WHITE);
				btnNewButton.setIcon(new ImageIcon(AssocView.class.getResource("/img/donsMenu.png")));
				toolBar.add(btnNewButton);
				
				JButton btnNewButton_1 = new JButton("");
				btnNewButton_1.setIcon(new ImageIcon(AssocView.class.getResource("/img/logMenu.png")));
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				toolBar.add(btnNewButton_1);
				
				JButton btnNewButton_2 = new JButton("");
				btnNewButton_2.setIcon(new ImageIcon(AssocView.class.getResource("/img/inscripMenu.png")));
				toolBar.add(btnNewButton_2);
				
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.EAST);
				
				table = new JTable();
				panel.add(table, BorderLayout.CENTER);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
}
