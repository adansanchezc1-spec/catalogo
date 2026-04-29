package model.main.controller;

import java.util.List;
import java.util.Objects;

import model.main.menu.Cliente;
import model.main.menu.Gerente;
import model.main.menu.ProductoCatalogo;
import model.main.menu.ProveedorComercial;
import model.main.service.CatalogoService;
import model.main.service.ICatalogoService;

public class CatalogoController {

    private final ICatalogoService service;

    public CatalogoController() {
        this(new CatalogoService());
    }

    public CatalogoController(ICatalogoService service) {
        this.service = Objects.requireNonNull(service, "El servicio no puede ser nulo");
    }

    public void crearProducto(ProductoCatalogo producto) {
        service.crearProducto(producto);
    }

    public List<ProductoCatalogo> obtenerProductos() {
        return service.obtenerProductos();
    }

    public void actualizarProducto(ProductoCatalogo producto) {
        service.actualizarProducto(producto);
    }

    public void eliminarProducto(String id) {
        service.eliminarProducto(id);
    }

    public ProductoCatalogo obtenerProductoPorId(String id) {
        return service.obtenerProductoPorId(id);
    }

    public void cambiarProveedor(String idProducto, ProveedorComercial nuevoProveedor) {
        service.cambiarProveedor(idProducto, nuevoProveedor);
    }

    public void interactuarConGerente(Gerente gerente) {
        System.out.println("Gerente: " + gerente.getNombre());
        System.out.println("El gerente esta revisando el catalogo...");
        gerente.solicitarCatalogo(this);
    }

    public void ejecutarGerente(Gerente gerente) {
        ProveedorComercial proveedor = new ProveedorComercial("Prov001", "Proveedor Ejemplo");
        ProductoCatalogo producto1 = new ProductoCatalogo("P001", "Camara", "Camara digital", 2500.0, proveedor);
        ProductoCatalogo producto2 = new ProductoCatalogo("P002", "Televisor", "TV 4K", 7800.0, proveedor);
        if (obtenerProductoPorId("P001") == null) {
            crearProducto(producto1);
        }
        if (obtenerProductoPorId("P002") == null) {
            crearProducto(producto2);
        }

        System.out.println("Productos guardados en catalogo.json:");
        obtenerProductos().forEach(producto -> System.out.println(producto.getInfo()));

        ProductoCatalogo productoActualizado = new ProductoCatalogo("P002", "Televisor", "TV 4K Smart", 8200.0, proveedor);
        actualizarProducto(productoActualizado);
        System.out.println("\nProducto actualizado:");
        System.out.println(obtenerProductoPorId("P002"));

        eliminarProducto("P001");
        System.out.println("\nProductos restantes despues de eliminar P001:");
        obtenerProductos().forEach(producto -> System.out.println(producto.getInfo()));

        gerente.realizarReunion(new Cliente("Cliente1", "123"));
        gerente.setProveedorComercial();
        gerente.proveerInformacion();
    }
}
