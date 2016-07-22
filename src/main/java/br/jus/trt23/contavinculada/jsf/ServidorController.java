package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Servidor;
import br.jus.trt23.contavinculada.session.ServidorFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ServidorController extends AbstractController<ServidorFacade, Servidor> {

    @Inject
    private transient ServidorFacade facade;

    public ServidorController() {
        setMessagePrefix("Servidor");
    }

    @Override
    protected ServidorFacade getFacade() {
        return facade;
    }

    @FacesConverter(forClass = Servidor.class)
    public static class ServidorConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ServidorController controller = (ServidorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "servidorController");
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
            if (object instanceof Servidor) {
                Servidor o = (Servidor) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Servidor.class.getName());
            }
        }

    }


}
