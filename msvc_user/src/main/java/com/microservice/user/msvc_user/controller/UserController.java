package com.microservice.user.msvc_user.controller;

import com.microservice.user.msvc_user.entities.User;
import com.microservice.user.msvc_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public void saveUser(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<?> findById(@RequestParam("id") Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam("id") Long id){
        userService.deleteById(id);
    }
}
