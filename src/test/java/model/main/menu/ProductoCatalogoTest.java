package model.main.menu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoCatalogoTest {

    @Test
    void testConstructorAndGetters() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripción Test", 100.0, proveedor);

        assertEquals("ID1", producto.getId());
        assertEquals("Producto Test", producto.getNombre());
        assertEquals("Descripción Test", producto.getDescripcion());
        assertEquals(100.0, producto.getPrecio());
        assertEquals(proveedor, producto.getProveedor());
    }

    @Test
    void testSetProveedor() {
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripción Test", 100.0, null);
        ProveedorComercial nuevoProveedor = new ProveedorComercial("P2", "Nuevo Proveedor");

        producto.setProveedor(nuevoProveedor);

        assertEquals(nuevoProveedor, producto.getProveedor());
    }

    @Test
    void testGetInfo() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripción Test", 100.0, proveedor);

        String expected = "ID: ID1, Nombre: Producto Test, Descripción: Descripción Test, Precio: 100.0, Proveedor: Proveedor Test";
        assertEquals(expected, producto.getInfo());
    }

    @Test
    void testGetInfoWithoutProveedor() {
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripción Test", 100.0, null);

        String expected = "ID: ID1, Nombre: Producto Test, Descripción: Descripción Test, Precio: 100.0, Proveedor: N/A";
        assertEquals(expected, producto.getInfo());
    }

    @Test
    void testToString() {
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripción Test", 100.0, null);

        assertEquals(producto.getInfo(), producto.toString());
    }
}