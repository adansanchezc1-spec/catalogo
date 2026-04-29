package model.main.menu;

import java.util.Objects;

public class ProveedorComercial {
    private final String id;
    private final String nombre;

    public ProveedorComercial(String id, String nombre) {
        this.id = Objects.requireNonNull(id, "El ID no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
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
