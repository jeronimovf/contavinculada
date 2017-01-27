package br.jus.trt23.contavinculada.controllers;

import br.jus.trt23.contavinculada.entities.Servidor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class ServidorController extends AbstractController<Servidor> {

    public ServidorController() {
        super(Servidor.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "Servidor";
    }
}
