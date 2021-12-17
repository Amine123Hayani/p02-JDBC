package com.esisa.biblio.swing;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.esisa.biblio.business.BiblioService;

public class TitlesListDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private String title = "Liste des Livres";
	private TitlesListPanel panel;
	private BiblioService service;

	public TitlesListDialog(JFrame parent, BiblioService service) {
		super(parent, true);
		this.service = service;
		setTitle(title);
		setSize(700, 300);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		panel = new TitlesListPanel(title);
		setContentPane(panel);
	}
	
	public void searchByAuthor(int id) {
		panel.setTitles(service.searchTitlesByAuthor(id));
	}
	
	public void allTitles() {
		panel.setTitles(service.titles());
	}

}

