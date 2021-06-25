package com.schoolHeroes.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	
	private JLabel usernameLabel;
	private JTextField usernameField;

	private JLabel passwordLabel;
	private JPasswordField passwordField;
	
	private JButton btnConnexion;
	private JLabel inscriptionLink;

	public LoginForm() {
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		usernameLabel = new JLabel("Nom d'utilisateur");
		usernameLabel.setBounds(50, 50, 150, 25);
		contentPane.add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(200, 50, 200, 25);
		contentPane.add(usernameField);
				
		passwordLabel = new JLabel("Mot de passe");
		passwordLabel.setBounds(50, 100, 150, 25);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 100, 200, 25);
		contentPane.add(passwordField);
				
		btnConnexion = new JButton("Se connecter");
		btnConnexion.setBounds(125, 175, 200, 25);
		contentPane.add(btnConnexion);
		
		inscriptionLink = new JLabel("S'inscrire");
		inscriptionLink.setHorizontalAlignment(SwingConstants.CENTER);
		inscriptionLink.setForeground(Color.blue.darker());
		inscriptionLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		inscriptionLink.setBounds(0, 215, 450, 25);
		contentPane.add(inscriptionLink);
		
		loginListernerList();
	}
	
	public void loginListernerList() {
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainForm frame = new MainForm();
				dispose();
				frame.setVisible(true);
			}
		});
		
		inscriptionLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InscriptionForm frame= new InscriptionForm();
				dispose();
				frame.setVisible(true);
			}
		});
		
	}
	
}
