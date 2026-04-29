package model.main.catalogo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CatalogoTest {

    @Test
    void testClassCanBeLoaded() {
        assertDoesNotThrow(() -> Class.forName("model.main.catalogo.Catalogo"));
    }

    @Test
    void testMainMethodExists() {
        assertDoesNotThrow(() -> {
            Class<?> clazz = Class.forName("model.main.catalogo.Catalogo");
            clazz.getMethod("main", String[].class);
        });
    }
}
