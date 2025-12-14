package com.example.gestores;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.modelo.Bosque;


public class GestorBosque {
    public void crearBosque(Bosque bosque) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            Session session = factory.getCurrentSession();
            Transaction tx = session.beginTransaction();
            if (bosque.getMonstruoJefe() != null || bosque.getNombre() != null
                    || bosque.getNivelPeligro() != null) {
                session.persist(bosque);
                tx.commit();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void eliminarBosque(Bosque bosque) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            Session session = factory.getCurrentSession();
            Transaction tx = session.beginTransaction();
            if ( bosque.getMonstruoJefe() != null || bosque.getNombre() != null
                    || bosque.getNivelPeligro() != null) {
                session.remove(bosque);
                tx.commit();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void modificarVida(Bosque bosque) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            Session session = factory.getCurrentSession();
            Transaction tx = session.beginTransaction();
            if ( bosque.getMonstruoJefe() != null || bosque.getNombre() != null
                    || bosque.getNivelPeligro() != null) {
                session.merge(bosque);
                tx.commit();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}