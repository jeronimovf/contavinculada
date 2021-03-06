//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Retencao extends EntidadeGenerica {

    @ManyToOne
    private Faturamento faturamento;
    
    @ManyToOne
    private Colaborador colaborador;
    
    @ManyToOne
    private EncargoAliquota aliquota;
    
    private Double valor;
    

    @OneToOne(mappedBy = "retencao")
    private Liberacao liberacao;    
}
