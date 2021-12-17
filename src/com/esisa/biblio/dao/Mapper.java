package com.esisa.biblio.dao;

import com.esisa.biblio.models.Author;
import com.esisa.biblio.models.Publisher;
import com.esisa.biblio.models.Title;

public class Mapper {

	public Mapper() {

	}

	public static Author getAuthor(String ...row) { 
		return new Author(getInt(row[0]), row[1], getInt(row[2]));
	}
	
	public static Title getTitle(String ...row) { 
		return new Title(row[0], row[1], new Publisher(getInt(row[3]), null, null), getInt(row[2]));
	}

	public static Object[] getRow(Author a) {
		return new Object[] {a.getId(), a.getName(), a.getYearBorn()};
	}
	
	public static Object[] getRow(Title a) {
		return new Object[] {a.getIsbn(), a.getTitle(), a.getPublisher().getId(), a.getYearPublished()};
	}

	public static int getInt(String data) {
		try {
			return Integer.parseInt(data);
		} catch (Exception e) {
			return -1;
		}
	}
	
	public static float getFloat(String data) {
		try {
			return Float.parseFloat(data);
		} catch (Exception e) {
			return -1;
		}
	}
	
	public static double getDouble(String data) {
		try {
			return Double.parseDouble(data);
		} catch (Exception e) {
			return -1;
		}
	}
}
