package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.RAT;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named("ratController")
@Dependent
public class RATController extends AbstractController<RAT> {
    public RATController() {
        super(RAT.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "RAT";
    }
}
