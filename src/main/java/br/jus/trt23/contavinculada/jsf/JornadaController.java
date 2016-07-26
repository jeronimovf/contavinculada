package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Jornada;

import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named
@ViewScoped
public class JornadaController extends AbstractController<Jornada> {

    public JornadaController() {
        super();
    }

    @Override
    protected String getMessagePrefix() {
        return "Jornada";
    }
}
