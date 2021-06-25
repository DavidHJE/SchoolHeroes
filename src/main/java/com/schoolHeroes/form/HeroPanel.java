package com.schoolHeroes.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.schoolHeroes.model.Hero;

@SuppressWarnings("serial")
public class HeroPanel extends JPanel implements Observer {
	private JTextField searchField;
	private JButton btnSearchHero;

	private JButton btnAddHero;
	private JButton btnUpdateHero;
	private JButton btnDeleteHero;

	private JTable heroesTable;
	private JScrollPane heroesScrollPane;

	private JButton btnExport;
	private JFileChooser fileChooser;

	private HeroTableModel heroTableModel;
	private HeroList listHeroes;

	public HeroPanel() {
		setBounds(0, 0, 1000, 600);
		setLayout(null);

		try {
			listHeroes = Hero.getAllHero();
			listHeroes.addObserver(this);
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

		heroesScrollPane = new JScrollPane();
		heroesScrollPane.setBounds(25, 75, 950, 450);
		heroesScrollPane.setViewportView(heroesTable);
		add(heroesScrollPane);

		searchField = new JTextField();
		searchField.setBounds(25, 25, 200, 25);
		add(searchField);

		btnSearchHero = new JButton("Rechercher");
		btnSearchHero.setBounds(235, 25, 150, 25);
		add(btnSearchHero);

		btnAddHero = new JButton("Ajouter");
		btnAddHero.setBounds(475, 25, 150, 25);
		add(btnAddHero);

		btnUpdateHero = new JButton("Modifier");
		btnUpdateHero.setBounds(650, 25, 150, 25);
		add(btnUpdateHero);

		btnDeleteHero = new JButton("Supprimer");
		btnDeleteHero.setBounds(825, 25, 150, 25);
		add(btnDeleteHero);

		btnExport = new JButton("Exporter CSV");
		btnExport.setBounds(825, 535, 150, 25);
		add(btnExport);

		fileChooser = new JFileChooser();

		heroListenerList();
	}

	/*
	 * Check and add the CSV extension if missing
	 */
	public String checkCorrigeExtensionCSV(File file) {
		String ext = null;
		String nameFile = file.getName();
		int i = nameFile.lastIndexOf('.');

		if (i > 0 && i < nameFile.length() - 1) {
			ext = nameFile.substring(i + 1).toLowerCase();
		} else if (i >= nameFile.length() - 1) {
			nameFile = nameFile.substring(0, nameFile.length() - 1);
		}

		if (ext == null || !ext.equalsIgnoreCase("csv")) {
			nameFile = nameFile + ".csv";
		}

		return nameFile;
	}

	private void heroListenerList() {
		btnSearchHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listHeroes.filterHeroesName(searchField.getText());
			}
		});

		btnAddHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddHeroForm dialog = new AddHeroForm(listHeroes);
				dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});

		btnUpdateHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = heroesTable.getSelectedRow();
				if (row == -1) {
					row = 0;
					heroesTable.changeSelection(row, 0, false, false);
				}

				Hero hero = null;
				try {
					hero = heroTableModel.getHeroAt(row);
				} catch (Exception e2) {
					e2.printStackTrace();
				}

				EditHeroForm dialog = new EditHeroForm(listHeroes, hero);
				dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});

		btnDeleteHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = heroesTable.getSelectedRow();
				Hero hero;

				if (row == -1) {
					JOptionPane.showMessageDialog(HeroPanel.this, "Aucun héros selectionné", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					hero = heroTableModel.getHeroAt(row);
					Object[] options = { "OUI", "Non" };

					int response = JOptionPane.showOptionDialog(HeroPanel.this,
							"Voulez vous vraiment suprimer le hero " + hero.getName() + " ?", "Supprimer ce héros",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, // do not use a custom Icon
							options, // the titles of buttons
							options[1]); // default button title

					if (response == 1) {
						return;
					}
				}

				try {
					listHeroes.remove(hero);
					hero.deleteFromDB();
					JOptionPane.showMessageDialog(HeroPanel.this, "Le héros a été supprimer avec succès !");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int restult = fileChooser.showSaveDialog(HeroPanel.this);

					if (restult == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						String fileName = checkCorrigeExtensionCSV(file);
						String pathFile = file.getParent();

						try {
							listHeroes.convertToCSV(pathFile + File.separator + fileName);
							JOptionPane.showMessageDialog(HeroPanel.this, "Export réussi !");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

	}

	public void update(String action) {
		heroTableModel.fireTableDataChanged();
	}
}
