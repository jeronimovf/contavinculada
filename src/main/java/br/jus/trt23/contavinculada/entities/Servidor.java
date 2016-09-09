//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Entity

public class Servidor extends PessoaFisica {
    protected final static String[] uniqueIndex = {"matricula"};    
    
    @Getter
    @Setter
    @NotEmpty
    private String matricula;
    @OneToMany(mappedBy = "servidor")
    private Set<Fiscal> fiscais;

    public Servidor() {
        this.fiscais = new TreeSet<>();
    }

    public Set<Fiscal> getFiscais() {
        return new TreeSet<>(fiscais);
    }

    public void addFiscais(Fiscal fiscal) {
        fiscais.add(fiscal);
        fiscal.setServidor(this);
    }

}
