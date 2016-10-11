//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import br.jus.trt23.contavinculada.constants.Constantes;
import br.jus.trt23.contavinculada.constraints.VigenciaEstritaAoContrato;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = Constantes.SCHEMA)
@VigenciaEstritaAoContrato
public class Faturamento extends EntidadeGenerica implements IEscopoRestritoAoContrato{
    protected final static String[] uniqueIndex = {"contrato","referenciaInicio", "referenciaFim"};    
    @Getter
    @Setter
    @NotNull(message = "Início da referência não pode ser nulo")
    private LocalDate referenciaInicio;

    @Getter
    @Setter
    @NotNull(message = "Fim da referência não pode ser nulo")
    private LocalDate referenciaFim;
    
    @Transient
    private Integer diasEntreReferencias;

    @Getter
    @Setter
    @NotNull(message="Contrato não pode ser nulo")
    @ManyToOne
    private Contrato contrato;
    
    @Getter
    @Setter
    private String notas;

    @Getter
    @Setter
    @Transient
    private String situacao;

    @Getter
    @Setter
    private String nfeOuFatura;

    @Getter
    @Setter
    private LocalDate atestadaEm;

    @Getter
    @Setter
    @ManyToOne
    private Fiscal atestadaPor;

    @Getter
    @Setter
    @Transient
    private Double valorLiquido;

    @Getter
    @Setter
    @Transient
    private Double valorBruto;

    @Getter
    @Setter
    @OneToMany(mappedBy = "faturamento", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<FaturamentoItem> itens;


    @Getter
    @Setter
    @OneToMany(targetEntity = Glosa.class, mappedBy = "faturamento")
    private List<Glosa> glosas;

    public Faturamento() {
        this.itens = new ArrayList<>();
        this.glosas = new ArrayList<>();
    }

    public void addItens(FaturamentoItem item){
        itens.add(item);
        item.setFaturamento(this);
    }
    
    public void addGlosas(Glosa glosa){
        glosas.add(glosa);
        glosa.setFaturamento(this);
    }


    public Integer getDiasEntreReferencias() {
        if(null == diasEntreReferencias){
            diasEntreReferencias = Period.between(referenciaInicio, referenciaFim).getDays()+1;
        }
        return diasEntreReferencias;
    }

    @Override
    public String getNomeNatural() {
        return "Faturamento";
    }
}
