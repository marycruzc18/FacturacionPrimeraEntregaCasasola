package com.example.demo.controllers;


import com.example.demo.models.Ventas;
import com.example.demo.repository.VentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VentasController {
    @Autowired
    private VentasRepository ven;

    // Obtener ventas
    @GetMapping("ventas")
    public List<Ventas> getVentas(){
        return  ven.findAll();
    }

    //Guardar ventas en la base de datos
    @PostMapping("altaventas")
    public String post(@RequestBody Ventas ventas) {
        try {
            ven.save(ventas);
            return "Venta Guardada";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al guardar la venta: " + e.getMessage();
        }
    }

   //Eliminar venta
    @DeleteMapping("bajaventa/{id}")
    public String delete(@PathVariable Long id){

        Ventas deleteVentas = ven.findById(id).get();
        ven.delete(deleteVentas);
        return "Venta Eliminada";
    }
}
