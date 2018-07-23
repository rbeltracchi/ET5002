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
public class TypeFilter implements Filter {
    private String hostServiceType;

    /**
     * Constructor vacío.
     */
    public TypeFilter() {
    }

    /**
     * Cosntructor del filtro por tipo de hospedaje (cabaña, hotel, etc).
     * 
     * @param hostServiceType (String) Tipo de Alojamiento (Hotel, cabaña, etc).
     */
    public TypeFilter(String hostServiceType) {
        this.hostServiceType = hostServiceType;
    }

    /**
     *
     * @return Retorna el tipo de hospedaje elegido para la búsqueda.
     */
    public String getHostServiceType() {
        return hostServiceType;
    }

    /**
     * Asigna el tipo de hospedaje a buscar.
     * 
     * @param hostServiceType (String) Tipo de hospedaje (cabaña, hote, etc).
     */
    public void setHostServiceType(String hostServiceType) {
        this.hostServiceType = hostServiceType;
    }
    
    /**
     * Este método comprueba si el tipo de alojamiento requerido coincide con el del alojamiento
     * pasado por parámetros.
     * 
     * @param h (HostService) El alojamiento (hotel, cabaña, etc) que se va a comprobar si
     * cumple con los filtros requeridos.
     * 
     * @return Retorna true si el tipo de alojamiento requerido coincide con el del alojamiento
     * pasado por parámetro. En caso contrario, retorna false.
     */
    @Override
    public boolean isAMatch(HostService h) {
    	Housing housing = (Housing) h;
    	
    	boolean housingMatch = this.hostServiceType.equals(housing.getHostServiceType());
    	
    	//Descarta las rooms del housing que no cumplen con las commodities.
    	List<Room> roomsSaved = new ArrayList<>();
    	for(Room room : housing.getRooms()) {
    		if(this.hostServiceType.equals(room.getHostServiceType())) {
    			roomsSaved.add(room);
    		}
    	}
    	
    	//Si cumple alguna room, el housing sale con esas rooms que cumplieron con el filtro.
    	if (roomsSaved.size() > 0) {
    		housing.getRooms().clear();
			housing.getRooms().addAll(roomsSaved);
			return true;
    	}
    	else
    		//Si no cumplio ninguna room el housing se queda con las rooms que tenia.
    		//Se retorna si el housing cumplio o no.
    		return housingMatch;
    }
    
}
