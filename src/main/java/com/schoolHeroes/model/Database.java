package com.schoolHeroes.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static Database instance;
	private Connection connexion;

	private Database() {
		try {
			Class.forName("org.postgresql.Driver");
			this.connexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/school_Heroes", "david",
					"david");

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}

		return instance;
	}
	
	public Connection getConnexion() {
		return connexion;
	}

}
