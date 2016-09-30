package br.jus.trt23.contavinculada.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Colaborador extends EntidadeGenerica {
    protected final static String[] uniqueIndex = {"empregador", "colaborador"};

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(nullable=false)    
    private PessoaJuridica empregador;
    
    @Getter
    @Setter
    @NotNull
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(nullable=false)        
    private PessoaFisica colaborador;
    
    @Getter
    @Setter
    private Boolean isDesligamentoPorJustaCausa;

    @Getter
    @Setter
    private LocalDate recebeuManualEm;

    @Getter
    @Setter
    private String responsavelTecnico;

    @Getter
    @OneToMany(mappedBy = "titular")
    private List<Alocacao> titularEm;
    
    @Getter
    @OneToMany(mappedBy = "substituto")
    private List<Alocacao> substitutoEm;

    @Getter
    @OneToMany(mappedBy = "substituto")
    private List<FaturamentoItem> substituicoes;   

    @Getter
    @OneToMany(mappedBy = "titular")
    private List<FaturamentoItem> titularidades;     

    @Getter
    @OneToMany(mappedBy = "colaborador")
    private List<Retencao> retencoes; 
    
    @Getter
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<Salario> salarios;

    public Colaborador() {
        this.substitutoEm = new ArrayList<>();
        this.titularEm = new ArrayList<>();
        this.titularidades = new ArrayList<>();
        this. substituicoes = new ArrayList<>();
        this.retencoes = new ArrayList<>();
        this.salarios = new ArrayList<>();
    }

    
    public void AddTitularEm(Alocacao alocacao){
            titularEm.add(alocacao);
            alocacao.setTitular(this);
    }

    public void AddSubstitutoEm(Alocacao alocacao){
            substitutoEm.add(alocacao);
            alocacao.setSubstituto(this);
    }    

    public void AddSubstituicoes(FaturamentoItem faturamentoIt){
            substituicoes.add(faturamentoIt);
            faturamentoIt.setSubstituto(this);
    }    

    public void AddTitularidades(FaturamentoItem faturamentoIt){
            titularidades.add(faturamentoIt);
            faturamentoIt.setTitular(this);
    }       

    public void AddRetencoes(Retencao retencao){
            retencoes.add(retencao);
            retencao.setColaborador(this);
    }    
    
    public void AddSalarios(Salario salario){
        salarios.add(salario);
    }
    
    @Override
    public String toString(){
        return getColaborador().toString();
    }

    @Override
    public String getNomeNatural() {
        return "Colaborador";
    }
    
}
