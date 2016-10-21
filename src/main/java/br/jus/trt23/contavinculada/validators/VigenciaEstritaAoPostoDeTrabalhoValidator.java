/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.validators;

import br.jus.trt23.contavinculada.constraints.VigenciaEstritaAoPostoDeTrabalho;
import br.jus.trt23.contavinculada.entities.Contrato;
import br.jus.trt23.contavinculada.entities.EntidadeGenerica;
import br.jus.trt23.contavinculada.entities.IEscopoRestritoAoContrato;

/**
 *
 * @author j129-9
 */
public class VigenciaEstritaAoPostoDeTrabalhoValidator extends ValidadorGenerico<VigenciaEstritaAoPostoDeTrabalho, EntidadeGenerica>{

    @Override
    public boolean passedCustomValidation() {
        if(null == value) return true;
        if(!(value instanceof IEscopoRestritoAoContrato)) return false;
        IEscopoRestritoAoContrato iEscopoRestritoAoContrato = 
                (IEscopoRestritoAoContrato) value;
        Contrato contrato = iEscopoRestritoAoContrato.getContrato();
        if(null == contrato) return true;
        return value.isVigenteEstritamenteEntre(contrato.getVigenteDesde(), contrato.getVigenteAte());
    }

    
}
