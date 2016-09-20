package br.jus.trt23.contavinculada.flows;

import br.jus.trt23.contavinculada.entities.Retencao;
import br.jus.trt23.contavinculada.jsf.RetencaoController;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import lombok.RequiredArgsConstructor;

@Named("retencaoFlow")
@RequiredArgsConstructor
@FlowScoped("retencaoflow")
public class RetencaoFlow extends AbstractFlow<Retencao>{
    @Override
    public RetencaoController getController(){
        if(controller instanceof RetencaoController){
            return (RetencaoController) controller;
        }
        return null;
    }
    
    
}
