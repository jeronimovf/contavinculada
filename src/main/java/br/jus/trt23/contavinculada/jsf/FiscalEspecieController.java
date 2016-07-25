package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.FiscalEspecie;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class FiscalEspecieController extends AbstractController<FiscalEspecie> {

    public FiscalEspecieController() {
        super();
    }

    @Override
    protected String getMessagePrefix() {
        return "FiscalEspecie";
    }


}
