package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.FaturamentoItemEvento;

import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named
@ViewScoped
public class FaturamentoItemEventoController extends AbstractController<FaturamentoItemEvento> {

    public FaturamentoItemEventoController() {
        super();
    }

    @Override
    protected String getMessagePrefix() {
        return "FaturamentoItemEvento";
    }
}
