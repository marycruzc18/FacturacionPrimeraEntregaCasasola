package com.example.demo.repository;

import com.example.demo.models.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentasRepository extends JpaRepository<Ventas, Long>  {
}
