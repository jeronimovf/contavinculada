/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.session;

import br.jus.trt23.contavinculada.entities.Alocacao;
import br.jus.trt23.contavinculada.entities.Colaborador;
import br.jus.trt23.contavinculada.entities.PessoaFisica;
import br.jus.trt23.contavinculada.entities.PostoDeTrabalho;
import java.util.List;
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
public class AlocacaoFacade extends AbstractFacade<Alocacao> {

    @Inject
    private EntityManager em;
    


    public AlocacaoFacade() {
        super(Alocacao.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Alocacao> complete(String criteria) {
        //o complete para alocação procura uma correspondência pelo nome
        //do titular e do substituto do posto de trabalho
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Alocacao> c = cq.from(Alocacao.class);
        Join<Alocacao,Colaborador> p = c.join("titular");
        Join<Alocacao,Colaborador> q = c.join("substituto");        
        Join<Colaborador,PessoaFisica> n = p.join("colaborador");
        Join<Colaborador,PessoaFisica> r = p.join("colaborador");        
        cq.select(c).where(
                cb.or(
                    cb.like(cb.upper(n.get("nome")),"%".concat(criteria.toUpperCase()).concat("%")),
                    cb.like(cb.upper(r.get("nome")),"%".concat(criteria.toUpperCase()).concat("%"))
                )
        );
        return getEntityManager().createQuery(cq).getResultList();         
    }

    public Alocacao findVigenteParaOPostoDeTrabalho(PostoDeTrabalho posto) throws Exception{
        List<Alocacao> listAlocacao;
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Alocacao> c = cq.from(Alocacao.class);
        cq.select(c).where(
                cb.equal(c.get("postoDeTrabalho"), posto),
                cb.isNull(c.get("vigenteAte"))
        );

        listAlocacao = getEntityManager().createQuery(cq).getResultList();    

        switch (listAlocacao.size()){
            case 0:
                return null;
            case 1:
                return listAlocacao.get(0);
            default:
                throw new Exception("Situação inconsistente.  Posto de trabalho possui mais de uma alocação ativa");                
        }
    }
    
    
    
}
