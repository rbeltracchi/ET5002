/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.data.ServicesDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.codec.digest.DigestUtils;

import com.plan111mil.data.entity.User;
import com.plan111mil.exception.InvalidCredentialsException;

/**
 *
 * @author ET5002
 */
public abstract class DBDAO {

	/**
	 * Solo es visible por esta clase. Se instancia una unica vez.
	 */
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCATpersistence");
	private static EntityManager entityManager = emf.createEntityManager();

	static <T> List<T> findAll(Class<T> objectClass) {
		Query findAllQuery = entityManager.createQuery("from " + objectClass.getSimpleName());
		List<T> listAll = (List<T>) findAllQuery.getResultList();
		refreshEntityManager();
		return listAll;
	}

	/**
	 * Creo una consulta sql para buscar si el usuario que se está tratando de crear
	 * existe en la base. en caso de encontrar un resultado se lanza una excepción
	 * donde no encuentre registros devuelve false, caso contrario, devuelve true
	 */
	static boolean userExists(String userName) {
		Query query = entityManager.createQuery("from User where name = :user");
		query.setParameter("user", userName);
		try {
			query.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		return true;
	}

	static boolean persist(Object objToPersist) {
		boolean persistido = true;
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(objToPersist);
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			persistido = false;
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		return persistido;
	}

	static public void delete(Object objToDelete) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(objToDelete);
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	static User validateUser(String userName, String password) throws InvalidCredentialsException {
		String passwordMD5 = DigestUtils.md5Hex(password);

		Query loginQuery = entityManager.createQuery(
				"from " + User.class.getSimpleName() + " where name=:name and password=:password", User.class);
		loginQuery.setParameter("name", userName);
		loginQuery.setParameter("password", passwordMD5);
		try {
			return (User) loginQuery.getSingleResult();

		} catch (NoResultException e) {
			throw new InvalidCredentialsException(); // si ningun usuario cumplió se lanza excepción
		}
	}

	/**
	 * Renueva el entity manager para que los objetos pedidos hasta el momento ya no
	 * esten pendientes de persistencia por Hibernate. Es util cuando se quiere
	 * mostrar un listado filtrado, para no tener problemas con las modificaciones
	 * pendientes de transaccion que quedan tras modificar un objeto que es
	 * persistido por Hibernate.
	 * 
	 * @param obj
	 */
	private static void refreshEntityManager() {
		entityManager.close();
		entityManager = emf.createEntityManager();
	}

}
