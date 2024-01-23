package com.example.demo.controllers;

import com.example.demo.models.Productos;
import com.example.demo.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductosController {
    @Autowired
    private ProductosRepository pro;

    //Obtener todos los productos
    @GetMapping("productos")
    public List<Productos> getProductos(){
        return pro.findAll();
    }
    //Guardar los productos en la base de datos
    @PostMapping("altaproductos")
    public String post(@RequestBody Productos productos) {
        try {
            pro.save(productos);
            return "Producto Guardado";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al guardar producto: " + e.getMessage();
        }
    }

    //Modificar productos

    @PutMapping("modificarproductos/{id}")
    public String update(@PathVariable Long id, @RequestBody Productos productos) {
        Productos updateProductos = pro.findById(id).get();
        updateProductos.setDescripcion(productos.getDescripcion());
        updateProductos.setCodigo(productos.getCodigo());
        updateProductos.setStock(productos.getStock());
        updateProductos.setPrecio(productos.getPrecio());
        pro.save(updateProductos);
        return "Producto Modificado";
    }

    //eliminar productos
    @DeleteMapping("bajaproductos/{id}")
    public String delete(@PathVariable Long id){

        Productos deleteProductos = pro.findById(id).get();
        pro.delete(deleteProductos);
        return "Producto Eliminado";
    }
}