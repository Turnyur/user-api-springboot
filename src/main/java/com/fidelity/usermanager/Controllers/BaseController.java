package com.fidelity.usermanager.Controllers;

import com.fidelity.usermanager.Const.AppConst;
import com.fidelity.usermanager.Exceptions.AuthorizationException;
import com.fidelity.usermanager.Exceptions.UserException;
import com.fidelity.usermanager.Models.User;
import com.fidelity.usermanager.Services.IUserService;
import com.fidelity.usermanager.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseController {

    public IUserService userService;
    @Autowired
    public BaseController(IUserService userService) {
        this.userService = userService;
    }

    protected boolean isUserDuplicate(String email){
        List<User> users =  userService.findByEmail(email);
        if(users.size()>0){
            return true;
        }
        return false;
    }
    protected void validateToken(String authorization) {
       // logger.info("TOKEN: " +authorization);
        if(authorization.equals(AppConst.TOKEN)){

        }else{
            throw new AuthorizationException("Authorization", "Invalid token");
        }
    }


}
