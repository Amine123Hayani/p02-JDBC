package com.esisa.biblio.swing;

import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
	private static final long serialVersionUID = 1L;
	private String Path = "Resources/Icons/";
	private String Suffix = ".png";
	private Vector<JMenuItem> items;

	public Menu() {
		items = new Vector<JMenuItem>();
	}
	
	public Menu(String MenuLabels[][]) {
		items = new Vector<JMenuItem>();
		for (int i = 0; i < MenuLabels.length; i++) {
			addMenu(MenuLabels[i]);
		}
	}
	
	public void addMenu(String ...T) {
		JMenu M = new JMenu(T[0]);
		M.setIcon(new ImageIcon(Path + T[0] + Suffix));
		add(M);
		for (int j = 1; j < T.length; j++) {
			if(T[j].equals("-")) {
				M.addSeparator();
			}else {
				ImageIcon icon = new ImageIcon(Path + T[j] + Suffix);
				JMenuItem M1 = new JMenuItem(T[j], icon);
				M.add(M1);
				items.add(M1);
			}
		}
	}
	
	public void addActionListener(ActionListener listener) {
		for (JMenuItem Item : items) {
			Item.addActionListener(listener);
		}
	}

}
