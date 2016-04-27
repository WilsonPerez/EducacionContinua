/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idi.ucuenca.controller.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author wilo
 */
public class GenericController<T> implements Serializable {

    private T objeto;

    public void GenericContoller(T objeto) {
        this.objeto = objeto;
    }

    public void GenericContoller() {
    }

    public T getObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }

    public EntityManager getEntityManager() {
        return EntityManagerUtil.get().createEntityManager();
    }

    public String create(T objeto) {

        EntityManager em = null;
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(objeto);
            em.getTransaction().commit();
            return "0";
        } catch (Exception ex) {
            try {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            } catch (Exception re) {
                re.printStackTrace();
            }
            System.out.println(ex.getCause());
            if (ex.getCause().toString().contains("org.hibernate.exception.ConstraintViolationException")) {
                return "1";
            }
            return "";
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    public void edit(T obj) throws Exception {
        EntityManager em = null;
        em = getEntityManager();
        System.out.println("editado................");
        try {
            em.getTransaction().begin();
            obj = em.merge(obj);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                re.printStackTrace();
            }

            ex.printStackTrace();
            throw new Exception(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(T objeto, Object id) {
        EntityManager em = getEntityManager();
        System.out.println("Eliminar ");
        try {
            em.getTransaction().begin();
            T obj = null;
            try {
                obj = (T) em.getReference(objeto.getClass(), id);
                System.out.println("Eliminado........ ");
            } catch (EntityNotFoundException enfe) {
            }
            em.remove(obj);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                ex.printStackTrace();
                em.getTransaction().rollback();
            } catch (Exception re) {
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public T find(T obj, Object id) {
        EntityManager em = getEntityManager();
        System.out.println("Clase: " + obj.getClass().getName() + id);
        try {
            return (T) em.find(obj.getClass(), id);
        } finally {
            em.close();
        }
    }

    public int getCount(T obj) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Object> rt = cq.from(obj.getClass());
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception c) {
            c.getMessage();
            return 0;
        } finally {
            em.close();
        }
    }

    public List<T> findEntities(int maxResults, int firstResult, T obj) {
        return findEntities(false, maxResults, firstResult, obj);
    }

    private List<T> findEntities(boolean all, int maxResults, int firstResult, T obj) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(obj.getClass()));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<T> listarTodos(T obj) {
        EntityManager em = getEntityManager();
        String clase = obj.getClass().getSimpleName();
        Query query = em.createQuery("select c from " + clase + " c");
        List resultList = query.getResultList();
        em.close();
        return resultList;
    }

    
    
    
    
     public List<T> findWhere(T obj, String where) {
        EntityManager em = getEntityManager();
        try {
            
            Query q = em.createQuery("select object(c) from "+ obj.getClass().getSimpleName() + " as c where " + where);
            List<T> lp = q.getResultList();
            return lp;
        }catch(Exception e){
            e.printStackTrace();
                System.out.println(e.getMessage());
        }
        finally{
            em.close();
        }
        return null;
    }

    
    
    public List<T> findWhere(T obj, T obj2) {
        
        EntityManager em = getEntityManager();
        try {
           
            String clase = obj.getClass().getSimpleName();
            Query query = em.createQuery("select c from " + clase + "  c where c.escuela = :pescuela");

            query.setParameter("pescuela", obj2);
            List<T> productos = query.getResultList();
           return productos;
        } finally {
            em.close();
        }
    }
    public List<T> findWhereComentarioUsuario(T obj, T obj2, int estado) {
        EntityManager em = getEntityManager();
        try{
            String clase = obj.getClass().getSimpleName();
            Query query = em.createQuery("select c from " + clase + "  c where c.usuario = :pusuario and c.estado = :pestado");

            query.setParameter("pusuario", obj2);
            query.setParameter("pestado", estado);

            List<T> productos = query.getResultList();
            
            return productos;
        } finally {
            em.close();
        }
    }

    public List<T> findWhereQuery(T obj, String where, Map<String, Object> parametros) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery(" from " + obj.getClass().getName() + " c where " + where);
            for (Entry<String, Object> parametroi : parametros.entrySet()) {
                q.setParameter(parametroi.getKey(), parametroi.getValue());
            }
            List<T> lp = q.getResultList();
            return lp;
        }catch(Exception e){
            e.printStackTrace();
                System.out.println(e.getMessage());
        }
        finally{
            em.close();
        }
        return null;
    }


    
}
