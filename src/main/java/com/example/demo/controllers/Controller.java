package com.example.demo.controllers;

import com.example.demo.models.Cliente;
import com.example.demo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private Repository repo;

    @GetMapping
    public String index(){
        return "Conectado";
    }

    //Obtener todos los clientes
    @GetMapping("cliente")
    public List<Cliente> getCliente(){
        return repo.findAll();
    }
    //Guardar los clientes en la base de datos
    @PostMapping("alta")
    public String post(@RequestBody Cliente cliente) {
        try {
            repo.save(cliente);
            return "Guardado";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al guardar: " + e.getMessage();
        }
    }
   //Modificar cliente
    @PutMapping("modificar/{id}")
    public String update(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente updateCliente = repo.findById(id).get();
        updateCliente.setNombre(cliente.getNombre());
        updateCliente.setApellido(cliente.getApellido());
        updateCliente.setDocumento(cliente.getDocumento());
        repo.save(updateCliente);
        return "Modificado";
    }
   //Eliminar clientes
    @DeleteMapping("baja/{id}")
    public String delete (@PathVariable Long id){
        Cliente deleteCliente = repo.findById(id).get();
        repo.delete(deleteCliente);
        return "Eliminado";
    }


}




