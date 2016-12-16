package br.jus.trt23.contavinculada.flows;

import br.jus.trt23.contavinculada.entities.FaturamentoItemEvento;
import br.jus.trt23.contavinculada.jsf.FaturamentoItemEventoController;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
@FlowScoped("faturamentoitemevento")
public class FaturamentoItemEventoFlow extends AbstractFlow<FaturamentoItemEvento> {

    @Override
    public FaturamentoItemEventoController getController() {
        if (controller instanceof FaturamentoItemEventoController) {
            return (FaturamentoItemEventoController) controller;
        }
        return null;
    }

}
