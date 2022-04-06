package com.fidelity.usermanager.Services;

import com.fidelity.usermanager.Const.AppConst;
import com.fidelity.usermanager.DTOs.APIResponseDTO;
import com.fidelity.usermanager.Exceptions.BirthDateException;
import com.fidelity.usermanager.Exceptions.ResourceNotFound;
import com.fidelity.usermanager.Exceptions.UserException;
import com.fidelity.usermanager.Models.User;
import com.fidelity.usermanager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class UserService implements  IUserService{

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        System.out.println("Day Of BIRTH:"+ user.getBirthDate());
        long dayDiff =getDateDiffDays(user.getBirthDate());
        System.out.println("Day DIFF:"+ dayDiff);
        if (dayDiff<AppConst.MIN_AGE){
            throw new BirthDateException("api/users", "BirthDate", "You must be 18 years and above");
        }
        User res=userRepository.save(user);
        if(res==null){
            throw new ResourceNotFound(null, null, "Unable to create User at this time");
        }else{
            return  res;
        }

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        Optional<User> usr = userRepository.findById(Long.parseLong(id));
        if(usr.isPresent()){
            return usr.get();
        }
        throw new ResourceNotFound("/users/"+id, "id", String.format("User with id %s not found", id) );
    }

    @Override
    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    //return null;
    }

    @Override
    public User updateUserDetail(User user, String userId) {

        System.out.println("Day Of BIRTH:"+ user.getBirthDate());
        long dayDiff =getDateDiffDays(user.getBirthDate());
        System.out.println("Day DIFF:"+ dayDiff);
        if (dayDiff<AppConst.MIN_AGE){

            throw new BirthDateException("/users", "BirthDate", "You must be 18 years and above");
        }
        Optional<User> exUsr = userRepository.findById(Long.parseLong(userId));

        if(!exUsr.isPresent()){
            throw new ResourceNotFound("User", "Id", "Unable to find this record");
        }
        exUsr.get().setFirstName(user.getFirstName());
        exUsr.get().setLastName(user.getLastName());
        exUsr.get().setBirthDate(user.getBirthDate());
        exUsr.get().setEmail(user.getEmail());

        userRepository.save(exUsr.get());

        return exUsr.get();
    }

    @Override
    public void deleteUser(String id) {
         userRepository.delete(userRepository.getById(Long.parseLong(id)));
    }

    @Override
    public APIResponseDTO makeAPIResponse() {

        APIResponseDTO respObject = new APIResponseDTO();
        respObject.message ="";
        respObject.timestamp = new Date();
        respObject.status = "success";
        respObject.data = new ArrayList<>();
        respObject.errors= new HashMap<>();
        return respObject;
    }

    private long getDateDiffDays(Date mDate){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date firstDate = mDate;
           Date secondDate = new Date(System.currentTimeMillis());
        long diffInMillies = secondDate.getTime() - firstDate.getTime();
        long years_difference = (diffInMillies / (1000l*60*60*24*365));
        return  years_difference;
    }



}
