package model.main.controller;

import model.main.menu.Gerente;
import model.main.menu.Cliente;
import model.main.menu.ProductoCatalogo;
import model.main.menu.ProveedorComercial;
import model.main.service.CatalogoService;
import java.util.List;

/**
 *
 * @author ADAN
 */
public class CatalogoController {

    private final CatalogoService service;

    public CatalogoController() {
        this(new CatalogoService());
    }

    public CatalogoController(CatalogoService service) {
        this.service = service;
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
        System.out.println("El gerente está revisando el catálogo...");
        gerente.solicitarCatalogo(this);
    }

    public void ejecutarGerente(Gerente gerente) {
        // Ejemplo de uso
        ProveedorComercial proveedor = new ProveedorComercial("Prov001", "Proveedor Ejemplo");
        ProductoCatalogo producto1 = new ProductoCatalogo("P001", "Cámara", "Cámara digital", 2500.0, proveedor);
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
        System.out.println("\nProductos restantes después de eliminar P001:");
        obtenerProductos().forEach(producto -> System.out.println(producto.getInfo()));

        gerente.realizarReunion(new Cliente("Cliente1", "123"));
        gerente.setProveedorComercial();
        gerente.proveerInformacion();
    }
}
