package com.fidelity.usermanager.Services;

import com.fidelity.usermanager.Models.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IUserService {
     User saveUser(User user);
     Iterable<User> getAllUsers();
    User getUserById(String id);
    List<User> findByEmail(String Email);
     User updateUserDetail(User user, String userId);
    void deleteUser(String id);
}
