package com.esisa.biblio.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Database {
	private DataSource ds = null;
	private Connection db = null;

	public Database() {
	}

	public Database(DataSource ds) {
		this.ds = ds;
		this.db = ds.getConnection();
	}

	public void setDataSource(DataSource ds){
		this.ds = ds;
		this.db = ds.getConnection();
	}

	public DataSource getDataSource() {
		return ds;
	}

	public String[][] select(String tableName) {
		return executeSelect("SELECT * FROM " + tableName);
	}

	public String[][] select(String tableName, String key, Object value) {
		return executeSelect("SELECT * FROM " + tableName + " WHERE " + key + " = '" + value + "'");
	}
	
	public String[][] selectContains(String tableName, String key, Object keyword) {
		return executeSelect("SELECT * FROM " + tableName + " WHERE " + key + " LIKE '%" + keyword + "%'");
	}
	
	public String[][] selectStartWith(String tableName, String key, Object keyword) {
		return executeSelect("SELECT * FROM " + tableName + " WHERE " + key + " LIKE '" + keyword + "%'");
	}
	
	public String[][] select(String tableName, String key, Object[][] values) {
		String condition = key + " = '" + values[0] + "'";
		for (int i = 1; i < values.length; i++) {
			condition += " OR " + key + " = '" + values[i] + "'";
		}
		return executeSelect("SELECT * FROM " + tableName + " WHERE " + condition);
	}

	public Vector<String[]> selectV2(String tableName) {
		return executeSelectV2("SELECT * FROM " + tableName);
	}
	
	public Vector<String[]> selectV2(String tableName, String key, Object value) {
		return executeSelectV2("SELECT * FROM " + tableName + " WHERE " + key + " = '" + value + "'");
	}
	
	public Vector<String[]> selectV2(String tableName, String key, Object values[]) {
		String condition = key + " = '" + values[0] + "'";
		for (int i = 1; i < values.length; i++) {
			condition += " OR " + key + " = '" + values[i] + "'";
		}
		return executeSelectV2("SELECT * FROM " + tableName + " WHERE " + condition);
	}

	public int insert(String tableName, Object ...row) {
		String query = "INSERT INTO " + tableName + " VALUES (" + "'" + row[0] + "'";
		for (int i = 1; i < row.length; i++) {
			query += ", '" + row[i] + "'";
		}
		query += ")";
		return executeUpdate(query);
	}
	
	public int update(String tableName, Object ...row) {
		if (row.length < 2) return -1;
		Field fields[] = getFields(tableName);
		if (row.length > fields.length) return -1;

		String query = "UPDATE " + tableName + " SET " + fields[1].getName() +  " = '" + row[1] + "'";
		for (int i = 2; i < row.length; i++) {
			query = query + ", " + fields[i].getName() +  " = '" + row[i] + "'";
		}
		query = query + "WHERE " + fields[0].getName() + " = '" + row[0] + "'";
		return executeUpdate(query);
	}

	public int delete(String tableName, String key, Object id) {
		String query = "DELETE FROM " + tableName + " WHERE " + key + " = '" + id + "'";
		return executeUpdate(query);
	}

	public String[][] executeSelect(String query) {
		try {
			Statement sql = db.createStatement();
			ResultSet rs = sql.executeQuery(query);
			ResultSetMetaData rsm = rs.getMetaData();
			int cols = rsm.getColumnCount();
			rs.last();
			int rows = rs.getRow();
			rs.beforeFirst();
			String data[][] = new String[rows + 1][cols];
			
			for (int i = 0; i < cols; i++) {
				data[0][i] = rsm.getColumnName(i + 1);
			}
			for (int i = 1; i < data.length; i++) {
				rs.next();
				for (int j = 0; j < cols; j++) {
					data[i][j] = rs.getString(j + 1);
				}
			}
			return data;
		} catch (SQLException e) {
			System.out.println("Erreur : " + e.getMessage());
			return null;
		}
	}
	
	public Vector<String[]> executeSelectV2(String query) {
		try {
			Statement sql = db.createStatement();
			ResultSet rs = sql.executeQuery(query);
			ResultSetMetaData rsm = rs.getMetaData();
			int cols = rsm.getColumnCount();
			Vector<String[]> data = new Vector<>();
			String[] data2 = new String[cols];
			for (int i = 0; i < cols; i++) {
				data2[i] = rsm.getColumnName(i + 1);
			}
			data.add(data2);
			
			while(rs.next()) {
				data2 = new String[cols];
				for (int j = 0; j < cols; j++) {
					data2[j] = rs.getString(j + 1);
				}
				data.add(data2);
			}
			return data;
		} catch (SQLException e) {
			System.out.println("Erreur : " + e.getMessage());
			return null;
		}
	}

	private int executeUpdate(String query) {
		try {
			Statement sql = db.createStatement();
			return sql.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erreur : " + e.getMessage());
			return -1;
		}
	}

	public Field[] getFields(String tableName) {
		try {
			Statement sql = db.createStatement();
			ResultSet rs = sql.executeQuery("SELECT * FROM " + tableName);
			ResultSetMetaData rsm = rs.getMetaData();
			int cols = rsm.getColumnCount();
			Field fields[] = new Field[cols];
			for (int col = 1; col <= cols; col++) {
				fields[col - 1] = new Field(rsm.getColumnName(col), rsm.getColumnType(col), rsm.getColumnDisplaySize(col));
			}
			return fields;
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
			return null;
		}
	}
	
	public Vector<String[]> getTableNames() {
		if (ds instanceof MySQLDataSource)
			return executeSelectV2("SELECT table_name FROM information_schema.tables WHERE table_schema = '" + ds.getSource() + "'");
		else if (ds instanceof SQLServerDataSource)
			return executeSelectV2("SELECT table_name FROM information_schema.tables WHERE table_catalog = '" + ds.getSource() + "'");
		return null;
	}
	
	public void close() {
		try {
			if (db != null)
				db.close();
		} catch (SQLException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}
}