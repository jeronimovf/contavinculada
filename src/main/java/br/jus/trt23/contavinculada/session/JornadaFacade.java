/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.session;

import br.jus.trt23.contavinculada.entities.Jornada;
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
public class JornadaFacade extends AbstractFacade<Jornada> {

    @Inject
    private EntityManager em;
    


    public JornadaFacade() {
        super(Jornada.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Jornada> complete(String criteria) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Jornada> c = cq.from(Jornada.class);
        cq.select(c).where(cb.like(cb.upper(c.get("nome")),"%".concat(criteria.toUpperCase()).concat("%")));
        return getEntityManager().createQuery(cq).getResultList();        
    }

    
    
    
}
