package model.main.menu;

import java.util.Objects;

public class Cliente {

    private final String nombre;
    private final String id;

    public Cliente(String nombre, String id) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        this.id = Objects.requireNonNull(id, "El ID no puede ser nulo");
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }
}
