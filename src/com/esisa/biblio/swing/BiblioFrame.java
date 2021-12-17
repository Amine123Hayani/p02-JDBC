package com.esisa.biblio.swing;

import javax.swing.JFrame;

import com.esisa.biblio.business.BiblioService;
import com.esisa.biblio.business.BiblioServiceDefault;
import com.esisa.biblio.dao.AuthorDAO;
import com.esisa.biblio.dao.AuthorDaoJDBC;
import com.esisa.biblio.dao.TitleDAO;
import com.esisa.biblio.dao.TitleDaoJDBC;
import com.esisa.biblio.dao.jdbc.DataSource;
import com.esisa.biblio.dao.jdbc.Database;
import com.esisa.biblio.dao.jdbc.MySQLDataSource;

public class BiblioFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private BiblioService service;

	public BiblioFrame() {
		init();
		ui();
	}
	
	private void init() {
		// Wiring :
		DataSource ds = new MySQLDataSource("Biblio");
		Database db = new Database(ds);
		AuthorDAO authorDAO = new AuthorDaoJDBC(db);
		TitleDAO titleDAO = new TitleDaoJDBC(db);
		service = new BiblioServiceDefault(authorDAO, null, titleDAO);
		
	}
	
	void ui() {
		setSize(1000, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setJMenuBar(new BiblioMenu(this, service));
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new BiblioFrame();
	}
}
