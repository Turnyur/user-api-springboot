package com.fidelity.usermanager.Controllers;

import com.fidelity.usermanager.Const.AppConst;
import com.fidelity.usermanager.DTOs.APIResponseDTO;
import com.fidelity.usermanager.Exceptions.EmailDuplicateException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")

public class UserController extends BaseController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    private IUserService userService;
    @Autowired
    public UserController(IUserService userService) {
        super(userService);
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<APIResponseDTO> getUsers(@RequestHeader String authorization){
       validateToken(authorization);
        APIResponseDTO response =userService.makeAPIResponse();
        response.code=HttpStatus.OK.value();
        response.path="api/users";
        List<?> users= userService.getAllUsers();
        response.data = users;
        return new ResponseEntity<APIResponseDTO>(response, HttpStatus.OK);
    }



    @GetMapping("{id}")
    public ResponseEntity<APIResponseDTO> getUser(@RequestHeader String authorization, @PathVariable String id) {
        validateToken(authorization);

        APIResponseDTO response =userService.makeAPIResponse();
        response.code=HttpStatus.OK.value();
        response.path="api/users/"+id;
        List<User> users =new ArrayList<User>();
        users.add(userService.getUserById(id));
        response.data = users;
        return new ResponseEntity<APIResponseDTO>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@RequestHeader String authorization, @PathVariable String id) {
        validateToken(authorization);
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PutMapping("{id}")
    public ResponseEntity<APIResponseDTO> updateUserDetails(@RequestHeader String authorization, @Valid @RequestBody User user, @PathVariable String id ) {
        validateToken(authorization);

        APIResponseDTO response =userService.makeAPIResponse();
        response.code=HttpStatus.OK.value();
        response.path="api/users/"+id;
        List<User> users =new ArrayList<User>();
        users.add(userService.updateUserDetail(user, id));
        response.data = users;

        return new ResponseEntity<APIResponseDTO>(response, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<APIResponseDTO> createUser(@RequestHeader String authorization, @Valid @RequestBody User user) {
        validateToken(authorization);
       boolean dupStatus = isUserDuplicate(user.getEmail());
        logger.info("DUP_STATUS: "+dupStatus);
        logger.info("USER: "+user);
        if(dupStatus){

            throw new EmailDuplicateException("/users", "Email", String.format("Duplicate email %s", user.getEmail()));
        }else{
            APIResponseDTO response =userService.makeAPIResponse();
            response.code=HttpStatus.CREATED.value();
            response.path="api/users";
            List<User> users =new ArrayList<User>();
            users.add(userService.saveUser(user));
            response.data = users;

            return new ResponseEntity<APIResponseDTO>(response, HttpStatus.CREATED);
        }


    }



    }

