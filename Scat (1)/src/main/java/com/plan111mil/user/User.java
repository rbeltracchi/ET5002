/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.user;


import com.plan111mil.hostservice.Housing;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author JMontoya
 */
@Entity (name = "Users")
public class User implements Serializable{
    @Id
    @Column(name = "idUser")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String password;
    @OneToOne(mappedBy = "idUser")
    private Housing housing;
    
    
    
    /**
     *
     */
    public User(){
        
    }
    
    
    /**
     * @author JMontoya
     * @param id
     * @param name
     * @param password
     */
    public User(String name, String password){
        this.name = name;
        this.password = password;
    }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /**
     * @JMontoya
     * In the future this method is going to create a encripted password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    
    /**
     * @author JMontoya
     * 
     * @param users
     * @return true or false
     */
    public boolean validateUser(List<User> users){
        boolean success = true;
        if(users.contains(this))
            success = false;
        return success;
    }

    /**
     * @return the housing
     */
    public Housing getHousing() {
        return housing;
    }

    
    /**
     * @param housing the housing to set
     */
    public void setHousing(Housing housing) {
        this.housing = housing;
    }

}
