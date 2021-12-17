package com.esisa.biblio.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import com.esisa.biblio.business.BiblioService;

public class BiblioMenu extends Menu implements ActionListener {
	private static final long serialVersionUID = 1L;
	private AuthorsListDialog authorsListDialog;
	private SearchAuthorsDialog searchAuthorsDialog;
	private TitlesListDialog titlesListDialog;

	public BiblioMenu(JFrame parent, BiblioService service) {
		addMenu("Gestion des auteurs",
				"Nouvel Auteur",
				"Liste des Auteurs",
				"Recherche d'auteurs",
				"-",
				"Quitter"
				);
		addActionListener(this);
		
		authorsListDialog = new AuthorsListDialog(parent, service);
		searchAuthorsDialog = new SearchAuthorsDialog(parent, service);
		titlesListDialog = new TitlesListDialog(parent, service);
		
		searchAuthorsDialog.setTitlesListDialog(titlesListDialog);
	}

	public void actionPerformed(ActionEvent e) {
		JMenuItem src = (JMenuItem)e.getSource();
		if (src.getText().equals("Gestion des auteurs")) {
			
		}else if (src.getText().equals("Nouvel Auteur")) {
			titlesListDialog.setVisible(true);
		}else if (src.getText().equals("Liste des Auteurs")) {
			authorsListDialog.setVisible(true);
		}else if (src.getText().equals("Recherche d'auteurs")) {
			searchAuthorsDialog.setVisible(true);
		}else if (src.getText().equals("Quitter")) {
			System.exit(0);
		}

	}
}
