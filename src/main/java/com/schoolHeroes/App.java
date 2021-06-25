package com.schoolHeroes;

import java.awt.EventQueue;

import com.schoolHeroes.form.LoginForm;
import com.schoolHeroes.form.MainForm;

public class App {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
//	public static void main(String[] args) {
//		try {
//			MainForm frame = new MainForm();
//			frame.setVisible(true);
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
}
