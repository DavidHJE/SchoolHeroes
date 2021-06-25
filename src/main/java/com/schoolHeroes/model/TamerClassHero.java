package com.schoolHeroes.model;

public class TamerClassHero extends ClassHero {
	private String name = "Donpteur";
	private String theClassName = "com.schoolHeroes.model.TamerClassHero";

	private Double ratioAttack = 1.0;
	private Double ratioDefence = .8;
	private Double ratioEvasion = 1.2;
	private Double ratioEscape = 1.521;

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

		return "Attaque au point";
	}

	public String defend(int defence) {
		if ((defence - this.ratioDefence) < 40) {
			return "Défence raté";
		}

		return "defendre avec tamer";
	}

	public String dodge(int evasion) {
		if ((evasion / this.ratioEvasion) < 40) {
			return "evation raté";
		}

		return "esquive tamer";
	}

	public String escape(int escape) {
		if ((escape + this.ratioEscape) < 40) {
			return "fuite annulé";
		}
		
		return "escape tamer";
	}

	@Override
	public String skill1(Hero hero) {
		return hero.getName() + "Ok invoquation !";
	}
}