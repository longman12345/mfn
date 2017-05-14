package com.mfn.um.dto;

/**
 * Created by LPF on 2017/4/22.
 */
public class RegisterRequestDTO {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterRequestDTO{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
