package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Colaborador;
import br.jus.trt23.contavinculada.entities.PessoaJuridica;
import br.jus.trt23.contavinculada.entities.Salario;
import br.jus.trt23.contavinculada.session.ColaboradorFacade;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

    public List<Colaborador> complete(String criteria, PessoaJuridica contratante) {
        if (getFacade() instanceof ColaboradorFacade) {
            ColaboradorFacade colaboradorFacade = (ColaboradorFacade) getFacade();
            return colaboradorFacade.complete(criteria, contratante);
        }
        return null;
    }
    

    public Salario findSalarioVigente(Colaborador colaborador) throws java.lang.Exception {
        List<Salario> salarios = new ArrayList<>();
        for (Salario sal : colaborador.getSalarios()) {
            if (getFacade().isVigente(sal)) {
                salarios.add(sal);
            }
        }
        if (salarios.size() > 1) {
            throw new Exception("Há mais de um salário vigente atualmente.");
        }
        return salarios.get(0);
    }

    public Salario findSalarioPlenamenteVigenteEntre(LocalDate inicio,
            LocalDate fim, Colaborador colaborador) throws java.lang.Exception {
        List<Salario> salarios;
        Stream<Salario> sSalarios = colaborador.getSalarios().stream();
        salarios = sSalarios.filter(s -> s.isVigenteParcialmente(inicio,fim)).collect(Collectors.toList());
        if (salarios.size() <= 0) {
            throw new Exception("Não há salário vigente para o período.");
        }

        if (salarios.size() > 1) {
            throw new Exception("Há mais de um salário vigente no período informado.");
        }
        
        if (!salarios.get(0).isVigentePlenamenteEntre(inicio, fim)) {
            throw new Exception("Não há salário cuja vigência contemple todo o período informado");
        }
        return salarios.get(0);
    }
}
