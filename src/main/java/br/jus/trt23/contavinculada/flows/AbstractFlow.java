/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.flows;

import br.jus.trt23.contavinculada.entities.EntidadeGenerica;
import br.jus.trt23.contavinculada.jsf.AbstractController;
import java.io.Serializable;
import javax.inject.Inject;
import lombok.Getter;

/**
 *
 * @author j129-9
 */
public abstract class AbstractFlow<T extends EntidadeGenerica> implements Serializable{
    @Getter
    @Inject    
    protected AbstractController<T> controller;       
}
