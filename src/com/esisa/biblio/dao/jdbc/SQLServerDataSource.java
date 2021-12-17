package com.esisa.biblio.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerDataSource extends DataSource {
	public static final String SQL_SERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String SQL_SERVER_BRIDGE = "jdbc:sqlserver:";
	
	public SQLServerDataSource(String host, String source, String user, String password) {
		super(SQL_SERVER_DRIVER, SQL_SERVER_BRIDGE, host, source, user, password);
	}
	
	public SQLServerDataSource(String source, String user, String password) {
		super(SQL_SERVER_DRIVER, SQL_SERVER_BRIDGE, "localhost", source, user, password);
	}
	
	public SQLServerDataSource(String source, String user) {
		super(SQL_SERVER_DRIVER, SQL_SERVER_BRIDGE, "localhost", source, user, "");
	}
	
	public Connection getConnection() {
		try {
			Class.forName(getDriver());
			String url = getBridge() + "//" + getHost() + ";databaseName=" + getSource() + ";user=" + getUser() + ";password=" + getPassword(); 
			Connection db = DriverManager.getConnection(url);
			System.out.println("Connection bien établie...");
			return db;
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
			return null;
		}
	}

}
