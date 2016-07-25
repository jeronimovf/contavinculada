package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Encargo;
import br.jus.trt23.contavinculada.entities.PessoaJuridica;
import br.jus.trt23.contavinculada.session.EncargoFacade;
import br.jus.trt23.contavinculada.session.PessoaJuridicaFacade;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named
@SessionScoped
public class PessoaJuridicaController extends AbstractController<PessoaJuridicaFacade, PessoaJuridica> {

    @Inject
    private transient PessoaJuridicaFacade facade;

    public PessoaJuridicaController() {
        super();        
        setMessagePrefix("PessoaJuridica");
    }

    @Override
    protected PessoaJuridicaFacade getFacade() {
        return facade;
    }

    @FacesConverter(forClass = PessoaJuridica.class)
    public static class PessoaJuridicaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PessoaJuridicaController controller = (PessoaJuridicaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pessoaJuridicaController");
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
            if (object instanceof PessoaJuridica) {
                PessoaJuridica o = (PessoaJuridica) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PessoaJuridica.class.getName());
            }
        }

    }


}
