/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.entities;

import br.jus.trt23.contavinculada.constraints.Diferida;
import br.jus.trt23.contavinculada.constraints.VigenciaValida;
import br.jus.trt23.contavinculada.listeners.EntidadeListener;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author j129-9
 */
@MappedSuperclass
@Getter
@Setter
@RequiredArgsConstructor
@VigenciaValida
@EntityListeners(EntidadeListener.class)
public abstract class EntidadeGenerica implements Serializable, Comparable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID" )      
    protected Long id;

    @NotNull(message = "Início da vigência não pode ser nulo.")
    private LocalDate vigenteDesde;

    private LocalDate vigenteAte;

    @NotNull(message = "Criação não pode ser nula.", groups = Diferida.class)
    private LocalDateTime criadoEm;

    private LocalDateTime destruidoEm;

    protected static String[] uniqueIndex;
    
    public abstract String getNomeNatural();

    public static Object getFieldValue(Object bean, String fieldName) {
        try {
            BeanInfo info = Introspector.getBeanInfo(bean.getClass());
            for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                if (pd.getName().equals(fieldName)) {
                    return pd.getReadMethod().invoke(bean);
                }
            }
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(EntidadeGenerica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        //uma instância não pode ser igual a nulo
        if (obj == null) {
            return false;
        }

        //a mesma instância com dois ponteiros é igual
        if (this == obj) {
            return true;
        }

        //se os objetos não são da mesma classe não são iguais
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        }

        EntidadeGenerica eg = (EntidadeGenerica) obj;

        //se ambos os objetos são persistentes a igualdade pode ser testada
        //pelos ids
        if (null != getId()) {
            return getId().equals(eg.getId());
        }

        //o objeto corrente não é persistente, mas o testado é.  não são iguais.
        if (null != eg.getId()) {
            return false;
        }

        //ambos os objetos não são persistentes.  igualdade verificada
        //pelos campos de indice unico.
        return compareByIndex(eg);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        try {
            hash = 53 * hash + Objects.hashCode(this.id);
            hash = 53 * hash + Objects.hashCode(this.vigenteDesde);
            hash = 53 * hash + Objects.hashCode(this.vigenteAte);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Error {0} on hashCode method of {1}", e.getMessage()), new Object[]{this, this.getClass().getName()});
        }
        return hash;
    }

    @Override
    public int compareTo(Object o) {
        try {
            if (null == o) {
                return -1;
            }
            if (o instanceof EntidadeGenerica) {
                EntidadeGenerica eg = (EntidadeGenerica) o;
                if (null == getId()) {
                    return -1;
                }
                return getId().compareTo(eg.getId());
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    private boolean compareByIndex(EntidadeGenerica eg) {
        Class c = this.getClass();
        Field f1;
        Object o1, o2;
        for (String fieldName : uniqueIndex) {
            try {
                f1 = c.getField(fieldName);
                o1 = f1.get(this);
                o2 = f1.get(eg);
                if (!o1.equals(o2)) {
                    return false;
                }

            } catch (NoSuchFieldException | SecurityException |
                    IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(EntidadeGenerica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
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
    public Boolean isVigentePlenamenteEntre(LocalDate inicio, LocalDate fim) {
        return (vigenteDesde.compareTo(inicio) <= 0 && (null == vigenteAte
                || vigenteAte.compareTo(fim) >= 0));
    }

    //retorna verdadeiro se a entidade tiver vigencia que abranja a data 
    //informada
    public Boolean isVigenteParcialmente(LocalDate inicio, LocalDate fim) {
        //início posterior
        if ((vigenteDesde.compareTo(fim) <= 0) && (null == vigenteAte || vigenteAte.compareTo(inicio) >= 0)) {
            return true;
        } else {
        }
        return false;
    }

    public Boolean isVigenteEstritamenteEntre(LocalDate inicio, LocalDate fim) {
        //o inicio do objeto corrente deve ser igual ou superior ao inicio do 
        //período testado
        if (vigenteDesde.compareTo(inicio) >= 0) {
            //se o período testado estiver em aberto, fim==null, retorna true,
            //contudo, se posteriormente for definida uma vigência final para 
            //o objeto pai, poderá gerar inconsistência            
            if (null == fim) {
                return true;
            }
            //se o período de teste tiver uma data de término definida,
            //mas o objeto corrente não a tiver, acusa incompatibilidade de 
            //vigência
            if (null == vigenteAte) {
                return false;
            }

            //se o objeto corrente possui vigência até a data final do período
            //de teste retorna verdadeiro, senão, o contrário
            return vigenteAte.compareTo(fim) <= 0;
        }
        return false;
    }

    public void setVigenciaIgual(EntidadeGenerica eg) {
        setVigenteDesde(eg.getVigenteDesde());
        setVigenteAte(eg.getVigenteAte());
    }
}
