/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palmigiros.jpa.sessions;

import com.palmigiros.jpa.entities.Ciudades;
import com.palmigiros.jpa.entities.Ciudades_;
import com.palmigiros.jpa.entities.Departamentos;

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
public class CiudadesFacade extends AbstractFacade<Ciudades> {

    @PersistenceContext(unitName = "PalmiGiros_PalmiGiros_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadesFacade() {
        super(Ciudades.class);
    }
    /**
     * Este método sirve para buscar ciudades en un autocomplete
     * Además si se pasa el id del departamento en el autocomplete solo 
     * sale las ciudadeses pertenecientes a ese departamento
     * @param query
     * @param idDepartamento
     * @return lista de Ciudades
     */
    public List<Ciudades> findCiudadesByNombre(String query, Integer idDepartamento) {
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Ciudades> cq = cb.createQuery(Ciudades.class);
        Root<Ciudades> ciudades = cq.from(Ciudades.class);
        
        Predicate restrictions = cb.conjunction();
        
        if(idDepartamento != null){
            restrictions = cb.and(restrictions, cb.equal(ciudades.get(Ciudades_.idDepartamento), new Departamentos(idDepartamento)));
        }
        
        if(query != null){
            restrictions = cb.and(restrictions, cb.like(ciudades.get(Ciudades_.ciudad), "%" + query + "%"));
        }
        
        cq.where(restrictions);
        cq.orderBy(cb.asc(ciudades.get(Ciudades_.ciudad)));
        TypedQuery<Ciudades> q = em.createQuery(cq);
        try {
            return q.setMaxResults(10).getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
