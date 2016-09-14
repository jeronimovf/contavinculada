package br.jus.trt23.contavinculada.flows;

import br.jus.trt23.contavinculada.entities.PessoaJuridica;
import br.jus.trt23.contavinculada.jsf.PessoaJuridicaController;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
@FlowScoped("pessoajuridicaflow")
public class PessoaJuridicaFlow extends AbstractFlow<PessoaJuridica>{
    @Override
    public PessoaJuridicaController getController(){
        if(controller instanceof PessoaJuridicaController){
            return (PessoaJuridicaController) controller;
        }
        return null;
    }
    
    
}
