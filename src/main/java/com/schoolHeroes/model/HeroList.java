package com.schoolHeroes.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.schoolHeroes.model.interfaceModel.Observer;
import com.schoolHeroes.model.interfaceModel.Sujet;

public class HeroList implements List<Hero>, Sujet {
	private ArrayList<Hero> heroesList = new ArrayList<Hero>();
	private ArrayList<Hero> allHeroesList = new ArrayList<Hero>();

	private ArrayList<Observer> observerList;

	public HeroList() {
		heroesList = new ArrayList<Hero>();
		observerList = new ArrayList<Observer>();
	}

	public void filterHeroesName(String name) {
		heroesList.removeAll(heroesList);

		for (Hero hero : allHeroesList) {

			if ((hero.getName()).toLowerCase().contains(name.toLowerCase())) {
				heroesList.add(hero);
			}
		}

		notifyObservers("filter");
	}

	public void notifyUptadeHero() {
		notifyObservers("upadte");
	}

	public void convertToCSV(String pathFile) throws IOException {
		ArrayList<String> dataLines = new ArrayList<String>();
		String headers = "Id,Nom,Classe,Niveau,Expérience,Point de vie,Attaque,Défense,Esquive,Fuite";

		dataLines.add(headers);

		for (Hero heroData : heroesList) {
			String data = heroData.getId() + "," + heroData.getName() + "," + heroData.getClassHero().getName() + ","
					+ heroData.getLevel() + "," + heroData.getExperience() + "," + heroData.getLife() + ","
					+ heroData.getAttack() + "," + heroData.getDefence() + "," + heroData.getEvasion() + ","
					+ heroData.getEvasion();
			dataLines.add(data);
		}

		File file = new File(pathFile);

		FileWriter fileWriter = new FileWriter(file);

		for (String dataLine : dataLines) {
			dataLine = dataLine + "\n";
			fileWriter.write(dataLine);
		}
		fileWriter.close();
	}

	public int size() {
		return heroesList.size();
	}

	public boolean isEmpty() {
		return heroesList.isEmpty();
	}

	public boolean contains(Object o) {
		return heroesList.contains(o);
	}

	public Iterator<Hero> iterator() {
		return heroesList.iterator();
	}

	public Object[] toArray() {
		return heroesList.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return heroesList.toArray(a);
	}

	public boolean add(Hero e) {
		if (heroesList.add(e)) {
			allHeroesList.add(e);
			notifyObservers("add");
			return true;
		} else {
			return false;
		}
	}

	public boolean remove(Hero e) {
		if (heroesList.remove(e)) {
			allHeroesList.remove(e);
			notifyObservers("remove");
			return true;
		} else {
			return false;
		}
	}

	public boolean remove(Object o) {
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		return heroesList.containsAll(c);
	}

	public boolean addAll(Collection<? extends Hero> c) {
		return heroesList.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends Hero> c) {
		return heroesList.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c) {
		return heroesList.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return heroesList.retainAll(c);
	}

	public void clear() {
		heroesList.clear();
	}

	public Hero get(int index) {
		return heroesList.get(index);
	}

	public Hero set(int index, Hero element) {
		return heroesList.set(index, element);
	}

	public void add(int index, Hero element) {
		heroesList.add(index, element);
	}

	public Hero remove(int index) {
		return heroesList.remove(index);
	}

	public int indexOf(Object o) {
		return heroesList.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return heroesList.lastIndexOf(o);
	}

	public ListIterator<Hero> listIterator() {
		return null;
	}

	public ListIterator<Hero> listIterator(int index) {
		return null;
	}

	public List<Hero> subList(int fromIndex, int toIndex) {
		return null;
	}

	public void addObserver(Observer o) {
		observerList.add(o);
	}

	public void removeObserver(Observer o) {
		observerList.remove(o);
	}

	public void notifyObservers(String action) {
		for (Observer o : observerList) {
			o.update(action);
		}
	}

}
