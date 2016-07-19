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
public enum EDiasComputados {
    DOMINGOS("Domingos"),
    SABADOS("Sábados"),
    SEGUNDAS_SEXTAS("Segundas às sextas"),
    IGNORAR_FERIADOS("Ignorar feriados");
    private final String nome;

    public String getNome() {
        return nome;
    }
    
    EDiasComputados(String nome){
        this.nome = nome;
    }

    @Override
    public String toString(){
        return getNome();
    }    
    
}
