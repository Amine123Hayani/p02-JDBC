package com.esisa.biblio.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class AccessDataSource extends DataSource {
	public static final String MS_ACCESS_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	public static final String MS_ACCESS_BRIDGE = "jdbc:odbc:";
	
	public AccessDataSource(String host) {
		super(MS_ACCESS_DRIVER, MS_ACCESS_BRIDGE, host, null, null, null);
	}
	
	public Connection getConnection() {
		try {
			Class.forName(getDriver());
			String url = getBridge() + getHost(); 
			Connection db = DriverManager.getConnection(url);
			System.out.println("Connection bien établie...");
			return db;
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
			return null;
		}
	}
}
