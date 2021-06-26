package com.schoolHeroes.form;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.schoolHeroes.model.User;

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

	
	public ProfilePanel(User user) {
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
		usernameField.setText(user.getUsername());
		add(usernameField);
		
		lastNameLabel = new JLabel("Nom");
		lastNameLabel.setBounds(150, 100, 150, 25);
		add(lastNameLabel);
		
		lastNameField = new JTextField();
		lastNameField.setBounds(350, 100, 300, 25);
		lastNameField.setText(user.getLastName());
		add(lastNameField);
		
		firstNameLabel = new JLabel("Prénom");
		firstNameLabel.setBounds(150, 150, 150, 25);
		add(firstNameLabel);
		
		firstNameField = new JTextField();
		firstNameField.setBounds(350, 150, 300, 25);
		firstNameField.setText(user.getFirstName());
		add(firstNameField);
		
		mailLabel = new JLabel("Adresse e-mail");
		mailLabel.setBounds(150, 200, 150, 25);
		add(mailLabel);
		
		mailField = new JTextField();
		mailField.setBounds(350, 200, 300, 25);
		mailField.setText(user.getEmail());
		add(mailField);
		
		descriptionLabel = new JLabel("Description");
		descriptionLabel.setBounds(150, 250, 150, 25);
		add(descriptionLabel);
		
		descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(350, 250, 300, 200);
		add(descriptionScrollPane);
		
		descriptionField = new JTextArea();
		descriptionField.setText(user.getDescription());
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
		
		heroListenerList(user);
	}
	
	private void heroListenerList(final User user) {
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainForm mainForm = (MainForm) getRootPane().getParent();
				LoginForm frame = new LoginForm();
				mainForm.dispose();
				frame.setVisible(true);
			}
		});
		
		changePasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatePasswordForm dialog = new UpdatePasswordForm(user);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usernameField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(ProfilePanel.this, "Le nom d'utilisateur ne doit pas être vide !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				} 
				
				if (lastNameField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(ProfilePanel.this, "Le nom ne doit pas être vide !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (firstNameField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(ProfilePanel.this, "Le prénom ne doit pas être vide !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				boolean isUsernameUnique = false;
				try {
					isUsernameUnique = User.isUsernameUnique(usernameField.getText().trim(), user.getId());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				if(isUsernameUnique == false) {
					JOptionPane.showMessageDialog(ProfilePanel.this, "Veuillez choisir un autre nom d'utilisateur",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (!mailField.getText().trim().equals("")) {
					user.setEmail(mailField.getText());
				}
				
				if (!descriptionField.getText().trim().equals("")) {
					user.setDescription(descriptionField.getText());
				}
				
				user.setUsername(usernameField.getText());
				user.setFirstName(firstNameField.getText());
				user.setLastName(lastNameField.getText());
				
				try {
					user.updateAndSaveToDB();
					JOptionPane.showMessageDialog(ProfilePanel.this, "Modification réussi !");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Oui", "Non" };

				int response = JOptionPane.showOptionDialog(ProfilePanel.this,
						"Voulez vous vraiment suprimer ce compte ?", "Supprimer ce compte",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if (response == 1) {
					return;
				}
				
				try {
					user.deleteFromDB();

					JOptionPane.showMessageDialog(ProfilePanel.this, "Votre compte a été supprimer avec succès.");
					
					MainForm mainForm = (MainForm) getRootPane().getParent();
					LoginForm frame = new LoginForm();
					mainForm.dispose();
					frame.setVisible(true);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
