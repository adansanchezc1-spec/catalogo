package model.main.catalogo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CatalogoTest {

    @Test
    public void testClassCanBeLoaded() {
        // Test que la clase Catalogo se puede cargar sin errores
        Assertions.assertDoesNotThrow(() -> {
            Class.forName("model.main.catalogo.Catalogo");
        });
    }

    @Test
    public void testMainMethodExists() {
        // Test que el método main existe
        Assertions.assertDoesNotThrow(() -> {
            Class<?> clazz = Class.forName("model.main.catalogo.Catalogo");
            clazz.getMethod("main", String[].class);
        });
    }
}