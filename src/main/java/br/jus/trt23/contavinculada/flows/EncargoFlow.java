package br.jus.trt23.contavinculada.flows;

import br.jus.trt23.contavinculada.entities.Encargo;
import br.jus.trt23.contavinculada.jsf.EncargoController;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
@FlowScoped("encargo")
public class EncargoFlow extends AbstractFlow<Encargo>{
    @Override
    public EncargoController getController(){
        if(controller instanceof EncargoController){
            return (EncargoController) controller;
        }
        return null;
    }
    
    
}
