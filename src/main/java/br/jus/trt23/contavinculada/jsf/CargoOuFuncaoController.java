package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.CargoOuFuncao;
import br.jus.trt23.contavinculada.session.CargoOuFuncaoFacade;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named
@SessionScoped
public class CargoOuFuncaoController extends AbstractController<CargoOuFuncaoFacade, CargoOuFuncao> {

    @Inject
    private transient CargoOuFuncaoFacade facade;

    public CargoOuFuncaoController() {
        setMessagePrefix("CargoOuFuncao");
    }

    @Override
    protected CargoOuFuncaoFacade getFacade() {
        return facade;
    }

    @FacesConverter(forClass = CargoOuFuncao.class)
    public static class CargoOuFuncaoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CargoOuFuncaoController controller = (CargoOuFuncaoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cargoOuFuncaoController");
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
            if (object instanceof CargoOuFuncao) {
                CargoOuFuncao o = (CargoOuFuncao) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CargoOuFuncao.class.getName());
            }
        }

    }


}
