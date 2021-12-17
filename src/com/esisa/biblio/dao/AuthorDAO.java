package com.esisa.biblio.dao;

import java.util.List;

import com.esisa.biblio.models.Author;

public interface AuthorDAO {
	public int insert(Author a);
	public int update(Author a);
	public int delete(int id);
	public Author select(int id);
	public List<Author> select();
	public List<Author> select(String keyword);
}
