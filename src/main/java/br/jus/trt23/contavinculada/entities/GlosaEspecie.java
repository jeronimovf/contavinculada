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

@Entity

public class GlosaEspecie extends EntidadeGenerica {

    protected final static String[] uniqueIndex = {"nome"};
    
    @Getter
    @Setter
    private String nome;

    @OneToMany(targetEntity = Glosa.class, mappedBy = "especie")
    private Set<Glosa> glosas;

    public GlosaEspecie() {
        this.glosas = new TreeSet<>();
    }

    public Set<Glosa> getGlosas() {
        return new TreeSet<>(glosas);
    }

    public void addGlosas(Glosa glosa) {
        glosas.add(glosa);
        glosa.setEspecie(this);
    }
}
