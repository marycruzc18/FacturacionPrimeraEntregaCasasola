package com.example.demo.controllers;

import com.example.demo.models.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cliente")
public class Controller {


    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String index(){
        return "Conectado";
    }

    //Muestar la información del cliente con la edad

    @GetMapping("/info")
    public ResponseEntity<?> obtenerInfoCliente() {
        try {
            List<Cliente> clientes = clienteRepository.findAll();

            if (!clientes.isEmpty()) {
                for (Cliente cliente : clientes) {
                    int edad = clienteService.calcularEdad(cliente.getFechaNacimiento());
                    cliente.setEdad(edad);
                }
                return new ResponseEntity<>(clientes, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No hay clientes en la base de datos", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener información de los clientes: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //Obtener todos los clientes
    @GetMapping("clientes")
    public ResponseEntity<?> getCliente() {
        try {
            List<Cliente> clientes = clienteRepository.findAll();
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno al obtener clientes: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //Guardar los clientes en la base de datos
    @PostMapping("alta")
    public String post(@RequestBody Cliente cliente) {
        try {
            clienteRepository.save(cliente);
            return "Guardado";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al guardar: " + e.getMessage();
        }
    }
   //Modificar cliente
    @PutMapping("modificar/{id}")
    public String update(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente updateCliente = clienteRepository.findById(id).get();
        updateCliente.setNombre(cliente.getNombre());
        updateCliente.setApellido(cliente.getApellido());
        updateCliente.setFechaNacimiento(cliente.getFechaNacimiento());
        clienteRepository.save(updateCliente);
        return "Modificado";
    }
   //Eliminar clientes
    @DeleteMapping("baja/{id}")
    public String delete (@PathVariable Long id){
        Cliente deleteCliente = clienteRepository.findById(id).get();
        clienteRepository.delete(deleteCliente);
        return "Eliminado";
    }


}




