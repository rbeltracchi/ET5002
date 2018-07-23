/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.plan111mil.data.entity.HostService;
import com.plan111mil.data.entity.Room;

/**
 *
 * @author ET5002
 */
public class RoomListRenderer extends JLabel implements ListCellRenderer<HostService> {

	public RoomListRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList jlist, HostService hs, int index, boolean isSelected,
			boolean cellHasFocus) {
		Room r = (Room) hs;

		JLabel labelName = new JLabel("Habitación " + r.getName());
		labelName.setFont(new Font("Calibri", Font.BOLD, 18));
		labelName.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		JLabel labelType = new JLabel("Tipo de habitación: " + r.getHostServiceType());
		labelType.setFont(new Font("Calibri", Font.BOLD, 14));
		labelType.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		JLabel labelCapacity = new JLabel("Capacidad: " + r.getCapacity().toString());
		labelCapacity.setFont(new Font("Calibri", Font.BOLD, 14));
		labelCapacity.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		JLabel labelDescription = new JLabel("Descripción: " + r.getDescription());
		labelDescription.setFont(new Font("Calibri", Font.BOLD, 14));
		labelDescription.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		JLabel labelCommodities = new JLabel("Servicios: " + r.getCommodities());
		labelCommodities.setFont(new Font("Calibri", Font.BOLD, 14));
		labelCommodities.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		JLabel availableLabel = new JLabel("Disponible: " + returnText(r));
		availableLabel.setFont(new Font("Calibri", Font.BOLD, 14));
		availableLabel.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);

		JPanel panel = new JPanel();
		panel.add(Box.createRigidArea(new Dimension(0, 15)));
		panel.add(labelName);
		panel.add(labelType);
		panel.add(labelCapacity);
		panel.add(labelDescription);
		panel.add(labelCommodities);
		panel.add(availableLabel);
		panel.add(Box.createRigidArea(new Dimension(0, 15)));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createLineBorder(LookAndFeel.CUSTOM_BLUE));

		if (isSelected) {
			panel.setBackground(jlist.getSelectionBackground());
			panel.setForeground(jlist.getSelectionForeground());
		} else {
			panel.setBackground(jlist.getBackground());
			panel.setForeground(jlist.getForeground());
		}

		return panel;
	}

	private String returnText(Room r) {
		if (r.isAvailable()) {
			return "disponible";
		}

		return "no disponible";
	}

}
