package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.CalendarioFeriado;
import br.jus.trt23.contavinculada.entities.CalendarioFeriadoItem;
import br.jus.trt23.contavinculada.enums.EActiveAction;
import br.jus.trt23.contavinculada.enums.EDiasComputados;
import br.jus.trt23.contavinculada.enums.EFeriadoEscopo;
import br.jus.trt23.contavinculada.jsf.util.JsfUtil;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.faces.flow.FlowScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.datatable.DataTable;

@Named
@FlowScoped("calendarioferiadoflow")
@Getter
@Setter
public class CalendarioFeriadoController extends AbstractController<CalendarioFeriado>{

    private CalendarioFeriadoItem calendarioFeriadoItem;    

    public String prepareCalendarioFeriadoItemNovo() {
        setCalendarioFeriadoItem(new CalendarioFeriadoItem());
        return "ItemNovo";
    }

    public String prepareCalendarioFeriadoItemEdit() {
        Object obj = JsfUtil.findComponent("calendarioFeriadoItemDT");
        if(obj instanceof DataTable){
            DataTable dt = (DataTable)obj;  
            calendarioFeriadoItem = (CalendarioFeriadoItem) dt.getRowData();
        }
        setActiveAction(EActiveAction.EDIT);
        return "ItemEdit";
    }

    public String saveOrCreateCalendarioFeriadoItem() throws Exception {
        String msg;
        try {
            selected.addFeriados(calendarioFeriadoItem);
            saveOrCreate();
            msg = getResponseCreated("Feriado");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }

    public CalendarioFeriadoController() {
        super(CalendarioFeriado.class);
        calendarioFeriadoItem = new CalendarioFeriadoItem();
    }

    public Set<SelectItem> getFeriadoEscopo() {
        return JsfUtil.getSelectItems(Arrays.asList(EFeriadoEscopo.values()), true);
    }

    public Set<SelectItem> getDiasComputados() {
        Set<SelectItem> si = new HashSet<>();
        for (EDiasComputados edc : EDiasComputados.values()) {
            si.add(new SelectItem(edc, edc.getNome()));
        }
        return si;
    }

    @Override
    protected void prepareDlg() {

    }

    @Override
    protected String getMessagePrefix() {
        return "CalendarioFeriado";
    }
 
    
}
