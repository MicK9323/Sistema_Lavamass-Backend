package com.mca.apps.lavamassapirest.payloads.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    private String dniOrEmail;

    @NotBlank
    private String password;

    public String getDniOrEmail() {
        return dniOrEmail;
    }

    public void setDniOrEmail(String dniOrEmail) {
        this.dniOrEmail = dniOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
