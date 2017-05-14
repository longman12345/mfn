package com.mfn.login.integration.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by LPF on 2017/4/8.
 */
@Repository("com.mfn.login.integration.dao.BaseDAO")
public abstract class BaseDAO {
    @Autowired
    @Qualifier("sqlSessionFactory")
    private SqlSessionFactory sessionFactory;

    public SqlSession getSession() {
        return sessionFactory.openSession();
    }
}
