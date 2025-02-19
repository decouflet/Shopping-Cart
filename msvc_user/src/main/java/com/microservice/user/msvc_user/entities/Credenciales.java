package com.microservice.user.msvc_user.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credenciales {
    private String name;
    private String password;
}
