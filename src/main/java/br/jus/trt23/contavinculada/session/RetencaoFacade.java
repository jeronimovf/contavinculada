/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.session;

import br.jus.trt23.contavinculada.entities.Faturamento;
import br.jus.trt23.contavinculada.entities.Retencao;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author j129-9
 */
@Stateless
public class RetencaoFacade extends AbstractFacade<Retencao> {

    @Inject
    private EntityManager em;
    


    public RetencaoFacade() {
        super(Retencao.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Retencao> complete(String criteria) {
        return null;       
    }

    public List<Retencao> findRetencaoPorFaturamento(Faturamento faturamento) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Retencao> c = cq.from(Retencao.class);
        cq.select(c).where(cb.equal(c.get("faturamento"),faturamento)).
                distinct(true).orderBy(cb.asc(c.get("colaborador")),cb.asc(c.get("ratItem")));
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();        
    }

    
    
    
}
