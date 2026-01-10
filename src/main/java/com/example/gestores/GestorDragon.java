package com.example.gestores;
import com.example.modelo.Dragon;
import com.example.util.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class GestorDragon {
    public void crearDragon(Dragon dragon) {
        EntityTransaction tx = null;
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            if (dragon.getNombre() != null || dragon.getIntensidadFuego() != null
                    || dragon.getResistencia() != null) {
                em.persist(dragon);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error creando el dragon: " + e.getMessage());
        }
    }

    public void eliminarDragon(Dragon dragon) {
        EntityTransaction tx = null;
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            if (dragon.getNombre() != null || dragon.getIntensidadFuego() != null
                    || dragon.getResistencia() != null) {
                em.remove(dragon);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error eliminando el dragon: " + e.getMessage());
        }
    }

    public void modificarDragon(Dragon dragon) {
        EntityTransaction tx = null;
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            if (dragon.getNombre() != null || dragon.getIntensidadFuego() != null
                    || dragon.getResistencia() != null) {
                em.merge(dragon);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error modificando el dragon: " + e.getMessage());
        }
    }

    /**
     * Obtiene un dragón por su ID
     * @param id ID del dragón a buscar
     * @return El dragón encontrado o null si no existe
     */
    public Dragon obtenerDragonPorId(Integer id) {
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            return em.find(Dragon.class, id);
        } catch (Exception e) {
            System.out.println("Error obteniendo el dragón: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene todos los dragones de la base de datos
     * @return Lista de todos los dragones
     */
    public List<Dragon> obtenerTodosDragones() {
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            return em.createQuery("SELECT d FROM Dragon d", Dragon.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error obteniendo los dragones: " + e.getMessage());
            return null;
        }
    }
}
