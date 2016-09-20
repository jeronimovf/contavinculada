/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.validators;

import br.jus.trt23.contavinculada.constraints.VigenciaValida;
import br.jus.trt23.contavinculada.entities.EntidadeGenerica;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author j129-9
 */
public class VigenciaValidator implements ConstraintValidator<VigenciaValida, EntidadeGenerica> {

    @Override
    public void initialize(VigenciaValida constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(EntidadeGenerica value, ConstraintValidatorContext context) {
        if(null == value) return true;
        if(null == value.getVigenteDesde()){
            return false;
        }
        else{
            if(null == value.getVigenteAte()){
                return true;
            }
            else{
                return value.getVigenteAte().compareTo(value.getVigenteDesde())>0;
            }
        }
    }

    
}
