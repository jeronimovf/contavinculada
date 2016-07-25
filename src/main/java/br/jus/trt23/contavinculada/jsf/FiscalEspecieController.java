package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.FiscalEspecie;
import br.jus.trt23.contavinculada.session.FiscalEspecieFacade;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named
@SessionScoped
public class FiscalEspecieController extends AbstractController<FiscalEspecieFacade, FiscalEspecie> {

    @Inject
    private transient FiscalEspecieFacade facade;

    public FiscalEspecieController() {
        super();        
        setMessagePrefix("FiscalEspecie");
    }

    @Override
    protected FiscalEspecieFacade getFacade() {
        return facade;
    }

    @FacesConverter(forClass = FiscalEspecie.class)
    public static class FiscalEspecieControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FiscalEspecieController controller = (FiscalEspecieController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fiscalEspecieController");
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
            if (object instanceof FiscalEspecie) {
                FiscalEspecie o = (FiscalEspecie) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FiscalEspecie.class.getName());
            }
        }

    }


}
