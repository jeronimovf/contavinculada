/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.handlers;

import br.jus.trt23.contavinculada.entities.Contrato;
import br.jus.trt23.contavinculada.entities.FiscalEspecie;
import br.jus.trt23.contavinculada.sessions.AbstractFacade;
import br.jus.trt23.contavinculada.sessions.ContratoFacade;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author j129-9
 */
public class ContratoPorFiscalLazyDataModel extends GenericLazyDataModel<Contrato> {

    @Getter
    @Setter
    private String matricula;
    @Getter
    @Setter
    private FiscalEspecie fiscalEspecie;

    public ContratoPorFiscalLazyDataModel() {
    }

    public ContratoPorFiscalLazyDataModel(AbstractFacade<Contrato> facade) {
        super(facade);
    }

    public ContratoPorFiscalLazyDataModel(AbstractFacade<Contrato> facade, Map<String, Object> filters) {
        super(facade, filters);
    }

    public ContratoPorFiscalLazyDataModel(List<Contrato> itemList) {
        super(itemList);
    }

    public ContratoPorFiscalLazyDataModel(Class<Contrato> entityClass) {
        super(entityClass);
    }

    public ContratoPorFiscalLazyDataModel(String matricula, FiscalEspecie fiscalEspecie, AbstractFacade<Contrato> facade) {
        super(facade);
        this.matricula = matricula;
        this.fiscalEspecie = fiscalEspecie;
    }

    public ContratoPorFiscalLazyDataModel(String matricula, FiscalEspecie fiscalEspecie, AbstractFacade<Contrato> facade, Map<String, Object> filters) {
        super(facade, filters);
        this.matricula = matricula;
        this.fiscalEspecie = fiscalEspecie;
    }
    
    

    @Override
    protected List<Contrato> findRange(LazyQueryHandler lazyQueryHandler) {
        if (null == getFiscalEspecie()) {
            return getFacade().findContratosPorServidorLogin(getMatricula(), lazyQueryHandler);
        } else {
            return getFacade().findContratosPorServidorLogin(getMatricula(),
                    getFiscalEspecie(), lazyQueryHandler);
        }
    }

    protected ContratoFacade getFacade() {
        if (facade instanceof ContratoFacade) {
            return (ContratoFacade) facade;
        }
        return null;
    }

//    @Override
//    public List<Contrato> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
//        System.out.println("vaisifude");
//        return super.load(first, pageSize, sortField, sortOrder, filters); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public List<Contrato> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
//        System.out.println("vaisifude");
//        return super.load(first, pageSize, multiSortMeta, filters); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    protected int count(Map<String, Object> filters) {
        return getFacade().countContratosPorServidorLogin(getMatricula(), getFiscalEspecie(), filters);
    }

    
}
