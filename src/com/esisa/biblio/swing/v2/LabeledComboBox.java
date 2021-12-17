package com.esisa.biblio.swing.v2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabeledComboBox extends JPanel{
	private static final long serialVersionUID = 1L;

	private JComboBox<Object> C;
	
	public LabeledComboBox(String Label, Vector<Object> data) {
		setLayout(new FlowLayout());
		if (!Label.contains(":"))
			Label = Label + " : ";
		JLabel L = new JLabel(Label);
		C = new JComboBox<>(data);
		add(L);
		add(C);
	}
	
	public LabeledComboBox(String Label, Vector<Object> data, int LabelWidth) {
		this(Label, data);
		JLabel L = (JLabel) getComponent(0);
		L.setPreferredSize(new Dimension(LabelWidth, getPreferredSize().height));
	}
	
	public void addActionListener(ActionListener actionListener) {
		C.addActionListener(actionListener);
	}

	public Object getValue() {
		return (Object)C.getSelectedItem();
	}
}

