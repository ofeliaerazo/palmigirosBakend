/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palmigiros.rest.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.palmigiros.jpa.entities.Roles;
import com.palmigiros.jpa.entities.Usuarios;
import com.palmigiros.jpa.sessions.UsuariosFacade;
import com.palmigiros.rest.auth.DigestUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bratc
 */
@Stateless
@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuariosREST {

   
    @EJB
    private UsuariosFacade usuariosEJB;
   
    /**
     * Obtiene todos los usuarios 
     *
     * @return lista de usuarios
     */
    @GET
   // @RolesAllowed({"ADMIN"})
    public List<Usuarios> findAll() {
        return usuariosEJB.findAll();
    }
    
     /**
     * Obtiene todos los usuarios con rol empleado
     *
     * @return lista de usuarios
     */
     @GET
   // @RolesAllowed({"ADMIN"})
    @Path("empleados")
    public List<Usuarios> findAllUsuariosByRol() {
        return usuariosEJB.findAllUsuariosByRol("EMPLEADO");
    }
    @POST
   // @RolesAllowed({"ADMIN","EMPLEADO"})
    @Path("administradores")
    public Response create(Usuarios usuario) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            if (usuariosEJB.findUsuariosByEmail(usuario.getEmail()) == null) {
                if (usuariosEJB.findUsuarioByNumDocumento(usuario.getNumDocumento()) == null) {
                    
                    usuario.setPassword(DigestUtil.cifrarPassword(usuario.getPassword()));
                    usuario.setRolesList(new ArrayList<Roles>());
                    usuario.getRolesList().add(new Roles("ADMIN"));
                    usuariosEJB.create(usuario);
                    return Response.status(Response.Status.CREATED).entity(gson.toJson("El usuario administrador se creó correctamente!")).build();

                
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El número de documento ya se encuentra registrado!.")).build();
                }
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El email ya se encuentra registrado!.")).build();
            }
        } catch (Exception e) {
            Logger.getLogger(UsuariosREST.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al guardar el usuario!.")).build();
        }
    }
    
   
   // @RolesAllowed({"ADMIN","EMPLEADO"})
    @POST
    @Path("empleados")
    public Response createEmpleado(Usuarios usuario) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            if (usuariosEJB.findUsuariosByEmail(usuario.getEmail()) == null) {
                if (usuariosEJB.findUsuarioByNumDocumento(usuario.getNumDocumento()) == null) {
                    
                    usuario.setPassword(DigestUtil.cifrarPassword(usuario.getPassword()));
                    usuario.setRolesList(new ArrayList<Roles>());
                    usuario.getRolesList().add(new Roles("EMPLEADO"));
                    usuariosEJB.create(usuario);
                    return Response.status(Response.Status.CREATED).entity(gson.toJson("El usuario empleado se creó correctamente!")).build();

                
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El número de documento ya se encuentra registrado!.")).build();
                }
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El email ya se encuentra registrado!.")).build();
            }
        } catch (Exception e) {
            Logger.getLogger(UsuariosREST.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al guardar el usuario!.")).build();
        }
    }

     /**
     * Busca empleados por su id
     *
     * @param id
     * @return empleados
     */
    @GET
    @Path("{id}")
    public Usuarios findById(@PathParam("id") Integer id) {
        return usuariosEJB.find(id);
    }
    /**
     * Actualiza empleados por su id
     *
     * @param id
     * @return empleados
     */
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, Usuarios usuarios){
        usuariosEJB.edit(usuarios);
    }
    
}
