/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.converters;

import br.jus.trt23.contavinculada.entities.Colaborador;
import br.jus.trt23.contavinculada.jsf.util.JsfUtil;
import br.jus.trt23.contavinculada.sessions.ColaboradorFacade;
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
@FacesConverter(forClass = Colaborador.class)
public class ColaboradorConverter implements Converter {

    @Inject
    private ColaboradorFacade facade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (facesContext == null || component == null) {
            throw new NullPointerException();
        }
        if (null == value || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.facade.find(getKey(value));
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
        if (facesContext == null || component == null) {
            throw new NullPointerException();
        }
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Colaborador) {
            Colaborador o = (Colaborador) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Colaborador.class.getName()});
            return null;
        }
    }
}
