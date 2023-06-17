/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PersistenceManager {
    private static PersistenceManager instance;
    private EntityManagerFactory entityManagerFactory;

    private PersistenceManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("HospitalUnit");
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    
    public static PersistenceManager getInstance() {
        if (instance == null) {
            instance = new PersistenceManager();
        }
        return instance;
    }

    public void deleteUserById(int userId) {
    EntityManager entityManager = PersistenceManager.getInstance().getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    
    try {
        transaction.begin();
        
        Query query = entityManager.createQuery("DELETE FROM Users u WHERE u.id = :userId");
        query.setParameter("userId", userId);
        query.executeUpdate();
        
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        // Handle the exception
    } finally {
        entityManager.close();
    }
}

    
    public boolean persistEntity(Object entity) {
        boolean isgood = true;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (RuntimeException e) {
            isgood = false;
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
        }
        
        return isgood;
    }
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
    
    
}
