/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.session;

import br.jus.trt23.contavinculada.entities.Contrato;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author j129-9
 */
@Named
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

}
