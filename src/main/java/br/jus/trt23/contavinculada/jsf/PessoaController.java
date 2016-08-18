package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Pessoa;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PessoaController extends AbstractController<Pessoa> {

    public PessoaController() {
        super(Pessoa.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "Pessoa";
    }
}
