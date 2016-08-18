package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.FiscalEspecie;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class FiscalEspecieController extends AbstractController<FiscalEspecie> {

    public FiscalEspecieController() {
        super(FiscalEspecie.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "FiscalEspecie";
    }


}
