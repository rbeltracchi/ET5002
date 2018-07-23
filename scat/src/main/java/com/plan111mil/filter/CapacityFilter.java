/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.filter;

import com.plan111mil.data.entity.HostService;

/**
 * @author ET5002
 */
public class CapacityFilter implements Filter {
    private Integer capacity;

    /**
     * Constructor vacío.
     */
    public CapacityFilter() {
    }

    /**
     * Constructor del filtro por capacidad de hospedaje (cabaña, hotel, etc).
     * 
     * @param capacity (Integer) Capacidad deseada del hospedaje/alojamiento.
     */
    public CapacityFilter(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     *
     * @return Retorna la capacidad por la cual se desea filtrar.
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     *
     * @param capacity
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    
    
    
    /**
     * Este método comprueba si la capacidad disponible del alojamiento pasado por parámetros cumple con los
     * requisitos de acuerdo a la capacidad requerida.
     * 
     * @param h (HostService) El alojamiento (hotel, cabaña, etc) que se va a comprobar si
     * cumple con los filtros requeridos.
     * 
     * @return Retorna true si la capacidad requerida es mayor o igual a la que posee el alojamiento
     * pasado por parámetro. En caso contrario, retorna false.
     */
    @Override
    public boolean isAMatch(HostService h) {
        return (this.capacity <= h.getCapacity());
    }
    
}
