package com.schoolHeroes.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.schoolHeroes.model.User;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InscriptionForm extends JFrame {

	private JPanel contentPane;
	
	private JLabel usernameLabel;
	private JTextField usernameField;
	
	private JLabel firstNameLabel;
	private JTextField firstNameField;
	
	private JLabel lastNameLabel;
	private JTextField lastNameField;
	
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	
	private JLabel passwordConfirmLabel;
	private JPasswordField passwordConfirmField;
	
	private JLabel mailLabel;
	private JTextField mailField;
	
	private JLabel descriptionLabel;
	private JTextArea descriptionField;
	private JScrollPane descriptionScrollPane;

	private JButton inscriptionButton;
	private JLabel connexionLink;

	public InscriptionForm() {
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(450, 500);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		usernameLabel = new JLabel("Nom d'utilisateur");
		usernameLabel.setBounds(50, 25, 150, 25);
		contentPane.add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(200, 25, 200, 25);
		contentPane.add(usernameField);
		
		lastNameLabel = new JLabel("Nom");
		lastNameLabel.setBounds(50, 60, 150, 25);
		contentPane.add(lastNameLabel);
		
		lastNameField = new JTextField();
		lastNameField.setBounds(200, 60, 200, 25);
		contentPane.add(lastNameField);
		
		firstNameLabel = new JLabel("Prénom");
		firstNameLabel.setBounds(50, 95, 150, 25);
		contentPane.add(firstNameLabel);
		
		firstNameField = new JTextField();
		firstNameField.setBounds(200, 95, 200, 25);
		contentPane.add(firstNameField);
		
		passwordLabel = new JLabel("Mot de passe");
		passwordLabel.setBounds(50, 130, 150, 25);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 130, 200, 25);
		contentPane.add(passwordField);
		
		passwordConfirmLabel = new JLabel("Mot de passe");
		passwordConfirmLabel.setBounds(50, 165, 150, 25);
		contentPane.add(passwordConfirmLabel);
		
		passwordConfirmField = new JPasswordField();
		passwordConfirmField.setBounds(200, 165, 200, 25);
		contentPane.add(passwordConfirmField);
		
		mailLabel = new JLabel("Adresse e-mail");
		mailLabel.setBounds(50, 200, 150, 25);
		contentPane.add(mailLabel);
		
		mailField = new JTextField();
		mailField.setBounds(200, 200, 200, 25);
		contentPane.add(mailField);
		
		descriptionLabel = new JLabel("Description");
		descriptionLabel.setBounds(50, 235, 150, 25);
		contentPane.add(descriptionLabel);
		
		descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(200, 235, 200, 125);
		contentPane.add(descriptionScrollPane);
		
		descriptionField = new JTextArea();
		descriptionField.setLineWrap(true);
		descriptionScrollPane.setViewportView(descriptionField);
		
		inscriptionButton = new JButton("S'inscrire");
		inscriptionButton.setBounds(125, 385, 200, 25);
		contentPane.add(inscriptionButton);
		
		connexionLink = new JLabel("Se connecter");
		connexionLink.setHorizontalAlignment(SwingConstants.CENTER);
		connexionLink.setForeground(Color.blue.darker());
		connexionLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		connexionLink.setBounds(0, 420, 450, 25);
		contentPane.add(connexionLink);
		
		inscriptionListenerList();
	}
	
	public void inscriptionListenerList() {
		inscriptionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				
				if (usernameField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Le nom d'utilisateur ne doit pas être vide !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				} 
				
				if (lastNameField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Le nom ne doit pas être vide !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (firstNameField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Le prénom ne doit pas être vide !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (passwordField.getPassword().length == 0 || passwordConfirmField.getPassword().length == 0) {
					JOptionPane.showMessageDialog(contentPane, "Le mot de passe ne doit pas être vide !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!Arrays.equals(passwordField.getPassword(), passwordConfirmField.getPassword())) {
					JOptionPane.showMessageDialog(contentPane, "Les deux mots de passe ne correspondent pas !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				boolean isUsernameUnique = false;
				try {
					isUsernameUnique = User.isUsernameUnique(usernameField.getText().trim());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(isUsernameUnique == false) {
					JOptionPane.showMessageDialog(contentPane, "Veuillez choisir un autre nom d'utilisateur",
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
					char[] password = passwordField.getPassword();
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
					String hashPassword = bCryptPasswordEncoder.encode(new String(password));
					user.setPassword(hashPassword);
					Arrays.fill(password, '0');
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, "Impossible de hasher le mot de passe ! Veuillez Reésayer !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Date date = new Date(System.currentTimeMillis());
				user.setCreatedAt(date);
				try {
					user.createAndSaveToDB();
					user.setPassword(null);
					JOptionPane.showMessageDialog(contentPane, "Enrregistrement réussi !");
					
					MainForm frame = new MainForm(user);
					dispose();
					frame.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		connexionLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginForm frame= new LoginForm();
				dispose();
				frame.setVisible(true);
			}
		});
	}
	
}
