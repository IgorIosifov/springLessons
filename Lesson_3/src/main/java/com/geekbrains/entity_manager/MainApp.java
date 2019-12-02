package com.geekbrains.entity_manager;

import com.geekbrains.PrepareDataApp;

import javax.persistence.EntityManager;

public class MainApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        EntityManager em = null;
        try {
            em = EMF.getEntityManager();
            em.getTransaction().begin();
            SimpleEntity se1 = em.find(SimpleEntity.class, 1L);
            System.out.println(se1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
