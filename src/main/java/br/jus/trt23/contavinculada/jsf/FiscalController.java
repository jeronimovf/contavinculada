package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Fiscal;
import br.jus.trt23.contavinculada.sessions.FiscalFacade;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class FiscalController extends AbstractController<Fiscal> {
    public FiscalController() {
        super(Fiscal.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "Fiscal";
    }

    public Fiscal findByServidorMatricula(String login) {
        FiscalFacade fiscalFacade = (FiscalFacade)getFacade();
        return fiscalFacade.findByServidorMatricula(login);
    }
}
