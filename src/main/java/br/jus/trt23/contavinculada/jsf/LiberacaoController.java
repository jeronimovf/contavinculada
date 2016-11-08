/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Liberacao;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author j129-9
 */
@Named
@Dependent
public class LiberacaoController extends AbstractController<Liberacao> {


    public LiberacaoController() {
        super(Liberacao.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "Liberacao";
    }
}
