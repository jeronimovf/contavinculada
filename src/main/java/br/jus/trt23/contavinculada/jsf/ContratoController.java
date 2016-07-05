package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Contrato;
import br.jus.trt23.contavinculada.session.ContratoFacade;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named
@SessionScoped
public class ContratoController extends AbstractController<ContratoFacade, Contrato> {

    @Inject
    private transient ContratoFacade facade;

    public ContratoController() {
        setMessagePrefix("Contrato");
    }

    @Override
    protected ContratoFacade getFacade() {
        return facade;
    }

    public String prepareAditivoEdit(Contrato aditivo) {
        selected = aditivo;
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    @FacesConverter(forClass = Contrato.class)
    public static class ContratoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContratoController controller = (ContratoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contratoController");
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
            if (object instanceof Contrato) {
                Contrato o = (Contrato) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Contrato.class.getName());
            }
        }

    }

}
