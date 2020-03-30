package com.assosoft.utils;

import javax.swing.ImageIcon;

import com.assosoft.view.MainContactView;

public class Tools {

	/** Returns an ImageIcon, or null if the path was invalid. */
	public static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MainContactView.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
