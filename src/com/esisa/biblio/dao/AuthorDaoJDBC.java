package com.esisa.biblio.dao;

import java.util.List;
import java.util.Vector;

import com.esisa.biblio.dao.jdbc.Database;
import com.esisa.biblio.models.Author;


public class AuthorDaoJDBC implements AuthorDAO {
	private String tableName = "Authors";
	private Database db;

	public AuthorDaoJDBC(Database db) {
		super();
		this.db = db;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int insert(Author a) {
		return 0;
	}

	public int update(Author a) {
		return 0;
	}

	public int delete(int id) {
		return 0;
	}

	public Author select(int id) {
		return null;
	}

	public List<Author> select() {
		String data[][] = db.select(tableName);
		return getAuthors(data);
	}
	
	public List<Author> select(String keyword) {
		String data[][] = db.select(tableName);
		return getAuthors(data);
	}
	
	private List<Author> getAuthors(String[][] data) {
		Vector<Author> authors = new Vector<>();
		for (int i = 1; i < data.length; i++) {
			authors.add(Mapper.getAuthor(data[i]));
		}
		return authors;
	}

}
