package com.mfn.common;

/**
 * Created by LPF on 2017/2/25.
 */
public class UserDTO {
    private String userId;
    private String username;
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
        return "UserDTO{" +
            "userId='" + userId + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
