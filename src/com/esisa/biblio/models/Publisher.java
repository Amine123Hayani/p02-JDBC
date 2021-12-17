package com.esisa.biblio.models;

public class Publisher {
	private int id;
	private String name;
	private String company;
	
	public Publisher() {
		// TODO Auto-generated constructor stub
	}

	public Publisher(int id, String name, String company) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	

}
