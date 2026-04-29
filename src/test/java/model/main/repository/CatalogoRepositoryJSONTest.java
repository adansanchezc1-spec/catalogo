package model.main.repository;

import model.main.menu.ProductoCatalogo;
import model.main.menu.ProveedorComercial;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogoRepositoryJSONTest {

    private CatalogoRepositoryJSON repository;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("catalogo_test", ".json");
        repository = new CatalogoRepositoryJSON(tempFile);
    }

    @AfterEach
    void tearDown() throws IOException {
        if (Files.exists(tempFile)) {
            Files.delete(tempFile);
        }
    }

    @Test
    void testSaveAndFindAll() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripcion", 100.0, proveedor);

        repository.saveData(producto);

        List<ProductoCatalogo> productos = repository.findAll();
        assertEquals(1, productos.size());
        assertEquals("ID1", productos.get(0).getId());
    }

    @Test
    void testUpdateData() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripcion", 100.0, proveedor);
        repository.saveData(producto);

        ProductoCatalogo updated = new ProductoCatalogo("ID1", "Producto Updated", "Descripcion Updated", 200.0, proveedor);
        repository.updateData(updated);

        ProductoCatalogo found = repository.findDataById("ID1");
        assertEquals("Producto Updated", found.getNombre());
        assertEquals(200.0, found.getPrecio());
    }

    @Test
    void testDeleteData() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripcion", 100.0, proveedor);
        repository.saveData(producto);

        repository.deleteData("ID1");

        List<ProductoCatalogo> productos = repository.findAll();
        assertTrue(productos.isEmpty());
    }

    @Test
    void testFindDataById() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripcion", 100.0, proveedor);
        repository.saveData(producto);

        ProductoCatalogo found = repository.findDataById("ID1");
        assertNotNull(found);
        assertEquals("ID1", found.getId());

        ProductoCatalogo notFound = repository.findDataById("ID2");
        assertNull(notFound);
    }
}
