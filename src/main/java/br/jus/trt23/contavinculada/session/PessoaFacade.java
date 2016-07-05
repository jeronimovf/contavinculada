/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.session;

import br.jus.trt23.contavinculada.entities.Pessoa;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 *
 * @author j129-9
 */
@Named
@Dependent
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

    
    
    
}
