/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author j129-9
 */
@MappedSuperclass
@Getter
@Setter
@RequiredArgsConstructor
public abstract class EntidadeGenerica implements Serializable{   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
        
    @NotNull
    private LocalDate vigenteDesde;

    private LocalDate vigenteAte;  
    
    @NotNull
    private LocalDateTime criadoEm;

    private LocalDateTime destruidoEm;      
}
