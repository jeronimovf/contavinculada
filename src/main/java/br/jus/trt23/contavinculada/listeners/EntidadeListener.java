package br.jus.trt23.contavinculada.listeners;


import br.jus.trt23.contavinculada.entities.EntidadeGenerica;
import br.jus.trt23.contavinculada.sessions.ImplementacaoBasicaFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author j129-9
 */
public class EntidadeListener {

    ImplementacaoBasicaFacade implementacaoBasicaFacade = lookupImplementacaoBasicaFacadeBean();
   
    @PrePersist
    @PreUpdate
    public void setMandatoryFields(final EntidadeGenerica entity) {
        entity.setCriadoEm(implementacaoBasicaFacade.getTimestampOnServer());
    }    

    private ImplementacaoBasicaFacade lookupImplementacaoBasicaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ImplementacaoBasicaFacade) c.lookup("java:global/ContaVinculadaV2/ImplementacaoBasicaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
