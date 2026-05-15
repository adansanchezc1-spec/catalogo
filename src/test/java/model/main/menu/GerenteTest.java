package model.main.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GerenteTest {

    @Test
    void testConstructorAndGetters() {
        Gerente gerente = new Gerente("ID1", "Gerente Test");

        assertEquals("Gerente Test", gerente.getNombre());
        assertEquals("ID1", gerente.getId());
    }

    @Test
    void testSolicitarCatalogo() {
        Gerente gerente = new Gerente("ID1", "Gerente Test");

        assertNotNull(gerente);
    }
}
