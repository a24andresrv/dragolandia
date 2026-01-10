package com.example.gestores;

import com.example.modelo.Bosque;
import com.example.util.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;


public class GestorBosque {
    public void crearBosque(Bosque bosque) {
        EntityTransaction tx = null;
        try (EntityManager em = HibernateUtil.getEntityManager()) {
             tx = em.getTransaction();
            tx.begin();
            if (bosque.getMonstruoJefe() != null || bosque.getNombre() != null
                    || bosque.getNivelPeligro() != null) {
                em.persist(bosque);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error creando el bosque: " + e.getMessage());
        }
    }

    public void eliminarBosque(Bosque bosque) {
        EntityTransaction tx = null;
        try (EntityManager em = HibernateUtil.getEntityManager()) {
             tx = em.getTransaction();
            tx.begin();
            if ( bosque.getMonstruoJefe() != null || bosque.getNombre() != null
                    || bosque.getNivelPeligro() != null) {
                em.remove(bosque);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error eliminando el bosque: " + e.getMessage());
        }
    }

    public void modificarBosque(Bosque bosque) {
        EntityTransaction tx = null;
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            if ( bosque.getMonstruoJefe() != null || bosque.getNombre() != null
                    || bosque.getNivelPeligro() != null) {
                em.merge(bosque);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null){
                tx.rollback();
            }
            System.out.println("Error modificando el bosque: " + e.getMessage());
        }
    }

    /**
     * Obtiene un bosque por su ID
     * @param id ID del bosque a buscar
     * @return El bosque encontrado o null si no existe
     */
    public Bosque obtenerBosquePorId(Integer id) {
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            return em.find(Bosque.class, id);
        } catch (Exception e) {
            System.out.println("Error obteniendo el bosque: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene todos los bosques de la base de datos
     * @return Lista de todos los bosques
     */
    public List<Bosque> obtenerTodosBosques() {
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            return em.createQuery("SELECT b FROM Bosque b", Bosque.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error obteniendo los bosques: " + e.getMessage());
            return null;
        }
    }
}