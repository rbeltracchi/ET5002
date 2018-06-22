/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.user;

import com.plan111mil.hostservice.HostService;
import com.plan111mil.hostservice.Housing;
import java.util.List;
import javax.persistence.Entity;


/**
 *
 * @author JMontoya
 */
@Entity(name = "Tourism_Office")
public class OfficeAdmin extends User {
    
    /**
     *
     */
    public OfficeAdmin(){
        
    }
    
    /**
     *
     * @param id
     * @param name
     * @param password
     */
    public OfficeAdmin(String name, String password){
        super(name,password);
    }
    
    
    /**
     * @author LTaglioretti
     * @author JMontoya
     * @param listUsers
     * @param user
     */
    public void addUser(List<User> listUsers, User user){
        if(!(listUsers.contains(user)))
            listUsers.add(user);
    }
    
    
    /**
     * @author LTaglioretti
     * @author JMontoya
     * @param users
     * @param user 
     */
    public void deleteUser(List<User> users, User user){
        users.remove(user);
    }
    
    
    /**
     * @author LTaglioretti
     * @author JMontoya
     * 
     * @param listHousings
     * @param housing
     * @param user 
     */
    public void addHousing(List<HostService> listHousings, Housing housing, User user){
        if(!(listHousings.contains(housing))){
            housing.setIdUser(user);
            listHousings.add(housing);
        }
    }
    
    
    /**
     * @author LTaglioretti
     * @author JMontoya
     * @param housings
     * @param housing 
     */
    public void deleteHousing(List<HostService> housings, HostService housing){
        housings.remove(housing);
    }
}
