package com.plan111mil.data.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.plan111mil.filter.Filter;

/**
 *
 * @author ET5002
 *
 */
@Entity
public class Housing extends HostService {
	public static final String HOUSING_TYPES[] = { "Hotel", "Cabaña", "Hostel" };

	private String address;
	private String mail;
	private String phone;
	/**
	 * El atributo orphanRemoval le indica a Hibernate que desasocie las referencias
	 * de las rooms sobre el housing cuando se intente persistir un delete sobre la
	 * lista de rooms (y por lo tanto sobre la tabla ROOM.
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "housing", orphanRemoval = true) // la
																												// anotación
																												// 'FetchType.EAGER'
																												// hace
																												// que
																												// al
																												// momento
	// de traer Housing de la BD también se carguen sus rooms.
	private List<Room> rooms;
	@OneToOne
	private User idUser;

	/**
	 * Constructor Vacío
	 */
	public Housing() {
	}

	/**
	 * Método Constructor.
	 *
	 * @param address
	 *            (String) Dirección del hospedaje/alojamiento.
	 * @param mail
	 *            (String) E-mail del hospedaje/alojamiento..
	 * @param phone
	 *            (String) Teléfono del hospedaje/alojamiento..
	 * @param name
	 *            (String) Nombre del hospedaje/alojamiento.
	 * @param description
	 *            (String) Descripción del hospedaje/alojamiento..
	 * @param hostServiceType
	 *            (String) Descripción del tipo de alojamiento.
	 * @param user
	 */
	public Housing(String address, String mail, String phone, String name, String description, String hostServiceType,
			User user) {
		super(name, description, hostServiceType);
		this.address = address;
		this.mail = mail;
		this.phone = phone;
		this.idUser = user;
		this.rooms = new ArrayList<>();
	}

	/**
	 * Este es un método que comprueba si un hospedaje/alojamiento pasa el criterio
	 * de filtrado recibido por parámetro
	 *
	 * @param filters
	 *            es la lista de filtros
	 * 
	 * @return retorna 'true' solo si entre el hospedaje/alojamiento y las
	 *         habitaciones disponibles se cumplen todos los filtros
	 */
	@Override
	public boolean searchDisp(List<Filter> filters) {
		for (Filter f : filters) {
			if (!f.isAMatch(this))
				return false;
		}
		return true;
	}

	/**
	 * Permite agregar una nueva habitación a la lista.
	 *
	 * @param r
	 *            (Room) Nueva habitación en la lista.
	 */
	public void addRoom(Room r) {
		this.rooms.add(r);
	}

	/**
	 * Permite eliminar una habitación a la lista.
	 *
	 * @param r
	 *            (Room) Habitación a eliminar.
	 */
	public void deleteRoom(Room r) {
		this.rooms.remove(r);
	}

	/**
	 *
	 * @return Devuelve una descripción completa del hospedaje/alojamiento..
	 */
	@Override
	public String toString() {
		return "Hospedaje: " + this.getName() + " | mail: " + this.mail + " | Teléfono: " + this.phone
				+ " | Dirección: " + this.address + "\n";
	}

	/**
	 * Calcula la capacidad disponible del 'Housing' sumando la capacidad de sus
	 * 'rooms' disponibles
	 * 
	 * @return Devuelve la capacidad disponible del 'Housing'
	 */
	@Override
	public Integer getCapacity() {
		int capacity = 0;
		for (Room room : rooms) {
			if (room.isAvailable())
				capacity += room.getCapacity();
		}
		return capacity;
	}

	/**
	 * @return Devuelve la dirección del hospedaje/alojamiento.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Permite asignarle la dirección al hospedaje/alojamiento.
	 *
	 * @param address
	 *            (String) Dirección del hospedaje/alojamiento..
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return Devuelve el E-mail del hospedaje/alojamiento.
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Permite asignarle un correo electrónico al hospedaje/alojamiento.
	 *
	 * @param mail
	 *            (String) E-mail del hospedaje/alojamiento..
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return Devuelve el número de teléfono del hospedaje/alojamiento
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Permite asignar un número de teléfono al hospedaje/alojamiento.
	 *
	 * @param phone
	 *            (String) Teléfono del hospedaje/alojamiento.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return Devuelve un listado de las habitaciones del hospedaje/alojamiento.
	 */

	// Nuevo metodo by Lean
	public List<Room> getRooms() {
		Collections.sort(rooms);
		return rooms;
	}
	//
	// public List<Room> getRooms() {
	// return rooms;
	// }
	//

	/**
	 * Permite asignar una lista de habitaciones al hospedaje/alojamiento.
	 * 
	 * @param rooms
	 *            (List) Lista de habitaciones.
	 */
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
		for (Room tel : rooms) {
			tel.setHousing(this);
		}
	}

	/**
	 * @return the idUser
	 */
	public User getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser
	 *            the idUser to set
	 */
	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

}
