package com.example.demo.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column

    private String nombre;

    @Column

    private String apellido;

    @Column

    private LocalDate fechaNacimiento;

    @Transient
    private int edad;

    // Constructor sin argumentos necesario para JPA
    public Cliente() {
    }
    // Constructor con todos los atributos
    public Cliente(String nombre, String apellido, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = calcularEdad();
    }


    // Método para calcular la edad
    private int calcularEdad() {
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }
}
