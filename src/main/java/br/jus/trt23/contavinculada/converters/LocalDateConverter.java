/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author j129-9
 */
@FacesConverter(value = "localDateConverter")
public class LocalDateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof LocalDate) {
            LocalDate dateValue = (LocalDate) value;
            return dateValue.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            throw new IllegalArgumentException("object " + value + " is of type " + value.getClass().getName() + "; expected type: " + LocalDate.class.getName());
        }
    }
}
