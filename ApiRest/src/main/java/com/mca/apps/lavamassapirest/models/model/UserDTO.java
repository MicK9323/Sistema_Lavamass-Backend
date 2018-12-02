package com.mca.apps.lavamassapirest.models.model;

import com.mca.apps.lavamassapirest.utils.G;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REL_ID")
    private int id;

    @Column(name = "COD")
    private String codUser;

    @Column(name = "DNI", updatable = false)
    private String dni;

    @Column(name = "PASSWD")
    private String passwd;

    @Column(name = "NAME", updatable = false)
    private String name;

    @Column(name = "LASTNAME", updatable = false)
    private String lastName;

    @Column(name = "TELF")
    private String telf;

    @Column(name = "ADRESS")
    private String adress;

    @Column(name = "EMAIL")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "REL_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROL_ID", referencedColumnName = "ROL_ID"))
    private Set<RoleDTO> roles = new HashSet<>();

    @Column(name = "REGISTRY_DATE", updatable = false)
    private Timestamp registryDate;

    public UserDTO() {
    }

    public UserDTO(String dni, String passwd, String name, String lastName, String telf, String adress, String email) {
        this.dni = dni;
        this.passwd = passwd;
        this.name = name;
        this.lastName = lastName;
        this.telf = telf;
        this.adress = adress;
        this.email = email;
    }

    @PrePersist
    private void prePersist() {
        codUser = G.buildCodUser(dni, lastName);
        registryDate = G.getTimestamp();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodUser() {
        return codUser;
    }

    public void setCodUser(String codUser) {
        this.codUser = codUser;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return G.capitalize(name);
    }

    public void setName(String name) {
        this.name = G.upCase(name);
    }

    public String getLastName() {
        return G.capitalize(lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = G.upCase(lastName);
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public Timestamp getRegistryDate() {
        return registryDate;
    }

    public void setRegistryDate(Timestamp registryDate) {
        this.registryDate = registryDate;
    }
}
