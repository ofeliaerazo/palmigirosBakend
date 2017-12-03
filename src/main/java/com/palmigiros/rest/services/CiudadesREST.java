/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palmigiros.rest.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.palmigiros.jpa.entities.Ciudades;
import com.palmigiros.jpa.sessions.CiudadesFacade;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bratc
 */
@Stateless
@Path("ciudades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CiudadesREST {
     @EJB
    private CiudadesFacade ciudadesEJB;
   
    /**
     * Obtiene todos los ciudades 
     *
     * @return lista de ciudades
     */
    @GET
   // @RolesAllowed({"ADMIN"})
    public List<Ciudades> findAll() {
        return ciudadesEJB.findAll();
    }
    
     /**
     * Obtiene todos los ciudades con rol empleado
     *
     * @return lista de ciudades
     */
       @POST
 
    public Response create(Ciudades ciudad) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
           ciudadesEJB.create(ciudad);
           return Response.status(Response.Status.CREATED).entity(gson.toJson("El ciudad se creó correctamente!")).build();

        } catch (Exception e) {
            Logger.getLogger(CiudadesREST.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al guardar el ciudad!.")).build();
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
    public Ciudades findById(@PathParam("id") Integer id) {
        return ciudadesEJB.find(id);
    }
    /**
     * Actualiza empleados por su id
     *
     * @param id
     * @return empleados
     */
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, Ciudades ciudades){
        ciudadesEJB.edit(ciudades);
    }
}
