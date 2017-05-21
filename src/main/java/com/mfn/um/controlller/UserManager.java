package com.mfn.um.controlller;

import com.mfn.common.ErrorStatus;
import com.mfn.common.MfnContextUtils;
import com.mfn.common.SeqUtils;
import com.mfn.common.UserDTO;
import com.mfn.login.integration.UserDAO;
import com.mfn.um.dto.RegisterReponseDTO;
import com.mfn.um.dto.RegisterRequestDTO;
import com.mfn.um.dto.UploadPortraitResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by LPF on 2017/2/25.
 */
@RestController
@RequestMapping("usermanager")
public class UserManager {

    private static final Logger LOG = LoggerFactory.getLogger(UserManager.class);

    @Autowired
    @Qualifier("com.mfn.common.SeqUtils")
    SeqUtils seqUtils;

    @Autowired
    @Qualifier("com.mfn.login.integration.UserDAO")
    UserDAO userDAO;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    RegisterReponseDTO register(@RequestBody RegisterRequestDTO request,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) {
        LOG.info("{} UserManager.register request: {}", MfnContextUtils.getSeq(), request);

        RegisterReponseDTO responseDTO = new RegisterReponseDTO();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(request.getUsername());
        userDTO.setPassword(request.getPassword());
        try {
            userDAO.add(userDTO);
        }catch (Exception e) {
            LOG.error("DB error", e);
            responseDTO.setCode(ErrorStatus.SYSTEM_ERROR.getCode());
            responseDTO.setMsg(ErrorStatus.SYSTEM_ERROR.getMsg());
            return responseDTO;
        }
        return responseDTO;
    }

    @RequestMapping(value = "uploadPortrait", method = RequestMethod.POST)
    UploadPortraitResponseDTO uploadPortrait(@RequestParam(name = "file")MultipartFile file,
                                             HttpServletRequest httpServletRequest,
                                             HttpServletResponse httpServletResponse) {
        UploadPortraitResponseDTO responseDTO = new UploadPortraitResponseDTO();

        return responseDTO;
    }

}
