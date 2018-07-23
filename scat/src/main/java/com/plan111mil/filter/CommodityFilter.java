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
public class CommodityFilter implements Filter {
	private String commodity;

	/**
	 * Constructor vacío.
	 */
	public CommodityFilter() {
	}

	/**
	 * Cosntructor del filtro por tipo de commodity.
	 * 
	 * @param commodity
	 *            (String) Servicio que ofrece el hospedaje.
	 */
	public CommodityFilter(String commodity) {
		this.commodity = commodity;
	}

	/**
	 *
	 * @return Retorna el servicio requerido para la búsqueda.
	 */
	public String getCommodity() {
		return commodity;
	}

	/**
	 * Asigna el tipo de servicio a buscar.
	 * 
	 * @param commodity
	 *            (String) Servicio que ofrece el hospedaje.
	 */
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	/**
	 * Este método comprueba si el alojamiento que recibe como parámetro cuenta con
	 * el servicio requerido en la búsqueda. Chequea tanto en el Housing como en sus
	 * rooms.
	 * 
	 * @param h
	 *            (HostService) El alojamiento (hotel, cabaña, etc) que se va a
	 *            comprobar si cumple con los filtros requeridos.
	 * 
	 * @return Retorna true si el alojamiento pasado por parámetro cuenta con el
	 *         servicio que se requiere.
	 */
	@Override
	public boolean isAMatch(HostService h) {
		Housing housing = (Housing) h;

		// Chequea si el housing cumple con las commodities
		boolean housingMatch = stringInCollection(this.commodity, housing.getCommodities());

		// Descarta las rooms del housing que no cumplen con las commodities.
		List<Room> roomsSaved = new ArrayList<>();
		for (Room room : housing.getRooms()) {
			if (stringInCollection(this.commodity, room.getCommodities())) {
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
			// Si no cumplio ninguna room el housing se queda con las rooms que tenia.
			// Se retorna si el housing cumplio o no.
			return housingMatch;
	}

	private boolean stringInCollection(String strExpected, List<String> collection) {
		for (String str : collection)
			if (str.equals(strExpected))
				return true;
		return false;
	}

}
