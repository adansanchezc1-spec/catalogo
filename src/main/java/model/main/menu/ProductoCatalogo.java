/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.main.menu;

/**
 *
 * @author ADAN
 */
public class ProductoCatalogo {
    private String id;
    private String nombre;
    private String descripcion;
    private double precio;
    private ProveedorComercial proveedor;

    public ProductoCatalogo(String id, String nombre, String descripcion, double precio, ProveedorComercial proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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
        return "ID: " + id + ", Nombre: " + nombre + ", Descripción: " + descripcion + ", Precio: " + precio + ", Proveedor: " + (proveedor != null ? proveedor.getNombre() : "N/A");
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
