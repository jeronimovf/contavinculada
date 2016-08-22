package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.ContaVinculada;
import br.jus.trt23.contavinculada.entities.Contrato;
import br.jus.trt23.contavinculada.entities.EncargoAliquota;
import br.jus.trt23.contavinculada.entities.Faturamento;
import br.jus.trt23.contavinculada.entities.Fiscal;
import br.jus.trt23.contavinculada.entities.PostoDeTrabalho;
import br.jus.trt23.contavinculada.jsf.util.JsfUtil;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class ContratoController extends AbstractController<Contrato> {

    public ContratoController() {
        super(Contrato.class);
    }

    private Fiscal fiscalNovo;
    private EncargoAliquota aliquotaNova;
    private Faturamento faturamentoNovo;
    private ContaVinculada contaNova;
    private PostoDeTrabalho postoNovo;
    private Contrato aditivoNovo;

    @Override
    protected void prepareDlg(){
        prepareFiscalNovo();
        prepareAliquotaNova();
        prepareContaNova();
        preparePostoNovo();
        prepareFaturamentoNovo();
        prepareAditivoNovo();
    }
    
    public String prepareFiscalNovo() {
        setFiscalNovo(new Fiscal());
        return "fiscalNovo";
    }

    public String prepareAliquotaNova() {
        setAliquotaNova(new EncargoAliquota());
        return "aliquotaNova";
    }

    public String prepareFaturamentoNovo() {
        setFaturamentoNovo(new Faturamento());
        return "faturamentoNovo";
    }

    public String prepareContaNova() {
        setContaNova(new ContaVinculada());
        return "contaNova";
    }

    public String preparePostoNovo() {
        setPostoNovo(new PostoDeTrabalho());
        return "postoNovo";
    }

    public String prepareAditivoNovo() {
        setAditivoNovo(new Contrato());
        return "aditivoNovo";
    }
    
    public String prepareAditivoEdit(Contrato aditivo) {
        selected = aditivo;
        return "Edit";
    }

    public String fiscalCreate() throws Exception {
        String msg;
        try {
            selected.getFiscais().add(fiscalNovo);
            fiscalNovo.setContrato(selected);
            saveOrCreate();
            prepareFiscalNovo();
            msg = getResponseCreated("Fiscal");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }
    
    public String postoDeTrabalhoCreate() throws Exception {
        String msg;
        try {
            selected.getPostosDeTrabalho().add(postoNovo);
            postoNovo.setContrato(selected);
            saveOrCreate();
            msg = getResponseCreated("PostoDeTrabalho");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }

    public String contaCreate() throws Exception {
        String msg;
        try {
            selected.getContasVinculadas().add(contaNova);
            contaNova.setContrato(selected);
            saveOrCreate();
            prepareContaNova();
            msg = getResponseCreated("ContaVinculada");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }    
    
    public String faturamentoCreate() throws Exception {
        String msg;
        try {
            selected.getFaturamentos().add(faturamentoNovo);
            faturamentoNovo.setContrato(selected);
            saveOrCreate();
            prepareFaturamentoNovo();
            msg = getResponseCreated("Faturamento");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }       
    public String encargoAliquotaCreate() throws Exception {
        String msg;
        try {
            selected.getAliquotas().add(aliquotaNova);
            aliquotaNova.setContrato(selected);
            saveOrCreate();
            prepareAliquotaNova();
            msg = getResponseCreated("EncargoAliquota");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }       

    @Override
    protected String getMessagePrefix() {
        return "Contrato";
    }
}
