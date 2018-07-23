package com.plan111mil.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

//import org.hibernate.annotations.LazyCollection;
//import org.hibernate.annotations.LazyCollectionOption;

import com.plan111mil.filter.Filter;

/**
 *
 * @author ET5002
 * 
 */
@MappedSuperclass // esta anotación es lo que se encarga que funcione el mapeo con la jerarquia y
					// la clase abstracta
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // se creará una tabla por clase concreta
public abstract class HostService implements Serializable, Comparable<HostService> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(unique = true)
	private String name;

	@ElementCollection // esta anotación genera las tablas (de la lista de String) en la base con las
						// 'commodities'(de Housing y Room)
	//@LazyCollection(LazyCollectionOption.FALSE) // esta anotación es para que se cargue la lista al momento de cargar la
												// entidad
	private List<String> commodities;

	private String description;
	private String hostServiceType;

	/**
	 * Constructor vacío
	 */
	public HostService() {
	}

	/**
	 * Constructor de los diferentes tipos de hospedaje/alojamiento del sistema
	 *
	 * @param name
	 *            (String) Nombre del hospedaje/alojamiento.
	 * @param description
	 *            (String) Descripción del hospedaje/alojamiento.
	 * @param hostServiceType
	 *            (String) Tipo de Alojamiento (Hotel, cabaña, etc)
	 *
	 */
	public HostService(String name, String description, String hostServiceType) {
		this.name = name;
		this.commodities = new ArrayList<>();
		this.description = description;
		this.hostServiceType = hostServiceType;
	}

	/**
	 * Este es un método que comprueba si un HostService pasa el criterio de
	 * filtrado recibido por parámetro
	 *
	 * @param filters
	 *            es la lista de filtros, se deben cumplir todos los filtros.
	 *
	 * @return retorna 'true' solo si pasa todos los filtros.
	 */
	public abstract boolean searchDisp(List<Filter> filters);

	/**
	 * Agrega un nuevo servicio al hospedaje/alojamiento.
	 *
	 * @param c
	 *            (String) Nuevo Servicio del hospedaje/alojamiento.
	 */
	public void addCommodity(String c) {
		this.commodities.add(c);
	}

	/**
	 * Elimina un servicio al hospedaje/alojamiento.
	 *
	 * @param c
	 *            (String) Servicio a eliminar.
	 */
	public void removeCommodity(String c) {
		this.commodities.remove(c);
	}

	/**
	 * @return Retorna nombre del hospedaje/alojamiento.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Se le asigna un nombre.
	 *
	 * @param name
	 *            (String) nombre del hospedaje/alojamiento.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return Retorna el ID del hospedaje/alojamiento.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            (Integer) ID del hospedaje/alojamiento.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna la lista de servicios del hospedaje/alojamiento.
	 */
	public List<String> getCommodities() {
		return commodities;
	}

	/**
	 * Asigna una lista de servicios al hospedaje/alojamiento.
	 *
	 * @param commodities
	 *            (List) Lista de los servicios.
	 */
	public void setCommodities(List<String> commodities) {
		this.commodities = commodities;
	}

	/**
	 * @return Retorna la descripción del hospedaje/alojamiento.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Asigna una descripción al hospedaje/alojamiento.
	 *
	 * @param description
	 *            (String) Descripción del hospedaje/alojamiento.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Retorna la capacidad
	 */
	public abstract Integer getCapacity();

	/**
	 * @return Retorna el tipo de hospedaje/alojamiento. Ej.: Hotel, cabaña, etc.
	 */
	public String getHostServiceType() {
		return hostServiceType;
	}

	/**
	 * Asigna el tipo de hospedaje/alojamiento. Ej.: Hotel, cabaña, etc.
	 * 
	 * @param hostServiceType
	 *            (String) Tipo de hospedaje/alojamiento.
	 */
	public void setHostServiceType(String hostServiceType) {
		this.hostServiceType = hostServiceType;
	}

	@Override
	public int compareTo(HostService o) {
		return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
	}

}
