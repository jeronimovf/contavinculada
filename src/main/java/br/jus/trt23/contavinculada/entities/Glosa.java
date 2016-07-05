//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Glosa extends EntidadeGenerica{

    private Integer qtd;

    @ManyToOne(targetEntity = GlosaEspecie.class)
    private GlosaEspecie especie;

    private String glosadoPor;

    @ManyToOne(targetEntity = Faturamento.class)
    private Faturamento faturamento;

    private String descricao;

    private Double valorUnitario;
}
