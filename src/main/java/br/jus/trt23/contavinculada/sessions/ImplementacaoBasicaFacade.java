/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.sessions;

import br.jus.trt23.contavinculada.entities.EntidadeGenerica;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 *
 * @author j129-9
 */
@Stateless
public class ImplementacaoBasicaFacade extends AbstractFacade<EntidadeGenerica> {

    @Inject
    private EntityManager em;

    public ImplementacaoBasicaFacade() {
        super(EntidadeGenerica.class);
    }

    public boolean eVigenciaUnicaNoContexto(SimpleEntry<String, EntidadeGenerica> contexto,
            EntidadeGenerica value) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<?> c = cq.from(value.getClass());
        Join<?, ?> d = c.join(contexto.getKey());

        cq.select(cb.count(c.get(contexto.getKey())));

        //só pode haver uma vigência em aberto
        if (null == value.getVigenteAte()) {

        } else {
            cq.where(
                    cb.equal(c.get(contexto.getKey()), contexto.getValue())
            );
            vigenteParcialmentePredicado(cq, c, value.getVigenteDesde(), value.getVigenteAte());
        }
        Query q=getEntityManager().createQuery(cq);
        Long nRegistros = (Long)q.getSingleResult();
        return nRegistros<=0;
    }

    @Override
    public List<EntidadeGenerica> complete(String criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
