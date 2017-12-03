/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palmigiros.jpa.sessions;

import com.palmigiros.jpa.entities.Clientes;
import com.palmigiros.jpa.entities.Giros;
import com.palmigiros.jpa.entities.Giros_;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


/**
 *
 * @author bratc
 */
@Stateless
public class GirosFacade extends AbstractFacade<Giros> {

    @PersistenceContext(unitName = "PalmiGiros_PalmiGiros_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GirosFacade() {
        super(Giros.class);
    }
    
    /**
     * Este método sirve para buscar ciudad en un autocomplete
     * Además si se pasa el id del departamento en el autocomplete solo 
     * sale las ciudades pertenecientes a ese departamento
    
     * @param idClienteReceptor
     * @return lista de Ciudad
     */
    public List<Giros> findGirosByIdClienteReceptor(Integer idClienteReceptor) {
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Giros> cq = cb.createQuery(Giros.class);
        Root<Giros> giro = cq.from(Giros.class);
        
        Predicate restrictions = cb.conjunction();
        
        if(idClienteReceptor != null){
            restrictions = cb.and(restrictions, 
                    cb.equal(giro.get(Giros_.idClienteReceptor),  new Clientes(idClienteReceptor))
                   // cb.equal(giro.get(Giros_.estado), true)
            );
        }
        
       
        
        cq.where(restrictions);
        cq.orderBy(cb.asc(giro.get(Giros_.idClienteReceptor)));
        TypedQuery<Giros> q = em.createQuery(cq);
        try {
            return q.setMaxResults(10).getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    
}
