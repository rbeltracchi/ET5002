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
 * @author MVazquez
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
     * @param commodity (String) Servicio que ofrece el hospedaje.
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
     * @param commodity (String) Servicio que ofrece el hospedaje.
     */
    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    /**
     * Este método comprueba si el alojamiento que recibe como parámetro cuenta con el servicio
     * requerido en la búsqueda.
     * 
     * @param h (HostService) El alojamiento (hotel, cabaña, etc) que se va a comprobar si
     * cumple con los filtros requeridos.
     * 
     * @return Retorna true si el alojamiento pasado por parámetro cuenta con el servicio que
     * se requiere.
     */
    @Override
    public boolean isAMatch(HostService h) {
        for(String c : h.getCommodities())
            if((this.commodity).equals(c)){
                return true;
            }
        return false;
    }

}
