package com.example.gestores;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.modelo.Mago;

public class GestorMago {
    public void crearMago(Mago mago) {
        Transaction tx = null;
        try(SessionFactory factory = new Configuration().configure().buildSessionFactory()){
            Session session = factory.getCurrentSession();
            tx = session.beginTransaction();
            if (mago.getNombre() != null || mago.getNivelMagia() != null
                    || mago.getVida() != null) {
                session.persist(mago);
                tx.commit();
            }
        }catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void eliminarMago(Mago mago) {
        Transaction tx = null;
        try(SessionFactory factory = new Configuration().configure().buildSessionFactory()){
            Session session = factory.getCurrentSession();
            tx = session.beginTransaction();
            if (mago.getNombre() != null || mago.getNivelMagia() != null
                    || mago.getVida() != null) {
                session.remove(mago);
                tx.commit();
            }
        }catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void modificarMago(Mago mago) {
        Transaction tx = null;
        try(SessionFactory factory = new Configuration().configure().buildSessionFactory()){
            Session session = factory.getCurrentSession();
            tx = session.beginTransaction();
            if (mago.getNombre() != null || mago.getNivelMagia() != null
                    || mago.getVida() != null) {
                session.merge(mago);
                tx.commit();
            }
        }catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
    }
}