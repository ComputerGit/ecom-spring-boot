package com.at.t.eCommerce.dto; // Adjust the package as per your project structure

public class AuthenticationResponse {
    private String jwt; // JWT token field

    // Constructor
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    // Getter for the JWT token
    public String getJwt() {
        return jwt;
    }

    // Setter for the JWT token
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
