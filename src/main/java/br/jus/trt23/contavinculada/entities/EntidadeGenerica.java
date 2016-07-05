/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

/**
 *
 * @author j129-9
 */
@MappedSuperclass
@Data
public abstract class EntidadeGenerica implements Serializable{   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
        
    private LocalDateTime vigenteDesde;

    private LocalDateTime vigenteAte;  
    
    @Column(nullable = false)
    private LocalDateTime criadoEm;

    private LocalDateTime destruidoEm;    
}
