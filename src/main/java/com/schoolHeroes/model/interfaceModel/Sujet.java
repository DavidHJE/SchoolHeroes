package com.schoolHeroes.model.interfaceModel;

public interface Sujet {

	public void addObserver(Observer o);

	public void notifyObservers(String action);

	public void removeObserver(Observer o);

}
