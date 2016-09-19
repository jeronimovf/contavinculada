package br.jus.trt23.contavinculada.flows;

import br.jus.trt23.contavinculada.entities.FiscalEspecie;
import br.jus.trt23.contavinculada.jsf.FiscalEspecieController;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
@FlowScoped("fiscalespecieflow")
public class FiscalEspecieFlow extends AbstractFlow<FiscalEspecie>{
    @Override
    public FiscalEspecieController getController(){
        if(controller instanceof FiscalEspecieController){
            return (FiscalEspecieController) controller;
        }
        return null;
    }
    
    
}