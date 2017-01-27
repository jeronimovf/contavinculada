package br.jus.trt23.contavinculada.flows;

import br.jus.trt23.contavinculada.entities.CargoOuFuncao;
import br.jus.trt23.contavinculada.controllers.CargoOuFuncaoController;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
@FlowScoped("cargooufuncao")
public class CargoOuFuncaoFlow extends AbstractFlow<CargoOuFuncao>{
    @Override
    public CargoOuFuncaoController getController(){
        if(controller instanceof CargoOuFuncaoController){
            return (CargoOuFuncaoController) controller;
        }
        return null;
    }
    
    
}
