package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.PessoaFisica;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class PessoaFisicaController extends AbstractController<PessoaFisica> {

    public PessoaFisicaController() {
        super(PessoaFisica.class);        
    }

    @Override
    protected String getMessagePrefix() {
        return "PessoaFisica";
    }
}
