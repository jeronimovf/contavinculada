/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.validators;

import br.jus.trt23.contavinculada.constraints.VigenciaUnicaNoContexto;
import br.jus.trt23.contavinculada.entities.EntidadeGenerica;
import br.jus.trt23.contavinculada.entities.IEntidadeQueDefineOContexto;
import br.jus.trt23.contavinculada.sessions.ImplementacaoBasicaFacade;
import java.util.AbstractMap.SimpleEntry;
import javax.inject.Inject;

/**
 *
 * @author j129-9
 */
public class VigenciaUnicaNoContextoValidator extends ValidadorGenerico<VigenciaUnicaNoContexto, EntidadeGenerica>{
    @Inject
    private ImplementacaoBasicaFacade facade;
    
    @Override
    public boolean passedCustomValidation() {
        if(null == value) return true;
        if(!(value instanceof IEntidadeQueDefineOContexto)) return false;
        IEntidadeQueDefineOContexto iEntidadeQueDefineOContexto = 
                (IEntidadeQueDefineOContexto) value;
        SimpleEntry eg = iEntidadeQueDefineOContexto.getContexto();
        if (null == eg) return true;
        return facade.eVigenciaUnicaNoContexto(eg,value);
    }

    
}
