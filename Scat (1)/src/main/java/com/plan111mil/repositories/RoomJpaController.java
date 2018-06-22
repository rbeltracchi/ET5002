/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.repositories;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.plan111mil.hostservice.Housing;
import com.plan111mil.hostservice.Room;
import com.plan111mil.repositories.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/*
 * @author JMontoya
 * @author LTaglioretti
 */
public class RoomJpaController extends DBManagerRepository implements Serializable {

    public RoomJpaController() {
    }

    public void create(Room room) {
        EntityManager em = null;
        try {
            em = super.getManager();
            em.getTransaction().begin();
            Housing housing = room.getHousing();
            if (housing != null) {
                housing = em.getReference(housing.getClass(), housing.getId());
                room.setHousing(housing);
            }
            em.persist(room);
            if (housing != null) {
                housing.getRooms().add(room);
                housing = em.merge(housing);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Room room) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = super.getManager();
            em.getTransaction().begin();
            Room persistentRoom = em.find(Room.class, room.getId());
            Housing housingOld = persistentRoom.getHousing();
            Housing housingNew = room.getHousing();
            if (housingNew != null) {
                housingNew = em.getReference(housingNew.getClass(), housingNew.getId());
                room.setHousing(housingNew);
            }
            room = em.merge(room);
            if (housingOld != null && !housingOld.equals(housingNew)) {
                housingOld.getRooms().remove(room);
                housingOld = em.merge(housingOld);
            }
            if (housingNew != null && !housingNew.equals(housingOld)) {
                housingNew.getRooms().add(room);
                housingNew = em.merge(housingNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = room.getId();
                if (findRoom(id) == null) {
                    throw new NonexistentEntityException("The room with id " + id + " no longer exists.");
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
            Room room;
            try {
                room = em.getReference(Room.class, id);
                room.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The room with id " + id + " no longer exists.", enfe);
            }
            Housing housing = room.getHousing();
            if (housing != null) {
                housing.getRooms().remove(room);
                housing = em.merge(housing);
            }
            em.remove(room);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Room> findRoomEntities() {
        return findRoomEntities(true, -1, -1);
    }

    public List<Room> findRoomEntities(int maxResults, int firstResult) {
        return findRoomEntities(false, maxResults, firstResult);
    }

    private List<Room> findRoomEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = super.getManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Room.class));
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

    public Room findRoom(Integer id) {
        EntityManager em = super.getManager();
        try {
            return em.find(Room.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoomCount() {
        EntityManager em = super.getManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Room> rt = cq.from(Room.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
