/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.crud.session;

import br.jus.trt23.contavinculada.entities.Encargo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author j129-9
 */
@Stateless(name="encargoFacade_")
public class EncargoFacade extends AbstractFacade<Encargo> {

    @PersistenceContext(unitName = "contaVinculadaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncargoFacade() {
        super(Encargo.class);
    }
    
}
