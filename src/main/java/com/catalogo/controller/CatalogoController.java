package com.catalogo.controller;

import com.catalogo.model.ProductoCatalogo;
import com.catalogo.service.CatalogoService;
import com.catalogo.view.CatalogoView;

import java.util.List;

/**
 * Controlador MVC — coordina las interacciones entre Vista y Servicio.
 * Principio SRP: solo delega; no contiene lógica de negocio ni de presentación.
 * Principio DIP: depende de abstracciones (servicio e interfaz de vista).
 */
public class CatalogoController {

    private final CatalogoService service;
    private final CatalogoView    view;

    public CatalogoController(CatalogoService service, CatalogoView view) {
        this.service = service;
        this.view    = view;
    }

    /** Crea un nuevo producto y notifica el resultado. */
    public void crearProducto(ProductoCatalogo producto) {
        try {
            service.crear(producto);
            view.mostrarMensaje("Producto '" + producto.getNombre() + "' creado con ID: " + producto.getId());
        } catch (IllegalArgumentException e) {
            view.mostrarError(e.getMessage());
        }
    }

    /** Obtiene y muestra todos los productos. */
    public List<ProductoCatalogo> obtenerProductos() {
        List<ProductoCatalogo> lista = service.listar();
        view.mostrarProductos(lista);
        return lista;
    }

    /** Actualiza un producto y notifica el resultado. */
    public void actualizarProducto(ProductoCatalogo producto) {
        try {
            service.actualizar(producto);
            view.mostrarMensaje("Producto '" + producto.getId() + "' actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            view.mostrarError(e.getMessage());
        }
    }

    /** Elimina un producto por ID y notifica el resultado. */
    public void eliminarProducto(String id) {
        try {
            service.eliminar(id);
            view.mostrarMensaje("Producto con ID '" + id + "' eliminado.");
        } catch (IllegalArgumentException e) {
            view.mostrarError(e.getMessage());
        }
    }
}
