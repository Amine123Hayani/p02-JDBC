package com.esisa.biblio.swing;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.esisa.biblio.business.BiblioService;

public class AuthorsListDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private String title = "Liste des auteurs";
	
	public AuthorsListDialog(JFrame parent, BiblioService service) {
		super(parent, true);
		setTitle(title);
		setSize(700, 300);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		AuthorsListPanel panel = new AuthorsListPanel(title);
		panel.setAuthors(service.authors());
		setContentPane(panel);
	}
}

