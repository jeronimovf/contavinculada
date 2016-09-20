/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.validators;

import br.jus.trt23.contavinculada.constraints.ContratoValido;
import br.jus.trt23.contavinculada.entities.Contrato;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author j129-9
 */
public class ContratoValidator implements ConstraintValidator<ContratoValido, Contrato> {

    @Override
    public void initialize(ContratoValido constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(Contrato value, ConstraintValidatorContext context) {
        if(null == value) return true;
        
        return true;
    }
    
}
