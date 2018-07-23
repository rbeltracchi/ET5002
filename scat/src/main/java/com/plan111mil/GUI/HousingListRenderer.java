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
import com.plan111mil.data.entity.Housing;

/**
 *
 * @author ET5002
 */
public class HousingListRenderer extends JPanel implements ListCellRenderer<HostService> {

	public HousingListRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList jlist, HostService hs, int index, boolean isSelected,
			boolean cellHasFocus) {
		Housing h = (Housing) hs;

		JLabel labelName = new JLabel(h.getName());
		labelName.setFont(new Font("Calibri", Font.BOLD, 18));
		labelName.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		JLabel labelType = new JLabel(h.getHostServiceType());
		labelType.setFont(new Font("Calibri", Font.BOLD, 14));
		labelType.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		JLabel labelAddress = new JLabel(h.getAddress());
		labelAddress.setFont(new Font("Calibri", Font.BOLD, 14));
		labelAddress.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		JLabel labelPhone = new JLabel(h.getPhone());
		labelPhone.setFont(new Font("Calibri", Font.BOLD, 14));
		labelPhone.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		JLabel labelMail = new JLabel(h.getMail());
		labelMail.setFont(new Font("Calibri", Font.BOLD, 14));
		labelMail.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		JLabel labelDescription = new JLabel(h.getDescription());
		labelDescription.setFont(new Font("Calibri", Font.BOLD, 14));
		labelDescription.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);
		JLabel labelCommodities = new JLabel("Servicios: " + h.getCommodities());
		labelCommodities.setFont(new Font("Calibri", Font.PLAIN, 14));
		labelCommodities.setForeground(LookAndFeel.CUSTOM_DARK_BLUE);

		JPanel panel = new JPanel();
		panel.add(Box.createRigidArea(new Dimension(0, 15)));
		panel.add(labelName);
		panel.add(labelType);
		panel.add(labelAddress);
		panel.add(labelPhone);
		panel.add(labelMail);
		panel.add(labelDescription);
		panel.add(labelCommodities);
		panel.add(Box.createRigidArea(new Dimension(0, 15)));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.white);
		panel.setForeground(Color.white);
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

}
