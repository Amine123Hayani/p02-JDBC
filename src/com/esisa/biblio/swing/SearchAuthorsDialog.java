package com.esisa.biblio.swing;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.esisa.biblio.business.BiblioService;

public class SearchAuthorsDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private String title = "Rechercher d'auteurs";
	private JTextField keyword;
	private AuthorsListPanel panel;
	private BiblioService service;

	public SearchAuthorsDialog(JFrame parent, BiblioService service) {
		super(parent, true);
		this.service = service;
		setTitle(title);
		setSize(700, 300);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		panel = new AuthorsListPanel(title);
		getContentPane().add("Center", panel);

		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p.add(new JLabel("Mot clé : "));
		keyword = new JTextField(15);
		keyword.addActionListener(this);
		p.add(keyword);
		JButton btn = new JButton("Chercher");
		btn.addActionListener(this);
		p.add(btn);

		getContentPane().add("North", p);
	}
	
	public void setTitlesListDialog(TitlesListDialog titlesListDialog) {
		panel.setTitlesListDialog(titlesListDialog);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) 
			panel.setAuthors(service.authors(keyword.getText())); 	
	}
}