package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Pessoa;

import javax.inject.Named;
import javax.faces.bean.ViewScoped;

@Named
@ViewScoped
public class PessoaController extends AbstractController<Pessoa> {

    public PessoaController() {
        super();
    }

    @Override
    protected String getMessagePrefix() {
        return "Pessoa";
    }
}
