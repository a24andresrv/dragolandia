package com.example.gestores;

import com.example.modelo.Hechizo;
import com.example.util.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 * Clase GestorHechizo - Gestiona las operaciones CRUD de hechizos en la base de datos
 */
public class GestorHechizo {
    
    /**
     * Crea un nuevo hechizo en la base de datos
     * @param hechizo El hechizo a crear
     */
    public void crearHechizo(Hechizo hechizo) {
        EntityTransaction tx = null;
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            if (hechizo != null && hechizo.getNombre() != null) {
                em.persist(hechizo);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error creando el hechizo: " + e.getMessage());
        }
    }

    /**
     * Elimina un hechizo de la base de datos
     * @param hechizo El hechizo a eliminar
     */
    public void eliminarHechizo(Hechizo hechizo) {
        EntityTransaction tx = null;
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            if (hechizo != null && hechizo.getId() != null) {
                Hechizo hechizoAEliminar = em.find(Hechizo.class, hechizo.getId());
                if (hechizoAEliminar != null) {
                    em.remove(hechizoAEliminar);
                    tx.commit();
                }
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error eliminando el hechizo: " + e.getMessage());
        }
    }

    /**
     * Modifica un hechizo existente en la base de datos
     * @param hechizo El hechizo con los datos actualizados
     */
    public void modificarHechizo(Hechizo hechizo) {
        EntityTransaction tx = null;
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            if (hechizo != null && hechizo.getId() != null) {
                em.merge(hechizo);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error modificando el hechizo: " + e.getMessage());
        }
    }

    /**
     * Obtiene un hechizo por su ID
     * @param id ID del hechizo a buscar
     * @return El hechizo encontrado o null si no existe
     */
    public Hechizo obtenerHechizoPorId(Integer id) {
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            return em.find(Hechizo.class, id);
        } catch (Exception e) {
            System.out.println("Error obteniendo el hechizo: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene todos los hechizos de la base de datos
     * @return Lista de todos los hechizos
     */
    public List<Hechizo> obtenerTodosHechizos() {
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            return em.createQuery("SELECT h FROM Hechizo h", Hechizo.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error obteniendo los hechizos: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene los hechizos de un mago específico
     * @param magoId ID del mago
     * @return Lista de hechizos del mago
     */
    public List<Hechizo> obtenerHechizosPorMago(Integer magoId) {
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            return em.createQuery(
                "SELECT h FROM Hechizo h WHERE h.mago_id = :magoId", Hechizo.class)
                .setParameter("magoId", magoId)
                .getResultList();
        } catch (Exception e) {
            System.out.println("Error obteniendo los hechizos del mago: " + e.getMessage());
            return null;
        }
    }
}
