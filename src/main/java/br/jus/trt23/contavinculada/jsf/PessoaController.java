package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Pessoa;
import br.jus.trt23.contavinculada.entities.PessoaJuridica;
import br.jus.trt23.contavinculada.session.PessoaFacade;
import br.jus.trt23.contavinculada.session.PessoaFisicaFacade;
import br.jus.trt23.contavinculada.session.PessoaJuridicaFacade;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named
@SessionScoped
public class PessoaController extends AbstractController<PessoaFacade, Pessoa> {

    @Inject
    private transient PessoaFacade facade;
    @Inject
    private transient PessoaJuridicaFacade pjFacade;
    @Inject
    private transient PessoaFisicaFacade pfFacade;    

    public PessoaController() {
        setMessagePrefix("Pessoa");
    }

    @Override
    protected PessoaFacade getFacade() {
        return facade;
    }

    @Override
    public List<Pessoa> complete(String criteria) {
        List<Pessoa> p = new ArrayList<>();
        p.addAll(pfFacade.complete(criteria));
        p.addAll(pjFacade.complete(criteria));
        return p; //To change body of generated methods, choose Tools | Templates.
    }

    @FacesConverter(forClass = Pessoa.class)
    public static class PessoaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PessoaController controller = (PessoaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pessoaController");
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
            if (object instanceof Pessoa) {
                Pessoa o = (Pessoa) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Pessoa.class.getName());
            }
        }

    }


}
