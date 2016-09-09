//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(
        uniqueConstraints
        = @UniqueConstraint(columnNames = {"vigenteDesde", "vigenteAte", "nome"})
)
public class CalendarioFeriado extends EntidadeGenerica {
    protected final static String[] uniqueIndex = {"nome"};
    @OneToMany(mappedBy = "feriadoCalendario")
    private Set<PostoDeTrabalho> postosDeTrabalho;

    @Getter
    @Setter
    @NotEmpty
    private String nome;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private Set<CalendarioFeriadoItem> feriados;

    @Getter
    @Setter
    @NotEmpty
    private String descricao;

    public CalendarioFeriado() {
        this.feriados = new TreeSet<>();
        this.postosDeTrabalho = new TreeSet<>();
    }

    public Collection<CalendarioFeriadoItem> getFeriados() {
        return new TreeSet<>(feriados);
    }

    public void addFeriados(CalendarioFeriadoItem item){
        feriados.add(item);
    }

    public Collection<PostoDeTrabalho> getPostosDeTrabalho() {
        return new TreeSet<>(postosDeTrabalho);
    }
    
    public void AddPostosDeTrabalho(PostoDeTrabalho posto) {
            postosDeTrabalho.add(posto);
            posto.setFeriadoCalendario(this);
    }    

    @Override
    public String toString() {
        return getNome();
    }

}
