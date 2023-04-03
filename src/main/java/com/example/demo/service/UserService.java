package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(User user){

        userRepository.save(user);
        return "User added";
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(int id){
        return userRepository.findById(id).get();
    }

    public String updateUser(int id, User user) {
        User existUser = null;
        try {
           existUser = getUser(id);
        }
        catch (Exception e) {
            if (existUser == null) return "User not found with id " + id;
        }
        existUser = user;
        userRepository.save(existUser);
        return "User with user id "+id+" successfully updated";
    }

    public String deleteUser(int id ) {
        userRepository.deleteById(id);
        return "User deleted with id "+id;
    }

    public String deleteAllUsers() {
        userRepository.deleteAll();
        return "All users deleted";
    }
}