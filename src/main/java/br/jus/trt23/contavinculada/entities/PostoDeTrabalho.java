//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import br.jus.trt23.contavinculada.enums.EDiasComputados;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class PostoDeTrabalho extends EntidadeGenerica {

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Contrato contrato;
    
    @NotNull
    @ManyToOne(targetEntity = CargoOuFuncao.class)
    private CargoOuFuncao cargoOuFuncao;
    
    @NotNull
    @ManyToOne
    private Jornada jornada;
    
    @OneToMany(mappedBy = "postoDeTrabalho")
    private List<Alocacao> alocacoes;
    
    @OneToMany(mappedBy = "postoDeTrabalho")
    private List<FaturamentoItem> faturamentoItems;  
    
    @Enumerated
    @ElementCollection
    private List<EDiasComputados> diasComputados;
    
    //TODO: incluir um script que permita liberar o preenchimento de feriadoCalendario
    //se o item não estiver em diasComputados
    @ManyToOne
    private CalendarioFeriado feriadoCalendario;
    
    public Alocacao getAlocacaoAtiva(){
        //TODO: Buscar a alocação ativa no momento
        return null;
    }
    
    public Alocacao getAlocacaoAtivaEntre(Date inicio, Date fim){
        //TODO: Buscar a alocação ativa em um período
        return null;
    }    
}
