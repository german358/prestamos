package com.example.prestamos.repository;


import com.example.prestamos.entities.Tipodocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ITipoDocumentoRepository extends JpaRepository<Tipodocumento,Integer> {
@Query("SELECT t FROM Tipodocumento t WHERE t.nombre= ?1")
    ArrayList<Tipodocumento> findByNombre(String nombre);



}
