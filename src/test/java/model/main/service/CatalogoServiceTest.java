package model.main.service;

import model.main.menu.ProductoCatalogo;
import model.main.menu.ProveedorComercial;
import model.main.repository.CatalogoRepositoryJSON;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogoServiceTest {

    private CatalogoService service;
    private Path tempFile;

    @BeforeEach
    void setUp() throws Exception {
        tempFile = Files.createTempFile("catalogo_service_test", ".json");
        service = new CatalogoService(new CatalogoRepositoryJSON(tempFile));
    }

    @AfterEach
    void tearDown() throws Exception {
        if (Files.exists(tempFile)) {
            Files.delete(tempFile);
        }
    }

    @Test
    void testCrearProducto() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripcion", 100.0, proveedor);

        service.crearProducto(producto);

        List<ProductoCatalogo> productos = service.obtenerProductos();
        assertEquals(1, productos.size());
        assertEquals("ID1", productos.get(0).getId());
    }

    @Test
    void testObtenerProductos() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto1 = new ProductoCatalogo("ID1", "Producto 1", "Descripcion 1", 100.0, proveedor);
        ProductoCatalogo producto2 = new ProductoCatalogo("ID2", "Producto 2", "Descripcion 2", 200.0, proveedor);

        service.crearProducto(producto1);
        service.crearProducto(producto2);

        List<ProductoCatalogo> productos = service.obtenerProductos();
        assertEquals(2, productos.size());
    }

    @Test
    void testActualizarProducto() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripcion", 100.0, proveedor);
        service.crearProducto(producto);

        ProductoCatalogo updated = new ProductoCatalogo("ID1", "Producto Updated", "Descripcion Updated", 200.0, proveedor);
        service.actualizarProducto(updated);

        ProductoCatalogo found = service.obtenerProductoPorId("ID1");
        assertEquals("Producto Updated", found.getNombre());
    }

    @Test
    void testEliminarProducto() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripcion", 100.0, proveedor);
        service.crearProducto(producto);

        service.eliminarProducto("ID1");

        List<ProductoCatalogo> productos = service.obtenerProductos();
        assertTrue(productos.isEmpty());
    }

    @Test
    void testObtenerProductoPorId() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripcion", 100.0, proveedor);
        service.crearProducto(producto);

        ProductoCatalogo found = service.obtenerProductoPorId("ID1");
        assertNotNull(found);
        assertEquals("ID1", found.getId());

        ProductoCatalogo notFound = service.obtenerProductoPorId("ID2");
        assertNull(notFound);
    }

    @Test
    void testCambiarProveedor() {
        ProveedorComercial proveedor1 = new ProveedorComercial("P1", "Proveedor 1");
        ProveedorComercial proveedor2 = new ProveedorComercial("P2", "Proveedor 2");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripcion", 100.0, proveedor1);
        service.crearProducto(producto);

        service.cambiarProveedor("ID1", proveedor2);

        ProductoCatalogo updated = service.obtenerProductoPorId("ID1");
        assertEquals(proveedor2, updated.getProveedor());
    }
}
