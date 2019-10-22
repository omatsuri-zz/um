/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement_API.API.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author arman
 */
@Entity
@Table(name = "tb_m_application")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a")
    , @NamedQuery(name = "Application.findById", query = "SELECT a FROM Application a WHERE a.id = :id")
    , @NamedQuery(name = "Application.findByName", query = "SELECT a FROM Application a WHERE a.name = :name")
    , @NamedQuery(name = "Application.findByToken", query = "SELECT a FROM Application a WHERE a.token = :token")
    , @NamedQuery(name = "Application.findByBaseUrl", query = "SELECT a FROM Application a WHERE a.baseUrl = :baseUrl")
    , @NamedQuery(name = "Application.findByIsActive", query = "SELECT a FROM Application a WHERE a.isActive = :isActive")})
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "token")
    private String token;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "base_url")
    private String baseUrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application")
    private List<ApplicationRole> applicationRoleList;

    public Application() {
    }

    public Application(Integer id) {
        this.id = id;
    }

    public Application(Integer id, String name, String token, String baseUrl, boolean isActive) {
        this.id = id;
        this.name = name;
        this.token = token;
        this.baseUrl = baseUrl;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public List<ApplicationRole> getApplicationRoleList() {
        return applicationRoleList;
    }

    public void setApplicationRoleList(List<ApplicationRole> applicationRoleList) {
        this.applicationRoleList = applicationRoleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.usermanagement_API.API.entities.Application[ id=" + id + " ]";
    }
    
}
