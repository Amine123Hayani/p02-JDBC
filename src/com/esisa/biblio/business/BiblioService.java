package com.esisa.biblio.business;

import java.util.List;

import com.esisa.biblio.models.Author;
import com.esisa.biblio.models.Title;

public interface BiblioService {
	public Author author(int id);
	public List<Author> authors();
	public List<Author> authors(String keyword);
	
	public List<Title> titles();
	public List<Title> searchTitlesByAuthor(int id);

}
