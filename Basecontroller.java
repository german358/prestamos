package com.example.prestamos.controllers;

import com.example.prestamos.entities.User;
import com.example.prestamos.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class Basecontroller {
    @Autowired
    protected userService service;



    protected User seguridad(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //tomo el correo que nos guarda spring security en getname
        String currentPrincipalName = authentication.getName();
        User user = this.service.selectByUserName(currentPrincipalName);
        return  user;
    }
}
