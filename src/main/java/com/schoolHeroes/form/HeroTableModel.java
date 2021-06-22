package com.schoolHeroes.form;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.schoolHeroes.model.Hero;

public class HeroTableModel extends AbstractTableModel {
	
	private List<Hero> listHeroes;
	private String[] headers = new String[] {
			"Nom",
			"Class",
			"Niveau",
			"Point de vie",
			"Attaque",
			"Défence",
			"Esquive",
			"Vitesse Fuite"
	};

	public HeroTableModel() {
		super();
	}
	
	public HeroTableModel(List<Hero> heroes) {
		// listHeroes = new ArrayList<Hero>(heroes);
		listHeroes = heroes;
	}

	public int getRowCount() {
		return listHeroes.size();
	}

	public int getColumnCount() {
		return 8;
	}

	@Override
	public String getColumnName(int column) {
		// return super.getColumnName(column);
		return headers[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object value = "Pas de donnée";
        Hero hero = listHeroes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = hero.getName();
                break;
            case 1:
                value = hero.getClassHero();
                break;
            case 2:
                value = hero.getLevel();
                break;
            case 3:
                value = hero.getLife();
                break;
            case 4:
                value = hero.getAttack();
                break;
            case 5:
                value = hero.getDefence();
                break;
            case 6:
                value = hero.getEvasion();
                break;
            case 7:
                value = hero.getEscape();
                break;
        }
        
        return value;
	}
	
	/**
     * This will return the hero at the specified row...
     * @param row
     * @return Hero
     */
    public Hero getHeroAt(int row) {
        return listHeroes.get(row);
    }

	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
//		return super.isCellEditable(rowIndex, columnIndex);
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// super.setValueAt(aValue, rowIndex, columnIndex);
		return;
	}

}
