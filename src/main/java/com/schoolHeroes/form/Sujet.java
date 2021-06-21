package com.schoolHeroes.form;

public interface Sujet {

	public void addObserver(Observer o);

	public void removeObserver(Observer o);

	public void notifyObservers();
	
}
