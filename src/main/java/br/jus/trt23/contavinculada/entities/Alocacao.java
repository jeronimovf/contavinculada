//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(
    uniqueConstraints = 
            @UniqueConstraint(columnNames={"vigenteDesde", "vigenteAte", "colaborador_id", "postoDeTrabalho_id"})            
)
public class Alocacao extends EntidadeGenerica {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(nullable = false)    
    private PostoDeTrabalho postoDeTrabalho;
    
    private Double salario;
}
