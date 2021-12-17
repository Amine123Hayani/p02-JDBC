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
import com.esisa.biblio.models.Title;

public class TitlesListPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel model;
	private JLabel result;
	
	public TitlesListPanel(String title) {
		setLayout(new BorderLayout());

		model = new DefaultTableModel(new String[] {"ISBN", "Titre", "Editeur", "Année"}, 0) {
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

	public void setTitles(List<Title> titles) {
		model.setRowCount(0);
		for (Title title : titles) {
			model.addRow(Mapper.getRow(title));
		}
		result.setText("Nombre de livres trouvés : " + titles.size());
	}

}
