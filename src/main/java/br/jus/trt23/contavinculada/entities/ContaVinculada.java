//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(
    uniqueConstraints = 
            @UniqueConstraint(columnNames={"banco", "agencia", "conta", "operacao"})            
)
public class ContaVinculada extends EntidadeGenerica {
    protected final static String[] uniqueIndex = {"contrato", "banco", "agencia", "conta","operacao"};
    
    @NotNull
    @ManyToOne
    private Contrato contrato;

    @NotEmpty
    @Column(nullable = false)
    private String banco;

    @NotEmpty
    @Column(nullable = false)
    private String agencia;

    @NotEmpty
    @Column(nullable = false)
    private String conta;

    private String operacao;    
}
