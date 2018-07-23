/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author ET5002
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // se creará una única tabla para toda la jerarquia
// mediante esta anotación se indica el nombre de la columna discriminadora y el
// tipo
@DiscriminatorColumn(name = "userType", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("housingUser") // se le asigna un valor al discriminador para esta clase (String: housingUser)
public class User implements Serializable {

	@Id
	@Column(name = "idUser")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(nullable = false, unique = true) // su valor no pude ser null y es único (solo un nombre de usuario en BD)
	private String name;
	@Column(nullable = false, length = 32) // su valor no pude ser null y su longitud 32 caracteres (longitud MD5)
	private String password;

	@OneToOne(mappedBy = "idUser")
	private Housing housing;

	// estas constantes se usan para mantener los valores posibles de la columna
	// discriminadora
	public static final String USER_TYPE_HOUSING_USER = "housingUser";
	public static final String USER_TYPE_OFFICE_ADMIN = "officeAdmin";

	// el atributo userType es la columna discriminadora en BD, su valor depende
	// de la clase. Por lo que se mapea de solo lectura (no se puede insertar, no se
	// puede actualizar)
	// por lo tanto no tiene setter, solo getter para consultar su valor
	@Column(insertable = false, updatable = false)
	private String userType;

	/**
	 *
	 */
	public User() {

	}

	/**
	 * @param name
	 * @param password
	 *            contraseña no cifrada de la que se guardará su hash MD5
	 */
	public User(String name, String password, String userType) {
		this.name = name;
		this.password = DigestUtils.md5Hex(password);
		this.userType = userType;
	}

	/**
	 * 
	 * @return retorna 'true' si es usuario administrador
	 */
	public boolean isOfficeAdmin() {
		return userType.equals(USER_TYPE_OFFICE_ADMIN);
	}

	/**
	 * 
	 * @return tipo usuario (según columna discriminadora en BD)
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the encrypted password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = DigestUtils.md5Hex(password);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the housing
	 */
	public Housing getHousing() {
		return housing;
	}

	/**
	 * @param housing
	 *            the housing to set
	 */
	public void setHousing(Housing housing) {
		this.housing = housing;
	}

	@Override
	public String toString() {
		return "Nombre: " + name + " | Tipo usuario: " + this.getUserType() + "\n";
	}
}
