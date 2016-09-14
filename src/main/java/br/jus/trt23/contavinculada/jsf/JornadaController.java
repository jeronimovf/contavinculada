package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Jornada;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class JornadaController extends AbstractController<Jornada> {

    public JornadaController() {
        super(Jornada.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "Jornada";
    }
}
