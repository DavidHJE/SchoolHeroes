package com.schoolHeroes.form;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.schoolHeroes.model.ArcherClassHero;
import com.schoolHeroes.model.ClassHero;
import com.schoolHeroes.model.Hero;
import com.schoolHeroes.model.TamerClassHero;
import com.schoolHeroes.model.WarriorClassHero;

public class EditHeroForm extends JDialog {

	JPanel mainContentJPanel = new JPanel();
	private JTextField nameField;
	// TODO refaire correctement la combobox
//	private JComboBox<ClassHero> classHeroComboBox;
	private JComboBox classHeroComboBox;
	private JTextField pathIconHero;
	private JFormattedTextField attackField;
	private JFormattedTextField defenceField;
	private JFormattedTextField evasionField;
	private JFormattedTextField escapeField;
	private JFormattedTextField lifeField;
	private JFormattedTextField experienceField;
	private JFormattedTextField levelField;

	private JLabel nameLabel;
	private JLabel classLabel;
	private JLabel pathIconHeroLabel;
	private JLabel attackLabel;
	private JLabel defenceLabel;
	private JLabel evasionLabel;
	private JLabel escapeLabel;
	private JLabel lifeLabel;
	private JLabel experienceLabel;
	private JLabel levelLabel;

	private JButton valideBtn;
	private JButton cancelBtn;

	private NumberFormat amountFormat;

	/**
	 * Create the dialog. TODO :: list des héros puis ajouter
	 */
	public EditHeroForm(HeroList listHeros, Hero hero) {
		setTitle("Ajouter un Hero");
		setResizable(false);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setSize(500, 520);
		setLocationRelativeTo(null);

		mainContentJPanel.setLayout(null);
		setContentPane(mainContentJPanel);

		nameLabel = new JLabel("Nom du hero");
		nameLabel.setBounds(25, 15, 200, 25);
		mainContentJPanel.add(nameLabel);

		nameField = new JTextField();
		nameField.setBounds(260, 15, 200, 25);
		nameField.setText(hero.getName());
		mainContentJPanel.add(nameField);

		classLabel = new JLabel("Classe du hero");
		classLabel.setBounds(25, 55, 200, 25);
		mainContentJPanel.add(classLabel);

		// TODO retire les commentaires
//		classHeroComboBox = new JComboBox<ClassHero>();
//		classHeroComboBox.setBounds(260, 55, 200, 25);
//		classHeroComboBox.addItem(new WarriorClassHero());
//		classHeroComboBox.addItem(new ArcherClassHero());
//		classHeroComboBox.addItem(new TamerClassHero());
//		classHeroComboBox.setSelectedItem(hero.getClassHero().getName()); // not works
//		mainContentJPanel.add(classHeroComboBox);
		
		
//		WarriorClassHero warrior = new WarriorClassHero();
//		ArcherClassHero archer = new ArcherClassHero();
//		TamerClassHero tamer = new TamerClassHero();
//		ClassHero[] classHeroes = new ClassHero[] {warrior, archer, tamer};
//		classHeroComboBox = new JComboBox((new DefaultComboBoxModel(classHeroes)));
//		classHeroComboBox.setRenderer(new DefaultListCellRenderer() {
//            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//                if(value instanceof ClassHero){
//                	ClassHero classHero = (ClassHero) value;
//                    setText(classHero.getName());
//                }
//                return this;
//            }
//        } );
//		classHeroComboBox.setBounds(260, 55, 200, 25);
//		mainContentJPanel.add(classHeroComboBox);
		
		pathIconHeroLabel = new JLabel("Icon du hero");
		pathIconHeroLabel.setBounds(25, 95, 200, 25);
		mainContentJPanel.add(pathIconHeroLabel);

		pathIconHero = new JTextField(); // File chose img
		pathIconHero.setBounds(260, 95, 200, 25);
		pathIconHero.setText(hero.getPathIconHero());
		mainContentJPanel.add(pathIconHero);

		attackLabel = new JLabel("Attaque du hero"); // File chose img
		attackLabel.setBounds(25, 135, 200, 25);
		mainContentJPanel.add(attackLabel);

		attackField = new JFormattedTextField(amountFormat);
		attackField.setBounds(260, 135, 200, 25);
		attackField.setText(Integer.toString(hero.getAttack()));
		mainContentJPanel.add(attackField);

		defenceLabel = new JLabel("Défense du hero"); // File chose img
		defenceLabel.setBounds(25, 175, 200, 25);
		mainContentJPanel.add(defenceLabel);

		defenceField = new JFormattedTextField(amountFormat);
		defenceField.setBounds(260, 175, 200, 25);
		defenceField.setText(Integer.toString(hero.getDefence()));
		mainContentJPanel.add(defenceField);

		evasionLabel = new JLabel("Esquive du hero"); // File chose img
		evasionLabel.setBounds(25, 215, 200, 25);
		mainContentJPanel.add(evasionLabel);

		evasionField = new JFormattedTextField(amountFormat);
		evasionField.setBounds(260, 215, 200, 25);
		evasionField.setText(Integer.toString(hero.getEvasion()));
		mainContentJPanel.add(evasionField);

		escapeLabel = new JLabel("Vitesse de fuite du hero"); // File chose img
		escapeLabel.setBounds(25, 255, 200, 25);
		mainContentJPanel.add(escapeLabel);

		escapeField = new JFormattedTextField(amountFormat);
		escapeField.setBounds(260, 255, 200, 25);
		escapeField.setText(Integer.toString(hero.getEscape()));
		mainContentJPanel.add(escapeField);

		lifeLabel = new JLabel("Point de vie du hero"); // File chose img
		lifeLabel.setBounds(25, 295, 200, 25);
		mainContentJPanel.add(lifeLabel);

		lifeField = new JFormattedTextField(amountFormat);
		lifeField.setBounds(260, 295, 200, 25);
		lifeField.setText(Integer.toString(hero.getLife()));
		mainContentJPanel.add(lifeField);

		experienceLabel = new JLabel("Point d'expérience du hero"); // File chose img
		experienceLabel.setBounds(25, 335, 200, 25);
		mainContentJPanel.add(experienceLabel);

		experienceField = new JFormattedTextField(amountFormat);
		experienceField.setBounds(260, 335, 200, 25);
		experienceField.setText(Integer.toString(hero.getExperience()));
		mainContentJPanel.add(experienceField);

		levelLabel = new JLabel("Niveau du hero"); // File chose img
		levelLabel.setBounds(25, 375, 200, 25);
		mainContentJPanel.add(levelLabel);

		levelField = new JFormattedTextField(amountFormat);
		levelField.setBounds(260, 375, 200, 25);
		levelField.setText(Integer.toString(hero.getLevel()));
		mainContentJPanel.add(levelField);

		valideBtn = new JButton("Modier Hero");
		valideBtn.setBounds(25, 430, 200, 25);
		mainContentJPanel.add(valideBtn);

		cancelBtn = new JButton("Annuler");
		cancelBtn.setBounds(260, 430, 200, 25);
		mainContentJPanel.add(cancelBtn);

		addHeroListenerList(listHeros, hero);
	}

	private void addHeroListenerList(final HeroList listHeros, final Hero hero) {

		valideBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().equals("")) {
					JOptionPane.showMessageDialog(mainContentJPanel, "Le nom du héros ne doit pas être vide !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					hero.setName(nameField.getText());
				}

				// TODO retire les commentaires
//				ClassHero classHero;
//				if (classHeroComboBox.getSelectedItem() instanceof ClassHero) {
//					classHero = (ClassHero) classHeroComboBox.getSelectedItem();
//					hero.setClassHero(classHero);
//				} else {
//					JOptionPane.showMessageDialog(mainContentJPanel, "Vous n'avez pas sélectionné une classe valide !",
//							"Erreur", JOptionPane.ERROR_MESSAGE);
//					return;
//				}

				if (!pathIconHero.getText().equals("")) {
					hero.setPathIconHero(pathIconHero.getText());
				}

				if (!attackField.getText().equals("")) {

					try {
						hero.setAttack(Integer.parseInt(attackField.getText()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(mainContentJPanel,
								"La saisie pour l'attaque du héros est incorrecte !", "Erreur",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				if (!defenceField.getText().equals("")) {
					try {
						hero.setDefence(Integer.parseInt(defenceField.getText()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(mainContentJPanel,
								"La saisie pour la défense du héros est incorrecte !", "Erreur",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				if (!evasionField.getText().equals("")) {
					try {
						hero.setEvasion(Integer.parseInt(evasionField.getText()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(mainContentJPanel,
								"La saisie pour l'esquive du héros est incorrecte !", "Erreur",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				if (!escapeField.getText().equals("")) {
					try {
						hero.setEscape(Integer.parseInt(escapeField.getText()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(mainContentJPanel,
								"La saisie pour la vitesse de fuite du héros est incorrecte !", "Erreur",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				if (!lifeField.getText().equals("")) {
					try {
						hero.setLife(Integer.parseInt(lifeField.getText()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(mainContentJPanel,
								"La saisie pour les points de vie du héros est incorrecte !", "Erreur",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				if (!experienceField.getText().equals("")) {
					try {
						hero.setExperience(Integer.parseInt(experienceField.getText()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(mainContentJPanel,
								"La saisie pour les points d'expérience du héros est incorrecte !", "Erreur",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				if (!levelField.getText().equals("")) {
					try {
						hero.setLevel(Integer.parseInt(levelField.getText()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(mainContentJPanel,
								"La saisie pour la niveau du héros est incorrecte !", "Erreur",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				try {
					hero.updateAndSaveToDB();
					listHeros.notifyUptadeHero();

					JOptionPane.showMessageDialog(mainContentJPanel, "Le héros a été modifier avec succès !");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}
}
