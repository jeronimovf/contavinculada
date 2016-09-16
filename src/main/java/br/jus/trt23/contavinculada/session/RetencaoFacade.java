/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.session;

import br.jus.trt23.contavinculada.entities.Retencao;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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

    
    
    
}
