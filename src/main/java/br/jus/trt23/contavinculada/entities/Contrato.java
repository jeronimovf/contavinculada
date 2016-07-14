//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
            @UniqueConstraint(columnNames={"vigenteDesde", "vigenteAte", "numero"})            
)
public class Contrato extends EntidadeGenerica {

    @Column(nullable=false)
    private String numero;

    @ManyToOne
    private Contrato aditivoDe;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(nullable=false)        
    private Pessoa contratado;

    @Column(nullable=false)
    private String objeto;

    private LocalDate assinaturaData;

    @Lob
    private Byte[] inteiroTeor;

    private String processo;

    private String arp;

    private String regimeTributacao;

    
    @OneToMany(mappedBy = "contrato", cascade={CascadeType.MERGE,CascadeType.PERSIST})
    private List<ContaVinculada> contasVinculadas;


    @OneToMany(mappedBy = "contrato" ,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<PostoDeTrabalho> postosDeTrabalho;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Faturamento> faturamentos;

    @OneToMany(mappedBy = "aditivoDe", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Contrato> aditivos;

    @OneToMany(mappedBy = "contrato", cascade = {CascadeType.PERSIST,CascadeType.MERGE}  )
    private List<Fiscal> fiscais;

    @ManyToMany(mappedBy = "contratos" , cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<EncargoAliquota> aliquotas;

    private String situacao;
}
