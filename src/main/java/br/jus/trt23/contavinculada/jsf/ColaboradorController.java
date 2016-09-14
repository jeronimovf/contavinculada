package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Colaborador;
import br.jus.trt23.contavinculada.entities.PessoaJuridica;
import br.jus.trt23.contavinculada.session.ColaboradorFacade;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class ColaboradorController extends AbstractController<Colaborador> {

    public ColaboradorController() {
        super(Colaborador.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "Colaborador";
    }

    public List<Colaborador> complete(String criteria, PessoaJuridica contratante){
        if(getFacade() instanceof ColaboradorFacade){
            ColaboradorFacade colaboradorFacade = (ColaboradorFacade) getFacade();
            return colaboradorFacade.complete(criteria, contratante);            
        }
        return new ArrayList<>();
    }

}
