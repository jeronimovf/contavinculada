//
// This file was generated by the JPA Modeler
//
package br.jus.trt23.contavinculada.entities;

import br.jus.trt23.contavinculada.constants.Constantes;
import br.jus.trt23.contavinculada.constraints.VigenciaEstritaAoContrato;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = Constantes.SCHEMA)
@SequenceGenerator(name = "ID", sequenceName = "ENCARGOALIQUOTA_SEQ", allocationSize = 1, schema=Constantes.SCHEMA)
@VigenciaEstritaAoContrato
public class EncargoAliquota extends EntidadeGenerica implements IEscopoRestritoAoContrato{
    protected final static String[] uniqueIndex = {"contrato","encargo","aliquota"};
    @Getter
    @Setter
    @ManyToOne
    private Encargo encargo;

    @Getter
    @Setter
    private double aliquota;

    @Getter
    @Setter
    @ManyToOne
    private Contrato contrato;

    public EncargoAliquota() {

    }

    @Override
    public String getNomeNatural() {
        return "Alíquota";
    }
}
