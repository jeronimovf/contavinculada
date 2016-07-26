package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.CalendarioFeriado;
import br.jus.trt23.contavinculada.entities.CalendarioFeriadoItem;
import br.jus.trt23.contavinculada.enums.EDiasComputados;
import br.jus.trt23.contavinculada.enums.EFeriadoEscopo;
import br.jus.trt23.contavinculada.jsf.util.JsfUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class CalendarioFeriadoController extends AbstractController<CalendarioFeriado> {
    private CalendarioFeriadoItem calendarioFeriadoItemNovo;

    public String prepareCalendarioFeriadoItemNovo() {
        setCalendarioFeriadoItemNovo(new CalendarioFeriadoItem());
        return "CalendarioFeriadoItemNovo";
    }

    public String calendarioFeriadoItemCreate() throws Exception {
        String msg;
        try {
            selected.getFeriados().add(calendarioFeriadoItemNovo);
            saveOrCreate();
            prepareCalendarioFeriadoItemNovo();
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
        super();
    }

    public List<SelectItem> getFeriadoEscopo() {
        return JsfUtil.getSelectItems(Arrays.asList(EFeriadoEscopo.values()), true);
    }

    public List<SelectItem> getDiasComputados() {
        List<SelectItem> si = new ArrayList<>();
        for(EDiasComputados edc: EDiasComputados.values()){
            si.add(new SelectItem(edc,edc.getNome()));
        }
        return si;
    }

    @Override
    protected void prepareDlg() {
        prepareCalendarioFeriadoItemNovo();
    }

    @Override
    protected String getMessagePrefix() {
        return "CalendarioFeriado";
    }
}
