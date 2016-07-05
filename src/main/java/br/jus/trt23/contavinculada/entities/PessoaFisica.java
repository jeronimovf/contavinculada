//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class PessoaFisica extends Pessoa {
    private String cpf;

    private String nome;

    private LocalDate nascimentoData;

    private String rgNumero;
    
    private String rgOgaoExpedidor;

    private String rgUF;

    private String pisPasep;

    private String ctps;
    
    @OneToMany(mappedBy = "colaborador")
    private List<Colaborador> colaboradorEm;

    @Override
    public String getDescricao() {
        return getNome().concat(" (").concat(getCpf()).concat(")");
    }
    
    

}
