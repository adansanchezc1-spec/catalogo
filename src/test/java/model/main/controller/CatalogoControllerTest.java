package model.main.controller;

import model.main.menu.ProductoCatalogo;
import model.main.menu.ProveedorComercial;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CatalogoControllerTest {

    private CatalogoController controller;

    @BeforeEach
    void setUp() {
        controller = new CatalogoController();
    }

    @AfterEach
    void tearDown() throws Exception {
        Path filePath = Paths.get("catalogo.json");
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
    }

    @Test
    void testCrearProducto() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripción", 100.0, proveedor);

        controller.crearProducto(producto);

        List<ProductoCatalogo> productos = controller.obtenerProductos();
        assertEquals(1, productos.size());
    }

    @Test
    void testObtenerProductos() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripción", 100.0, proveedor);
        controller.crearProducto(producto);

        List<ProductoCatalogo> productos = controller.obtenerProductos();
        assertFalse(productos.isEmpty());
    }

    @Test
    void testActualizarProducto() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripción", 100.0, proveedor);
        controller.crearProducto(producto);

        ProductoCatalogo updated = new ProductoCatalogo("ID1", "Producto Updated", "Descripción", 200.0, proveedor);
        controller.actualizarProducto(updated);

        ProductoCatalogo found = controller.obtenerProductoPorId("ID1");
        assertEquals("Producto Updated", found.getNombre());
    }

    @Test
    void testEliminarProducto() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripción", 100.0, proveedor);
        controller.crearProducto(producto);

        controller.eliminarProducto("ID1");

        List<ProductoCatalogo> productos = controller.obtenerProductos();
        assertTrue(productos.isEmpty());
    }

    @Test
    void testObtenerProductoPorId() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripción", 100.0, proveedor);
        controller.crearProducto(producto);

        ProductoCatalogo found = controller.obtenerProductoPorId("ID1");
        assertNotNull(found);

        ProductoCatalogo notFound = controller.obtenerProductoPorId("ID2");
        assertNull(notFound);
    }

    @Test
    void testCambiarProveedor() {
        ProveedorComercial proveedor1 = new ProveedorComercial("P1", "Proveedor 1");
        ProveedorComercial proveedor2 = new ProveedorComercial("P2", "Proveedor 2");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripción", 100.0, proveedor1);
        controller.crearProducto(producto);

        controller.cambiarProveedor("ID1", proveedor2);

        ProductoCatalogo updated = controller.obtenerProductoPorId("ID1");
        assertEquals(proveedor2, updated.getProveedor());
    }
}