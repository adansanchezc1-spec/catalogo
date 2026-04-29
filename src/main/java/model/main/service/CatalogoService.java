package model.main.service;

import java.util.List;
import java.util.Objects;

import model.main.menu.ProductoCatalogo;
import model.main.menu.ProveedorComercial;
import model.main.repository.CatalogoRepositoryJSON;
import model.main.repository.ICatalogoRepository;

public class CatalogoService implements ICatalogoService {
    private final ICatalogoRepository repository;

    public CatalogoService() {
        this(new CatalogoRepositoryJSON());
    }

    public CatalogoService(ICatalogoRepository repository) {
        this.repository = Objects.requireNonNull(repository, "El repositorio no puede ser nulo");
    }

    @Override
    public void crearProducto(ProductoCatalogo producto) {
        repository.saveData(producto);
    }

    @Override
    public List<ProductoCatalogo> obtenerProductos() {
        return repository.findAll();
    }

    @Override
    public void actualizarProducto(ProductoCatalogo producto) {
        repository.updateData(producto);
    }

    @Override
    public void eliminarProducto(String id) {
        repository.deleteData(id);
    }

    @Override
    public ProductoCatalogo obtenerProductoPorId(String id) {
        return repository.findDataById(id);
    }

    @Override
    public void cambiarProveedor(String idProducto, ProveedorComercial nuevoProveedor) {
        ProductoCatalogo producto = obtenerProductoPorId(idProducto);
        if (producto != null) {
            producto.setProveedor(nuevoProveedor);
            actualizarProducto(producto);
        }
    }
}
