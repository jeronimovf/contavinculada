package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Servidor;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ServidorController extends AbstractController<Servidor> {

    public ServidorController() {
        super();
    }

    @Override
    protected String getMessagePrefix() {
        return "Servidor";
    }
}
