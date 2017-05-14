package com.mfn.login.controller;

import com.mfn.common.ErrorStatus;
import com.mfn.common.MfnBaseController;
import com.mfn.common.MfnContextUtils;
import com.mfn.common.UserDTO;
import com.mfn.login.dto.LoginRequestDTO;
import com.mfn.login.dto.LoginResponseDTO;
import com.mfn.login.integration.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LPF on 2017/4/4.
 */
@Controller
public class LoginController extends MfnBaseController{

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    @Qualifier("com.mfn.login.integration.UserDAO")
    UserDAO userDAO;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    LoginResponseDTO login(@RequestBody LoginRequestDTO request,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) {
        LOG.info("{} LoginController.login request: {}", MfnContextUtils.getSeq(), request.toString());
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(request.getUsername());
        userDTO.setPassword(request.getPassword());
        UserDTO selectResponse;
        try {
            selectResponse = userDAO.select(userDTO);
        }catch (Exception e) {
            responseDTO.setCode(ErrorStatus.SYSTEM_ERROR.getCode());
            responseDTO.setMsg(ErrorStatus.SYSTEM_ERROR.getMsg());
            return responseDTO;
        }

        if (selectResponse == null) {
            responseDTO.setCode("-1");
        } else {
            responseDTO.setCode("0");
            Cookie userId = new Cookie("userId", selectResponse.getUserId());
            httpServletResponse.addCookie(userId);
            Cookie mfnToken = new Cookie("mfnToken", "mfnToken"+selectResponse.getUserId());
            httpServletResponse.addCookie(mfnToken);
        }
        return responseDTO;
    }
}
