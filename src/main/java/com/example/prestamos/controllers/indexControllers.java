package com.example.prestamos.controllers;

import com.example.prestamos.entities.User;
import com.example.prestamos.services.Response;
import com.example.prestamos.services.userService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class indexControllers {

    private userService userservice;

    public indexControllers(userService service) {
        this.userservice = service;

    }


    @RequestMapping("/")
    public String index() {

        return "Hola Developers";
    }

    @RequestMapping("getusuarios")
    public ArrayList<User> getUsuarios() {

        return this.userservice.selectall();
    }


    @PostMapping("create")
    public Response createuser(@RequestBody User request) {
        return this.userservice.createUser(request);

    }

    @RequestMapping("getuser/{id}")
    public User getUsuario(@PathVariable int id) {

        return this.userservice.selectById(id);
    }

    @DeleteMapping("delete/{id}")
    public Response deleteUsuario(@PathVariable int id) {

        return this.userservice.deleteUserById(id);
    }

    @PutMapping("update")
    public Response updateUser(@RequestBody User request) {
        return this.userservice.updateUser(request);

    }
    @PostMapping("auth")
    public Response auth(@RequestBody User request){
        return  this.userservice.loginUser(request);
    }
}