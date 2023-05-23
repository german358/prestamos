package com.example.prestamos.controllers;

import com.example.prestamos.entities.Tipodocumento;
import com.example.prestamos.services.Response;
import com.example.prestamos.services.TipoDocumentoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
@RequestMapping("tipodocumento")

public class DocumentoController {
    private TipoDocumentoService service;
    public DocumentoController (TipoDocumentoService ser){

        this.service=ser;
    }




    @GetMapping("index")
public String index(Model documentos){
        //consulto los documentos ala DB segun mi logica de negocio
        ArrayList<Tipodocumento>documentosDB= this.service.selectAll();
        //armo el modelo que se le pasa a thymeleaf
        documentos.addAttribute("misdocumentos",documentosDB);
    return "documento/index";
}
@GetMapping("create")
public String create(){

        return "documento/create";
}


    @PostMapping("createdoc")
    public RedirectView create(Tipodocumento data){
        Response response=this.service.createDocumento(data);
        return new RedirectView("/tipodocumento/index");
    }

}
