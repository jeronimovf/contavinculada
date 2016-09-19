//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class RAT extends EntidadeGenerica {
    protected final static String[] uniqueIndex = {"percentual"};
    @Getter
    @Setter
    @NotEmpty
    private String percentual;

    @Getter
    @Setter
    @OneToMany(mappedBy = "rat",cascade = {CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.MERGE})
    private List<RATItem> itens;

    public RAT() {
        itens=new ArrayList<>();
    }
    
    public void addItem(RATItem item){
        itens.add(item);
        item.setRat(this);        
    }
    
    @Override
    public String toString() {
        return getPercentual();
    }

    
}