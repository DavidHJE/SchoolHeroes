package com.schoolHeroes.model;

public class WarriorClassHero extends ClassHero {
	private String name = "Warrior";
	private String theClassName = "com.schoolHeroes.model.WarriorClassHero";

	private Double ratioAttack = 1.75;
	private Double ratioDefence = 1.4;
	private Double ratioEvasion = 0.215;
	private Double ratioEscape = 0.109;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getTheClassName() {
		return theClassName;
	}

	public String attack(int attack) {
		if ((attack * this.ratioAttack) < 40) {
			return "Attaque raté";
		}

		return "Attaque a l'épée";
	}

	public String defend(int defence) {
		if ((defence - this.ratioDefence) < 40) {
			return "Défence raté";
		}

		return "defend au bouclier";
	}

	public String dodge(int evasion) {
		if ((evasion / this.ratioEvasion) < 40) {
			return "evation raté";
		}

		return "evite avec son armure lourd";
	}

	public String escape(int escape) {
		if ((escape + this.ratioEscape) < 40) {
			return "fuite annulé";
		}

		return "escape avec sans son armure";
	}
}
