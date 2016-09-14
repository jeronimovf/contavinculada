package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.FiscalEspecie;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class FiscalEspecieController extends AbstractController<FiscalEspecie> {

    public FiscalEspecieController() {
        super(FiscalEspecie.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "FiscalEspecie";
    }


}
