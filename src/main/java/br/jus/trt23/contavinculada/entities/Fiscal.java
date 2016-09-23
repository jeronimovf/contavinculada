//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import br.jus.trt23.contavinculada.constraints.VigenciaEstritaAoContrato;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@VigenciaEstritaAoContrato
public class Fiscal extends EntidadeGenerica implements IEscopoRestritoAoContrato{
    protected final static String[] uniqueIndex = {"especie","servidor"};        
    @Getter
    @Setter
    @NotNull
    @ManyToOne(targetEntity = FiscalEspecie.class)
    @JoinColumn(nullable=false)
    private FiscalEspecie especie;

        @Getter
    @Setter
    @NotEmpty
    private String atoDesignacao;

    @Getter
    @Setter
        @NotNull
    @ManyToOne
    @JoinColumn(nullable=false)
    private Servidor servidor;
    
    @Getter
    @Setter
    @NotNull
    @ManyToOne
    @JoinColumn(nullable=false)
    private Contrato contrato;
    
    @Getter
    @OneToMany(mappedBy = "atestadaPor")
    private List<Faturamento> faturamentosAtestados;    

    public Fiscal() {
        this.faturamentosAtestados = new ArrayList<>();
    }

    public void addFaturamentosAtestados(Faturamento faturamento){
        faturamentosAtestados.add(faturamento);
    }

    @Override
    public String getNomeNatural() {
        return "Fiscal";
    }
    
}
