package br.jus.trt23.contavinculada.flows;

import br.jus.trt23.contavinculada.entities.RAT;
import br.jus.trt23.contavinculada.jsf.RATController;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import lombok.RequiredArgsConstructor;

@Named("ratFlow")
@RequiredArgsConstructor
@FlowScoped("ratflow")
public class RATFlow extends AbstractFlow<RAT>{
    @Override
    public RATController getController(){
        if(controller instanceof RATController){
            return (RATController) controller;
        }
        return null;
    }
    
    
}
