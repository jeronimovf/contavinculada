//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(
    uniqueConstraints = 
            @UniqueConstraint(columnNames={"vigenteDesde", "vigenteAte", "nome"})            
)
public class CalendarioFeriado extends EntidadeGenerica {

    @OneToMany(mappedBy = "feriadoCalendario")
    private List<PostoDeTrabalho> postoDeTrabalhos;

    @Column(nullable = false)
    private String nome;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private List<CalendarioFeriadoItem> feriados;

    private String descricao;

    @Override
    public String toString() {
        return getNome();
    }
    
    

}
