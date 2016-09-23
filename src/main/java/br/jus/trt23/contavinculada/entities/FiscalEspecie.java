//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        uniqueConstraints
        = @UniqueConstraint(columnNames = {"vigenteDesde", "vigenteAte", "nome"})
)
public class FiscalEspecie extends EntidadeGenerica {

    protected final static String[] uniqueIndex = {"nome"};
    @Getter
    @Setter
    @NotNull
    private String nome;

    @Getter
    @OneToMany(targetEntity = Fiscal.class, mappedBy = "especie")
    private List<Fiscal> fiscais;

    public FiscalEspecie() {
        this.fiscais = new ArrayList<>();
    }

    public void addFiscais(Fiscal fiscal) {
        fiscais.add(fiscal);
        fiscal.setEspecie(this);
    }

    @Override
    public String toString() {
        return getNome(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeNatural() {
        return "Espécie de fiscal";
    }

}
