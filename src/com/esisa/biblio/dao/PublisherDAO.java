package com.esisa.biblio.dao;

import java.util.List;

import com.esisa.biblio.models.Publisher;

public interface PublisherDAO {
	public int insert(Publisher a);
	public int update(Publisher a);
	public int delete(int id);
	public Publisher select(int id);
	public List<Publisher> select();
	public List<Publisher> select(String keyword);
}
