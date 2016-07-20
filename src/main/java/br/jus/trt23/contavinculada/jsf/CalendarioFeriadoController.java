package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.CalendarioFeriado;
import br.jus.trt23.contavinculada.entities.CalendarioFeriadoItem;
import br.jus.trt23.contavinculada.enums.EFeriadoEscopo;
import br.jus.trt23.contavinculada.jsf.util.JsfUtil;
import br.jus.trt23.contavinculada.session.CalendarioFeriadoFacade;
import java.util.Arrays;
import java.util.List;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
@Getter
@Setter
public class CalendarioFeriadoController extends AbstractController<CalendarioFeriadoFacade, CalendarioFeriado> {

    @Inject
    private transient CalendarioFeriadoFacade facade;

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
        setMessagePrefix("CalendarioFeriado");
    }

    @Override
    protected CalendarioFeriadoFacade getFacade() {
        return facade;
    }

    public List<SelectItem> getFeriadoEscopo() {
        return JsfUtil.getSelectItems(Arrays.asList(EFeriadoEscopo.values()), true);
    }

    @Override
    protected void prepareDlg() {
        prepareCalendarioFeriadoItemNovo();
    }

    @FacesConverter(forClass = CalendarioFeriado.class)
    public static class CalendarioFeriadoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CalendarioFeriadoController controller = (CalendarioFeriadoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "calendarioFeriadoController");
            return controller.getEntity(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CalendarioFeriado) {
                CalendarioFeriado o = (CalendarioFeriado) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CalendarioFeriado.class.getName());
            }
        }
    }

}
