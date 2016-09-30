package br.jus.trt23.contavinculada.flows;

import br.jus.trt23.contavinculada.entities.Colaborador;
import br.jus.trt23.contavinculada.entities.Contrato;
import br.jus.trt23.contavinculada.jsf.ContratoController;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Named
@RequiredArgsConstructor
@FlowScoped("contratoflow")
public class ContratoFlow extends AbstractFlow<Contrato>{
    @Setter
    List<Colaborador> colaboradoresParaOContrato;
    
    @Override
    public ContratoController getController(){
        if(controller instanceof ContratoController){
            return (ContratoController) controller;
        }
        return null;
    }
    
    public List<Colaborador> getColaboradoresParaOContrato() throws Exception {
        if (this.colaboradoresParaOContrato == null) {
            setColaboradoresParaOContrato(
                    getController().completeColaboradoresParaOContrato(
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
}
