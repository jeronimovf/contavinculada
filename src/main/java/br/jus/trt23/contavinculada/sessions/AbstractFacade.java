/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.sessions;

import br.jus.trt23.contavinculada.entities.EntidadeGenerica;
import br.jus.trt23.contavinculada.handlers.LazyQueryHandler;
import br.jus.trt23.contavinculada.qualifiers.Slf4jLogger;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;

/**
 *
 * @author j129-9
 * @param <T>
 */
public abstract class AbstractFacade<T extends EntidadeGenerica> {

    @Inject
    @Slf4jLogger
    Logger logger;

    private final Class<T> entityClass;

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
        entity = getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    public T newInstance() {
        try {
            return (T) Class.forName(entityClass.getName()).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error(ex.getMessage());
            return null;
        }
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
    // e que estejam vigentes em todo o período informado
    public List<T> findAll(LocalDate vigenteDesde, LocalDate vigenteAte) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c).where(
                cb.and(
                        cb.lessThanOrEqualTo(c.get("vigenteDesde"), vigenteDesde),
                        cb.or(
                                cb.isNull(c.get("vigenteDesde")),
                                cb.greaterThanOrEqualTo(c.get("vigenteDesde"), vigenteAte)
                        ),
                        cb.isNull(c.get("destruidoEm"))
                ));
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    //o método findAll retorna apenas os registros que não tenham sido destruídos
    //o método findRange retorna apenas os registros que não tenham sido 
    //destruídos e que estejam vigentes em todo o período informado paginados
    //entre range[0] e range[1]
    public List<T> findRange(int[] range, LocalDate vigenteDesde,
            LocalDate vigenteAte) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c).where(
                cb.and(
                        cb.lessThanOrEqualTo(c.get("vigenteDesde"), vigenteDesde),
                        cb.or(
                                cb.isNull(c.get("vigenteDesde")),
                                cb.greaterThanOrEqualTo(c.get("vigenteDesde"), vigenteAte)
                        ),
                        cb.isNull(c.get("destruidoEm"))
                ));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();        
    }

    public List<T> findRange(LazyQueryHandler lqh){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> entityRoot = cq.from(entityClass);
        cq.select(entityRoot);
        List<Predicate> predicates = getPredicates(entityRoot, lqh.getFilters());
        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }
        if (lqh.getSortFields() != null && !lqh.getSortFields().isEmpty()) {
            for (String sortField : lqh.getSortFields().keySet()) {
                if (entityRoot.get(sortField) != null) {
                    String sortOrder = lqh.getSortFields().get(sortField);
                    if (sortOrder.startsWith("ASC")) {
                        cq.orderBy(cb.asc(entityRoot.get(sortField)));
                    }
                    if (sortOrder.startsWith("DESC")) {
                        cq.orderBy(cb.desc(entityRoot.get(sortField)));
                    }
                }
            }
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(lqh.getPageSize());
        q.setFirstResult(lqh.getPaginationFirst());
        return q.getResultList();       
    }
    
    //o método findRange retorna apenas os registros que não tenham sido 
    //destruídos paginados entre range[0] e range[1]
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
    
    //essa implementação tem por objetivo receber uma criteria query
    //já customizada para aplicar as restrições típicas de uma consulta
    //utilizada por um lazydatamodel
    public List<T> findRange(CriteriaQuery<T> cq, LazyQueryHandler lqh) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        Root<T> c = cq.from(entityClass);
        cq.getRestriction().getExpressions().addAll(getPredicates(c, lqh.getFilters()));

        if (lqh.getSortFields() != null && !lqh.getSortFields().isEmpty()) {
            for (String sortField : lqh.getSortFields().keySet()) {
                if (c.get(sortField) != null) {
                    String sortOrder = lqh.getSortFields().get(sortField);
                    if (sortOrder.startsWith("ASC")) {
                        cq.orderBy(cb.asc(c.get(sortField)));
                    }
                    if (sortOrder.startsWith("DESC")) {
                        cq.orderBy(cb.desc(c.get(sortField)));
                    }
                }
            }
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(lqh.getPageSize());
        q.setFirstResult(lqh.getPaginationFirst());
        return q.getResultList();
    }    
    
    public int count(Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> entityRoot = cq.from(entityClass);
        cq.select(cb.count(entityRoot));
        List<Predicate> predicates = getPredicates(entityRoot, filters);
        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public int count() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }    

    protected List<Predicate> getPredicates(Root<T> entityRoot, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.metamodel.Metamodel entityModel = this.getEntityManager().getMetamodel();
        javax.persistence.metamodel.ManagedType<T> entityType = entityModel.managedType(entityClass);
        String fieldTypeName;
        // Add predicates (WHERE clauses) based on filters map
        List<Predicate> predicates = new java.util.ArrayList<>();
        for (String s : filters.keySet()) {
            Path<Object> pkFieldPath;
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
                //TODO: a compilação de predicatos não faz a normalização
                //das strings para realizar a busca fonética
                //O dilema:  salvar no banco a versão fonética?
                //Criar uma função em bd e outra na aplicação para converter 
                //o texto armazenado e pesquisado e permitir a comparação?
                if (fieldTypeName.contains("String")) {
                    predicates.add(cb.like(cb.upper((Expression) pkFieldPath), filters.get(s).toString().toUpperCase()));
                } else {
                    Expression<?> filterExpression = getCastExpression((String) filters.get(s), fieldTypeName, cb);
                    if (filterExpression != null) {
                        predicates.add(cb.equal((Expression<?>) pkFieldPath, filterExpression));
                    } else {
                        predicates.add(cb.equal((Expression<?>) pkFieldPath, filters.get(s)));
                    }
                }
            }
        }
        return predicates;
    }

    private Expression<?> getCastExpression(String searchValue, String typeName, CriteriaBuilder cb) {
        Expression<?> expression = null;
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
        Query qry = getEntityManager().createNativeQuery("SELECT LOCALTIMESTAMP FROM DUAL");
        Timestamp ts = (Timestamp) qry.getSingleResult();
        return ts.toLocalDateTime();
    }
    
    public LocalDate getDateOnServer() {
        Query qry = getEntityManager().createNativeQuery("SELECT LOCALDATE FROM DUAL");
        Date date = (Date) qry.getSingleResult();
        return date.toLocalDate();
    }    
    
    public Boolean isVigente(EntidadeGenerica entidade) {
        LocalDate hoje = getTimestampOnServer().toLocalDate();
        return entidade.isVigenteParcialmente(hoje, hoje);
    }    

    //para entender a diferenca entre as funções que comparam períodos de 
    //vigência: 
    //VigentePlenamenteEntre: não interessa se o início da vigência  
    //do objeto corrente é anterior a do período de teste ou se o términdo da
    //vigência do objeto seja posterior ao final do período, mas se, em todo
    //o período de teste, o objeto esteve vigente.
    //Se periodo está contido o.vigencia
    //vigenteParcialmente: se o objeto corrente tiver sua vigência coincidindo
    //com qualquer parte do período teste, retorna verdadeiro.  
    //Se existe (o.vigencia intersecção periodo)
    //vigenteEstritamenteEntre: a vigência do objeto corrente deve estar
    //compreendida no período de teste.
    //Se o.vigencia está contido periodo.
    //retorna verdadeiro se a entidade tiver vigencia em todo o período
    //informado.
    //Se o método for seguido de Predicado ele apenas acrescenta o critério
    //a um objeto query previamente configurado.

    //retorna entidades cujas vigencias coincidam, ainda que parcialmente
    //no intervalo especificado
    public List<T> vigenteParcialmenteEntre(LocalDate inicio, LocalDate fim) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        vigenteParcialmentePredicado(cq, c, inicio, fim);
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    protected <X> Predicate vigenteHojePredicado(CriteriaQuery cq, Root<X> onde) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();        
        Predicate where = cq.getRestriction();
        List<Expression<Boolean>> expressions = new ArrayList<>();
        expressions.add(
                cb.and(
                        cb.lessThanOrEqualTo(onde.get("vigenteDesde"), cb.currentDate()),
                        cb.greaterThanOrEqualTo(onde.get("vigenteAte"), cb.currentDate()),
                        cb.isNull(onde.get("destruidoEm"))
                )
        );
        where.getExpressions().addAll(expressions);
        return where;
    }
 
    protected <X> Predicate vigenteParcialmentePredicado(
            CriteriaQuery cq, Root<X> onde,
            final LocalDate inicio, final LocalDate fim) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        Predicate where = cq.getRestriction();
        List<Expression<Boolean>> expressions = new ArrayList<>();
        expressions.add(
                cb.and(
                        cb.lessThanOrEqualTo(onde.get("vigenteDesde"), fim),
                        cb.greaterThanOrEqualTo(onde.get("vigenteAte"), inicio),
                        cb.isNull(onde.get("destruidoEm"))
                )
        );
        where.getExpressions().addAll(expressions);
        return where;
    }    

    protected <Z, X> Predicate relacionadoVigenteHojePredicado(CriteriaQuery cq, Join<Z, X> path) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        Predicate where = cq.getRestriction();
        List<Expression<Boolean>> expressions = new ArrayList<>();
        expressions.add(
                cb.and(
                        cb.lessThanOrEqualTo(path.get("vigenteDesde"), cb.currentDate()),
                        cb.greaterThanOrEqualTo(path.get("vigenteAte"), cb.currentDate()),
                        cb.isNull(path.get("destruidoEm"))
                )
        );
        where.getExpressions().addAll(expressions);
        return where;
    }
   
    protected <Z, X> Predicate relacionadoVigenteParcialmentePredicado(
            CriteriaQuery cq, Join<Z, X> path,
            final LocalDate inicio, final LocalDate fim) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        Predicate where = cq.getRestriction();
        List<Expression<Boolean>> expressions = new ArrayList<>();
        expressions.add(
                cb.and(
                        cb.lessThanOrEqualTo(path.get("vigenteDesde"), fim),
                        cb.greaterThanOrEqualTo(path.get("vigenteAte"), inicio),
                        cb.isNull(path.get("destruidoEm"))
                )
        );
        where.getExpressions().addAll(expressions);
        return where;
    }

    public List<T> vigentePlenamenteEntre(LocalDate inicio, LocalDate fim) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(entityClass);
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        vigentePlenamentePredicado(cq, c, inicio, fim);
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }
   
    protected <T> Predicate vigentePlenamentePredicado(
            CriteriaQuery cq, Root<T> root,
            final LocalDate inicio, final LocalDate fim) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        Predicate where = cq.getRestriction();
        List<Expression<Boolean>> expressions = new ArrayList<>();
        expressions.add(
                cb.and(
                        cb.lessThanOrEqualTo(root.get("vigenteDesde"), inicio),
                        cb.greaterThanOrEqualTo(root.get("vigenteAte"), fim),
                        cb.isNull(root.get("destruidoEm"))
                )
        );
        where.getExpressions().addAll(expressions);
        return where;
    }    
    
        //retorna entidades cujas vigencias coincidam, ainda que parcialmente
    //no intervalo especificado
    public List<T> vigenteEstritamenteEntre(LocalDate inicio, LocalDate fim) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        vigenteEstritamentePredicado(cq, c, inicio, fim);
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }
    
    
    //adiciona um predicado a query cq para buscar apenas os objetos
    //indicados em root cujas vigências estejam contidadas no período
    protected <T> Predicate vigenteEstritamentePredicado(
            CriteriaQuery cq, Root<T> root,
            final LocalDate inicio, final LocalDate fim) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        Predicate where = cq.getRestriction();
        List<Expression<Boolean>> expressions = new ArrayList<>();
        expressions.add(
                cb.and(
                        cb.greaterThanOrEqualTo(root.get("vigenteDesde"), inicio),
                        cb.lessThanOrEqualTo(root.get("vigenteAte"), fim),
                        cb.isNull(root.get("destruidoEm"))
                )
        );
        where.getExpressions().addAll(expressions);
        return where;
    }        
}
