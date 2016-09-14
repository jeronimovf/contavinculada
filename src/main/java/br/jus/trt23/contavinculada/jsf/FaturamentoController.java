package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Faturamento;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class FaturamentoController extends AbstractController<Faturamento> {

    public FaturamentoController() {
        super(Faturamento.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "Faturamento";
    }
}
