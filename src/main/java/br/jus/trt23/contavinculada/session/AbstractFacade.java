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
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
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
    
    public List<T> findRange(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        javax.persistence.criteria.Root<T> entityRoot = cq.from(entityClass);
        cq.select(entityRoot);
        List<javax.persistence.criteria.Predicate> predicates = getPredicates(cb, entityRoot, filters);
        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new javax.persistence.criteria.Predicate[]{}));
        }
        if (sortField != null && sortField.length() > 0) {
            if (entityRoot.get(sortField) != null) {
                if (sortOrder.equals(SortOrder.ASCENDING)) {
                    cq.orderBy(cb.asc(entityRoot.get(sortField)));
                }
                if (sortOrder.equals(SortOrder.DESCENDING)) {
                    cq.orderBy(cb.desc(entityRoot.get(sortField)));
                }
            }
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(pageSize);
        q.setFirstResult(first);
        return q.getResultList();
    }

    public List<T> findRange(int first, int pageSize, List<SortMeta> sortFields, Map<String, Object> filters) {
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        javax.persistence.criteria.Root<T> entityRoot = cq.from(entityClass);
        cq.select(entityRoot);
        List<javax.persistence.criteria.Predicate> predicates = getPredicates(cb, entityRoot, filters);
        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new javax.persistence.criteria.Predicate[]{}));
        }
        if (sortFields != null && !sortFields.isEmpty()) {
            for (SortMeta sortMeta : sortFields) {
                if (entityRoot.get(sortMeta.getSortField()) != null) {
                    if (sortMeta.getSortOrder().equals(SortOrder.ASCENDING)) {
                        cq.orderBy(cb.asc(entityRoot.get(sortMeta.getSortField())));
                    }
                    if (sortMeta.getSortOrder().equals(SortOrder.DESCENDING)) {
                        cq.orderBy(cb.desc(entityRoot.get(sortMeta.getSortField())));
                    }
                }
            }
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(pageSize);
        q.setFirstResult(first);
        return q.getResultList();
    }

    public int count(Map<String, Object> filters) {
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        javax.persistence.criteria.Root<T> entityRoot = cq.from(entityClass);
        cq.select(cb.count(entityRoot));
        List<javax.persistence.criteria.Predicate> predicates = getPredicates(cb, entityRoot, filters);
        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new javax.persistence.criteria.Predicate[]{}));
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    private List<Predicate> getPredicates(CriteriaBuilder cb, Root<T> entityRoot, Map<String, Object> filters) {
        javax.persistence.metamodel.Metamodel entityModel = this.getEntityManager().getMetamodel();
        javax.persistence.metamodel.ManagedType<T> entityType = entityModel.managedType(entityClass);
        java.util.Set<javax.persistence.metamodel.EmbeddableType<?>> embeddables = entityModel.getEmbeddables();
        String fieldTypeName = null;
        // Add predicates (WHERE clauses) based on filters map
        List<javax.persistence.criteria.Predicate> predicates = new java.util.ArrayList<>();
        for (String s : filters.keySet()) {
            javax.persistence.criteria.Path<Object> pkFieldPath = null;
            if (s.contains(".")) {
                String embeddedIdField = s.split("\\.")[0];
                String embeddedIdMember = s.split("\\.")[1];
                pkFieldPath = entityRoot.get(embeddedIdField).get(embeddedIdMember);
                javax.persistence.metamodel.EmbeddableType<?> embeddableType = entityModel.embeddable(entityType.getAttribute(embeddedIdField).getJavaType());
                fieldTypeName = embeddableType.getAttribute(embeddedIdMember).getJavaType().getName();
            } else {
                pkFieldPath = entityRoot.get(s);
                fieldTypeName = entityType.getAttribute(s).getJavaType().getName();
            }
            if (pkFieldPath != null && fieldTypeName != null) {
                if (fieldTypeName.contains("String")) {
                    predicates.add(cb.like((javax.persistence.criteria.Expression) pkFieldPath, filters.get(s) + "%"));
                } else {
                    javax.persistence.criteria.Expression<?> filterExpression = getCastExpression((String) filters.get(s), fieldTypeName, cb);
                    if (filterExpression != null) {
                        predicates.add(cb.equal((javax.persistence.criteria.Expression<?>) pkFieldPath, filterExpression));
                    } else {
                        predicates.add(cb.equal((javax.persistence.criteria.Expression<?>) pkFieldPath, filters.get(s)));
                    }
                }
            }
        }
        return predicates;
    }

    private Expression<?> getCastExpression(String searchValue, String typeName, CriteriaBuilder cb) {
        javax.persistence.criteria.Expression<?> expression = null;
        switch (typeName) {
            case "short":
                expression = cb.literal(Short.parseShort(searchValue));
                break;
            case "byte":
                expression = cb.literal(Byte.parseByte(searchValue));
                break;
            case "int":
                expression = cb.literal(Integer.parseInt(searchValue));
                break;
            case "long":
                expression = cb.literal(Long.parseLong(searchValue));
                break;
            case "float":
                expression = cb.literal(Float.parseFloat(searchValue));
                break;
            case "double":
                expression = cb.literal(Double.parseDouble(searchValue));
                break;
            case "boolean":
                expression = cb.literal(Boolean.parseBoolean(searchValue));
                break;
            default:
                break;
        }
        return expression;
    }    
    
    public LocalDateTime getTimestampOnServer() {
        Query qry = getEntityManager().createNativeQuery("SELECT localtimestamp");
        Timestamp ts = (Timestamp) qry.getSingleResult();
        return ts.toLocalDateTime();
    }    
}
