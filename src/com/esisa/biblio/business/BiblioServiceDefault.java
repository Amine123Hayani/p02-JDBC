package com.esisa.biblio.business;

import java.util.List;

import com.esisa.biblio.dao.AuthorDAO;
import com.esisa.biblio.dao.PublisherDAO;
import com.esisa.biblio.dao.TitleDAO;
import com.esisa.biblio.models.Author;
import com.esisa.biblio.models.Title;

public class BiblioServiceDefault implements BiblioService {
	private AuthorDAO authorDAO;
	private PublisherDAO publisherDAO;
	private TitleDAO titleDAO;

	public BiblioServiceDefault() {

	}

	public BiblioServiceDefault(AuthorDAO authorDAO, PublisherDAO publisherDAO, TitleDAO titleDAO) {
		super();
		this.authorDAO = authorDAO;
		this.publisherDAO = publisherDAO;
		this.titleDAO = titleDAO;
	}

	public AuthorDAO getAuthorDAO() {
		return authorDAO;
	}

	public void setAuthorDAO(AuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}

	public PublisherDAO getPublisherDAO() {
		return publisherDAO;
	}

	public void setPublisherDAO(PublisherDAO publisherDAO) {
		this.publisherDAO = publisherDAO;
	}

	public TitleDAO getTitleDAO() {
		return titleDAO;
	}

	public void setTitleDAO(TitleDAO titleDAO) {
		this.titleDAO = titleDAO;
	}

	/**********************************************************************/

	public Author author(int id) {
		return authorDAO.select(id);
	}

	public List<Author> authors() {
		return authorDAO.select();
	}

	public List<Author> authors(String keyword) {
		return authorDAO.select(keyword);
	}

	public List<Title> searchTitlesByAuthor(int id) {
		return titleDAO.selecTitlesByAuthor(id);
	}

	public List<Title> titles() {
		return titleDAO.select();
	}

}
