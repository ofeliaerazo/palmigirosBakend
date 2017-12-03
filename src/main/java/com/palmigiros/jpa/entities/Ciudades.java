/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palmigiros.jpa.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bratc
 */
@Entity
@Table(name = "ciudades")
@XmlRootElement
public class Ciudades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "ciudad")
    private String ciudad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCiudadEmisor")
    private List<Giros> girosListEmisor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCiudadReceptor")
    private List<Giros> girosListReceptor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCiudad")
    private List<Clientes> clientesList;
    @JoinColumn(name = "idDepartamento", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Departamentos idDepartamento;

    public Ciudades() {
    }

    public Ciudades(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    @XmlTransient
    public List<Giros> getGirosListEmisor() {
        return girosListEmisor;
    }

    public void setGirosListEmisor(List<Giros> girosListEmisor) {
        this.girosListEmisor = girosListEmisor;
    }
    @XmlTransient
    public List<Giros> getGirosListReceptor() {
        return girosListReceptor;
    }

    public void setGirosListReceptor(List<Giros> girosListReceptor) {
        this.girosListReceptor = girosListReceptor;
    }

   
    @XmlTransient
    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    public Departamentos getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamentos idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    

    @Override
    public String toString() {
        return "com.palmigiros.jpa.entities.Ciudades[ id=" + id + " ]";
    }
    
}
