package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Colaborador;
import br.jus.trt23.contavinculada.session.ColaboradorFacade;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named
@SessionScoped
public class ColaboradorController extends AbstractController<ColaboradorFacade, Colaborador> {

    @Inject
    private transient ColaboradorFacade facade;

    public ColaboradorController() {
        super();        
        setMessagePrefix("Colaborador");
    }

    @Override
    protected ColaboradorFacade getFacade() {
        return facade;
    }

    @FacesConverter(forClass = Colaborador.class)
    public static class ColaboradorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ColaboradorController controller = (ColaboradorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "colaboradorController");
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
            if (object instanceof Colaborador) {
                Colaborador o = (Colaborador) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Colaborador.class.getName());
            }
        }

    }


}
