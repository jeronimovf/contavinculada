package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Colaborador;
import br.jus.trt23.contavinculada.entities.PessoaJuridica;
import br.jus.trt23.contavinculada.enums.EActiveAction;
import br.jus.trt23.contavinculada.jsf.util.JsfUtil;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.datatable.DataTable;

@Named
@ViewScoped
@Getter
@Setter
public class PessoaJuridicaController extends AbstractController<PessoaJuridica> {

    private Colaborador colaboradorNovo;

    public PessoaJuridicaController() {
        super(PessoaJuridica.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "PessoaJuridica";
    }

    @Override
    protected void prepareDlg() {
        prepareColaboradorNovo();
    }

    public String prepareColaboradorNovo() {
        setColaboradorNovo(new Colaborador());
        return "colaboradorNovo";
    }
    
    public String prepareColaboradorEdit(){
        Object obj = JsfUtil.findComponent("colaboradorDT");
        if(obj instanceof DataTable){
            DataTable dt = (DataTable)obj;  
            setColaboradorNovo((Colaborador) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "colaboradorEdit";
    }    
    
    public String saveOrCreateColaborador() throws Exception {
        String msg;
        try {
            selected.getContratanteEm().add(colaboradorNovo);
            colaboradorNovo.setEmpregador(selected);
            saveOrCreate();
            prepareColaboradorNovo();
            msg = getResponseCreated("Colaborador");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }    
}
