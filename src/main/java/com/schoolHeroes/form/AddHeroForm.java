package com.schoolHeroes.form;

import java.awt.Dialog;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.schoolHeroes.model.ArcherClassHero;
import com.schoolHeroes.model.ClassHero;
import com.schoolHeroes.model.Hero;
import com.schoolHeroes.model.TamerClassHero;
import com.schoolHeroes.model.WarriorClassHero;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddHeroForm extends JDialog {

	JPanel mainContentJPanel = new JPanel();
	private JTextField nameField;
	// TODO refaire correctement la combobox
	private JComboBox<ClassHero> classHeroComboBox;
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
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// try {
	// AddHeroForm dialog = new AddHeroForm();
	// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	// dialog.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * Create the dialog. TODO :: list des héros puis ajouter
	 */
	public AddHeroForm(HeroList listHeros) {
		// Works
		//		 ClassHero ArcherClassHero = new com.schoolHeroes.model.ArcherClassHero();
		//		
		//		 Hero hero = new Hero("david", ArcherClassHero);
		//		 listHeros.add(hero);


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
		mainContentJPanel.add(nameField);

		classLabel = new JLabel("Classe du hero");
		classLabel.setBounds(25, 55, 200, 25);
		mainContentJPanel.add(classLabel);

		classHeroComboBox = new JComboBox<ClassHero>();
		classHeroComboBox.setBounds(260, 55, 200, 25);
		classHeroComboBox.addItem(new WarriorClassHero());
		classHeroComboBox.addItem(new ArcherClassHero());
		classHeroComboBox.addItem(new TamerClassHero());
		mainContentJPanel.add(classHeroComboBox);

		pathIconHeroLabel = new JLabel("Icon du hero");
		pathIconHeroLabel.setBounds(25, 95, 200, 25);
		mainContentJPanel.add(pathIconHeroLabel);

		pathIconHero = new JTextField(); // File chose img
		pathIconHero.setBounds(260, 95, 200, 25);
		mainContentJPanel.add(pathIconHero);

		attackLabel = new JLabel("Attaque du hero"); // File chose img
		attackLabel.setBounds(25, 135, 200, 25);
		mainContentJPanel.add(attackLabel);

		attackField = new JFormattedTextField(amountFormat);
		attackField.setBounds(260, 135, 200, 25);
		mainContentJPanel.add(attackField);

		defenceLabel = new JLabel("Défense du hero"); // File chose img
		defenceLabel.setBounds(25, 175, 200, 25);
		mainContentJPanel.add(defenceLabel);

		defenceField = new JFormattedTextField(amountFormat);
		defenceField.setBounds(260, 175, 200, 25);
		mainContentJPanel.add(defenceField);

		evasionLabel = new JLabel("Esquive du hero"); // File chose img
		evasionLabel.setBounds(25, 215, 200, 25);
		mainContentJPanel.add(evasionLabel);

		evasionField = new JFormattedTextField(amountFormat);
		evasionField.setBounds(260, 215, 200, 25);
		mainContentJPanel.add(evasionField);

		escapeLabel = new JLabel("Vitesse de fuite du hero"); // File chose img
		escapeLabel.setBounds(25, 255, 200, 25);
		mainContentJPanel.add(escapeLabel);

		escapeField = new JFormattedTextField(amountFormat);
		escapeField.setBounds(260, 255, 200, 25);
		mainContentJPanel.add(escapeField);

		lifeLabel = new JLabel("Point de vie du hero"); // File chose img
		lifeLabel.setBounds(25, 295, 200, 25);
		mainContentJPanel.add(lifeLabel);

		lifeField = new JFormattedTextField(amountFormat);
		lifeField.setBounds(260, 295, 200, 25);
		mainContentJPanel.add(lifeField);

		experienceLabel = new JLabel("Point d'expérience du hero"); // File chose img
		experienceLabel.setBounds(25, 335, 200, 25);
		mainContentJPanel.add(experienceLabel);

		experienceField = new JFormattedTextField(amountFormat);
		experienceField.setBounds(260, 335, 200, 25);
		mainContentJPanel.add(experienceField);

		levelLabel = new JLabel("Niveau du hero"); // File chose img
		levelLabel.setBounds(25, 375, 200, 25);
		mainContentJPanel.add(levelLabel);

		levelField = new JFormattedTextField(amountFormat);
		levelField.setBounds(260, 375, 200, 25);
		mainContentJPanel.add(levelField);

		valideBtn = new JButton("Ajouter Hero");
		valideBtn.setBounds(25, 430, 200, 25);
		mainContentJPanel.add(valideBtn);

		cancelBtn = new JButton("Annuler");
		cancelBtn.setBounds(260, 430, 200, 25);
		mainContentJPanel.add(cancelBtn);

		addHeroListenerList();
	}

	private void addHeroListenerList() {

		valideBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO faire les vérifications puis ajouter toute les champs
				// essayer d'autre facon de faire


				if(nameField.getText().equals("")) {
					return;
				}

				ClassHero classHero;
				if (classHeroComboBox.getSelectedItem() instanceof ClassHero) {
					classHero = (ClassHero) classHeroComboBox.getSelectedItem();
				}else {
					return;
				}

				Hero hero = new Hero(nameField.getText(), classHero);

				try {
					hero.createAndSaveToDB();
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
