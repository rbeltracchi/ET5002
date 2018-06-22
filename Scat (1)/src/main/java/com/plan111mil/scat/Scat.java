/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.scat;

import com.plan111mil.filter.Filter;
import com.plan111mil.hostservice.HostService;
import com.plan111mil.hostservice.Housing;
import com.plan111mil.user.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LTaglioretti
 */
public class Scat {
    private List<HostService>housings;
    private List<User> users;

    /**
     *Constructor que inicializa los array lists.
     */
    public Scat() {
        this.housings = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    /**
     *
     * @return Retorna la lista de todos los hospedajes registrados.
     */
    public List<HostService> getHousings() {
        return housings;
    }

    /**
     *
     * @param housings
     */
    public void setHousings(List<HostService> housings) {
        this.housings = housings;
    }

    /**
     *
     * @return Retorna la lista de todos los usarios registrados.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     *
     * @param users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    /**
     * Este método recorre los alojamientos registrados haciendo uso del método searchDisp
     * para comprobar si cumplen con los requisitos de filtrado. De ser así, se agregan a
     * una lista para luego mostrarlos/listarlos.
     * 
     * @param f Es la lista de filtros con los cuales se desea realizar la busqueda.
     * @return Retorna la lista de los hospedajes que cumplen con los filtros pasados.
     */
    public ArrayList<Housing> getFilteredListOfHousings(List<Filter> f){
        ArrayList filteredList = new ArrayList<>();
        
        for(HostService h : this.housings){
            ArrayList fcopy = new ArrayList<Filter>(f);
            if(h.searchDisp(fcopy))
                filteredList.add(h);
        }
        return filteredList;
    }


    
}
