package br.jus.trt23.contavinculada.controllers;

import br.jus.trt23.contavinculada.entities.Alocacao;
import br.jus.trt23.contavinculada.entities.PostoDeTrabalho;
import br.jus.trt23.contavinculada.sessions.AlocacaoFacade;
import java.time.LocalDate;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class AlocacaoController extends AbstractController<Alocacao> {
    public AlocacaoController() {
        super(Alocacao.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "Alocacao";
    }
    
    public List<Alocacao> findVigenteParaOPostoDeTrabalho(
            PostoDeTrabalho posto, LocalDate refInicio, LocalDate refFim) throws Exception{
        if(getFacade() instanceof AlocacaoFacade){
            AlocacaoFacade alocacaoFacade = (AlocacaoFacade) getFacade();
            return alocacaoFacade.findVigenteParaOPostoDeTrabalho(
                    posto, refInicio,refFim);
        }
        return null;
    }
    
    public Alocacao findVigenteHojeParaOPostoDeTrabalho(
            PostoDeTrabalho posto) throws Exception{
        if(getFacade() instanceof AlocacaoFacade){
            AlocacaoFacade alocacaoFacade = (AlocacaoFacade) getFacade();
            return alocacaoFacade.findVigenteHojeParaOPostoDeTrabalho(
                    posto);
        }
        return null;
    }
    
}
