package br.jus.trt23.contavinculada.controllers;

import br.jus.trt23.contavinculada.entities.CalendarioFeriado;
import br.jus.trt23.contavinculada.entities.CalendarioFeriadoItem;
import br.jus.trt23.contavinculada.enums.EDiasComputados;
import br.jus.trt23.contavinculada.enums.EFeriadoEscopo;
import br.jus.trt23.contavinculada.handlers.JsfUtil;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.Dependent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.datatable.DataTable;

@Named
@Dependent
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

    public List<SelectItem> getFeriadoEscopo() {
        return JsfUtil.getSelectItems(Arrays.asList(EFeriadoEscopo.values()), true);
    }

    public Set<SelectItem> getDiasComputados() {
        Set<SelectItem> diasComputadosList = new HashSet<>();
        for(EDiasComputados dia: EDiasComputados.values()){
            diasComputadosList.add(new SelectItem(dia,dia.getNome()));
        }
        return diasComputadosList;
    }

    @Override
    protected String getMessagePrefix() {
        return "CalendarioFeriado";
    }
 
    
}
