package model.main.service;

import java.util.List;

import model.main.menu.ProductoCatalogo;
import model.main.menu.ProveedorComercial;
import model.main.repository.CatalogoRepositoryJSON;

/**
 *
 * @author ADAN
 */
public class CatalogoService {
    private final CatalogoRepositoryJSON repository;

    public CatalogoService() {
        this(new CatalogoRepositoryJSON());
    }

    public CatalogoService(CatalogoRepositoryJSON repository) {
        this.repository = repository;
    }

    public void crearProducto(ProductoCatalogo producto) {
        repository.saveData(producto);
    }

    public List<ProductoCatalogo> obtenerProductos() {
        return repository.findAll();
    }

    public void actualizarProducto(ProductoCatalogo producto) {
        repository.updateData(producto);
    }

    public void eliminarProducto(String id) {
        repository.deleteData(id);
    }

    public ProductoCatalogo obtenerProductoPorId(String id) {
        return repository.findDataById(id);
    }

    public void cambiarProveedor(String idProducto, ProveedorComercial nuevoProveedor) {
        ProductoCatalogo producto = obtenerProductoPorId(idProducto);
        if (producto != null) {
            producto.setProveedor(nuevoProveedor);
            actualizarProducto(producto);
        }
    }
}
