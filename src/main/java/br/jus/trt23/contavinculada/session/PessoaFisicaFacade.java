/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.session;

import br.jus.trt23.contavinculada.entities.PessoaFisica;
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
public class PessoaFisicaFacade extends AbstractFacade<PessoaFisica> {

    @Inject
    private EntityManager em;
    


    public PessoaFisicaFacade() {
        super(PessoaFisica.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    
    
}
