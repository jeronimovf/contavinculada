package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Colaborador;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ColaboradorController extends AbstractController<Colaborador> {

    public ColaboradorController() {
        super();
    }

    @Override
    protected String getMessagePrefix() {
        return "Colaborador";
    }


}
