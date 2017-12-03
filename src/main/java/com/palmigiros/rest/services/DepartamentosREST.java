/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palmigiros.rest.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.palmigiros.jpa.entities.Departamentos;
import com.palmigiros.jpa.sessions.DepartamentosFacade;

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
@Path("departamentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DepartamentosREST {
     @EJB
    private DepartamentosFacade departamentosEJB;
   
    /**
     * Obtiene todos los departamentos 
     *
     * @return lista de departamentos
     */
    @GET
   // @RolesAllowed({"ADMIN"})
    public List<Departamentos> findAll() {
        return departamentosEJB.findAll();
    }
    
     /**
     * Obtiene todos los departamentos con rol empleado
     *
     * @return lista de departamentos
     */
       @POST
 
    public Response create(Departamentos departamento) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
           departamentosEJB.create(departamento);
           return Response.status(Response.Status.CREATED).entity(gson.toJson("El departamento se creó correctamente!")).build();

        } catch (Exception e) {
            Logger.getLogger(DepartamentosREST.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al guardar el departamento!.")).build();
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
    public Departamentos findById(@PathParam("id") Integer id) {
        return departamentosEJB.find(id);
    }
    /**
     * Actualiza empleados por su id
     *
     * @param id
     * @return empleados
     */
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, Departamentos departamentos){
        departamentosEJB.edit(departamentos);
    }
}
