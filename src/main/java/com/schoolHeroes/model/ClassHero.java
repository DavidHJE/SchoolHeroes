package com.schoolHeroes.model;

import com.schoolHeroes.model.interfaceModel.ActionsHero;

public abstract class ClassHero implements ActionsHero {
	protected String name;
	protected String theClassName;

	protected Number ratioAttack = 1;
	protected Number ratioDefence = 1;
	protected Number ratioEvasion = 1;
	protected Number ratioEscape = 1;

	public String attack(int attack) {
		return null;
	}

	public String defend(int defence) {
		return null;
	}

	public String dodge(int evasion) {
		return null;
	}

	public String escape(int escape) {
		return null;
	}

	public String getName() {
		return name;
	}

	public String getTheClassName() {
		return theClassName;
	}

	public String skill1(Hero hero) {
		return null;
	}

	public String skill2(Hero hero) {
		return null;
	}

	public String skill3(Hero hero) {
		return null;
	}

	public String skill4(Hero hero) {
		return null;
	}

	public String skill5(Hero hero) {
		return null;
	}

}
