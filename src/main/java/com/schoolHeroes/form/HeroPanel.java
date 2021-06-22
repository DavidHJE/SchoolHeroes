package com.schoolHeroes.form;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.postgresql.core.Utils;

import com.schoolHeroes.model.ArcherClassHero;
import com.schoolHeroes.model.ClassHero;
import com.schoolHeroes.model.Hero;
import com.schoolHeroes.model.TamerClassHero;
import com.schoolHeroes.model.WarriorClassHero;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class HeroPanel extends JPanel implements Observer {
	private JPanel thisPanel;
	private JTable heroesTable;
	
	private JFileChooser fileChooser;
	
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
		thisPanel = this; // HeroPanel.this
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
		
		fileChooser = new JFileChooser();
		
		
		heroListenerList();
	}
	
	private void heroListenerList() {
		/**
		 * TODO :: ne sert pas a grand chose pour le moment
		 */
		heroesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
//					System.out.println("value changed");
//					int row = heroesTable.getSelectedRow();
//					Hero hero = heroTableModel.getHeroAt(row);
//					System.out.println(hero);
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
				Hero hero = null;
				int row = heroesTable.getSelectedRow();
				if(row == -1) {
					row = 0;
					heroesTable.changeSelection(row,0,false,false);
				}
				
				System.out.println(row);
				try {
					hero = heroTableModel.getHeroAt(row);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
					// e2.printStackTrace();
				}
				
				System.out.println(hero);
				
				EditHeroForm dialog = new EditHeroForm(listHeroes, hero);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		btnDeleteHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("delete");
				
				int row = heroesTable.getSelectedRow();
				Hero hero;
				if(row == -1) {
					JOptionPane.showMessageDialog(thisPanel,
							"Aucun héros selectionné", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					hero = heroTableModel.getHeroAt(row);
					Object[] options = {"OUI",
                    "Non"};
					int response = JOptionPane.showOptionDialog(thisPanel,
						    "Voulez vous vraiment suprimer le hero " + hero.getName() + " ?",
						    "Supprimer ce héros",
						    JOptionPane.YES_NO_OPTION,
						    JOptionPane.QUESTION_MESSAGE,
						    null,     //do not use a custom Icon
						    options,  //the titles of buttons
						    options[1]); //default button title
					System.out.println(response);
					if (response == 1) {
						return;
					}
				}
				
				System.out.println(hero);
				try {
					listHeroes.remove(hero);
					hero.deleteFromDB();
					JOptionPane.showMessageDialog(thisPanel, "Le héros a été supprimer avec succès !");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		btnExporter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("export");
				try {
					int restult = fileChooser.showSaveDialog(thisPanel);

					if(restult == JFileChooser.APPROVE_OPTION) {
						//String fileName = fileChooser.getSelectedFile().getName();
						File file = fileChooser.getSelectedFile();
						String fileName = valideOrSetCSV(file);
						String pathFile = file.getParent();
						System.out.println(pathFile + File.separator + fileName);
						
						listHeroes.convertToCSV(pathFile + File.separator + fileName);
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		btnSearchHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("search");
				
				listHeroes.filterHeroesName(textField.getText());
			}
		});
		
	}

	public void update(String action) {

		// TODO mettre reset le tableau
		System.out.println("notifications");

		if(action.equals("add")) {
			System.out.println("add in datatable");
		} else if (action.equals("delete")) {
			System.out.println("delete hero");
		}
		
		heroTableModel.fireTableDataChanged();
		
	}
	
	/*
     * return the file name with a good extention
     */  
    public String valideOrSetCSV(File file) {
        String ext = null;
        String nameFile = file.getName();
        int i = nameFile.lastIndexOf('.');

        if (i > 0 &&  i < nameFile.length() - 1) {
            ext = nameFile.substring(i+1).toLowerCase();
        } else if (i >= nameFile.length() -1) {
        	nameFile = nameFile.substring(0, nameFile.length() -1);
        }
        
        if(ext == null || !ext.equalsIgnoreCase("csv")) {
        	nameFile = nameFile + ".csv";
        } 
        
        return nameFile;
    }
}
