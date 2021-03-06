//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import br.jus.trt23.contavinculada.enums.EFeriadoEscopo;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(
    uniqueConstraints = 
            @UniqueConstraint(columnNames={"vigenteDesde", "vigenteAte", "nome"})            
)
public class CalendarioFeriadoItem extends EntidadeGenerica {

    @NotEmpty
    private String nome;

    @NotNull
    private LocalDate quando;

    @NotNull
    private EFeriadoEscopo escopo;
    
    //TODO: Aqui talvez fosse interessante uma anotação ScriptAssert
    private String onde;

    @Override
    public String toString() {
        return getNome();
    }
    
    

}
