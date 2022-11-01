package com.example.registration.service;


import com.example.registration.model.User;
import com.example.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User registerUser(String login, String password, String email) {
        if (login != null && password != null) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            return userRepository.save(user);
        } else {
            return null;
        }
    }
    public User authetication (String login, String password){
        return userRepository.findByLoginAndPassword(login,password).orElse(null);
    }
}

