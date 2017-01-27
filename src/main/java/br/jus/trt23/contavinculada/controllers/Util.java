/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.controllers;

import br.jus.trt23.contavinculada.entities.Proad;
import java.io.Serializable;
import java.time.LocalDate;
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
        //a string não necessita de compactação ou a compactação não é viável
        if (null == str || ncaracteres <= 0 || str.length() <= ncaracteres) {
            return str;
        } else {
            String[] aResultado = str.split("\\s");
            int ultimoIndice = aResultado.length - 1;
            int primeiroTamanho = aResultado[0].length();
            int ultimoTamanho = aResultado[ultimoIndice].length();
            switch (aResultado.length) {
                //a string tem apenas um token
                case 1:
                    return str.substring(0, ncaracteres - 3).concat("...");
                case 2:
                    espacoLivre = ncaracteres - primeiroTamanho;
                    //a string tem dois tokens, mas não há espaço para compactar
                    //o segundo
                    if (espacoLivre <= 3) {
                        return str.substring(0, ncaracteres - 3).concat("...");
                    } //a string tem dois tokens devendo constar o primeiro
                    //na íntegra e os últimos caracteres do último token
                    //até o limite
                    else {
                        return aResultado[0].concat("...").concat(aResultado[ultimoIndice].substring((espacoLivre + 3) - ultimoTamanho));
                    }
                default:
                    espacoLivre = ncaracteres - aResultado[0].length();
                    //a string tem mais de dois tokens, mas não há espaço para
                    //compactar os outros tokens
                    if (espacoLivre <= 3) {
                        return str.substring(0, ncaracteres - 3).concat("...");
                    } //a string tem dois tokens devendo constar o primeiro
                    //na íntegra e os últimos caracteres do último token
                    //até o limite
                    else if (espacoLivre <= ultimoTamanho) {
                        return aResultado[0].concat("...").concat(aResultado[ultimoIndice].substring((espacoLivre + 3) - ultimoTamanho));
                    } //há espaco para compactar os tokens intermediários 
                    //mantendo-se os tokens dos extremos
                    else {
                        //calcula o espaço livre após a extração do primeiro
                        //e do último token além da reticências
                        espacoLivre = ncaracteres - (aResultado[0].length() + 3 + aResultado[aResultado.length - 1].length());
                        if (espacoLivre <= 1) {
                            return aResultado[0].concat("...").concat(aResultado[aResultado.length - 1]);
                        } else {
                            //passa a string intermediaria para extração de iniciais
                            String iniciais = extraiIniciais(Arrays.copyOfRange(aResultado, 1, (aResultado.length - 1)));
                            if (iniciais.length() <= espacoLivre) {
                                return aResultado[0].concat(" ").concat(iniciais).concat("...").concat(aResultado[aResultado.length - 1]);
                            } else {
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
    
    //para entender a diferenca entre as funções que comparam períodos de 
    //vigência: 
    //isVigentePlenamenteEntre: não interessa se o início da vigência  
    //do objeto corrente é anterior a do período de teste ou se o términdo da
    //vigência do objeto seja posterior ao final do período, mas se, em todo
    //o período de teste, o objeto esteve vigente.
    //Se periodo está contido o.vigencia
    //isVigenteParcialmente: se o objeto corrente tiver sua vigência coincidindo
    //com qualquer parte do período teste, retorna verdadeiro.  
    //Se existe (o.vigencia intersecção periodo)
    //isVigenteEstritamenteEntre: a vigência do objeto corrente deve estar
    //compreendida no período de teste.
    //Se o.vigencia está contido periodo.
    //retorna verdadeiro se a entidade tiver vigencia em todo o período
    //informado
    public Boolean isP1VigentePlenamenteEmP2(LocalDate p1Inicio, LocalDate p1Fim,
            LocalDate p2Inicio, LocalDate p2Fim) {
        return (p1Inicio.compareTo(p2Inicio) <= 0 && (null == p1Fim
                || p1Fim.compareTo(p2Fim) >= 0));
    }

    //retorna verdadeiro se a entidade tiver vigencia que abranja a data 
    //informada
    public Boolean isP1VigenteParcialmenteEmP2(LocalDate p1Inicio, LocalDate p1Fim,
            LocalDate p2Inicio, LocalDate p2Fim) {
        //início posterior
        if ((p1Inicio.compareTo(p2Fim) <= 0) && (null == p1Fim || p1Fim.compareTo(p2Inicio) >= 0)) {
            return true;
        } else {
        }
        return false;
    }

    public Boolean isP1VigenteEstritamenteEmP2(LocalDate p1Inicio, LocalDate p1Fim,
            LocalDate p2Inicio, LocalDate p2Fim) {
        //o inicio do objeto corrente deve ser igual ou superior ao inicio do 
        //período testado
        if (p1Inicio.compareTo(p2Inicio) >= 0) {
            //se o período testado estiver em aberto, fim==null, retorna true,
            //contudo, se posteriormente for definida uma vigência final para 
            //o objeto pai, poderá gerar inconsistência            
            if (null == p2Fim) {
                return true;
            }
            //se o período de teste tiver uma data de término definida,
            //mas o objeto corrente não a tiver, acusa incompatibilidade de 
            //vigência
            if (null == p1Fim) {
                return false;
            }

            //se o objeto corrente possui vigência até a data final do período
            //de teste retorna verdadeiro, senão, o contrário
            return p1Fim.compareTo(p2Fim) <= 0;
        }
        return false;
    }

}
