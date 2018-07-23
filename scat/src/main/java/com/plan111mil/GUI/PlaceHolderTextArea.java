/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;

/**
 * @author ET5002
 */
public class PlaceHolderTextArea extends JTextArea {

	private Dimension d = new Dimension(400, 100);
	private String placeholder = "";
	private Color phColor = new Color(0, 0, 0);
	private boolean band = true;

	/**
	 * Constructor de clase
	 */
	public PlaceHolderTextArea() {
		super();
		setSize(d);
		setPreferredSize(d);
		setVisible(true);
		// setMargin(new Insets(3, 6, 3, 6));
		setLineWrap(true);
		setWrapStyleWord(true);
		setForeground(Color.GRAY);

		addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().equals(""))
					setText(placeholder);

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(placeholder))
					setText("");
			}
		});
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public Color getPhColor() {
		return phColor;
	}

	public void setPhColor(Color phColor) {
		this.phColor = phColor;
	}

}// JCTextField:end