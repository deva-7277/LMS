package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/get_users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/get_user")
    public User getUser(@RequestParam("id") int id){
        return userService.getUser(id);
    }

    // delete by a roll no
    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable ("id") int id){
        return userService.deleteUser(id);
    }

    // delete all the users
    @DeleteMapping("/delete-all-users")
    public String deleteAllUsers(){
        return userService.deleteAllUsers();
    }

    // update a user by id
    @PutMapping("/update-user")
    public String updateUser(@RequestParam("id") int id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

}