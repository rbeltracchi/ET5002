/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.repositories;

import com.plan111mil.user.User;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author JMontoya
 */
public class UserRepository extends DBManagerRepository{
 
    private EntityManagerFactory emf;
    private EntityManager manager;
    
	public UserRepository() {
	}

	// SELECT / READ
	public User search(int idUser) {
		
		User user = this.getManager().find(User.class, idUser);
                return user;
	}
	
	// SELECT ALL / SELECT ALL
	public ArrayList<User> searchAllUsers(){
		
		//Esta sentencia ejecuta un lenguaje SQL especial de la JPA llamado JPQL, con el cual obtenemos todos los clientes.
		Query query = this.getManager().createQuery("SELECT user FROM User user");
		
		@SuppressWarnings("unchecked")
		ArrayList<User> users = (ArrayList<User>) query.getResultList();
		return users;
	}

	// INSERT / CREATE
	public void crear(User user) {
            this.getManager().getTransaction().begin();
            this.getManager().persist(user);
            this.getManager().getTransaction().commit();
	}

	// UPDATE / UPDATE
	public void actualizar(User user) {
            this.getManager().getTransaction().begin();
            this.getManager().persist(user);
            this.getManager().getTransaction().commit();

        }  
        
        // DELETE / DELETE
	public void eliminar(User user) {
            this.getManager().getTransaction().begin();
            this.getManager().remove(user);
            this.getManager().getTransaction().commit();
	}
}
