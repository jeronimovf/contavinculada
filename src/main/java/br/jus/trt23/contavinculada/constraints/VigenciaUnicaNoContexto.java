/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.constraints;

import br.jus.trt23.contavinculada.validators.VigenciaUnicaNoContextoValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.ANNOTATION_TYPE,ElementType.TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = { VigenciaUnicaNoContextoValidator.class })
@Documented
public @interface VigenciaUnicaNoContexto {

	String message() default "As vigências neste contexto são exclusivas.  Não podem existir duas ocorrências com data de vigência em aberto (termino da validade em branco) ou coincidência de períodos, ainda que parcial.";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}