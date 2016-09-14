package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.CargoOuFuncao;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class CargoOuFuncaoController extends AbstractController<CargoOuFuncao> {
    public CargoOuFuncaoController() {
        super(CargoOuFuncao.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "CargoOuFuncao";
    }
}
