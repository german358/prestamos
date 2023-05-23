package com.example.prestamos.services;

import com.example.prestamos.entities.Tipodocumento;
import com.example.prestamos.repository.ITipoDocumentoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TipoDocumentoService {

private ITipoDocumentoRepository repository;
public  TipoDocumentoService(ITipoDocumentoRepository rep){
    this.repository=rep;

}

public ArrayList<Tipodocumento>selectAll(){

    return (ArrayList<Tipodocumento>) this.repository.findAll();
}

public  Response createDocumento(Tipodocumento data){
    // debo de validar sie el documento ya existe
    ArrayList<Tipodocumento> documentos=this.repository.findByNombre(data.getNombre());
    if (documentos!=null&& documentos.size()>0){
        Response response=new Response();
        response.setCode(500);
        response.setMessage("El tipo de Documento ya existe");
        return  response;
    }

    this.repository.save(data);
    Response response=new Response();
    response.setCode(200);
    response.setMessage("Documento registrado exitosamente");
    return  response;
}
}
