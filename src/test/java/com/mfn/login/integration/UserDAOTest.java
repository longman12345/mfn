package com.mfn.login.integration;

import com.mfn.common.UserDTO;
import com.mfn.server.MfnServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by LPF on 2017/4/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MfnServer.class, properties = "classpath:application.properties")
public class UserDAOTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserDAOTest.class);

    @Autowired
    @Qualifier("com.mfn.login.integration.UserDAO")
    UserDAO userDAO;

    @Test
    public void select() throws Exception {
        UserDTO request = new UserDTO();
        request.setUsername("aa");
        request.setPassword("aa");
        UserDTO response = userDAO.select(request);
        LOG.info(response.toString());
    }

    @Test
    public void add() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

}