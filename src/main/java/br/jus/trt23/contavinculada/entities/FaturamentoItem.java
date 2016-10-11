/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.entities;

import br.jus.trt23.contavinculada.constants.Constantes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author j129-9
 */
@Entity
@Table(schema = Constantes.SCHEMA)
@Getter
@Setter
public class FaturamentoItem extends EntidadeGenerica {

    protected final static String[] uniqueIndex = {"faturamento","postoDeTrabalho","dia"};    
    @NotNull
    @ManyToOne
    private Faturamento faturamento;
    
    @NotNull
    @ManyToOne
    private PostoDeTrabalho postoDeTrabalho;
    
    @NotNull
    private LocalDate dia;
    
    @ManyToOne
    private GlosaEspecie glosa;
    
    @ManyToOne
    private FaturamentoItemEvento evento;
    
    @ManyToOne
    private Colaborador substituto;
    
    @ManyToOne
    private Colaborador titular;    
    
    @ManyToMany(mappedBy = "faturamentoItens")
    private List<Retencao> retencoes;

    public FaturamentoItem() {
        this.retencoes = new ArrayList<>();
    }
    
    

    @Override
    public String getNomeNatural() {
        return "Item de faturamento";
    }
    
}
