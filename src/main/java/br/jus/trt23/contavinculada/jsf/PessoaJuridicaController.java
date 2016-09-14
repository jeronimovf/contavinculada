package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Colaborador;
import br.jus.trt23.contavinculada.entities.PessoaFisica;
import br.jus.trt23.contavinculada.entities.PessoaJuridica;
import br.jus.trt23.contavinculada.entities.Salario;
import br.jus.trt23.contavinculada.jsf.util.JsfUtil;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.datatable.DataTable;

@Named
@Dependent
@Getter
@Setter
public class PessoaJuridicaController extends AbstractController<PessoaJuridica> {

    private Colaborador colaboradorNovo;
    private Salario salarioNovo;

    public PessoaJuridicaController() {
        super(PessoaJuridica.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "PessoaJuridica";
    }

    public String prepareColaboradorNovo() {
        setColaboradorNovo(new Colaborador());
        getColaboradorNovo().setColaborador(new PessoaFisica());
        return "ColaboradorNovo";
    }
    
    public String prepareSalarioNovo(){
        setSalarioNovo(new Salario());
        return "SalarioNovo";
    }
    
    public String prepareColaboradorEdit(){
        Object obj = JsfUtil.findComponent("colaboradorDT");
        if(obj instanceof DataTable){
            DataTable dt = (DataTable)obj;  
            setColaboradorNovo((Colaborador) dt.getRowData());
        }
        return "ColaboradorEdit";
    }    

    public String prepareSalarioEdit(){
        Object obj = JsfUtil.findComponent("salarioDT");
        if(obj instanceof DataTable){
            DataTable dt = (DataTable)obj;  
            setSalarioNovo((Salario) dt.getRowData());
        }
        return "SalarioEdit";
    }       

    public String saveOrCreateColaborador() throws Exception {
        String msg;
        try {
            selected.addContratanteEm(colaboradorNovo);
            saveOrCreate();
            msg = getResponseCreated("Colaborador");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }    
    
    public String saveOrCreateSalario() throws Exception {
        String msg;
        try {
            getColaboradorNovo().getSalarios().add(getSalarioNovo());            
            saveOrCreate();
            prepareSalarioNovo();
            msg = getResponseCreated("Salario");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }    
    
}
