package com.example.gestores;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.example.modelo.Monstruo;
public class GestorMonstruo {
    public void crearMonstruo(Monstruo monstruo) {
        Transaction tx = null;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            Session session = factory.getCurrentSession();
            tx = session.beginTransaction();
            if (monstruo.getNombre() != null || monstruo.getVida() != null
                    || monstruo.getFuerza() != null || monstruo.getTipo() != null) {
                session.persist(monstruo);
                tx.commit();
            }
        }catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void modificarMonstruo(Monstruo monstruo) {
        Transaction tx = null;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            Session session = factory.getCurrentSession();
            tx = session.beginTransaction();
            if (monstruo.getNombre() != null || monstruo.getVida() != null
                    || monstruo.getFuerza() != null || monstruo.getTipo() != null) {
                session.merge(monstruo);
                tx.commit();
            }
        }catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void eliminarMonstruo(Monstruo monstruo) {
        Transaction tx = null;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            Session session = factory.getCurrentSession();
            tx = session.beginTransaction();
            if (monstruo.getNombre() != null || monstruo.getVida() != null
                    || monstruo.getFuerza() != null || monstruo.getTipo() != null) {
                session.remove(monstruo);
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
