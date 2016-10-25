//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import br.jus.trt23.contavinculada.constants.Constantes;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = Constantes.SCHEMA)
@Getter
@Setter
@RequiredArgsConstructor
@SequenceGenerator(name = "ID", sequenceName = "SALARIO_SEQ", allocationSize = 1, schema=Constantes.SCHEMA)
public class Salario extends EntidadeGenerica {
    protected final static String[] uniqueIndex = {"valor"};        
    private Double valor;
    
    @Override
    public String toString() {
        return getValor().toString();
    }

    @Override
    public String getNomeNatural() {
        return "Salário";
    }
  
}
