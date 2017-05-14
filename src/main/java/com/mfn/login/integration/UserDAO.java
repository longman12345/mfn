package com.mfn.login.integration;

import com.mfn.common.UserDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by LPF on 2017/4/4.
 */
public interface UserDAO {
    UserDTO select(UserDTO request) throws SQLException;
    void add(UserDTO request) throws SQLException;
    List<UserDTO> getAll() throws SQLException;
}
