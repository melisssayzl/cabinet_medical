package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConfig {
//	Attributs ...
	private String host = "";
	private int port = 0;
	private String dbName = "";
	private String dbUser = "";
	private String dbPassword = "";
	
	private static DatabaseConfig singleton = null;
	
	public static DatabaseConfig getSingleton(String host, int port, String dbName, String dbUser, String dbPassword) {
		if (singleton == null) {
			singleton = new DatabaseConfig(host, port, dbName, dbUser, dbPassword);
		}
		else {
			singleton.setDatabaseParameters(host, port, dbName, dbUser, dbPassword);
		}
		
		return singleton;
	}
	
	public static DatabaseConfig getSingleton() {
		if (singleton == null) {
			singleton = new DatabaseConfig();
		}
		
		return singleton;
	}

//	Methodes : fonctions & procedures ...
	private DatabaseConfig() {
	}
	
	private DatabaseConfig(String host, int port, String dbName, String dbUser, String dbPassword) {
		this.setDatabaseParameters(host, port, dbName, dbUser, dbPassword);
	}
	
	private void setDatabaseParameters(String host, int port, String dbName, String dbUser, String dbPassword) {
		this.host = host;
		this.port = port;
		this.dbName = dbName;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

//	JDBC methodes ...
	public Connection getConnection(){
		Connection connection = null;
		
		try {
			String dbURL = "jdbc:mysql://"+host+":"+port+"/"+dbName;
			
			Class.forName("com.mysql.cj.jdbc.Driver");  
			connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	/**
	 * insert, update, delete with normal statement ...
	 * <br>
	 * Les parametres sont dans la requete SQL ....
	 * @param sql
	 * @return
	 */
	//
	public boolean execute(String sql) {
		boolean ok = true;
		
		try {
			Connection connection = getConnection();
			
			Statement stmt = connection.createStatement();
			
			stmt.execute(sql);
			
			connection.close();
		} catch (Exception e) {
			ok = false;
			e.printStackTrace();
		}
		
		return ok;
	}
	
	/**
	 * insert, update, delete with prepared statement ...
	 * 
	 * @param sql
	 * @param sqlParameters
	 * @return
	 */
	public boolean executePS(String sql, Object ... sqlParameters) {
		boolean ok = true;
		
		try {
			Connection connection = getConnection();
			
			PreparedStatement pStmt = connection.prepareStatement(sql);
			
			for (int i=0; i<sqlParameters.length; i++) {
				pStmt.setObject(i+1, sqlParameters[i]);
			}
			
			pStmt.execute();
			
			connection.close();
		} catch (Exception e) {
			ok = false;
			e.printStackTrace();
		}
		
		return ok;
	}
	
	/**
	 * Execute select sql query and get List<List<Object>> as result ...
	 * 
	 * @param sqelectSql
	 * @param sqlParameters
	 * @return
	 */
	public List<List<Object>> executeQueryPS(String sqelectSql, Object ... sqlParameters){
		List<List<Object>> data = new ArrayList<>();
		
		try {
			Connection connection = getConnection();
			
			PreparedStatement pStmt = connection.prepareStatement(sqelectSql);
			
			for (int i=0; i<sqlParameters.length; i++) {
				pStmt.setObject(i+1, sqlParameters[i]);
			}
			
			ResultSet rows = pStmt.executeQuery();
			
			int nbColumns = rows.getMetaData().getColumnCount();
			
			while (rows.next()) {
				List<Object> line = new ArrayList<>();
				
				for (int i=0; i<nbColumns; i++) {
					line.add( rows.getObject( i+1 ) );
				}
				
				data.add(line);
			}
			
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public Object getValue(String selectSql, Object ... sqlParameters) {
		Object value = null;
		
		List<List<Object>> data = executeQueryPS(selectSql, sqlParameters);
		if (data.size()>0) {
			value = data.get(0).get(0);
		}
		
		return value;
	}
	
}
