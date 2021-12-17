package com.esisa.biblio.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.esisa.biblio.dao.Mapper;
import com.esisa.biblio.models.Author;

public class AuthorsListPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel model;
	private JLabel result;
	private TitlesListDialog titlesListDialog;

	public AuthorsListPanel(String title) {
		setLayout(new BorderLayout());

		model = new DefaultTableModel(new String[] {"Identificateur", "Nom d'auteur", "Date de naissance"}, 0) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		add("Center", new JScrollPane(table));


		JLabel label = new JLabel(title);
		label.setFont(new Font("Segeo UI", Font.BOLD, 18));
		add("North", label);

		result = new JLabel();
		result.setFont(new Font("SEGOE UI", Font.BOLD, 18));
		result.setForeground(Color.BLUE);
		add("South", result);
	}

	public void setAuthors(List<Author> authors) {
		model.setRowCount(0);
		for (Author author : authors) {
			model.addRow(Mapper.getRow(author));
		}
		result.setText("Nombre d'auteurs trouvés : " + authors.size());
	}

	public void setTitlesListDialog(TitlesListDialog titlesListDialog) {
		this.titlesListDialog = titlesListDialog;
	}

	public TitlesListDialog getTitlesListDialog() {
		return titlesListDialog;
	}
}
