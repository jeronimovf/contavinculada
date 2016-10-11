//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import br.jus.trt23.contavinculada.constants.Constantes;
import br.jus.trt23.contavinculada.constraints.VigenciaValida;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(schema = Constantes.SCHEMA,
        uniqueConstraints
        = @UniqueConstraint(columnNames = {"vigenteDesde", "vigenteAte", "numero"})
)
@VigenciaValida
public class Contrato extends EntidadeGenerica {

    protected final static String[] uniqueIndex = {"numero"};
    @Getter
    @Setter
    @NotEmpty
    private String numero;

    @Getter
    @Setter
    @ManyToOne
    private Contrato aditivoDe;

    @Getter
    @Setter
    @NotNull
    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(nullable = false)
    private Pessoa contratado;

    @Getter
    @Setter
    @NotEmpty
    private String objeto;

    @Getter
    @Setter
    @NotNull
    private LocalDate assinaturaData;

    @Getter
    @Setter
    @Lob
    private Byte[] inteiroTeor;

    @Getter
    @Setter
    @NotNull
    private String processo;

    @Getter
    @Setter
    private String arp;

    @Getter
    @Setter
    private String regimeTributacao;

    @Getter
    @OneToMany(mappedBy = "contrato", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<ContaVinculada> contasVinculadas;

    @Getter
    @OneToMany(mappedBy = "contrato", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<PostoDeTrabalho> postosDeTrabalho;

    @Getter
    @OneToMany(mappedBy = "contrato",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Faturamento> faturamentos;

    @Getter
    @OneToMany(mappedBy = "aditivoDe", cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Contrato> aditivos;

    @Getter
    @OneToMany(mappedBy = "contrato", cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Fiscal> fiscais;

    @Getter
    @OneToMany(mappedBy = "contrato", cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private List<EncargoAliquota> aliquotas;

    @Getter
    @Setter
    private String situacao;
    
            
    @Getter
    @Setter
    @ManyToOne
    private RAT rat;    

    public Contrato() {
        this.contasVinculadas = new ArrayList<>();
        this.postosDeTrabalho = new ArrayList<>();
        this.faturamentos = new ArrayList<>();
        this.aditivos = new ArrayList<>();
        this.fiscais = new ArrayList<>();
        this.aliquotas = new ArrayList<>();
    }

    public void AddContasVinculadas(ContaVinculada conta) {
        contasVinculadas.add(conta);
        conta.setContrato(this);
    }

    public void addPostosDeTrabalho(PostoDeTrabalho posto) {
        postosDeTrabalho.add(posto);
        posto.setContrato(this);
    }

    public void addFaturamentos(Faturamento faturamento) {
        faturamentos.add(faturamento);
        faturamento.setContrato(this);
    }

    public void addAditivos(Contrato aditivo) {
        aditivos.add(aditivo);
        aditivo.setAditivoDe(this);
    }

    public void addFiscais(Fiscal fiscal) {
        fiscais.add(fiscal);
        fiscal.setContrato(this);
    }

    public void addAliquotas(EncargoAliquota aliquota) {
        aliquotas.add(aliquota);
        aliquota.setContrato(this);
    }

    @Override
    public String toString() {
        if (null != getAditivoDe()) {
            return getNumero() + "/" + getAditivoDe();
        } else {
            return getNumero();
        }
    }

    @Override
    public String getNomeNatural() {
        return "Contrato";
    }
}
