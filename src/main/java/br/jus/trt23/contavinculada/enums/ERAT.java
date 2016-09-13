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
public enum ERAT {
    PORCENTO1("1%"),
    PORCENTO2("2%"),
    PORCENTO3("3%");
    private final String nome;

    public String getNome() {
        return nome;
    }
    
    ERAT(String nome){
        this.nome = nome;
    }

    @Override
    public String toString(){
        return getNome();
    }    
}
