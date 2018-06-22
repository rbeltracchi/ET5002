/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.repositories;

import com.plan111mil.hostservice.Housing;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.plan111mil.hostservice.Room;
import com.plan111mil.repositories.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author JMontoya
 * @author LTaglioretti
 */
public class HousingJpaController extends DBManagerRepository implements Serializable {
    
    public HousingJpaController() {
    }

    public void create(Housing housing) {
        if (housing.getRooms() == null) {
            housing.setRooms(new ArrayList<Room>());
        }
        EntityManager em = null;
        try {
            em = super.getManager();
            em.getTransaction().begin();
            List<Room> attachedRooms = new ArrayList<Room>();
            for (Room roomsRoomToAttach : housing.getRooms()) {
                roomsRoomToAttach = em.getReference(roomsRoomToAttach.getClass(), roomsRoomToAttach.getId());
                attachedRooms.add(roomsRoomToAttach);
            }
            housing.setRooms(attachedRooms);
            em.persist(housing);
            for (Room roomsRoom : housing.getRooms()) {
                Housing oldHousingOfRoomsRoom = roomsRoom.getHousing();
                roomsRoom.setHousing(housing);
                roomsRoom = em.merge(roomsRoom);
                if (oldHousingOfRoomsRoom != null) {
                    oldHousingOfRoomsRoom.getRooms().remove(roomsRoom);
                    oldHousingOfRoomsRoom = em.merge(oldHousingOfRoomsRoom);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Housing housing) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = super.getManager();
            em.getTransaction().begin();
            Housing persistentHousing = em.find(Housing.class, housing.getId());
            List<Room> roomsOld = persistentHousing.getRooms();
            List<Room> roomsNew = housing.getRooms();
            List<Room> attachedRoomsNew = new ArrayList<Room>();
            for (Room roomsNewRoomToAttach : roomsNew) {
                roomsNewRoomToAttach = em.getReference(roomsNewRoomToAttach.getClass(), roomsNewRoomToAttach.getId());
                attachedRoomsNew.add(roomsNewRoomToAttach);
            }
            roomsNew = attachedRoomsNew;
            housing.setRooms(roomsNew);
            housing = em.merge(housing);
            for (Room roomsOldRoom : roomsOld) {
                if (!roomsNew.contains(roomsOldRoom)) {
                    roomsOldRoom.setHousing(null);
                    roomsOldRoom = em.merge(roomsOldRoom);
                }
            }
            for (Room roomsNewRoom : roomsNew) {
                if (!roomsOld.contains(roomsNewRoom)) {
                    Housing oldHousingOfRoomsNewRoom = roomsNewRoom.getHousing();
                    roomsNewRoom.setHousing(housing);
                    roomsNewRoom = em.merge(roomsNewRoom);
                    if (oldHousingOfRoomsNewRoom != null && !oldHousingOfRoomsNewRoom.equals(housing)) {
                        oldHousingOfRoomsNewRoom.getRooms().remove(roomsNewRoom);
                        oldHousingOfRoomsNewRoom = em.merge(oldHousingOfRoomsNewRoom);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = housing.getId();
                if (findHousing(id) == null) {
                    throw new NonexistentEntityException("The housing with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = super.getManager();
            em.getTransaction().begin();
            Housing housing;
            try {
                housing = em.getReference(Housing.class, id);
                housing.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The housing with id " + id + " no longer exists.", enfe);
            }
            List<Room> rooms = housing.getRooms();
            for (Room roomsRoom : rooms) {
                roomsRoom.setHousing(null);
                roomsRoom = em.merge(roomsRoom);
            }
            em.remove(housing);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Housing> findHousingEntities() {
        return findHousingEntities(true, -1, -1);
    }

    public List<Housing> findHousingEntities(int maxResults, int firstResult) {
        return findHousingEntities(false, maxResults, firstResult);
    }

    private List<Housing> findHousingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = super.getManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Housing.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Housing findHousing(Integer id) {
        EntityManager em = super.getManager();
        try {
            return em.find(Housing.class, id);
        } finally {
            em.close();
        }
    }

    public int getHousingCount() {
        EntityManager em = super.getManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Housing> rt = cq.from(Housing.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
