package br.jus.trt23.contavinculada.entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

    @OneToMany(mappedBy = "titular")
    private Set<Alocacao> titularEm;
    
    @OneToMany(mappedBy = "substituto")
    private Set<Alocacao> substitutoEm;

    @OneToMany(mappedBy = "substituto")
    private Set<FaturamentoItem> substituicoes;   

    @OneToMany(mappedBy = "titular")
    private Set<FaturamentoItem> titularidades;     

    @OneToMany(mappedBy = "colaborador")
    private Set<Retencao> retencoes; 
    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<Salario> salarios;

    public Colaborador() {
        this.substitutoEm = new TreeSet<>();
        this.titularEm = new TreeSet<>();
        this.titularidades = new TreeSet<>();
        this. substituicoes = new TreeSet<>();
        this.retencoes = new TreeSet<>();
        this.salarios = new TreeSet<>();
    }

    
    public Set<Alocacao> getTitularEm() {
        return new TreeSet<>(titularEm);
    }
    
    public void AddTitularEm(Alocacao alocacao){
            titularEm.add(alocacao);
            alocacao.setTitular(this);
    }

    public Set<Alocacao> getSubstitutoEm() {
        return new TreeSet<>(substitutoEm);       
    }
    
    public void AddSubstitutoEm(Alocacao alocacao){
            substitutoEm.add(alocacao);
            alocacao.setSubstituto(this);
    }    

    public Set<FaturamentoItem> getSubstituicoes() {
        return new TreeSet<>(substituicoes);               
    }

    public void AddSubstituicoes(FaturamentoItem faturamentoIt){
            substituicoes.add(faturamentoIt);
            faturamentoIt.setSubstituto(this);
    }    
    
    public Set<FaturamentoItem> getTitularidades() {
        return new TreeSet<>(titularidades);               
    }

    public void AddTitularidades(FaturamentoItem faturamentoIt){
            titularidades.add(faturamentoIt);
            faturamentoIt.setTitular(this);
    }       
    
    public Set<Retencao> getRetencoes() {
        return new TreeSet<>(retencoes);          
    }

    public void AddRetencoes(Retencao retencao){
            retencoes.add(retencao);
            retencao.setColaborador(this);
    }    
    
    public Set<Salario> getSalarios() {
        return new TreeSet<>(salarios);          
    }    
    
    public void AddSalarios(Salario salario){
        salarios.add(salario);
    }
    
    @Override
    public String toString(){
        return getColaborador().toString();
    }
    
}
