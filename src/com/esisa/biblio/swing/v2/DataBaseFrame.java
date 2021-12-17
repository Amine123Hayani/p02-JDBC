package com.esisa.biblio.swing.v2;

import java.awt.Toolkit;

import javax.swing.JFrame;

import com.esisa.biblio.dao.jdbc.Database;

public class DataBaseFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public DataBaseFrame() {
		//setContentPane(new DataBasePanel(new Database(new com.esisa.biblio.dao.jdbc.SQLServerDataSource("NorthWind", "SOUFIANE"))));
		setContentPane(new DataBasePanel(new Database(new com.esisa.biblio.dao.jdbc.MySQLDataSource("Biblio"))));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - 1000) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 600) / 2);
		setSize(1000, 600);
		setTitle("DB Browser");
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new DataBaseFrame();
	}
}