//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Salario extends EntidadeGenerica{  
    private Double valor;
    
    @Override
    public String toString() {
        return getValor().toString();
    }

    
}
