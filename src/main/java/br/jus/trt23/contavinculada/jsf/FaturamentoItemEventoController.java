package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.FaturamentoItemEvento;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class FaturamentoItemEventoController extends AbstractController<FaturamentoItemEvento> {

    public FaturamentoItemEventoController() {
        super(FaturamentoItemEvento.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "FaturamentoItemEvento";
    }
}
