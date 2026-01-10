package com.example.gestores;
import com.example.modelo.Mago;
import com.example.util.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class GestorMago {
    public void crearMago(Mago mago) {
        EntityTransaction tx = null;
        try(EntityManager em = HibernateUtil.getEntityManager()){
            tx = em.getTransaction();
            tx.begin();
            if (mago.getNombre() != null || mago.getNivelMagia() != null
                    || mago.getVida() != null) {
                em.persist(mago);
                tx.commit();
            }
        }catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error creando el mago: " + e.getMessage());
        }
    }
    public void eliminarMago(Mago mago) {
        EntityTransaction tx = null;
        try(EntityManager em = HibernateUtil.getEntityManager()){
            tx = em.getTransaction();
            tx.begin();
            if (mago.getNombre() != null || mago.getNivelMagia() != null
                    || mago.getVida() != null) {
                em.remove(mago);
                tx.commit();
            }
        }catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error eliminando el mago: " + e.getMessage());
        }
    }
    public void modificarMago(Mago mago) {
        EntityTransaction tx = null;
        try(EntityManager em = HibernateUtil.getEntityManager()){
            tx = em.getTransaction();
            tx.begin();
            if (mago.getNombre() != null || mago.getNivelMagia() != null
                    || mago.getVida() != null) {
                em.merge(mago);
                tx.commit();
            }
        }catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error modificando el mago: " + e.getMessage());
        }
    }

    /**
     * Obtiene un mago por su ID
     * @param id ID del mago a buscar
     * @return El mago encontrado o null si no existe
     */
    public Mago obtenerMagoPorId(Integer id) {
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            return em.find(Mago.class, id);
        } catch (Exception e) {
            System.out.println("Error obteniendo el mago: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene todos los magos de la base de datos
     * @return Lista de todos los magos
     */
    public List<Mago> obtenerTodosMagos() {
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            return em.createQuery("SELECT m FROM Mago m", Mago.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error obteniendo los magos: " + e.getMessage());
            return null;
        }
    }
}