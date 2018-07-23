/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.data.ServicesDAO;

import java.util.List;

import com.plan111mil.data.entity.OfficeAdmin;
import com.plan111mil.data.entity.User;
import com.plan111mil.exception.InvalidCredentialsException;

/**
 *
 * @author ET5002
 */
public class UserServices {

	public UserServices() {
	}

	public List<User> getUsers() {
		return DBDAO.findAll(User.class);
	}

	public boolean persistUser(User user) {
		return DBDAO.persist(user);
	}

	public void deleteUsers(User user) {
		DBDAO.delete(user);
	}

	/**
	 * @LMusso
	 *
	 * 		La función de este método es proveer un mecanismo para el inicio de
	 *         sesión de usuarios desde la interface gráfica Si los parámetros son
	 *         correctos se devolverá el objeto usuario en caso contrario se lanzará
	 *         una excepción que podrá ser atrapada en la interface gráfica para
	 *         mostrar su mensaje por una ventana (dialog)
	 *
	 * @param userName
	 *            nombre de usuario
	 * @param password
	 *            contraseña
	 * @return Usuario
	 * @throws InvalidCredentialsException
	 *             en caso de error de nombre de usuario o contraseña
	 */
	public User validateUser(String userName, String password) throws InvalidCredentialsException {
		User user = null;
		try {
			user = DBDAO.validateUser(userName, password);
		}
		catch (InvalidCredentialsException e) {
			
			if (user == null) {
				if(getUsers().size() == 0) {
					user = new OfficeAdmin("admin", "admin", User.USER_TYPE_OFFICE_ADMIN);
					persistUser(user);
				}
				else
					throw new InvalidCredentialsException(); // si ningun usuario cumplió se lanza excepción
			}
			
		}
		return user;
	}

	/**
	 * Este método llama al repositorio para buscar la existencia de un usuario
	 */
	public boolean userExists(String userName) {
		return DBDAO.userExists(userName);
	}

}
