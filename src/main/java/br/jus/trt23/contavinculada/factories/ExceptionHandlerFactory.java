/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.factories;

import br.jus.trt23.contavinculada.handlers.CustomExceptionHandler;
import javax.faces.context.ExceptionHandler;

/**
 *
 * @author j129-9
 */
public class ExceptionHandlerFactory extends javax.faces.context.ExceptionHandlerFactory {

    private final ExceptionHandlerFactory parent;

    public ExceptionHandlerFactory(ExceptionHandlerFactory parent) {
        this.parent = parent;
    }    
    
    @Override
    public ExceptionHandler getExceptionHandler() {
        return new CustomExceptionHandler(this.parent.getExceptionHandler());
    }
    
}
