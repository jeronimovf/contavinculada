/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.sessions;

import br.jus.trt23.contavinculada.entities.Fiscal;
import br.jus.trt23.contavinculada.entities.Servidor;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 *
 * @author j129-9
 */
@Stateless
public class FiscalFacade extends AbstractFacade<Fiscal> {

    @Inject
    private EntityManager em;
    


    public FiscalFacade() {
        super(Fiscal.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Fiscal> complete(String criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Fiscal findByServidorMatricula(String login) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Fiscal> cq = cb.createQuery(Fiscal.class);
        Root<Fiscal> c = cq.from(Fiscal.class);
        Join<Fiscal,Servidor> s = c.join("servidor");
        cq.select(c).where(
                cb.equal(cb.upper(s.get("matricula")),login.toUpperCase())
        ).distinct(true);
        
        List<Fiscal> fiscais = getEntityManager().createQuery(cq).getResultList();        
        
        if(fiscais.size()==1) {
            return fiscais.get(0);
        }
        else{
            return null;
        }
    }

    
    
    
}
