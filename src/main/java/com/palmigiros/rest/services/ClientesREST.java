/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palmigiros.rest.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.palmigiros.jpa.entities.Clientes;
import com.palmigiros.jpa.sessions.ClientesFacade;

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
@Path("clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientesREST {
     @EJB
    private ClientesFacade clientesEJB;
   
    /**
     * Obtiene todos los clientes 
     *
     * @return lista de clientes
     */
    @GET
   // @RolesAllowed({"ADMIN"})
    public List<Clientes> findAll() {
        return clientesEJB.findAll();
    }
    
     /**
     * Obtiene todos los clientes con rol empleado
     *
     * @return lista de clientes
     */
       @POST
 
    public Response create(Clientes cliente) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
           clientesEJB.create(cliente);
           return Response.status(Response.Status.CREATED).entity(gson.toJson("El cliente se creó correctamente!")).build();

        } catch (Exception e) {
            Logger.getLogger(ClientesREST.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al guardar el cliente!.")).build();
        }
    }
    
    /**
     * Busca cliente por numero de documento
     *
     * @param id
     * @return clientes
     */
    @GET
    @Path("find")
    public Response findClientesByNumDocumento(
            @QueryParam("documento") String documento
    ) {
        System.out.println("DOC"+documento);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
       
            Clientes cliente =  clientesEJB.findClientesByNumDocumento(documento);
            if(cliente != null){
            return Response.status(Response.Status.ACCEPTED).entity(cliente).build();
            } else{
                return Response.status(Response.Status.NOT_FOUND).entity(gson.toJson("No se encuentra el cliente")).build();
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
    public Clientes findById(@PathParam("id") Integer id) {
        return clientesEJB.find(id);
    }
    /**
     * Actualiza empleados por su id
     *
     * @param id
     * @return empleados
     */
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, Clientes clientes){
        clientesEJB.edit(clientes);
    }
}
