package com.catalogo.service;

import com.catalogo.model.ProductoCatalogo;
import com.catalogo.repository.ICatalogoRepository;

import java.util.List;
import java.util.UUID;

public class CatalogoService {

    private final ICatalogoRepository repository;

    public CatalogoService(ICatalogoRepository repository) {
        this.repository = repository;
    }

    public void crear(ProductoCatalogo producto) {
        validarProducto(producto);
        if (producto.getId() == null || producto.getId().isBlank()) {
            producto.setId(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }
        repository.save(producto);
    }

    public List<ProductoCatalogo> listar() {
        return repository.findAll();
    }

    public void actualizar(ProductoCatalogo producto) {
        if (producto.getId() == null || producto.getId().isBlank()) {
            throw new IllegalArgumentException("El ID del producto es obligatorio para actualizar.");
        }
        validarProducto(producto);
        repository.update(producto);
    }

    public void eliminar(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El ID no puede ser nulo o vacio.");
        }
        repository.delete(id);
    }

    private void validarProducto(ProductoCatalogo producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (producto.getCategoria() == null || producto.getCategoria().isBlank()) {
            throw new IllegalArgumentException("La categoria es obligatoria.");
        }
    }
}
