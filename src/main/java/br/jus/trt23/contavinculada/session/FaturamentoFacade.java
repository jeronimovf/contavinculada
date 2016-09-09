/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.session;

import br.jus.trt23.contavinculada.entities.Faturamento;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author j129-9
 */
@Stateless
public class FaturamentoFacade extends AbstractFacade<Faturamento> {

    @Inject
    private EntityManager em;
    


    public FaturamentoFacade() {
        super(Faturamento.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Faturamento> complete(String criteria) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Faturamento> c = cq.from(Faturamento.class);
        cq.select(c).where(cb.like(cb.upper(c.get("referencia")),"%".concat(criteria.toUpperCase()).concat("%")));
        return getEntityManager().createQuery(cq).getResultList();
    }

    
    
    
}
