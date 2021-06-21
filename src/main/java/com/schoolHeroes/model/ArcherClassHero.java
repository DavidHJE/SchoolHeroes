package com.schoolHeroes.model;

public class ArcherClassHero extends ClassHero implements ActionsHero {
	private String name = "Archer";
	private String theClassName = "com.schoolHeroes.model.ArcherClassHero";

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

		return "Attaque a l'arc";
	}

	public String defend(int defence) {
		if ((defence - this.ratioDefence) < 40) {
			return "Défence raté";
		}

		return "defendre avec archer";
	}

	public String dodge(int evasion) {
		if ((evasion / this.ratioEvasion) < 40) {
			return "evation raté";
		}

		return "esquive archer";
	}

	public String escape(int escape) {
		if ((escape + this.ratioEscape) < 40) {
			return "fuite annulé";
		}

		return "escape archer";
	}


	@Override
	public String toString() {
		return this.name;
	}
}
