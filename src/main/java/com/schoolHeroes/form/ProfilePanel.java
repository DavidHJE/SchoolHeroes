package com.schoolHeroes.form;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProfilePanel extends JPanel {
	private JButton logOutButton;
	
	private JLabel usernameLabel;
	private JTextField usernameField;
	
	private JLabel firstNameLabel;
	private JTextField firstNameField;
	
	private JLabel lastNameLabel;
	private JTextField lastNameField;
	
	private JLabel mailLabel;
	private JTextField mailField;
	
	private JLabel descriptionLabel;
	private JTextArea descriptionField;
	private JScrollPane descriptionScrollPane;

	private JButton changePasswordButton;
	private JButton editButton;
	private JButton deleteButton;

	
	public ProfilePanel() {
		setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		logOutButton = new JButton("Se déconnecter");
		logOutButton.setBounds(700, 20, 150, 25);
		add(logOutButton);
		
		usernameLabel = new JLabel("Nom d'utilisateur");
		usernameLabel.setBounds(150, 50, 150, 25);
		add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(350, 50, 300, 25);
		add(usernameField);
		
		lastNameLabel = new JLabel("Nom");
		lastNameLabel.setBounds(150, 100, 150, 25);
		add(lastNameLabel);
		
		lastNameField = new JTextField();
		lastNameField.setBounds(350, 100, 300, 25);
		add(lastNameField);
		
		firstNameLabel = new JLabel("Prénom");
		firstNameLabel.setBounds(150, 150, 150, 25);
		add(firstNameLabel);
		
		firstNameField = new JTextField();
		firstNameField.setBounds(350, 150, 300, 25);
		add(firstNameField);
		
		mailLabel = new JLabel("Adresse e-mail");
		mailLabel.setBounds(150, 200, 150, 25);
		add(mailLabel);
		
		mailField = new JTextField();
		mailField.setBounds(350, 200, 300, 25);
		add(mailField);
		
		descriptionLabel = new JLabel("Description");
		descriptionLabel.setBounds(150, 250, 150, 25);
		add(descriptionLabel);
		
		descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(350, 250, 300, 200);
		add(descriptionScrollPane);
		
		descriptionField = new JTextArea();
		descriptionField.setLineWrap(true);
		descriptionScrollPane.setViewportView(descriptionField);
		
		changePasswordButton = new JButton("Modifier mot de passe");
		changePasswordButton.setBounds(175, 500, 200, 25);
		add(changePasswordButton);
		
		editButton = new JButton("Modifier");
		editButton.setBounds(400, 500, 200, 25);
		add(editButton);
		
		deleteButton = new JButton("Supprimer");
		deleteButton.setBounds(625, 500, 200, 25);
		deleteButton.setBackground(Color.RED.brighter());
		deleteButton.setForeground(Color.WHITE);
		add(deleteButton);
	}

}
