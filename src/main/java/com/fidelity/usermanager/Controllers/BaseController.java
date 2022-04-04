package com.fidelity.usermanager.Controllers;

import com.fidelity.usermanager.Models.User;
import com.fidelity.usermanager.Services.IUserService;
import com.fidelity.usermanager.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    public IUserService userService;
    @Autowired
    public BaseController(IUserService userService) {
        this.userService = userService;
    }

    public boolean isUserDuplicate(String Email){
        if(userService.findByEmail(Email)==null){
            return true;
        }
        return false;
    }
}
