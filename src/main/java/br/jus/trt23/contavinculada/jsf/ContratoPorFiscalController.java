package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.handlers.ContratoPorFiscalLazyDataModel;
import br.jus.trt23.contavinculada.qualifiers.PorFiscal;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@Dependent
@Getter
@Setter
@PorFiscal
public class ContratoPorFiscalController extends ContratoController{
    
    private ContratoPorFiscalLazyDataModel lazyItems;

    @Override
    protected String getMessagePrefix() {
        return "Contrato";
    }      

    @Override
    public ContratoPorFiscalLazyDataModel getLazyItems() {
        if (lazyItems == null) {
            if (null == getPermanentFilters()) {
                lazyItems = new ContratoPorFiscalLazyDataModel(getFacade());
            } else {
                lazyItems = new ContratoPorFiscalLazyDataModel(getFacade(),getPermanentFilters());
            }
        }

        return lazyItems;
    }
    
    
}
