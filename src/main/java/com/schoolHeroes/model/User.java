package com.schoolHeroes.model;

import java.security.Identity;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class User {

	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String description;
	private Date createdAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void createAndSaveToDB() throws SQLException {
		Database db = Database.getInstance();
		Connection connexion = db.getConnexion();

		Statement st = connexion.createStatement();
		ResultSet result = st.executeQuery("SELECT max(id) FROM public.users;");
		result.next();
		int lastId = result.getInt(1) + 1;
		this.id = lastId;

		PreparedStatement pst = connexion.prepareStatement(
				"INSERT INTO public.users(id, first_name, last_name, username, password, created_at, description, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
		pst.setInt(1, lastId);
		pst.setString(2, this.firstName);
		pst.setString(3, this.lastName);
		pst.setString(4, this.username);
		pst.setString(5, this.password);
		pst.setDate(6, this.createdAt);
		pst.setString(7, this.description);
		pst.setString(8, this.email);
		pst.execute();
	}

	public void updateAndSaveToDB() throws Exception {
		if (this.id == 0) {
			throw new Exception("This user don't have a id");
		}

		Database db = Database.getInstance();
		Connection connexion = db.getConnexion();

		PreparedStatement pst = connexion.prepareStatement(
				"UPDATE public.users SET first_name=?, last_name=?, username=?, created_at=?, description=?, email=? WHERE id=?;");
		pst.setString(1, this.firstName);
		pst.setString(2, this.lastName);
		pst.setString(3, this.username);
		pst.setDate(4, this.createdAt);
		pst.setString(5, this.description);
		pst.setString(6, this.email);
		pst.setInt(7, this.id);
		pst.execute();
	}

	public void changePasswordAndSaveToDB() throws Exception {
		if (this.id == 0) {
			throw new Exception("This user don't have a id");
		}

		Database db = Database.getInstance();
		Connection connexion = db.getConnexion();

		PreparedStatement pst = connexion.prepareStatement("UPDATE public.users SET password=? WHERE id=?;");
		pst.setString(1, this.password);
		pst.setInt(2, this.id);
		pst.execute();
	}

	public void deleteFromDB() throws Exception {
		if (this.id == 0) {
			throw new Exception("This user don't have a id");
		}

		Database db = Database.getInstance();
		Connection connexion = db.getConnexion();

		PreparedStatement pst = connexion.prepareStatement("DELETE FROM public.users WHERE id=?;");
		pst.setInt(1, this.id);
		pst.execute();
	}
	
	public static boolean isUsernameUnique(String username) throws Exception {
		Database db = Database.getInstance();
		Connection connexion = db.getConnexion();
		
		PreparedStatement pst = connexion.prepareStatement("SELECT username FROM public.users WHERE username=?;");
		pst.setString(1, username);
		ResultSet resultSet = pst.executeQuery();
		
		if(resultSet.next())  {
			return false;
		} else {
			return true;
		}
		
	}
	
	public static boolean isUsernameUnique(String username, int id) throws Exception {
		Database db = Database.getInstance();
		Connection connexion = db.getConnexion();
		
		PreparedStatement pst = connexion.prepareStatement("SELECT username FROM public.users WHERE username=? AND id!=?;");
		pst.setString(1, username);
		pst.setInt(2, id);
		ResultSet resultSet = pst.executeQuery();
		
		if(resultSet.next())  {
			return false;
		} else {
			return true;
		}
		
	}
	
	public static User getUser(String username, char[] password) throws Exception {
		Database db = Database.getInstance();
		Connection connexion = db.getConnexion();
		
		PreparedStatement pst = connexion.prepareStatement("SELECT * FROM public.users WHERE username=?;");
		pst.setString(1, username);
		ResultSet resultSet = pst.executeQuery();
		
		if(resultSet.next()) {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			boolean isCorrectPassword = bCryptPasswordEncoder.matches(new String(password), resultSet.getString("password"));
			
			if(isCorrectPassword) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setUsername(resultSet.getString("username"));
				user.setDescription(resultSet.getString("description"));
				user.setEmail(resultSet.getString("email"));
				user.setCreatedAt(resultSet.getDate("created_at"));
				
				return user;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", description=" + description + ", createdAt="
				+ createdAt + "]";
	}

	
	
}
