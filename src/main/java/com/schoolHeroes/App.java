package com.schoolHeroes;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.schoolHeroes.form.MainForm;
import com.schoolHeroes.model.Hero;

public class App 
{	
    public static void main( String[] args )
    {
    	try {
			MainForm frame = new MainForm();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
}
