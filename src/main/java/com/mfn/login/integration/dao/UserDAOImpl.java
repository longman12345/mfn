package com.mfn.login.integration.dao;

import com.mfn.common.UserDTO;
import com.mfn.login.integration.UserDAO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
/**
 * Created by LPF on 2017/4/4.
 */
@Repository("com.mfn.login.integration.UserDAO")
public class UserDAOImpl extends BaseDAO implements UserDAO {

    @Override
    @Cacheable(value="select", sync=true)
    public UserDTO select(UserDTO request) throws SQLException {
        try (SqlSession session = this.getSession()){
            UserDAO mapper = session.getMapper(UserDAO.class);
            return mapper.select(request);
        }
    }

    @Override
    public void add(UserDTO request) throws SQLException {
        try (SqlSession session = this.getSession()){
            UserDAO mapper = session.getMapper(UserDAO.class);
            mapper.add(request);
        }
    }

    @Override
    public List<UserDTO> getAll() throws SQLException {
        return null;
    }
}
