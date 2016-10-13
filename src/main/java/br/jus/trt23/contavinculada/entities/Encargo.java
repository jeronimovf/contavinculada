//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import br.jus.trt23.contavinculada.constants.Constantes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = Constantes.SCHEMA)
@SequenceGenerator(name = "ID", sequenceName = "ENCARGO_SEQ", allocationSize = 1, schema=Constantes.SCHEMA)
public class Encargo extends EntidadeGenerica{  
    protected final static String[] uniqueIndex = {"nome"};    
    @Getter
    @Setter
    private String nome;
    
    @Getter
    @OneToMany(mappedBy = "encargo")
    private List<EncargoAliquota> aliquotas;    

    public Encargo() {
        this.aliquotas = new ArrayList<>();
    }
    
    public void addAliquotas(EncargoAliquota aliquota){
        aliquotas.add(aliquota);
        aliquota.setEncargo(this);
    }
    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public String getNomeNatural() {
        return "Encargo";
    }

    
}
