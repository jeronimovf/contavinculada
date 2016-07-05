/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.session;

import br.jus.trt23.contavinculada.entities.PessoaJuridica;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

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

    
    
    
}
