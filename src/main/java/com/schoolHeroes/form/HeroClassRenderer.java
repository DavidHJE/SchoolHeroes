package com.schoolHeroes.form;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.schoolHeroes.model.ClassHero;

@SuppressWarnings("serial")
public class HeroClassRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (value instanceof ClassHero) {
			ClassHero classHero = (ClassHero) value;
			setText(classHero.getName());
		}
		return this;
	}

}
