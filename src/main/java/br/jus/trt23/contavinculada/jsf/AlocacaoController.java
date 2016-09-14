package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Alocacao;
import br.jus.trt23.contavinculada.entities.PostoDeTrabalho;
import br.jus.trt23.contavinculada.session.AlocacaoFacade;
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
    
    public Alocacao findVigenteParaOPostoDeTrabalho(PostoDeTrabalho posto) throws Exception{
        if(getFacade() instanceof AlocacaoFacade){
            AlocacaoFacade alocacaoFacade = (AlocacaoFacade) getFacade();
            return alocacaoFacade.findVigenteParaOPostoDeTrabalho(posto);
        }
        return null;
    }
}
