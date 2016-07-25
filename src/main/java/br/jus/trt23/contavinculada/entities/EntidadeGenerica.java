/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.entities;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author j129-9
 */
@MappedSuperclass
@Getter
@Setter
@RequiredArgsConstructor
public abstract class EntidadeGenerica implements Serializable{   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
        
    @NotNull
    private LocalDate vigenteDesde;

    private LocalDate vigenteAte;  
    
    @NotNull
    private LocalDateTime criadoEm;

    private LocalDateTime destruidoEm;  

    public static Object getFieldValue(Object bean, String fieldName) {
        try {
            BeanInfo info = Introspector.getBeanInfo(bean.getClass());
            for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                if (pd.getName().equals(fieldName)) {
                    return pd.getReadMethod().invoke(bean);
                }
            }
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(EntidadeGenerica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
}
