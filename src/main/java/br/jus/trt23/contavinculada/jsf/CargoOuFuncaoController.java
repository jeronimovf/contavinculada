package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.CargoOuFuncao;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

@Named
@FlowScoped("cargooufuncaoflow")
public class CargoOuFuncaoController extends AbstractController<CargoOuFuncao> {
    public CargoOuFuncaoController() {
        super(CargoOuFuncao.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "CargoOuFuncao";
    }
}
