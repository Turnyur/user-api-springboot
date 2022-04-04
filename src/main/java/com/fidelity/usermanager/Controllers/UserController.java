package com.fidelity.usermanager.Controllers;

import com.fidelity.usermanager.Const.AppConst;
import com.fidelity.usermanager.Exceptions.ResourceNotFound;
import com.fidelity.usermanager.Exceptions.UserException;
import com.fidelity.usermanager.Models.User;
import com.fidelity.usermanager.Services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")

public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    private IUserService userService;
    @Autowired
    public UserController(IUserService userService) {

        this.userService = userService;
    }

    public boolean isUserDuplicate(String email){
       List<User> users =  userService.findByEmail(email);
        if(users.size()>1){
            return true;
        }
        return false;
    }
    @GetMapping
    public ResponseEntity<Iterable<User>> getUsers(@RequestHeader String authorization){

       validateToken(authorization);
        return new ResponseEntity<Iterable<User>>(userService.getAllUsers(),HttpStatus.OK);
    }

    private void validateToken(String authorization) {
        logger.info("TOKEN: " +authorization);

        if(authorization.equals(AppConst.TOKEN)){

        }else{
            throw new UserException("Authorization", "Invalid token");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@RequestHeader String authorization, @PathVariable String id) {
        validateToken(authorization);
        return new ResponseEntity<User>(userService.getUserById(id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@RequestHeader String authorization, @PathVariable String id) {
        validateToken(authorization);
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUserDetails(@RequestHeader String authorization, @Valid @RequestBody User user, @PathVariable String id ) {
        validateToken(authorization);

        User res =userService.updateUserDetail(user, id);
       logger.info("RETURNED USER:"+ res);
        return new ResponseEntity<User>(res, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestHeader String authorization, @Valid @RequestBody User user) {
        validateToken(authorization);
       boolean dupStatus = isUserDuplicate(user.getEmail());
        logger.info("DUP_STATUS: "+dupStatus);
        logger.info("USER: "+user);
        if(dupStatus){

            throw new ResourceNotFound("User", "Email", "A user has already been registered with this email");
        }else{
            return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
        }


    }



    }

