package com.example.demo.service;

import com.example.demo.models.Cliente;
import com.example.demo.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class ClienteService {


    public Cliente obtenerClienteConEdad(Cliente cliente) {
        int edad = calcularEdad(cliente.getFechaNacimiento());
        cliente.setEdad(edad);
        return cliente;
    }

    public int calcularEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento != null) {
            LocalDate fechaActual = LocalDate.now();
            return Period.between(fechaNacimiento, fechaActual).getYears();
        } else {
            // En caso de que la fecha de nacimiento sea nula, puedes manejarlo de la manera que prefieras
            // Aquí se retorna -1 como indicador de que no se pudo calcular la edad.
            return -1;
        }
    }

}
