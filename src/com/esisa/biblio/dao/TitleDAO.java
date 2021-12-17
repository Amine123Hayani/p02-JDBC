package com.esisa.biblio.dao;

import java.util.List;

import com.esisa.biblio.models.Title;

public interface TitleDAO {
	public List<Title> select();
	public List<Title> selecTitlesByAuthor(int id);
}
