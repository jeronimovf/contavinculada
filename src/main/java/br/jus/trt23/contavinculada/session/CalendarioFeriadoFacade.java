/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.session;

import br.jus.trt23.contavinculada.entities.CalendarioFeriado;
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
public class CalendarioFeriadoFacade extends AbstractFacade<CalendarioFeriado> {

    @Inject
    private EntityManager em;
    


    public CalendarioFeriadoFacade() {
        super(CalendarioFeriado.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<CalendarioFeriado> complete(String criteria) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<CalendarioFeriado> c = cq.from(CalendarioFeriado.class);
        cq.select(c).where(cb.like(cb.upper(c.get("nome")),"%".concat(criteria.toUpperCase()).concat("%")));
        return getEntityManager().createQuery(cq).getResultList();        
    }

    
    
    
}
