//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Telefone extends EntidadeGenerica {

    @NotEmpty
    private String tipo;

    @NotEmpty
    @Pattern(regexp = "(\\(\\d{2,3}\\)")
    private String ddd;
    
    @NotEmpty    
    @Pattern(regexp = "\\d{2,3})([-\\s]?\\d{4}){2}")
    private String numero;
}
