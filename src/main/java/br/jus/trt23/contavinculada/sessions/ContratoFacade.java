/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.sessions;

import br.jus.trt23.contavinculada.entities.Contrato;
import br.jus.trt23.contavinculada.entities.Fiscal;
import br.jus.trt23.contavinculada.entities.FiscalEspecie;
import br.jus.trt23.contavinculada.entities.Servidor;
import br.jus.trt23.contavinculada.handlers.LazyQueryHandler;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 *
 * @author j129-9
 */

@Stateless
public class ContratoFacade extends AbstractFacade<Contrato> {

    @Inject
    private EntityManager em;

    public ContratoFacade() {
        super(Contrato.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Contrato> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Contrato> c = cq.from(Contrato.class);
        cq.select(c).where(cb.isNull(c.get("aditivoDe")));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<Contrato> findRange(int[] range) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Contrato> c = cq.from(Contrato.class);
        cq.select(c).where(cb.isNull(c.get("aditivoDe")));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public List<Contrato> complete(String criteria) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Contrato> c = cq.from(Contrato.class);
        cq.select(c).where(cb.like(cb.upper(c.get("numero")), "%".concat(criteria.toUpperCase()).concat("%")));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<Contrato> findContratosPorServidorLogin(final String login, 
            LazyQueryHandler lqh) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Contrato> c = cq.from(Contrato.class);
        Join<Contrato,Fiscal> f = c.join("fiscais");
        Join<Fiscal,Servidor> s = f.join("servidor");
        cq.select(c).where(
                cb.equal(cb.upper(s.get("matricula")),login.toUpperCase())
        );
        relacionadoVigenteHojePredicado(cq, f);        
        return findRange(cq, lqh);
    }

    public List<Contrato> findContratosPorServidorLogin(String login, 
            FiscalEspecie fiscalEspecie, LazyQueryHandler lqh) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Contrato> cq = cb.createQuery(Contrato.class);
        Root<Contrato> c = cq.from(Contrato.class);
        Join<Contrato,Fiscal> f = c.join("fiscais");
        Join<Fiscal,Servidor> s = f.join("servidor");
        cq.select(c).where(
                cb.equal(cb.upper(s.get("matricula")),login.toUpperCase()),
                cb.equal(f.get("especie"), fiscalEspecie)
        ).distinct(true);
        relacionadoVigenteHojePredicado(cq, f);
        return findRange(cq, lqh);
    }

    public int countContratosPorServidorLogin(String login, 
            FiscalEspecie fiscalEspecie, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Contrato> c = cq.from(Contrato.class);
        Join<Contrato,Fiscal> f = c.join("fiscais");
        Join<Fiscal,Servidor> s = f.join("servidor");
        cq.select(cb.count(c)).where(
                cb.equal(cb.upper(s.get("matricula")),login.toUpperCase()),
                cb.equal(f.get("especie"), fiscalEspecie)
        ).distinct(true);
        relacionadoVigenteHojePredicado(cq, f);
        cq.getRestriction().getExpressions().addAll(getPredicates(c, filters));        
        
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();         
    }
}
