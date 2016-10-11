package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.FaturamentoItemEvento;
import br.jus.trt23.contavinculada.sessions.FaturamentoItemEventoFacade;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class FaturamentoItemEventoController extends AbstractController<FaturamentoItemEvento> {

    public FaturamentoItemEventoController() {
        super(FaturamentoItemEvento.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "FaturamentoItemEvento"; 
    }
    
    public FaturamentoItemEvento getFaturamentoItemEventoPadrao(){
        FaturamentoItemEventoFacade faturamentoItemEventoFacade;
        if(getFacade() instanceof FaturamentoItemEventoFacade){
            faturamentoItemEventoFacade = (FaturamentoItemEventoFacade)getFacade();
            return faturamentoItemEventoFacade.getFaturamentoItemEventoPadrao();
        }
        return null;
    }    
}
