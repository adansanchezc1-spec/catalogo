package model.main.menu;

import java.util.Objects;

public class ProductoCatalogo {
    private final String id;
    private final String nombre;
    private final String descripcion;
    private final double precio;
    private ProveedorComercial proveedor;

    public ProductoCatalogo(String id, String nombre, String descripcion, double precio, ProveedorComercial proveedor) {
        this.id = Objects.requireNonNull(id, "El ID no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        this.descripcion = Objects.requireNonNull(descripcion, "La descripcion no puede ser nula");
        this.precio = precio;
        this.proveedor = proveedor;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public ProveedorComercial getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorComercial proveedor) {
        this.proveedor = proveedor;
    }

    public String getInfo() {
        return "ID: " + id
                + ", Nombre: " + nombre
                + ", Descripción: " + descripcion
                + ", Precio: " + precio
                + ", Proveedor: " + (proveedor != null ? proveedor.getNombre() : "N/A");
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
