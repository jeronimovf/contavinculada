/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.enums;

/**
 *
 * @author j129-9
 */
public enum EFeriadoEscopo {
    NACIONAL("Nacional"),
    ESTADUAL("Estadual"),
    MUNICIPAL("Municipal"),
    REGIMENTAL("Regimental");
    private final String nome;

    public String getNome() {
        return nome;
    }
    
    EFeriadoEscopo(String nome){
        this.nome = nome;
    }

    @Override
    public String toString(){
        return getNome();
    }    
}
