/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palmigiros.rest.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.palmigiros.jpa.entities.Giros;
import com.palmigiros.jpa.sessions.GirosFacade;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
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
@Path("giros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GirosREST {
     @EJB
    private GirosFacade girosEJB;
   
    /**
     * Obtiene todos los giros 
     *
     * @return lista de giros
     */
    @GET
   // @RolesAllowed({"ADMIN"})
    public List<Giros> findAll() {
        return girosEJB.findAll();
    }
    
     /**
     * Obtiene todos los giros con rol empleado
     *
     * @return lista de giros
     */
       @POST
 
    public Response create(Giros giro) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
           giro.setFecha(null);
           girosEJB.create(giro);
           return Response.status(Response.Status.CREATED).entity(gson.toJson("El giro se creó correctamente!")).build();

        } catch (Exception e) {
            Logger.getLogger(GirosREST.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al guardar el giro!.")).build();
        }
    }
    
    /**
     * Busca un giro por idClienteReceptor
     *
     * @param idClienteReceptor
     * @return empleados
     */
    @GET
    @Path("findGiro")
    public List<Giros> findGirosByIdClienteReceptor(
            @QueryParam("idClienteReceptor") Integer idClienteReceptor) {
        return girosEJB.findGirosByIdClienteReceptor(idClienteReceptor);
    }
  
     /**
     * Busca empleados por su id
     *
     * @param id
     * @return empleados
     */
    @GET
    @Path("{id}")
    public Giros findById(@PathParam("id") Integer id) {
        return girosEJB.find(id);
    }
    /**
     * Actualiza empleados por su id
     *
     * @param id
     * @return empleados
     */
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, Giros giros){
        girosEJB.edit(giros);
    }
}
