package com.example.gestores;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.modelo.Dragon;

public class GestorDragon {
    public void crearDragon(Dragon dragon) {
        Transaction tx = null;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            Session session = factory.getCurrentSession();
            tx = session.beginTransaction();
            if (dragon.getNombre() != null || dragon.getIntensidadFuego() != null
                    || dragon.getResistencia() != null) {
                session.persist(dragon);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void eliminarDragon(Dragon dragon) {
        Transaction tx = null;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            Session session = factory.getCurrentSession();
            tx = session.beginTransaction();
            if (dragon.getNombre() != null || dragon.getIntensidadFuego() != null
                    || dragon.getResistencia() != null) {
                session.remove(dragon);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void modificarDragon(Dragon dragon) {
        Transaction tx = null;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            Session session = factory.getCurrentSession();
            tx = session.beginTransaction();
            if (dragon.getNombre() != null || dragon.getIntensidadFuego() != null
                    || dragon.getResistencia() != null) {
                session.merge(dragon);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
    }
}
