package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.PessoaJuridica;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PessoaJuridicaController extends AbstractController<PessoaJuridica> {

    public PessoaJuridicaController() {
        super();
    }

    @Override
    protected String getMessagePrefix() {
        return "PessoaJuridica";
    }
}
