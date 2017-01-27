package br.jus.trt23.contavinculada.controllers;

import br.jus.trt23.contavinculada.entities.Pessoa;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class PessoaController extends AbstractController<Pessoa> {

    public PessoaController() {
        super(Pessoa.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "Pessoa";
    }
}
