package com.example.prestamos.controllers;

import com.example.prestamos.entities.Tipodocumento;
import com.example.prestamos.services.Response;
import com.example.prestamos.services.TipoDocumentoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractList;
import java.util.ArrayList;

@RestController
@RequestMapping("tipodocumento")
public class TipoDocumentoControllers {
    private TipoDocumentoService service;
    public TipoDocumentoControllers (TipoDocumentoService ser){

        this.service=ser;
    }
    @RequestMapping("get")
public ArrayList<Tipodocumento>get(){

        return this.service.selectAll();
}
@PostMapping("create")
public Response create(@RequestBody Tipodocumento data){

        return this.service.createDocumento(data);
}

}
