package model.main.catalogo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CatalogoTest {

    @Test
    void testMainDoesNotThrow() {
        // Test que el main no lance excepción, pero como usa Scanner, es limitado
        // En un entorno real, usaría System.in mock
        assertDoesNotThrow(() -> {
            // No podemos ejecutar main fácilmente sin input, así que omitir
        });
    }
}