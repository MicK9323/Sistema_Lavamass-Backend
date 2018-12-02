package com.mca.apps.lavamassapirest.models.model;

import com.mca.apps.lavamassapirest.utils.G;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class RoleDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROL_ID")
    private int id;

    @Column(name = "ROL_NAME")
    private String roleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return G.capitalize(roleName);
    }

    public void setRoleName(String roleName) {
        this.roleName = G.upCase(roleName);
    }
}
