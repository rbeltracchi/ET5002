/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.filter;

import com.plan111mil.data.entity.HostService;


/**
 *
 * @author ET5002
 */
public class NameFilter implements Filter {
private String name;

    /**
     * Constructor vacío.
     */
    public NameFilter() {
    }

    
    /**
     * Cosntructor del filtro por nombre de hospedaje
     * @param name (String)
     */
    public NameFilter(String name) {
        this.name = name;
    }

    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    
    


    /**
     * Este método comprueba si el nombre del alojamiento requerido coincide con el del alojamiento
     * pasado por parámetros.
     * 
     * @param h (HostService) El alojamiento (hotel, cabaña, etc) que se va a comprobar si
     * cumple con los filtros requeridos.
     * 
     * @return Retorna true si el nombre del alojamiento requerido coincide con el del alojamiento
     * pasado por parámetro. En caso contrario, retorna false.
     */
    @Override
    public boolean isAMatch(HostService h) {
        return ((h.getName().toLowerCase()).contains(this.name.toLowerCase()));
    }
    

}
