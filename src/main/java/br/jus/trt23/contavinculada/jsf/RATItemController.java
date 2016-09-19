package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.RATItem;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named("ratItemController")
@Dependent
public class RATItemController extends AbstractController<RATItem> {
    public RATItemController() {
        super(RATItem.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "RATItem";
    }
}
