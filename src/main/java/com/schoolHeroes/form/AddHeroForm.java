package com.schoolHeroes.form;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.schoolHeroes.model.ArcherClassHero;
import com.schoolHeroes.model.ClassHero;
import com.schoolHeroes.model.Hero;
import com.schoolHeroes.model.TamerClassHero;
import com.schoolHeroes.model.WarriorClassHero;

@SuppressWarnings("serial")
public class AddHeroForm extends JDialog {

	JPanel mainContentJPanel = new JPanel();

	private JLabel nameLabel;
	private JTextField nameField;

	private JLabel classLabel;
	private JComboBox<ClassHero> classHeroComboBox;

	private JLabel pathIconHeroLabel;
	private JTextField pathIconHero;

	private JLabel attackLabel;
	private JFormattedTextField attackField;

	private JLabel defenceLabel;
	private JFormattedTextField defenceField;

	private JLabel evasionLabel;
	private JFormattedTextField evasionField;

	private JLabel escapeLabel;
	private JFormattedTextField escapeField;

	private JLabel lifeLabel;
	private JFormattedTextField lifeField;

	private JLabel experienceLabel;
	private JFormattedTextField experienceField;

	private JLabel levelLabel;
	private JFormattedTextField levelField;

	private JButton valideBtn;
	private JButton cancelBtn;

	private NumberFormat amountFormat;

	public AddHeroForm(HeroList listHeros) {
		setTitle("Ajouter un Heros");
		setResizable(false);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setSize(500, 520);
		setLocationRelativeTo(null);

		mainContentJPanel.setLayout(null);
		setContentPane(mainContentJPanel);

		nameLabel = new JLabel("Nom");
		nameLabel.setBounds(25, 15, 200, 25);
		mainContentJPanel.add(nameLabel);

		nameField = new JTextField();
		nameField.setBounds(260, 15, 200, 25);
		mainContentJPanel.add(nameField);

		classLabel = new JLabel("Classe");
		classLabel.setBounds(25, 55, 200, 25);
		mainContentJPanel.add(classLabel);

		WarriorClassHero warrior = new WarriorClassHero();
		ArcherClassHero archer = new ArcherClassHero();
		TamerClassHero tamer = new TamerClassHero();
		ClassHero[] classHeroes = new ClassHero[] { warrior, archer, tamer };
		classHeroComboBox = new JComboBox<ClassHero>(new DefaultComboBoxModel<ClassHero>(classHeroes));
		classHeroComboBox.setRenderer(new HeroClassRenderer());
		classHeroComboBox.setBounds(260, 55, 200, 25);
		mainContentJPanel.add(classHeroComboBox);

		pathIconHeroLabel = new JLabel("Icon");
		pathIconHeroLabel.setBounds(25, 95, 200, 25);
		mainContentJPanel.add(pathIconHeroLabel);

		pathIconHero = new JTextField();
		pathIconHero.setBounds(260, 95, 200, 25);
		mainContentJPanel.add(pathIconHero);

		attackLabel = new JLabel("Attaque");
		attackLabel.setBounds(25, 135, 200, 25);
		mainContentJPanel.add(attackLabel);

		attackField = new JFormattedTextField(amountFormat);
		attackField.setBounds(260, 135, 200, 25);
		mainContentJPanel.add(attackField);

		defenceLabel = new JLabel("Défense");
		defenceLabel.setBounds(25, 175, 200, 25);
		mainContentJPanel.add(defenceLabel);

		defenceField = new JFormattedTextField(amountFormat);
		defenceField.setBounds(260, 175, 200, 25);
		mainContentJPanel.add(defenceField);

		evasionLabel = new JLabel("Esquive");
		evasionLabel.setBounds(25, 215, 200, 25);
		mainContentJPanel.add(evasionLabel);

		evasionField = new JFormattedTextField(amountFormat);
		evasionField.setBounds(260, 215, 200, 25);
		mainContentJPanel.add(evasionField);

		escapeLabel = new JLabel("Vitesse de fuite");
		escapeLabel.setBounds(25, 255, 200, 25);
		mainContentJPanel.add(escapeLabel);

		escapeField = new JFormattedTextField(amountFormat);
		escapeField.setBounds(260, 255, 200, 25);
		mainContentJPanel.add(escapeField);

		lifeLabel = new JLabel("Point de vie");
		lifeLabel.setBounds(25, 295, 200, 25);
		mainContentJPanel.add(lifeLabel);

		lifeField = new JFormattedTextField(amountFormat);
		lifeField.setBounds(260, 295, 200, 25);
		mainContentJPanel.add(lifeField);

		experienceLabel = new JLabel("Point d'expérience");
		experienceLabel.setBounds(25, 335, 200, 25);
		mainContentJPanel.add(experienceLabel);

		experienceField = new JFormattedTextField(amountFormat);
		experienceField.setBounds(260, 335, 200, 25);
		mainContentJPanel.add(experienceField);

		levelLabel = new JLabel("Niveau");
		levelLabel.setBounds(25, 375, 200, 25);
		mainContentJPanel.add(levelLabel);

		levelField = new JFormattedTextField(amountFormat);
		levelField.setBounds(260, 375, 200, 25);
		mainContentJPanel.add(levelField);

		valideBtn = new JButton("Ajouter");
		valideBtn.setBounds(25, 430, 200, 25);
		mainContentJPanel.add(valideBtn);

		cancelBtn = new JButton("Annuler");
		cancelBtn.setBounds(260, 430, 200, 25);
		mainContentJPanel.add(cancelBtn);

		addHeroListenerList(listHeros);
	}

	private void addHeroListenerList(final HeroList listHeros) {

		valideBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(mainContentJPanel, "Le nom du héros ne doit pas être vide !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}

				ClassHero classHero;
				if (classHeroComboBox.getSelectedItem() instanceof ClassHero) {
					classHero = (ClassHero) classHeroComboBox.getSelectedItem();
				} else {
					JOptionPane.showMessageDialog(mainContentJPanel, "Vous n'avez pas sélectionné une classe valide !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}

				Hero hero = new Hero(nameField.getText(), classHero);

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
					hero.createAndSaveToDB();
					listHeros.add(hero);

					JOptionPane.showMessageDialog(mainContentJPanel, "Le héros a été créé avec succès !");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				nameField.setText("");
				classHeroComboBox.setSelectedIndex(0);
				pathIconHero.setText("");
				attackField.setText("");
				defenceField.setText("");
				evasionField.setText("");
				escapeField.setText("");
				lifeField.setText("");
				experienceField.setText("");
				levelField.setText("");
			}
		});

		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}
}
