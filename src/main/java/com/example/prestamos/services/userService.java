package com.example.prestamos.services;


import com.example.prestamos.entities.User;
import com.example.prestamos.repository.IUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class userService {

    private IUserRepository userRepository;

    public userService(IUserRepository rep) {
        this.userRepository = rep;

    }

    public ArrayList<User> selectall() {
        return (ArrayList<User>) this.userRepository.findAll();

    }


    public Response createUser(User data) {
        Response response = new Response();
        if (data.getPassword().equals(null) || data.getPassword().equals("")) {
            response.setCode(500);
            response.setMessage("Error, su contraseña no es válida.");
            return response;
        }
        ArrayList<User> existe = this.userRepository.existeCorreo(data.getCorreoElectronico());
        if (existe != null && existe.size() > 0) {
            response.setCode(200);
            response.setMessage("Error, el correo electronico ya esta en uso");
            return response;
        }
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        data.setPassword(encrypt.encode(data.getPassword()));
        this.userRepository.save(data);
        response.setCode(200);
        response.setMessage("Usuario registrado exitosamente");
        return response;
    }

    public User selectById(int id) {
        Optional<User> existe = this.userRepository.findById(id);
        if (existe.isPresent()) {
            return existe.get();
        } else {
            return null;

        }
    }

    public Response deleteUserById(int id) {
        Response response = new Response();
        try {

            this.userRepository.deleteById(id);
            response.setCode(200);
            response.setMessage("Usuario eliminado exitosamente");
            return response;
        } catch (Exception ex) {
            response.setCode(500);
            response.setMessage("Error" + ex.getMessage());
            return response;
        }


    }

    public Response updateUser(User data) {
        Response response = new Response();
        if (data.getId() == 0) {
            response.setCode(500);
            response.setMessage("Error el id del usuario no es valido");
            return response;
        }

        //validamos si el usuario que desea actualizar existe
        User exists = selectById(data.getId());
        if (exists == null) {
            response.setCode(500);
            response.setMessage("Error el usuario no existe en la base de datos");
            return response;
        }

        exists.setCorreoElectronico(data.getCorreoElectronico());
        this.userRepository.save(exists);
        response.setCode(200);
        response.setMessage("Usuario modificado exitosamente");

        return response;
    }

    public Response loginUser(User data) {
        Response response = new Response();
        if (data.getPassword().equals(null) || data.getPassword().equals("")) {
            response.setCode(500);
            response.setMessage("Error, su contraseña no es válida.");
            return response;
        }
        ArrayList<User> existe = this.userRepository.validaCredenciales(data.getCorreoElectronico(), data.getPassword());
        if (existe != null && existe.size() > 0) {
            response.setCode(200);
            response.setMessage("Usuario validado exitosamente");
            return response;
        }
        response.setCode(500);
        response.setMessage("sus datos no son validos");
        return response;
    }

    public Response updateUserName(User data) {
        Response response = new Response();
        if (data.getId() == 0) {
            response.setCode(500);
            response.setMessage("Error el id del usuario no es valido");
            return response;
        }

        //validamos si el usuario que desea actualizar existe
        User exists = selectById(data.getId());
        if (exists == null) {
            response.setCode(500);
            response.setMessage("Error el usuario no existe en la base de datos");
            return response;
        }
        exists.setNombres(data.getNombres());
        exists.setApellidos(data.getApellidos());
        this.userRepository.save(exists);
        response.setCode(200);
        response.setMessage("Usuario modificado exitosamente");

        return response;
    }

    public User selectByUserName(String username) {
        User existe = this.userRepository.findByUserName(username);

        return existe;
    }
}