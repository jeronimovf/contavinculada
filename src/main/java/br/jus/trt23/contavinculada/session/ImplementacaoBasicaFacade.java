/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.session;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author j129-9
 */
@Stateless
public class ImplementacaoBasicaFacade{

    @Inject
    private EntityManager em;

    public ImplementacaoBasicaFacade() {
    }

    public LocalDateTime getTimestampOnServer(){
        Query qry = em.createNativeQuery("SELECT localtimestamp");
        Timestamp ts = (Timestamp) qry.getSingleResult();
        return ts.toLocalDateTime();
    }
}
