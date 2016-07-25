package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Encargo;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class EncargoController extends AbstractController<Encargo> {

    public EncargoController() {
        super();
    }

    @Override
    protected String getMessagePrefix() {
        return "Encargo";
    }
}
