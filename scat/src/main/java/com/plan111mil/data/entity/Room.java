package com.plan111mil.data.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.plan111mil.filter.Filter;

/**
 *
 * @author ET5002
 */
@Entity
public class Room extends HostService {

	public static final String ROOM_TYPES[] = { "Simple", "Doble", "Triple", "Cuadruple" };
	private Integer capacity;
	private boolean available;

	/**
	 * 
	 * El atributo fetch en la anotacion ManyToOne le indica a Hibernate que levante
	 * el atributo Housing cuando se consulte por primera vez.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Housing housing;

	/**
	 * Constructor vacio
	 */
	public Room() {
	}

	/**
	 * Método constructor
	 *
	 * @param available
	 *            (boolean) Indica, mediante true o false, la disponibilidad.
	 * @param name
	 *            (String) Indica el nombre de la habitación.
	 * @param description
	 *            (String) Descripción de Room.
	 * @param capacity
	 *            (Integer) Capacidad de Room.
	 * @param hostServiceType
	 *            (String) Descripción del tipo de Room.
	 * @param housing
	 */
	public Room(boolean available, String name, String description, Integer capacity, String hostServiceType,
			Housing housing) {
		super(name, description, hostServiceType);
		this.capacity = capacity;
		this.available = available;
		this.housing = housing;
	}

	/**
	 * Este es un método que comprueba si una room está disponible y también cumple
	 * el criterio de filtrado recibido por parámetro.
	 *
	 * @param filters
	 *            es la lista de filtros, se deben cumplir todos los filtros.
	 *
	 * @return retorna 'true' solo si la room está disponible y pasa todos los
	 *         filtros.
	 */
	@Override
	public boolean searchDisp(List<Filter> filters) {
		// si la 'room' está disponible
		if (this.available) {
			// se recorren los filtros
			for (Filter filter : filters) {
				if (!filter.isAMatch(this)) // si no cumple
					return false;
			}
			// si cumple con todos
			return true;
		} else
			// si no está disponible
			return false;

	}

	/**
	 * @return Devuelve una descripción completa de una habitación
	 */
	@Override
	public String toString() {
		return "Habitación: " + this.getName() + " | Capacidad: " + this.getCapacity() + " | Disponible: "
				+ this.available;
	}

	/**
	 * 
	 * @return Retorna la capacidad de la room
	 */
	@Override
	public Integer getCapacity() {
		return capacity;
	}

	/**
	 * 
	 * @param capacity
	 *            Capacidad de la room
	 */
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
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
	 * @param housing
	 *            (Housing) Alojamiento/hospedaje.
	 */
	public void setHousing(Housing housing) {
		this.housing = housing;
	}

	/**
	 * @return Devuelve, mediante true o false, si la habitación esta disponible o
	 *         no.
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * Permite modificar el estado de la disponibilidad
	 *
	 * @param available
	 *            (boolean) Estado de la disponibilidad de una habitación.
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

}
