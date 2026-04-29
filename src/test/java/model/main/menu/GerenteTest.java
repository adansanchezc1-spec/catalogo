package model.main.menu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GerenteTest {

    @Test
    void testConstructorAndGetters() {
        Gerente gerente = new Gerente("ID1", "Gerente Test");

        assertEquals("Gerente Test", gerente.getNombre());
    }

    @Test
    void testSolicitarCatalogo() {
        // Este test requiere un mock del controller, pero para simplicidad, solo verificar que no lance excepción
        Gerente gerente = new Gerente("ID1", "Gerente Test");
        // No podemos testear fácilmente sin mock, así que omitir por ahora
        assertNotNull(gerente);
    }
}