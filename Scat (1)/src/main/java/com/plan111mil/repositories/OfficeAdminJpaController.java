/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.repositories;

import com.plan111mil.repositories.exceptions.NonexistentEntityException;
import com.plan111mil.repositories.exceptions.PreexistingEntityException;
import com.plan111mil.user.OfficeAdmin;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author JMontoya
 * @author LTaglioretti
 */
public class OfficeAdminJpaController extends DBManagerRepository implements Serializable {

    public OfficeAdminJpaController() {
    }

    public void create(OfficeAdmin officeAdmin) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = super.getManager();
            em.getTransaction().begin();
            em.persist(officeAdmin);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOfficeAdmin(officeAdmin.getId()) != null) {
                throw new PreexistingEntityException("OfficeAdmin " + officeAdmin + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OfficeAdmin officeAdmin) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = super.getManager();
            em.getTransaction().begin();
            officeAdmin = em.merge(officeAdmin);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = officeAdmin.getId();
                if (findOfficeAdmin(id) == null) {
                    throw new NonexistentEntityException("The officeAdmin with id " + id + " no longer exists.");
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
            OfficeAdmin officeAdmin;
            try {
                officeAdmin = em.getReference(OfficeAdmin.class, id);
                officeAdmin.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The officeAdmin with id " + id + " no longer exists.", enfe);
            }
            em.remove(officeAdmin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OfficeAdmin> findOfficeAdminEntities() {
        return findOfficeAdminEntities(true, -1, -1);
    }

    public List<OfficeAdmin> findOfficeAdminEntities(int maxResults, int firstResult) {
        return findOfficeAdminEntities(false, maxResults, firstResult);
    }

    private List<OfficeAdmin> findOfficeAdminEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = super.getManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OfficeAdmin.class));
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

    public OfficeAdmin findOfficeAdmin(Integer id) {
        EntityManager em = super.getManager();
        try {
            return em.find(OfficeAdmin.class, id);
        } finally {
            em.close();
        }
    }

    public int getOfficeAdminCount() {
        EntityManager em = super.getManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OfficeAdmin> rt = cq.from(OfficeAdmin.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
