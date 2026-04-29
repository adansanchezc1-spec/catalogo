package model.main.repository;

import model.main.menu.ProductoCatalogo;
import model.main.menu.ProveedorComercial;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CatalogoRepositoryJSONTest {

    private CatalogoRepositoryJSON repository;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("catalogo_test", ".json");
        // Crear una instancia con archivo temporal, pero como es hardcodeado, usar reflexión o cambiar constructor
        // Para simplicidad, usar el archivo por defecto y limpiar después
        repository = new CatalogoRepositoryJSON();
    }

    @AfterEach
    void tearDown() throws IOException {
        // Limpiar el archivo catalogo.json
        Path filePath = Paths.get("catalogo.json");
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
    }

    @Test
    void testSaveAndFindAll() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripción", 100.0, proveedor);

        repository.saveData(producto);

        List<ProductoCatalogo> productos = repository.findAll();
        assertEquals(1, productos.size());
        assertEquals("ID1", productos.get(0).getId());
    }

    @Test
    void testUpdateData() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripción", 100.0, proveedor);
        repository.saveData(producto);

        ProductoCatalogo updated = new ProductoCatalogo("ID1", "Producto Updated", "Descripción Updated", 200.0, proveedor);
        repository.updateData(updated);

        ProductoCatalogo found = repository.findDataById("ID1");
        assertEquals("Producto Updated", found.getNombre());
        assertEquals(200.0, found.getPrecio());
    }

    @Test
    void testDeleteData() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripción", 100.0, proveedor);
        repository.saveData(producto);

        repository.deleteData("ID1");

        List<ProductoCatalogo> productos = repository.findAll();
        assertTrue(productos.isEmpty());
    }

    @Test
    void testFindDataById() {
        ProveedorComercial proveedor = new ProveedorComercial("P1", "Proveedor Test");
        ProductoCatalogo producto = new ProductoCatalogo("ID1", "Producto Test", "Descripción", 100.0, proveedor);
        repository.saveData(producto);

        ProductoCatalogo found = repository.findDataById("ID1");
        assertNotNull(found);
        assertEquals("ID1", found.getId());

        ProductoCatalogo notFound = repository.findDataById("ID2");
        assertNull(notFound);
    }
}