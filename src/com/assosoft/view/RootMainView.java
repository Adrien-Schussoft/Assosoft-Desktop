package com.assosoft.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class RootMainView extends JFrame {
	
	protected static Component makeTextPanel(String text) {
	    JPanel panel = new JPanel(false);
	    JLabel filler = new JLabel(text);
	    filler.setHorizontalAlignment(JLabel.CENTER);
	    panel.setLayout(new GridLayout(1, 1));
	    panel.add(filler);
	    return panel;
	}
	
	JTabbedPane tabbedPane = new JTabbedPane();

	public RootMainView() {
		super();
		
//		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(20, 20, 0, 0);
		gc.fill = GridBagConstraints.BOTH;

	    
	    JTabbedPane tp=new JTabbedPane();  
	    tp.add("Associations", new RootAssocView());
	    tp.add("Utilisateurs", new RootUserView());
//	    tp.add("Dons", new RootDonView());
	    try {
			tp.add("Statistiques", new RootStatView());
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    
	    getContentPane().add(tp, gc);
	    
	 // make the frame half the height and width
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int height = screenSize.height;
	    int width = screenSize.width;
	    setSize(width/2, height/2);
	    
	    setLocationRelativeTo(null);
	    setVisible(true);  

	}

	
}
