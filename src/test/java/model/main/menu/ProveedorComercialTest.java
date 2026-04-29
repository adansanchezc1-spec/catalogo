package model.main.menu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProveedorComercialTest {

    @Test
    void testConstructorAndGetters() {
        ProveedorComercial proveedor = new ProveedorComercial("ID1", "Proveedor Test");

        assertEquals("ID1", proveedor.getId());
        assertEquals("Proveedor Test", proveedor.getNombre());
    }

    @Test
    void testCrearProductoEjemplo() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");

        ProductoCatalogo producto = proveedor.crearProductoEjemplo();

        assertEquals("P003", producto.getId());
        assertEquals("Laptop", producto.getNombre());
        assertEquals("Laptop gaming", producto.getDescripcion());
        assertEquals(15000.0, producto.getPrecio());
        assertEquals(proveedor, producto.getProveedor());
    }
}