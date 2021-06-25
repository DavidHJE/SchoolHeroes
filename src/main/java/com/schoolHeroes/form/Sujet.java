package com.schoolHeroes.form;

public interface Sujet {

	public void addObserver(Observer o);

	public void notifyObservers(String action);

	public void removeObserver(Observer o);

}
