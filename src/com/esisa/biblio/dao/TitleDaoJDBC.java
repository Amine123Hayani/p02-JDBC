package com.esisa.biblio.dao;

import java.util.List;
import java.util.Vector;

import com.esisa.biblio.dao.jdbc.Database;
import com.esisa.biblio.models.Title;

public class TitleDaoJDBC implements TitleDAO {
	private Database db;
	
	public TitleDaoJDBC(Database db) {
		this.db = db;
	}
	
	public List<Title> selecTitlesByAuthor(int id) {
		String query = "SELECT * FROM titles, title_author WHERE title_author.au_id = " + id + " AND title_author.isbn = titles.isbn";
		String data[][] = db.executeSelect(query);
		Vector<Title> titles = new Vector<>();
		for (int i = 1; i < data.length; i++) {
			titles.add(Mapper.getTitle(data[i]));
		}
		return titles;
	}

	public List<Title> select() {
		String data[][] = db.select("Titles");
		Vector<Title> titles = new Vector<>();
		for (int i = 1; i < data.length; i++) {
			titles.add(Mapper.getTitle(data[i]));
		}
		return titles;
	}
	
}
