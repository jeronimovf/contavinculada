//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity

@Getter
@Setter
@RequiredArgsConstructor
public class FaturamentoItemEvento extends EntidadeGenerica {
    private String nome;
    private Boolean isSubstituicao;
}
