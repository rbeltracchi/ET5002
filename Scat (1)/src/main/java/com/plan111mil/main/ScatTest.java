/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.main;

import com.plan111mil.filter.CapacityFilter;
import com.plan111mil.filter.CommodityFilter;
import com.plan111mil.filter.Filter;
import com.plan111mil.filter.TypeFilter;
import com.plan111mil.hostservice.Housing;
import com.plan111mil.hostservice.Room;
import com.plan111mil.scat.Scat;
import com.plan111mil.user.OfficeAdmin;
import com.plan111mil.user.User;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JArrascaeta
 * @author JMontoya
 * @author LMusso
 * @author RRomeo
 * @author JSardon
 * @author MSchmitz
 * @author CSilva
 * @author MSilva
 * @author SSprovieri
 * @author LTaglioretti
 * @author DVallejo
 * @author MVazquez
 * 
 */
public class ScatTest {
    public static void main(String[] args) {
        
        //Creación de la clase principal, donde se realizan las búsquedas.
        
        Scat scat = new Scat();
        
        // Creación de los objetos OfficeAdmin y User, con su tabla correspondiente.
        
        OfficeAdmin admin = new OfficeAdmin("Matias", "pass");
        User user1 = new User("Judit", "sinH");
        User user2 = new User("Laura", "Elena");
        User user3 = new User("Maria", "scat");
        
        // Creación de los objetos Housing y Room, con sus correspondientes tablas.
        
        Housing ofi = new Housing("direccion", "mail", "tel", "Oficina de Turismo", "Oficina", 0, "Of", admin);
        

        Housing h1 = new Housing("Suiza 1320", "reservas@altosdetandil.com.ar", "0249-154498751", "Altos de Tandil Hotel Boutique & Spa", "Hotel y Spa", 15, "Hotel", user1);
        ArrayList<String> commodities = new ArrayList<>();
        commodities.add("Estacionamiento");
        commodities.add("Piscina");
        commodities.add("****");
        h1.setCommodities(commodities);
        Room r1h1 = new Room( true, "1", "Vista a la sierra", 4, "Cuadruple", h1);
        ArrayList<String> roomcom1 = new ArrayList<>();
        roomcom1.add("WIFI");
        roomcom1.add("Jacuzzi");
        r1h1.setCommodities(roomcom1);
        Room r2h1 = new Room( false, "2", "Vista al parque", 2, "Double", h1);
        Room r3h1 = new Room( true, "3", "Vista a la sierra", 3, "Triple", h1);
        Room r4h1 = new Room(true, "4", "Vista a la sierra", 2, "Double", h1);
        ArrayList<String> roomcom4 = new ArrayList<>();
        roomcom4.add("WIFI");
        r4h1.setCommodities(roomcom4);
        Room r5h1 = new Room(true, "5", "Vista a la sierra", 3, "Triple", h1);
        Room r6h1 = new Room(false, "6", "Vista al parque", 3, "Triple", h1);
        h1.addRoom(r1h1);
        h1.addRoom(r2h1);
        h1.addRoom(r3h1);
        h1.addRoom(r4h1);
        h1.addRoom(r5h1);
        h1.addRoom(r6h1);

        
        Housing h2 = new Housing("C. Namuncura y Don Bosco", "info@hotelamaike.com", "0249-154337525",  "Amaike", "Hotel y Spa en el corazón de la sierra de Tandil ", 12, "Hotel", user2);
        ArrayList<String> commodities2 = new ArrayList<>();
        commodities2.add("Estacionamiento");
        commodities2.add("****");
        h2.setCommodities(commodities2);
        Room r1h2 = new Room(false, "Antú", "Vista al parque", 2, "Double", h2);
        Room r2h2 = new Room(true, "Kuyén", "Vista a la sierra", 3, "Triple", h2);
        Room r3h2 = new Room(true, "Mapu", "Vista a la sierra", 2, "Double", h2);
        ArrayList<String> roomcom3 = new ArrayList<>();
        roomcom3.add("WIFI");
        r3h2.setCommodities(roomcom3);
        Room r4h2 = new Room(true, "Wenú", "Vista a la sierra", 3, "Triple", h2);
        Room r5h2 = new Room(false, "Ruca", "Vista al parque", 3, "Triple", h2);
        h2.addRoom(r1h2);
        h2.addRoom(r2h2);
        h2.addRoom(r3h2);
        h2.addRoom(r4h2);
        h2.addRoom(r5h2);
        
        
        
        Housing h3 = new Housing("Mitre 545", "tandil@hotel-libertador.com.ar", " (0249) 442 2127 / 28", "Hotel Libertador", "Hotel", 20, "Hotel", user3);
        ArrayList<String> commodities3 = new ArrayList<>();
        commodities3.add("Estacionamiento");
        commodities3.add("****");
        h3.setCommodities(commodities3);
        Room r1h3 = new Room( true, "1", "Sin vista", 1, "Superior", h3);
        Room r2h3 = new Room( false, "2", "Vista al parque", 2, "Suite", h3);
        Room r3h3 = new Room( true, "3", "Vista al parque", 2, "Suite", h3);
        ArrayList<String> roomcom33 = new ArrayList<>();
        roomcom33.add("WIFI");
        r3h3.setCommodities(roomcom33);
        h3.addRoom(r1h3);
        h3.addRoom(r2h3);
        h3.addRoom(r3h3);
  
        //Se agregan a las listas principales de Scat los usuarios y alojamientos.
        
        
        scat.getHousings().add(h1);
        scat.getHousings().add(h2);
        scat.getHousings().add(h3);
        
        scat.getUsers().add(admin);
        scat.getUsers().add(user1);
        scat.getUsers().add(user2);
        scat.getUsers().add(user3);
        
        //Se crea la conexión a la base de datos
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCATpersistence");
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        
        
        //********************************************************************//
        //Se persisten los datos. Comentar para realizar pruebas.
        //********************************************************************//
        
        //Tabla Users
        manager.persist(admin);
        manager.persist(user1);
        manager.persist(user2);
        manager.persist(user3);
        
        //Tablas Housings y Rooms
        manager.persist(h1);
        manager.persist(r1h1);
        manager.persist(r2h1);                       
        manager.persist(r3h1);
        manager.persist(r4h1);
        manager.persist(r5h1);
        manager.persist(r6h1);
        
        //Tablas Housings y Rooms
        manager.persist(h2);
        manager.persist(r1h2);
        manager.persist(r2h2);
        manager.persist(r3h2);
        manager.persist(r4h2);
        manager.persist(r5h2);
        
        //Tablas Housings y Rooms
        manager.persist(h3);
        manager.persist(r1h3);
        manager.persist(r2h3);
        manager.persist(r3h3);
        
        //Se realiza el commit a la BD y se cierran las conexiones.
        manager.getTransaction().commit();
        manager.close();
        emf.close();
        
        //********************************************************************//
        //Fin de persistencia de datos. Comentar para realizar pruebas.
        //********************************************************************//
        
        //Se crean los filtros para las búsquedas.
        
        System.out.println("Búsqueda 1: ");
        Filter filtro1 = new CommodityFilter("Piscina");
        Filter filtro2 = new CommodityFilter("WIFI");
        Filter filtro3 = new CapacityFilter(10);
        Filter filtro4 = new TypeFilter("Cuadruple");
        ArrayList<Filter> filtros = new ArrayList<>();
        filtros.add(filtro1);
        filtros.add(filtro2);
        filtros.add(filtro3);
        filtros.add(filtro4);
        
        //Se llama al método principal de Scat, que realiza las búsquedas/filtrados de datos.
        System.out.println("Disponibles: " + scat.getFilteredListOfHousings(filtros));

        //Se crean los filtros para las búsquedas.
        
        System.out.println("Búsqueda 2: ");
        Filter filtro5 = new CommodityFilter("Estacionamiento");
        Filter filtro6 = new CapacityFilter(18);
        Filter filtro7 = new TypeFilter("Suite");
        ArrayList<Filter> filtros2 = new ArrayList<>();
        filtros2.add(filtro5);
        filtros2.add(filtro6);
        filtros2.add(filtro7); 
        
        //Se llama al método principal de Scat, que realiza las búsquedas/filtrados de datos.
        System.out.println("Disponibles: " + scat.getFilteredListOfHousings(filtros2));


    }
   
}
