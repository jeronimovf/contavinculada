package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.PessoaFisica;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PessoaFisicaController extends AbstractController<PessoaFisica> {

    public PessoaFisicaController() {
        super();        
    }

    @Override
    protected String getMessagePrefix() {
        return "PessoaFisica";
    }
}
