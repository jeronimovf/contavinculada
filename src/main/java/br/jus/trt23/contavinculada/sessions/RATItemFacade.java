/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.sessions;

import br.jus.trt23.contavinculada.entities.RATItem;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author j129-9
 */
@Stateless
public class RATItemFacade extends AbstractFacade<RATItem> {

    @Inject
    private EntityManager em;
    


    public RATItemFacade() {
        super(RATItem.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<RATItem> complete(String criteria) {
        return null;        
    }

    
    
    
}
