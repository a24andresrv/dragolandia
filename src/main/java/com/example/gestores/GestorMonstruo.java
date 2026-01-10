package com.example.gestores;
import com.example.modelo.Monstruo;
import com.example.util.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class GestorMonstruo {

    public void crearMonstruo(Monstruo monstruo) {
        EntityTransaction tx = null;
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            if (monstruo.getNombre() != null || monstruo.getVida() != null
                    || monstruo.getFuerza() != null || monstruo.getTipo() != null) {
                em.persist(monstruo);
                tx.commit();
            }
        }catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error creando el monstruo: " + e.getMessage());
        }
    }
    public void modificarMonstruo(Monstruo monstruo) {
        EntityTransaction tx = null;
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            if (monstruo.getNombre() != null || monstruo.getVida() != null
                    || monstruo.getFuerza() != null || monstruo.getTipo() != null) {
                em.merge(monstruo);
                tx.commit();
            }
        }catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error modificando el monstruo: " + e.getMessage());
        }
    }
    public void eliminarMonstruo(Monstruo monstruo) {
        EntityTransaction tx = null;
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            if (monstruo.getNombre() != null || monstruo.getVida() != null
                    || monstruo.getFuerza() != null || monstruo.getTipo() != null) {
                em.remove(monstruo);
                tx.commit();
            }
        }catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error eliminando el monstruo: " + e.getMessage());
        }
    }

    /**
     * Obtiene un monstruo por su ID
     * @param id ID del monstruo a buscar
     * @return El monstruo encontrado o null si no existe
     */
    public Monstruo obtenerMonstruoPorId(Integer id) {
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            return em.find(Monstruo.class, id);
        } catch (Exception e) {
            System.out.println("Error obteniendo el monstruo: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene todos los monstruos de la base de datos
     * @return Lista de todos los monstruos
     */
    public List<Monstruo> obtenerTodosMonstruos() {
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            return em.createQuery("SELECT m FROM Monstruo m", Monstruo.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error obteniendo los monstruos: " + e.getMessage());
            return null;
        }
    }
}
