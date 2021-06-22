package com.schoolHeroes.form;

import com.schoolHeroes.model.Hero;

public interface Sujet {

	public void addObserver(Observer o);

	public void removeObserver(Observer o);

	public void notifyObservers(String action);
	
}
