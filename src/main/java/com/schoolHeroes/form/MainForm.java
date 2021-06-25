package com.schoolHeroes.form;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class MainForm extends JFrame {

	private JPanel contentPane;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

	public MainForm() {
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1000, 640);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		tabbedPane.setBounds(0, 0, 1000, 600);
		contentPane.add(tabbedPane);

		HeroPanel heroPanel = new HeroPanel();
		heroPanel.setLayout(null);

		tabbedPane.add("Heros", heroPanel);

	}
}
