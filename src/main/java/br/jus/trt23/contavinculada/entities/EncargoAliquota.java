//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class EncargoAliquota extends EntidadeGenerica {

    @ManyToOne
    private Encargo encargo;
   
    private Double aliquota;
    
    @ManyToMany
    private List<Contrato> contratos;
    
    @OneToMany(mappedBy = "aliquota")
    private List<Retencao> retencoes;    

}
