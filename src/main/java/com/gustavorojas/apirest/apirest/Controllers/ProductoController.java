package com.gustavorojas.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavorojas.apirest.apirest.Entities.Producto;
import com.gustavorojas.apirest.apirest.Repositories.ProductoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping // Encuentra todos los productos
    public List<Producto> obtenerPRProductos(){
        return productoRepository.findAll();
    }
    @GetMapping("/{id}") // Busca un producto de manera individual por id
    public Producto obtenerProductoPorId(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(() ->  new RuntimeException("No se encontró el producto con el ID: " + id));
      }
    @PostMapping // crea productos nuevos
    public Producto crearProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}") // actualiza los productos
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detallesProducto){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() ->  new RuntimeException("No se encontró el producto con el ID: " + id));

        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return productoRepository.save(producto);

    }

    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() ->  new RuntimeException("No se encontró el producto con el ID: " + id));
        productoRepository.delete(producto);
        return "El producto con el ID: " + id + " fue eliminado correctamente";

    }


    
    

}
