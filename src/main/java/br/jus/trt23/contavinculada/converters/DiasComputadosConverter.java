/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.converters;

import br.jus.trt23.contavinculada.enums.EDiasComputados;
import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author j129-9
 */
@Named
@Dependent
@FacesConverter(forClass = EDiasComputados.class)
public class DiasComputadosConverter extends EnumConverter {

    public DiasComputadosConverter() {
        super(EDiasComputados.class);
    }

    public DiasComputadosConverter(Class targetClass) {
        super(EDiasComputados.class);
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return EDiasComputados.getByNome(value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof EDiasComputados) {
            EDiasComputados o = (EDiasComputados) object;
            return o.getNome();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EDiasComputados.class.getName());
        }
    }
}
