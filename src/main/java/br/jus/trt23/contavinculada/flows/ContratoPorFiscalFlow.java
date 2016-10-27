package br.jus.trt23.contavinculada.flows;

import br.jus.trt23.contavinculada.entities.Colaborador;
import br.jus.trt23.contavinculada.entities.Contrato;
import br.jus.trt23.contavinculada.entities.FiscalEspecie;
import br.jus.trt23.contavinculada.jsf.ContratoPorFiscalController;
import br.jus.trt23.contavinculada.jsf.FiscalEspecieController;
import br.jus.trt23.contavinculada.jsf.RetencaoController;
import br.jus.trt23.contavinculada.qualifiers.PorFiscal;
import br.jus.trt23.webacesso.util.UsuarioSessao;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@FlowScoped("contratoporfiscalflow")
public class ContratoPorFiscalFlow extends AbstractFlow<Contrato>{
    //1 faturamento, 2 retenção
    @Getter
    @Setter
    private Integer exibeFaturamentoOuRetencao;
    
    @Setter
    private List<Colaborador> colaboradoresParaOContrato;
    
    @Inject
    private UsuarioSessao usuarioSessao;
    
    @Getter
    @Setter
    @Inject 
    private RetencaoController retencaoController;
    
    @Inject
    private FiscalEspecieController fiscalEspecieController;
    
    @Inject
    @PorFiscal
    @Getter
    private ContratoPorFiscalController controller;
    
    private FiscalEspecie fiscalEspecie;

    public ContratoPorFiscalFlow() {
        this.exibeFaturamentoOuRetencao = 1;

    }   
    
    public List<Colaborador> getColaboradoresParaOContrato() throws Exception {
        if (this.colaboradoresParaOContrato == null) {
            setColaboradoresParaOContrato(
                    controller.completeColaboradoresParaOContrato(
                            "",getController().getSelected()));
        }
        return this.colaboradoresParaOContrato;
    }    
    
    public List<Colaborador> completeColaboradoresParaOContrato(String onde) throws Exception{
        if("%%%".equals(onde)) return getColaboradoresParaOContrato();
        Stream<Colaborador> stream = getColaboradoresParaOContrato().stream();
        return stream.filter(c -> c.getColaborador().getNome().toLowerCase().
                contains(onde.toLowerCase())).collect(Collectors.toList());
    }
    
    @PostConstruct
    public void init(){
        fiscalEspecie = fiscalEspecieController.getEntity(1L);
        controller.getLazyItems().setMatricula(usuarioSessao.getLogin());
        controller.getLazyItems().setFiscalEspecie(fiscalEspecie);
    }
}
