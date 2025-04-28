package com.Travelbnb.paylod;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class JwtToken_dto {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String type;
    private String token;
}
