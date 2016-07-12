/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author j129-9
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class FaturamentoItem extends EntidadeGenerica {

    @ManyToOne
    private Faturamento faturamento;
    
    @ManyToOne
    private PostoDeTrabalho postoDeTrabalho;
    
    private LocalDate dia;
    
    @ManyToOne
    private GlosaEspecie glosa;
    
    @ManyToOne
    private FaturamentoItemEvento evento;
    
    @ManyToOne
    private Colaborador substituto;
    
}
