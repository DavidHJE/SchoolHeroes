package com.schoolHeroes.form;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.schoolHeroes.model.ArcherClassHero;
import com.schoolHeroes.model.ClassHero;
import com.schoolHeroes.model.Hero;
import com.schoolHeroes.model.TamerClassHero;
import com.schoolHeroes.model.WarriorClassHero;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HeroPanel extends JPanel implements Observer {
	private JTable heroesTable;
	
	private JTextField textField;
	private JButton btnSearchHero;
	
	private JButton btnAddHero;
	private JButton btnUpdateHero;
	private JButton btnDeleteHero;

	private JButton btnExporter;
	
	private HeroTableModel heroTableModel;
	private HeroList listHeroes;
	
	/**
	 * Create the panel.
	 */
	public HeroPanel() {
		// TODO :: add sort and filter
		setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		try {
			listHeroes = Hero.getAllHero();
			listHeroes.addObserver(this);
			
//			for (Hero hero : listHeroes) {
//				System.out.println(hero);
//			}
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		heroTableModel = new HeroTableModel(listHeroes);
		heroesTable = new JTable(heroTableModel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 75, 950, 450);
		scrollPane.setViewportView(heroesTable);
		add(scrollPane);
		
		textField = new JTextField();
		textField.setBounds(25, 25, 200, 25);
		add(textField);
		
		btnSearchHero = new JButton("Rechercher");
		btnSearchHero.setBounds(235, 25, 150, 25);
		add(btnSearchHero);

		btnAddHero = new JButton("Ajouter hero");
		btnAddHero.setBounds(475, 25, 150, 25);
		add(btnAddHero);

		btnUpdateHero = new JButton("Modifier Hero");
		btnUpdateHero.setBounds(650, 25, 150, 25);
		add(btnUpdateHero);

		btnDeleteHero = new JButton("Supprimer Hero");
		btnDeleteHero.setBounds(825, 25, 150, 25);
		add(btnDeleteHero);
		
		btnExporter = new JButton("Exporter");
		btnExporter.setBounds(825, 535, 150, 25);
		add(btnExporter);
		
		heroListenerList();
	}
	
	private void heroListenerList() {
		/**
		 * TODO :: ne sert pas a grand chose pour le moment
		 */
		heroesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
					System.out.println("value changed");
					int row = heroesTable.getSelectedRow();
					Hero hero = heroTableModel.getHeroAt(row);
					System.out.println(hero);
			}
		});
		
		btnAddHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("add");
				AddHeroForm dialog = new AddHeroForm(listHeroes);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
			}
		});
		
		btnUpdateHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("update");
			}
		});
		
		btnDeleteHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("delete");
			}
		});
		
		btnExporter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("export");
			}
		});
		
		btnSearchHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("search");
				
				for (Hero hero : listHeroes) {
					System.out.println(hero);
				}
			}
		});
		
	}

	public void update() {

		// TODO mettre reset le tableau
		System.out.println("notifications");
		
	}
}
