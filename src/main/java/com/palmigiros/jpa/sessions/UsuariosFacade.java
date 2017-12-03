/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palmigiros.jpa.sessions;

import com.palmigiros.jpa.entities.Roles;
import com.palmigiros.jpa.entities.Roles_;
import com.palmigiros.jpa.entities.Usuarios;
import com.palmigiros.jpa.entities.Usuarios_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;

/**
 *
 * @author bratc
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "PalmiGiros_PalmiGiros_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    public Usuarios findUsuariosByEmail(String email) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
        Root<Usuarios> usuario = cq.from(Usuarios.class);
        cq.where(cb.equal(usuario.get(Usuarios_.email), email));
        TypedQuery<Usuarios> q = getEntityManager().createQuery(cq);
        try {
            return (Usuarios) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
   
    
    /**
     * Busca usuario por numDocumento
     *
     * @param numDocumento
     * @return Usuario
     */
    public Usuarios findUsuarioByNumDocumento(String numDocumento) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
        Root<Usuarios> usuario = cq.from(Usuarios.class);
        cq.where(cb.equal(usuario.get(Usuarios_.numDocumento), numDocumento));
        TypedQuery<Usuarios> q = getEntityManager().createQuery(cq);
        try {
            return (Usuarios) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }
    /**
     * Busca usuario por rol
     *
     * @param rol
     * @return Usuarios
     */
    public List<Usuarios> findAllUsuariosByRol(String rol) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
        Root<Roles> rootRoles = cq.from(Roles.class);
        cq.where(cb.equal(rootRoles.get(Roles_.id), rol));
        ListJoin<Roles,Usuarios> joinRoles = rootRoles.join(Roles_.usuariosList);
        CriteriaQuery<Usuarios> cqq = cq.select(joinRoles);
        
        TypedQuery<Usuarios> q = getEntityManager().createQuery(cq);
        try {
            return  q.getResultList();
       
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    
}
