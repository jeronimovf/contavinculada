/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.session;

import br.jus.trt23.contavinculada.entities.PessoaJuridica;
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
public class PessoaJuridicaFacade extends AbstractFacade<PessoaJuridica> {

    @Inject
    private EntityManager em;
    


    public PessoaJuridicaFacade() {
        super(PessoaJuridica.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<PessoaJuridica> complete(String criteria) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<PessoaJuridica> c = cq.from(PessoaJuridica.class);
        cq.select(c).where(
                cb.or(
                        cb.like(cb.upper(c.get("nomeFantasia")),"%".concat(criteria.toUpperCase()).concat("%")),
                        cb.like(c.get("cnpj"),"%".concat(criteria).concat("%")),
                        cb.like(c.get("razaoSocial"),"%".concat(criteria).concat("%"))
                )
        );
        return getEntityManager().createQuery(cq).getResultList();        
    }

    
    
    
}
