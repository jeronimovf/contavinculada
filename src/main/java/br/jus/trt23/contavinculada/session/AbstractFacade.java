/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.session;

import br.jus.trt23.contavinculada.entities.EntidadeGenerica;
import br.jus.trt23.contavinculada.qualifiers.Slf4jLogger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public void create(T entity) throws Exception {
        entity.setCriadoEm(getTimestampOnServer());
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

    //o método findAll retorna apenas os registros que não tenham sido destruídos
    public List<T> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c).where(cb.isNull(c.get("destruidoEm")));
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    //o método findAll retorna apenas os registros que não tenham sido destruídos
    public List<T> findRange(int[] range) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c).where(cb.isNull(c.get("destruidoEm")));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    //o método findAll retorna apenas os registros que não tenham sido destruídos
    // e que estejam vigentes em todo o período informado
    public List<T> findAll(LocalDate vigenteDesde, LocalDate vigenteAte) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c).where(
                cb.and(
                    cb.lessThanOrEqualTo(c.get("vigenteDesde"),vigenteDesde),
                    cb.greaterThanOrEqualTo(c.get("vigenteDesde"),vigenteAte),
                    cb.isNull(c.get("destruidoEm"))
                ));
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    //o método findAll retorna apenas os registros que não tenham sido destruídos
    //o método findAll retorna apenas os registros que não tenham sido destruídos
    // e que estejam vigentes em todo o período informado
    public List<T> findRange(int[] range, LocalDate vigenteDesde,
            LocalDate vigenteAte) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c).where(
                cb.and(
                    cb.lessThanOrEqualTo(c.get("vigenteDesde"),vigenteDesde),
                    cb.greaterThanOrEqualTo(c.get("vigenteDesde"),vigenteAte),
                    cb.isNull(c.get("destruidoEm"))
                ));
        Query q = getEntityManager().createQuery(cq);
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
    
    public LocalDateTime getTimestampOnServer() {
        Query qry = getEntityManager().createNativeQuery("SELECT localtimestamp");
        Timestamp ts = (Timestamp) qry.getSingleResult();
        return ts.toLocalDateTime();
    }    
}
