/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.sessions;

import br.jus.trt23.contavinculada.entities.Servidor;
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
public class ServidorFacade extends AbstractFacade<Servidor> {

    @Inject
    private EntityManager em;
    


    public ServidorFacade() {
        super(Servidor.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Servidor> complete(String criteria) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Servidor> c = cq.from(Servidor.class);
        cq.select(c).where(
                cb.or(
                        cb.like(cb.upper(c.get("nome")),"%".concat(criteria.toUpperCase()).concat("%")),
                        cb.like(c.get("cpf"),"%".concat(criteria).concat("%")),
                        cb.like(c.get("matricula"),"%".concat(criteria).concat("%"))
                )
        );
        return getEntityManager().createQuery(cq).getResultList();  
    }

    
    
    
}
