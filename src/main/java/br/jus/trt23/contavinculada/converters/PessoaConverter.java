/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.converters;

import br.jus.trt23.contavinculada.entities.Pessoa;
import br.jus.trt23.contavinculada.entities.PessoaFisica;
import br.jus.trt23.contavinculada.entities.PessoaJuridica;
import br.jus.trt23.contavinculada.jsf.util.JsfUtil;
import br.jus.trt23.contavinculada.session.PessoaFacade;
import br.jus.trt23.contavinculada.session.PessoaFisicaFacade;
import br.jus.trt23.contavinculada.session.PessoaJuridicaFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author j129-9
 */
@Named
@Dependent
@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter {

    @Inject
    private PessoaFacade facade;
    @Inject
    private PessoaFisicaFacade facadePF;
    @Inject
    private PessoaJuridicaFacade facadePJ;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        facade.find(getKey(value));
        facadePF.find(getKey(value));
        facadePJ.find(getKey(value));
        return facade.find(getKey(value));
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
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof PessoaJuridica) {
            PessoaJuridica o = (PessoaJuridica) object;
            return getStringKey(o.getId());
        } else if (object instanceof PessoaFisica) {
            PessoaFisica o = (PessoaFisica) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), object.getClass().getName()});
            return null;
        }
    }

}
