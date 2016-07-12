//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Encargo extends EntidadeGenerica{  
    private String nome;
    
    private Boolean isRetencaoContaVinculada;
    
    @OneToMany(mappedBy = "encargo")
    private List<EncargoAliquota> aliquota;    

}
