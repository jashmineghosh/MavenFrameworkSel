package com.autoFramework.myCompany.myProjectFramework.helper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.autoFramework.myCompany.myProjectFramework.helper.logger.LoggerHelper;

public class DataBaseHelper {

	private static String databaseURL = "jdbc:mysql://localhost:3306/QAdbt";
	private static String user = "root";
	private static String password = "root";
	private static  String driver = "com.mysql.jdbc.Driver";
	private static Logger log = LoggerHelper.getLogger(DataBaseHelper.class);
	private static Connection connection ;
//	private static DataBaseHelper instance = null;
	
//	public DataBaseHelper() {
//		connection = getSingleInstanceConnection();
//	}
//
//	public static DataBaseHelper getInstance() {
//		if (instance == null) {
//			instance = new DataBaseHelper();
//		}
//		return instance;
//	}

	private Connection getSingleInstanceConnection() {
		try {
			Class.forName(driver);
			try {
				connection = DriverManager.getConnection(databaseURL, user, password);
				if (connection != null) {
					log.info("Connected to data base..");
					
				}
			} catch (SQLException e) {
				log.error("Failed to create Data base connection.." + e);
			}
		} catch (ClassNotFoundException e) {
			log.info("Driver not found.." + e);
		}
		return connection;
		
	}

//	public Connection getConnection() {
//		return connection;
//	}

	public static ResultSet getResultSet(String dbQuery) {
		DataBaseHelper dp = new DataBaseHelper();
		connection = dp.getSingleInstanceConnection();
//		instance = DataBaseHelper.getInstance();
//		connection = instance.getConnection();
		log.info("Executing query: " + dbQuery);
		try {
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(dbQuery);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
