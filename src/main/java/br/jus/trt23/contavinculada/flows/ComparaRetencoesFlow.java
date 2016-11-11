package br.jus.trt23.contavinculada.flows;

import br.jus.trt23.contavinculada.entities.Contrato;
import br.jus.trt23.contavinculada.entities.Faturamento;
import br.jus.trt23.contavinculada.entities.FiscalEspecie;
import br.jus.trt23.contavinculada.jsf.ContratoPorFiscalController;
import br.jus.trt23.contavinculada.jsf.FiscalEspecieController;
import br.jus.trt23.contavinculada.qualifiers.PorFiscal;
import br.jus.trt23.webacesso.util.UsuarioSessao;
import javax.annotation.PostConstruct;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@FlowScoped("compararetencoesflow")
public class ComparaRetencoesFlow extends AbstractFlow<Contrato>{  
    public ComparaRetencoesFlow() {
    }
    
    @Getter
    @Setter
    private Faturamento faturamentoBase;

    @Getter
    @Setter
    private Faturamento faturamentoAComparar;    
    
    @Inject
    private UsuarioSessao usuarioSessao;
       
    @Inject
    private FiscalEspecieController fiscalEspecieController;
    
    @Inject
    @PorFiscal
    @Getter
    private ContratoPorFiscalController controller;
    
    private FiscalEspecie fiscalEspecie;
       
    @PostConstruct
    public void init(){
        //o id 3 corresponde ao fiscal administrativo
        fiscalEspecie = fiscalEspecieController.getEntity(3L);
        controller.getLazyItems().setMatricula(usuarioSessao.getLogin());
        controller.getLazyItems().setFiscalEspecie(fiscalEspecie);
    }

}
