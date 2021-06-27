package com.schoolHeroes.model;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;

public class Hero {
	private String name;
	private ClassHero classHero;
	private String pathIconHero = "schoolHeroes/src/main/java/com/schoolHeroes/images/avatar1.png";
	private ImageIcon iconHero = new ImageIcon(this.pathIconHero);

	private int id = 0;
	private int attack = 10;
	private int defence = 10;
	private int evasion = 10;
	private int escape = 10;
	private int life = 10;
	private int level = 1;
	private int experience = 0;

	public Hero(String name, ClassHero classHero) {
		this.name = name;
		this.classHero = classHero;
	}

	/**
	 * Getters and Setters
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassHero getClassHero() {
		return classHero;
	}

	public void setClassHero(ClassHero classHero) {
		this.classHero = classHero;
	}

	public String getPathIconHero() {
		return pathIconHero;
	}

	public void setPathIconHero(String pathIconHero) {
		this.pathIconHero = pathIconHero;
	}

	public ImageIcon getIconHero() {
		return iconHero;
	}

	public void setIconHero(ImageIcon iconHero) {
		this.iconHero = iconHero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getEvasion() {
		return evasion;
	}

	public void setEvasion(int evasion) {
		this.evasion = evasion;
	}

	public int getEscape() {
		return escape;
	}

	public void setEscape(int escape) {
		this.escape = escape;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	/**
	 * Actions for Heroes
	 */
	public String attack() {
		return classHero.attack(this.attack);
	}

	public String defend() {
		return classHero.defend(this.defence);
	}

	public String dodge() {
		return classHero.dodge(this.evasion);
	}

	public String escape() {
		return classHero.escape(this.escape);
	}

	public String skill1() {
		return classHero.skill1(this);
	}

	public String skill2() {
		return classHero.skill2(this);
	}

	public String skill3() {
		return classHero.skill3(this);
	}

	public String skill4() {
		return classHero.skill4(this);
	}

	public String skill5() {
		return classHero.skill5(this);
	}

	@Override
	public String toString() {
		return "Hero [name=" + name + ", classHero=" + classHero + ", pathIconHero=" + pathIconHero + ", iconHero="
				+ iconHero + ", id=" + id + ", attack=" + attack + ", defence=" + defence + ", evasion=" + evasion
				+ ", escape=" + escape + ", life=" + life + ", level=" + level + ", experience=" + experience + "]";
	}

	public void createAndSaveToDB() throws Exception {
		Database db = Database.getInstance();
		Connection connexion = db.getConnexion();

		Statement st = connexion.createStatement();
		ResultSet result = st.executeQuery("SELECT max(id) FROM public.heroes;");
		
		int lastId = 1;
		if (result.next()) {
			lastId = result.getInt(1) + 1;
		}
		this.id = lastId;

		PreparedStatement pst = connexion.prepareStatement(
				"INSERT INTO public.heroes(name, id, class, path_icon_hero, life, attack, defence, evasion, escape, experience, level) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		pst.setString(1, this.name);
		pst.setInt(2, this.id);
		pst.setString(3, classHero.getTheClassName());
		pst.setString(4, this.pathIconHero);
		pst.setInt(5, this.life);
		pst.setInt(6, this.attack);
		pst.setInt(7, this.defence);
		pst.setInt(8, this.evasion);
		pst.setInt(9, this.escape);
		pst.setInt(10, this.experience);
		pst.setInt(11, this.level);
		pst.execute();
	}

	public void updateAndSaveToDB() throws Exception {
		if (this.id == 0) {
			throw new Exception("This hero don't have a id");
		}

		Database db = Database.getInstance();
		Connection connexion = db.getConnexion();

		PreparedStatement pst = connexion.prepareStatement(
				"UPDATE public.heroes SET name=?, class=?, path_icon_hero=?, life=?, attack=?, defence=?, evasion=?, escape=?, experience=?, level=? WHERE id=?;");
		pst.setString(1, this.name);
		pst.setString(2, classHero.getTheClassName());
		pst.setString(3, this.pathIconHero);
		pst.setInt(4, this.life);
		pst.setInt(5, this.attack);
		pst.setInt(6, this.defence);
		pst.setInt(7, this.evasion);
		pst.setInt(8, this.escape);
		pst.setInt(9, this.experience);
		pst.setInt(10, this.level);
		pst.setInt(11, this.id);
		pst.execute();
	}

	public void deleteFromDB() throws Exception {
		if (this.id == 0) {
			throw new Exception("This hero don't have a id");
		}

		Database db = Database.getInstance();
		Connection connexion = db.getConnexion();

		PreparedStatement pst = connexion.prepareStatement("DELETE FROM public.heroes WHERE id=?;");
		pst.setInt(1, this.id);
		pst.execute();
	}

	public static HeroList getAllHero()
			throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Database db = Database.getInstance();
		Connection connexion = db.getConnexion();

		Statement st = connexion.createStatement();
		ResultSet result = st.executeQuery("SELECT * FROM public.heroes");
		HeroList listHeroes = new HeroList();

		while (result.next()) {
			ClassHero classHero = (ClassHero) Class.forName(result.getString("class")).getDeclaredConstructor()
					.newInstance();

			Hero hero = new Hero(result.getString("name"), classHero);
			hero.setId(result.getInt("id"));
			hero.setPathIconHero(result.getString("path_icon_hero"));
			hero.setLife(result.getInt("life"));
			hero.setAttack(result.getInt("attack"));
			hero.setDefence(result.getInt("defence"));
			hero.setEvasion(result.getInt("evasion"));
			hero.setEscape(result.getInt("escape"));
			hero.setExperience(result.getInt("experience"));
			hero.setLevel(result.getInt("level"));

			listHeroes.add(hero);
		}

		return listHeroes;
	}

}
