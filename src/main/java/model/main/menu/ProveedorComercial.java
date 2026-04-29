/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.main.menu;

import java.util.Objects;

/**
 *
 * @author ADAN
 */
public class ProveedorComercial {
    private String id;
    private String nombre;  
    public ProveedorComercial(String id,String nombre){
        this.id=id;
        this.nombre=nombre;
    }

     public String getId() {
        return id;
    }

     public String getNombre() {
        return nombre;
    }

    public ProductoCatalogo crearProductoEjemplo() {
        return new ProductoCatalogo("P003", "Laptop", "Laptop gaming", 15000.0, this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProveedorComercial other)) {
            return false;
        }
        return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
