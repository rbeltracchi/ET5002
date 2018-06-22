/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.filter;

import com.plan111mil.hostservice.HostService;

/**
 *
 * @author LTaglioretti
 */
public interface Filter {
    
    /**
     * Este método comprueba si el atributo del alojamiento pasado por parámetros coincide con el
     * atributo elegido.
     * 
     * @param h (HostService) El alojamiento (hotel, cabaña, etc) que se va a comprobar si
     * cumple con los filtros requeridos.
     * 
     * @return Retorna true si cumple, false si no.
     */
    public boolean isAMatch(HostService h);
     

}
