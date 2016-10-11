/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.sessions;

import br.jus.trt23.contavinculada.entities.RAT;
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
public class RATFacade extends AbstractFacade<RAT> {

    @Inject
    private EntityManager em;
    


    public RATFacade() {
        super(RAT.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<RAT> complete(String criteria) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<RAT> c = cq.from(RAT.class);
        cq.select(c).where(cb.like(cb.upper(c.get("percentual")),"%".concat(criteria.toUpperCase()).concat("%")));
        return getEntityManager().createQuery(cq).getResultList();        
    }

    
    
    
}
