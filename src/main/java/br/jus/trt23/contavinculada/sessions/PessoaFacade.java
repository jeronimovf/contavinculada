/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.sessions;

import br.jus.trt23.contavinculada.entities.Pessoa;
import br.jus.trt23.contavinculada.entities.PessoaFisica;
import br.jus.trt23.contavinculada.entities.PessoaJuridica;
import java.util.ArrayList;
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
public class PessoaFacade extends AbstractFacade<Pessoa> {

    @Inject
    private EntityManager em;
    


    public PessoaFacade() {
        super(Pessoa.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Pessoa> complete(String criteria) {
        List<Pessoa> pessoas = new ArrayList<>();
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        
        CriteriaQuery cqPF = cb.createQuery();
        Root<PessoaFisica> cPF = cqPF.from(PessoaFisica.class);
        cqPF.select(cPF).where(cb.like(cb.upper(cPF.get("nome")),"%".concat(criteria.toUpperCase()).concat("%")));
        pessoas.addAll(getEntityManager().createQuery(cqPF).getResultList());
        
        CriteriaQuery cqPJ = cb.createQuery();
        Root<PessoaJuridica> cPJ = cqPJ.from(PessoaJuridica.class);
        cqPJ.select(cPJ).where(cb.or(
                cb.like(cb.upper(cPJ.get("razaoSocial")),"%".concat(criteria.toUpperCase()).concat("%")),
                cb.like(cb.upper(cPJ.get("nomeFantasia")),"%".concat(criteria.toUpperCase()).concat("%"))                
        ));
        pessoas.addAll(getEntityManager().createQuery(cqPJ).getResultList());
        
        return pessoas;        

    }

    
    
    
}
