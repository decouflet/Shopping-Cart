package com.microservice.user.msvc_user.controller;

import com.microservice.user.msvc_user.entities.Credenciales;
import com.microservice.user.msvc_user.entities.User;
import com.microservice.user.msvc_user.entities.UserDTO;
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
    public void saveUser(@RequestBody User user, @RequestBody UserDTO userDTO) {
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

    @GetMapping("/search-from-cart")
    public UserDTO findUserFromCart(@RequestParam("name") String name,@RequestParam("password") String password){
        try {
            User user = userService.findByNameAndPassword(name, password);
            return UserDTO.builder()
                    .name(user.getName())
                    .password(user.getPassword())
                    .vip(user.isVip())
                    .id(user.getId())
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam("id") Long id){
        userService.deleteById(id);
    }
}
