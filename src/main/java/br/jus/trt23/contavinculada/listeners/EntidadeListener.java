/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.listeners;

import br.jus.trt23.contavinculada.entities.EntidadeGenerica;
import java.time.LocalDateTime;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author j129-9
 */
@Getter
@Setter
@RequiredArgsConstructor
public class EntidadeListener {
    @Inject
    EntityManager em;

    @PrePersist
    @PreUpdate
    public void setMandatoryFields(EntidadeGenerica entity){
        entity.setCriadoEm(getTimestampOnServer());
    }     
    

    public LocalDateTime getTimestampOnServer() {
//        Query qry = em.createNativeQuery("SELECT CURRENT_DATE AS now FROM DUAL", "currentTimestamp");
//        Timestamp ts = (Timestamp) qry.getSingleResult();
//        return ts.toLocalDateTime();
          return LocalDateTime.now();
    }    
}
