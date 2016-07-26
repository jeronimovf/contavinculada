package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Faturamento;

import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named
@ViewScoped
public class FaturamentoController extends AbstractController<Faturamento> {

    public FaturamentoController() {
        super();
    }

    @Override
    protected String getMessagePrefix() {
        return "Faturamento";
    }
}
