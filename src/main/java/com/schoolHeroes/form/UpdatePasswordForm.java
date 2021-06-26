package com.schoolHeroes.form;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.schoolHeroes.model.User;

public class UpdatePasswordForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JLabel oldPasswordLabel;
	private JPasswordField oldPasswordField;
	
	private JLabel newPasswordLabel;
	private JPasswordField newPasswordField;
	
	private JLabel cofirmNewPasswordLabel;
	private JPasswordField confirmNewPasswordField;
	
	private JButton updatePasswordButton;
	private JButton cancelButton;

	public UpdatePasswordForm(User user) {
		setTitle("Modifier le mot de passe");
		setResizable(false);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setSize(530, 250);
		setLocationRelativeTo(null);

		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		oldPasswordLabel = new JLabel("Ancien mot de passe");
		oldPasswordLabel.setBounds(50, 20, 200, 25);
		contentPanel.add(oldPasswordLabel);
		
		oldPasswordField = new JPasswordField();
		oldPasswordField.setBounds(270, 20, 200, 25);
		contentPanel.add(oldPasswordField);
		
		newPasswordLabel = new JLabel("Nouveau mot de passe");
		newPasswordLabel.setBounds(50, 70, 200, 25);
		contentPanel.add(newPasswordLabel);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(270, 70, 200, 25);
		contentPanel.add(newPasswordField);
		
		cofirmNewPasswordLabel = new JLabel("Corfirmer mot de passe");
		cofirmNewPasswordLabel.setBounds(50, 120, 200, 25);
		contentPanel.add(cofirmNewPasswordLabel);
		
		confirmNewPasswordField = new JPasswordField();
		confirmNewPasswordField.setBounds(270, 120, 200, 25);
		contentPanel.add(confirmNewPasswordField);

		cancelButton = new JButton("Annuler");
		cancelButton.setBounds(50, 170, 200, 25);
		contentPanel.add(cancelButton);
		
		updatePasswordButton = new JButton("Modifier");
		updatePasswordButton.setBounds(270, 170, 200, 25);
		contentPanel.add(updatePasswordButton);
		
		updatePasswordListernerList(user);
	}
	
	private void updatePasswordListernerList(final User user) {
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		updatePasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (oldPasswordField.getPassword().length == 0 || newPasswordField.getPassword().length == 0 || confirmNewPasswordField.getPassword().length == 0) {
					JOptionPane.showMessageDialog(UpdatePasswordForm.this, "Le mot de passe ne doit pas être vide !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!Arrays.equals(newPasswordField.getPassword(), confirmNewPasswordField.getPassword())) {
					JOptionPane.showMessageDialog(UpdatePasswordForm.this, "Les deux mots de passe ne correspondent pas !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					if (user.isCorrectPassword(oldPasswordField.getPassword())) {
						user.changePasswordAndSaveToDB(newPasswordField.getPassword());
						JOptionPane.showMessageDialog(UpdatePasswordForm.this, "Mot de passe modifier avec succès !");
						
						dispose();
					} else {
						JOptionPane.showMessageDialog(UpdatePasswordForm.this, "Mot de passe incorrecte !",
								"Erreur", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
