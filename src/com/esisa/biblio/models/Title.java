package com.esisa.biblio.models;

import java.util.Vector;

public class Title {
	private String isbn;
	private String title;
	private Publisher publisher;
	private int yearPublished;
	private Vector<Author> authors;
	
	public Title() {
		authors = new Vector<Author>();
	}

	public Title(String isbn, String title, Publisher publisher, int yearPublished) {
		this();
		this.isbn = isbn;
		this.title = title;
		this.publisher = publisher;
		this.yearPublished = yearPublished;
	}

	public void add(Author a) {
		authors.add(a);
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public int getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(int yearPublished) {
		this.yearPublished = yearPublished;
	}

	public Vector<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Vector<Author> authors) {
		this.authors = authors;
	}

	public String toString() {
		return "Title [isbn=" + isbn + ", title=" + title + ", publisher=" + publisher + ", yearPublished="
				+ yearPublished + ", authors=" + authors + "]";
	}
	
}
