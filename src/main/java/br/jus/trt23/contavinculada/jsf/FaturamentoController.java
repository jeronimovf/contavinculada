package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Faturamento;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class FaturamentoController extends AbstractController<Faturamento> {

    public FaturamentoController() {
        super(Faturamento.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "Faturamento";
    }
}
