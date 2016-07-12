//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Liberacao extends EntidadeGenerica {
    @Lob
    private Byte[] anexos;

    @OneToOne
    private Retencao retencao;

    private String liberadoPor;

    private String parecerLiberacao;

}
