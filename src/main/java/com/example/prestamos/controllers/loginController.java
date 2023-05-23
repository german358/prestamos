package com.example.prestamos.controllers;

import com.example.prestamos.dto.registroDTO;
import com.example.prestamos.entities.Tipodocumento;
import com.example.prestamos.entities.User;
import com.example.prestamos.services.Response;
import com.example.prestamos.services.TipoDocumentoService;
import com.example.prestamos.services.userService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
@RequestMapping("login")
public class loginController {
    private userService service;
    private TipoDocumentoService docservice;

    public loginController(userService service,TipoDocumentoService docservice){

        this.service=service;
        this.docservice=docservice;
    }



    @GetMapping("login")
    public String login(){

        return "login/login";
    }
    @GetMapping("registro")
    //cargamos los documentos desde la logica de negocio
    public String registro(Model tiposdedocumento){
        ArrayList<Tipodocumento>tiposdocumentosDB=this.docservice.selectAll();
        //pasamos la info al model de thymeleaf
        tiposdedocumento.addAttribute("misdocumentos",tiposdocumentosDB);

        return "login/registro";


    }
@PostMapping("postlogin")
    public RedirectView postlogin(User data){
    Response response=this.service.createUser(data);
    if(response.getCode()==200){
return new RedirectView("/inicio");
    }
     else {return new RedirectView("/login/error");

    }
    }
@GetMapping("error")
public String error(){

        return "login/error";
}
@PostMapping("postregistro")

public RedirectView postregistro(registroDTO data){
        if(data.getPassword().equals(null)|| data.getPassword().equals("")){
            return new RedirectView("/login/error");
        }

        if(!data.getPassword().equals(data.getValidaPassword())){
            return new RedirectView("/login/error");
        }

        //mapping
User user =new User();
        user.setNombres(data.getNombres());
        user.setApellidos(data.getApellidos());
        user.setNumeroDocumento(data.getNumeroDocumento());
        user.setCorreoElectronico(data.getCorreoElectronico());
        user.setTipodocumento(data.getTipodocumento());
        user.setPassword(data.getPassword());

        Response response=this.service.createUser(user);
    if(response.getCode()==200){
        return new RedirectView("/inicio");
    }
    else {
        return new RedirectView("/login/error");

    }




}

}
