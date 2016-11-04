/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Proad;
import java.io.Serializable;
import java.util.Arrays;
import java.util.ResourceBundle;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author j129-9
 */
@Named
@ApplicationScoped
@RequiredArgsConstructor
public class Util implements Serializable {

    public String compactaString(final String str, final int ncaracteres) {
        int espacoLivre;
        System.out.println("String: " + str);
        //a string não necessita de compactação ou a compactação não é viável
        if (null == str || ncaracteres <= 0 || str.length() <= ncaracteres) {
            System.out.println("Return: " + 1);
            return str;
        } else {
            String[] aResultado = str.split("\\s");
            int ultimoIndice = aResultado.length - 1;
            int primeiroTamanho = aResultado[0].length();
            int ultimoTamanho = aResultado[ultimoIndice].length();
            switch (aResultado.length) {
                //a string tem apenas um token
                case 1:
                    System.out.println("Return: " + 2);
                    return str.substring(0, ncaracteres - 3).concat("...");
                case 2:
                    espacoLivre = ncaracteres - primeiroTamanho;
                    //a string tem dois tokens, mas não há espaço para compactar
                    //o segundo
                    if (espacoLivre <= 3) {
                        System.out.println("Return: " + 3);
                        return str.substring(0, ncaracteres - 3).concat("...");
                    } //a string tem dois tokens devendo constar o primeiro
                    //na íntegra e os últimos caracteres do último token
                    //até o limite
                    else {
                        System.out.println("Return: " + 4);
                        return aResultado[0].concat("...").concat(aResultado[ultimoIndice].substring((espacoLivre + 3) - ultimoTamanho));
                    }
                default:
                    espacoLivre = ncaracteres - aResultado[0].length();
                    //a string tem mais de dois tokens, mas não há espaço para
                    //compactar os outros tokens
                    if (espacoLivre <= 3) {
                        System.out.println("Return: " + 5);
                        return str.substring(0, ncaracteres - 3).concat("...");
                    } //a string tem dois tokens devendo constar o primeiro
                    //na íntegra e os últimos caracteres do último token
                    //até o limite
                    else if (espacoLivre <= ultimoTamanho) {
                        System.out.println("Return: " + 6);
                        return aResultado[0].concat("...").concat(aResultado[ultimoIndice].substring((espacoLivre + 3) - ultimoTamanho));
                    } //há espaco para compactar os tokens intermediários 
                    //mantendo-se os tokens dos extremos
                    else {
                        //calcula o espaço livre após a extração do primeiro
                        //e do último token além da reticências
                        espacoLivre = ncaracteres - (aResultado[0].length() + 3 + aResultado[aResultado.length - 1].length());
                        if (espacoLivre <= 1) {
                            System.out.println("Return: " + 7);
                            return aResultado[0].concat("...").concat(aResultado[aResultado.length - 1]);
                        } else {
                            //passa a string intermediaria para extração de iniciais
                            String iniciais = extraiIniciais(Arrays.copyOfRange(aResultado, 1, (aResultado.length - 1)));
                            if (iniciais.length() <= espacoLivre) {
                                System.out.println("Return: " + 8);
                                return aResultado[0].concat(" ").concat(iniciais).concat("...").concat(aResultado[aResultado.length - 1]);
                            } else {
                                System.out.println("Return: " + 9);
                                return aResultado[0].concat(" ").concat(iniciais.substring(0, espacoLivre)).concat("...").concat(aResultado[aResultado.length - 1]);
                            }
                        }
                    }
            }
        }
    }

    public String extraiIniciais(String[] aString) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aString.length; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(aString[i].substring(0, 1));
        }
        return sb.toString();
    }

    public String getProadUrlParaOProcesso(final Proad processo) {
        ResourceBundle bundle = ResourceBundle.getBundle("configs");
        String urlBase = bundle.getString("proad_url");
        String protocoloNumeroFieldName
                = bundle.getString("proad_protocoloNumeroFieldName");
        String protocoloAnoFieldName
                = bundle.getString("proad_protocoloAnoFieldName");
        if (null != processo && null != processo.getProtocoloNumero()
                && null != processo.getProtocoloAno() && null != urlBase
                && null != protocoloAnoFieldName && null != protocoloNumeroFieldName) {
            StringBuilder sb = new StringBuilder();
            sb.append(urlBase);
            sb.append("?");
            sb.append(protocoloNumeroFieldName);
            sb.append("=");
            sb.append(processo.getProtocoloNumero());
            sb.append("&");
            sb.append(protocoloAnoFieldName);
            sb.append("=");
            sb.append(processo.getProtocoloAno());
            sb.append("&evento=y");
            return sb.toString();
        }
        return null;
    }
}
