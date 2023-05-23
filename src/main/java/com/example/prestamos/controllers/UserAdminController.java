package com.example.prestamos.controllers;

import com.example.prestamos.dto.createUserDTO;
import com.example.prestamos.dto.updateUserDto;
import com.example.prestamos.entities.Tipodocumento;
import com.example.prestamos.entities.User;
import com.example.prestamos.services.Response;
import com.example.prestamos.services.TipoDocumentoService;
import com.example.prestamos.services.userService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.Id;
import java.util.ArrayList;

@Controller
@RequestMapping("admin")
public class UserAdminController {
    private userService service;
    private TipoDocumentoService docservice;

    public UserAdminController(userService service, TipoDocumentoService documentoService) {
        this.docservice = documentoService;

        this.service = service;
    }

    @GetMapping("usuarios")
    public String usuariosregistrados(Model usuarios) {
        ArrayList<User> usersDB = this.service.selectall();
        usuarios.addAttribute("usuarios", usersDB);
        return "/useradmin/usuariosregistrados";

    }

    @GetMapping("edituser/{id}")
    public String edituser(@PathVariable int id, Model data) {
        User userinfo = this.service.selectById(id);
        data.addAttribute("user", userinfo);
        ArrayList<Tipodocumento> documentos = docservice.selectAll();
        data.addAttribute("misdocumentos", documentos);
        return "useradmin/edituser";
    }

    @PostMapping("edituserpost")
    public RedirectView edituserpost(updateUserDto data) {

        //mapping de los datos
        User newUser = new User();
        newUser.setNombres(data.getNombres());
        newUser.setApellidos(data.getApellidos());
        newUser.setId(data.getId());
        Response response = this.service.updateUserName(newUser);
        return new RedirectView("/admin/usuarios");
    }

    @GetMapping("deleteuser/{id}")
    public RedirectView deleteUser(@PathVariable int id) {
        Response response = this.service.deleteUserById(id);
        return new RedirectView("/admin/usuarios");

    }

    @GetMapping("createusuario")
    public String createuser(Model data) {

        ArrayList<Tipodocumento> documentos = docservice.selectAll();
       data.addAttribute("misdocumentos", documentos);



        return "/useradmin/createuser";

        }
        @PostMapping("cread")
        public RedirectView createusrs(createUserDTO data){
        User emplle =new User();
        emplle.setNombres(data.getNombres());
        emplle.setApellidos(data.getApellidos());
        emplle.setCorreoElectronico(data.getCorreoElectronico());
        emplle.setNumeroDocumento(data.getNumeroDocumento());
        emplle.setPassword(data.getPassword());

            Response response=this.service.createUser(emplle);
            return new RedirectView("/admin/usuarios");
        }

@GetMapping("tiposdocumento")
        public  String tipodocumento(Model data){
        data.addAttribute("tiposdocumento",this.docservice.selectAll());
        data.addAttribute("titulopagina","Administracion de tipo de documentos");
        return "useradmin/tipodocumento";
        }
    }



