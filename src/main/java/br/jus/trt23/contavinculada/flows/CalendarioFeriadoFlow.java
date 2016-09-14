package br.jus.trt23.contavinculada.flows;

import br.jus.trt23.contavinculada.entities.CalendarioFeriado;
import br.jus.trt23.contavinculada.jsf.CalendarioFeriadoController;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
@FlowScoped("calendarioferiadoflow")
public class CalendarioFeriadoFlow extends AbstractFlow<CalendarioFeriado>{
    @Override
    public CalendarioFeriadoController getController(){
        if(controller instanceof CalendarioFeriadoController){
            return (CalendarioFeriadoController) controller;
        }
        return null;
    }
    
    
}
