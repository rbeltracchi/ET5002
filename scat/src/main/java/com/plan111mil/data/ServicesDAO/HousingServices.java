/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.data.ServicesDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.plan111mil.data.entity.Housing;
import com.plan111mil.filter.Filter;

/**
 *
 * @author ET5002
 */
public class HousingServices {

	public HousingServices() {
	}

	public List<Housing> getHousings() {
		List<Housing> housings = DBDAO.findAll(Housing.class);
		Collections.sort(housings);
		return housings;
	}

	public boolean persistHousing(Housing housing) {
		return DBDAO.persist(housing);
	}

	public void deleteHousing(Housing housing) {
		DBDAO.delete(housing);
	}

	/**
	 * Este método recorre los alojamientos registrados haciendo uso del método
	 * searchDisp para comprobar si cumplen con los requisitos de filtrado. De ser
	 * así, se agregan a una lista para luego mostrarlos/listarlos.
	 *
	 * @param f
	 *            Es la lista de filtros con los cuales se desea realizar la
	 *            busqueda.
	 * @return Retorna la lista de los hospedajes que cumplen con los filtros
	 *         pasados.
	 */
	public List<Housing> getFilteredListOfHousings(List<Filter> f) {

		List<Housing> housings = getHousings(); // se traen los housings de BD
		
		if (f.isEmpty())
			return housings; // si la lista de filtros esta vacia retorna todos los housings

		else {
			List<Housing> filteredList = new ArrayList<>(); // lista resultado con los filtrados
			for (Housing h : housings) {
				if (h.searchDisp(f))
					filteredList.add(h);
			}
			return filteredList; // devuelve solo los filtrados
		}
	}

}
