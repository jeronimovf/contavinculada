/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.session;

import br.jus.trt23.contavinculada.entities.EntidadeGenerica;
import br.jus.trt23.contavinculada.qualifiers.Slf4jLogger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;

/**
 *
 * @author j129-9
 * @param <T>
 */
@Dependent
public abstract class AbstractFacade<T extends EntidadeGenerica> {
    @Inject
    @Slf4jLogger
    Logger logger;

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();
    
    public abstract List<T> complete(String criteria);

    public void create(T entity) throws Exception{

       getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public T newInstance() {

        try {
            return (T) Class.forName(entityClass.getName()).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    public LocalDateTime getTimestampOnServer(){
        Query qry = getEntityManager().createNativeQuery("SELECT CURRENT_DATE AS now FROM DUAL", "currentTimestamp");
        Timestamp ts = (Timestamp) qry.getSingleResult();
        return ts.toLocalDateTime();               
    }
    
//    public Calendar getTimestamp() {
//        EntityManager em = entityManagerQC;
//        SessionImpl con = (SessionImpl) em.getDelegate();
//        try {
//            con.connection().createStatement().execute("ALTER SESSION SET NLS_DATE_FORMAT = 'DD/MM/YYYY HH24:MI:SS'");
//        } catch (SQLException ex) {
//            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Query qry = em.createNativeQuery("SELECT CURRENT_DATE AS now FROM DUAL", "currentTimestamp");
//        Calendar cal = Calendar.getInstance();
//        Timestamp ts = (Timestamp) qry.getSingleResult();
//        cal.setTime(ts);
//        return cal;
//    }
}
