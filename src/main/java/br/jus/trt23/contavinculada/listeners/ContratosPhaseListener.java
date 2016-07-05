/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.listeners;

import br.jus.trt23.contavinculada.jsf.ColaboradorController;
import br.jus.trt23.contavinculada.jsf.ContratoController;
import br.jus.trt23.contavinculada.jsf.EncargoController;
import br.jus.trt23.contavinculada.jsf.FiscalEspecieController;
import br.jus.trt23.contavinculada.jsf.PessoaFisicaController;
import br.jus.trt23.contavinculada.jsf.PessoaJuridicaController;
import br.jus.trt23.contavinculada.jsf.ProxyController;
import br.jus.trt23.contavinculada.jsf.ServidorController;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

/**
 *
 * @author j129-9
 */
public class ContratosPhaseListener implements PhaseListener {

    @Inject
    FiscalEspecieController fiscalEspecieController;
    @Inject
    ProxyController proxyController;
    @Inject
    EncargoController encargoController;
    @Inject
    ContratoController contratoController;
    @Inject
    PessoaJuridicaController pessoaJuridicaController;
    @Inject
    PessoaFisicaController pessoaFisicaController;    
    @Inject
    ColaboradorController colaboradorController;  
    @Inject
    ServidorController servidorController;

    @Override
    public void afterPhase(PhaseEvent event) {
        if (event.getPhaseId().equals(getPhaseId())) {
            String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
            switch (viewId) {
                case "/fiscalespecie/List.xhtml":
                    proxyController.setController(fiscalEspecieController);
                    break;
                case "/encargo/List.xhtml":
                    proxyController.setController(encargoController);                    
                    break;
                case "/contrato/List.xhtml":
                    proxyController.setController(contratoController);
                    break;  
                case "/pessoajuridica/List.xhtml":
                    proxyController.setController(pessoaJuridicaController);
                    break;         
                case "/pessoafisica/List.xhtml":
                    proxyController.setController(pessoaFisicaController);
                    break;    
                case "/colaborador/List.xhtml":
                    proxyController.setController(colaboradorController);
                    break;           
                case "/servidor/List.xhtml":
                    proxyController.setController(servidorController);
                    break;                      
                default:
                    break;
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
