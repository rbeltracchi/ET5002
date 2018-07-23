/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.data.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author ET5002
 */
@Entity(name = "Tourism_Office")
@DiscriminatorValue("officeAdmin") // se le asigna un valor al discriminador para esta clase (String: officeAdmin)
public class OfficeAdmin extends User {

	public OfficeAdmin() {
	}

	/**
	 *
	 * @param name
	 * @param password
	 */
	public OfficeAdmin(String name, String password, String userType) {
		super(name, password, userType);
	}

	// ******** TODO: revisar si estos métodos siguen teniendo sentido ahora que
	// está userService ******//

	/**
	 * @param listUsers
	 * @param user
	 */
	public void addUser(List<User> listUsers, User user) {
		if (!(listUsers.contains(user)))
			listUsers.add(user);
	}

	/**
	 * @param users
	 * @param user
	 */
	public void deleteUser(List<User> users, User user) {
		users.remove(user);
	}

	/**
	 * 
	 * @param listHousings
	 * @param housing
	 * @param user
	 */
	public void addHousing(List<HostService> listHousings, Housing housing, User user) {
		if (!(listHousings.contains(housing))) {
			housing.setIdUser(user);
			listHousings.add(housing);
		}
	}

	/**
	 * @param housings
	 * @param housing
	 */
	public void deleteHousing(List<HostService> housings, HostService housing) {
		housings.remove(housing);
	}

}
