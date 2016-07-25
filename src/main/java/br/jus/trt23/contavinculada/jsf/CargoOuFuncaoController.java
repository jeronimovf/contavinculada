package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.CargoOuFuncao;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CargoOuFuncaoController extends AbstractController<CargoOuFuncao> {
    public CargoOuFuncaoController() {
        super();
    }

    @Override
    protected String getMessagePrefix() {
        return "CargoOuFuncao";
    }
}
