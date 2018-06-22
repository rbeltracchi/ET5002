package com.plan111mil.hostservice;

import com.plan111mil.filter.Filter;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author laura
 * @author Georgy
 */
@Entity
public class Room extends HostService {

    
    private boolean available;

    @ManyToOne
    private Housing housing;

    
    /**
     * Constructor vacio
     */
    public Room() {
    }

    /**
     * Método constructor
     *
     * @param available (boolean) Indica, mediante true o false, la
     * disponibilidad.
     * @param name (String) Indica el nombre de la habitación.
     * @param description (String) Descripción de Room.
     * @param capacity (Integer) Capacidad de Room.
     * @param hostServiceType (String) Descripción del tipo de Room.
     * @param housing
     */
    public Room(boolean available, String name, String description, Integer capacity, String hostServiceType,Housing housing) {
        super(name, description, capacity, hostServiceType);
        this.available = available;
        this.housing = housing;
    }
    
    
    /**
     * Este es un método que comprueba si una room está disponible y
     * también cumple el criterio de filtrado recibido por parámetro.
     *
     * @param filters es la lista de filtros, se deben cumplir todos los filtros.
     *
     * @return retorna 'true' solo si la room está disponible y pasa todos los filtros. 
     */
    @Override
    public boolean searchDisp(List<Filter> filters) {
                
        //TODO: volví a poner el método en room porqué agregué la verificación de la disponibilidad
        //que en un primer momento la había dada por supuesta en los filtros pero me parece que es
        //mucho mas lógico hacerla acá. --Laura
        
        if(this.available) // si está disponible la habitación 
            return super.searchDisp(filters); //se llama al método del padre que la pasa por los filtros
        else
            return false; // si no está disponible, retorna 'false'
    }
    


    /**
     * @return Devuelve una descripción completa de una habitación
     */
    @Override
    public String toString() {
        return "Habitación: "
                + this.getName()
                + " | Capacidad: "
                + this.getCapacity()
                + " | Disponible: " + this.available;
    }
    
    /**
     * 
     * @return Retorna un alojamiento/hospedaje.
     */
    public Housing getHousing() {
        return housing;
    }

    
    /**
     * 
     * @param housing (Housing) Alojamiento/hospedaje.
     */
    public void setHousing(Housing housing) {
        this.housing = housing;
    }

    
    /**
     * @return Devuelve, mediante true o false, si la habitación esta disponible
     * o no.
     */
    public boolean isAvailable() {
        return available;
    }

    
    /**
     * Permite modificar el estado de la disponibilidad
     *
     * @param available (boolean) Estado de la disponibilidad de una habitación.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

}
