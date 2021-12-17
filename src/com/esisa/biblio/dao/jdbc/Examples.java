package com.esisa.biblio.dao.jdbc;

import java.util.Vector;

public class Examples {
	public Examples() {
		exp02();
	}
	
	public void print(String data[][]) {
		if (data == null) {
			System.out.println("Array is Empty!");
			return;
		}
		print(data, 1, data.length - 1);
	}
	
	public void print(String data[][], int start, int end) {
		//Affichage des titres :
		for (int i = 0; i < data[0].length; i++) {
			System.out.print(data[0][i] + '\t');
		}
		System.out.println();
		//Affichage des données :
		for (int row = start; row <= end; row++) {
			for (int col = 0; col < data[row].length; col++) {
				System.out.print(data[row][col] + '\t');
			}
			System.out.println();
		}
	}
	
	void exp01() {
		Database db = new Database(new MySQLDataSource("Biblio"));
		String data[][] = db.select("Authors");
		print(data, data.length - 10, data.length - 1);
	}
	
	void exp02() {
		Database db = new Database(new MySQLDataSource("Biblio"));
		System.out.println(db.insert("Authors", 16100, "James Gosling", 1955));
		System.out.println(db.insert("Authors", 16101, "Erich Gamma", 1961));
	}
	
	void exp03() {
		Database db = new Database(new MySQLDataSource("Biblio"));
		Field fields[] = db.getFields("Titles");
		for (Field field : fields) {
			System.out.println(field);
		}
	}
	
	void exp04() {
		Database db = new Database(new MySQLDataSource("Biblio"));
		int result = db.update("Authors", 16098, "Larmey, Chris", 1962);
		System.out.println(">> Nombre de ligne modifiées : " + result);
	}
	
	void exp05() {
		Database db = new Database(new MySQLDataSource("mysql"));
		Field fields[] = db.getFields("user");
		System.out.println(">> Nombre de colonnes : " + fields.length);
		for (Field field : fields) {
			System.out.println(field);
		}
	}
	
	void exp06() {
		Database db = new Database(new MySQLDataSource("mysql"));
		String data[][] = db.select("user");
		print(data);
	}
	
	void exp07() {
		Database db = new Database(new MySQLDataSource("Biblio"));
		System.out.println(db.delete("Authors", "Au_ID", 16100));
		String data[][] = db.select("Authors");
		print(data, data.length - 10, data.length - 1);
	}
	
	void exp08() {
		Database db = new Database(new MySQLDataSource("Biblio"));
		String data[][] = db.select("Authors", "AU_ID", 16099);
		print(data);
		System.out.println("-----------------------------------");
		String publisher = "IEEE";
		data = db.select("Publishers", "Name", publisher);
		print(data);
		System.out.println("-----------------------------------");
		data = db.select("Titles", "Publisher_ID", data[1][0]);
		print(data);
		System.out.println(">>> " + (data.length - 1) + " livre(s) trouvés");
	}
	
	void exp09() {
		Database db = new Database(new MySQLDataSource("Biblio"));
		String data[][] = db.select("Authors", "Author", "De Pace, M.");
		print(data);
		data = db.select("title_author", "Au_ID", data[1][0]);
		print(data);
		String[] ISBN = new String[data.length];
		for (int i = 0; i < data.length; i++) {
			ISBN[i] = data[i][0];
		}
		data = db.select("titles", "ISBN", ISBN);
		print(data);
	}
	
	void exp10() {
		Database db = new Database(new SQLServerDataSource("Work", "SOUFIANE"));
		String data[][] = db.select("Produits");
		print(data);
	}
	
	void exp11() {
		Database db = new Database(new MySQLDataSource("Biblio"));
		Vector<String[]> data = db.selectV2("title_author");
		for (String[] strings : data) {
			for (int i = 0; i < strings.length; i++) {
				System.out.print(strings[i] + "\t\t");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		new Examples();
	}
}