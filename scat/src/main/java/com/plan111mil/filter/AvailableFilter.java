/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.filter;

import java.util.ArrayList;
import java.util.List;

import com.plan111mil.data.entity.HostService;
import com.plan111mil.data.entity.Housing;
import com.plan111mil.data.entity.Room;

/**
 *
 * @author ET5002
 */
public class AvailableFilter implements Filter {

	/**
	 * Constructor del filtro por disponibilidad de un housing segun sus rooms.
	 *
	 */
	public AvailableFilter() {
	}

	/**
	 * Este método comprueba si el housing tiene al menos una room disponible.
	 * 
	 * @param h
	 *            (HostService) El alojamiento (hotel, cabaña, etc) que se va a
	 *            comprobar si cumple con los filtros requeridos.
	 * 
	 * @return Retorna true si el housing tiene al menos una room disponible. En
	 *         caso contrario, retorna false.
	 */
	@Override
	public boolean isAMatch(HostService h) {
		Housing housing = (Housing) h;

		// Descarta las rooms del housing que no estan disponibles
		List<Room> roomsSaved = new ArrayList<>();
		for (Room room : housing.getRooms()) {
			if (room.isAvailable()) {
				roomsSaved.add(room);
			}
		}

		// Si cumple alguna room, el housing sale con esas rooms que cumplieron con el
		// filtro.
		if (roomsSaved.size() > 0) {
			housing.getRooms().clear();
			housing.getRooms().addAll(roomsSaved);
			return true;
		} else
			return false;

	}

}
