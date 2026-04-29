package model.main.service;

import java.util.List;

import model.main.menu.ProductoCatalogo;
import model.main.menu.ProveedorComercial;

public interface ICatalogoService {
    void crearProducto(ProductoCatalogo producto);

    List<ProductoCatalogo> obtenerProductos();

    void actualizarProducto(ProductoCatalogo producto);

    void eliminarProducto(String id);

    ProductoCatalogo obtenerProductoPorId(String id);

    void cambiarProveedor(String idProducto, ProveedorComercial nuevoProveedor);
}
