package com.schoolHeroes.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private static Database instance;
	private Connection connexion;

	private Database() {
		try {
			Class.forName("org.postgresql.Driver");
			this.connexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/school_Heroes", "david", "david");

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
	
	public int getTheNextIdHoroes() throws SQLException {
		Statement st= this.connexion.createStatement();
        ResultSet result = st.executeQuery("SELECT max(id) FROM public.heroes;");
        result.next();
        int id = result.getInt(1) + 1;
        
		return id;
	}
	
}
