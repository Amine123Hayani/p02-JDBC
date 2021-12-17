package com.esisa.biblio.swing.v2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.esisa.biblio.dao.jdbc.Database;

public class DataBasePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private Database db;
	private LabeledComboBox lcb, lcbD;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField field;
	private JDialog searchDialog;
	private JPanel buttonPanel1, buttonPanel2;

	public DataBasePanel(Database db) {
		this.db = db;
		Vector<String[]> tableNames = db.getTableNames();

		Vector<Object> tmp = new Vector<>();
		for (int i = 1; i < tableNames.size(); i++) {
			tmp.add(tableNames.get(i)[0]);
		}

		setLayout(new BorderLayout());

		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

		lcb = new LabeledComboBox("Table : ", tmp);
		lcb.addActionListener(this);
		container.add(lcb);
		
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		container.add(new JScrollPane(table));

		select();

		add("Center", container);

		buttonPanel1 = new JPanel(new FlowLayout());

		JButton b1 = new JButton("Insert");
		b1.addActionListener(this);
		buttonPanel1.add(b1);

		JButton b2 = new JButton("Update");
		b2.addActionListener(this);
		buttonPanel1.add(b2);

		JButton b3 = new JButton("Delete");
		b3.addActionListener(this);
		buttonPanel1.add(b3);

		JButton b4 = new JButton("Search");
		b4.addActionListener(this);
		buttonPanel1.add(b4);
		
		add("South", buttonPanel1);
		
		buttonPanel2 = new JPanel(new FlowLayout());
		
		JButton b5 = new JButton("Add Row");
		b5.addActionListener(this);
		buttonPanel2.add(b5);
		
		JButton b6 = new JButton(" Cancel ");
		b6.addActionListener(this);
		buttonPanel2.add(b6);
		
		createSearchDialog();
	}

	private void createSearchDialog() {
		searchDialog = new JDialog();

		JPanel panel = new JPanel();

		Vector<Object> data = new Vector<>();
		data.add("Search Title By Author");
		data.add("Search Title By Publisher");
		data.add("Search Author By Title");
		data.add("Search Publisher By Title");
		lcbD = new LabeledComboBox("Select search type : ", data);
		panel.add(lcbD);

		JPanel p = new JPanel(new FlowLayout());
		JLabel label = new JLabel("Value : ");
		p.add(label);
		field = new JTextField(15);
		p.add(field);
		panel.add(p);
		
		searchDialog.setLayout(new BorderLayout());
		searchDialog.getContentPane().add("Center", panel);
		
		JPanel p2 = new JPanel(new FlowLayout());
		JButton btn2 = new JButton("Find");
		btn2.addActionListener(this);
		p2.add(btn2);
		JButton btn = new JButton("Cancel");
		btn.addActionListener(this);
		p2.add(btn);
		
		searchDialog.getContentPane().add("South", p2);

		searchDialog.setAlwaysOnTop(true);
		searchDialog.setResizable(false);
		searchDialog.setTitle("Search Box");
		searchDialog.setSize(400, 150);
		searchDialog.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - 400) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 200) / 2);
		searchDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComboBox<?>) {
			select();
		}else if (((JButton)e.getSource()).getText().equals("Insert")) {
			tableModel.addRow(new String[] {});
			table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
			buttonPanel1.setVisible(false);
			buttonPanel2.setVisible(true);
			add("South", buttonPanel2);
		}else if (((JButton)e.getSource()).getText().equals("Update")) {
			try {
				Object row[] = new Object[tableModel.getColumnCount()];
				for (int i = 0; i < row.length; i++) {
					row[i] = table.getValueAt(table.getSelectedRow(), i);
				}
				db.update((String)lcb.getValue(), row);
				JOptionPane.showMessageDialog(this, "Updated successfully!");
				select();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Select row to update");
			}

		}else if (((JButton)e.getSource()).getText().equals("Delete")) {
			try {
				Object id = table.getValueAt(table.getSelectedRow(), 0);
				if (id != null) {
					db.delete((String)lcb.getValue(), tableModel.getColumnName(0), id);
					JOptionPane.showMessageDialog(this, "Deleted successfully!");
					select();
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Select row to delete");
			}
		}else if (((JButton)e.getSource()).getText().equals("Search")) {
			field.setText("");
			searchDialog.setVisible(true);
		}else if (((JButton)e.getSource()).getText().equals("Find")) {
			if (lcbD.getValue().equals("Search Title By Author")) {
				searchTitleByAuthor();
			}else if (lcbD.getValue().equals("Search Title By Publisher")) {
				searchTitleByPublisher();
			}else if (lcbD.getValue().equals("Search Author By Title")) {
				searchAuthorByTitle();
			}else if (lcbD.getValue().equals("Search Publisher By Title")) {
				searchPublisherByTitle();
			}
		}else if (((JButton)e.getSource()).getText().equals("Add Row")) {
			if (!nullrow()) {
				Object[] row = new String[tableModel.getColumnCount()];
				for (int i = 0; i < tableModel.getColumnCount(); i++) {
					row[i] = table.getValueAt(tableModel.getRowCount() - 1, i);
				}
				db.insert((String)lcb.getValue(), row);
				JOptionPane.showMessageDialog(this, "Added successfully!");
				buttonPanel2.setVisible(false);
				buttonPanel1.setVisible(true);
				add("South", buttonPanel1);
				select();
			}else {
				JOptionPane.showMessageDialog(this, "Invalid Data");
			}
		}else if (((JButton)e.getSource()).getText().equals("Cancel")) {
			searchDialog.dispose();
		}else if (((JButton)e.getSource()).getText().equals(" Cancel ")) {
			tableModel.removeRow(tableModel.getRowCount() - 1);
			buttonPanel2.setVisible(false);
			buttonPanel1.setVisible(true);
			add("South", buttonPanel1);
		}
	}

	private void searchTitleByAuthor() {
		try {
			Vector<String[]> data = db.selectV2("Authors", "Author", field.getText());
			data = db.selectV2("title_author", "Au_ID", data.get(1)[0]);
			String[] ISBN = new String[data.size()];
			for (int i = 0; i < data.size(); i++) {
				ISBN[i] = data.get(i)[0];
			}
			data = db.selectV2("titles", "ISBN", ISBN);
			setData(data);
			searchDialog.dispose();
		} catch (Exception e) {
			searchDialog.dispose();
			JOptionPane.showMessageDialog(this, "Not Found!");
			field.setText("");
			searchDialog.setVisible(true);
		}
	}

	private void searchTitleByPublisher() {
		try {
			Vector<String[]> data = db.selectV2("Publishers", "Name", field.getText());
			data = db.selectV2("titles", "Publisher_ID", data.get(1)[0]);
			setData(data);
			searchDialog.dispose();
		} catch (Exception e) {
			searchDialog.dispose();
			JOptionPane.showMessageDialog(this, "Not Found!");
			field.setText("");
			searchDialog.setVisible(true);
		}
	}

	private void searchAuthorByTitle() {
		try {
			Vector<String[]> data = db.selectV2("titles", "title", field.getText());
			data = db.selectV2("title_author", "ISBN", data.get(1)[0]);
			data = db.selectV2("Authors", "Au_ID", data.get(1)[1]);
			setData(data);
			searchDialog.dispose();
		} catch (Exception e) {
			searchDialog.dispose();
			JOptionPane.showMessageDialog(this, "Not Found!");
			field.setText("");
			searchDialog.setVisible(true);
		}
	}

	private void searchPublisherByTitle() {
		try {
			Vector<String[]> data = db.selectV2("titles", "title", field.getText());
			data = db.selectV2("Publishers", "Publisher_ID", data.get(1)[3]);
			setData(data);
			searchDialog.dispose();
		} catch (Exception e) {
			searchDialog.dispose();
			JOptionPane.showMessageDialog(this, "Not Found!");
			field.setText("");
			searchDialog.setVisible(true);
		}
	}

	private void setData(Vector<String[]> data) {
		tableModel = new DefaultTableModel(data.get(0), 0);
		for (int i = 1; i < data.size(); i++) {
			tableModel.addRow(data.get(i));
		}
		table.setModel(tableModel);
	}
	
	private void select() {
		Vector<String[]> data = db.selectV2((String) lcb.getValue());
		tableModel = new DefaultTableModel(data.get(0), 0);
		for (int i = 1; i < data.size(); i++) {
			tableModel.addRow(data.get(i));
		}
		table.setModel(tableModel);
	}

	private boolean nullrow() {
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			if (table.getValueAt(tableModel.getRowCount() - 1, i) == null)
				return true;
		}
		return false;
	}
}
