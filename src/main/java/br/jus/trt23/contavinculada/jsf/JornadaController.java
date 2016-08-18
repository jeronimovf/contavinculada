package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Jornada;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class JornadaController extends AbstractController<Jornada> {

    public JornadaController() {
        super(Jornada.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "Jornada";
    }
}
